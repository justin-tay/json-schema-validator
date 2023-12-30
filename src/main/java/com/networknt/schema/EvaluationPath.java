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

/**
 * The evaluation path is the set of keys, starting from the schema root,
 * through which evaluation passes to reach the schema object that produced a
 * specific result.
 */
public class EvaluationPath {
    /**
     * Determines the evaluation path to the schema location.
     * 
     * @param evaluationPath the preceding evaluation path
     * @param schema         the current schema
     * @return the evaluation path
     */
    public static JsonNodePath of(JsonNodePath evaluationPath, JsonValidator schema) {
        return of(evaluationPath, schema.getSchemaLocation());
    }

    /**
     * Determines the evaluation path to the schema location.
     * 
     * @param evaluationPath the preceding evaluation path
     * @param schemaLocation the current schema location
     * @return the evaluation path
     */
    public static JsonNodePath of(JsonNodePath evaluationPath, JsonNodePath schemaLocation) {
        Object element = schemaLocation.getElement(-1);
        if (element instanceof Integer) {
            return evaluationPath.resolve((Integer) element);
        } else {
            return evaluationPath.resolve(element.toString());
        }
    }
}
