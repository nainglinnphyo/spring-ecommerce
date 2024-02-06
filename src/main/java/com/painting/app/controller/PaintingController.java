package com.painting.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.painting.app.entity.Painting;
import com.painting.app.service.PaintingService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/painting")
@Tag(name = "Painting", description = "Endpoints related to paintings")
public class PaintingController {
     public enum PaintingType {
          PUBLISH,
          UNPUBLISH,
          ALL
     }

     @Autowired
     private PaintingService paintingService;

     @GetMapping("")
     public List<Painting> getAllPainting(
               @Parameter(description = "Type of paintings to fetch. Options: 'PUBLISH', 'UNPUBLISH', 'ALL'. Default is 'ALL'.", schema = @Schema(allowableValues = {
                         "PUBLISH", "UNPUBLISH", "ALL" })) @RequestParam(defaultValue = "ALL") PaintingType type) {

          String typeValue = type.name();
          return paintingService.fetchAllPainting(typeValue);
     }
}
