/*
 * Copyright (c) 2023 the original author or authors.
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
package com.networknt.schema;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UriReferenceTest {

    @Test
    void document() {
        assertEquals("#", UriReference.DOCUMENT.toString());
    }

    @Test
    void root() {
        assertEquals("", UriReference.ROOT.toString());
    }

    @Test
    void absolute() {
        JsonNodePath uriReference = UriReference.get("http://json-schema.org/draft-04/schema#");
        assertEquals("http://json-schema.org/draft-04/schema#", uriReference.toString());
        assertEquals("http://json-schema.org", uriReference.getName(0));
        assertEquals("draft-04", uriReference.getName(1));
        assertEquals("schema#", uriReference.getName(2));
        assertEquals(3, uriReference.getNameCount());
    }

    @Test
    void absoluteHost() {
        JsonNodePath uriReference = UriReference.get("http://json-schema.org");
        assertEquals("http://json-schema.org", uriReference.toString());
        assertEquals("http://json-schema.org", uriReference.getName(0));
        assertEquals(1, uriReference.getNameCount());
    }

    @Test
    void absolutePath() {
        JsonNodePath uriReference = UriReference.get("http://json-schema.org/schema#");
        assertEquals("http://json-schema.org/schema#", uriReference.toString());
        assertEquals("http://json-schema.org", uriReference.getName(0));
        assertEquals("schema#", uriReference.getName(1));
        assertEquals(2, uriReference.getNameCount());
    }

    @Test
    void basePath() {
        JsonNodePath path = UriReference.get("http://json-schema.org/schema");
        JsonNodePath base = UriReference.getBase(path);
        assertEquals(base, path);
    }
    
    @Test
    void basePathFragment() {
        JsonNodePath path = UriReference.get("http://json-schema.org/schema#");
        JsonNodePath base = UriReference.getBase(path);
        assertEquals(base, path);
    }
    
    @Test
    void basePathIndexFragment() {
        JsonNodePath path = UriReference.get("http://json-schema.org/schema/#");
        JsonNodePath base = UriReference.getBase(path);
        assertEquals(base, path);
    }
    
    @Test
    void basePathIndex() {
        JsonNodePath path = UriReference.get("http://json-schema.org/schema/");
        JsonNodePath base = UriReference.getBase(path);
        assertEquals(base, path);
    }

}
