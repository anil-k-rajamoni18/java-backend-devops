package com.learn.todoapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenApiRequest(
        @JsonProperty("client_id") String clientId,
        @JsonProperty("client_secret") String clientSecret,
        @JsonProperty("grant_type") String grantType

) {
}
