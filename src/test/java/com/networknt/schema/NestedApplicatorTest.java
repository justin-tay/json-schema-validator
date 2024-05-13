/*
 * Copyright (c) 2024 the original author or authors.
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

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.SpecVersion.VersionFlag;

/**
 * Tests for nested applicators.
 */
public class NestedApplicatorTest {
    @Test
    void anyOfOneOfOneOfFalse() {
        String schemaData = "{\r\n"
                + "  \"anyOf\": [\r\n"
                + "    {\r\n"
                + "      \"oneOf\": [\r\n"
                + "        false,\r\n"
                + "        false\r\n"
                + "      ]\r\n"
                + "    },\r\n"
                + "    {\r\n"
                + "      \"oneOf\": [\r\n"
                + "        false,\r\n"
                + "        false\r\n"
                + "      ]\r\n"
                + "    }\r\n"
                + "  ]\r\n"
                + "}";
        JsonSchema schema = JsonSchemaFactory.getInstance(VersionFlag.V202012).getSchema(schemaData);
        Set<ValidationMessage> messages = schema.validate("{}", InputFormat.JSON);
        Assertions.assertFalse(messages.isEmpty());
    }

    @Test
    void anyOfOneOfOneOfTrue() {
        String schemaData = "{\r\n"
                + "  \"anyOf\": [\r\n"
                + "    {\r\n"
                + "      \"oneOf\": [\r\n"
                + "        false,\r\n"
                + "        false\r\n"
                + "      ]\r\n"
                + "    },\r\n"
                + "    {\r\n"
                + "      \"oneOf\": [\r\n"
                + "        false,\r\n"
                + "        true\r\n"
                + "      ]\r\n"
                + "    }\r\n"
                + "  ]\r\n"
                + "}";
        JsonSchema schema = JsonSchemaFactory.getInstance(VersionFlag.V202012).getSchema(schemaData);
        Set<ValidationMessage> messages = schema.validate("{}", InputFormat.JSON);
        Assertions.assertTrue(messages.isEmpty());
    }

    @Test
    void allOfOneOfAnyOfFalse() {
        String schemaData = "{\r\n"
                + "  \"allOf\": [\r\n"
                + "    {\r\n"
                + "      \"oneOf\": [\r\n"
                + "        false,\r\n"
                + "        false\r\n"
                + "      ]\r\n"
                + "    },\r\n"
                + "    {\r\n"
                + "      \"anyOf\": [\r\n"
                + "        false,\r\n"
                + "        false\r\n"
                + "      ]\r\n"
                + "    }\r\n"
                + "  ]\r\n"
                + "}";
        JsonSchema schema = JsonSchemaFactory.getInstance(VersionFlag.V202012).getSchema(schemaData);
        Set<ValidationMessage> messages = schema.validate("{}", InputFormat.JSON);
        Assertions.assertFalse(messages.isEmpty());
    }

    @Test
    void allOfOneOfAnyOfTrue() {
        String schemaData = "{\r\n"
                + "  \"allOf\": [\r\n"
                + "    {\r\n"
                + "      \"oneOf\": [\r\n"
                + "        false,\r\n"
                + "        true\r\n"
                + "      ]\r\n"
                + "    },\r\n"
                + "    {\r\n"
                + "      \"anyOf\": [\r\n"
                + "        false,\r\n"
                + "        true\r\n"
                + "      ]\r\n"
                + "    }\r\n"
                + "  ]\r\n"
                + "}";
        JsonSchema schema = JsonSchemaFactory.getInstance(VersionFlag.V202012).getSchema(schemaData);
        Set<ValidationMessage> messages = schema.validate("{}", InputFormat.JSON);
        Assertions.assertTrue(messages.isEmpty());
    }

    @Test
    void oneOfAllOfAnyOfFalse() {
        String schemaData = "{\r\n"
                + "  \"oneOf\": [\r\n"
                + "    {\r\n"
                + "      \"allOf\": [\r\n"
                + "        false,\r\n"
                + "        true\r\n"
                + "      ]\r\n"
                + "    },\r\n"
                + "    {\r\n"
                + "      \"anyOf\": [\r\n"
                + "        false,\r\n"
                + "        false\r\n"
                + "      ]\r\n"
                + "    }\r\n"
                + "  ]\r\n"
                + "}";
        JsonSchema schema = JsonSchemaFactory.getInstance(VersionFlag.V202012).getSchema(schemaData);
        Set<ValidationMessage> messages = schema.validate("{}", InputFormat.JSON);
        Assertions.assertFalse(messages.isEmpty());
    }

    @Test
    void oneOfAllOfAnyOfTrue() {
        String schemaData = "{\r\n"
                + "  \"oneOf\": [\r\n"
                + "    {\r\n"
                + "      \"allOf\": [\r\n"
                + "        false,\r\n"
                + "        true\r\n"
                + "      ]\r\n"
                + "    },\r\n"
                + "    {\r\n"
                + "      \"anyOf\": [\r\n"
                + "        false,\r\n"
                + "        true\r\n"
                + "      ]\r\n"
                + "    }\r\n"
                + "  ]\r\n"
                + "}";
        JsonSchema schema = JsonSchemaFactory.getInstance(VersionFlag.V202012).getSchema(schemaData);
        Set<ValidationMessage> messages = schema.validate("{}", InputFormat.JSON);
        Assertions.assertTrue(messages.isEmpty());
    }

