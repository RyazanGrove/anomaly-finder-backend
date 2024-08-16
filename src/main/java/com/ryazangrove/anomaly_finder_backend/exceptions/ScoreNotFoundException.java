package com.ryazangrove.anomaly_finder_backend.exceptions;

public class ScoreNotFoundException extends RuntimeException {
    public ScoreNotFoundException(Long id) {
        super("Could not find score with id " + id);
    }
}
