package com.networknt.schema.regex;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RegularExpressionBenchTest {
    @Test
    void digitGraalJs1() {
        for (int x = 0; x < 100; x++) {
            RegularExpression regex = new GraalJSRegularExpression("\\d");
            assertTrue(regex.matches("1"));
            assertFalse(regex.matches("a"));
        }
    }
    
    @Test
    void digitGraalJs2() {
        for (int x = 0; x < 100; x++) {
            RegularExpression regex = new GraalJSRegularExpression2("\\d");
            assertTrue(regex.matches("1"));
            assertFalse(regex.matches("a"));
        }
    }

    @Test
    void digitJoni() {
        for (int x = 0; x < 100; x++) {
            RegularExpression regex = new JoniRegularExpression("\\d");
            assertTrue(regex.matches("1"));
            assertFalse(regex.matches("a"));
        }
    }

    @Test
    void digitJdk() {
        for (int x = 0; x < 100; x++) {
            RegularExpression regex = new JDKRegularExpression("\\d");
            assertTrue(regex.matches("1"));
            assertFalse(regex.matches("a"));
        }
    }
}
