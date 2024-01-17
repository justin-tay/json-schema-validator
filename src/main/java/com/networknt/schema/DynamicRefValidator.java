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

public class DynamicRefValidator extends BaseJsonValidator {
    private static final Logger logger = LoggerFactory.getLogger(DynamicRefValidator.class);

    private Map<SchemaLocation, JsonSchema> schemas = new HashMap<>();
    
    private String refValue;

    public DynamicRefValidator(SchemaLocation schemaLocation, JsonNodePath evaluationPath, JsonNode schemaNode, JsonSchema parentSchema, ValidationContext validationContext) {
        super(schemaLocation, evaluationPath, schemaNode, parentSchema, ValidatorTypeCode.DYNAMIC_REF, validationContext);

        String refValue = schemaNode.asText();
        if (!refValue.startsWith("#")) {
            ValidationMessage validationMessage = ValidationMessage.builder()
                    .type(ValidatorTypeCode.RECURSIVE_REF.getValue()).code("internal.invalidRecursiveRef")
                    .message("{0}: The value of a $dynamicRef must start with '#' but is '{1}'").instanceLocation(schemaLocation.getFragment())
                    .evaluationPath(schemaLocation.getFragment()).arguments(refValue).build();
            throw new JsonSchemaException(validationMessage);
        }
        this.refValue = schemaLocation.resolve(refValue).toString();
    }
    
    private JsonSchema getSchema(CollectorContext collectorContext) {
        JsonSchema schema = null; 
        for(Iterator<Scope> iter = collectorContext.getDynamicScopes().descendingIterator(); iter.hasNext(); ) {
            Scope scope = iter.next();
            schema = scope.getDynamicAnchors().get(this.refValue);
            if (schema != null) {
                break;
            }
        }
        return schema;
    }

    @Override
    public Set<ValidationMessage> validate(ExecutionContext executionContext, JsonNode node, JsonNode rootNode, JsonNodePath instanceLocation) {
        CollectorContext collectorContext = executionContext.getCollectorContext();

        Set<ValidationMessage> errors = new HashSet<>();

        Scope parentScope = collectorContext.enterDynamicScope();
        try {
            debug(logger, node, rootNode, instanceLocation);
            JsonSchema schema = getSchema(collectorContext); 
            if (null != schema) {
                JsonSchema refSchema = schemas.computeIfAbsent(schema.getSchemaLocation(), key -> {
                   return schema.fromRef(getParentSchema(), getEvaluationPath());
                });
                errors = refSchema.validate(executionContext, node, rootNode, instanceLocation);
            }
        } finally {
            Scope scope = collectorContext.exitDynamicScope();
            if (errors.isEmpty()) {
                parentScope.mergeWith(scope);
            }
        }

        return errors;
    }

    @Override
    public Set<ValidationMessage> walk(ExecutionContext executionContext, JsonNode node, JsonNode rootNode, JsonNodePath instanceLocation, boolean shouldValidateSchema) {
        CollectorContext collectorContext = executionContext.getCollectorContext();

        Set<ValidationMessage> errors = new HashSet<>();

        Scope parentScope = collectorContext.enterDynamicScope();
        try {
            debug(logger, node, rootNode, instanceLocation);

            JsonSchema schema = collectorContext.getOutermostSchema();
            if (null != schema) {
                JsonSchema refSchema = schemas.computeIfAbsent(schema.getSchemaLocation(), key -> {
                    return schema.fromRef(getParentSchema(), getEvaluationPath());
                 });
                errors = refSchema.walk(executionContext, node, rootNode, instanceLocation, shouldValidateSchema);
            }
        } finally {
            Scope scope = collectorContext.exitDynamicScope();
            if (shouldValidateSchema) {
                if (errors.isEmpty()) {
                    parentScope.mergeWith(scope);
                }
            }
        }

        return errors;
    }

}
