/*
 * Copyright (c) 2024 the original author or authors.
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
package com.networknt.schema.regex;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

/**
 * GraalJS {@link RegularExpression}.
 * <p>
 * This requires a dependency on org.graalvm.js:js which along with its
 * dependency libraries are 50 mb.
 */
class GraalJSRegularExpression implements RegularExpression {
    private static final Context CONTEXT = GraalJSContextFactory.getInstance();
    private static final String SOURCE = "pattern => {\n"
            + "    const regex = new RegExp(pattern, 'u');\n"
            + "    return text => text.match(regex)\n"
            + "};";
    private static final Value REGEXP_BUILDER = CONTEXT.eval("js", SOURCE);

    private final Value function;

    GraalJSRegularExpression(String regex) {
        synchronized(CONTEXT) {
            this.function = REGEXP_BUILDER.execute(regex);
        }
    }

    @Override
    public boolean matches(String value) {
        synchronized(CONTEXT) {
            return !function.execute(value).isNull();
        }
    }
}