package com.learn.springbootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/greet")
    public String greetUser(@RequestParam("name") String userName) {
        final ZonedDateTime dateTime = ZonedDateTime.now();
        return String.format("Hello %s, Welcome to Spring Boot App, today: %s", userName, dateTime);
    }
}
