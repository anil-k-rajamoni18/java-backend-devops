package com.learn.springbootdemo.loader;

import com.learn.springbootdemo.entity.User;
import com.learn.springbootdemo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class StaticDataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = List.of(
                new User("Aarav Sharma", "aarav.sharma@example.com", true),
                new User("Isha Verma", "isha.verma@example.com", true),
                new User("Vivaan Patel", "vivaan.patel@example.com", false),
                new User("Anaya Mehta", "anaya.mehta@example.com", true),
                new User("Krishna Iyer", "krishna.iyer@example.com", true),
                new User("Riya Reddy", "riya.reddy@example.com", false),
                new User("Aryan Gupta", "aryan.gupta@example.com", true),
                new User("Diya Nair", "diya.nair@example.com", true),
                new User("Kabir Joshi", "kabir.joshi@example.com", false),
                new User("Sneha Das", "sneha.das@example.com", true),
                new User("Yash Malhotra", "yash.malhotra@example.com", true),
                new User("Priya Sinha", "priya.sinha@example.com", true),
                new User("Rohan Bhat", "rohan.bhat@example.com", true),
                new User("Meera Roy", "meera.roy@example.com", false),
                new User("Lakshay Jain", "lakshay.jain@example.com", true),
                new User("Neha Dutta", "neha.dutta@example.com", true),
                new User("Manav Kapoor", "manav.kapoor@example.com", false),
                new User("Tanvi Bhatt", "tanvi.bhatt@example.com", true),
                new User("Siddharth Rao", "siddharth.rao@example.com", true),
                new User("Aanya Sen", "aanya.sen@example.com", true),
                new User("Gaurav Tripathi", "gaurav.tripathi@example.com", true),
                new User("Pooja Pillai", "pooja.pillai@example.com", false),
                new User("Kunal Mishra", "kunal.mishra@example.com", true),
                new User("Shreya Kaur", "shreya.kaur@example.com", true),
                new User("Nikhil Saxena", "nikhil.saxena@example.com", false),
                new User("Tanya Agrawal", "tanya.agrawal@example.com", true),
                new User("Arjun Desai", "arjun.desai@example.com", true),
                new User("Bhavna Ghosh", "bhavna.ghosh@example.com", true),
                new User("Harshad Kulkarni", "harshad.kulkarni@example.com", false),
                new User("Rachna Chatterjee", "rachna.chatterjee@example.com", true)
        );

        // Encrypt and set passwords
        users.forEach(user -> user.setPassword(passwordEncoder.encode("password123")));

        repository.saveAll(users);

        log.info("Loaded 10 users into the database");
    }
}

