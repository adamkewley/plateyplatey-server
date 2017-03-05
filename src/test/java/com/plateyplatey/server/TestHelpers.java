package com.plateyplatey.server;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class TestHelpers {
    public static List<File> getAllFilesInResourceDir(String resourceDirPath) {
        try {
            final URL exampleFilesDirURL = getResourceURL(resourceDirPath);
            final File exampleFilesDir = new File(exampleFilesDirURL.toURI());

            return Arrays.asList(exampleFilesDir.listFiles()).stream().filter(file -> file.isFile()).collect(Collectors.toList());
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static URL getResourceURL(String path) {
        return Thread.currentThread().getContextClassLoader().getResource(path);
    }
}
