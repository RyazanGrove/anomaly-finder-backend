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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageInfoService imageInfoService;

    @Operation(responses = {
        @ApiResponse(responseCode = "200", description = "Returns found image in the response body by provided image file name.",
            content = @Content(mediaType = MediaType.IMAGE_JPEG_VALUE)),
        @ApiResponse(responseCode = "404", description = "Could not find image or image info with provided id. Returns error message.",
            content = @Content(examples = {
                @ExampleObject(
                    value = "{\"error\": \"Could not find metadata for image with id 10\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
    })
    @GetMapping("/{imageFileName}")
    public ResponseEntity<?> serveImageHandler(@PathVariable String imageFileName, HttpServletResponse response) throws IOException {
        try { 
            InputStream image = imageInfoService.loadImage(imageFileName);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE); 
            StreamUtils.copy(image,response.getOutputStream());  
            return new ResponseEntity(HttpStatus.OK);
        } catch (ImageInfoNotFoundException | ImageNotFoundException ex) {
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("error", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMap);
        }
    }
    
    @GetMapping()
    public ResponseEntity<?> getImages() {
        throw new NotImplementedException("This method is not implemented yet.");
    }

    @PostMapping()
    public ResponseEntity<?> postImage() {
        throw new NotImplementedException("This method is not implemented yet.");
    }
}
