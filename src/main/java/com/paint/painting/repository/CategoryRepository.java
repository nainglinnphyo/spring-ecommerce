package com.paint.painting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paint.painting.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // You can add custom queries if needed
}
