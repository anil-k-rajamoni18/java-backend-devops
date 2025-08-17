package com.learn;

import com.learn.module1.User;
import com.learn.module3.MyBean;
import com.learn.module3.UserService;
import com.learn.module3.AppConfig;
import com.learn.module3.MessageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        // without ioc
        /*
        UserService userService = new UserService(new EmailService());
        userService.register(new User("kumar", "kumar123@gmail.com"));
        */

        // ioc with beans.xml
        /*
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        User user = context.getBean("user", User.class);
        MessageService messageService = context.getBean("emailService", MessageService.class);
        UserService userService = context.getBean("userService", UserService.class);
        userService.register(user);
        */

        // ioc with java config
        /*
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = context.getBean("user", User.class);
        MessageService messageService = context.getBean("emailService", MessageService.class);
        UserService userService = context.getBean("userService", UserService.class);
        userService.register(user);
         */

        // ioc with annotation config
        /*
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = new User("ram", "ram@gmail.com");
        MessageService messageService = context.getBean("emailService", MessageService.class);
        MessageService messageService2 = context.getBean("emailService", MessageService.class);
        System.out.println(messageService.hashCode());
        System.out.println(messageService2.hashCode());
        UserService userService = context.getBean("userService", UserService.class);
        userService.register(user);
        */
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MyBean myBean = context.getBean("myBean", MyBean.class);
        System.out.println(myBean);
    }

}