package com.learn.dao;

import com.learn.model.Employee;
import com.learn.util.MongoUtil;
import com.mongodb.client.*;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

public class EmployeeDAO {
    private final MongoCollection<Document> collection;

    public EmployeeDAO() {
        MongoDatabase db = MongoUtil.getDatabase("companydb");
        collection = db.getCollection("employees");
    }

    public void addEmployee(Employee e) {
        collection.insertOne(e.toDocument());
        System.out.println("Employee added.");
    }

    public void getEmployee(String empId) {
        Document doc = collection.find(eq("empId", empId)).first();
        if (doc != null) {
            System.out.println(Employee.fromDocument(doc));
        } else {
            System.out.println("❌ Employee not found.");
        }
    }

    public void updateEmployeeAge(String empId, int newAge) {
        collection.updateOne(eq("empId", empId), new Document("$set", new Document("age", newAge)));
        System.out.println("Employee age updated.");
    }

    public void deleteEmployee(String empId) {
        collection.deleteOne(eq("empId", empId));
        System.out.println("Employee deleted.");
    }

    public void listEmployees() {
        FindIterable<Document> docs = collection.find();
        for (Document doc : docs) {
            System.out.println(Employee.fromDocument(doc));
        }
    }
}
