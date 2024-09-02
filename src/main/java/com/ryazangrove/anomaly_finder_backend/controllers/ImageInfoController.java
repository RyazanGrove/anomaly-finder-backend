package com.ryazangrove.anomaly_finder_backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryazangrove.anomaly_finder_backend.exceptions.ImageInfoNotFoundException;
import com.ryazangrove.anomaly_finder_backend.exceptions.NotImplementedException;
import com.ryazangrove.anomaly_finder_backend.models.ImageInfo;
import com.ryazangrove.anomaly_finder_backend.services.ImageInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/image-infos")
public class ImageInfoController {

    @Autowired
    private ImageInfoService imageInfoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(imageInfoService.getImageInfo(id));
        } catch (ImageInfoNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/generate")
    public ResponseEntity<List<ImageInfo>> getGeneratedImageInfos() {
        return ResponseEntity.ok(imageInfoService.getRandomImageInfos());
    }

    @GetMapping()
    public ResponseEntity<?> getImageInfos() {
        throw new NotImplementedException("This method is not implemented yet.");
    }

    @PostMapping()
    public ResponseEntity<?> postImageInfo() {
        throw new NotImplementedException("This method is not implemented yet.");
    }
}
