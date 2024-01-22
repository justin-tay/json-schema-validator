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
package com.networknt.schema.uri;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Schema Loaders.
 */
public class SchemaLoaders extends ArrayList<SchemaLoader> {
    private static final long serialVersionUID = 1L;

    public SchemaLoaders() {
        super();
    }

    public SchemaLoaders(Collection<? extends SchemaLoader> c) {
        super(c);
    }

    public SchemaLoaders(int initialCapacity) {
        super(initialCapacity);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<SchemaLoader> values = new ArrayList<>();

        public Builder() {
        }

        public Builder(Builder copy) {
            this.values.addAll(copy.values);
        }

        public Builder values(Consumer<List<SchemaLoader>> values) {
            values.accept(this.values);
            return this;
        }
        
        public Builder add(SchemaLoader schemaLoader) {
            this.values.add(schemaLoader);
            return this;
        }

        public Builder values(Map<String, String> mappings) {
            this.values.add(new MapSchemaLoader(mappings));
            return this;
        }
        
        public Builder values(Function<String, String> mappings) {
            this.values.add(new MapSchemaLoader(mappings));
            return this;
        }

        public SchemaLoaders build() {
            List<SchemaLoader> result = new ArrayList<>();
            result.add(new ClasspathSchemaLoader());
            result.addAll(this.values);
            result.add(new UriSchemaLoader());
            return new SchemaLoaders(result);
        }
    }
}