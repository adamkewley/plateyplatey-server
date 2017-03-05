package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

public final class PlateArrangement {

    @JsonProperty
    @NotNull
    private String name;

    @JsonProperty
    @NotNull
    @NotEmpty
    private List<PlateWellId> order;

    public String getName() {
        return name;
    }

    public List<PlateWellId> getOrder() {
        return order;
    }
}
