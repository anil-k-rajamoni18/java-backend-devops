package com.learn.springbootdemo.controller;

import com.learn.springbootdemo.dto.UserDto;
import com.learn.springbootdemo.handler.UserNotFoundException;
import com.learn.springbootdemo.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean // mock service layer
    private UserService userService;

    @Test
    @DisplayName("GET /api/users should return paginated users")
    void testGetAllUsers() throws Exception {
        Page<UserDto> page = new PageImpl<>(
                List.of(new UserDto("ram", "ram@example.com", true))
        );
        Mockito.when(userService.getAllUsers(anyInt(), anyInt(), anyString(), anyBoolean()))
                .thenReturn(page);

        mockMvc.perform(get("/api/users")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "id")
                        .param("active", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name", is("ram")));
    }

    @Test
    @DisplayName("GET /api/users/{id} should return user by ID")
    void testGetUserById() throws Exception {
        UserDto user = new UserDto("kumar", "kumar@example.com", true);
        Mockito.when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("kumar@example.com")));
    }

    @Test
    @DisplayName("GET /api/users/search should return list of users by name")
    void testSearchUsers() throws Exception {
        List<UserDto> users = List.of(
                new UserDto("Charlie", "charlie@example.com", true)
        );
        Mockito.when(userService.searchUsersByName("char"))
                .thenReturn(users);

        mockMvc.perform(get("/api/users/search").param("name", "char"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Charlie")));
    }

    @Test
    @DisplayName("POST /api/users should create a new user")
    void testCreateUser() throws Exception {
        UserDto request = new UserDto("Deva", "Deva@example.com", true);
        Mockito.when(userService.createUser(any(UserDto.class)))
                .thenReturn(request);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Deva\",\"email\":\"Deva@example.com\",\"active\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Deva")));
    }

    @Test
    @DisplayName("PUT /api/users/{id} should update user")
    void testUpdateUser() throws Exception {
        UserDto updated = new UserDto("Eve", "eve@example.com", false);
        Mockito.when(userService.updateUser(eq(1L), any(UserDto.class)))
                .thenReturn(updated);

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Eve\",\"email\":\"eve@example.com\",\"active\":false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active", is(false)));
    }

    @Test
    @DisplayName("PATCH /api/users/{id} should update user email")
    void testUpdateUserEmail() throws Exception {
        UserDto updated = new UserDto("Frank", "frank.new@example.com", true);
        Mockito.when(userService.updateUserEmail(1L, "frank.new@example.com"))
                .thenReturn(updated);

        mockMvc.perform(patch("/api/users/1")
                        .param("email", "frank.new@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("frank.new@example.com")));
    }

    @Test
    @DisplayName("DELETE /api/users/{id} should delete user")
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk());
        Mockito.verify(userService).deleteUser(1L);
    }

    @Test
    @DisplayName("GET /api/users/{id} should return 404 when user not found")
    void testUserNotFound() throws Exception {
        Mockito.when(userService.getUserById(99L))
                .thenThrow(new UserNotFoundException("User not found"));

        mockMvc.perform(get("/api/users/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}
