package com.ryazangrove.anomaly_finder_backend.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ryazangrove.anomaly_finder_backend.models.ImageInfo;
import com.ryazangrove.anomaly_finder_backend.repository.ImageInfoRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

    @Autowired
    private ImageInfoRepository imageInfoRepository;

    @PostConstruct
    public void init() {
		//initialize two initial images
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
    }
}
