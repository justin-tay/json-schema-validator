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

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.walk.JsonSchemaWalker;

/**
 * Standard json validator interface, implemented by all validators and JsonSchema.
 */
public interface JsonSchemaValidator extends JsonSchemaWalker {
    /**
     * Validate the given JsonNode, the given node is the child node of the root node at given
     * data path.
     * @param executionContext  ExecutionContext
     * @param node     JsonNode
     * @param rootNode JsonNode
     * @param instanceLocation JsonNodePath
     *
     * @return A list of ValidationMessage if there is any validation error, or an empty
     * list if there is no error.
     */
    List<ValidationMessage> validate(ExecutionContext executionContext, JsonNode node, JsonNode rootNode,
            JsonNodePath instanceLocation);

    /**
     * This is default implementation of walk method. Its job is to call the
     * validate method if shouldValidateSchema is enabled.
     */
    @Override
    default List<ValidationMessage> walk(ExecutionContext executionContext, JsonNode node, JsonNode rootNode,
            JsonNodePath instanceLocation, boolean shouldValidateSchema) {
        if (node == null) {
            // Note that null is not the same as NullNode
            return Collections.emptyList();
        }
        return shouldValidateSchema ? validate(executionContext, node, rootNode, instanceLocation)
                : Collections.emptyList();
    }

    /**
     * The schema location is the canonical URI of the schema object plus a JSON
     * Pointer fragment indicating the subschema that produced a result. In contrast
     * with the evaluation path, the schema location MUST NOT include by-reference
     * applicators such as $ref or $dynamicRef.
     * 
     * @return the schema location
     */
    SchemaLocation getSchemaLocation();

    /**
     * The evaluation path is the set of keys, starting from the schema root,
     * through which evaluation passes to reach the schema object that produced a
     * specific result.
     * 
     * @return the evaluation path
     */
    JsonNodePath getEvaluationPath();
}
