package com.networknt.schema.regex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GraalJSRegularExpressionTest {
    @Test
    void digit() {
        for (int x = 0; x < 100; x++) {
            RegularExpression regex = new GraalJSRegularExpression("\\d");
            assertTrue(regex.matches("1"));
            assertFalse(regex.matches("a"));
        }
    }
    
    @Test
    void invalidEscape() {
        RuntimeException e = assertThrows(RuntimeException.class, () -> new GraalJSRegularExpression("\\a"));
        assertEquals("SyntaxError: Invalid escape", e.getMessage());
    }
}
