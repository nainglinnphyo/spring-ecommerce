package com.paint.painting.service;

import com.paint.painting.entity.Order;
import com.paint.painting.entity.OrderItem;
import com.paint.painting.entity.Paint;
import com.paint.painting.repository.OrderItemRepository;
import com.paint.painting.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;

    public Order createOrder(Order order) {
        order.setUser(order.getUser());
        order.setCreatedAt(new Date());
        order.setUpdatedAt(new Date());
        return orderRepository.save(order);
    }

    public List<Order> getAll (){
        List<Order> orders = orderRepository.findAll();
        System.out.println(orders);
        System.out.println("##############################");

        return orders;
    }
}
