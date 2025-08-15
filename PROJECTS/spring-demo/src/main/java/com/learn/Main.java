package com.learn;

import com.learn.module1.User;
import com.learn.module2.EmailService;
import com.learn.module2.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // without ioc
        /*
        UserService userService = new UserService(new EmailService());
        userService.register(new User("kumar", "kumar123@gmail.com"));
        */

        // with ioc

        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");

        System.out.println(context.getBean("user"));

    }
}