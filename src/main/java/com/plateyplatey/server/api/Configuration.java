package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Map;

public final class Configuration {

    @JsonProperty
    @NotNull
    private Map<String, String> keybinds;

    @JsonProperty
    @NotNull
    private String defaultPlateTemplateId;

    @JsonProperty
    @NotNull
    private String defaultDocumentId;

    public Map<String, String> getKeybinds() {
        return keybinds;
    }

    public String getDefaultPlateTemplateId() {
        return defaultPlateTemplateId;
    }

    public String getDefaultDocumentId() {
        return defaultDocumentId;
    }
}
