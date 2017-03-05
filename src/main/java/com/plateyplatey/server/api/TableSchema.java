package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public final class TableSchema {
    @JsonProperty
    @NotNull
    private List<ColumnSchema> columns;

    public List<ColumnSchema> getColumns() {
        return columns;
    }
}
