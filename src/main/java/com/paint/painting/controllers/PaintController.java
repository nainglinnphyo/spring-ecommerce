package com.paint.painting.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paint.painting.entity.Artist;
import com.paint.painting.entity.Category;
import com.paint.painting.entity.Paint;
import com.paint.painting.service.PaintService;

import org.hibernate.boot.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/paints")
public class PaintController {
    private static final String UPLOAD_DIR = "uploads";

    @Autowired
    private PaintService paintService;

    @GetMapping
    public List<Paint> getPaintWithArtist(@RequestParam(required = false, defaultValue = "") String search) {
        List<Paint> paints;

        if (!search.isEmpty()) {
            paints = paintService.findAllByTitle(search);
        } else {
            paints = paintService.findAll();
        }

        return paints.stream()
                .map(paint -> {
                    Paint paintDTO = new Paint();
                    paintDTO.setId(paint.getId());
                    paintDTO.setTitle(paint.getTitle());
                    paintDTO.setDescription(paint.getDescription());
                    paintDTO.setPrice(paint.getPrice());
                    paintDTO.setCreatedAt(paint.getCreatedAt());
                    paintDTO.setUpdatedAt(paint.getUpdatedAt());
                    paintDTO.setImagePath(paint.getImagePath());
                    Artist existArtist = paint.getArtist();
                    Category existCategory = paint.getCategory();
                    Artist artist = new Artist();
                    Category category = new Category();
                    category.setId(existCategory.getId());
                    category.setName(existCategory.getName());
                    artist.setId(existArtist.getId());
                    artist.setName(existArtist.getName());
                    paintDTO.setArtist(artist);
                    paintDTO.setCategory(category);
                    return paintDTO;
                }).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paint> getPaintById(@PathVariable Long id) {
        Paint paint = paintService.getPaintById(id);
        Paint paintDTO = new Paint();
        paintDTO.setId(paint.getId());
        paintDTO.setTitle(paint.getTitle());
        paintDTO.setDescription(paint.getDescription());
        paintDTO.setPrice(paint.getPrice());
        paintDTO.setCreatedAt(paint.getCreatedAt());
        paintDTO.setUpdatedAt(paint.getUpdatedAt());
        Artist existArtist = paint.getArtist();
        Artist artist = new Artist();
        artist.setId(existArtist.getId());
        artist.setName(existArtist.getName());
        paintDTO.setArtist(artist);

        // return paintDTO;
        return ResponseEntity.ok(paintDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaint(@PathVariable Long id) {
        paintService.deletePaint(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Paint> createPaint(@RequestParam("file") MultipartFile file,
            @RequestPart("metadata") String metadataJson) {
        Paint paint = null;
        try {
            paint = new ObjectMapper().readValue(metadataJson, Paint.class);
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/upload/")
                .path(fileName)
                .toUriString();
        paint.setImagePath(fileDownloadUri);
        Paint createdPaint = paintService.createPaint(paint);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPaint.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdPaint);
    }

    @PostMapping("upload")
    public ResponseEntity<String> handleImageUpload(@RequestParam("image") MultipartFile image) {
        try {
            // Get the path where the image will be saved
            String uploadPath = Paths.get(".").toAbsolutePath().normalize().toString() + File.separator + UPLOAD_DIR;

            // Create the upload directory if it doesn't exist
            File dir = new File(uploadPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Generate a unique filename for the image
            String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();

            // Save the image to the server
            Path filePath = Paths.get(uploadPath, fileName);
            image.transferTo(filePath.toFile());

            // Build the local URL for the uploaded image
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/upload/")
                    .path(fileName)
                    .toUriString();

            return ResponseEntity.ok("Image uploaded successfully. Local URL: " + fileDownloadUri);
        } catch (IOException ex) {
            return ResponseEntity.status(500).body("Internal Server Error: " + ex.getMessage());
        }
    }

}
