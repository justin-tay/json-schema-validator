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

import java.util.Set;

/**
 * Formats the validation results.
 * 
 * @param <T> the result type
 */
public interface OutputFormat<T> {
    /**
     * Customize the execution context before validation.
     * <p>
     * The validation context should only be used for reference as it is shared.
     * 
     * @param executionContext  the execution context
     * @param validationContext the validation context for reference
     */
    default void customize(ExecutionContext executionContext, ValidationContext validationContext) {
    }

    /**
     * Formats the validation results.
     * 
     * @param validationMessages
     * @param executionContext
     * @return the result
     */
    T format(Set<ValidationMessage> validationMessages, ExecutionContext executionContext,
            ValidationContext validationContext);

    public static class FlagOutput {
        private final boolean valid;

        public FlagOutput(boolean valid) {
            this.valid = valid;
        }

        public boolean isValid() {
            return this.valid;
        }
    }

    public static final Flag FLAG = new Flag();

    public static class Flag implements OutputFormat<FlagOutput> {

        @Override
        public FlagOutput format(Set<ValidationMessage> validationMessages, ExecutionContext executionContext,
                ValidationContext validationContext) {
            return new FlagOutput(validationMessages.isEmpty());
        }
    }
}
