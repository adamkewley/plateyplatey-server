package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public final class ConfigurationId {

    String id;

    @JsonCreator
    public ConfigurationId(String id) {
        this.id = id;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.id;
    }
}
