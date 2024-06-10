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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test for GraalJSRegularExpression.
 */
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
