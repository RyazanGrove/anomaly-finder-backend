package com.ryazangrove.anomaly_finder_backend.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
public class VersionController {

    @Operation(responses = {
        @ApiResponse(responseCode = "200", description = "Returns version of backend.",
            content = @Content(examples = {
                @ExampleObject(
                    value = "{\"version\": \"0.2.0\"}")
                }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping("/version")
    public ResponseEntity<?> version() {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("version", "0.3.1");
        return ResponseEntity.ok(responseMap);
    }
}
