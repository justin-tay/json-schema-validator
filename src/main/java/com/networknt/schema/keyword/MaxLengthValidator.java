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

package com.networknt.schema.keyword;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.ExecutionContext;
import com.networknt.schema.JsonNodePath;
import com.networknt.schema.Schema;
import com.networknt.schema.JsonType;
import com.networknt.schema.SchemaLocation;
import com.networknt.schema.TypeFactory;
import com.networknt.schema.ValidationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link KeywordValidator} for maxLength.
 */
public class MaxLengthValidator extends BaseKeywordValidator implements KeywordValidator {
    private static final Logger logger = LoggerFactory.getLogger(MaxLengthValidator.class);

    private final int maxLength;

    public MaxLengthValidator(SchemaLocation schemaLocation, JsonNodePath evaluationPath, JsonNode schemaNode, Schema parentSchema, ValidationContext validationContext) {
        super(ValidatorTypeCode.MAX_LENGTH, schemaNode, schemaLocation, parentSchema, validationContext, evaluationPath);
        if (schemaNode != null && schemaNode.canConvertToExactIntegral()) {
            this.maxLength = schemaNode.intValue();
        } else {
            this.maxLength = Integer.MAX_VALUE;
        }
    }

    public void validate(ExecutionContext executionContext, JsonNode node, JsonNode rootNode, JsonNodePath instanceLocation) {
        debug(logger, executionContext, node, rootNode, instanceLocation);

        JsonType nodeType = TypeFactory.getValueNodeType(node, this.validationContext.getSchemaRegistryConfig());
        if (nodeType != JsonType.STRING) {
            // ignore no-string typs
            return;
        }
        if (node.textValue().codePointCount(0, node.textValue().length()) > this.maxLength) {
            executionContext.addError(error().instanceNode(node).instanceLocation(instanceLocation)
                    .locale(executionContext.getExecutionConfig().getLocale())
                    .arguments(this.maxLength).build());
        }
    }

}
