package com.plateyplatey.server.resources;

import com.plateyplatey.server.api.Plate;
import com.plateyplatey.server.api.PlateId;
import com.plateyplatey.server.api.PlateSummary;
import com.plateyplatey.server.dao.PlateDAO;

import javax.ws.rs.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Path("/plates")
public final class PlatesResource {

    private final PlateDAO dao;

    public PlatesResource(PlateDAO plateDAO) {
        Objects.requireNonNull(plateDAO);

        this.dao = plateDAO;
    }

    @GET
    @Path("{plate-id}")
    @Produces("application/json")
    public Plate getPlateById(
            @PathParam("plate-id")
                    PlateId plateId) {

        final Optional<Plate> maybePlate = this.dao.getPlateById(plateId);

        if (maybePlate.isPresent()) return maybePlate.get();
        else throw new WebApplicationException(404);
    }

    @GET
    @Produces("application/json")
    public List<PlateSummary> getAllPlates() {
        return this.dao.getSummaryOfAllPlates();
    }
}
