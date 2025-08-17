package com.learn.module2;

import com.learn.module1.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean("user")
    public User user() {
        return new User("kumar", "kumar@outlook.com");
    }

    @Bean("emailService")
    public  MessageService messageService() {
        return new EmailService();
    }

    @Bean
    public UserService userService(MessageService messageService) {
        return new UserService(messageService);
    }
}
