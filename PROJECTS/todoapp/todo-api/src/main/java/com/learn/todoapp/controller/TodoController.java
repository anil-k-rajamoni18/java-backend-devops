package com.learn.todoapp.controller;

import com.learn.todoapp.dto.StatusUpdateRequest;
import com.learn.todoapp.dto.TodoCreateRequest;
import com.learn.todoapp.dto.TodoResponse;
import com.learn.todoapp.dto.TodoUpdateRequest;
import com.learn.todoapp.enums.TodoStatus;
import com.learn.todoapp.service.TodoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TodoController {

    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Get all todos with optional filtering and search
     * GET /api/todos?status=PENDING&search=task&page=0&size=10
     */
    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAllTodos(
            @RequestParam(required = false) TodoStatus status,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "20") int size) {

        logger.info("GET /api/todos - status: {}, search: {}, page: {}, size: {}",
                status, search, page, size);

        List<TodoResponse> todos;

        // Handle search functionality
        if (search != null && !search.trim().isEmpty()) {
            todos = todoService.searchTodos(search.trim());
        }
        // Handle status filtering
        else if (status != null) {
            todos = todoService.getTodosByStatus(status);
        }
        // Get all todos
        else {
            todos = todoService.getAllTodos();
        }

        return ResponseEntity.ok(todos);
    }

    /**
     * Get paginated todos
     * GET /api/todos/paginated?page=0&size=10&sort=createdAt,desc
     */
    @GetMapping("/paginated")
    public ResponseEntity<Page<TodoResponse>> getAllTodosWithPagination(
            @RequestParam(required = false) TodoStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection) {

        logger.info("GET /api/todos/paginated - status: {}, page: {}, size: {}", status, page, size);

        Sort sort = sortDirection.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<TodoResponse> todos = status != null
                ? todoService.getTodosByStatusWithPagination(status, pageable)
                : todoService.getAllTodosWithPagination(pageable);

        return ResponseEntity.ok(todos);
    }

    /**
     * Get a specific todo by ID
     * GET /api/todos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodoById(@PathVariable Long id) {
        logger.info("GET /api/todos/{}", id);

        TodoResponse todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }

    /**
     * Create a new todo
     * POST /api/todos
     */
    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(@Valid @RequestBody TodoCreateRequest request) {
        logger.info("POST /api/todos - title: {}", request.getTitle());

        TodoResponse createdTodo = todoService.createTodo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    /**
     * Update an existing todo
     * PUT /api/todos/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoUpdateRequest request) {

        logger.info("PUT /api/todos/{} - title: {}", id, request.getTitle());

        TodoResponse updatedTodo = todoService.updateTodo(id, request);
        return ResponseEntity.ok(updatedTodo);
    }

    /**
     * Update only the status of a todo
     * PATCH /api/todos/{id}/status
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<TodoResponse> updateTodoStatus(
            @PathVariable Long id,
            @Valid @RequestBody StatusUpdateRequest request) {

        TodoResponse updatedTodo = todoService.updateTodoStatus(id, request.getStatus());
        return ResponseEntity.ok(updatedTodo);
    }

    /**
     * Delete a todo
     * DELETE /api/todos/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get todo statistics
     * GET /api/todos/stats
     */
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getTodoStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("total", todoService.getTotalCount());
        stats.put("pending", todoService.getCountByStatus(TodoStatus.PENDING));
        stats.put("inProgress", todoService.getCountByStatus(TodoStatus.IN_PROGRESS));
        stats.put("done", todoService.getCountByStatus(TodoStatus.DONE));

        return ResponseEntity.ok(stats);
    }
}
