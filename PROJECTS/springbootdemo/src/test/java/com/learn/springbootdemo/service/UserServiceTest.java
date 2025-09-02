package com.learn.springbootdemo.service;

import com.learn.springbootdemo.dto.UserDto;
import com.learn.springbootdemo.entity.User;
import com.learn.springbootdemo.handler.UserNotFoundException;
import com.learn.springbootdemo.repository.UserRepository;
import com.learn.springbootdemo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void testGetUserById() {
        User mockUser = new User("ram", "ram@gmail.com", true);
        when(userRepository.getUserById(1L)).thenReturn(Optional.of(mockUser));

        UserDto result = userService.getUserById(1L);

        assertEquals("ram", result.getName());
        verify(userRepository, times(1)).getUserById(1L);
    }

    @Test
    void testUserNotFound() {
        when(userRepository.getUserById(1L)).thenReturn(Optional.empty());

        assertThrowsExactly(UserNotFoundException.class, () -> userService.getUserById(1L), "User Not Found with Id: 2");
        verify(userRepository, times(1)).getUserById(1L);
    }

    @Test
    void testCreateUser() {
        UserDto userDto = new UserDto("ram", "ram@gmai.com", true);
        User mockUser = new User("ram", "ram@gmail.com", "@1837293700382hsg%292", true);
        when(passwordEncoder.encode("password123")).thenReturn("@1837293700382hsg%292");
        when(userRepository.save(mockUser)).thenReturn(mockUser);

        UserDto result = userService.createUser(userDto);

        assertEquals("ram", result.getName());
        verify(passwordEncoder, times(1)).encode("password123");
        verify(userRepository, times(1)).save(mockUser);
    }
}
