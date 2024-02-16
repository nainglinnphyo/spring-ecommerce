package com.paint.painting.repository;

import com.paint.painting.entity.Order;
import com.paint.painting.entity.Paint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}