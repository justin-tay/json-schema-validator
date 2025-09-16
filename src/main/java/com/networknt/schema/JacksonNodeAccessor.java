package com.networknt.schema;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map.Entry;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public final class JacksonNodeAccessor implements NodeAccessor<JsonNode> {
	public static final JacksonNodeAccessor INSTANCE = new JacksonNodeAccessor();

    @Override
    final public JsonNode get(JsonNode node, String propertyName) {
        return node.get(propertyName);
    }

	@Override
	final public int size(JsonNode node) {
		return node.size();
	}

	@Override
	final public Set<Entry<String, JsonNode>> properties(JsonNode node) {
		return node.properties();
	}

	@Override
	final public JsonNode get(JsonNode node, int index) {
		return node.get(index);
	}

	@Override
	final public boolean isArray(JsonNode node) {
		return node.isArray();
	}

	@Override
	final public boolean isNumber(JsonNode node) {
		return node.isNumber();
	}

	@Override
	final public boolean isString(JsonNode node) {
		return node.isShort();
	}

	@Override
	final public boolean isObject(JsonNode node) {
		return node.isObject();
	}

	@Override
	final public boolean isBoolean(JsonNode node) {
		return node.isBoolean();
	}

	@Override
	public boolean isNull(JsonNode node) {
		return node.isNull();
	}

	@Override
	public String stringValue(JsonNode node) {
		return node.textValue();
	}

	@Override
	public boolean booleanValue(JsonNode node) {
		return node.booleanValue();
	}

	@Override
	public boolean canConvertToExactIntegral(JsonNode node) {
		return node.canConvertToExactIntegral();
	}

	@Override
	public boolean isIntegralNumber(JsonNode node) {
		return node.isIntegralNumber();
	}

	@Override
	public boolean isFloatingPointNumber(JsonNode node) {
		return node.isFloatingPointNumber();
	}

	@Override
	public boolean isShort(JsonNode node) {
		return node.isShort();
	}

	@Override
	public boolean isLong(JsonNode node) {
		return node.isLong();
	}

	@Override
	public boolean isFloat(JsonNode node) {
		return node.isFloat();
	}

	@Override
	public boolean isDouble(JsonNode node) {
		return node.isDouble();
	}

	@Override
	public boolean isBigDecimal(JsonNode node) {
		return node.isBigDecimal();
	}

	@Override
	public boolean isBigInteger(JsonNode node) {
		return node.isBigInteger();
	}

	@Override
	public boolean canConvertToInt(JsonNode node) {
		return node.canConvertToInt();
	}

	@Override
	public boolean canConvertToLong(JsonNode node) {
		return node.canConvertToLong();
	}

	@Override
	public BigDecimal decimalValue(JsonNode node) {
		return node.decimalValue();
	}

	@Override
	public BigInteger bigIntegerValue(JsonNode node) {
		return node.bigIntegerValue();
	}

	@Override
	public short shortValue(JsonNode node) {
		return node.shortValue();
	}

	@Override
	public int intValue(JsonNode node) {
		return node.intValue();
	}

	@Override
	public long longValue(JsonNode node) {
		return node.longValue();
	}

	@Override
	public float floatValue(JsonNode node) {
		return node.floatValue();
	}

	@Override
	public double doubleValue(JsonNode node) {
		return node.doubleValue();
	}

	@Override
	public <S extends JsonNode> S set(JsonNode node, String propertyName, JsonNode value) {
		if (node instanceof ObjectNode) {
			return ((ObjectNode) node).set(propertyName, value);
		}
		throw new IllegalArgumentException();
	}

	@Override
	public JsonNode missingNode() {
		return MissingNode.getInstance();
	}
}
