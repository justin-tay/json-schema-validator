package com.networknt.schema;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import com.networknt.schema.keyword.KeywordType;
import com.networknt.schema.serialization.JsonMapperFactory;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Abstract class to use if the data JSON has a declared schema node at root level
 *
 * @see Issue769ContainsTest
 * @author vwuilbea
 */
abstract class AbstractJsonSchemaTest {

    private static final String SCHEMA = "$schema";
    private static final SpecificationVersion DEFAULT_VERSION_FLAG = SpecificationVersion.DRAFT_2020_12;
    private static final String ASSERT_MSG_KEYWORD = "Validation result should contain {0} keyword";

    protected List<Error> validate(String dataPath) {
        JsonNode dataNode = getJsonNodeFromPath(dataPath);
        return getJsonSchemaFromDataNode(dataNode).validate(dataNode);
    }

    protected void assertValidatorType(String filename, KeywordType validatorTypeCode) {
        List<Error> errors = validate(getDataTestFolder() + filename);

        assertTrue(
                errors.stream().anyMatch(vm -> validatorTypeCode.getValue().equals(vm.getKeyword())),
                () -> MessageFormat.format(ASSERT_MSG_KEYWORD, validatorTypeCode.getValue()));
    }

    protected abstract String getDataTestFolder();

    private Schema getJsonSchemaFromDataNode(JsonNode dataNode) {
        return Optional.ofNullable(dataNode.get(SCHEMA))
                .map(JsonNode::asString)
                .map(this::getJsonNodeFromPath)
                .map(this::getJsonSchema)
                .orElseThrow(() -> new IllegalArgumentException("No schema found on document to test"));
    }

    private JsonNode getJsonNodeFromPath(String dataPath) {
        InputStream dataInputStream = getClass().getResourceAsStream(dataPath);
        ObjectMapper mapper = JsonMapperFactory.getInstance();
        return mapper.readTree(dataInputStream);
    }

    private Schema getJsonSchema(JsonNode schemaNode) {
        return SchemaRegistry
                .withDefaultDialect(SpecificationVersionDetector.detectOptionalVersion(schemaNode, false).orElse(DEFAULT_VERSION_FLAG))
                .getSchema(schemaNode);
    }

}
