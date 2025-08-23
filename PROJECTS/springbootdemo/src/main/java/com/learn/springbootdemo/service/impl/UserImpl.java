package com.learn.springbootdemo.service.impl;

import com.learn.springbootdemo.dto.UserDto;
import com.learn.springbootdemo.entity.User;
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
public class UserImpl implements UserService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Page<UserDto> getAllUsers(int page, int size, String sort, Boolean active) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        Page<User> userPage;
        if (active != null) {
            userPage = userRepository.findAllByActive(active, pageable);
        } else {
            userPage = userRepository.findAll(pageable);
        }

        return userPage.map(UserMapper::toDto);
    }


    @Override
    public UserDto getUserById(Long id) {
        Optional<User> userOptional = userRepository.getUserById(id);
        User user = userOptional.orElseThrow(() -> new RuntimeException("User Not Found with Id: " + id));
        return UserMapper.toDto(user);
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
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        existingUser.setName(userDto.getName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setActive(userDto.getActive());

        User updatedUser = userRepository.save(existingUser);
        return UserMapper.toDto(updatedUser);
    }


    @Override
    public UserDto updateUserEmail(Long id, String email) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        existingUser.setEmail(email);
        User updatedUser = userRepository.save(existingUser);
        return UserMapper.toDto(updatedUser);
    }


    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

}
