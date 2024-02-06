package com.painting.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.painting.app.entity.Painting;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, Long> {

}