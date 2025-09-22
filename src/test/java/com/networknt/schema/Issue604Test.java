package com.networknt.schema;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class Issue604Test {
    @Test
    void failure() {
        SchemaValidatorsConfig config = SchemaValidatorsConfig.builder()
                .applyDefaultsStrategy(new ApplyDefaultsStrategy(true, false, false))
                .build();
        SchemaRegistry factory = SchemaRegistry.withDefaultDialect(Specification.Version.DRAFT_7, builder -> builder.schemaRegistryConfig(config));
        Schema schema = factory.getSchema("{ \"type\": \"object\", \"properties\": { \"foo\": { \"type\": \"object\", \"properties\": { \"bar\": { \"type\": \"boolean\", \"default\": false } } } } }");
        ObjectMapper objectMapper = new ObjectMapper();
        assertDoesNotThrow(() -> {
            schema.walk(objectMapper.readTree("{}"), false);
        });
    }

}
