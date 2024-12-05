package org.spring.orders.dto;

import org.spring.orders.model.Order;

import java.util.List;

public class UserResponse {
    private String name;

    private String email;

    private String city;
    private List<Order> orders;

    public UserResponse() {
    }

    public UserResponse(String name, String email, String city, List<Order> orders) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public UserResponse setName(String name) {
        this.name = name;
        return this;
    }

    public UserResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserResponse setCity(String city) {
        this.city = city;
        return this;
    }

    public UserResponse setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }
}
