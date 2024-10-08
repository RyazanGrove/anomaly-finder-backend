package com.ryazangrove.anomaly_finder_backend.exceptions;

public class ImageInfoNotFoundException extends RuntimeException {
    public ImageInfoNotFoundException(Long id) {
        super("Could not find metadata for image with id " + id);
    }

    public ImageInfoNotFoundException(String fileName) {
        super("Could not find metadata for image with file name " + fileName);
    }
}
