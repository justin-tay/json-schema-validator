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

package com.networknt.schema.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

/**
 * Test for ListView.
 */
class ListViewTest {

    @Test
    void testUnion() {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        a.add(1);
        a.add(2);
        c.add(3);

        List<Integer> view = new ListView<Integer>().union(a).union(b).union(c);
        assertEquals(3, view.size());
        List<Integer> values = view.stream().collect(Collectors.toList());
        assertEquals(1, values.get(0));
        assertEquals(2, values.get(1));
        assertEquals(3, values.get(2));
    }

    @Test
    void testToString() {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        a.add(1);
        a.add(2);
        c.add(3);

        List<Integer> view = new ListView<Integer>().union(a).union(b).union(c);
        String value = view.toString();
        assertEquals("[1, 2, 3]", value);
    }

    @Test
    void testIsEmpty() {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);

        ListView<Integer> view = new ListView<>();
        assertTrue(view.isEmpty());
        view.union(a);
        assertFalse(view.isEmpty());
    }

    @Test
    void testEquals() {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        a.add(1);
        a.add(2);
        c.add(3);

        List<Integer> view = new ListView<Integer>().union(a).union(b).union(c);
        assertEquals(3, view.size());

        List<Integer> result = new ArrayList<>();
        result.add(1);
        result.add(2);
        result.add(3);
        assertEquals(result, view);
    }

    @Test
    void testContains() {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        a.add(1);
        a.add(2);
        c.add(3);

        List<Integer> view = new ListView<Integer>().union(a).union(b).union(c);
        assertTrue(view.contains(1));
        assertTrue(view.contains(2));
        assertTrue(view.contains(3));
        assertFalse(view.contains(4));
    }

    @Test
    void testContainsAll() {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        a.add(1);
        a.add(2);
        c.add(3);

        List<Integer> view = new ListView<Integer>().union(a).union(b).union(c);
        List<Integer> result = new ArrayList<>();
        result.add(1);
        result.add(2);
        result.add(3);
        assertTrue(view.containsAll(result));
        result.add(4);
        assertFalse(view.containsAll(result));
    }

    @Test
    void testToArray() {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        a.add(1);
        a.add(2);
        c.add(3);

        List<Integer> view = new ListView<Integer>().union(a).union(b).union(c);
        assertEquals(3, view.size());

        Object[] result = view.toArray();
        assertEquals(3, result.length);
        assertEquals(1, result[0]);
        assertEquals(2, result[1]);
        assertEquals(3, result[2]);
    }

    @Test
    void testToArrayArray() {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        a.add(1);
        a.add(2);
        c.add(3);

        List<Integer> view = new ListView<Integer>().union(a).union(b).union(c);
        assertEquals(3, view.size());

        Integer[] result = view.toArray(new Integer[0]);
        assertEquals(3, result.length);
        assertEquals(1, result[0]);
        assertEquals(2, result[1]);
        assertEquals(3, result[2]);
    }

    @Test
    void testAddAll() {
        List<Integer> view = new ListView<>();
        assertThrows(UnsupportedOperationException.class, () -> view.addAll(Collections.singleton(1)));
    }

    @Test
    void testAdd() {
        List<Integer> view = new ListView<>();
        assertThrows(UnsupportedOperationException.class, () -> view.add(1));
    }

    @Test
    void testClear() {
        List<Integer> view = new ListView<>();
        assertThrows(UnsupportedOperationException.class, () -> view.clear());
    }

    @Test
    void testRemove() {
        List<Integer> view = new ListView<>();
        assertThrows(UnsupportedOperationException.class, () -> view.remove(1));
    }

    @Test
    void testRemoveAll() {
        List<Integer> view = new ListView<>();
        assertThrows(UnsupportedOperationException.class, () -> view.removeAll(Collections.singleton(1)));
    }

    @Test
    void testRetainAll() {
        List<Integer> view = new ListView<>();
        assertThrows(UnsupportedOperationException.class, () -> view.retainAll(Collections.singleton(1)));
    }

    @Test
    void testGetShouldThrowIfIndexOutOfRange() {
        List<Integer> view = new ListView<>();
        assertThrows(IndexOutOfBoundsException.class, () -> view.get(0));
    }

    @Test
    void testNextShouldThrowNoSuchElementException() {
        List<Integer> view = new ListView<>();
        Iterator<Integer> iterator = view.listIterator();
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    void testIndexOf() {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        c.add(4);
        c.add(5);
        c.add(null);
        c.add(3);

        List<Integer> view = new ListView<Integer>().union(a).union(b).union(c);
        assertEquals(0, view.indexOf(1));
        assertEquals(1, view.indexOf(2));
        assertEquals(2, view.indexOf(3));
        assertEquals(3, view.indexOf(4));
        assertEquals(4, view.indexOf(5));
        assertEquals(5, view.indexOf(null));
        assertEquals(-1, view.indexOf(10));
    }

    @Test
    void testLastIndexOf() {
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        c.add(4);
        c.add(5);
        c.add(null);
        c.add(3);

        List<Integer> view = new ListView<Integer>().union(a).union(b).union(c);
        assertEquals(0, view.lastIndexOf(1));
        assertEquals(1, view.lastIndexOf(2));
        assertEquals(6, view.lastIndexOf(3));
        assertEquals(3, view.lastIndexOf(4));
        assertEquals(4, view.lastIndexOf(5));
        assertEquals(5, view.lastIndexOf(null));
        assertEquals(-1, view.lastIndexOf(10));
    }
}
