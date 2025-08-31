package com.learn.todoapp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRegistrationResponse {
    private Long userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private String roles;
    private ClientCredentialsResponse clientCredentials;
}
