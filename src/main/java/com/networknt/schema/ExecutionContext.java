/*
 * Copyright (c) 2023 the original author or authors.
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

package com.networknt.schema;

import com.networknt.schema.annotation.JsonNodeAnnotations;
import com.networknt.schema.result.JsonNodeResults;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Stack;

/**
 * Stores the execution context for the validation run.
 */
public class ExecutionContext {
    private ExecutionConfig executionConfig;
    private CollectorContext collectorContext = null;
    private Stack<DiscriminatorContext> discriminatorContexts = null;
    private JsonNodeAnnotations annotations = null;
    private JsonNodeResults results = null;

    private JsonNodePath evaluationPath = new JsonNodePath(PathType.JSON_POINTER);
    private Deque<EvaluationState> evaluationStateStack = new ArrayDeque<>();

    public JsonNodePath getEvaluationPath() {
        return evaluationPath;
    }

    public void setEvaluationPath(JsonNodePath evaluationPath) {
        this.evaluationPath = evaluationPath;
    }

    public Deque<EvaluationState> getEvaluationStateStack() {
        return evaluationStateStack;
    }

    public void setEvaluationSchemaStack(Deque<EvaluationState> evaluationStateStack) {
        this.evaluationStateStack = evaluationStateStack;
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
    protected boolean hasAdjacentKeywordInEvaluationPath(String keyword) {
        Iterator<EvaluationState> evaluationStateIterator = this.evaluationStateStack.iterator();
        
        // Skip the first
        if (evaluationStateIterator.hasNext()) {
            evaluationStateIterator.next();
        }
        
        while (evaluationStateIterator.hasNext()) {
            EvaluationState state = evaluationStateIterator.next();;
            JsonSchema schema = state.getEvaluationSchema();
            JsonNodePath evaluationPath = state.getEvaluationPath();
            for (JsonValidator validator : schema.getValidators()) {
                if (keyword.equals(validator.getKeyword())) {
                    return true;
                }
            }
//            if (schema.getEvaluationPath().compareTo(evaluationPath) != 0) {
//                throw new RuntimeException("fail");
//            }
            Object element = evaluationPath.getElement(-1);
            if ("properties".equals(element) || "items".equals(element)) {
                // If there is a change in instance location then return false
                return false;
            }
        }
        return false;
    }

    /**
     * This is used during the execution to determine if the validator should fail fast.
     * <p>
     * This valid is determined by the previous validator.
     */
    private Boolean failFast = null;

    /**
     * Creates an execution context.
     */
    public ExecutionContext() {
        this(new ExecutionConfig(), null);
    }

    /**
     * Creates an execution context.
     * 
     * @param collectorContext the collector context
     */
    public ExecutionContext(CollectorContext collectorContext) {
        this(new ExecutionConfig(), collectorContext);
    }

    /**
     * Creates an execution context.
     * 
     * @param executionConfig the execution configuration
     */
    public ExecutionContext(ExecutionConfig executionConfig) {
        this(executionConfig, null);
    }

    /**
     * Creates an execution context.
     * 
     * @param executionConfig  the execution configuration
     * @param collectorContext the collector context
     */
    public ExecutionContext(ExecutionConfig executionConfig, CollectorContext collectorContext) {
        this.collectorContext = collectorContext;
        this.executionConfig = executionConfig;
    }

    /**
     * Gets the collector context.
     * 
     * @return the collector context
     */
    public CollectorContext getCollectorContext() {
        if (this.collectorContext == null) {
            this.collectorContext = new CollectorContext();
        }
        return this.collectorContext;
    }

    /**
     * Sets the collector context.
     * 
     * @param collectorContext the collector context
     */
    public void setCollectorContext(CollectorContext collectorContext) {
        this.collectorContext = collectorContext;
    }

    /**
     * Gets the execution configuration.
     * 
     * @return the execution configuration
     */
    public ExecutionConfig getExecutionConfig() {
        return executionConfig;
    }

    /**
     * Sets the execution configuration.
     * 
     * @param executionConfig the execution configuration
     */
    public void setExecutionConfig(ExecutionConfig executionConfig) {
        this.executionConfig = executionConfig;
    }

    public JsonNodeAnnotations getAnnotations() {
        if (this.annotations == null) {
            this.annotations = new JsonNodeAnnotations();
        }
        return annotations;
    }

    public JsonNodeResults getResults() {
        if (this.results == null) {
            this.results = new JsonNodeResults();
        }
        return results;
    }

    /**
     * Determines if the validator should immediately throw a fail fast exception if
     * an error has occurred.
     * <p>
     * This defaults to the execution config fail fast at the start of the execution.
     * 
     * @return true if fail fast
     */
    public boolean isFailFast() {
        if (this.failFast == null) {
            this.failFast = getExecutionConfig().isFailFast();
        }
        return failFast;
    }

    /**
     * Sets if the validator should immediately throw a fail fast exception if an
     * error has occurred.
     * 
     * @param failFast true to fail fast
     */
    public void setFailFast(boolean failFast) {
        this.failFast = failFast;
    }

    public DiscriminatorContext getCurrentDiscriminatorContext() {
        if (this.discriminatorContexts == null) {
            return null;
        }

        if (!this.discriminatorContexts.empty()) {
            return this.discriminatorContexts.peek();
        }
        return null; // this is the case when we get on a schema that has a discriminator, but it's not used in anyOf
    }

    public void enterDiscriminatorContext(final DiscriminatorContext ctx, @SuppressWarnings("unused") JsonNodePath instanceLocation) {
        if (this.discriminatorContexts == null) {
            this.discriminatorContexts = new Stack<>();
        }
        this.discriminatorContexts.push(ctx);
    }

    public void leaveDiscriminatorContextImmediately(@SuppressWarnings("unused") JsonNodePath instanceLocation) {
        this.discriminatorContexts.pop();
    }
}
