package com.learn;

import com.learn.dao.EmployeeDAO;
import com.learn.model.Employee;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Employee Management =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee");
            System.out.println("3. Update Age");
            System.out.println("4. Delete Employee");
            System.out.println("5. List All");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Emp ID: ");
                    String id = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Dept: ");
                    String dept = sc.nextLine();
                    System.out.print("Age: ");
                    int age = sc.nextInt();
                    dao.addEmployee(new Employee(id, name, dept, age));
                    break;

                case 2:
                    System.out.print("Enter Emp ID: ");
                    dao.getEmployee(sc.nextLine());
                    break;

                case 3:
                    System.out.print("Emp ID: ");
                    String updateId = sc.nextLine();
                    System.out.print("New Age: ");
                    int newAge = sc.nextInt();
                    dao.updateEmployeeAge(updateId, newAge);
                    break;

                case 4:
                    System.out.print("Emp ID to delete: ");
                    dao.deleteEmployee(sc.nextLine());
                    break;

                case 5:
                    dao.listEmployees();
                    break;
            }
        } while (choice != 0);

        System.out.println("👋 Exiting...");
    }
}