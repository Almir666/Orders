package org.spring.orders.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.spring.orders.dto.UserResponse;
import org.spring.orders.model.StoreUser;
import org.spring.orders.service.UserService;
import org.spring.orders.utils.Views;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @JsonView(Views.UserSummary.class)
    public List<StoreUser> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<StoreUser> createUser(@RequestBody StoreUser user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PutMapping()
    public ResponseEntity<StoreUser> updateUser(@RequestBody StoreUser updateUser) {
        return ResponseEntity.ok(userService.updateUser(updateUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEntity<StoreUser>> deleteUser(@PathVariable long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
