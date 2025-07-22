package com.learn;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        // Create
        User newUser = new User(12, "Kumar", "kumar@example.com");
        userDAO.addUser(newUser);

        // Read
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            System.out.println(user.getId() + ": " + user.getName() + " - " + user.getEmail());
        }

        // Update
        if (!users.isEmpty()) {
            User userToUpdate = users.get(11);
            userToUpdate.setEmail("kumar123@example.com");
            userDAO.updateUser(userToUpdate);
        }

        // Delete
        if (!users.isEmpty()) {
            userDAO.deleteUser(users.get(11).getId());
        }
    }
}
