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

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.path.NodePath;

/**
 * Represents an error which could be when parsing a schema or when validating
 * an instance.
 * 
 * @see <a href=
 *      "https://github.com/json-schema-org/json-schema-spec/blob/main/specs/output/jsonschema-validation-output-machines.md">JSON
 *      Schema</a>
 */
@JsonIgnoreProperties({ "messageSupplier", "schemaNode", "instanceNode", "valid" })
@JsonPropertyOrder({ "keyword", "instanceLocation", "message", "evaluationPath", "schemaLocation",
        "messageKey", "arguments", "details", "property", "index" })
@JsonInclude(Include.NON_NULL)
public interface Error {
    /**
     * The instance location is the location of the JSON value within the root
     * instance being validated.
     * 
     * @return The path to the input json
     */
    NodePath getInstanceLocation();

    /**
     * The evaluation path is the set of keys, starting from the schema root,
     * through which evaluation passes to reach the schema object that produced a
     * specific result.
     * 
     * @return the evaluation path
     */
    NodePath getEvaluationPath();
    
    /**
     * The schema location is the canonical IRI of the schema object plus a JSON
     * Pointer fragment indicating the subschema that produced a result. In contrast
     * with the evaluation path, the schema location MUST NOT include by-reference
     * applicators such as $ref or $dynamicRef.
     * 
     * @return the schema location
     */
    SchemaLocation getSchemaLocation();
    
    /**
     * Returns the instance node which was evaluated.
     * <p>
     * This corresponds with the instance location.
     * 
     * @return the instance node
     */
    JsonNode getInstanceNode();
    
    /**
     * Returns the schema node which was evaluated.
     * <p>
     * This corresponds with the schema location.
     * 
     * @return the schema node
     */
    JsonNode getSchemaNode();
    
    /**
     * Returns the property with the error.
     * <p>
     * For instance, for the required validator the instance location does not
     * contain the missing property name as the instance must refer to the input
     * data.
     * 
     * @return the property name
     */
    String getProperty();

    public Integer getIndex();

    public Object[] getArguments();

    public Map<String, Object> getDetails();

    /**
     * Gets the formatted error message.
     * 
     * @return the error message
     */
    public String getMessage();

    public String getMessageKey();
    
    public String getKeyword();

    public static BasicError.Builder builder() {
        return BasicError.builder();
    }
}
