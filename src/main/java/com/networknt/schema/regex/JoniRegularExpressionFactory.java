package com.networknt.schema.regex;

/**
 * Joni {@link RegularExpressionFactory}.
 */
public class JoniRegularExpressionFactory implements RegularExpressionFactory {
    private static final JoniRegularExpressionFactory INSTANCE = new JoniRegularExpressionFactory();

    public static JoniRegularExpressionFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public RegularExpression getRegularExpression(String regex) {
        return new JoniRegularExpression(regex);
    }
}
