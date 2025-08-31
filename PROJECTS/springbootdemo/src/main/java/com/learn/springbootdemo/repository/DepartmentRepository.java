package com.learn.springbootdemo.repository;

import com.learn.springbootdemo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserById(Long id);
    Page<User> findAllByActive(Boolean active, Pageable pageable);
    List<User> findByNameContainingIgnoreCase(String name);
}
