package com.ryazangrove.anomaly_finder_backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryazangrove.anomaly_finder_backend.exceptions.ImageInfoNotFoundException;
import com.ryazangrove.anomaly_finder_backend.exceptions.NotImplementedException;
import com.ryazangrove.anomaly_finder_backend.models.ImageInfo;
import com.ryazangrove.anomaly_finder_backend.services.ImageInfoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/image-infos")
public class ImageInfoController {

    @Autowired
    private ImageInfoService imageInfoService;

    @Operation(responses = {
        @ApiResponse(responseCode = "200", description = "Returns found image info object with provided id.",
            content = @Content(examples = {
                @ExampleObject(
                    value = "{\"id\": 100, \"fileName\": \"1\", \"type\": \"jpg\", \"imageWidth\": 2208, \"imageHeight\": 1400," + //
                    "\"target\": {\"xMin\": 100, \"xMax\": 120, \"yMin\": 60, \"yMax\": 80}}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(responseCode = "404", description = "Could not find image info with provided id. Returns error message.",
            content = @Content(examples = {
                @ExampleObject(
                    value = "{\"error\": \"Could not find metadata for image with id 10\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(imageInfoService.getImageInfo(id));
        } catch (ImageInfoNotFoundException ex) {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
    }

    @Operation(responses = {
        @ApiResponse(responseCode = "200", description = "Returns generated sequence for image info objects with random order.",
            content = @Content(examples = {
                @ExampleObject(
                    value = "[{\"id\": 100, \"fileName\": \"1\", \"type\": \"jpg\", \"imageWidth\": 2208, \"imageHeight\": 1400," + //
                    "\"target\": {\"xMin\": 100, \"xMax\": 120, \"yMin\": 60, \"yMax\": 80}}," + //
                    "{\"id\": 101, \"fileName\": \"2\", \"type\": \"png\", \"imageWidth\": 1024, \"imageHeight\": 768," + //
                    "\"target\": {\"xMin\": 170, \"xMax\": 210, \"yMin\": 160, \"yMax\": 185}}]")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping("/generate")
    public ResponseEntity<List<ImageInfo>> getGeneratedImageInfos() {
        return ResponseEntity.ok(imageInfoService.getRandomImageInfos());
    }

    @GetMapping()
    public ResponseEntity<?> getImageInfos() {
        throw new NotImplementedException("This method is not implemented yet.");
    }

    @PostMapping()
    public ResponseEntity<?> postImageInfo() {
        throw new NotImplementedException("This method is not implemented yet.");
    }
}
