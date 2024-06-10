package com.networknt.schema.regex;

/**
 * GraalJS {@link RegularExpressionFactory}.
 */
public class GraalJSRegularExpressionFactory implements RegularExpressionFactory {
    private static final GraalJSRegularExpressionFactory INSTANCE = new GraalJSRegularExpressionFactory();
    
    public static GraalJSRegularExpressionFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public RegularExpression getRegularExpression(String regex) {
        return new GraalJSRegularExpression(regex);
    }
}
