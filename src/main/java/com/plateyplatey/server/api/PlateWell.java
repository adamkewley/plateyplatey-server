package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

public final class PlateWell {

    @JsonProperty
    @NotNull
    private PlateWellId id;

    @JsonProperty
    @NotNull
    private int x;

    @JsonProperty
    @NotNull
    private int y;

    @JsonProperty
    @Nullable
    private int radius;

    public PlateWellId getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Nullable
    public int getRadius() {
        return radius;
    }
}
