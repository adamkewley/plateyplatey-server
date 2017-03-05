package com.plateyplatey.server;

import com.plateyplatey.server.configuration.AppConfiguration;
import com.plateyplatey.server.dao.*;
import com.plateyplatey.server.resources.ConfigurationsResource;
import com.plateyplatey.server.resources.DocumentsResource;
import com.plateyplatey.server.resources.PlatesResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URL;

public final class App extends Application<AppConfiguration>
{
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(final Bootstrap<AppConfiguration> bootstrap) {

    }

    @Override
    public void run(AppConfiguration appConfiguration, Environment environment) throws Exception {

        logger.info("Registering jersey HTTP API");

        final URL configurationsDirURL = Helpers.getResourceURL("configurations/");
        final URI configurationsDirURI = configurationsDirURL.toURI();
        final ConfigurationDAO configurationDAO = new SimpleFilesystemConfigurationDAO(configurationsDirURI);
        final ConfigurationsResource configurationsResource = new ConfigurationsResource(configurationDAO);
        environment.jersey().register(configurationsResource);

        final URL documentsDirURL = Helpers.getResourceURL("documents/");
        final URI documentsDirURI = documentsDirURL.toURI();
        final DocumentDAO documentDAO = new SimpleFilesystemDocumentDAO(documentsDirURI);
        final DocumentsResource documentsResource = new DocumentsResource(documentDAO);
        environment.jersey().register(documentsResource);

        final URL platesDirURL = Helpers.getResourceURL("plates/");
        final URI platesDirURI = platesDirURL.toURI();
        final PlateDAO plateDAO = new SimpleFilesystemPlateDAO(platesDirURI);
        final PlatesResource platesResource = new PlatesResource(plateDAO);
        environment.jersey().register(platesResource);

        logger.info("HTTP API registered");
    }
}
