package com.ryazangrove.anomaly_finder_backend.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryazangrove.anomaly_finder_backend.models.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}
