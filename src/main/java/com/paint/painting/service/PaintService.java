package com.paint.painting.service;

import com.paint.painting.entity.Paint;
import com.paint.painting.repository.PaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaintService {
    @Autowired
    private PaintRepository paintRepository;

    public List<Paint> findAll() {
        return paintRepository.findAll();
    }
}
