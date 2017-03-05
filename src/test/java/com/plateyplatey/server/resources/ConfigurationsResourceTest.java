package com.plateyplatey.server.resources;

import com.plateyplatey.server.Fakers;
import com.plateyplatey.server.api.Configuration;
import com.plateyplatey.server.dao.ConfigurationDAO;
import org.junit.Test;

import javax.ws.rs.WebApplicationException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class ConfigurationsResourceTest {

    @Test(expected = NullPointerException.class)
    public void testCtorThrowsIfDAOIsNull() {
        // Should throw
        new ConfigurationsResource(null);
    }

    @Test
    public void testGetConfigurationIdReturnsConfigurationReturnedByDAO() {
        final Configuration expectedConfiguration = Fakers.generateConfiguration();
        final ConfigurationDAO dao =
                createConfigurationDAOThatAlwaysReturns(Optional.of(expectedConfiguration));

        final ConfigurationsResource resource = new ConfigurationsResource(dao);

        final Configuration returnedConfiguration = resource.getConfigurationById(Fakers.generateConfigurationId());

        assertThat(returnedConfiguration).isEqualTo(expectedConfiguration);
    }

    public static ConfigurationDAO createConfigurationDAOThatAlwaysReturns(Optional<Configuration> configuration) {
        final ConfigurationDAO dao = mock(ConfigurationDAO.class);
        when(dao.getConfigurationById(any())).thenReturn(configuration);
        return dao;
    }

    @Test(expected = WebApplicationException.class)
    public void testGetConfigurationByIdThrows404ExceptionIfNothingIsReturnedByDAO() {
        final ConfigurationDAO dao =
                createConfigurationDAOThatAlwaysReturns(Optional.empty());

        final ConfigurationsResource resource = new ConfigurationsResource(dao);

        try {
            resource.getConfigurationById(Fakers.generateConfigurationId());
        } catch (WebApplicationException ex) {
            assertThat(ex.getResponse().getStatus()).isEqualTo(404);

            throw ex;
        }
    }
}