package com.learn.todoapp.dto;

import com.learn.todoapp.enity.Todo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.learn.todoapp.enums.TodoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TodoResponse {
    private Long id;
    private String title;
    private String description;
    private TodoStatus status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;


    public TodoResponse(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.description = todo.getDescription();
        this.status = todo.getStatus();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = todo.getUpdatedAt();
    }

    // Static factory method for easy conversion
    public static TodoResponse fromEntity(Todo todo) {
        return new TodoResponse(todo);
    }
}
