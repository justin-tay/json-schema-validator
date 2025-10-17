package com.networknt.schema;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ArrayNode;
import com.networknt.schema.dialect.Dialects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class OpenAPI30JsonSchemaTest {
    protected ObjectMapper mapper = new ObjectMapper();

    OpenAPI30JsonSchemaTest() {
    }

    private void runTestFile(String testCaseFile) throws Exception {
        final SchemaLocation testCaseFileUri = SchemaLocation.of("classpath:" + testCaseFile);
        InputStream in = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(testCaseFile);
        ArrayNode testCases = mapper.readValue(in, ArrayNode.class);

        for (int j = 0; j < testCases.size(); j++) {
            try {
                JsonNode testCase = testCases.get(j);
                System.out.println("Test Case ["+(j+1)+"]: "+testCase.get("description"));
                //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testCase.get("schema")));
                ArrayNode testNodes = (ArrayNode) testCase.get("tests");
                for (int i = 0; i < testNodes.size(); i++) {
                    JsonNode test = testNodes.get(i);
                    System.out.println("> Test Data ["+(i+1)+"]: "+test);
                    JsonNode node = test.get("data");
                    JsonNode typeLooseNode = test.get("isTypeLoose");
                    // Configure the schemaValidator to set typeLoose's value based on the test file,
                    // if test file do not contains typeLoose flag, use default value: true.
                    SchemaRegistryConfig.Builder configBuilder = SchemaRegistryConfig.builder();
                    configBuilder.typeLoose(typeLooseNode != null && typeLooseNode.asBoolean());
                    SchemaRegistry validatorFactory = SchemaRegistry.withDialect(Dialects.getOpenApi30(),
                            builder -> builder.schemaRegistryConfig(configBuilder.build()));

                    Schema schema = validatorFactory.getSchema(testCaseFileUri, testCase.get("schema"));

                    List<Error> errors = new ArrayList<Error>(schema.validate(node));

                    if (test.get("valid").asBoolean()) {
                        if (!errors.isEmpty()) {
                            System.out.println("---- Test Data ["+(i+1)+"] FAILED [Unexpected Errors] ----");
                            System.out.println("> Schema: " + schema);
                            System.out.println("> Data  : " + test.get("data"));
                            System.out.println("> Errors:");
                            for (Error error : errors) {
                                System.out.println(error);
                            }
                        }
                        assertEquals(0, errors.size());
                    } else {
//                    	System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(testCase.get("schema")));
                        if (errors.isEmpty()) {
                            System.out.println("---- Test Data ["+(i+1)+"] FAILED [Unexpected Success] ----");
                            System.out.println("> Schema: " + schema);
                            System.out.println("> Data  : " + test.get("data"));
                        } else {
                            JsonNode errorCount = test.get("errorCount");
                            if (errorCount != null && errorCount.isInt() && errors.size() != errorCount.asInt()) {
                                System.out.println("---- Test Data [" + (i + 1) + "] FAILED [Expected "
                                        + errorCount.asInt() + " Errors but was " + errors.size() + "] ----");
                                System.out.println("> Schema: " + schema);
                                System.out.println("> Data  : " + test.get("data"));
                                System.out.println("> Errors: " + errors);
                                for (Error error : errors) {
                                    System.out.println(error);
                                }
                                assertEquals(errorCount.asInt(), errors.size(), "expected error count");
                            }
                        }
                        assertFalse(errors.isEmpty());
                    }
                }
            } catch (SchemaException e) {
                throw new IllegalStateException(String.format("Current schema should not be invalid: %s", testCaseFile), e);
            }
        }
    }

    @Test
    void testDiscriminatorMapping() throws Exception {
        runTestFile("openapi3/discriminator.json");
    }
}
