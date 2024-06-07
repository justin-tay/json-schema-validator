package com.networknt.schema.regex;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

class GraalJSRegularExpression implements RegularExpression {
    private static final Context CONTEXT = GraalJSContextFactory.getInstance();
    private static final Value REGEXP_BUILDER = CONTEXT.eval("js",
                    "pattern => {"
                + "  const regex = new RegExp(pattern, 'u');"
                + "  return text => text.match(regex)"
                + "};");

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