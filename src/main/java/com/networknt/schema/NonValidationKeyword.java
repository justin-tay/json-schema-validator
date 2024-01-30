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

import java.util.Collections;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Used for Keywords that have no validation aspect, but are part of the metaschema.
 */
public class NonValidationKeyword extends AbstractKeyword {
    private final boolean collectAnnotations;

    private static final class Validator extends AbstractJsonValidator {
        private final boolean collectAnnotations;
        
        public Validator(SchemaLocation schemaLocation, JsonNodePath evaluationPath, JsonNode schemaNode,
                JsonSchema parentSchema, ValidationContext validationContext, Keyword keyword, boolean collectAnnotations) {
            super(schemaLocation, evaluationPath, keyword, schemaNode);
            this.collectAnnotations = collectAnnotations;
            String id = validationContext.resolveSchemaId(schemaNode);
            String anchor = validationContext.getMetaSchema().readAnchor(schemaNode);
            String dynamicAnchor = validationContext.getMetaSchema().readDynamicAnchor(schemaNode);
            if (id != null || anchor != null || dynamicAnchor != null) {
                // Used to register schema resources with $id
                validationContext.newSchema(schemaLocation, evaluationPath, schemaNode, parentSchema);
            }
            if ("$defs".equals(keyword.getValue()) || "definitions".equals(keyword.getValue())) {
                for (Iterator<Entry<String, JsonNode>> field = schemaNode.fields(); field.hasNext(); ) {
                    Entry<String, JsonNode> property = field.next();
                    validationContext.newSchema(schemaLocation.append(property.getKey()),
                            evaluationPath.append(property.getKey()), property.getValue(), parentSchema);
                }
            }
        }

        @Override
        public Set<ValidationMessage> validate(ExecutionContext executionContext, JsonNode node, JsonNode rootNode, JsonNodePath instanceLocation) {
            if (collectAnnotations && collectAnnotations(executionContext)) {
                Object value = getAnnotationValue(getSchemaNode());
                if (value != null) {
                    putAnnotation(executionContext,
                            annotation -> annotation.instanceLocation(instanceLocation).value(value));
                }
            }
            return Collections.emptySet();
        }
        
        protected Object getAnnotationValue(JsonNode schemaNode) {
            if (schemaNode.isTextual()) {
                return schemaNode.textValue(); 
            } else if (schemaNode.isNumber()) {
                return schemaNode.numberValue();
            } else if (schemaNode.isObject()) {
                return schemaNode;
            }
            return null;
        }
    }

    public NonValidationKeyword(String keyword) {
        this(keyword, true);
    }

    public NonValidationKeyword(String keyword, boolean collectAnnotations) {
        super(keyword);
        this.collectAnnotations = collectAnnotations;
    }

    @Override
    public JsonValidator newValidator(SchemaLocation schemaLocation, JsonNodePath evaluationPath, JsonNode schemaNode,
                                      JsonSchema parentSchema, ValidationContext validationContext) throws JsonSchemaException, Exception {
        return new Validator(schemaLocation, evaluationPath, schemaNode, parentSchema, validationContext, this, collectAnnotations);
    }
}
