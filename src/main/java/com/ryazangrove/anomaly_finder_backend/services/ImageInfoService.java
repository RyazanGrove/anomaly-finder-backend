package com.ryazangrove.anomaly_finder_backend.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryazangrove.anomaly_finder_backend.exceptions.ImageInfoNotFoundException;
import com.ryazangrove.anomaly_finder_backend.exceptions.ImageNotFoundException;
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

    public InputStream loadImage(String imageFileName) throws ImageInfoNotFoundException, ImageNotFoundException{
        Optional<ImageInfo> imageInfo = getImageInfoByImageFileName(imageFileName);
        if (imageInfo.isEmpty()) throw new ImageInfoNotFoundException(imageFileName);
        String imageType = imageInfo.get().getType();
        try{
            InputStream fileInputStream = new FileInputStream("src/main/resources/images/" + imageFileName + "." + imageType);
            return fileInputStream;
        } catch (FileNotFoundException ex) {
            throw new ImageNotFoundException(imageFileName);
        }
    }

    private Optional<ImageInfo> getImageInfoByImageFileName(String imageFileName){
        return imageInfoRepository.findByFileName(imageFileName);
    }
}
