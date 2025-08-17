package com.learn.module3;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyBean implements InitializingBean, DisposableBean {

    @Autowired
    private EmailService service;

    public MyBean() {
        System.out.println("MyBean: DefaultNoArgs constructor called.");
    }
    @PostConstruct
    public void postConstruct() {
        System.out.println("MyBean: PostConstruct called");
    }

    @Override
    public void destroy() {
        System.out.println("DisposableBean destroy");
    }
    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean afterPropertiesSet");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("PreDestroy called");
    }
}
