package com.paint.painting.controllers;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/upload")
public class FileController {
     private static final String UPLOAD_DIR = "uploads";
     // ... (previous code)

     @GetMapping("/{fileName}")
     public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
          Path filePath = Paths.get(".").toAbsolutePath().normalize().resolve(UPLOAD_DIR).resolve(fileName);
          Resource resource = new FileSystemResource(filePath);

          return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Adjust the MediaType based on your file type
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

     }
}
