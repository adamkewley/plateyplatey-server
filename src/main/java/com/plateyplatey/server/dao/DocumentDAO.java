package com.plateyplatey.server.dao;

import com.plateyplatey.server.api.Document;
import com.plateyplatey.server.api.DocumentId;

import java.util.Optional;

public interface DocumentDAO {

    Optional<Document> getDocumentById(DocumentId id);
}
