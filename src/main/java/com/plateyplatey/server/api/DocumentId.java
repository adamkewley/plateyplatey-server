package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public final class DocumentId {

    private String id;

    @JsonCreator
    public DocumentId(String id) {
        this.id = id;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.id;
    }
}
