package com.networknt.schema;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Set;

public interface NodeAccessor<T> {
	int size(T node);
    T get(T node, String propertyName);
    T get(T node, int index);
    Set<Map.Entry<String, T>> properties(T node);
    boolean isArray(T node);
    boolean isNumber(T node);
    boolean isString(T node);
    boolean isObject(T node);
    boolean isBoolean(T node);
    boolean isNull(T node);
    boolean isIntegralNumber(T node);
    boolean isFloatingPointNumber(T node);
    boolean isShort(T node);
    boolean isLong(T node);
    boolean isFloat(T node);
    boolean isDouble(T node);
    boolean isBigDecimal(T node);
    boolean isBigInteger(T node);
    String stringValue(T node);
    boolean booleanValue(T node);
    boolean canConvertToExactIntegral(T node);
    boolean canConvertToInt(T node);
    boolean canConvertToLong(T node);
    BigDecimal decimalValue(T node);
    BigInteger bigIntegerValue(T node);
    short shortValue(T node);
    int intValue(T node);
    long longValue(T node);
    float floatValue(T node);
    double doubleValue(T node);
    <S extends T> S set(T node, String propertyName, T value);
    T missingNode();
}
