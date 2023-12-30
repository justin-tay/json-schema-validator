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

package com.networknt.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.CollectorContext.Scope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class NotValidator extends BaseJsonValidator {
    private static final Logger logger = LoggerFactory.getLogger(NotValidator.class);

    private final JsonSchema schema;

    public NotValidator(JsonNodePath schemaLocation, JsonNode schemaNode, JsonSchema parentSchema, ValidationContext validationContext) {
        super(schemaLocation, schemaNode, parentSchema, ValidatorTypeCode.NOT, validationContext);
        this.schema = validationContext.newSchema(schemaLocation, schemaNode, parentSchema);
    }

    @Override
    public Set<ValidationMessage> validate(ExecutionContext executionContext, JsonNode node, JsonNode rootNode, JsonNodePath instanceLocation, JsonNodePath evaluationPath) {
        CollectorContext collectorContext = executionContext.getCollectorContext();
        Set<ValidationMessage> errors = new HashSet<>();

        Scope parentScope = collectorContext.enterDynamicScope();
        try {
            debug(logger, node, rootNode, instanceLocation);
            errors = this.schema.validate(executionContext, node, rootNode, instanceLocation, evaluationPath);
            if (errors.isEmpty()) {
                return Collections.singleton(message().instanceLocation(instanceLocation)
                        .locale(executionContext.getExecutionConfig().getLocale()).arguments(this.schema.toString())
                        .build());
            }
            return Collections.emptySet();
        } finally {
            Scope scope = collectorContext.exitDynamicScope();
            if (errors.isEmpty()) {
                parentScope.mergeWith(scope);
            }
        }
    }
    
    @Override
    public Set<ValidationMessage> walk(ExecutionContext executionContext, JsonNode node, JsonNode rootNode, JsonNodePath instanceLocation, JsonNodePath evaluationPath, boolean shouldValidateSchema) {
        if (shouldValidateSchema) {
            return validate(executionContext, node, rootNode, instanceLocation, evaluationPath);
        }

        Set<ValidationMessage> errors = this.schema.walk(executionContext, node, rootNode, instanceLocation, evaluationPath, shouldValidateSchema);
        if (errors.isEmpty()) {
            return Collections.singleton(message().instanceLocation(instanceLocation)
                    .locale(executionContext.getExecutionConfig().getLocale()).arguments(this.schema.toString())
                    .build());
        }
        return Collections.emptySet();
    }

    @Override
    public void preloadJsonSchema() {
        if (null != this.schema) {
            this.schema.initializeValidators();
        }
    }
}
