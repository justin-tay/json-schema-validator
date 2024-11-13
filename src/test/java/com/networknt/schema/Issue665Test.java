package com.networknt.schema;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Issue665Test extends BaseJsonSchemaValidatorTest {

    @Test
    void testUrnUriAsLocalRef() throws IOException {
        JsonSchema schema = getJsonSchemaFromClasspath("draft7/urn/issue665.json", SpecVersion.VersionFlag.V7);
        Assertions.assertNotNull(schema);
        Assertions.assertDoesNotThrow(schema::initializeValidators);
        List<ValidationMessage> messages = schema.validate(getJsonNodeFromStringContent(
                "{\"myData\": {\"value\": \"hello\"}}"));
        Assertions.assertEquals(messages, Collections.emptyList());
    }

    @Test
    void testUrnUriAsLocalRef_ExternalURN() {
        JsonSchemaFactory factory = JsonSchemaFactory
                .builder(JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7))
                .schemaMappers(schemaMappers -> {
                    schemaMappers.mappings(Collections.singletonMap("urn:data",
                            "classpath:draft7/urn/issue665_external_urn_subschema.json"));
                })
                .build();

        try (InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("draft7/urn/issue665_external_urn_ref.json")) {
            JsonSchema schema = factory.getSchema(is);
            Assertions.assertNotNull(schema);
            Assertions.assertDoesNotThrow(schema::initializeValidators);
            List<ValidationMessage> messages = schema.validate(getJsonNodeFromStringContent(
                    "{\"myData\": {\"value\": \"hello\"}}"));
            Assertions.assertEquals(messages, Collections.emptyList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
