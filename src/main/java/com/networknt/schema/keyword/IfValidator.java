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

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.Error;
import com.networknt.schema.ExecutionContext;
import com.networknt.schema.Schema;
import com.networknt.schema.SchemaLocation;
import com.networknt.schema.path.NodePath;
import com.networknt.schema.utils.Flag;
import com.networknt.schema.SchemaContext;

import java.util.*;

/**
 * {@link KeywordValidator} for if.
 */
public class IfValidator extends BaseKeywordValidator {
    private final Schema ifSchema;
    private final Schema thenSchema;
    private final Schema elseSchema;

    public IfValidator(SchemaLocation schemaLocation, JsonNode schemaNode, Schema parentSchema, SchemaContext schemaContext) {
        super(KeywordType.IF_THEN_ELSE, schemaNode, schemaLocation, parentSchema, schemaContext);
        JsonNode node = parentSchema.getSchemaNode().get("if");
        if (node != null) {
            SchemaLocation schemaLocationOfSchema = parentSchema.getSchemaLocation().append("if");
            this.ifSchema = schemaContext.newSchema(schemaLocationOfSchema, node, parentSchema);
        } else {
            this.ifSchema = null;
        }
        node = parentSchema.getSchemaNode().get("then");
        if (node != null) {
            SchemaLocation schemaLocationOfSchema = parentSchema.getSchemaLocation().append("then");
            this.thenSchema = schemaContext.newSchema(schemaLocationOfSchema, node, parentSchema);
        } else {
            this.thenSchema = null;
        }
        node = parentSchema.getSchemaNode().get("else");
        if (node != null) {
            SchemaLocation schemaLocationOfSchema = parentSchema.getSchemaLocation().append("else");
            this.elseSchema = schemaContext.newSchema(schemaLocationOfSchema, node, parentSchema);
        } else {
            this.elseSchema = null;
        }
    }

    @Override
    public void validate(ExecutionContext executionContext, JsonNode node, JsonNode rootNode, NodePath instanceLocation) {
        boolean ifConditionPassed = false;

        // Save flag as nested schema evaluation shouldn't trigger fail fast
        boolean failFast = executionContext.isFailFast();
        List<Error> existingErrors = executionContext.getErrors();
        List<Error> test = new Flag<>();
        executionContext.setErrors(test);
        try {
            executionContext.setFailFast(false);
            this.ifSchema.validate(executionContext, node, rootNode, instanceLocation);
            ifConditionPassed = test.isEmpty();
        } finally {
            // Restore flag
            executionContext.setErrors(existingErrors);
            executionContext.setFailFast(failFast);
        }

        if (ifConditionPassed && this.thenSchema != null) {
            // The "if" keyword is a bit unusual as it actually handles multiple keywords
            // This removes the "if" in the evaluation path so the rest of the evaluation paths will be correct
            executionContext.evaluationPathRemoveLast();
            executionContext.evaluationPathAddLast("then");
            // For performance the "if" isn't replaced on the evaluation path
//            try {
                this.thenSchema.validate(executionContext, node, rootNode, instanceLocation);
//            } finally {
//                executionContext.evaluationPathRemoveLast();
//                executionContext.evaluationPathAddLast("if");
//            }
        } else if (!ifConditionPassed && this.elseSchema != null) {
            executionContext.evaluationPathRemoveLast();
            executionContext.evaluationPathAddLast("else");
//            try {
                this.elseSchema.validate(executionContext, node, rootNode, instanceLocation);
//            } finally {
//                executionContext.evaluationPathRemoveLast();
//                executionContext.evaluationPathAddLast("if");
//            }
        }
    }

    @Override
    public void preloadSchema() {
        if (null != this.ifSchema) {
            this.ifSchema.initializeValidators();
        }
        if (null != this.thenSchema) {
            this.thenSchema.initializeValidators();
        }
        if (null != this.elseSchema) {
            this.elseSchema.initializeValidators();
        }
    }

    @Override
    public void walk(ExecutionContext executionContext, JsonNode node, JsonNode rootNode, NodePath instanceLocation, boolean shouldValidateSchema) {
        // The "if" keyword is a bit unusual as it actually handles multiple keywords
        // This removes the "if" in the evaluation path so the rest of the evaluation paths will be correct

        boolean checkCondition = node != null && shouldValidateSchema;
        boolean ifConditionPassed = false;

        // Save flag as nested schema evaluation shouldn't trigger fail fast
        boolean failFast = executionContext.isFailFast();
        List<Error> existingErrors = executionContext.getErrors();
        List<Error> test = new ArrayList<>();
        executionContext.setErrors(test);
        try {
            executionContext.setFailFast(false);
            this.ifSchema.walk(executionContext, node, rootNode, instanceLocation, shouldValidateSchema);
            ifConditionPassed = test.isEmpty();
        } finally {
            // Restore flag
            executionContext.setErrors(existingErrors);
            executionContext.setFailFast(failFast);
        }
        if (!checkCondition) {
            if (this.thenSchema != null) {
                executionContext.evaluationPathRemoveLast();
                executionContext.evaluationPathAddLast("then");
//                try {
                    this.thenSchema.walk(executionContext, node, rootNode, instanceLocation, shouldValidateSchema);
//                } finally {
//                    executionContext.evaluationPathRemoveLast();
//                    executionContext.evaluationPathAddLast("if");
//                }
            }
            if (this.elseSchema != null) {
                executionContext.evaluationPathRemoveLast();
                executionContext.evaluationPathAddLast("else");
//                try {
                    this.elseSchema.walk(executionContext, node, rootNode, instanceLocation, shouldValidateSchema);
//                } finally {
//                    executionContext.evaluationPathRemoveLast();
//                    executionContext.evaluationPathAddLast("if");
//                }
            }
        } else {
            if (this.thenSchema != null && ifConditionPassed) {
                executionContext.evaluationPathRemoveLast();
                executionContext.evaluationPathAddLast("then");
//                try {
                    this.thenSchema.walk(executionContext, node, rootNode, instanceLocation, shouldValidateSchema);
//                } finally {
//                    executionContext.evaluationPathRemoveLast();
//                    executionContext.evaluationPathAddLast("if");
//                }
            } else if (this.elseSchema != null && !ifConditionPassed) {
                executionContext.evaluationPathRemoveLast();
                executionContext.evaluationPathAddLast("else");
//                try {
                    this.elseSchema.walk(executionContext, node, rootNode, instanceLocation, shouldValidateSchema);
//                } finally {
//                    executionContext.evaluationPathRemoveLast();
//                    executionContext.evaluationPathAddLast("if");
//                }
            }
        }
    }

}
