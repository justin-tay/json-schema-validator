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

/**
 * KeywordValidator interface implemented by all keyword validators.
 */
public interface JsonValidator extends JsonSchemaValidator {
    /**
     * In case the {@link com.networknt.schema.JsonValidator} has a related {@link com.networknt.schema.JsonSchema} or several
     * ones, calling preloadJsonSchema will actually load the schema document(s) eagerly.
     *
     * @throws JsonSchemaException (a {@link java.lang.RuntimeException}) in case the {@link com.networknt.schema.JsonSchema} or nested schemas
     * are invalid (like <code>$ref</code> not resolving)
     * @since 1.0.54
     */
    default void preloadJsonSchema() throws JsonSchemaException {
        // do nothing by default - to be overridden in subclasses
    }

    /**
     * The keyword of the validator.
     * 
     * @return the keyword
     */
    String getKeyword();
}
