/*
 * Copyright (c) 2023 Network New Technologies Inc.
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class UnevaluatedItemsValidator extends BaseJsonValidator {
    private static final Logger logger = LoggerFactory.getLogger(UnevaluatedItemsValidator.class);

    private final JsonSchema schema;
    private final boolean disabled;

    public UnevaluatedItemsValidator(JsonNodePath schemaLocation, JsonNodePath evaluationPath, JsonNode schemaNode,
            JsonSchema parentSchema, ValidationContext validationContext) {
        super(schemaLocation, evaluationPath, schemaNode, parentSchema, ValidatorTypeCode.UNEVALUATED_ITEMS,
                validationContext);

        this.disabled = validationContext.getConfig().isUnevaluatedItemsAnalysisDisabled();
        if (schemaNode.isObject() || schemaNode.isBoolean()) {
            this.schema = validationContext.newSchema(schemaLocation, evaluationPath, schemaNode, parentSchema);
        } else {
            throw new IllegalArgumentException("The value of 'unevaluatedItems' MUST be a valid JSON Schema.");
        }
    }

    @Override
    public Set<ValidationMessage> validate(ExecutionContext executionContext, JsonNode node, JsonNode rootNode,
            JsonNodePath instanceLocation) {
        if (this.disabled || !node.isArray())
            return Collections.emptySet();

        debug(logger, node, rootNode, instanceLocation);
        CollectorContext collectorContext = executionContext.getCollectorContext();

        collectorContext.exitDynamicScope();
        try {

            boolean valid = false;
            int validCount = 0;

            // This indicates whether the "unevaluatedItems" subschema was used for
            // evaluated for setting the annotation
            boolean evaluated = false;

            // If schema is "unevaluatedItems: true" this is valid
            if (getSchemaNode().isBoolean() && getSchemaNode().booleanValue()) {
                valid = true;
                // No need to actually evaluate since the schema is true but if there are any
                // items the annotation needs to be set
                if (node.size() > 0) {
                    evaluated = true;
                }
            } else {
                // Get all the "items" for the instanceLocation

                Map<String, Map<JsonNodePath, JsonNodeAnnotation>> instanceLocationAnnotations = executionContext
                        .getAnnotations().asMap().getOrDefault(instanceLocation, Collections.emptyMap());
                
                List<JsonNodeAnnotation> items = instanceLocationAnnotations
                        .getOrDefault("items", Collections.emptyMap()).values().stream()
                        .filter(a -> a.getEvaluationPath().startsWith(this.evaluationPath.getParent()))
                        .collect(Collectors.toList());
                if (items.isEmpty()) {
                    // The "items" wasn't applied meaning it is unevaluated if there is content
                    valid = false;
                } else {
                    // Annotation results for "items" keywords from multiple schemas applied to the
                    // same instance location are combined by setting the combined result to true if
                    // any of the values are true, and otherwise retaining the largest numerical
                    // value.
                    for (JsonNodeAnnotation annotation : items) {
                        if (annotation.getValue() instanceof Number) {
                            Number value = annotation.getValue();
                            int existing = value.intValue();
                            if (existing > validCount) {
                                validCount = existing;
                            }
                        } else if (annotation.getValue() instanceof Boolean) {
                            // The annotation "items: true"
                            valid = true;
                        }
                    }
                    if (!valid) {
                        // Check the additionalItems annotation
                        // If the "additionalItems" subschema is applied to any positions within the
                        // instance array, it produces an annotation result of boolean true, analogous
                        // to the single schema behavior of "items". If any "additionalItems" keyword
                        // from any subschema applied to the same instance location produces an
                        // annotation value of true, then the combined result from these keywords is
                        // also true.
                        List<JsonNodeAnnotation> additionalItems = instanceLocationAnnotations
                                .getOrDefault("additionalItems", Collections.emptyMap()).values().stream()
                                .filter(a -> a.getEvaluationPath().startsWith(this.evaluationPath.getParent()))
                                .collect(Collectors.toList());
                        for (JsonNodeAnnotation annotation : additionalItems) {
                            if (annotation.getValue() instanceof Boolean
                                    && Boolean.TRUE.equals(annotation.getValue())) {
                                // The annotation "additionalItems: true"
                                valid = true;
                            }
                        }
                    }
                }
                if (!valid) {
                    // Unevaluated
                    // Check if there are any "unevaluatedItems" annotations
                    List<JsonNodeAnnotation> unevaluatedItems = instanceLocationAnnotations
                            .getOrDefault("unevaluatedItems", Collections.emptyMap()).values().stream()
                            .filter(a -> a.getEvaluationPath().startsWith(this.evaluationPath.getParent()))
                            .collect(Collectors.toList());
                    for (JsonNodeAnnotation annotation : unevaluatedItems) {
                        if (annotation.getValue() instanceof Boolean && Boolean.TRUE.equals(annotation.getValue())) {
                            // The annotation "unevaluatedItems: true"
                            valid = true;
                        }
                    }
                }
            }
            Set<ValidationMessage> messages = null;
            if (!valid) {
                if (node.size() > 0) {
                    evaluated = true;
                }
                messages = new LinkedHashSet<>();
                // Start evaluating from the valid count
                for (int x = validCount; x < node.size(); x++) {
                    // The schema is either "false" or an object schema
                    messages.addAll(
                            this.schema.validate(executionContext, node.get(x), node, instanceLocation.resolve(x)));
                }
                if (messages.isEmpty()) {
                    valid = true;
                } else {
                    // Report these as unevaluated paths or not matching the unevalutedItems schema
                    messages = messages.stream()
                            .map(m -> message().instanceLocation(m.getInstanceLocation())
                                    .locale(executionContext.getExecutionConfig().getLocale()).build())
                            .collect(Collectors.toCollection(LinkedHashSet::new));
                }
            }
            // If the "unevaluatedItems" subschema is applied to any positions within the
            // instance array, it produces an annotation result of boolean true, analogous
            // to the single schema behavior of "items". If any "unevaluatedItems" keyword
            // from any subschema applied to the same instance location produces an
            // annotation value of true, then the combined result from these keywords is
            // also true.
            if (evaluated) {
                executionContext.getAnnotations()
                        .put(JsonNodeAnnotation.builder().instanceLocation(instanceLocation)
                                .evaluationPath(this.evaluationPath).schemaLocation(this.schemaLocation)
                                .keyword("unevaluatedItems").value(true).build());
            }
            return messages == null || messages.isEmpty() ? Collections.emptySet() : messages;
            /*
            if (!valid) {
                System.out.println(instanceLocation + " " + node);
                throw new IllegalArgumentException(instanceLocation + " " + node);
            }*/

