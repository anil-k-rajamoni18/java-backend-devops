package com.learn.springbootdemo.handler;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

public class ApiException extends RuntimeException {
    private final String errorCode;
    private final HttpStatus status;
    private final LocalDateTime timestamp;
    private final String path;
    private final Map<String, String> details;

    public ApiException(String message, String errorCode, HttpStatus status, String path, Map<String, String> details) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.details = details;
    }
}