package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Map;

public final class DocumentSheet {

    @JsonProperty
    @NotNull
    private PlateId plateTemplate;

    @JsonProperty
    @NotNull
    private TableSchemaId tableSchema;

    @JsonProperty
    @NotNull
    private Map<PlateWellId, Map<ColumnId, ColumnValue>> data;

    public PlateId getPlateTemplate() {
        return plateTemplate;
    }

    public TableSchemaId getTableSchema() {
        return tableSchema;
    }

    public Map<PlateWellId, Map<ColumnId, ColumnValue>> getData() {
        return data;
    }
}
