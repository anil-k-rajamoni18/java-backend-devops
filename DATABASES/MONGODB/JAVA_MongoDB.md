### Project: Employee Management System (CRUD)

**⚙️ Tech Stack:**
- Core Java (Java 8+)
- MongoDB (local or cloud)
- MongoDB Java Driver


**📁 Project Structure*
```less
EmployeeManagement/
│
├── Employee.java         // Model class
├── EmployeeDAO.java      // Data access (CRUD operations)
├── MongoUtil.java        // MongoDB connection helper
└── MainApp.java          // CLI interface for CRUD
```

**1. Add MongoDB Java Driver to Your Project**
```xml
<dependency>
  <groupId>org.mongodb</groupId>
  <artifactId>mongodb-driver-sync</artifactId>
  <version>4.11.0</version>
</dependency>
```
- Or download the JAR from: https://mvnrepository.com/artifact/org.mongodb/mongodb-driver-sync

**2. MongoUtil.java – MongoDB Connection**
```java
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoUtil {
    private static final String URI = "mongodb://localhost:27017";
    private static MongoClient mongoClient;

    public static MongoDatabase getDatabase(String dbName) {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URI);
        }
        return mongoClient.getDatabase(dbName);
    }
}
````

**3. Employee.java – Model Class**
```java
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
```

**4. EmployeeDAO.java – CRUD Operations**
```java
import com.mongodb.client.*;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

public class EmployeeDAO {
    private final MongoCollection<Document> collection;

    public EmployeeDAO() {
        MongoDatabase db = MongoUtil.getDatabase("company");
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
```

**5. MainApp.java – CLI Interface**
```java
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
```