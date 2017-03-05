package com.plateyplatey.server.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static com.plateyplatey.server.TestHelpers.getAllFilesInResourceDir;

public final class DocumentTest {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    @Test
    public void testCanDeserializeAllExampleDocumentFiles() throws IOException {
        final List<File> exampleDocuments = getAllFilesInResourceDir("fixtures/api/Document/example-document-files");

        for (File exampleDocument : exampleDocuments) {
            final String documentJson = FileUtils.readFileToString(exampleDocument, Charset.defaultCharset());

            assertCanDeserializeDocument(documentJson);
        }
    }

    private static void assertCanDeserializeDocument(String documentJson) throws IOException {
        // Will throw if deserialization not possible
        JSON_MAPPER.readValue(documentJson, Document.class);
    }
}