package com.plateyplatey.server.resources;

import com.plateyplatey.server.Fakers;
import com.plateyplatey.server.api.Document;
import com.plateyplatey.server.dao.DocumentDAO;
import org.junit.Test;

import javax.ws.rs.WebApplicationException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public final class DocumentsResourceTest {
    @Test(expected = NullPointerException.class)
    public void testCtorThrowsIfProvidedNullDAO() {
        // Should throw
        new DocumentsResource(null);
    }

    @Test
    public void testGetPlateByIdReturnsPlateIfDAOReturnsPlate() {
        final Document expectedDocument = Fakers.generateDocument();
        final DocumentDAO documentDAOMock =
                createDocumentDAOThatAlwaysReturns(Optional.of(expectedDocument));
        final DocumentsResource resource = new DocumentsResource(documentDAOMock);

        final Document returnedDocument = resource.getDocumentById(Fakers.generateDocumentId());

        assertThat(returnedDocument).isEqualTo(expectedDocument);
    }

    private static DocumentDAO createDocumentDAOThatAlwaysReturns(Optional<Document> document) {
        final DocumentDAO documentDAOMock = mock(DocumentDAO.class);
        when(documentDAOMock.getDocumentById(any()))
                .thenReturn(document);
        return documentDAOMock;
    }

    @Test(expected = WebApplicationException.class)
    public void testGetPlateByIdThrows404WebExceptionIfDAOReturnsEmpty() {
        final DocumentDAO documentDAOMock = createDocumentDAOThatAlwaysReturns(Optional.empty());
        final DocumentsResource resource = new DocumentsResource(documentDAOMock);

        try {
            resource.getDocumentById(Fakers.generateDocumentId());
        } catch (WebApplicationException ex) {
            assertThat(ex.getResponse().getStatus()).isEqualTo(404);
            throw ex;
        }
    }
}