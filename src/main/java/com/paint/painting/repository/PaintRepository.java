package com.paint.painting.repository;

import com.paint.painting.entity.Paint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaintRepository extends JpaRepository<Paint, Long> {
}
