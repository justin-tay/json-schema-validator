package com.networknt.schema.regex;

/**
 * Factory for {@link RegularExpression}.
 */
public interface RegularExpressionFactory {
    /**
     * Gets a {@link RegularExpression}.
     * 
     * @param regex the regular expression text value
     * @return the regular expression
     */
    RegularExpression getRegularExpression(String regex);
}
