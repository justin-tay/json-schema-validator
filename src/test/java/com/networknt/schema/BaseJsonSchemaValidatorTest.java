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

package com.networknt.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.serialization.JsonMapperFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by steve on 22/10/16.
 */
public class BaseJsonSchemaValidatorTest {

    private static final ObjectMapper mapper = JsonMapperFactory.getInstance();

    public static JsonNode getJsonNodeFromClasspath(String name) throws IOException {
        InputStream is1 = Thread.currentThread().getContextClassLoader()
            .getResourceAsStream(name);
        return mapper.readTree(is1);
    }

    public static JsonNode getJsonNodeFromStringContent(String content) throws IOException {
        return mapper.readTree(content);
    }

    public static JsonNode getJsonNodeFromUrl(String url) throws IOException {
        return mapper.readTree(new URL(url));
    }

    public static Schema getJsonSchemaFromClasspath(String name) {
        return getJsonSchemaFromClasspath(name, Specification.Version.DRAFT_4, null);
    }

    public static Schema getJsonSchemaFromClasspath(String name, Specification.Version schemaVersion) {
        return getJsonSchemaFromClasspath(name, schemaVersion, null);
    }

    public static Schema getJsonSchemaFromClasspath(String name, Specification.Version schemaVersion, SchemaValidatorsConfig config) {
        SchemaRegistry factory = SchemaRegistry.withDefaultDialect(schemaVersion, builder -> builder.schemaRegistryConfig(config));
        InputStream is = Thread.currentThread().getContextClassLoader()
            .getResourceAsStream(name);
        return factory.getSchema(is);
    }

    public static Schema getJsonSchemaFromStringContent(String schemaContent) {
        SchemaRegistry factory = SchemaRegistry.withDefaultDialect(Specification.Version.DRAFT_4);
        return factory.getSchema(schemaContent);
    }

    public static Schema getJsonSchemaFromUrl(String uri) throws URISyntaxException {
        SchemaRegistry factory = SchemaRegistry.withDefaultDialect(Specification.Version.DRAFT_4);
        return factory.getSchema(SchemaLocation.of(uri));
    }

    public static Schema getJsonSchemaFromJsonNode(JsonNode jsonNode) {
        SchemaRegistry factory = SchemaRegistry.withDefaultDialect(Specification.Version.DRAFT_4);
        return factory.getSchema(jsonNode);
    }

    // Automatically detect version for given JsonNode
    public static Schema getJsonSchemaFromJsonNodeAutomaticVersion(JsonNode jsonNode) {
        SchemaRegistry factory = SchemaRegistry.withDefaultDialect(SpecificationVersionDetector.detect(jsonNode));
        return factory.getSchema(jsonNode);
    }

}
