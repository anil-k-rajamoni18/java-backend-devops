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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@WithMockUser(username = "tester", roles = {"USER"})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    @DisplayName("GET /api/users/{id} returns user")
    void getUserById_ok() throws Exception {
        Mockito.when(userService.getUserById(1L))
                .thenReturn(new UserDto("Deva", "deva@example.com", true));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Deva")));
    }

    @Test
    @DisplayName("GET /api/users returns paged users")
    void getAllUsers_ok() throws Exception {
        List<UserDto> content = List.of(
                new UserDto("Alice", "alice@example.com", true),
                new UserDto("Bob", "bob@example.com", false)
        );
        Page<UserDto> page = new PageImpl<>(content);

        Mockito.when(userService.getAllUsers(0, 10, "id", true))
                .thenReturn(page);

        mockMvc.perform(get("/api/users")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "id")
                        .param("active", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].name", is("Alice")));
    }

    @Test
    @DisplayName("GET /api/users/search returns list")
    void searchUsers_ok() throws Exception {
        Mockito.when(userService.searchUsersByName("sa"))
                .thenReturn(List.of(
                        new UserDto("Saketh", "s@example.com", true),
                        new UserDto("Sandy", "sandy@example.com", true)
                ));

        mockMvc.perform(get("/api/users/search")
                        .param("name", "sa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Saketh")));
    }

    @Test
    @DisplayName("POST /api/users creates user")
    void createUser_ok() throws Exception {
        Mockito.when(userService.createUser(any(UserDto.class)))
                .thenReturn(new UserDto("Bob", "bob@example.com", true));

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                   {"name":"Bob","email":"bob@example.com","active":true}
                                   """)
                        .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Bob")));
    }

    @Test
    @DisplayName("PUT /api/users/{id} updates user")
    void updateUser_ok() throws Exception {
        Mockito.when(userService.updateUser(eq(1L), any(UserDto.class)))
                .thenReturn(new UserDto("Eve", "eve@example.com", false));

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {"name":"Eve","email":"eve@example.com","active":false}
                """)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active", is(false)));
    }

    @Test
    @DisplayName("PATCH /api/users/{id} updates email")
    void updateUserEmail_ok() throws Exception {
        Mockito.when(userService.updateUserEmail(1L, "frank.new@example.com"))
                .thenReturn(new UserDto("Frank", "frank.new@example.com", true));

        mockMvc.perform(patch("/api/users/1")
                        .param("email", "frank.new@example.com")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("frank.new@example.com")));
    }

    @Test
    @DisplayName("DELETE /api/users/{id} deletes user")
    void deleteUser_ok() throws Exception {
        mockMvc.perform(delete("/api/users/1")
                        .with(csrf()))
                .andExpect(status().isNoContent());

        Mockito.verify(userService).deleteUser(1L);
    }

    @Test
    @DisplayName("GET /api/users/{id} returns 404 when not found")
    void userNotFound_404() throws Exception {
        Mockito.when(userService.getUserById(99L))
                .thenThrow(new UserNotFoundException("User not found"));

        mockMvc.perform(get("/api/users/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}
