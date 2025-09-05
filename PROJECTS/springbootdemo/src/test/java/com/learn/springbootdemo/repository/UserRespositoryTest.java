package com.learn.springbootdemo.repository;

import com.learn.springbootdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // force embedded H2
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void save_and_getUserById_ok() {
        User u = new User();
        u.setName("Alice");
        u.setEmail("alice@example.com");
        u.setActive(true);

        User saved = userRepository.save(u);

        Optional<User> found = userRepository.getUserById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Alice", found.get().getName());
        assertEquals("alice@example.com", found.get().getEmail());
        assertTrue(found.get().getActive());
    }

    @Test
    void findAllByActive_true_paged() {
        userRepository.save(user("A", "a@ex.com", true));
        userRepository.save(user("B", "b@ex.com", true));
        userRepository.save(user("C", "c@ex.com", false));

        PageRequest pageReq = PageRequest.of(0, 10, Sort.by("id").ascending());

        Page<User> active = userRepository.findAllByActive(true, pageReq);

        assertEquals(2, active.getTotalElements());
        assertEquals(2, active.getContent().size());
        assertTrue(active.getContent().stream().allMatch(User::getActive));
    }

    @Test
    void findByNameContainingIgnoreCase_ok() {
        userRepository.save(user("Saketh", "s@ex.com", true));
        userRepository.save(user("Sandy",  "sa@ex.com", true));
        userRepository.save(user("Alex",   "a@ex.com", true));

        List<User> result = userRepository.findByNameContainingIgnoreCase("sa");

        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(u -> u.getName().equals("Saketh")));
        assertTrue(result.stream().anyMatch(u -> u.getName().equals("Sandy")));
    }

    /* helper */
    private static User user(String name, String email, boolean active) {
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setActive(active);
        return u;
    }
}
