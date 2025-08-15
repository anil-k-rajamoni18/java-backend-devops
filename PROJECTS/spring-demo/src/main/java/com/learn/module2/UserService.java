package com.learn.module2;


import com.learn.module1.User;

public class UserService {
    private final MessageService messageService;

    // constructor injection — preferred
    public UserService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void register(User u) {
        messageService.send(u.getEmail(), "Welcome!");
    }
}
