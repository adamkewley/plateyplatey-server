package com.plateyplatey.server.resources;

import com.plateyplatey.server.api.Configuration;
import com.plateyplatey.server.api.ConfigurationId;
import com.plateyplatey.server.dao.ConfigurationDAO;

import javax.ws.rs.*;
import java.util.Objects;
import java.util.Optional;

@Path("/configurations")
public final class ConfigurationsResource {

    private final ConfigurationDAO dao;

    public ConfigurationsResource(ConfigurationDAO dao) {
        Objects.requireNonNull(dao);

        this.dao = dao;
    }

    @GET
    @Path("{configuration-id}")
    @Produces("application/json")
    public Configuration getConfigurationById(
            @PathParam("configuration-id") ConfigurationId configurationId) {

        final Optional<Configuration> maybeConfiguration =
                this.dao.getConfigurationById(configurationId);

        if (maybeConfiguration.isPresent()) {
            return maybeConfiguration.get();
        } else throw new WebApplicationException(404);
    }
}
