package com.networknt.schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.uri.InputStreamSource;
import com.networknt.schema.uri.SchemaLoader;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonSchemaFactoryUriCacheTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void cacheEnabled() throws JsonProcessingException {
        runCacheTest(true);
    }

    @Test
    public void cacheDisabled() throws JsonProcessingException {
        runCacheTest(false);
    }

    private void runCacheTest(boolean enableCache) throws JsonProcessingException {
        CustomURIFetcher fetcher = new CustomURIFetcher();
        JsonSchemaFactory factory = buildJsonSchemaFactory(fetcher, enableCache);
        SchemaLocation schemaUri = SchemaLocation.of("cache:uri_mapping/schema1.json");
        String schema = "{ \"$schema\": \"https://json-schema.org/draft/2020-12/schema#\", \"title\": \"json-object-with-schema\", \"type\": \"string\" }";
        fetcher.addResource(schemaUri, schema);
        assertEquals(objectMapper.readTree(schema), factory.getSchema(schemaUri, new SchemaValidatorsConfig()).schemaNode);

        String modifiedSchema = "{ \"$schema\": \"https://json-schema.org/draft/2020-12/schema#\", \"title\": \"json-object-with-schema\", \"type\": \"object\" }";
        fetcher.addResource(schemaUri, modifiedSchema);

        assertEquals(objectMapper.readTree(enableCache ? schema : modifiedSchema), factory.getSchema(schemaUri, new SchemaValidatorsConfig()).schemaNode);
    }

    private JsonSchemaFactory buildJsonSchemaFactory(CustomURIFetcher uriFetcher, boolean enableUriSchemaCache) {
        return JsonSchemaFactory.builder(JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012))
                .enableUriSchemaCache(enableUriSchemaCache)
                .schemaLoaders(schemaLoaders -> schemaLoaders.add(0, uriFetcher))
                .addMetaSchema(JsonMetaSchema.getV202012())
                .build();
    }

    private class CustomURIFetcher implements SchemaLoader {

        private Map<SchemaLocation, InputStream> uriToResource = new HashMap<>();

        void addResource(SchemaLocation uri, String schema) {
            addResource(uri, new ByteArrayInputStream(schema.getBytes(StandardCharsets.UTF_8)));
        }

        void addResource(SchemaLocation uri, InputStream is) {
            uriToResource.put(uri, is);
        }

        @Override
        public InputStreamSource getSchema(SchemaLocation schemaLocation) {
            return () -> uriToResource.get(schemaLocation);
        }
    }
}
