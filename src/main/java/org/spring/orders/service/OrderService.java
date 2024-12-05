package org.spring.orders.service;

import org.spring.orders.model.Order;
import org.spring.orders.model.StoreUser;
import org.spring.orders.repository.OrderRepository;
import org.spring.orders.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public Order createOrder(long userId, Order order) {
        StoreUser user = userRepository.findById(userId).get();
        order.setUser(user);
        return orderRepository.save(order);
    }

    public ResponseEntity<Order> deleteOrder(long id) {
        if(orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
