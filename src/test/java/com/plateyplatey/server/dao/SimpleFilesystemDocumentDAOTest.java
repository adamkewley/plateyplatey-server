package com.plateyplatey.server.dao;

import com.plateyplatey.server.TestHelpers;
import com.plateyplatey.server.api.Document;
import com.plateyplatey.server.api.DocumentId;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public final class SimpleFilesystemDocumentDAOTest {

    @Test(expected = NullPointerException.class)
    public void testCtorThrowsIfCocumentsDirPathIsNull() throws FileNotFoundException {
        // Should throw
        new SimpleFilesystemDocumentDAO(null);
    }

    @Test(expected = IOException.class)
    public void testCtorThrowsIfDocumentsDirPathDoesNotExist() throws URISyntaxException, FileNotFoundException {
        final URI pathThatDoesNotExist = new URI("file:///path/that/does/not/exist");

        // Should throw
        new SimpleFilesystemDocumentDAO(pathThatDoesNotExist);
    }

    @Test
    public void testGetDocumentDeserializesADocumentNamedByTheIDFromTheFilesystem() throws URISyntaxException, FileNotFoundException {
        final URI pathToDocuments =
                TestHelpers.getResourceURL("fixtures/dao/SimpleFilesystemDocumentDAO/example-dir/")
                        .toURI();

        final SimpleFilesystemDocumentDAO dao = new SimpleFilesystemDocumentDAO(pathToDocuments);

        final Optional<Document> maybeReturnedDocument = dao.getDocumentById(new DocumentId("example"));

        assertThat(maybeReturnedDocument.isPresent()).isTrue();

        final Document returnedDocument = maybeReturnedDocument.get();

        // TODO: Check vs the doc on the filesystem
    }
}