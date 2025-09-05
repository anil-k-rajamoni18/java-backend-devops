package com.learn.springbootdemo.service.impl;

import com.learn.springbootdemo.dto.UserDto;
import com.learn.springbootdemo.entity.User;
import com.learn.springbootdemo.handler.UserNotFoundException;
import com.learn.springbootdemo.mapper.UserMapper;
import com.learn.springbootdemo.repository.UserRepository;
import com.learn.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    /** Centralized lookup so all paths throw the same custom exception. */
    private User loadUserOrThrow(Long id) {
        return userRepository.getUserById(id) // <- use ONE repo method everywhere
                .orElseThrow(() -> new UserNotFoundException(id));
        // If you prefer findById, switch to:
        // return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }
    @Override
    public Page<UserDto> getAllUsers(int page, int size, String sort, Boolean active) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        Page<User> users = userRepository.findAllByActive(active, pageable);
        return users.map(UserMapper::toDto);
    }


    @Override
    public UserDto getUserById(Long id) {
        return UserMapper.toDto(loadUserOrThrow(id));
    }

    @Override
    public List<UserDto> searchUsersByName(String name) {
        List<User> users = userRepository.findByNameContainingIgnoreCase(name);
        return users.stream().map(UserMapper::toDto).toList();
    }


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode("password123"));
        User savedUser = userRepository.save(user);
        return UserMapper.toDto(savedUser);
    }


    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        // BEFORE:
        // User existingUser = userRepository.findById(id)
        //         .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        // AFTER: use the same unified lookup everywhere
        User existingUser = loadUserOrThrow(id);

        existingUser.setName(userDto.getName());
        existingUser.setEmail(userDto.getEmail());
        // Boolean in DTO -> boolean on entity (safe for nulls)
        existingUser.setActive(Boolean.TRUE.equals(userDto.getActive()));

        User updatedUser = userRepository.save(existingUser);
        return UserMapper.toDto(updatedUser);
    }


    @Override
    public UserDto updateUserEmail(Long id, String email) {
        User existing = loadUserOrThrow(id);
        existing.setEmail(email);
        User updated = userRepository.save(existing);
        return UserMapper.toDto(updated);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            // BEFORE: throw new RuntimeException("User not found with ID: " + id);
            // AFTER:
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

}
