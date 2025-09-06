package com.learn.springbootdemo.controller;

import com.learn.springbootdemo.dto.UserDto;
import com.learn.springbootdemo.handler.UserNotFoundException;
import com.learn.springbootdemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<UserDto> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(required = true) Boolean active
    ) {
        return userService.getAllUsers(page, size, sort, active);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/search")
    public List<UserDto> searchUsers(@RequestParam String name) {
        return userService.searchUsersByName(name);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id,
                              @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @PatchMapping("/{id}")
    public UserDto updateUserEmail(@PathVariable Long id,
                                   @RequestParam String email) {
        return userService.updateUserEmail(id, email);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
