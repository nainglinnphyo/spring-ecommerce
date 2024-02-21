package com.paint.painting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paint.painting.entity.Category;
import com.paint.painting.repository.CategoryRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

     @Autowired
     private CategoryRepository categoryRepository;

     public List<Category> getAllCategories() {
          return categoryRepository.findAll();
     }

     public Optional<Category> getCategoryById(Long id) {
          return categoryRepository.findById(id);
     }

     public Category createCategory(Category category) {
          category.setCreatedAt(new Date());
          System.out.println(category);
          return categoryRepository.save(category);
     }

     public Category updateCategory(Long id, Category updatedCategory) {
          return categoryRepository.findById(id)
                    .map(category -> {
                         category.setName(updatedCategory.getName());
                         // Update other fields as needed
                         return categoryRepository.save(category);
                    })
                    .orElse(null);
     }

     public void deleteCategory(Long id) {
          categoryRepository.deleteById(id);
     }
}
