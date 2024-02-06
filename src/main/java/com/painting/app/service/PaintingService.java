package com.painting.app.service;

import java.util.List;

import com.painting.app.entity.Painting;

public interface PaintingService {
     List<Painting> fetchAllPainting(String type);
}
