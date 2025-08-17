package com.learn.module3;


import com.learn.module1.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private MessageService messageService;

    public void register(User u) {
        messageService.send(u.getEmail(), "Welcome!");
    }
}
