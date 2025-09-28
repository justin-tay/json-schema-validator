/*
 * Copyright (c) 2016 Network New Technologies Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.networknt.schema.keyword;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.function.Consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.ExecutionContext;
import com.networknt.schema.Schema;
import com.networknt.schema.SchemaLocation;
import com.networknt.schema.annotation.Annotation;
import com.networknt.schema.path.EvaluationPath;
import com.networknt.schema.path.NodePath;

/**
 * Abstract {@link KeywordValidator}.
 */
public abstract class AbstractKeywordValidator implements KeywordValidator {
    private final String keyword;
    protected final JsonNode schemaNode;
    protected final SchemaLocation schemaLocation;

    protected final NodePath evaluationPath;

    /**
     * Constructor.
     * @param keyword        the keyword
     * @param schemaNode     the schema node
     * @param schemaLocation the schema location
     * @param evaluationPath the evaluation path
     */
    public AbstractKeywordValidator(String keyword, JsonNode schemaNode, SchemaLocation schemaLocation, NodePath evaluationPath) {
        this.keyword = keyword;
        this.schemaNode = schemaNode;
        this.schemaLocation = schemaLocation;
        this.evaluationPath = evaluationPath;
    }

    /**
     * Constructor.
     * @param keyword        the keyword
     * @param schemaNode     the schema node
     * @param schemaLocation the schema location
     * @param evaluationPath the evaluation path
     */
    public AbstractKeywordValidator(Keyword keyword, JsonNode schemaNode, SchemaLocation schemaLocation, NodePath evaluationPath) {
        this(keyword.getValue(), schemaNode, schemaLocation, evaluationPath);
    }

    @Override
    public SchemaLocation getSchemaLocation() {
        return schemaLocation;
    }

    @Override
    public NodePath getEvaluationPath() {
        return evaluationPath;
    }

    @Override
    public String getKeyword() {
        return keyword;
    }

    /**
     * The schema node used to create the validator.
     * 
     * @return the schema node
     */
    public JsonNode getSchemaNode() {
        return this.schemaNode;
    }

    @Override
    public String toString() {
        return getEvaluationPath().getName(-1);
    }

    /**
     * Determine if annotations should be reported.
     * 
     * @param executionContext the execution context
     * @return true if annotations should be reported
     */
    protected boolean collectAnnotations(ExecutionContext executionContext) {
        return collectAnnotations(executionContext, getKeyword());
    }

    /**
     * Determine if annotations should be reported.
     * 
     * @param executionContext the execution context
     * @param keyword          the keyword
     * @return true if annotations should be reported
     */
    protected boolean collectAnnotations(ExecutionContext executionContext, String keyword) {
        return executionContext.getExecutionConfig().isAnnotationCollectionEnabled()
                && executionContext.getExecutionConfig().getAnnotationCollectionFilter().test(keyword);
    }

    /**
     * Puts an annotation.
     * 
     * @param executionContext the execution context
     * @param customizer to customize the annotation
     */
    protected void putAnnotation(ExecutionContext executionContext, Consumer<Annotation.Builder> customizer) {
        Annotation.Builder builder = Annotation.builder().evaluationPath(this.evaluationPath)
                .schemaLocation(this.schemaLocation).keyword(getKeyword());
        customizer.accept(builder);
        executionContext.getAnnotations().put(builder.build());
    }

    
    /**
     * Determines if the keyword exists adjacent in the evaluation path.
     * <p>
     * This does not check if the keyword exists in the current meta schema as this
     * can be a cross-draft case where the properties keyword is in a Draft 7 schema
     * and the unevaluatedProperties keyword is in an outer Draft 2020-12 schema.
     * <p>
     * The fact that the validator exists in the evaluation path implies that the
     * keyword was valid in whatever meta schema for that schema it was created for.
     * 
     * @param keyword the keyword to check
     * @return true if found
     */
    protected boolean hasAdjacentKeywordInEvaluationPath(ExecutionContext executionContext, String keyword) {
        Iterator<Object> evaluationSchemaPathIterator = executionContext.getEvaluationSchemaPath().descendingIterator();
        Iterator<Schema> evaluationSchemaIterator = executionContext.getEvaluationSchema().descendingIterator();

        ArrayDeque<Object> current = executionContext.getEvaluationSchemaPath().clone();

        // Skip the first as this is the path pointing to the current keyword eg. properties eg /$ref/properties
        // What is needed is the evaluationPath pointing to the current evaluationSchema eg /$ref
        if (evaluationSchemaPathIterator.hasNext()) {
            evaluationSchemaPathIterator.next();
            
            current.removeLast();
        }

        while (evaluationSchemaIterator.hasNext()) {
            Schema schema = evaluationSchemaIterator.next();
            for (KeywordValidator validator : schema.getValidators()) {
                if (keyword.equals(validator.getKeyword())) {
                    return true;
                }
            }
            String newPath = new EvaluationPath(current).toString();
            String oldPath = schema.getEvaluationPath().toString(); 
            if (!oldPath.equals(newPath)) {
                System.out.println("OLD: "+oldPath);
                System.out.println("NEW: "+newPath);
            }

            if (evaluationSchemaPathIterator.hasNext()) {
                Object evaluationPath = evaluationSchemaPathIterator.next();
                current.removeLast();
                if ("properties".equals(evaluationPath) || "items".equals(evaluationPath)) {
                    // If there is a change in instance location then return false
                    return false;
                }
            }
        }
        return false;
    }
}
