package com.plateyplatey.server.dao;

import com.plateyplatey.server.api.Plate;
import com.plateyplatey.server.api.PlateId;
import com.plateyplatey.server.api.PlateSummary;

import java.util.List;
import java.util.Optional;

public interface PlateDAO {

    Optional<Plate> getPlateById(PlateId id);
    List<PlateSummary> getSummaryOfAllPlates();
}
