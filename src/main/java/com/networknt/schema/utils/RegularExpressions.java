/*
 * Copyright (c) 2025 the original author or authors.
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
package com.networknt.schema.utils;

/**
 * Utility methods for Regular Expressions.
 */
public class RegularExpressions {
    private RegularExpressions() {
    }

    /**
     * The meaning of $ in ecmascript does not allow newlines while for other
     * languages it is typically allowed. The closest to the meaning in ecmascript
     * is \z.
     * 
     * @param regex the regex
     * @return the replacement
     */
    public static String replaceDollarAnchors(String regex) {
        if (regex.indexOf('$') == -1) {
            return regex;
        }
        /*
         * Note that for joni there's no option for this and this occurs in the Lexer
         * when the regex is compiled. If single line $ is AnchorType.SEMI_END_BUF and
         * if multiline is AnchorType.END_LINE. However what is required is
         * AnchorType.END_BUF.
         */
        StringBuilder result = new StringBuilder();
        boolean inCharacterClass = false;
        boolean inLiteralQuotingSection = false; // This isn't supported by ECMA but by Java
        for (int i = 0; i < regex.length(); i++) {
            char currentChar = regex.charAt(i);
            // Escaped
            if (currentChar == '\\') {
                result.append(currentChar);
                if (i + 1 < regex.length()) {
                    char escapedChar = regex.charAt(i + 1); 
                    result.append(escapedChar);
                    if (escapedChar == 'Q') {
                        inLiteralQuotingSection = true;
                    } else if (escapedChar == 'E') {
                        inLiteralQuotingSection = false;
                    }
                    i++;
                }
                continue;
            }
            // Literal Quoting Section (not supported by ECMA)
            if (inLiteralQuotingSection) {
                result.append(currentChar);
                continue;
            }
            // Character Class
            if (currentChar == '[') {
                inCharacterClass = true;
                result.append(currentChar);
                continue;
            } else if (currentChar == ']') {
                inCharacterClass = false;
                result.append(currentChar);
                continue;
            }

            if (currentChar == '$') {
                if (inCharacterClass) {
                    result.append(currentChar);
                } else {
                    result.append("\\z");
                }
            } else {
                result.append(currentChar);
            }
        }
        return result.toString();
    }
}
