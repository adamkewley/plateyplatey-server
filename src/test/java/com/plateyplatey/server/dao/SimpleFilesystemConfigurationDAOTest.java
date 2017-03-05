package com.plateyplatey.server.dao;

import com.plateyplatey.server.TestHelpers;
import com.plateyplatey.server.api.Configuration;
import com.plateyplatey.server.api.ConfigurationId;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public final class SimpleFilesystemConfigurationDAOTest {

    @Test(expected = NullPointerException.class)
    public void testCtorThrowsIfDirNameIsNull() throws FileNotFoundException {
        // Should throw
        new SimpleFilesystemConfigurationDAO(null);
    }

    @Test(expected = IOException.class)
    public void testCtorThrowsIfDirDoesNotExist() throws URISyntaxException, FileNotFoundException {
        final URI pathThatDoesNotExist = new URI("file:///path/that/does/not/exist");

        new SimpleFilesystemConfigurationDAO(pathThatDoesNotExist);
    }

    @Test
    public void testGetConfigurationDeserializesADocumentNamedByIDFromTheFilesystem() throws URISyntaxException, FileNotFoundException {
        final URI pathToConfiguration =
                TestHelpers.getResourceURL("fixtures/dao/SimpleFilesystemConfigurationDAO/example-dir/")
                        .toURI();

        final SimpleFilesystemConfigurationDAO dao = new SimpleFilesystemConfigurationDAO(pathToConfiguration);

        final Optional<Configuration> maybeReturnedConfiguration =
                dao.getConfigurationById(new ConfigurationId("example"));

        assertThat(maybeReturnedConfiguration.isPresent()).isTrue();

        final Configuration returnedConfiguration = maybeReturnedConfiguration.get();

        // TODO: Check vs the config on the filesystem
    }
}