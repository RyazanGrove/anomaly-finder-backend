package com.ryazangrove.anomaly_finder_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ryazangrove.anomaly_finder_backend.models.ImageInfo;

public interface ImageInfoRepository extends JpaRepository<ImageInfo, Long> {
    Optional<ImageInfo> findByFileName(String fileName);

    @Query(value = "SELECT * FROM IMAGE_INFO ORDER BY RAND() LIMIT ?1", nativeQuery = true)
    List<ImageInfo> getRandomImageInfos(int num);
}
