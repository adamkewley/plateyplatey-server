package com.plateyplatey.server.resources;

import com.plateyplatey.server.Fakers;
import com.plateyplatey.server.api.Plate;
import com.plateyplatey.server.api.PlateId;
import com.plateyplatey.server.api.PlateSummary;
import com.plateyplatey.server.dao.PlateDAO;
import org.junit.Test;

import javax.ws.rs.WebApplicationException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class PlatesResourceTest {

    @Test(expected = NullPointerException.class)
    public void testCtorThrowsIfDAOIsNull() {
        // Should throw
        new PlatesResource(null);
    }

    @Test
    public void testGetPlateByIdReturnsPlateSuppliedByDAO() {
        final Plate expectedPlate = Fakers.generatePlate();
        final PlateDAO plateDAOMock =
                createPlateDAOThatAlwaysReturns(Optional.of(expectedPlate));

        final PlatesResource resource = new PlatesResource(plateDAOMock);

        final Plate returnedPlate = resource.getPlateById(Fakers.generatePlateId());

        assertThat(returnedPlate).isEqualTo(expectedPlate);
    }

    private static PlateDAO createPlateDAOThatAlwaysReturns(Optional<Plate> plate) {
        final PlateDAO plateDAO = mock(PlateDAO.class);
        when(plateDAO.getPlateById(any())).thenReturn(plate);
        return plateDAO;
    }

    @Test(expected = WebApplicationException.class)
    public void testGetPlateByIdThrows404WebExceptionIfDAOReturnsEmpty() {
        final PlateDAO plateDAOMock =
                createPlateDAOThatAlwaysReturns(Optional.empty());
        final PlatesResource resource = new PlatesResource(plateDAOMock);

        try {
            resource.getPlateById(Fakers.generatePlateId());
        } catch (WebApplicationException ex) {
            assertThat(ex.getResponse().getStatus()).isEqualTo(404);
            throw ex;
        }
    }

    @Test
    public void testGetAllPlatesReturnsPlatesReturnedByDAO() {
        final List<PlateSummary> suppliedPlateSummaries = new ArrayList<>();
        suppliedPlateSummaries.add(Fakers.generatePlateSummary());
        suppliedPlateSummaries.add(Fakers.generatePlateSummary());
        suppliedPlateSummaries.add(Fakers.generatePlateSummary());
        suppliedPlateSummaries.add(Fakers.generatePlateSummary());
        suppliedPlateSummaries.add(Fakers.generatePlateSummary());

        final PlateDAO plateDAOMock =
                createPlateDAOThatReturns(suppliedPlateSummaries);

        final PlatesResource resource = new PlatesResource(plateDAOMock);

        final List<PlateSummary> returnedPlateSummaries = resource.getAllPlates();

        assertThat(returnedPlateSummaries).isEqualTo(suppliedPlateSummaries);
    }

    private static PlateDAO createPlateDAOThatReturns(List<PlateSummary> summaries) {
        final PlateDAO plateDAO = mock(PlateDAO.class);
        when(plateDAO.getSummaryOfAllPlates()).thenReturn(summaries);
        return plateDAO;
    }
}