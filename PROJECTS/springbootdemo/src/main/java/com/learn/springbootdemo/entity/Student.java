package com.learn.springbootdemo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_course",                   // join table
            joinColumns = @JoinColumn(name = "student_id"),   // FK to Student
            inverseJoinColumns = @JoinColumn(name = "course_id") // FK to Course
    )
    private List<Course> courses;
}