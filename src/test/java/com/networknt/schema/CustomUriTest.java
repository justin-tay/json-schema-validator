package com.networknt.schema;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.resource.InputStreamSource;
import com.networknt.schema.resource.SchemaLoader;

class CustomUriTest {
    @Test
    void customUri() throws Exception {
        /* Given */
        final JsonSchemaFactory factory = buildJsonSchemaFactory();
        final JsonSchema schema = factory.getSchema(
                "{\"$schema\": \"https://json-schema.org/draft/2019-09/schema\",\"type\": \"object\",\"additionalProperties\": false,\"properties\": {\"customAnyOf\": {\"anyOf\": [{\"type\": \"null\"},{\"$ref\": \"custom:date\"}]},\"customOneOf\": {\"oneOf\": [{\"type\": \"null\"},{\"$ref\": \"custom:date\"}]}}}");
        final ObjectMapper mapper = new ObjectMapper();
        final JsonNode value = mapper.readTree("{\"customAnyOf\": null,\"customOneOf\": null}");

        /* When */
        final List<ValidationMessage> errors = schema.validate(value);

        /* Then */
        assertThat(errors.isEmpty(), is(true));
    }

    private JsonSchemaFactory buildJsonSchemaFactory() {
        return JsonSchemaFactory.builder(JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V201909))
                .schemaLoaders(schemaLoaders -> schemaLoaders.add(new CustomUriFetcher())).build();
    }

    private static class CustomUriFetcher implements SchemaLoader {
        private static final String SCHEMA = "{\"$schema\": \"https://json-schema.org/draft/2019-09/schema\",\"$id\":\"custom:date\",\"type\":\"string\",\"format\":\"date\"}";

        @Override
        public InputStreamSource getSchema(AbsoluteIri absoluteIri) {
            return () -> new ByteArrayInputStream(SCHEMA.getBytes(StandardCharsets.UTF_8));
        }
    }
}
