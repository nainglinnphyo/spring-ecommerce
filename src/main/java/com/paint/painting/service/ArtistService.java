package com.paint.painting.service;

import com.paint.painting.entity.Artist;
import com.paint.painting.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    public List<Artist> findAll() {
        return (List<Artist>) artistRepository.findAll();
    }

    public List<Artist> findAllByName(String name) {
        return (List<Artist>) artistRepository.findByName(name);
    }

    public Artist getArtistById(Long id) {
        return artistRepository.findById(id).orElse(null);
    }

    public void deleteArtist(Long id) {
        artistRepository.deleteById(id);
    }

//    @Override
    public Artist createArtist(Artist artist) {
        // Set creation timestamp
        artist.setCreatedAt(new Date());
        artist.setUpdatedAt(new Date());
        return artistRepository.save(artist);
    }

}
