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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.junit.jupiter.api.Test;

/**
 * Test for GraalJSRegularExpression.
 */
class GraalJSRegularExpressionTest {
    @Test
    void digit() {
        RegularExpression regex = new GraalJSRegularExpression("\\d");
        assertTrue(regex.matches("1"));
        assertFalse(regex.matches("a"));
    }

    @Test
    void invalidEscape() {
        RuntimeException e = assertThrows(RuntimeException.class, () -> new GraalJSRegularExpression("\\a"));
        assertEquals("SyntaxError: Invalid escape", e.getMessage());
    }
    
    @Test
    void pattern() {
        RegularExpression regex = new GraalJSRegularExpression("((?<OrgOID>[^,. ]+)\\s*\\.\\s*(?<AOID>[^,. ]+))(?:\\s*,\\s*)?");
        assertTrue(regex.matches("FFFF.12645,AAAA.6456"));
    }

    @Test
    void concurrency() throws Exception {
        RegularExpression regex = new GraalJSRegularExpression("\\d");
        Exception[] instance = new Exception[1];
        CountDownLatch latch = new CountDownLatch(1);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 50; ++i) {
            Runnable runner = new Runnable() {
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        assertTrue(regex.matches("1"));
                    } catch (RuntimeException e) {
                        instance[0] = e;
                    }
                }
            };
            Thread thread = new Thread(runner, "Thread" + i);
            thread.start();
            threads.add(thread);
        }
        latch.countDown(); // Release the latch for threads to run concurrently
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        if (instance[0] != null) {
            throw instance[0];
        }
    }
}
