package com.learn.todoapp.repository;

import com.learn.todoapp.enity.Todo;
import com.learn.todoapp.enums.TodoStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    // Find todos by status
    List<Todo> findByStatus(TodoStatus status);

    // Find todos by status with pagination
    Page<Todo> findByStatus(TodoStatus status, Pageable pageable);

    // Search todos by title (case-insensitive)
    List<Todo> findByTitleContainingIgnoreCase(String title);

    // Search todos by title or description
    @Query("SELECT t FROM Todo t WHERE " +
            "LOWER(t.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(t.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Todo> findByTitleOrDescriptionContaining(@Param("searchTerm") String searchTerm);

    // Find recent todos
    @Query("SELECT t FROM Todo t WHERE t.createdAt >= :since ORDER BY t.createdAt DESC")
    List<Todo> findRecentTodos(@Param("since") LocalDateTime since);

    // Count todos by status
    long countByStatus(TodoStatus status);

    // Find all todos ordered by creation date
    List<Todo> findAllByOrderByCreatedAtDesc();

    // Find todos by status ordered by creation date
    List<Todo> findByStatusOrderByCreatedAtDesc(TodoStatus status);
}
