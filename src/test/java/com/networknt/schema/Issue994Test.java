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

package com.networknt.schema;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import com.networknt.schema.dialect.Dialect;
import com.networknt.schema.dialect.Dialects;
import com.networknt.schema.serialization.JsonMapperFactory;
import com.networknt.schema.vocabulary.Vocabulary;

class Issue994Test {
    @Test
    void test() throws JacksonException {
        String schemaData = "{\r\n"
                + "    \"$schema\": \"https://json-schema.org/draft/2020-12/schema\",\r\n"
                + "    \"type\": \"object\",\r\n"
                + "    \"properties\": {\r\n"
                + "        \"asString\": {\r\n"
                + "            \"type\": [\r\n"
                + "                \"string\",\r\n"
                + "                \"null\"\r\n"
                + "            ],\r\n"
                + "            \"isMandatory\": true\r\n"
                + "        }\r\n"
                + "    }\r\n"
                + "}";
        Dialect dialect = Dialect.builder(Dialects.getDraft202012()).vocabularies(vocabularies -> {
            vocabularies.remove(Vocabulary.DRAFT_2020_12_VALIDATION.getId());
        }).build();
        JsonNode schemaNode = JsonMapperFactory.getInstance().readTree(schemaData);
        Schema schema = SchemaRegistry
                .withDialect(dialect).getSchema(schemaNode);
        String inputData = "{\r\n"
                + "  \"asString\": \"hello\"\r\n"
                + "}";
        assertTrue(schema.validate(inputData, InputFormat.JSON, OutputFormat.BOOLEAN));
    }
}
