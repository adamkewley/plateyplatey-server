package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public final class PlateWellSelector {

    @JsonProperty
    private int x;

    @JsonProperty
    private int y;

    @JsonProperty
    private String label;

    @JsonProperty
    private List<PlateWellId> selects;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getLabel() {
        return label;
    }

    public List<PlateWellId> getSelects() {
        return selects;
    }
}
