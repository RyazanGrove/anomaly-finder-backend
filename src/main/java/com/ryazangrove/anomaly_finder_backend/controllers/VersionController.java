package com.ryazangrove.anomaly_finder_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @GetMapping("/version")
    public ResponseEntity<String> version() {
        return ResponseEntity.ok("0.1.1");
    }
}
