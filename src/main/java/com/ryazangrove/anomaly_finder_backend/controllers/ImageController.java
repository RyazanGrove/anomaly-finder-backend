package com.ryazangrove.anomaly_finder_backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryazangrove.anomaly_finder_backend.exceptions.ImageInfoNotFoundException;
import com.ryazangrove.anomaly_finder_backend.exceptions.ImageNotFoundException;
import com.ryazangrove.anomaly_finder_backend.exceptions.NotImplementedException;
import com.ryazangrove.anomaly_finder_backend.services.ImageInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageInfoService imageInfoService;

    @GetMapping("/{imageFileName}")
    public ResponseEntity<?> serveImageHandler(@PathVariable String imageFileName, HttpServletResponse response) throws IOException {
        try { 
            InputStream image = imageInfoService.loadImage(imageFileName);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE); 
            StreamUtils.copy(image,response.getOutputStream());  
            return new ResponseEntity(HttpStatus.OK);
        } catch (ImageInfoNotFoundException | ImageNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
