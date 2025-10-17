/*
 * Copyright (c) 2020 Network New Technologies Inc.
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
import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import com.networknt.schema.SchemaRegistry.Builder;
import com.networknt.schema.dialect.BasicDialectRegistry;
import com.networknt.schema.dialect.Dialect;
import com.networknt.schema.dialect.Dialects;
import com.networknt.schema.resource.InputStreamSource;
import com.networknt.schema.resource.MapSchemaIdResolver;
import com.networknt.schema.resource.ResourceLoader;
import com.networknt.schema.resource.SchemaIdResolver;

class UriMappingTest {

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Validate that a JSON URI Mapping file containing the URI Mapping schema is
     * schema valid.
     *
     * @throws IOException if unable to parse the mapping file
     */
    @Test
    void testBuilderUriMappingUri() throws IOException {
        URL mappings = UriMappingTest.class.getResource("/uri_mapping/uri-mapping.json");
        Dialect draftV4 = Dialects.getDraft4();
        Builder builder = SchemaRegistry.builder()
                .defaultDialectId(draftV4.getId())
                .dialectRegistry(new BasicDialectRegistry(draftV4))
                .schemaIdResolvers(schemaIdResolvers -> schemaIdResolvers.add(getUriMappingsFromUrl(mappings)));
        SchemaRegistry instance = builder.build();
        Schema schema = instance.getSchema(SchemaLocation.of(
                "https://raw.githubusercontent.com/networknt/json-schema-validator/master/src/test/resources/draft4/extra/uri_mapping/uri-mapping.schema.json"));
        try (InputStream inputStream = mappings.openStream()){
            assertEquals(0, schema.validate(mapper.readTree(inputStream)).size());
        }
    }

    /**
     * Validate that local URI is used when attempting to get a schema that is not
     * available publicly. Use the URL http://example.com/invalid/schema/url to use
     * a URL that returns a 404 Not Found. The locally mapped schema is a
     * valid, but empty schema.
     *
     * @throws IOException if unable to parse the mapping file
     */
    @Test
    void testBuilderExampleMappings() throws IOException {
        ResourceLoader schemaLoader = new ResourceLoader() {
            @Override
            public InputStreamSource getResource(AbsoluteIri absoluteIri) {
                String iri = absoluteIri.toString();
                if ("https://example.com/invalid/schema/url".equals(iri)) {
                    return () -> {
                        throw new FileNotFoundException(iri);
                    };
                }
                return null;
            }
        };
        SchemaRegistry instance = SchemaRegistry.withDefaultDialect(SpecificationVersion.DRAFT_4,
                builder -> builder.resourceLoaders(resourceLoaders -> resourceLoaders.add(schemaLoader)));
        SchemaLocation example = SchemaLocation.of("https://example.com/invalid/schema/url");
        // first test that attempting to use example URL throws an error
        try {
            Schema schema = instance.getSchema(example);
            schema.validate(mapper.createObjectNode());
            fail("Expected exception not thrown");
        } catch (SchemaException ex) {
            Throwable cause = ex.getCause();
            if (!(cause instanceof IOException )) {
                fail("Unexpected cause for JsonSchemaException", ex);
            }
            // passing, so do nothing
        } catch (Exception ex) {
            fail("Unexpected exception thrown", ex);
        }
        URL mappings = UriMappingTest.class.getResource("/uri_mapping/invalid-schema-uri.json");
        Dialect draftV4 = Dialects.getDraft4();
        Builder builder = SchemaRegistry.builder()
                .defaultDialectId(draftV4.getId())
                .dialectRegistry(new BasicDialectRegistry(draftV4))
                .schemaIdResolvers(schemaIdResolvers -> schemaIdResolvers.add(getUriMappingsFromUrl(mappings)));
        instance = builder.build();
        Schema schema = instance.getSchema(example);
        assertEquals(0, schema.validate(mapper.createObjectNode()).size());
    }

    /**
     * Validate that a JSON URI Mapping file containing the URI Mapping schema is
     * schema valid.
     *
     * @throws IOException if unable to parse the mapping file
     */
    @Test
    void testValidatorConfigUriMappingUri() throws IOException {
        URL mappings = UriMappingTest.class.getResource("/uri_mapping/uri-mapping.json");
        SchemaRegistry instance = SchemaRegistry.builder(SchemaRegistry.withDefaultDialect(SpecificationVersion.DRAFT_4))
                .schemaIdResolvers(schemaIdResolvers -> schemaIdResolvers.add(getUriMappingsFromUrl(mappings))).build();
        Schema schema = instance.getSchema(SchemaLocation.of(
                "https://raw.githubusercontent.com/networknt/json-schema-validator/master/src/test/resources/draft4/extra/uri_mapping/uri-mapping.schema.json"));
        try (InputStream inputStream = mappings.openStream()){
            assertEquals(0, schema.validate(mapper.readTree(inputStream)).size());
        }

    }

    /**
     * Validate that local URL is used when attempting to get a schema that is not
     * available publicly. Use the URL http://example.com/invalid/schema/url to use
     * a URL that returns a 404 Not Found. The locally mapped schema is a
     * valid, but empty schema.
     *
     * @throws IOException if unable to parse the mapping file
     */
    @Test
    void testValidatorConfigExampleMappings() throws IOException {
        ResourceLoader schemaLoader = new ResourceLoader() {
            @Override
            public InputStreamSource getResource(AbsoluteIri absoluteIri) {
                String iri = absoluteIri.toString();
                if ("https://example.com/invalid/schema/url".equals(iri)) {
                    return () -> {
                        throw new FileNotFoundException(iri);
                    };
                }
                return null;
            }
        };        
        URL mappings = UriMappingTest.class.getResource("/uri_mapping/invalid-schema-uri.json");
        SchemaRegistry instance = SchemaRegistry.builder(SchemaRegistry.withDefaultDialect(SpecificationVersion.DRAFT_4,
                builder -> builder.resourceLoaders(resourceLoaders -> resourceLoaders.add(schemaLoader)))).build();
        SchemaLocation example = SchemaLocation.of("https://example.com/invalid/schema/url");
        // first test that attempting to use example URL throws an error
        try {
            Schema schema = instance.getSchema(example);
            schema.validate(mapper.createObjectNode());
            fail("Expected exception not thrown");
        } catch (SchemaException ex) {
            Throwable cause = ex.getCause();
            if (!(cause instanceof IOException)) {
                fail("Unexpected cause for JsonSchemaException");
            }
            // passing, so do nothing
        } catch (Exception ex) {
            fail("Unexpected exception thrown");
        }
        instance = SchemaRegistry.builder(SchemaRegistry.withDefaultDialect(SpecificationVersion.DRAFT_4))
                .schemaIdResolvers(schemaIdResolvers -> schemaIdResolvers.add(getUriMappingsFromUrl(mappings))).build();
        Schema schema = instance.getSchema(example);
        assertEquals(0, schema.validate(mapper.createObjectNode()).size());
    }

    @Test
    void testMappingsForRef() throws IOException {
        URL mappings = UriMappingTest.class.getResource("/uri_mapping/schema-with-ref-mapping.json");
        SchemaRegistry instance = SchemaRegistry.builder(SchemaRegistry.withDefaultDialect(SpecificationVersion.DRAFT_4))
                .schemaIdResolvers(schemaIdResolvers -> schemaIdResolvers.add(getUriMappingsFromUrl(mappings))).build();
        Schema schema = instance.getSchema(SchemaLocation.of("resource:uri_mapping/schema-with-ref.json")
                );
        assertEquals(0, schema.validate(mapper.readTree("[]")).size());
    }

    private SchemaIdResolver getUriMappingsFromUrl(URL url) {
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            try (InputStream inputStream = url.openStream()) {
                for (JsonNode mapping : mapper.readTree(inputStream)) {
                    map.put(mapping.get("publicURL").asString(), mapping.get("localURL").asString());
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return new MapSchemaIdResolver(map);
    }
}
