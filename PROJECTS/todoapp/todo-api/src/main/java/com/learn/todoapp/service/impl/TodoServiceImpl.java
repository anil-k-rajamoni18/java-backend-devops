package com.learn.todoapp.service.impl;

import com.learn.todoapp.dto.TodoCreateRequest;
import com.learn.todoapp.dto.TodoResponse;
import com.learn.todoapp.dto.TodoUpdateRequest;
import com.learn.todoapp.enity.Todo;
import com.learn.todoapp.enums.TodoStatus;
import com.learn.todoapp.exception.TodoNotFoundException;
import com.learn.todoapp.repository.TodoRepository;
import com.learn.todoapp.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    private static final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);

    private final TodoRepository todoRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoResponse> getAllTodos() {
        logger.info("Fetching all todos");
        return todoRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(TodoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TodoResponse getTodoById(Long id) {
        logger.info("Fetching todo with id: {}", id);
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));
        return TodoResponse.fromEntity(todo);
    }

    @Override
    public TodoResponse createTodo(TodoCreateRequest request) {
        logger.info("Creating new todo with title: {}", request.getTitle());

        Todo todo = new Todo(request.getTitle(), request.getDescription());
        Todo savedTodo = todoRepository.save(todo);

        logger.info("Successfully created todo with id: {}", savedTodo.getId());
        return TodoResponse.fromEntity(savedTodo);
    }

    @Override
    public TodoResponse updateTodo(Long id, TodoUpdateRequest request) {
        logger.info("Updating todo with id: {}", id);

        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));

        existingTodo.setTitle(request.getTitle());
        existingTodo.setDescription(request.getDescription());

        if (request.getStatus() != null) {
            existingTodo.setStatus(request.getStatus());
        }

        Todo updatedTodo = todoRepository.save(existingTodo);
        logger.info("Successfully updated todo with id: {}", id);

        return TodoResponse.fromEntity(updatedTodo);
    }

    @Override
    public TodoResponse updateTodoStatus(Long id, TodoStatus status) {
        logger.info("Updating status of todo with id: {} to {}", id, status);

        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + id));

        existingTodo.setStatus(status);
        Todo updatedTodo = todoRepository.save(existingTodo);

        logger.info("Successfully updated status of todo with id: {} to {}", id, status);
        return TodoResponse.fromEntity(updatedTodo);
    }

    @Override
    public void deleteTodo(Long id) {
        logger.info("Deleting todo with id: {}", id);

        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException("Todo not found with id: " + id);
        }

        todoRepository.deleteById(id);
        logger.info("Successfully deleted todo with id: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoResponse> getTodosByStatus(TodoStatus status) {
        logger.info("Fetching todos with status: {}", status);
        return todoRepository.findByStatusOrderByCreatedAtDesc(status)
                .stream()
                .map(TodoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoResponse> searchTodos(String searchTerm) {
        logger.info("Searching todos with term: {}", searchTerm);
        return todoRepository.findByTitleOrDescriptionContaining(searchTerm)
                .stream()
                .map(TodoResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TodoResponse> getAllTodosWithPagination(Pageable pageable) {
        logger.info("Fetching todos with pagination: page={}, size={}",
                pageable.getPageNumber(), pageable.getPageSize());
        return todoRepository.findAll(pageable)
                .map(TodoResponse::fromEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TodoResponse> getTodosByStatusWithPagination(TodoStatus status, Pageable pageable) {
        logger.info("Fetching todos with status: {} and pagination: page={}, size={}",
                status, pageable.getPageNumber(), pageable.getPageSize());
        return todoRepository.findByStatus(status, pageable)
                .map(TodoResponse::fromEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalCount() {
        return todoRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public long getCountByStatus(TodoStatus status) {
        return todoRepository.countByStatus(status);
    }
}
