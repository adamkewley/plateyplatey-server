package com.plateyplatey.server;

import com.plateyplatey.server.api.Plate;
import com.plateyplatey.server.api.PlateId;
import com.plateyplatey.server.api.PlateSummary;

import java.net.URL;

public class Helpers {

    public static PlateSummary summarize(PlateId plateId, Plate plate) {
        return new PlateSummary(plateId, plate.getName());
    }

    public static URL getResourceURL(String path) {
        return Thread.currentThread().getContextClassLoader().getResource(path);
    }
}
