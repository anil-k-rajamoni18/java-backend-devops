package com.learn.springbootdemo.service;

import com.learn.springbootdemo.dto.UserDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    Page<UserDto> getAllUsers(int page, int size, String sort, Boolean active);
    UserDto getUserById(Long id);
    List<UserDto> searchUsersByName(String name);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    UserDto updateUserEmail(Long id, String email);
    void deleteUser(Long id);
}
