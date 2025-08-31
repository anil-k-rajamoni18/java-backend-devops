package com.learn.springbootdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Person {
    @Id
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "passport_id")  // foreign key in Person table
    private Passport passport;
}
