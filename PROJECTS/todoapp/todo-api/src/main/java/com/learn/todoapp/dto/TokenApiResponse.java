package com.learn.todoapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class TokenApiResponse {
    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("access_token")
    private String token;

    @JsonProperty("expires_in")
    private Long expiresIn;
}
