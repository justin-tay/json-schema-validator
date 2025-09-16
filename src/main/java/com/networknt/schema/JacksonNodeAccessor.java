package com.networknt.schema;

import com.fasterxml.jackson.databind.JsonNode;

public final class JacksonNodeAccessor implements NodeAccessor<JsonNode> {
    @Override
    public JsonNode get(JsonNode node, String propertyName) {
        return node.get(propertyName);
    }
}
