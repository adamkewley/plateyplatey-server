package com.plateyplatey.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.plateyplatey.server.api.*;

import java.io.IOException;

import static io.dropwizard.testing.FixtureHelpers.fixture;

public class Fakers {
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static Document generateDocument() {
        try {
            return JSON_MAPPER.readValue(fixture("fixtures/api/Document/example-document-files/default.json"), Document.class);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static DocumentId generateDocumentId() {
        final Faker faker = new Faker();

        return new DocumentId(faker.idNumber().valid());
    }

    public static PlateId generatePlateId() {
        final Faker faker = new Faker();

        return new PlateId(faker.idNumber().valid());
    }

    public static Plate generatePlate() {
        try {
            return JSON_MAPPER.readValue(fixture("fixtures/api/Plate/simple-plate-file.json"), Plate.class);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static PlateSummary generatePlateSummary() {
        try {
            return JSON_MAPPER.readValue(fixture("fixtures/api/PlateSummary/simple-plate-summary.json"), PlateSummary.class);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ConfigurationId generateConfigurationId() {
        final Faker faker = new Faker();

        return new ConfigurationId(faker.idNumber().valid());
    }

    public static Configuration generateConfiguration() {
        try {
            return JSON_MAPPER.readValue(fixture("fixtures/Fakers/configuration.json"), Configuration.class);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
