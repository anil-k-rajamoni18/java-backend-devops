package com.learn.springbootdemo.service;

import com.learn.springbootdemo.dto.UserDto;
import com.learn.springbootdemo.entity.User;
import com.learn.springbootdemo.handler.UserNotFoundException;
import com.learn.springbootdemo.repository.UserRepository;
import com.learn.springbootdemo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private PasswordEncoder passwordEncoder; // harmless even if unused
    @InjectMocks private UserServiceImpl userService;

    /* ---------- helpers ---------- */
    private static User entity(Long id, String name, String email, boolean active) {
        User u = new User();
        u.setId(id); u.setName(name); u.setEmail(email); u.setActive(active);
        return u;
    }

    /* ---------- getUserById ---------- */

    @Test
    void getUserById_found() {
        when(userRepository.getUserById(1L)).thenReturn(Optional.of(entity(1L, "Deva", "deva@example.com", true)));

        UserDto dto = userService.getUserById(1L);

        assertEquals("Deva", dto.getName());
        assertEquals("deva@example.com", dto.getEmail());
        assertTrue(dto.getActive());
        verify(userRepository).getUserById(1L);
    }

    @Test
    void getUserById_notFound() {
        when(userRepository.getUserById(99L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(99L));
        verify(userRepository).getUserById(99L);
    }

    /* ---------- createUser ---------- */

    @Test
    void createUser_saves_andReturnsDto() {
        UserDto in = new UserDto("ram", "ram@gmail.com", true);

        when(userRepository.save(any(User.class))).thenAnswer(inv -> {
            User e = inv.getArgument(0);
            e.setId(10L);
            return e;
        });

        UserDto out = userService.createUser(in);

        verify(userRepository).save(argThat(e ->
                "ram".equals(e.getName())
                        && "ram@gmail.com".equals(e.getEmail())
                        && Boolean.TRUE.equals(e.getActive())
        ));
        assertEquals("ram", out.getName());
        assertEquals("ram@gmail.com", out.getEmail());
        assertTrue(out.getActive());
    }

    /* ---------- updateUser ---------- */

    @Test
    void updateUser_ok() {
        when(userRepository.getUserById(1L)).thenReturn(Optional.of(entity(1L, "Old", "old@ex.com", true)));
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        UserDto req = new UserDto("NewName", "new@ex.com", false);
        UserDto out = userService.updateUser(1L, req);

        assertEquals("NewName", out.getName());
        assertEquals("new@ex.com", out.getEmail());
        assertFalse(out.getActive());
    }

    @Test
    void updateUser_notFound() {
        when(userRepository.getUserById(42L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.updateUser(42L, new UserDto()));
        verify(userRepository, never()).save(any());
    }

    /* ---------- updateUserEmail ---------- */

    @Test
    void updateUserEmail_ok() {
        when(userRepository.getUserById(1L)).thenReturn(Optional.of(entity(1L, "A", "a@ex.com", true)));
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));

        UserDto out = userService.updateUserEmail(1L, "new@ex.com");

        assertEquals("new@ex.com", out.getEmail());
        verify(userRepository).save(any(User.class));
    }

    /* ---------- deleteUser ---------- */

    @Test
    void deleteUser_exists_deletes() {
        when(userRepository.existsById(1L)).thenReturn(true);

        userService.deleteUser(1L);

        verify(userRepository).deleteById(1L);
    }

    @Test
    void deleteUser_notExists_throws() {
        when(userRepository.existsById(1L)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(1L));

        verify(userRepository, never()).deleteById(anyLong());
    }

    /* ---------- getAllUsers ---------- */

    @Test
    void getAllUsers_paged_activeTrue() {
        Page<User> pageEntities = new PageImpl<>(
                List.of(entity(1L, "A", "a@ex.com", true), entity(2L, "B", "b@ex.com", true)),
                PageRequest.of(0, 10),
                2
        );

        when(userRepository.findAllByActive(eq(true), any(Pageable.class))).thenReturn(pageEntities);

        Page<UserDto> page = userService.getAllUsers(0, 10, "id", true);

        assertEquals(2, page.getTotalElements());
        assertEquals("A", page.getContent().get(0).getName());
    }

    /* ---------- searchUsersByName ---------- */

    @Test
    void searchUsersByName_ok() {
        when(userRepository.findByNameContainingIgnoreCase("sa")).thenReturn(
                List.of(entity(1L, "Saketh", "s@ex.com", true), entity(2L, "Sandy", "sa@ex.com", true))
        );

        List<UserDto> result = userService.searchUsersByName("sa");

        assertEquals(2, result.size());
        assertEquals("Saketh", result.get(0).getName());
    }
}
