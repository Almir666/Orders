package org.spring.orders.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spring.orders.model.Order;
import org.spring.orders.model.StoreUser;
import org.spring.orders.repository.OrderRepository;
import org.spring.orders.repository.UserRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testCreateOrder() {
        long userId = 1L;
        Order order = new Order();
        StoreUser user = new StoreUser();
        user.setId(userId);
        order.setUser(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(orderRepository.save(order)).thenAnswer(invocation -> {
            Order savedOrder = invocation.getArgument(0);
            savedOrder.setId(userId);
            return savedOrder;
        });

        Order createdOrder = orderService.createOrder(userId, order);

        assertNotNull(createdOrder);
        assertEquals(1L, createdOrder.getId());
        assertEquals(user, createdOrder.getUser());
        verify(userRepository, times(1)).findById(userId);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testDeleteOrderExists() {
        long orderId = 1L;
        when(orderRepository.existsById(orderId)).thenReturn(true);

        ResponseEntity<Order> response = orderService.deleteOrder(orderId);

        assertNotNull(response);
        assertEquals(204, response.getStatusCode().value());
        verify(orderRepository, times(1)).existsById(orderId);
        verify(orderRepository, times(1)).deleteById(orderId);

    }

    @Test
    void testDeleteOrderNotExists() {
        long orderId = 1L;
        when(orderRepository.existsById(orderId)).thenReturn(false);

        ResponseEntity<Order> response = orderService.deleteOrder(orderId);

        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
        verify(orderRepository, times(1)).existsById(orderId);
        verify(orderRepository, never()).deleteById(orderId);
    }
}
