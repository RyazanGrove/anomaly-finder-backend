package com.ryazangrove.anomaly_finder_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryazangrove.anomaly_finder_backend.models.ImageInfo;

public interface ImageInfoRepository extends JpaRepository<ImageInfo, Long> {
}
