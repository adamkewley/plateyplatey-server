package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.Map;

public final class Document {

    @JsonProperty
    @NotNull
    private DocumentSchemaDetails fileSchema;

    @JsonProperty
    @NotNull
    private DocumentProperties properties;

    @JsonProperty
    @NotNull
    private Map<PlateId, Plate> plateLayouts;

    @JsonProperty
    @NotNull
    private Map<TableSchemaId, TableSchema> tableSchemas;

    @JsonProperty
    @NotNull
    private DocumentWorkbook workbook;

    @JsonProperty
    @NotNull
    private Map<SheetId, DocumentSheet> sheets;

    public DocumentSchemaDetails getFileSchema() {
        return fileSchema;
    }

    public DocumentProperties getProperties() {
        return properties;
    }

    public Map<PlateId, Plate> getPlateLayouts() {
        return plateLayouts;
    }

    public Map<TableSchemaId, TableSchema> getTableSchemas() {
        return tableSchemas;
    }

    public DocumentWorkbook getWorkbook() {
        return workbook;
    }

    public Map<SheetId, DocumentSheet> getSheets() {
        return sheets;
    }
}
