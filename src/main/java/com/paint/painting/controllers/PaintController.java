package com.paint.painting.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.paint.painting.entity.Artist;
import com.paint.painting.entity.Paint;
import com.paint.painting.service.PaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/api/paints")
public class PaintController {
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
                    Artist existArtist = paint.getArtist();
                    Artist artist = new Artist();
                    artist.setId(existArtist.getId());
                    artist.setName(existArtist.getName());
                    paintDTO.setArtist(artist);

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

//        return paintDTO;
        return ResponseEntity.ok(paintDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaint(@PathVariable Long id) {
        paintService.deletePaint(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<Paint> createPaint(@RequestBody Paint paint){
        Paint createdPaint = paintService.createPaint(paint);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPaint.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdPaint);
    }


}
