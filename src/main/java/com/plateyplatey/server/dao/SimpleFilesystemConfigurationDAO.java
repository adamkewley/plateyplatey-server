package com.plateyplatey.server.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plateyplatey.server.api.Configuration;
import com.plateyplatey.server.api.ConfigurationId;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Optional;

public final class SimpleFilesystemConfigurationDAO implements ConfigurationDAO {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    private final File configurationsDir;

    public SimpleFilesystemConfigurationDAO(URI configurationsDirPath) throws FileNotFoundException {

        Objects.requireNonNull(configurationsDirPath);

        this.configurationsDir = new File(configurationsDirPath);

        if (!this.configurationsDir.exists())
            throw new FileNotFoundException(this.configurationsDir.toString() + ": not found");
    }

    @Override
    public Optional<Configuration> getConfigurationById(ConfigurationId id) {

        final File configurationFile = new File(this.configurationsDir, id.toString());

        if (configurationFile.exists()) {
            try {
                final String configurationText = FileUtils.readFileToString(configurationFile, Charset.defaultCharset());
                final Configuration configuration = JSON_MAPPER.readValue(configurationText, Configuration.class);

                return Optional.of(configuration);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else return Optional.empty();
    }
}
