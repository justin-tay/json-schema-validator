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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.joni.exception.SyntaxException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 * Tests for JoniRegularExpression.
 */
class JoniRegularExpressionTest {

    enum InvalidEscapeInput {
        A("\\a"),
        HELLOA("hello\\a"),
        E("\\e"),
        F("\\f"),
        _1("\\1");

        String value;

        InvalidEscapeInput(String value) {
            this.value = value;
        }
    }

    @ParameterizedTest
    @EnumSource(InvalidEscapeInput.class)
    void invalidEscape(InvalidEscapeInput input) {
        SyntaxException e = assertThrows(SyntaxException.class, () -> new JoniRegularExpression(input.value));
        assertEquals("Invalid escape", e.getMessage());
    }
    
    enum ValidEscapeInput {
        W("\\w"),
        D("\\d"),
        CA("\\cA"),
        CB("\\cB"),
        CC("\\cC"),
        CG("\\cG");

        String value;

        ValidEscapeInput(String value) {
            this.value = value;
        }
    }
    
    @ParameterizedTest
    @EnumSource(ValidEscapeInput.class)
    void validEscape(ValidEscapeInput input) {
        assertDoesNotThrow(() -> new JoniRegularExpression(input.value));
    }

}
