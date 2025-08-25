package com.learn.todoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    private static final String DEFAULT_APP_VERSION = "0.0.0";

    @Autowired
    private BuildProperties properties;

    @GetMapping
    public String appHealthCheckInfo() {
        String appVersion = StringUtils.hasText(properties.getVersion()) ? properties.getVersion() : DEFAULT_APP_VERSION;
        return String.format("TODO API is UP and Running v%s", appVersion);
    }
}
