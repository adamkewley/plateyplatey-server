package com.plateyplatey.server.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plateyplatey.server.api.Document;
import com.plateyplatey.server.api.DocumentId;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Optional;

/**
 * Uses the filesystem as a persistence store for documents.
 */
public final class SimpleFilesystemDocumentDAO implements DocumentDAO {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    private final File documentsDir;

    public SimpleFilesystemDocumentDAO(URI documentsDirPath) throws FileNotFoundException {

        Objects.requireNonNull(documentsDirPath);

        this.documentsDir = new File(documentsDirPath);

        if (!this.documentsDir.exists()) {
            throw new FileNotFoundException(this.documentsDir.toString() + " does not exist");
        }
    }

    @Override
    public Optional<Document> getDocumentById(DocumentId id) {

        final File documentFile = new File(this.documentsDir, id.toString());

        if (documentFile.exists()) {
            try {
                final String documentText = FileUtils.readFileToString(documentFile, Charset.defaultCharset());
                final Document document = JSON_MAPPER.readValue(documentText, Document.class);
                return Optional.of(document);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else return Optional.empty();
    }
}
