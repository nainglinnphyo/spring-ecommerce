package com.paint.painting.service;

import com.paint.painting.entity.Artist;
import com.paint.painting.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    public List<Artist> findAll() {
        return (List<Artist>) artistRepository.findAll();
    }
    // Use repository methods as needed
}