/*
            Set<JsonNodePath> allPaths = allPaths(node, instanceLocation);

            // Short-circuit since schema is 'true'
            if (super.schemaNode.isBoolean() && super.schemaNode.asBoolean()) {
                collectorContext.getEvaluatedItems().addAll(allPaths);
                return Collections.emptySet();
            }

            Set<JsonNodePath> unevaluatedPaths = unevaluatedPaths(collectorContext, allPaths);

            // Short-circuit since schema is 'false'
            if (super.schemaNode.isBoolean() && !super.schemaNode.asBoolean() && !unevaluatedPaths.isEmpty()) {
                return reportUnevaluatedPaths(unevaluatedPaths, executionContext);
            }

            Set<JsonNodePath> failingPaths = new LinkedHashSet<>();
            unevaluatedPaths.forEach(path -> {
                String pointer = path.getPathType().convertToJsonPointer(path.toString());
                JsonNode property = rootNode.at(pointer);
                if (!this.schema.validate(executionContext, property, rootNode, path).isEmpty()) {
                    failingPaths.add(path);
                }
            });

            if (failingPaths.isEmpty()) {
                collectorContext.getEvaluatedItems().addAll(allPaths);
            } else {
                return reportUnevaluatedPaths(failingPaths, executionContext);
            }

            return Collections.emptySet();
            */
        } finally {
            collectorContext.enterDynamicScope();
        }
    }

    private Set<JsonNodePath> allPaths(JsonNode node, JsonNodePath instanceLocation) {
        Set<JsonNodePath> collector = new LinkedHashSet<>();
        int size = node.size();
        for (int i = 0; i < size; ++i) {
            JsonNodePath path = instanceLocation.resolve(i);
            collector.add(path);
        }
        return collector;
    }

    private Set<ValidationMessage> reportUnevaluatedPaths(Set<JsonNodePath> unevaluatedPaths,
            ExecutionContext executionContext) {
        return unevaluatedPaths
                .stream().map(path -> message().instanceLocation(path)
                        .locale(executionContext.getExecutionConfig().getLocale()).build())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static Set<JsonNodePath> unevaluatedPaths(CollectorContext collectorContext, Set<JsonNodePath> allPaths) {
        Set<JsonNodePath> unevaluatedProperties = new HashSet<>(allPaths);
        unevaluatedProperties.removeAll(collectorContext.getEvaluatedItems());
        return unevaluatedProperties;
    }

}
