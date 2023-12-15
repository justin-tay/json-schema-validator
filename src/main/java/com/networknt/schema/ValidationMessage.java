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

import com.networknt.schema.i18n.MessageFormatter;
import com.networknt.schema.utils.CachingSupplier;
import com.networknt.schema.utils.StringUtils;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Supplier;

public class ValidationMessage {
    private final String type;
    private final String code;
    private final JsonNodePath path;
    private final JsonNodePath schemaPath;
    private final Object[] arguments;
    private final Map<String, Object> details;
    private final String messageKey;
    private final Supplier<String> messageSupplier;

    ValidationMessage(String type, String code, JsonNodePath path, JsonNodePath schemaPath, Object[] arguments,
            Map<String, Object> details, String messageKey, Supplier<String> messageSupplier) {
        super();
        this.type = type;
        this.code = code;
        this.path = path;
        this.schemaPath = schemaPath;
        this.arguments = arguments;
        this.details = details;
        this.messageKey = messageKey;
        this.messageSupplier = messageSupplier;
    }

    public String getCode() {
        return code;
    }

    /**
     * @return The path to the input json
     */
    public JsonNodePath getPath() {
        return path;
    }

    /**
     * @return The path to the schema
     */
    public JsonNodePath getSchemaPath() {
        return schemaPath;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public String getMessage() {
        return messageSupplier.get();
    }

    public String getMessageKey() {
        return messageKey;
    }

    @Override
    public String toString() {
        return messageSupplier.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValidationMessage that = (ValidationMessage) o;

        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        if (schemaPath != null ? !schemaPath.equals(that.schemaPath) : that.schemaPath != null) return false;
        if (details != null ? !details.equals(that.details) : that.details != null) return false;
        if (messageKey != null ? !messageKey.equals(that.messageKey) : that.messageKey != null) return false;
        if (!Arrays.equals(arguments, that.arguments)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (schemaPath != null ? schemaPath.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        result = 31 * result + (arguments != null ? Arrays.hashCode(arguments) : 0);
        result = 31 * result + (messageKey != null ? messageKey.hashCode() : 0);
        return result;
    }

    public String getType() {
        return type;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String type;
        private String code;
        private JsonNodePath path;
        private JsonNodePath schemaPath;
        private Object[] arguments;
        private Map<String, Object> details;
        private MessageFormat format;
        private String message;
        private Supplier<String> messageSupplier;
        private MessageFormatter messageFormatter;
        private String messageKey;

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder path(JsonNodePath path) {
            this.path = path;
            return this;
        }

        public Builder schemaPath(JsonNodePath schemaPath) {
            this.schemaPath = schemaPath;
            return this;
        }

        public Builder arguments(Object... arguments) {
            this.arguments = arguments;
            return this;
        }

        public Builder details(Map<String, Object> details) {
            this.details = details;
            return this;
        }

        public Builder format(MessageFormat format) {
            this.format = format;
            return this;
        }

        @Deprecated
        public Builder customMessage(String message) {
            return message(message);
        }

        /**
         * Explicitly sets the message pattern to be used.
         * <p>
         * If set the message supplier and message formatter will be ignored.
         * 
         * @param message the message pattern
         * @return the builder
         */
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder messageSupplier(Supplier<String> messageSupplier) {
            this.messageSupplier = messageSupplier;
            return this;
        }

        public Builder messageFormatter(MessageFormatter messageFormatter) {
            this.messageFormatter = messageFormatter;
            return this;
        }

        public Builder messageKey(String messageKey) {
            this.messageKey = messageKey;
            return this;
        }

        public ValidationMessage build() {
            Supplier<String> messageSupplier = this.messageSupplier;
            String messageKey = this.messageKey;
            
            if (StringUtils.isNotBlank(this.message)) {
                messageKey = this.message;
                if (this.message.contains("{")) {
                    Object[] objs = getArguments();
                    MessageFormat format = new MessageFormat(this.message);
                    messageSupplier = new CachingSupplier<>(() -> format.format(objs));
                } else {
                    messageSupplier = message::toString;
                }
            } else if (messageSupplier == null) {
                Object[] objs = getArguments();
                MessageFormatter formatter = this.messageFormatter != null ? this.messageFormatter : format::format;
                messageSupplier = new CachingSupplier<>(() -> formatter.format(objs));
            }
            return new ValidationMessage(type, code, path, schemaPath, arguments, details, messageKey, messageSupplier);
        }
        
        private Object[] getArguments() {
            Object[] objs = new Object[(arguments == null ? 0 : arguments.length) + 1];
            objs[0] = path;
            if (arguments != null) {
                for (int i = 1; i < objs.length; i++) {
                    objs[i] = arguments[i - 1];
                }
            }
            return objs;
        }
    }
}
