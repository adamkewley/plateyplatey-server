package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

public final class DocumentWorkbook {

    @JsonProperty
    @NotNull
    @NotEmpty
    private List<SheetId> sheets;

    @JsonProperty
    @NotNull
    private SheetId focusedSheet;

    public List<SheetId> getSheets() {
        return sheets;
    }

    public SheetId getFocusedSheet() {
        return focusedSheet;
    }
}
