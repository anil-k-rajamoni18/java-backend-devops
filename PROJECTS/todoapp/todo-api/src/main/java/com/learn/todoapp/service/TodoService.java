package com.learn.todoapp.service;

import com.learn.todoapp.dto.TodoCreateRequest;
import com.learn.todoapp.dto.TodoResponse;
import com.learn.todoapp.dto.TodoUpdateRequest;
import com.learn.todoapp.enums.TodoStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TodoService {

    // Basic CRUD operations
    List<TodoResponse> getAllTodos();
    TodoResponse getTodoById(Long id);
    TodoResponse createTodo(TodoCreateRequest request);
    TodoResponse updateTodo(Long id, TodoUpdateRequest request);
    TodoResponse updateTodoStatus(Long id, TodoStatus status);
    void deleteTodo(Long id);

    // Advanced operations
    List<TodoResponse> getTodosByStatus(TodoStatus status);
    List<TodoResponse> searchTodos(String searchTerm);
    Page<TodoResponse> getAllTodosWithPagination(Pageable pageable);
    Page<TodoResponse> getTodosByStatusWithPagination(TodoStatus status, Pageable pageable);

    // Statistics
    long getTotalCount();
    long getCountByStatus(TodoStatus status);
}
