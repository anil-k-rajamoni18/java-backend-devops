package com.learn.springbootdemo.hanlder;

import com.learn.springbootdemo.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestGlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex) {
        ApiError error = new ApiError("USER_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        ApiError error = new ApiError("GENERIC_ERROR", "Something went wrong");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}