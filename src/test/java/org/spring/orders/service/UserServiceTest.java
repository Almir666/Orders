package org.spring.orders.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.spring.orders.dto.UserResponse;
import org.spring.orders.exception.UserNotFoundException;
import org.spring.orders.model.StoreUser;
import org.spring.orders.repository.UserRepository;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetAllUsers() {
        List<StoreUser> users = new ArrayList<>();
        users.add(new StoreUser(1L, "Almir", "Moscow", "qqq@www.ru"));
        users.add(new StoreUser(2L, "Alexandr", "Moscow", "zzz@www.ru"));

        when(userRepository.findAll()).thenReturn(users);

        List<StoreUser> result = userService.getAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(users, result);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserByIdUserExists() {
        StoreUser user = new StoreUser(1L, "Almir", "Moscow", "qqq@www.ru");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<UserResponse> response = userService.getUserById(1L);

        assertNotNull(response);
        assertEquals("Almir", response.getBody().getName());
        verify(userRepository, times(2)).findById(1L);
    }

    @Test
    void testGetUserByIdUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateUser() {
        StoreUser savedUser = new StoreUser(1L, "Almir", "Moscow", "qqq@www.ru");

        when(userRepository.save(savedUser)).thenReturn(savedUser);

        StoreUser result = userService.createUser(savedUser);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Almir", result.getName());
        verify(userRepository, times(1)).save(savedUser);
    }


    @Test
    void testUpdateUserSuccess() {
        StoreUser existingUser = new StoreUser(1L, "Almir", "Moscow", "qqq@www.ru");
        StoreUser updatedUser = new StoreUser(1L, "Alexandr", "Kazan", "zzz@www.ru");

        when(userRepository.findByName("Alexandr")).thenReturn(existingUser);
        when(userRepository.save(existingUser)).thenReturn(updatedUser);

        StoreUser result = userService.updateUser(updatedUser);

        assertNotNull(result);
        assertEquals("Alexandr", result.getName());
        assertEquals("zzz@www.ru", result.getEmail());
        assertEquals("Kazan", result.getCity());

        verify(userRepository).findByName("Alexandr");
        verify(userRepository).save(existingUser);
    }

    @Test
    void testDeleteUserSuccess() {
        long userId = 1L;
        StoreUser user = new StoreUser(userId, "Almir", "Moscow", "qqq@www.ru");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.existsById(userId)).thenReturn(true);

        var response = userService.deleteUser(userId);

        assertNotNull(response);
        assertEquals(204, response.getStatusCode().value());

        verify(userRepository).findById(userId);
        verify(userRepository).existsById(userId);
        verify(userRepository).deleteById(userId);
    }

    @Test
    void testDeleteUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1L));
        verify(userRepository, times(1)).findById(1L);
    }
}
