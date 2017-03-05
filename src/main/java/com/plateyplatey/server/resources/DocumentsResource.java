package com.plateyplatey.server.resources;

import com.plateyplatey.server.api.Document;
import com.plateyplatey.server.api.DocumentId;
import com.plateyplatey.server.dao.DocumentDAO;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import java.util.Objects;
import java.util.Optional;

@Path("/documents")
public final class DocumentsResource {

    private final DocumentDAO dao;

    public DocumentsResource(DocumentDAO dao) {
        Objects.requireNonNull(dao);

        this.dao = dao;
    }

    @GET
    @Path("{document-id}")
    @Produces("application/json")
    public Document getDocumentById(
            @NotNull @PathParam("document-id") DocumentId documentId) {

        final Optional<Document> maybeDocument = this.dao.getDocumentById(documentId);

        if (maybeDocument.isPresent()) {
            return maybeDocument.get();
        } else throw new WebApplicationException(404);
    }
}
