package com.learn.todoapp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ClientCredentialsResponse {
    private String clientName;
    private String clientId;
    private String clientSecret;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private boolean active;

    public ClientCredentialsResponse(String clientName, String clientId, String clientSecret,
                                     LocalDateTime createdAt, LocalDateTime expiresAt, boolean active) {
        this.clientName = clientName;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.active = active;
    }
}
