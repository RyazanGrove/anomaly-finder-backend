package com.ryazangrove.anomaly_finder_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryazangrove.anomaly_finder_backend.models.Score;
import com.ryazangrove.anomaly_finder_backend.repository.ScoreRepository;

@Service
public class ScoreService {
    
    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getScores() {
        return scoreRepository.findAll();
    }

    public void saveScore(Score score) {
        scoreRepository.save(score);
    }

    public Score getScore(Long id) throws Exception {
        return scoreRepository.findById(id).orElseThrow(() -> new Exception("Score not found with id: " + id));
    }
}
