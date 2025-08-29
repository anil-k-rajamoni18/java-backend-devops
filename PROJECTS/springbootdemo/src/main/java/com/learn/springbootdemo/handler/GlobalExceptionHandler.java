package com.learn.springbootdemo.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Map<String, String>> handleError(UserNotFoundException ex, HttpServletRequest request, Model model) {
//        model.addAttribute("status", 500);
//        model.addAttribute("error", "Internal Server Error");
//        model.addAttribute("message", ex.getMessage());
//        model.addAttribute("path", request.getRequestURI());
//        return "error"; // maps to templates/error.html

        Map<String, String> errorMap = Map.of("status", "500",
                "error", "Internal Server Error",
                "message", ex.getMessage(),
                "timestamp", LocalDateTime.now().toString(),
                "path", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMap.put(fieldName, errorMessage);
        });
        errorMap.put("status", "BAD REQUEST");
        errorMap.put("code", "400");
        errorMap.put("path", request.getContextPath());
        errorMap.put("timestamp", LocalDateTime.now().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
    }
}
