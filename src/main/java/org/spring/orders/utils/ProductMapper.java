package org.spring.orders.utils;

import org.spring.orders.dto.UserResponse;
import org.spring.orders.model.StoreUser;

public class ProductMapper {
    public static UserResponse toUserResponse(StoreUser user) {
        return new UserResponse(user.getName(),
                user.getEmail(),
                user.getCity(), user.getOrders());
    }
}
