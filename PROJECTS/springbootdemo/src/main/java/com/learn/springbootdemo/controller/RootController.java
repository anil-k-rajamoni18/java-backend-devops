package com.learn.springbootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    private static final String APP_VERSION = "0.0.1";

    @GetMapping(value = "/")
    public String healthCheck() {
        return String.format("Spring Boot App is UP and RUNNING v%s", APP_VERSION);
    }
}
