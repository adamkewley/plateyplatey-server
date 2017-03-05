package com.plateyplatey.server.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plateyplatey.server.TestHelpers;
import com.plateyplatey.server.api.Plate;
import com.plateyplatey.server.api.PlateId;
import com.plateyplatey.server.api.PlateSummary;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public final class SimpleFilesystemPlateDAOTest {

    private static ObjectMapper JSON_MAPPER = new ObjectMapper();

    @Test(expected = NullPointerException.class)
    public void testCtorThrowsIfPlatesDirPathIsNull() throws FileNotFoundException {
        // Should throw
        new SimpleFilesystemPlateDAO(null);
    }

    @Test(expected = IOException.class)
    public void testCtorThrowsIfPlatesDirDoesNotExist() throws URISyntaxException, FileNotFoundException {
        final URI pathThatDoesNotExist = new URI("file:///path/that/does/not/exist");

        // Should throw
        new SimpleFilesystemPlateDAO(pathThatDoesNotExist);
    }

    @Test
    public void testGetPlateByIdDeserializesAPlateNamedByTheIDFromTheFilesystem() throws URISyntaxException, FileNotFoundException {
        final URI pathToPlates =
                TestHelpers.getResourceURL("fixtures/dao/SimpleFilesystemPlateDAO/example")
                        .toURI();

        final SimpleFilesystemPlateDAO dao = new SimpleFilesystemPlateDAO(pathToPlates);

        final Optional<Plate> maybeReturnedPlate = dao.getPlateById(new PlateId("example"));

        assertThat(maybeReturnedPlate.isPresent()).isTrue();

        // TODO: Check vs doc on filesystem
    }

    @Test
    public void testGetPlateSummariesReturnsAListOfThePlatesInTheDirectory() throws URISyntaxException, IOException {
        final URI pathToPlates =
                TestHelpers.getResourceURL("fixtures/dao/SimpleFilesystemPlateDAO/example")
                        .toURI();

        final SimpleFilesystemPlateDAO dao = new SimpleFilesystemPlateDAO(pathToPlates);

        final List<PlateSummary> expectedSummaries =
                Arrays.asList(JSON_MAPPER.readValue(fixture("fixtures/dao/SimpleFilesystemPlateDAO/summary.json"), PlateSummary[].class));

        final List<PlateSummary> returnedSummaries = dao.getSummaryOfAllPlates();

        // They may not be in the same order

        for (PlateSummary returnedSummary : returnedSummaries) {
            assertThat(expectedSummaries.contains(returnedSummary)).isTrue();
        }
    }
}