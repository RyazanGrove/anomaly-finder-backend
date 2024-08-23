package com.ryazangrove.anomaly_finder_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryazangrove.anomaly_finder_backend.models.ImageInfo;

public interface ImageInfoRepository extends JpaRepository<ImageInfo, Long> {
    Optional<ImageInfo> findByFileName(String fileName);
}
