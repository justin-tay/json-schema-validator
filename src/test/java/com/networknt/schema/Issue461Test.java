package com.networknt.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.keyword.ValidatorTypeCode;
import com.networknt.schema.serialization.JsonMapperFactory;
import com.networknt.schema.walk.JsonSchemaWalkListener;
import com.networknt.schema.walk.WalkEvent;
import com.networknt.schema.walk.WalkFlow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class Issue461Test {
    protected ObjectMapper mapper = JsonMapperFactory.getInstance();

    protected Schema getJsonSchemaFromStreamContentV7(SchemaLocation schemaUri) {
        SchemaRegistryConfig svc = SchemaRegistryConfig.builder()
                .keywordWalkListener(ValidatorTypeCode.PROPERTIES.getValue(), new Walker())
                .build();
        SchemaRegistry factory = SchemaRegistry.withDefaultDialect(Specification.Version.DRAFT_7, builder -> builder.schemaRegistryConfig(svc));
        return factory.getSchema(schemaUri);
    }

    @Test
    void shouldWalkWithValidation() throws IOException {
        Schema schema = getJsonSchemaFromStreamContentV7(SchemaLocation.of("resource:/draft-07/schema#"));
        JsonNode data = mapper.readTree(Issue461Test.class.getResource("/data/issue461-v7.json"));
        ValidationResult result = schema.walk(data, true);
        Assertions.assertTrue(result.getErrors().isEmpty());
    }

    /**
     * Example NOP walker
     */
    private static class Walker implements JsonSchemaWalkListener {
        @Override
        public WalkFlow onWalkStart(final WalkEvent walkEvent) {
            return WalkFlow.CONTINUE;
        }

        @Override
        public void onWalkEnd(final WalkEvent walkEvent,
                              final List<Error> errors) {
        }
    }
}
