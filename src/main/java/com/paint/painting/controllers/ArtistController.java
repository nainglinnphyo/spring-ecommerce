package com.paint.painting.controllers;

import com.paint.painting.entity.Artist;
import com.paint.painting.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping
    public List<Artist> getArtists(@RequestParam(required = false, defaultValue = "") String search) {
        if(!search.isEmpty()){
            return artistService.findAllByName(search);
        }
        return artistService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        Artist artist = artistService.getArtistById(id);
        return ResponseEntity.ok(artist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Artist> createArtist(@RequestBody Artist artist){
        Artist createdArtist = artistService.createArtist(artist);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdArtist.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdArtist);
    }

}
