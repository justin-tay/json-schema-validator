/*
 * Copyright (c) 2016 Network New Technologies Inc.
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

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.SpecVersion.VersionFlag;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

/**
 * Created by steve on 22/10/16.
 */
public class PatternPropertiesValidatorTest extends BaseJsonSchemaValidatorTest {

    @Test
    public void testInvalidPatternPropertiesValidator() throws Exception {
        Assertions.assertThrows(JsonSchemaException.class, () -> {
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
            JsonSchema schema = factory.getSchema("{\"patternProperties\":6}");

            JsonNode node = getJsonNodeFromStringContent("");
            Set<ValidationMessage> errors = schema.validate(node);
            Assertions.assertEquals(errors.size(), 0);
        });
    }

    @Test
    public void testInvalidPatternPropertiesValidatorECMA262() throws Exception {
        Assertions.assertThrows(JsonSchemaException.class, () -> {
            SchemaValidatorsConfig config = new SchemaValidatorsConfig();
            config.setEcma262Validator(true);
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
            JsonSchema schema = factory.getSchema("{\"patternProperties\":6}", config);

            JsonNode node = getJsonNodeFromStringContent("");
            Set<ValidationMessage> errors = schema.validate(node);
            Assertions.assertEquals(errors.size(), 0);
        });
    }

    @Test
    void message() {
        String schemaData = "{\n"
                + "  \"$id\": \"https://www.example.org/schema\",\n"
                + "  \"type\": \"object\",\n"
                + "  \"patternProperties\": {\n"
                + "    \"^valid_\": {\n"
                + "      \"type\": [\"array\", \"string\"],\n"
                + "      \"items\": {\n"
                + "        \"type\": \"string\"\n"
                + "      }\n"
                + "    }\n"
                + "  }\n"
                + "}";
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V202012);
        SchemaValidatorsConfig config = new SchemaValidatorsConfig();
        config.setPathType(PathType.JSON_POINTER);
        JsonSchema schema = factory.getSchema(schemaData, config);
        String inputData = "{\n"
                + "  \"valid_array\": [\"array1_value\", \"array2_value\"],\n"
                + "  \"valid_string\": \"string_value\",\n"
                + "  \"valid_key\": 5\n"
                + "}";
        Set<ValidationMessage> messages = schema.validate(inputData, InputFormat.JSON);
        assertFalse(messages.isEmpty());
        ValidationMessage message = messages.iterator().next();
        assertEquals("/patternProperties/^valid_/type", message.getEvaluationPath().toString());
        assertEquals("https://www.example.org/schema#/patternProperties/^valid_/type", message.getSchemaLocation().toString());
        assertEquals("/valid_key", message.getInstanceLocation().toString());
        assertEquals("[\"array\",\"string\"]", message.getSchemaNode().toString());
        assertEquals("5", message.getInstanceNode().toString());
        assertEquals("/valid_key: integer found, but [array, string] is required", message.getMessage());
        assertNull(message.getProperty());
        
        String inputData2 = "{\n"
                + "  \"valid_array\": [999, 2],\n"
                + "  \"valid_string\": \"string_value\",\n"
                + "  \"valid_key\": 5\n"
                + "}";
        messages = schema.validate(inputData2, InputFormat.JSON);
        assertFalse(messages.isEmpty());
        message = messages.iterator().next();
        assertEquals("/patternProperties/^valid_/items/type", message.getEvaluationPath().toString());
        assertEquals("https://www.example.org/schema#/patternProperties/^valid_/items/type", message.getSchemaLocation().toString());
        assertEquals("/valid_array/0", message.getInstanceLocation().toString());
        assertEquals("\"string\"", message.getSchemaNode().toString());
        assertEquals("999", message.getInstanceNode().toString());
        assertEquals("/valid_array/0: integer found, string expected", message.getMessage());
        assertNull(message.getProperty());

        
    }
}
