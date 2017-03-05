package com.plateyplatey.server.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;

import static com.plateyplatey.server.TestHelpers.getAllFilesInResourceDir;
import static io.dropwizard.testing.FixtureHelpers.fixture;

public final class PlateTest {

    private static final ObjectMapper JSON_MAPPER =
            new ObjectMapper();

    @Test
    public void testCanDeserializeSimplePlateFromJson() throws IOException {
        final String plateJson = fixture("fixtures/api/Plate/simple-plate-file.json");

        assertCanDeserializePlate(plateJson);
    }

    @Test
    public void testCanDeserializeAllExamplePlateFiles() throws URISyntaxException, IOException {
        final List<File> exampleFiles = getAllFilesInResourceDir("fixtures/api/Plate/example-plate-files");

        for (File exampleFile : exampleFiles) {
            final String plateJson = FileUtils.readFileToString(exampleFile, Charset.defaultCharset());

            assertCanDeserializePlate(plateJson);
        }
    }

    private static void assertCanDeserializePlate(String plateJson) throws IOException {
        // Will throw if deserialization is not possible
        JSON_MAPPER.readValue(plateJson, Plate.class);
    }
}