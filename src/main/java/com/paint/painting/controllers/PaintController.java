package com.paint.painting.controllers;

import java.util.List;
import java.util.stream.Collectors;

import com.paint.painting.entity.Artist;
import com.paint.painting.entity.Paint;
import com.paint.painting.service.PaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/paints")
public class PaintController {
    @Autowired
    private PaintService paintService;

    @GetMapping
    public List<Paint> getPaintWithArtist() {
        List<Paint> paints = paintService.findAll();
        return paints.stream()
                .map(paint -> {
                    Paint paintDTO = new Paint();
                    paintDTO.setId(paint.getId());
                    paintDTO.setTitle(paint.getTitle());
                    paintDTO.setDescription(paint.getDescription());
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

}
