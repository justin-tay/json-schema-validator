package com.networknt.schema;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

class Issue396Test {
    protected Schema getJsonSchemaFromStreamContentV7(InputStream schemaContent) {
        SchemaRegistry factory = SchemaRegistry.withDefaultDialect(SpecificationVersion.DRAFT_7);
        return factory.getSchema(schemaContent);
    }

    protected JsonNode getJsonNodeFromStreamContent(InputStream content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(content);
        return node;
    }

    @Test
    void testComplexPropertyNamesV7() throws Exception {
        String schemaPath = "/schema/issue396-v7.json";
        String dataPath = "/data/issue396.json";
        InputStream schemaInputStream = getClass().getResourceAsStream(schemaPath);
        Schema schema = getJsonSchemaFromStreamContentV7(schemaInputStream);
        InputStream dataInputStream = getClass().getResourceAsStream(dataPath);
        JsonNode node = getJsonNodeFromStreamContent(dataInputStream);

        final Set<String> expected = new HashSet<>();
        node.properties().iterator().forEachRemaining(entry -> {
            if (!entry.getValue().asBoolean())
                expected.add(entry.getKey());
        });

        List<Error> errors = schema.validate(node);
        final Set<String> actual = errors.stream().map(Error::getProperty).map(Object::toString).collect(Collectors.toSet());
        Assertions.assertEquals(expected, actual);
    }
}
