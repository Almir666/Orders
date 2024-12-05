package org.spring.orders.controller;

import org.spring.orders.model.Order;
import org.spring.orders.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Order> createOrder(@PathVariable long userId, @RequestBody Order order) {
        return ResponseEntity.ok(orderService.createOrder(userId,order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEntity<Order>> deleteOrder(@PathVariable long id) {
        return ResponseEntity.ok(orderService.deleteOrder(id));
    }
}
