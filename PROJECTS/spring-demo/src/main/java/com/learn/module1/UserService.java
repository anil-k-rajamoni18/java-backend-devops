package com.learn.module1;

public class UserService {
    private FaxService service = new FaxService(); // concrete wiring inside class

    public void register(User u) {
        System.out.println(u);
        System.out.println("sending welcome notification");
        service.sendWelcome(u.getEmail());
    }
}