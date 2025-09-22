package com.networknt.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.walk.JsonSchemaWalkListener;
import com.networknt.schema.walk.WalkEvent;
import com.networknt.schema.walk.WalkFlow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Validating anyOf walker
 */
class Issue451Test {

    private static final String COLLECTOR_ID = "collector-451";

    protected Schema getJsonSchemaFromStreamContentV7(InputStream schemaContent) {
        SchemaValidatorsConfig svc = SchemaValidatorsConfig.builder()
                .propertyWalkListener(new CountingWalker())
                .build();
        SchemaRegistry factory = SchemaRegistry.withDefaultDialect(Specification.Version.DRAFT_7, builder -> builder.schemaRegistryConfig(svc));
        return factory.getSchema(schemaContent);
    }

    protected JsonNode getJsonNodeFromStreamContent(InputStream content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(content);
    }

    @Test
    void shouldWalkAnyOfProperties() {
        walk(null, false);
    }

    @Test
    void shouldWalkAnyOfPropertiesWithWithPayloadAndValidation() throws Exception {
        JsonNode data = getJsonNodeFromStreamContent(Issue451Test.class.getResourceAsStream(
                "/data/issue451.json"));
        walk(data,true);
    }

    @Test
    void shouldWalkAnyOfPropertiesWithWithPayload() throws Exception {
        JsonNode data = getJsonNodeFromStreamContent(Issue451Test.class.getResourceAsStream(
                "/data/issue451.json"));
        walk(data, false);
    }

    @SuppressWarnings("unchecked")
    private void walk(JsonNode data, boolean shouldValidate) {
        String schemaPath = "/schema/issue451-v7.json";
        InputStream schemaInputStream = getClass().getResourceAsStream(schemaPath);
        Schema schema = getJsonSchemaFromStreamContentV7(schemaInputStream);

        CollectorContext collectorContext = schema.walk(data, shouldValidate).getCollectorContext();

        Map<String, Integer> collector = (Map<String, Integer>) collectorContext.get(COLLECTOR_ID);
        Assertions.assertEquals(2,
                collector.get("https://example.com/issue-451.json#/definitions/definition1/properties/a"));
        Assertions.assertEquals(2,
                collector.get("https://example.com/issue-451.json#/definitions/definition2/properties/x"));
    }


    private static class CountingWalker implements JsonSchemaWalkListener {
        @Override
        public WalkFlow onWalkStart(WalkEvent walkEvent) {
            SchemaLocation path = walkEvent.getSchema().getSchemaLocation();
            collector(walkEvent.getExecutionContext()).compute(path.toString(), (k, v) -> v == null ? 1 : v + 1);
            return WalkFlow.CONTINUE;
        }

        @Override
        public void onWalkEnd(WalkEvent walkEvent, List<Error> errors) {

        }

        private Map<String, Integer> collector(ExecutionContext executionContext) {
            @SuppressWarnings("unchecked")
            Map<String, Integer> collector = (Map<String, Integer>) executionContext.getCollectorContext().get(COLLECTOR_ID);
            if(collector == null) {
                collector = new HashMap<>();
                executionContext.getCollectorContext().add(COLLECTOR_ID, collector);
            }

            return collector;
        }
    }
}

