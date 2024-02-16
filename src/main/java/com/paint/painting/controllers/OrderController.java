package com.paint.painting.controllers;

import com.paint.painting.entity.*;
import com.paint.painting.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createArtist(@RequestBody Order orderDTO){
        Order order = convertToEntity(orderDTO);
        Order createdOrder =  orderService.createOrder(order);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdOrder.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdOrder);
    }

    @GetMapping("/admin")
    public List<Order> getAllOrder(){
//        System.out.println(orderService.getAll());
        List<Order> orders =  orderService.getAll();
        return orders.stream()
                .map(order -> {
                    Order orderDto = new Order();
                    orderDto.setId(order.getId());
                    orderDto.setTotalPrice(order.getTotalPrice());
                    User existUser = order.getUser();
                    User user = new User();
                    user.setId(existUser.getId());
                    user.setPhone(existUser.getPhone());
                    user.setEmail(existUser.getEmail());
                    orderDto.setUser(user);
//                    OrderItem ex
//                    orderDto.setUser(order.getUser());
//                    orderDto.setOrderItems(order.getOrderItems());
                    return orderDto;
                }).collect(Collectors.toList());
    }

    private Order convertToEntity(Order orderDto) {
        Order order = new Order();
        User user = new User();
        user.setId(orderDto.getUser().getId());
        order.setUser(user);
        order.setTotalPrice(orderDto.getTotalPrice());

        List<OrderItem> orderItems = orderDto.getOrderItems().stream()
                .map(orderItemDTO -> convertToEntity(orderItemDTO, order))
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);
        return order;
    }


    private OrderItem convertToEntity(OrderItem orderItemDTO,Order order) {
        OrderItem orderItem = new OrderItem();
        Paint paint = new Paint();
        paint.setId(orderItemDTO.getPaint().getId());
        orderItem.setPaint(paint);
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setOrder(order);
        return orderItem;
    }

}
