package com.ryazangrove.anomaly_finder_backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryazangrove.anomaly_finder_backend.exceptions.ImageInfoNotFoundException;
import com.ryazangrove.anomaly_finder_backend.services.ImageInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;

import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageInfoService imageInfoService;

    @GetMapping("/{id}")
    public void serveImageHandler(@PathVariable Long id, HttpServletResponse response){
        try { 
            InputStream fileInputStream= new FileInputStream("src\\main\\resources\\images\\1.jpg"); 
            response.setContentType(MediaType.IMAGE_JPEG_VALUE); 
            StreamUtils.copy(fileInputStream,response.getOutputStream()); 
        } catch(Exception e) { 
            e.printStackTrace(); 
            response.setStatus(404);
        } 
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(imageInfoService.getImageInfo(id));
        } catch (ImageInfoNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
