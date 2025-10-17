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

import tools.jackson.databind.JsonNode;
import com.networknt.schema.ExecutionContext;
import com.networknt.schema.Schema;
import com.networknt.schema.SchemaException;
import com.networknt.schema.SchemaLocation;
import com.networknt.schema.path.NodePath;
import com.networknt.schema.SchemaContext;
import com.networknt.schema.utils.JsonNodeTypes;
import com.networknt.schema.utils.JsonType;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * {@link KeywordValidator} for maxmimum.
 */
public class MaximumValidator extends BaseKeywordValidator {
    private static final String PROPERTY_EXCLUSIVE_MAXIMUM = "exclusiveMaximum";

    private final boolean excludeEqual;

    private final ThresholdMixin typedMaximum;


    public MaximumValidator(SchemaLocation schemaLocation, final JsonNode schemaNode, Schema parentSchema, SchemaContext schemaContext) {
        super(KeywordType.MAXIMUM, schemaNode, schemaLocation, parentSchema, schemaContext);
        if (!schemaNode.isNumber()) {
            throw new SchemaException("maximum value is not a number");
        }

        JsonNode exclusiveMaximumNode = getParentSchema().getSchemaNode().get(PROPERTY_EXCLUSIVE_MAXIMUM);
        if (exclusiveMaximumNode != null && exclusiveMaximumNode.isBoolean()) {
            this.excludeEqual = exclusiveMaximumNode.booleanValue();
        } else {
            this.excludeEqual = false;
        }

        final String maximumText = schemaNode.asString();
        if ((schemaNode.isLong() || schemaNode.isInt()) && (JsonType.INTEGER.toString().equals(getNodeFieldType()))) {
            // "integer", and within long range
            final long lm = schemaNode.asLong();
            this.typedMaximum = new ThresholdMixin() {
                @Override
                public boolean crossesThreshold(JsonNode node) {
                    if (node.isBigInteger()) {
                        //node.isBigInteger is not trustable, the type BigInteger doesn't mean it is a big number.
                        int compare = node.bigIntegerValue().compareTo(new BigInteger(schemaNode.asString()));
                        return compare > 0 || (excludeEqual && compare == 0);

                    } else if (node.isString()) {
                        BigDecimal max = new BigDecimal(maximumText);
                        BigDecimal value = new BigDecimal(node.asString());
                        int compare = value.compareTo(max);
                        return compare > 0 || (excludeEqual && compare == 0);
                    }
                    long val = node.asLong();
                    return lm < val || (excludeEqual && lm == val);
                }

                @Override
                public String thresholdValue() {
                    return String.valueOf(lm);
                }
            };
        } else {
            this.typedMaximum = new ThresholdMixin() {
                @Override
                public boolean crossesThreshold(JsonNode node) {
                    if (schemaNode.isDouble() && schemaNode.doubleValue() == Double.POSITIVE_INFINITY) {
                        return false;
                    }
                    if (schemaNode.isDouble() && schemaNode.doubleValue() == Double.NEGATIVE_INFINITY) {
                        return true;
                    }
                    if (node.isDouble() && node.doubleValue() == Double.NEGATIVE_INFINITY) {
                        return false;
                    }
                    if (node.isDouble() && node.doubleValue() == Double.POSITIVE_INFINITY) {
                        return true;
                    }
                    final BigDecimal max = new BigDecimal(maximumText);
                    BigDecimal value = new BigDecimal(node.asString());
                    int compare = value.compareTo(max);
                    return compare > 0 || (excludeEqual && compare == 0);
                }

                @Override
                public String thresholdValue() {
                    return maximumText;
                }
            };
        }
    }

    public void validate(ExecutionContext executionContext, JsonNode node, JsonNode rootNode, NodePath instanceLocation) {
        

        if (!JsonNodeTypes.isNumber(node, this.schemaContext.getSchemaRegistryConfig())) {
            // maximum only applies to numbers
            return;
        }

        if (this.typedMaximum.crossesThreshold(node)) {
            executionContext.addError(error().instanceNode(node).instanceLocation(instanceLocation)
                    .evaluationPath(executionContext.getEvaluationPath()).locale(executionContext.getExecutionConfig().getLocale())
                    .arguments(this.typedMaximum.thresholdValue()).build());
        }
    }
}