package org.spring.orders.service;

import org.spring.orders.dto.UserResponse;
import org.spring.orders.exception.UserNotFoundException;
import org.spring.orders.model.StoreUser;
import org.spring.orders.repository.UserRepository;
import org.spring.orders.utils.ProductMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<StoreUser> getAllUsers() {
        return userRepository.findAll();
    }

    public ResponseEntity<UserResponse> getUserById(long id) {
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return ResponseEntity.ok(ProductMapper.toUserResponse(userRepository.findById(id).get()));
    }

    public StoreUser createUser(StoreUser user) {
        return userRepository.save(user);
    }

    public StoreUser updateUser(StoreUser user) {
        StoreUser currentUser = userRepository.findByName(user.getName());
        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        currentUser.setCity(user.getCity());
        return userRepository.save(currentUser);
    }

    public ResponseEntity<StoreUser> deleteUser(long id) {
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
