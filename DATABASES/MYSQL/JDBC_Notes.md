# JDBC (Java Database Connectivity)
- JDBC is an API in Java that allows Java programs to interact with databases using SQL.
- JDBC (Java Database Connectivity) API is a standard Java API that enables Java applications to interact with relational databases.
- It provides classes and interfaces for connecting to a database, executing SQL queries, and retrieving results.
- JDBC allows you to perform CRUD (Create, Read, Update, Delete) operations from Java code using SQL.


**🔸 Why JDBC?**
- Platform-independent database access.
- Eliminates the need for writing database-specific code.
- Bridges Java applications and databases.

**🔸 JDBC Architecture**
- Application Layer: Your Java program.
- JDBC API: Interfaces like Connection, Statement, etc.
- JDBC Driver Manager: Manages different types of drivers.
- JDBC Driver: The actual implementation that communicates with the database.
- Database: The backend (MySQL, Oracle, PostgreSQL, etc.).

`a. Two-tier Architecture`
- Java application directly communicates with the database.
- Suitable for small applications.

`b. Three-tier Architecture`
- Java application → JDBC → Middleware (like a server) → Database.
- Used in enterprise applications for scalability and security.



![](https://media.geeksforgeeks.org/wp-content/uploads/20250117153514606749/JDBC-Architecture.webp)
**🔹JDBC Drivers**
- In JDBC, a Driver is a Java class that implements the java.sql.Driver interface. 
- It acts as a middleware that connects Java applications to a specific database.
- A JDBC driver is mandatory to establish a DB connection.
- Each DB vendor (like Oracle, MySQL, PostgreSQL) provides its own driver JAR file.
- Incorrect or missing driver leads to exceptions like ClassNotFoundException or SQLException.

```java

```
| Type   | Description             | Example           |
| ------ | ----------------------- | ----------------- |
| Type 1 | JDBC-ODBC Bridge        | Obsolete          |
| Type 2 | Native-API Driver       | Oracle OCI        |
| Type 3 | Network Protocol Driver | Middleware server |
| Type 4 | Thin Driver (Pure Java) | MySQL Connector/J |

> ➡️ Type 4 is most commonly used.


```java
Class.forName("com.mysql.cj.jdbc.Driver");  // Load the MySQL JDBC driver
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/mydb", "user", "password");

```
- The com.mysql.cj.jdbc.Driver:
    - Is the Type 4 (Pure Java) MySQL driver.
    - Converts Java JDBC calls directly into the MySQL-specific protocol.

**🔹 JDBC API Components**
| Interface           | Description                             |
| ------------------- | --------------------------------------- |
| `Driver`            | Registers the driver with DriverManager |
| `Connection`        | Connects to the DB                      |
| `Statement`         | Executes SQL statements                 |
| `PreparedStatement` | Executes precompiled SQL                |
| `ResultSet`         | Holds data retrieved from DB            |
| `CallableStatement` | Executes stored procedures              |
| `ResultSetMetaData` | Metadata about ResultSet                |
| `DatabaseMetaData`  | Metadata about DB                       |


### 4. JDBC Workflow (Steps)
1. Load the Driver
```java
Class.forName("com.mysql.cj.jdbc.Driver");
```
2. Establish Connection
```java
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/dbname", "user", "password");
```
3. Create Statement
```java
Statement stmt = con.createStatement();
```
4. Execute SQL Query
```java
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
```
5. Process the Result
```java
while(rs.next()) {
    System.out.println(rs.getString("username"));
}
```
6. Close Connection
```java
rs.close();
stmt.close();
con.close();
```

![](https://www3.ntu.edu.sg/home/ehchua/programming/java/images/JDBC_Cycle.png)
---- 
#### Statement vs PreparedStatement vs CallableStatement
| Feature     | Statement               | PreparedStatement                  | CallableStatement       |
| ----------- | ----------------------- | ---------------------------------- | ----------------------- |
| SQL         | Hardcoded               | Parameterized                      | Calls Stored Procedures |
| Security    | Prone to SQL Injection  | Safer                              | Safer                   |
| Performance | Slower                  | Precompiled                        | Optimized               |
| Example     | `"SELECT * FROM users"` | `"SELECT * FROM users WHERE id=?"` | `"{call getUser(?)}"`   |


###🔹ResultSet
**🔸 Navigation Methods**
- rs.next(), rs.previous(), rs.first(), rs.last()
- rs.absolute(int row)

**🔸 Data Retrieval**
- getString(), getInt(), getDate(), etc.
- Use column index or name: rs.getString(1) or rs.getString("username")

**Types of ResultSet**
```java
ResultSet rs = stmt.executeQuery(
    ResultSet.TYPE_SCROLL_INSENSITIVE,
    ResultSet.CONCUR_READ_ONLY);
```

| Type                      | Description           |
| ------------------------- | --------------------- |
| TYPE\_FORWARD\_ONLY       | Default               |
| TYPE\_SCROLL\_INSENSITIVE | Scrollable but static |
| TYPE\_SCROLL\_SENSITIVE   | Reflects changes      |


### 🔹 Transactions in JDBC
- Used to manage Atomicity and Consistency.

- 🔸 Default: Auto Commit Mode
```java
conn.setAutoCommit(false);
```

- 🔸 Commit and Rollback
```java
conn.commit(); // Saves all changes
conn.rollback(); // Undoes changes
```

### 🔹 Batch Processing
- 🔸 Use Case: Insert/update/delete multiple records efficiently.
```sql
PreparedStatement ps = conn.prepareStatement("INSERT INTO users VALUES (?, ?)");
ps.setString(1, "John");
ps.setInt(2, 101);
ps.addBatch();

ps.setString(1, "Jane");
ps.setInt(2, 102);
ps.addBatch();

int[] result = ps.executeBatch();
```

### 🔹 Stored Procedures and CallableStatement
```sql
DELIMITER $$
CREATE PROCEDURE getUser(IN userId INT)
BEGIN
  SELECT * FROM users WHERE id = userId;
END$$
DELIMITER ;

```

**🔸 Calling from Java**
```java
CallableStatement cs = conn.prepareCall("{call getUser(?)}");
cs.setInt(1, 101);
ResultSet rs = cs.executeQuery();
```

### 🔹JDBC Metadata

**🔸 DatabaseMetaData**
```java
DatabaseMetaData meta = conn.getMetaData();
System.out.println(meta.getDatabaseProductName());
```

**🔸 ResultSetMetaData**
```java
ResultSetMetaData rsmd = rs.getMetaData();
int columns = rsmd.getColumnCount();
for (int i = 1; i <= columns; i++) {
    System.out.println(rsmd.getColumnName(i));
}
```

### 🔹JDBC Best Practices
- Always close Connection, Statement, and ResultSet.
- Use try-with-resources to auto-close:
```java
try(Connection conn = DriverManager.getConnection(...)) {
    ...
}
```
- Use PreparedStatement instead of Statement.
- Avoid hardcoding queries; use config files or ORM.
- Use connection pooling (HikariCP, Apache DBCP).


### 🔹Common Exceptions
| Exception                                  | Reason                    |
| ------------------------------------------ | ------------------------- |
| `SQLException`                             | Generic DB error          |
| `ClassNotFoundException`                   | Driver not found          |
| `SQLIntegrityConstraintViolationException` | Primary/foreign key issue |
| `CommunicationsException`                  | Network issues            |


### 🔹Advanced: Connection Pooling

**What is Connection Pooling in JDBC?**
- Connection Pooling is a technique used to manage database connections efficiently by reusing existing connections rather than creating a new one for every database operation.

**🔹 Why is Connection Pooling Important?**
- Creating a DB connection is expensive (slow and resource-heavy).
- Without pooling, each DriverManager.getConnection() creates a new physical connection.
- Pooling allows applications to reuse existing connections, improving:
    - 💨 Performance
    - 📈 Scalability
    - 🧠 Resource management

**🔹 How Connection Pooling Works**
- A pool of connections is created and maintained.
- When a Java app requests a DB connection:
- Instead of opening a new connection, it is given one from the pool.
- After the task is done, the connection is returned to the pool (not closed).
- This connection can be reused by other parts of the application.

- Use libraries like:
    - HikariCP
    - Apache DBCP
    - C3P0

| Library              | Features                               |
| -------------------- | -------------------------------------- |
| **HikariCP**         | Fastest, lightweight, production-ready |
| **Apache DBCP**      | From Apache Commons, widely used       |
| **C3P0**             | Older, stable but slower               |
| **Tomcat JDBC Pool** | Used in Tomcat server environments     |


**🔹 Important Pooling Config Parameters**
| Parameter           | Description                          |
| ------------------- | ------------------------------------ |
| `maximumPoolSize`   | Max number of connections            |
| `minimumIdle`       | Min number of idle connections       |
| `connectionTimeout` | Max wait time to get a connection    |
| `idleTimeout`       | How long idle connections live       |
| `maxLifetime`       | Max age before connection is retired |



**🔸 Without Connection Pooling:**
```java
Connection con = DriverManager.getConnection(...); // New connection every time
```

**🔸 With Connection Pooling:**
```java
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
config.setUsername("root");
config.setPassword("password");

HikariDataSource ds = new HikariDataSource(config);
Connection con = ds.getConnection(); // Gets a pooled connection

```
> Benefits: Reuses a ready connection. Fast. Efficient.


---
## 🔹 14. JDBC vs ORM (e.g., Hibernate)
| Feature        | JDBC             | Hibernate            |
| -------------- | ---------------- | -------------------- |
| Control        | Manual SQL       | Auto via mapping     |
| Learning curve | Simple           | Steeper              |
| Performance    | High (low-level) | Optimized internally |
| Flexibility    | Full control     | Abstracted           |


---
---
## Sample CRUD Application Using JDBC

**Database setup**
```sql
CREATE DATABASE sampledb;

USE sampledb;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100)
);
```

**🔸 1. Create (Insert)**
```java
String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, "John Doe");
pstmt.setString(2, "john@example.com");
pstmt.executeUpdate();
```

**🔸 2. Read (Select)**
```java
String sql = "SELECT * FROM users";
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(sql);
while (rs.next()) {
    System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
}
```

**🔸 3. Update**
```java
String sql = "UPDATE users SET email = ? WHERE id = ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setString(1, "newemail@example.com");
pstmt.setInt(2, 1);
pstmt.executeUpdate();
```

**🔸 4. Delete**
```java
String sql = "DELETE FROM users WHERE id = ?";
PreparedStatement pstmt = conn.prepareStatement(sql);
pstmt.setInt(1, 1);
pstmt.executeUpdate();
```

---
### 🧰 Project Structure
```plaintext
JDBC_CRUD/
├── db.properties
├── DBConnection.java
├── User.java
├── UserDAO.java
└── Main.java
```


**db.properties**
```java
db.url=jdbc:mysql://localhost:3306/sampledb
db.username=root
db.password=yourpassword
```

**DBConnection.java**
```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection != null) return connection;

        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.username");
            String password = prop.getProperty("db.password");

            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
```

**User**
```java
public class User {
    private int id;
    private String name;
    private String email;

    public User() {}

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

**UserDAO**
```java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection conn;

    public UserDAO() {
        conn = DBConnection.getConnection();
    }

    public void addUser(User user) {
        try {
            String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void updateUser(User user) {
        try {
            String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setInt(3, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        try {
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

**Main.java**
```java
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        // Create
        User newUser = new User(0, "Alice", "alice@example.com");
        userDAO.addUser(newUser);

        // Read
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            System.out.println(user.getId() + ": " + user.getName() + " - " + user.getEmail());
        }

        // Update
        if (!users.isEmpty()) {
            User userToUpdate = users.get(0);
            userToUpdate.setEmail("updated@example.com");
            userDAO.updateUser(userToUpdate);
        }

        // Delete
        if (!users.isEmpty()) {
            userDAO.deleteUser(users.get(0).getId());
        }
    }
}

```