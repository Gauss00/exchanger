package com.example.htmlproduct.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class ImageController {

    @GetMapping("/images/backdrop-874452.jpg")
    public ResponseEntity<Resource> getImage() throws IOException {
        Resource image = new ClassPathResource("static/images/backdrop-874452.jpg");
        return ResponseEntity.ok()
                .contentLength(image.contentLength())
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }
}
