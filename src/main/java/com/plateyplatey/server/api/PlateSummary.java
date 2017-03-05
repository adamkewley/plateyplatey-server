package com.plateyplatey.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class PlateSummary {

    public PlateSummary(
            @JsonProperty("id") PlateId id,
            @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    @JsonProperty
    private String name;

    @JsonProperty
    private PlateId id;

    public String getName() {
        return name;
    }

    public PlateId getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlateSummary that = (PlateSummary) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
