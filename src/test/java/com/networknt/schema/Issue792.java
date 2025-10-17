package com.networknt.schema;

import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class Issue792 {

    @Test
    void test() throws JacksonException {
        SchemaRegistryConfig config = SchemaRegistryConfig.builder().typeLoose(false).failFast(true).build();

        SchemaRegistry schemaFactory = SchemaRegistry.withDefaultDialect(SpecificationVersion.DRAFT_7, builder -> builder.schemaRegistryConfig(config));

        String schemaDef =
                "{\n" +
                "  \"$schema\": \"http://json-schema.org/draft-07/schema#\",\n" +
                "  \"$id\": \"http://some-id/\",\n" +
                "  \"title\": \"title\",\n" +
                "  \"type\": \"object\",\n" +
                "  \"properties\": {\n" +
                "    \"field\": {\n" +
                "      \"type\": \"string\",\n" +
                "      \"pattern\": \"^[A-Z]{2}$\"\n" +
                "    }\n" +
                "   }\n" +
                "}";

        Schema jsonSchema = schemaFactory.getSchema(schemaDef);
        JsonNode jsonNode = new ObjectMapper().readTree("{\"field\": \"pattern-violation\"}");

        assertEquals(1, jsonSchema.validate(jsonNode).size());
    }
}
