package com.networknt.schema.regex;

/**
 * JDK {@link RegularExpressionFactory}.
 */
public class JDKRegularExpressionFactory implements RegularExpressionFactory {
    private static final JDKRegularExpressionFactory INSTANCE = new JDKRegularExpressionFactory();
    
    public static JDKRegularExpressionFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public RegularExpression getRegularExpression(String regex) {
        return new JDKRegularExpression(regex);
    }
}
