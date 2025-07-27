package com.learn.model;

import org.bson.Document;

public class Employee {
    private String empId;
    private String name;
    private String department;
    private int age;

    // Constructor
    public Employee(String empId, String name, String department, int age) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.age = age;
    }

    // Convert to Mongo Document
    public Document toDocument() {
        return new Document("empId", empId)
                .append("name", name)
                .append("department", department)
                .append("age", age);
    }

    // Create from Mongo Document
    public static Employee fromDocument(Document doc) {
        return new Employee(
                doc.getString("empId"),
                doc.getString("name"),
                doc.getString("department"),
                doc.getInteger("age")
        );
    }

    @Override
    public String toString() {
        return String.format("EmpID: %s | Name: %s | Dept: %s | Age: %d", empId, name, department, age);
    }
}
