package com.learn.module3;

public class EmailService implements MessageService {
    public void send(String to, String body) {
        System.out.printf("Sending email to %s, with body: %s%n", to, body);
    }
}
