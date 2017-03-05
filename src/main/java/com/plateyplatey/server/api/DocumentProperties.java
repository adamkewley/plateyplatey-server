package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public final class DocumentProperties {

    @JsonProperty
    @NotNull
    private String documentName;

    public String getDocumentName() {
        return documentName;
    }
}
