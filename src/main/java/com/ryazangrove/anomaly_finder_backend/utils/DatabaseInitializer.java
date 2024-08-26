package com.ryazangrove.anomaly_finder_backend.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryazangrove.anomaly_finder_backend.models.ImageInfo;
import com.ryazangrove.anomaly_finder_backend.models.Score;
import com.ryazangrove.anomaly_finder_backend.models.ImageInfo.TargetArea;
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
		TargetArea target1 = TargetArea.builder()
			.xMin(579).xMax(635)
			.yMin(427).yMax(560)
			.build();
        ImageInfo img1 = ImageInfo.builder()
			.fileName("1")
			.type("jpg")
			.imageWidth(2208)
			.imageHeight(1400)
			.target(target1)
			.build();
		TargetArea target2 = TargetArea.builder()
			.xMin(161).xMax(145)
			.yMin(197).yMax(196)
			.build();
		ImageInfo img2 = ImageInfo.builder()
			.fileName("2")
			.type("png")
			.imageWidth(1024)
			.imageHeight(768)
			.target(target2)
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
