package com.ryazangrove.anomaly_finder_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryazangrove.anomaly_finder_backend.exceptions.ImageInfoNotFoundException;
import com.ryazangrove.anomaly_finder_backend.models.ImageInfo;
import com.ryazangrove.anomaly_finder_backend.repository.ImageInfoRepository;

@Service
public class ImageInfoService {
    
    @Autowired
    private ImageInfoRepository imageInfoRepository;

    public void saveImageInfo(ImageInfo info) {
        imageInfoRepository.save(info);
    }

    public ImageInfo getImageInfo(Long id) throws ImageInfoNotFoundException {
        return imageInfoRepository.findById(id).orElseThrow(() -> new ImageInfoNotFoundException(id));
    }
}
