package com.networknt.schema;

public interface NodeAccessor<T> {
    T get(T node, String propertyName);
}
