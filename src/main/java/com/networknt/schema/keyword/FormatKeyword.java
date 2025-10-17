/*
 * Copyright (c) 2016 Network New Technologies Inc.
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

package com.networknt.schema.keyword;

import tools.jackson.databind.JsonNode;
import com.networknt.schema.Schema;
import com.networknt.schema.SchemaLocation;
import com.networknt.schema.format.Format;
import com.networknt.schema.SchemaContext;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Format Keyword.
 */
public class FormatKeyword implements Keyword {
    private final String value;
    private final Map<String, Format> formats;
    
    public FormatKeyword(Map<String, Format> formats) {
        this(KeywordType.FORMAT, formats);
    }

    public FormatKeyword(Keyword type, Map<String, Format> formats) {
        this(type.getValue(), formats);
    }

    public FormatKeyword(String value, Map<String, Format> formats) {
        this.value = value;
        this.formats = formats;
    }

    Collection<Format> getFormats() {
        return Collections.unmodifiableCollection(this.formats.values());
    }

    @Override
    public KeywordValidator newValidator(SchemaLocation schemaLocation, JsonNode schemaNode, Schema parentSchema, SchemaContext schemaContext) {
        Format format = null;
        if (schemaNode != null && schemaNode.isString()) {
            String formatName = schemaNode.asString();
            format = this.formats.get(formatName);
        }
        return new FormatValidator(schemaLocation, schemaNode, parentSchema, schemaContext, format,
                this);
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
