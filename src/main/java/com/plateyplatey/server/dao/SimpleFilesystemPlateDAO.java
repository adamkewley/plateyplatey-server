package com.plateyplatey.server.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.plateyplatey.server.Helpers;
import com.plateyplatey.server.api.Document;
import com.plateyplatey.server.api.Plate;
import com.plateyplatey.server.api.PlateId;
import com.plateyplatey.server.api.PlateSummary;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Uses the filesystem as a persistence store for plates.
 */
public final class SimpleFilesystemPlateDAO implements PlateDAO {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    private final File platesDir;

    public SimpleFilesystemPlateDAO(URI platesDirPath) throws FileNotFoundException {
        Objects.requireNonNull(platesDirPath);

        this.platesDir = new File(platesDirPath);

        if (!this.platesDir.exists())
            throw new FileNotFoundException(this.platesDir.toString() + ": not found");
    }

    @Override
    public Optional<Plate> getPlateById(PlateId id) {
        final File plateFile = new File(this.platesDir, id.toString());

        if (plateFile.exists()) {
            try {
                final String plateTest = FileUtils.readFileToString(plateFile, Charset.defaultCharset());
                final Plate plate = JSON_MAPPER.readValue(plateTest, Plate.class);
                return Optional.of(plate);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else return Optional.empty();
    }

    @Override
    public List<PlateSummary> getSummaryOfAllPlates() {

        final List<PlateSummary> ret = new ArrayList<>();

        for (File plateFile : this.platesDir.listFiles()) {
            try {
                final PlateId plateId = new PlateId(plateFile.getName());
                final String plateJson = FileUtils.readFileToString(plateFile, Charset.defaultCharset());
                final Plate plate = JSON_MAPPER.readValue(plateJson, Plate.class);
                final PlateSummary plateSummary = Helpers.summarize(plateId, plate);

                ret.add(plateSummary);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        return ret;
    }
}
