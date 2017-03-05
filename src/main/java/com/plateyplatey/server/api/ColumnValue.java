package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public final class ColumnValue {

    @JsonProperty
    @NotNull
    private String value;

    public String getValue() {
        return value;
    }
}
