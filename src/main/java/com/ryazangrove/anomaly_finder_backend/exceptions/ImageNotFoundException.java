package com.ryazangrove.anomaly_finder_backend.exceptions;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String fileName) {
        super("Could not find image with file name " + fileName);
    }
}
