package com.networknt.schema;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Validation Entry.
 * 
 * @see <a href=
 *      "https://github.com/json-schema-org/json-schema-spec/blob/main/jsonschema-validation-output-machines.md">A
 *      Specification for Machine-Readable Output for JSON Schema Validation and
 *      Annotation</a>
 */
public class ValidationEntry {
    private final JsonNodePath evaluationPath;
    private final JsonNodePath schemaLocation;
    private final JsonNodePath instanceLocation;
    private final boolean valid;
    private final Map<String, String> errors;
    private final List<ValidationEntry> details;

    public ValidationEntry(JsonNodePath evaluationPath, JsonNodePath schemaLocation, JsonNodePath instanceLocation,
            boolean valid, Map<String, String> errors, List<ValidationEntry> details) {
        super();
        this.evaluationPath = evaluationPath;
        this.schemaLocation = schemaLocation;
        this.instanceLocation = instanceLocation;
        this.valid = valid;
        this.errors = errors != null ? errors : new LinkedHashMap<>();
        this.details = details != null ? details : new ArrayList<>();
    }

    public JsonNodePath getEvaluationPath() {
        return evaluationPath;
    }

    public JsonNodePath getSchemaLocation() {
        return schemaLocation;
    }

    public JsonNodePath getInstanceLocation() {
        return instanceLocation;
    }

    public boolean isValid() {
        return valid;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public List<ValidationEntry> getDetails() {
        return details;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private JsonNodePath evaluationPath = null;
        private JsonNodePath schemaLocation = null;
        private JsonNodePath instanceLocation = null;
        private boolean valid = false;
        private Map<String, String> errors = null;
        private List<ValidationEntry> details = null;

        public Builder evaluationPath(JsonNodePath evaluationPath) {
            this.evaluationPath = evaluationPath;
            return this;
        }

        public Builder schemaLocation(JsonNodePath schemaLocation) {
            this.schemaLocation = schemaLocation;
            return this;
        }

        public Builder instanceLocation(JsonNodePath instanceLocation) {
            this.instanceLocation = instanceLocation;
            return this;
        }

        public Builder valid(boolean valid) {
            this.valid = valid;
            return this;
        }

        public Builder errors(Map<String, String> errors) {
            this.errors = errors;
            return this;
        }

        public Builder details(List<ValidationEntry> details) {
            this.details = details;
            return this;
        }

        public ValidationEntry build() {
            return new ValidationEntry(evaluationPath, schemaLocation, instanceLocation, valid, errors, details);
        }
    }

    /**
     * Formats the validation messages.
     * <p>
     * This does not support the flag output method as a short-circuiting operation
     * should be used.
     * 
     * @param validationMessages the validation messages
     * @return the formatter
     */
    public static Formatter format(List<ValidationMessage> validationMessages) {
        return new Formatter(validationMessages);
    }

    public static class Formatter {
        private final List<ValidationMessage> validationMessages;

        public Formatter(List<ValidationMessage> validationMessages) {
            this.validationMessages = validationMessages;
        }

        /**
         * Formats as list structure.
         * 
         * @return as list structure
         */
        public ValidationEntry asList() {
            if (validationMessages != null && !validationMessages.isEmpty()) {
                ValidationEntry root = new ValidationEntry(null, null, null, false, null, null);
                Map<JsonNodePath, ValidationEntry> entries = new LinkedHashMap<>();
                for (ValidationMessage validationMessage : validationMessages) {
                    JsonNodePath evaluationPath = validationMessage.getEvaluationPath();
                    ValidationEntry existing = entries.get(evaluationPath);
                    if (existing == null) {
                        existing = builder().evaluationPath(validationMessage.getEvaluationPath())
                                .schemaLocation(validationMessage.getSchemaLocation())
                                .instanceLocation(validationMessage.getInstanceLocation()).valid(false).build();
                        entries.put(existing.getEvaluationPath(), existing);
                        root.getDetails().add(existing);
                    }
                    existing.getErrors().put(validationMessage.getCode(), validationMessage.getMessage());
                }
                return root;
            } else {
                // Valid
                return builder().valid(true).build();
            }
        }

        /**
         * Formats as hierarchical structure.
         * 
         * @return as hierarchical structure
         */
        public ValidationEntry asHierarchy() {
            // Not implemented
            throw new UnsupportedOperationException();
        }
    }
}
