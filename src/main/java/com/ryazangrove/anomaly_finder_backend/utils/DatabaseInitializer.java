package com.ryazangrove.anomaly_finder_backend.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryazangrove.anomaly_finder_backend.models.ImageInfo;
import com.ryazangrove.anomaly_finder_backend.models.Score;
import com.ryazangrove.anomaly_finder_backend.repository.ImageInfoRepository;
import com.ryazangrove.anomaly_finder_backend.repository.ScoreRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

    @Autowired
    private ImageInfoRepository imageInfoRepository;

	@Autowired
    private ScoreRepository scoreRepository;

    @PostConstruct
    public void init() {
		// initialize two initial images
        ImageInfo img1 = ImageInfo.builder()
			.fileName("1")
			.type("jpg")
			.imageWidth(2208)
			.imageHeight(1400)
			.target(null)
			.build();
		ImageInfo img2 = ImageInfo.builder()
			.fileName("2")
			.type("png")
			.imageWidth(1024)
			.imageHeight(768)
			.target(null)
			.build();
        imageInfoRepository.save(img1);
        imageInfoRepository.save(img2);

		// initialize initial scores
		Score s1 = Score.builder()
			.score(1000l)
			.nickname("John Doe")
			.build();
		Score s2 = Score.builder()
			.score(19520l)
			.nickname("Jane Doe")
			.build();
		Score s3 = Score.builder()
			.score(8382l)
			.nickname("Anonymous Visitor")
			.build();
		scoreRepository.save(s1);
		scoreRepository.save(s2);
		scoreRepository.save(s3);
    }
}
