package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public final class ColumnId {

    private String id;

    @JsonCreator
    public ColumnId(String id) {
        this.id = id;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.id;
    }
}
