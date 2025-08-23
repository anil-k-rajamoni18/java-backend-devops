package com.learn.springbootdemo.mapper;

import com.learn.springbootdemo.dto.UserDto;
import com.learn.springbootdemo.entity.User;

public class UserMapper {
    public static UserDto toDto(User user) {
        return new UserDto(user.getName(), user.getEmail(), user.getActive());
    }

    public static User toEntity(UserDto dto) {
        return new User(dto.getName(), dto.getEmail(), dto.getActive());
    }
}
