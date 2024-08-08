package com.ryazangrove.anomaly_finder_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ryazangrove.anomaly_finder_backend.models.Score;
import com.ryazangrove.anomaly_finder_backend.repository.ScoreRepository;

import java.util.List;

@RestController
public class ScoreController {

    @Autowired
    private ScoreRepository scoreRepository;

    @GetMapping("/scores")
    public List<Score> getScores() {
        return scoreRepository.findAll();
    }

    @PostMapping("/score")
    public Score postScore(@RequestBody Score score) {
        return scoreRepository.save(score);
    }
}
