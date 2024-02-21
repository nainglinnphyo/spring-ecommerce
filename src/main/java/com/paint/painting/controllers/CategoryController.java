package com.paint.painting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paint.painting.entity.Artist;
import com.paint.painting.entity.Category;
import com.paint.painting.service.CategoryService;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

     @Autowired
     private CategoryService categoryService;

     @GetMapping
     public List<Category> getAllCategories() {
          List<Category> categories = categoryService.getAllCategories();
          return categories.stream()
                    .map(category -> {
                         Category categoryDto = new Category();
                         categoryDto.setId(category.getId());
                         categoryDto.setName(category.getName());
                         return categoryDto;
                    }).collect(Collectors.toList());
     }

     @GetMapping("/{id}")
     public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
          return categoryService.getCategoryById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
     }

     @PostMapping
     public ResponseEntity<Category> createCategory(@RequestBody Category category) {
          Category createdCategory = categoryService.createCategory(category);
          URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdCategory.getId())
                    .toUri();
          return ResponseEntity.created(location).body(createdCategory);
     }

     @PutMapping("/{id}")
     public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
          Category updated = categoryService.updateCategory(id, updatedCategory);
          return updated != null
                    ? ResponseEntity.ok(updated)
                    : ResponseEntity.notFound().build();
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
          categoryService.deleteCategory(id);
          return ResponseEntity.noContent().build();
     }
}
