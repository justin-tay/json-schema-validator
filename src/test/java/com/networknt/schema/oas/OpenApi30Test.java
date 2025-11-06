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
package com.networknt.schema.oas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.networknt.schema.InputFormat;
import com.networknt.schema.Schema;
import com.networknt.schema.SchemaRegistry;
import com.networknt.schema.OutputFormat;
import com.networknt.schema.SchemaLocation;
import com.networknt.schema.SchemaRegistryConfig;
import com.networknt.schema.dialect.Dialect;
import com.networknt.schema.dialect.Dialects;
import com.networknt.schema.path.PathType;
import com.networknt.schema.Error;

/**
 * OpenApi30Test.
 */
class OpenApi30Test {
    /**
     * Test with the explicitly configured OpenApi30 instance.
     */
    @Test
    void validateMetaSchema() {
        SchemaRegistry factory = SchemaRegistry.withDialect(Dialects.getOpenApi30());
        Schema schema = factory.getSchema(SchemaLocation.of(
                "classpath:schema/oas/3.0/petstore.yaml#/paths/~1pet/post/requestBody/content/application~1json/schema"));
        String input = "{\r\n"
                + "  \"petType\": \"dog\",\r\n"
                + "  \"bark\": \"woof\"\r\n"
                + "}";
        List<Error> messages = schema.validate(input, InputFormat.JSON);
        assertEquals(0, messages.size());

        String invalid = "{\r\n"
                + "  \"petType\": \"dog\",\r\n"
                + "  \"meow\": \"meeeooow\"\r\n"
                + "}";
        messages = schema.validate(invalid, InputFormat.JSON);
        assertEquals(2, messages.size());
        List<Error> list = messages.stream().collect(Collectors.toList());
        assertEquals("oneOf", list.get(0).getKeyword());
        assertEquals("required", list.get(1).getKeyword());
        assertEquals("bark", list.get(1).getProperty());
    }

    /**
     * Tests that schema location with number in fragment can resolve.
     */
    @Test
    void jsonPointerWithNumberInFragment() {
        SchemaRegistryConfig config = SchemaRegistryConfig.builder().pathType(PathType.JSON_PATH).build();
        SchemaRegistry factory = SchemaRegistry.withDialect(Dialects.getOpenApi30(), builder -> builder.schemaRegistryConfig(config));
        Schema schema = factory.getSchema(SchemaLocation.of(
                "classpath:schema/oas/3.0/petstore.yaml#/paths/~1pet/post/responses/200/content/application~1json/schema")
                );
        assertNotNull(schema);
        //assertEquals("$.paths['/pet'].post.responses['200'].content['application/json'].schema",
        //        schema.getEvaluationPath().toString());
    }

    /**
     * Exclusive maximum true.
     */
    @Test
    void exclusiveMaximum() {
        String schemaData = "{\r\n"
                + "  \"type\": \"number\",\r\n"
                + "  \"minimum\": 0,\r\n"
                + "  \"maximum\": 100,\r\n"
                + "  \"exclusiveMaximum\": true\r\n"
                + "}\r\n";
        SchemaRegistry factory = SchemaRegistry.withDialect(Dialects.getOpenApi30());
        Schema schema = factory.getSchema(schemaData);
        assertFalse(schema.validate("100", InputFormat.JSON, OutputFormat.BOOLEAN));
    }

    /**
     * Exclusive minimum true.
     */
    @Test
    void exclusiveMinimum() {
        String schemaData = "{\r\n"
                + "  \"type\": \"number\",\r\n"
                + "  \"minimum\": 0,\r\n"
                + "  \"maximum\": 100,\r\n"
                + "  \"exclusiveMinimum\": true\r\n"
                + "}\r\n";
        SchemaRegistry factory = SchemaRegistry.withDialect(Dialects.getOpenApi30());
        Schema schema = factory.getSchema(schemaData);
        assertFalse(schema.validate("0", InputFormat.JSON, OutputFormat.BOOLEAN));
    }

    @Test
    void jsonPointerToSchemaShouldNotFlagUnknownKeywords() {
        String schemaData = "openapi: 3.0.0\r\n"
                + "info:\r\n"
                + "  title: \"Foo\"\r\n"
                + "  version: 1.0.0-dev\r\n"
                + "security:\r\n"
                + "  - apiKey: []\r\n"
                + "servers:\r\n"
                + "  - url: http://localhost:5000\r\n"
                + "paths:\r\n"
                + "  /items:\r\n"
                + "    post:\r\n"
                + "      requestBody:\r\n"
                + "        required: true\r\n"
                + "        content:\r\n"
                + "          application/json:\r\n"
                + "            schema:\r\n"
                + "              type: array\r\n"
                + "              items:\r\n"
                + "                $ref: \"#/components/schemas/Item\"\r\n"
                + "      responses:\r\n"
                + "        '200':\r\n"
                + "          description: Foo\r\n"
                + "        '400':\r\n"
                + "          description: Validation failed for the given request\r\n"
                + "          content:\r\n"
                + "            application/json:\r\n"
                + "              schema:\r\n"
                + "                type: string\r\n"
                + "                description: The validation error\r\n"
                + "        '401':\r\n"
                + "          description: Unauthorized\r\n"
                + "components:\r\n"
                + "  schemas:\r\n"
                + "    Item:\r\n"
                + "      type: object\r\n"
                + "      properties:\r\n"
                + "        Foo:\r\n"
                + "          type: string\r\n"
                + "          pattern: \"^[0-9]+$\"\r\n"
                + "        Bar:\r\n"
                + "          type: string\r\n"
                + "        Baz:\r\n"
                + "          type: string\r\n"
                + "      required:\r\n"
                + "        - Foo\r\n"
                + "        - Bar\r\n"
                + "        - Baz\r\n"
                + "  securitySchemes:\r\n"
                + "    apiKey:\r\n"
                + "      type: apiKey\r\n"
                + "      name: API-KEY\r\n"
                + "      in: header\r\n"
                + "";
        Map<String, String> schemas = new HashMap<>();
        schemas.put("https://example.org/openapi.yaml", schemaData);
        Dialect dialect = Dialect.builder(Dialects.getOpenApi30()).unknownKeywordFactory((keyword, schemaContext) -> {
            throw new IllegalArgumentException("Unknown keyword " + keyword);
        }).build();
        SchemaRegistry schemaRegistry = SchemaRegistry.withDialect(dialect, builder -> builder.schemas(schemas));
        assertDoesNotThrow(() -> {
            schemaRegistry.getSchema(SchemaLocation.of("https://example.org/openapi.yaml#/components/schemas/Item"));
        });
    }
}
