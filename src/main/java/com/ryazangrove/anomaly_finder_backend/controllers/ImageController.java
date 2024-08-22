package com.ryazangrove.anomaly_finder_backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;

import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/image")
public class ImageController {

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
}
