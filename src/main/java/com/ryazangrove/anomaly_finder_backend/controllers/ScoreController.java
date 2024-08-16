package com.ryazangrove.anomaly_finder_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ryazangrove.anomaly_finder_backend.models.Score;
import com.ryazangrove.anomaly_finder_backend.repository.ScoreRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreRepository scoreRepository;

    @GetMapping
    public ResponseEntity<List<Score>> getScores() {
        var score = scoreRepository.findAll();
        return ResponseEntity.ok(score);
    }

    @PostMapping
    public ResponseEntity<Void> postScore(@RequestBody Score score) {
        scoreRepository.save(score);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getScore(@PathVariable Long id) {
        Optional<Score> score = scoreRepository.findById(id);

        if (score.isPresent()) {
            return ResponseEntity.ok(score.get());
        } else {
            // Return a custom error message with a 404 status
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Score not found with id: " + id);
        }
    }
}
