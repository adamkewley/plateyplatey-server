package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public final class PlateWellId {

    private final String id;

    @JsonCreator
    public PlateWellId(String id) {
        this.id = id;
    }

    @Override
    @JsonValue
    public String toString() {
        return id;
    }
}
