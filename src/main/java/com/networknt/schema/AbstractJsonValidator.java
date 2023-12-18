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
import java.util.Set;

public abstract class AbstractJsonValidator implements JsonValidator {
    private final JsonNodePath schemaPath;
    private final JsonNodePath evaluationPath;

    public AbstractJsonValidator(JsonNodePath schemaPath, JsonNodePath evaluationPath) {
        this.schemaPath = schemaPath;
        this.evaluationPath = evaluationPath;
    }

    @Override
    public Set<ValidationMessage> walk(ExecutionContext executionContext, JsonNode node, JsonNode rootNode,
            JsonNodePath at, boolean shouldValidateSchema) {
        Set<ValidationMessage> validationMessages = Collections.emptySet();
        if (shouldValidateSchema) {
            validationMessages = validate(executionContext, node, rootNode, at);
        }
        return validationMessages;
    }

    @Override
    public JsonNodePath getSchemaPath() {
        return schemaPath;
    }

    @Override
    public JsonNodePath getEvaluationPath() {
        return evaluationPath;
    }
}
