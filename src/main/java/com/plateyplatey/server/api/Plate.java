package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.List;

public final class Plate {

    @JsonProperty
    @NotNull
    private String name;

    @JsonProperty
    @NotNull
    private int gridWidth;

    @JsonProperty
    @NotNull
    private int gridHeight;

    @JsonProperty
    @Nullable
    private List<PlateWellSelector> selectors;

    @JsonProperty
    @NotNull
    private List<PlateWell> wells;

    @JsonProperty
    @Nullable
    private int wellRadius;

    @JsonProperty
    @Nullable
    @NotEmpty
    private List<PlateArrangement> arrangements;

    public String getName() {
        return name;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public List<PlateWellSelector> getSelectors() {
        return selectors;
    }

    public List<PlateWell> getWells() {
        return wells;
    }

    @Nullable
    public int getWellRadius() {
        return wellRadius;
    }

    @Nullable
    public List<PlateArrangement> getArrangements() {
        return arrangements;
    }
}
