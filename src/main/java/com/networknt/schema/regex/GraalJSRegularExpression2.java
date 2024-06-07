package com.networknt.schema.regex;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

class GraalJSRegularExpression2 implements RegularExpression {
    private final String regex;

    GraalJSRegularExpression2(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean matches(String value) {
        try (Context context = Context.newBuilder("js").option("engine.WarnInterpreterOnly", "false")
                .build()){
            Value regexp = context.eval("js",
                    "pattern => {"
                + "  const regex = new RegExp(pattern, 'u');"
                + "  return text => text.match(regex)"
                + "};");
            return !regexp.execute(this.regex).execute(value).isNull();
        }
    }
}