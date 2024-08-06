package com.ryazangrove.anomaly_finder_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryazangrove.anomaly_finder_backend.interfaces.ScoreRepository;
import com.ryazangrove.anomaly_finder_backend.models.Score;

import java.util.List;

@RestController
public class ScoreController {

    @Autowired
    private ScoreRepository scoreRepository;

    @GetMapping("/scores")
    public List<Score> getScores() {
        return scoreRepository.findAll();
    }
}
