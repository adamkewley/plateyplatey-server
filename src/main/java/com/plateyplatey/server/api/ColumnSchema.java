package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public final class ColumnSchema {

    @JsonProperty
    @NotNull
    private ColumnId id;

    @JsonProperty
    @NotNull
    private String header;

    public ColumnId getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }
}
