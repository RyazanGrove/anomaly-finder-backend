package com.ryazangrove.anomaly_finder_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.ryazangrove.anomaly_finder_backend.exceptions.ScoreNotFoundException;
import com.ryazangrove.anomaly_finder_backend.models.Score;
import com.ryazangrove.anomaly_finder_backend.services.ScoreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @Operation(responses = {
        @ApiResponse(responseCode = "200", description = "Returns list of all scores.",
            content = @Content(examples = {
                @ExampleObject(
                    value = "[{\"id\": 1, \"score\": 10000, \"nickname\": \"John Doe\"}," + //
                    "{\"id\": 2, \"score\": 195000, \"nickname\": \"Jane Doe\"}]")
                }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping
    public ResponseEntity<List<Score>> get() {
        return ResponseEntity.ok(scoreService.getScores());
    }

    @Operation(responses = {
        @ApiResponse(responseCode = "200", description = "Returns empty JSON object.",
            content = @Content(examples = {
                @ExampleObject(
                    value = "{}")
                }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @PostMapping
    public ResponseEntity<String> post(@RequestBody Score score) {
        scoreService.saveScore(score);
        return ResponseEntity.ok("{}");
    }

    @Operation(responses = {
        @ApiResponse(responseCode = "200", description = "Returns score object with provided id.",
            content = @Content(examples = {
                @ExampleObject(
                    value = "{\"id\": 1, \"score\": 10001, \"nickname\": \"John Doe\"}")
                }, mediaType = MediaType.APPLICATION_JSON_VALUE)
        ),
        @ApiResponse(responseCode = "404", description = "Could not find score with provided id. Returns error message.",
            content = @Content(examples = {
                @ExampleObject(
                    value = "{\"error\": \"Could not find score with id 10\"}")
                }, mediaType = MediaType.APPLICATION_JSON_VALUE)
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(scoreService.getScore(id));
        } catch (ScoreNotFoundException ex) {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
    }
}
