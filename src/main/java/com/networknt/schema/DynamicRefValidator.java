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

import java.net.URI;
import java.util.Collections;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Validator for $dynamicRef.
 */
public class DynamicRefValidator extends BaseJsonValidator {
    private final String dynamicRefValue;

    public DynamicRefValidator(JsonNodePath schemaLocation, JsonNodePath evaluationPath, JsonNode schemaNode,
            JsonSchema parentSchema, ValidationContext validationContext) {
        super(schemaLocation, evaluationPath, schemaNode, parentSchema, ValidatorTypeCode.DYNAMIC_REF,
                validationContext);
        this.dynamicRefValue = schemaNode.asText();
        if (!this.dynamicRefValue.startsWith("#")) {
            ValidationMessage validationMessage = ValidationMessage.builder()
                    .type(ValidatorTypeCode.DYNAMIC_REF.getValue()).code("internal.invalidDynamicRef")
                    .message("{0}: The value of a $dynamicRef must be start with '#' but is '{1}'")
                    .instanceLocation(schemaLocation).evaluationPath(schemaLocation).arguments(dynamicRefValue).build();
            throw new JsonSchemaException(validationMessage);
        }
    }

    @Override
    public Set<ValidationMessage> validate(ExecutionContext executionContext, JsonNode node, JsonNode rootNode,
            JsonNodePath instanceLocation) {
        JsonSchema refSchema = getRefSchema();
        if (refSchema != null) {
            return refSchema.validate(executionContext, node, rootNode, instanceLocation);
        }
        return Collections.emptySet();
    }

    @Override
    public Set<ValidationMessage> walk(ExecutionContext executionContext, JsonNode node, JsonNode rootNode,
            JsonNodePath instanceLocation, boolean shouldValidateSchema) {
        JsonSchema refSchema = getRefSchema();
        if (refSchema != null) {
            return refSchema.walk(executionContext, node, rootNode, instanceLocation, shouldValidateSchema);
        }
        return Collections.emptySet();
    }

    protected JsonSchema getRefSchema() {
        JsonSchema current = parentSchema.findLexicalRoot();
        JsonSchema scope = current;
        String ref = UriReference.resolve(scope.getCurrentUri(), dynamicRefValue);
        JsonSchema refSchema = this.validationContext.getDynamicAnchors().get(ref);
        if (refSchema != null) { // This is a $dynamicRef without a matching $dynamicAnchor
            // A $dynamicRef without a matching $dynamicAnchor in the same schema resource
            // behaves like a normal $ref to $anchor
            while (scope.parentSchema != null) {
                scope = scope.parentSchema.findLexicalRoot();
                ref = UriReference.resolve(scope.getCurrentUri(), dynamicRefValue);
                JsonSchema scopeRefSchema = this.validationContext.getDynamicAnchors().get(ref);
                if (scopeRefSchema != null) {
                    refSchema = scopeRefSchema;
                }
            }
        }
        if (refSchema == null) {
            // A $dynamicRef without anchor in fragment behaves identical to $ref
            JsonSchemaRef r = RefValidator.getRefSchema(current, validationContext, dynamicRefValue, evaluationPath);
            if (r != null) {
                refSchema = r.getSchema();
            }
        } else {
            refSchema = refSchema.fromRef(this.parentSchema, this.evaluationPath);
        }
        return refSchema;
    }
}
