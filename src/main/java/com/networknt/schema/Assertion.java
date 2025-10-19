/*
 * Copyright (c) 2025 the original author or authors.
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

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.ibm.icu.text.MessageFormat;
import com.networknt.schema.i18n.MessageSource;
import com.networknt.schema.keyword.BaseKeywordValidator;
import com.networknt.schema.path.NodePath;

/**
 * Assertion.
 */
public class Assertion implements Error {
    protected final BaseKeywordValidator keywordValidator;
    protected final JsonNode instanceNode;
    protected final NodePath instanceLocation;
    protected final NodePath evaluationPath;
    protected final String messageKey;
    protected final Object[] arguments;
    protected final String property;
    protected final Integer index;
    protected final Map<String, Object> details;
    protected final Locale locale;
    protected String message = null;

    public Assertion(BaseKeywordValidator keywordValidator, JsonNode instanceNode, NodePath instanceLocation,
            NodePath evaluationPath, Locale locale, Object[] arguments) {
        this.keywordValidator = keywordValidator;
        this.instanceNode = instanceNode;
        this.instanceLocation = instanceLocation;
        this.evaluationPath = evaluationPath;
        this.messageKey = keywordValidator.getKeyword();
        this.arguments = arguments;
        this.property = null;
        this.index = null;
        this.details = null;
        this.locale = locale;
    }

    public Assertion(BaseKeywordValidator keywordValidator, JsonNode instanceNode, NodePath instanceLocation,
            NodePath evaluationPath, Locale locale, String messageKey, Object[] arguments) {
        this.keywordValidator = keywordValidator;
        this.instanceNode = instanceNode;
        this.instanceLocation = instanceLocation;
        this.evaluationPath = evaluationPath;
        this.messageKey = messageKey;
        this.arguments = arguments;
        this.property = null;
        this.index = null;
        this.details = null;
        this.locale = locale;
    }

    public Assertion(BaseKeywordValidator keywordValidator, JsonNode instanceNode, NodePath instanceLocation,
            NodePath evaluationPath, Locale locale, String messageKey, Object[] arguments, String property) {
        this.keywordValidator = keywordValidator;
        this.instanceNode = instanceNode;
        this.instanceLocation = instanceLocation;
        this.evaluationPath = evaluationPath;
        this.messageKey = messageKey;
        this.arguments = arguments;
        this.property = property;
        this.index = null;
        this.details = null;
        this.locale = locale;
    }
    
    public Assertion(BaseKeywordValidator keywordValidator, JsonNode instanceNode, NodePath instanceLocation,
            NodePath evaluationPath, Locale locale, String messageKey, Object[] arguments, Integer index) {
        this.keywordValidator = keywordValidator;
        this.instanceNode = instanceNode;
        this.instanceLocation = instanceLocation;
        this.evaluationPath = evaluationPath;
        this.messageKey = messageKey;
        this.arguments = arguments;
        this.property = null;
        this.index = index;
        this.details = null;
        this.locale = locale;
    }

    public Assertion(BaseKeywordValidator keywordValidator, JsonNode instanceNode, NodePath instanceLocation,
            NodePath evaluationPath, Locale locale, String messageKey, Object[] arguments, String property,
            Integer index, Map<String, Object> details) {
        this.keywordValidator = keywordValidator;
        this.instanceNode = instanceNode;
        this.instanceLocation = instanceLocation;
        this.evaluationPath = evaluationPath;
        this.messageKey = messageKey;
        this.arguments = arguments;
        this.property = property;
        this.index = index;
        this.details = details;
        this.locale = locale;
    }

    @Override
    public NodePath getInstanceLocation() {
        return this.instanceLocation;
    }

    @Override
    public NodePath getEvaluationPath() {
        return this.evaluationPath;
    }

    @Override
    public SchemaLocation getSchemaLocation() {
        return this.keywordValidator.getSchemaLocation();
    }

    @Override
    public JsonNode getInstanceNode() {
        return this.instanceNode;
    }

    @Override
    public JsonNode getSchemaNode() {
        return this.keywordValidator.getSchemaNode();
    }

    @Override
    public String getProperty() {
        return this.property;
    }

    @Override
    public Integer getIndex() {
        return this.index;
    }

    @Override
    public Object[] getArguments() {
        return this.arguments;
    }

    @Override
    public Map<String, Object> getDetails() {
        return this.details;
    }

    @Override
    public String getMessage() {
        if (this.message == null) {
            String messagePattern = null;
            Map<String, String> errorMessage = keywordValidator.getErrorMessage();
            if (errorMessage != null) {
                messagePattern = errorMessage.get("");
                if (this.property != null) {
                    String specificMessagePattern = errorMessage.get(this.property);
                    if (specificMessagePattern != null) {
                        messagePattern = specificMessagePattern;
                    }
                }
                if (messagePattern != null && !"".equals(messagePattern)) {
                    if (messagePattern.contains("{")) {
                        this.message = MessageFormat.format(messagePattern, arguments);
                    } else {
                        this.message = messagePattern;
                    }
                }
            }

            // Default to message source formatter
            if (this.message == null) {
                MessageSource messageSource = keywordValidator.getParentSchema().getSchemaContext()
                        .getSchemaRegistryConfig().getMessageSource();
                this.message = messageSource.getMessage(this.messageKey, locale, arguments);
            }
        }
        return this.message;
    }

    @Override
    public String getMessageKey() {
        return this.messageKey;
    }

    @Override
    public String getKeyword() {
        return this.keywordValidator.getKeyword();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (instanceLocation != null) {
            // Validation Error
            builder.append(instanceLocation.toString());
        } else if (keywordValidator.getSchemaLocation() != null) {
            // Parse Error
            builder.append(keywordValidator.getSchemaLocation().toString());
        }
        builder.append(": ");
        builder.append(getMessage());
        return builder.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Assertion that = (Assertion) o;

        if (getKeyword() != null ? !getKeyword().equals(that.getKeyword()) : that.getKeyword() != null) return false;
        if (instanceLocation != null ? !instanceLocation.equals(that.instanceLocation) : that.instanceLocation != null) return false;
        if (evaluationPath != null ? !evaluationPath.equals(that.evaluationPath) : that.evaluationPath != null) return false;
        if (details != null ? !details.equals(that.details) : that.details != null) return false;
        if (messageKey != null ? !messageKey.equals(that.messageKey) : that.messageKey != null) return false;
        if (property != null ? !property.equals(that.property) : that.property != null) return false;
        if (index != null ? !index.equals(that.index) : that.index != null) return false;
        return Arrays.equals(arguments, that.arguments);
    }

    @Override
    public int hashCode() {
        int result = getKeyword() != null ? getKeyword().hashCode() : 0;
        result = 31 * result + (instanceLocation != null ? instanceLocation.hashCode() : 0);
        result = 31 * result + (evaluationPath != null ? evaluationPath.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (arguments != null ? Arrays.hashCode(arguments) : 0);
        result = 31 * result + (messageKey != null ? messageKey.hashCode() : 0);
        result = 31 * result + (property != null ? property.hashCode() : 0);
        result = 31 * result + (index != null ? index.hashCode() : 0);
        return result;
    }    
}
