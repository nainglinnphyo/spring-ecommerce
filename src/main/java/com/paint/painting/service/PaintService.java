package com.paint.painting.service;

import com.paint.painting.entity.Artist;
import com.paint.painting.entity.Paint;
import com.paint.painting.repository.ArtistRepository;
import com.paint.painting.repository.PaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaintService {
    @Autowired
    private PaintRepository paintRepository;
    private ArtistRepository artistRepository;

    public List<Paint> findAll() {
        return paintRepository.findAll();
    }
    public List<Paint> findAllByTitle(String search) {
        return paintRepository.findByTitle(search);
    }

    public Paint getPaintById(Long id) {
        return paintRepository.findById(id).orElse(null);
    }

    public void deletePaint(Long id) {
        paintRepository.deleteById(id);
    }

    public Paint createPaint(Paint paint) {
        paint.setArtist(paint.getArtist());
        paint.setImagePath(paint.getImagePath());
        paint.setCreatedAt(new Date());
        paint.setUpdatedAt(new Date());
        return paintRepository.save(paint);
    }
}
