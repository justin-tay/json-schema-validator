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

import java.util.Objects;

/**
 * The schema location is the canonical URI of the schema object plus a JSON
 * Pointer fragment indicating the subschema that produced a result. In contrast
 * with the evaluation path, the schema location MUST NOT include by-reference
 * applicators such as $ref or $dynamicRef.
 */
public class SchemaLocation {
    private final JsonNodePath id;
    private final JsonNodePath fragment;

    private String value = null; // computed lazily

    public SchemaLocation(JsonNodePath id, JsonNodePath fragment) {
        this.id = id;
        this.fragment = fragment;
    }

    public JsonNodePath getId() {
        return id;
    }

    public JsonNodePath getFragment() {
        return fragment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fragment, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SchemaLocation other = (SchemaLocation) obj;
        return Objects.equals(fragment, other.fragment) && Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        if (this.value == null) {
            if (this.id != null && this.fragment == null) {
                this.value = this.id.toString();
            } else {
                StringBuilder result = new StringBuilder();
                if (this.id != null) {
                    result.append(this.id.toString());
                }
                if (this.fragment != null) {
                    result.append("#");
                    result.append(this.fragment.toString());
                }
                this.value = result.toString();
            }
        }
        return this.value;
    }
}
