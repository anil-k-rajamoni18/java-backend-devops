package com.learn.springbootdemo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")  // foreign key in Employee table
    private Department department;

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }
}
