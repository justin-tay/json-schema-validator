package com.networknt.schema;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

class Issue664Test {
    protected Schema getJsonSchemaFromStreamContentV7(InputStream schemaContent) {
        SchemaRegistry factory = SchemaRegistry.withDefaultDialect(SpecificationVersion.DRAFT_7);
        return factory.getSchema(schemaContent);
    }

    protected JsonNode getJsonNodeFromStreamContent(InputStream content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(content);
    }

    @Test
    void shouldHaveFullSchemaPaths() throws Exception {
        String schemaPath = "/schema/issue664-v7.json";
        String dataPath = "/data/issue664.json";
        InputStream schemaInputStream = getClass().getResourceAsStream(schemaPath);
        Schema schema = getJsonSchemaFromStreamContentV7(schemaInputStream);
        InputStream dataInputStream = getClass().getResourceAsStream(dataPath);
        JsonNode node = getJsonNodeFromStreamContent(dataInputStream);
        List<String> errorSchemaPaths = schema.validate(node).stream().map(Error::getSchemaLocation)
                .map(Object::toString).collect(Collectors.toList());

        List<String> expectedSchemaPaths = Arrays.asList(
            "#/items/allOf/0/anyOf/0/oneOf",
            "#/items/allOf/0/anyOf/0/oneOf/0/not",
            "#/items/allOf/1/else/properties/postal_code/pattern",
            "#/items/allOf/1/then/properties/postal_code/pattern"
        );
        MatcherAssert.assertThat(errorSchemaPaths, Matchers.containsInAnyOrder(expectedSchemaPaths.toArray()));
    }
}
