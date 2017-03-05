package com.plateyplatey.server.dao;

import com.plateyplatey.server.api.Configuration;
import com.plateyplatey.server.api.ConfigurationId;

import java.util.Optional;

public interface ConfigurationDAO {

    Optional<Configuration> getConfigurationById(ConfigurationId id);
}
