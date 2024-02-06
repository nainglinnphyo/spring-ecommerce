package com.painting.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.painting.app.entity.Painting;
import com.painting.app.repository.PaintingRepository;

@Service
public class PaintingServiceImpl implements PaintingService {
     @Autowired
     private PaintingRepository paintingRepository;

     @Override
     public List<Painting> fetchAllPainting(String type) {
          List<Painting> paintings;

          if ("publish".equalsIgnoreCase(type)) {
               paintings = paintingRepository.findByPublished(true);
          } else if ("unpublish".equalsIgnoreCase(type)) {
               paintings = paintingRepository.findByPublished(false);
          } else {
               paintings = paintingRepository.findAll();
          }

          return paintings;
     }

}
