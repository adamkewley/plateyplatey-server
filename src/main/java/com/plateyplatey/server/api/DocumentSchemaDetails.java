package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public final class DocumentSchemaDetails {

    @JsonProperty
    @NotNull
    private String version;

    public String getVersion() {
        return version;
    }
}
