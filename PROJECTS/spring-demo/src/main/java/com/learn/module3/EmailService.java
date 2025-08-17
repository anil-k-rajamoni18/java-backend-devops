package com.learn.module3;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements MessageService {

    @Value("${email.server}")
    private String emailServer;
    @Value("${email.user}")
    private String userName;
    @Value("${email.password}")
    private String password;

    public EmailService() {
        System.out.println("EmailService: DefaultNoArgs Constructor called");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("EmailService: PostConstruct called");
    }

    public void send(String to, String body) {
        System.out.printf("Sending email to %s, with body: %s%n", to, body);
    }
}
