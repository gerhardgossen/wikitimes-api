package de.l3s.wikitimes.client;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

final class ParameterizedCollectionType<T> implements ParameterizedType {
    private final Class<T> itemType;

    ParameterizedCollectionType(Class<T> itemType) {
        this.itemType = itemType;
    }

    @Override
    public Type getRawType() {
        return Collection.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[] { itemType };
    }
}