    @Test
    void example1() {
        JsonSchema schema = JsonSchemaFactory.getInstance(VersionFlag.V202012)
                .getSchema(SchemaLocation.of("classpath:/schema/inheritance_test.yaml#/components/schemas/Example1"));
        System.out.println(schema.validate("{}", InputFormat.JSON, OutputFormat.HIERARCHICAL));
    }

    @Test
    void example2() {
        JsonSchema schema = JsonSchemaFactory.getInstance(VersionFlag.V202012)
                .getSchema(SchemaLocation.of("classpath:/schema/inheritance_test.yaml#/components/schemas/Example2"));
        System.out.println(schema.validate("{}", InputFormat.JSON, OutputFormat.HIERARCHICAL));
    }

    @Test
    void example3() {
        JsonSchema schema = JsonSchemaFactory.getInstance(VersionFlag.V202012)
                .getSchema(SchemaLocation.of("classpath:/schema/inheritance_test.yaml#/components/schemas/Example3"));
        System.out.println(schema.validate("{}", InputFormat.JSON, OutputFormat.HIERARCHICAL));
    }

    @Test
    void example1a() {
        JsonSchema schema = JsonSchemaFactory.getInstance(VersionFlag.V202012)
                .getSchema(SchemaLocation.of("classpath:/schema/inheritance_test.yaml#/components/schemas/Example1a"));
        System.out.println(schema.validate("{}", InputFormat.JSON, OutputFormat.HIERARCHICAL));
    }
    
    @Test
    void t1() throws JsonMappingException, JsonProcessingException {
        String schemaData = "{\r\n"
                + "        \"type\" : \"object\",\r\n"
                + "        \"allOf\" : [\r\n"
                + "            {\r\n"
                + "                \"type\" : \"object\",\r\n"
                + "                \"anyOf\" : [\r\n"
                + "                    {\r\n"
                + "                        \"type\" : \"object\",\r\n"
                + "                        \"properties\" : {\r\n"
                + "                            \"data\" : {\r\n"
                + "                                \"type\" : \"string\"\r\n"
                + "                            }\r\n"
                + "                        }\r\n"
                + "                    },\r\n"
                + "                    {\r\n"
                + "                        \"type\" : \"object\",\r\n"
                + "                        \"properties\" : {\r\n"
                + "                            \"data\" : {\r\n"
                + "                                \"type\" : \"number\"\r\n"
                + "                            }\r\n"
                + "                        }\r\n"
                + "                    }\r\n"
                + "                ]\r\n"
                + "            },\r\n"
                + "            {\r\n"
                + "                \"type\" : \"object\",\r\n"
                + "                \"anyOf\" : [\r\n"
                + "                    {\r\n"
                + "                        \"type\" : \"object\",\r\n"
                + "                        \"properties\" : {\r\n"
                + "                            \"data\" : {\r\n"
                + "                                \"type\" : \"boolean\"\r\n"
                + "                            }\r\n"
                + "                        }\r\n"
                + "                    },\r\n"
                + "                    {\r\n"
                + "                        \"type\" : \"object\",\r\n"
                + "                        \"properties\" : {\r\n"
                + "                            \"data\" : {\r\n"
                + "                                \"type\" : \"string\",\r\n"
                + "                                \"enum\" : [\r\n"
                + "                                    \"value1\",\r\n"
                + "                                    \"value2\"\r\n"
                + "                                ]\r\n"
                + "                            }\r\n"
                + "                        }\r\n"
                + "                    }\r\n"
                + "                ]\r\n"
                + "            }\r\n"
                + "        ]\r\n"
                + "    }";
        JsonSchema schema = JsonSchemaFactory.getInstance(VersionFlag.V202012).getSchema(schemaData);
        Set<ValidationMessage> messages = schema.validate("{}", InputFormat.JSON);
        Assertions.assertTrue(messages.isEmpty());
        messages = schema.validate("{\r\n"
                + "        \"data\" : \"value1\"\r\n"
                + "    }", InputFormat.JSON);
        Assertions.assertTrue(messages.isEmpty());
        messages = schema.validate("{\r\n"
                + "        \"data\" : \"data\"\r\n"
                + "    }", InputFormat.JSON);
        Assertions.assertFalse(messages.isEmpty());
        messages = schema.validate("{\r\n"
                + "        \"data\" : true,\r\n"
                + "        \"data\" : \"value1\"\r\n"
                + "    }", InputFormat.JSON);
        Assertions.assertTrue(messages.isEmpty());
        
        JsonNode node = new ObjectMapper().readValue("{\r\n"
                + "        \"data\" : true,\r\n"
                + "        \"data\" : \"value1\"\r\n"
                + "    }", JsonNode.class);
        System.out.println(node);

    }

}
