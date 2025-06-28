## 📌 What is Data?
> Data is raw facts, figures, or statistics collected for reference or analysis. It has no meaning until it is processed.

Types of Data:
- Structured Data: Organized into rows & columns (e.g., tables in a database).
- Unstructured Data: No fixed format (e.g., emails, images, videos).
- Semi-Structured Data: Partially organized (e.g., XML, JSON).
- Metadata: Data about data (e.g., file size, creation date).


## 📌 What is a Database?
> A database is a structured collection of data that can be easily accessed, managed, and updated.

Key Characteristics:
- Stores data in a systematic way.
- Supports efficient retrieval, insertion, and modification.
- Maintains data integrity and security.

<img src="https://images.squarespace-cdn.com/content/v1/5f40c09c48e0fd517ae1f97b/1598788724225-1LNGVD6GP7XYMUDQV9G8/Database.jpg" width="600">

--

## 📌 Evolution of Databases
1. `Flat File Systems (1950s–60s)`:
- Data stored in plain text files.
- No relationships between data.

2. `Hierarchical Databases (e.g., IBM IMS)`:
- Tree-like structure.
- Rigid parent-child relationships.

3. `Network Databases (e.g., CODASYL)`:
- More flexible than hierarchical.
- Complex relationships allowed.

4. `Relational Databases (RDBMS) (1970s–)`:
- Tables with rows & columns.
- Based on SQL.
- Example: MySQL, PostgreSQL, Oracle.

5. `NoSQL Databases (2000s–`):`
- For big data, unstructured data.
- Example: MongoDB, Cassandra.

6. `NewSQL (2010s–)`:
- Combines benefits of SQL and scalability of NoSQL.
- Example: CockroachDB, Google Spanner.

## 📌 Types of Databases
* Relational Databases (RDBMS): MySQL, PostgreSQL
* NoSQL Databases: MongoDB, Cassandra, Redis
* In-Memory Databases: Redis, Memcached
* Time-Series Databases: InfluxDB, TimescaleDB
* Graph Databases: Neo4j, ArangoDB
* Object-Oriented Databases: db4o, ObjectDB
* Cloud Databases: Amazon RDS, Firebase, Google BigQuery
* Distributed Databases: Apache Cassandra, CockroachDB


## 📌 What is a DBMS (Database Management System)?
> Software that interacts with end users and databases to manage data.
- Functions of DBMS:
    - Data storage, retrieval, and update
    - Concurrency control
    - Backup & recovery
    - Security management
    - Data integrity

- Examples: MySQL, PostgreSQL, Oracle DB, SQL Server

<img src="https://www.quest.com/images/og/DBMS-OGImage_166634.jpg" width="600">

---
## 📌 What is MySQL?

> MySQL is an open-source relational database management system based on SQL (Structured Query Language). It's known for its speed, reliability, and ease of use.

Key Features:
- ACID compliant (with InnoDB)
- Supports joins, transactions, stored procedures
- Works well in web applications (LAMP stack)
- MySQL is free and open-source.
- MySQL is the DBMS behind some of the top websites and web-based applications in the world, including Airbnb, Uber, LinkedIn, Facebook, Twitter, and YouTube.
- MySQL is ideal for both small and large applications.

## ⚙️ How MySQL Works Internally

**1.Client Layer**
- User sends a query using a MySQL client or API.
- Query is passed to the MySQL server via TCP/IP.

**2. Parser & Optimizer**
- **Parser** checks SQL syntax.
- **Optimizer** decides the best query execution plan (e.g., index use, join order).

**3. Query Execution Engine**
- Executes the parsed and optimized query.

**4. Storage Engine Layer**
- Handles the actual data read/write operations.
- MySQL supports multiple storage engines:
  - **InnoDB (default):** Transaction-safe, row-level locking.
  - **MyISAM:** Fast reads, but no transaction support.
  - **Others:** MEMORY, CSV, ARCHIVE.

**5. Data Storage**
- Tables are stored as files.
- InnoDB stores data in:
  - Tablespaces
  - Redo logs
  - Undo logs (for transactions)

**6. Buffer Pool**
- Caches frequently accessed data in memory to improve performance.

**7. Redo Logs & Binary Logs**
- **Redo logs:** Ensure recovery in case of crash scenarios.
- **Binary logs:** Used for replication and backup.

<img src="https://dev.mysql.com/doc/refman/8.0/en/images/mysql-architecture.png" width="600">

---

## 📁 Relational vs Non-Relational Databases
| Aspect                 | **Relational (RDBMS)**                    | **Non-Relational (NoSQL)**                         |
| ---------------------- | ----------------------------------------- | -------------------------------------------------- |
| **Structure**          | Uses relations (tables)                   | Uses different formats (document, key-value, etc.) |
| **Data Integrity**     | Enforced via foreign keys, constraints    | Application-level logic handles integrity          |
| **Flexibility**        | Rigid – schema must be defined in advance | Flexible – can store varied and nested data        |
| **Normalization**      | Highly normalized to reduce redundancy    | Denormalization is common for performance          |
| **Storage Format**     | Row-based                                 | Varies (JSON, BSON, key-value pairs)               |
| **Data Relationships** | Strong and enforced via joins             | Weak or application-handled                        |



## 📊 SQL vs NoSQL
| Feature                 | **SQL** (Relational)                  | **NoSQL** (Non-Relational)                         |
| ----------------------- | ------------------------------------- | -------------------------------------------------- |
| **Full Form**           | Structured Query Language             | Not Only SQL                                       |
| **Data Model**          | Tables with rows and columns          | Document, Key-Value, Graph, or Column-Family       |
| **Schema**              | Fixed, predefined schema              | Flexible, dynamic schema                           |
| **Query Language**      | SQL                                   | Varies (JSON queries, API calls, etc.)             |
| **Scalability**         | Vertical (scale-up)                   | Horizontal (scale-out)                             |
| **Transactions (ACID)** | Strong support for ACID               | Some support ACID (depends on DB)                  |
| **Examples**            | MySQL, PostgreSQL, Oracle, SQL Server | MongoDB, Cassandra, Redis, Couchbase, DynamoDB     |
| **Best For**            | Complex queries, structured data      | Big data, high throughput, flexible schema needs   |
| **Joins**               | Native support                        | Limited or no join support                         |
| **Use Cases**           | ERP, CRM, banking, legacy systems     | Real-time apps, IoT, analytics, content management |


**✅ When to Use SQL (Relational):**
- You need complex joins, strict data integrity, and structured data.
- Ideal for financial systems, HR systems, legacy enterprise apps.

**✅ When to Use NoSQL (Non-Relational):**
- You need flexibility, high scalability, or you're handling semi-structured/unstructured data.
- Great for IoT, mobile apps, product catalogs, and real-time analytics.

---
## 🛠️ Installing MySQL (Step-by-Step Guide)
#### 1. Installing MySQL on Windows : MySQL Installer (GUI)

1. Download Installer
- Go to https://dev.mysql.com/downloads/installer/
- Choose the MySQL Installer (Windows x86, 32-bit)

2. Run Installer
- Select "Developer Default" or "Server only" (based on needs)
- Installer will auto-select MySQL Server, Workbench, Shell, etc.

3. Configure Server
- Choose Config Type: Development / Production
- Set port (default 3306) and root password
-  Add user accounts if needed

4. Complete Installation
- Installer will download and configure all components
- Launch MySQL Workbench or MySQL Shell to connect

#### 🍎 2. Installing MySQL on macOS
```bash
# Install Homebrew (if not already):
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"


# Install MySQL:
brew install mysql


# Start MySQL Service:
brew services start mysql

# Secure Installation (Set root password):
mysql_secure_installation


# Login to MySQL:
mysql -u root -p

```

#### 🐧 3. Installing MySQL on Ubuntu/Debian (Linux)
```bash
# Update Package Info:
sudo apt update

# Install MySQL Server:
sudo apt install mysql-server


# Secure Installation:
sudo mysql_secure_installation

# Login to MySQL:
sudo mysql -u root -p


# Start/Stop MySQL Service:
sudo systemctl start mysql
sudo systemctl stop mysql


# Check Version:
mysql --version


````

**Connecting with MySQL Shell**
```bash
# check the version
mysql --version

# connect to MySql Server
mysql -u root -p

```

---
## 📌 MySQL Data Types
> MySQL supports a variety of data types grouped into three main categories:

🔢 Numeric Types:
| Type       | Description               |
| ---------- | ------------------------- |
| `INT`      | Integer values            |
| `TINYINT`  | Very small integer        |
| `SMALLINT` | Small integer             |
| `BIGINT`   | Very large integer        |
| `DECIMAL`  | Fixed-point, exact values |
| `FLOAT`    | Approximate decimal       |
| `DOUBLE`   | Double precision float    |

📅 Date and Time Types:
| Type        | Description            |
| ----------- | ---------------------- |
| `DATE`      | YYYY-MM-DD             |
| `DATETIME`  | YYYY-MM-DD HH\:MM\:SS  |
| `TIMESTAMP` | Automatic current time |
| `TIME`      | HH\:MM\:SS             |
| `YEAR`      | Year (YYYY)            |

🔤 String Types:
| Type         | Description                  |
| ------------ | ---------------------------- |
| `CHAR(n)`    | Fixed-length string          |
| `VARCHAR(n)` | Variable-length string       |
| `TEXT`       | Large text                   |
| `BLOB`       | Binary large object (images) |
| `ENUM`       | Predefined value list        |

---

### **🔧 Database-level commands:**
```bash
# 1. CREATE DATABASE
CREATE DATABASE company_db;

# 2. SHOW DATABASES: Lists all databases in the MySQL server.
SHOW DATABASES;

#  3. USE : Selects a database to work with.
USE company_db;


# 4. DROP DATABASE: Deletes an existing database and all its contents. ⚠️ Irreversible – all tables and data in the database are deleted.
DROP DATABASE company_db;

# 5. RENAME DATABASE (Deprecated in newer versions)
-- No direct command. Use dump and restore:
mysqldump -u root -p old_db > old_db.sql
mysql -u root -p -e "CREATE DATABASE new_db"
mysql -u root -p new_db < old_db.sql
mysql -u root -p -e "DROP DATABASE old_db"

# 6. ALTER DATABASE: Changes database-level settings like character set or collation.
ALTER DATABASE company_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# 7. SELECT DATABASE()
SELECT DATABASE();

# 8. CREATE SCHEMA (Alias for CREATE DATABASE): Just another way to create a database.
CREATE SCHEMA school_db;
```

---
### 🔧 Table-level commands
**1. CREATE TABLE***
- Creates a new table in the current database.
```sql
CREATE TABLE table_name (
    column1 datatype [constraints],
    column2 datatype [constraints],
    ...
);


CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    hire_date DATE
);

```

**2. SHOW TABLES**
- Lists all tables in the selected database.
```sql
SHOW TABLES;
```

**3. DESCRIBE / DESC**
- Shows the structure (columns, types, etc.) of a table.
```sql
DESCRIBE employees;
```

**4. DROP TABLE**
- Deletes a table and all its data.
```sql
DROP TABLE table_name;


DROP TABLE employees;
```

**5. RENAME TABLE**
- Changes the name of an existing table.
```sql
RENAME TABLE old_name TO new_name;

RENAME TABLE employees TO staff;
```

**6. ALTER TABLE**
- Used to add, modify, or delete columns or constraints.
```sql
--  Add a column:
ALTER TABLE employees ADD email VARCHAR(100);

--  Modify a column:
ALTER TABLE employees MODIFY name VARCHAR(150);

-- Drop a column:
ALTER TABLE employees DROP COLUMN email;

-- Add a primary key:
ALTER TABLE employees ADD PRIMARY KEY (id);

```

**7. TRUNCATE TABLE**
- Deletes all rows from a table but keeps the structure.
```sql
TRUNCATE TABLE employees;

-- ⚠️ Faster than DELETE, but cannot be rolled back.
```

**8. CREATE TABLE AS**
- Creates a new table using the result of a query.
```sql
CREATE TABLE senior_employees AS
SELECT * FROM employees WHERE hire_date < '2015-01-01';
```

**9. SHOW CREATE TABLE**
- Displays the exact SQL statement used to create the table.
```sql
SHOW CREATE TABLE employees;
```
---

## 💬 MySQL Comments
- In MySQL, comments are used to add notes, explanations, or temporarily disable parts of SQL code. 
- Comments are ignored by the MySQL engine during execution but are very helpful for developers and teams.

**1. Single-line Comments**
- You can use two dashes -- to write comments on a single line.
```sql
-- This is a single-line comment
SELECT * FROM Employees;

-- This is also a single-line comment
SELECT * FROM Departments;

```

**2. Multi-line Comments**
- For longer or multi-line comments, use /* ... */.
```sql
/*
  This is a multi-line comment.
  It can span across several lines.
*/
SELECT * FROM Orders;
```

- You can also use this to comment out part of a query:
```sql
SELECT /* column1, */ column2 FROM Products;
```

--- 
## 📝 Insertion in MySQL 
- The INSERT statement in MySQL is used to add new records (rows) into a table.

**Basic Syntax**
```sql
INSERT INTO table_name (column1, column2, ...)
VALUES (value1, value2, ...);
```

```sql
CREATE TABLE Employees (
    ID INT,
    Name VARCHAR(50),
    Department VARCHAR(50)
);

-- Insert one row
INSERT INTO Employees (ID, Name, Department)
VALUES (1, 'Alice', 'HR');

```
**🧾 Insert Without Specifying Columns**
```sql
-- All columns must be listed in table definition order
INSERT INTO Employees
VALUES (2, 'Bob', 'IT');

-- ⚠️ Not recommended unless inserting into all columns in correct order.
```

**➕ Insert Multiple Rows**
```sql
INSERT INTO Employees (ID, Name, Department)
VALUES 
(3, 'Charlie', 'Finance'),
(4, 'Diana', 'Sales');

-- Efficient way to insert bulk data.
```

**Inserting with NULL and DEFAULT**
```sql
CREATE TABLE Products (
    ProductID INT,
    Name VARCHAR(100),
    Price DECIMAL(10,2),
    Stock INT DEFAULT 10
);

-- NULL value
INSERT INTO Products (ProductID, Name, Price, Stock)
VALUES (1, 'Keyboard', 19.99, NULL);

-- Use DEFAULT
INSERT INTO Products (ProductID, Name, Price)
VALUES (2, 'Mouse', 14.99); -- Stock becomes 10
```

**INSERT ... SELECT**
- Used to insert data from one table into another.
```sql
-- Copy active users into archive table
INSERT INTO ArchivedUsers (ID, Name, Email)
SELECT ID, Name, Email
FROM ActiveUsers
WHERE Status = 'inactive';
```

**Handling Duplicate Key Conflicts**
- INSERT IGNORE: Skips insert if duplicate key error occurs (e.g., on UNIQUE constraint).

```sql
INSERT IGNORE INTO Employees (ID, Name, Department)
VALUES (1, 'Alice', 'HR');
```

- INSERT ... ON DUPLICATE KEY UPDATE: Updates record if primary/unique key conflict occurs.
```sql
INSERT INTO Employees (ID, Name, Department)
VALUES (1, 'Alice', 'HR')
ON DUPLICATE KEY UPDATE Department = 'HR';
```

**Best Practices**
- Always specify column names explicitly.
- Use parameterized queries in apps to avoid SQL injection.
- Use transactions (START TRANSACTION, COMMIT, ROLLBACK) for critical inserts.

---
---

## 🔐 Constraints in MySQL
- Constraints in MySQL are rules applied to table columns to enforce data integrity, accuracy, and consistency. 
- SQL constraints define rules for the data in a table.
- They restrict the type of data that can be inserted.
- Ensure data accuracy and consistency.
- If a constraint is violated, the operation is aborted.
- Constraints can be:
  - Column-level – apply to a single column.
  - Table-level – apply to the whole table.

- Can be added:
  - When creating a table (CREATE TABLE).
  - After table creation (ALTER TABLE).

### **🔹 Types of Constraints in MySQL**
| Constraint Type | Description                                                     |
| --------------- | --------------------------------------------------------------- |
| `NOT NULL`      | Ensures a column cannot have a NULL value                       |
| `UNIQUE`        | Ensures all values in a column are different                    |
| `PRIMARY KEY`   | Combines `NOT NULL` + `UNIQUE`. Uniquely identifies each record |
| `FOREIGN KEY`   | Enforces referential integrity between tables                   |
| `CHECK`         | Ensures all values in a column satisfy a specific condition     |
| `DEFAULT`       | Assigns a default value if none is provided                     |


**🔹 1. NOT NULL Constraint**
- By default, columns can store NULL values.
- The NOT NULL constraint prevents a column from accepting NULL values.
- It ensures that a value must always be provided for the field.
- You cannot insert or update a record without specifying a value for this column.

> A signup form must not allow users to leave the email blank. Ensure all customers have an email.
```sql
CREATE TABLE Customers (
    ID INT,
    Name VARCHAR(100),
    Email VARCHAR(100) NOT NULL
);
```

```sql
CREATE TABLE Persons (
    ID int NOT NULL,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255) NOT NULL,
    Age int
);

-- Add constriant after creating table.
ALTER TABLE Persons
MODIFY Age int NOT NULL;
```
---

**🔹 2. UNIQUE Constraint**'
- The UNIQUE constraint ensures that each value in a column is distinct.
- Both UNIQUE and PRIMARY KEY constraints enforce uniqueness on one or more columns.
- A PRIMARY KEY automatically includes the behavior of a UNIQUE constraint.
- However, we can have many UNIQUE constraints per table, but only one PRIMARY KEY constraint per table.

> Prevent duplicate usernames in a system. A website where every username must be unique, like Instagram or Twitter. 

```sql
CREATE TABLE Users (
    UserID INT,
    Username VARCHAR(50) UNIQUE
);
```

```sql
CREATE TABLE Persons (
    ID int NOT NULL,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255),
    Age int,
    UNIQUE (ID)
);

-- To name a UNIQUE constraint, and to define a UNIQUE constraint on multiple columns, use the following SQL syntax

CREATE TABLE Persons (
    ID int NOT NULL,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255),
    Age int,
    CONSTRAINT UC_Person UNIQUE (ID,LastName)
);  

-- To drop a UNIQUE constraint, use the following SQL:
ALTER TABLE Persons
DROP CONSTRAINT UC_Person;

```

---
**🔹 3. PRIMARY KEY Constraint**
- The PRIMARY KEY constraint uniquely identifies each row in a table.
- It must have unique values and cannot include NULLs.
- Each table can have only one primary key, which may consist of one or multiple columns.

> Uniquely identify a product. An e-commerce platform uses ProductID to uniquely identify items.
```sql
CREATE TABLE Products (
    ProductID INT PRIMARY KEY,
    Name VARCHAR(100)
);
```

```sql
-- Creating a table with a primary key on a single column:
CREATE TABLE Persons (
    ID int NOT NULL,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255),
    Age int,
    PRIMARY KEY (ID)
);

-- Creating a table with a named composite primary key
CREATE TABLE Persons (
    ID int NOT NULL,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255),
    Age int,
    CONSTRAINT PK_Person PRIMARY KEY (ID, LastName)
);

-- Adding a primary key to an existing table:
ALTER TABLE Persons
ADD PRIMARY KEY (ID);


-- Adding a named composite primary key to an existing table:
ALTER TABLE Persons
ADD CONSTRAINT PK_Person PRIMARY KEY (ID, LastName);

-- To remove the primary key from the table:
ALTER TABLE Persons
DROP PRIMARY KEY;

```
---

**🔹 4. FOREIGN KEY Constraint**
- The FOREIGN KEY constraint is used in SQL to maintain referential integrity between two tables. 
- It ensures that the value in one table matches a value in another table — typically the primary key of the referenced table.

**✅ Key Points:**
- A foreign key in one table points to the primary key in another table.
- It ensures that the relationship between records in two tables remains valid.
- Prevents actions that would leave orphaned records (e.g., deleting a parent row that’s referenced elsewhere).
- Can be created when the table is defined with CREATE TABLE, or later with ALTER TABLE.

```sql
-- Link orders to customers. In an order tracking system, every order must be linked to a valid customer.
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY,
    CustomerID INT,
    FOREIGN KEY (CustomerID) REFERENCES Customers(ID)
);
```

```sql
-- Parent table
CREATE TABLE Departments (
    DeptID int PRIMARY KEY,
    DeptName varchar(100)
);

-- Child table with FOREIGN KEY
CREATE TABLE Employees (
    EmpID int PRIMARY KEY,
    EmpName varchar(100),
    DeptID int,
    FOREIGN KEY (DeptID) REFERENCES Departments(DeptID)
);

```
- Employees.DeptID is a foreign key.
- It references Departments.DeptID, the primary key of the Departments table.

**🔐 Benefits:**
- Maintains data consistency across related tables.
- Prevents invalid data entry (e.g., assigning an employee to a non-existent department).
- Supports cascading actions (like automatic deletes or updates).

**⚙️ Optional Clauses:**
```sql
FOREIGN KEY (DeptID)
REFERENCES Departments(DeptID)
ON DELETE CASCADE
ON UPDATE CASCADE
```
- ON DELETE CASCADE: If a department is deleted, all related employees are also deleted.
- ON UPDATE CASCADE: If the department ID is updated, the change reflects in all related records.

```sql
-- Using ALTER TABLE to Add a Foreign Key:
ALTER TABLE Employees
ADD CONSTRAINT FK_Dept
FOREIGN KEY (DeptID) REFERENCES Departments(DeptID);
```

---
**🔹 5. CHECK Constraint (MySQL 8.0+)**
- The CHECK constraint restricts the range of values that can be entered in a column.
- When applied to a column, it ensures only specific values are allowed for that column.
- When applied at the table level, it can enforce rules involving multiple columns in a row.

> Ensure age is 18 or above. A gym system only allows members 18 years or older to register.
```sql
CREATE TABLE Members (
    MemberID INT PRIMARY KEY,
    Age INT CHECK (Age >= 18)
);
```

```sql
-- Create a table with a CHECK constraint on a single column
CREATE TABLE Persons (
    ID int NOT NULL,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255),
    Age int,
    CHECK (Age >= 18)
);

-- Create a table with a named CHECK constraint involving multiple columns
CREATE TABLE Persons (
    ID int NOT NULL,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255),
    Age int,
    City varchar(255),
    CONSTRAINT CHK_Person CHECK (Age >= 18 AND City = 'Sandnes')
);

-- Add a CHECK constraint to an existing table
ALTER TABLE Persons
ADD CHECK (Age >= 18);

-- Add a named CHECK constraint to an existing table involving multiple columns
ALTER TABLE Persons
ADD CONSTRAINT CHK_PersonAge CHECK (Age >= 18 AND City = 'Sandnes');

-- Remove a CHECK constraint by name
ALTER TABLE Persons
DROP CHECK CHK_PersonAge;
```
---

**🔹 6. DEFAULT Constraint**
- The DEFAULT constraint is used to set a default value for a column.
- The default value will be added to all new records, if no other value is specified.

>  Set a default status for new users. When a user registers, their account is active by default unless specified.
```sql
CREATE TABLE Accounts (
    AccountID INT,
    Status VARCHAR(20) DEFAULT 'Active'
);
```

```sql
-- Create a table with a DEFAULT value for the "City" column
CREATE TABLE Persons (
    ID int NOT NULL,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255),
    Age int,
    City varchar(255) DEFAULT 'Sandnes'
);

-- The DEFAULT constraint can also use system functions, like setting the current date
CREATE TABLE Orders (
    ID int NOT NULL,
    OrderNumber int NOT NULL,
    OrderDate date DEFAULT CURRENT_DATE()
);

-- Add a DEFAULT value to an existing column
ALTER TABLE Persons
ALTER City SET DEFAULT 'Sandnes';

-- Remove the DEFAULT constraint from a column
ALTER TABLE Persons
ALTER City DROP DEFAULT;

```

---
### 📅 MySQL Dates
> In MySQL, working with date and time is common when dealing with things like order timestamps, birthdates, scheduling, etc.

| Data Type   | Description                           | Format                  |
| ----------- | ------------------------------------- | ----------------------- |
| `DATE`      | Stores only date                      | `'YYYY-MM-DD'`          |
| `DATETIME`  | Stores date and time                  | `'YYYY-MM-DD HH:MM:SS'` |
| `TIMESTAMP` | Similar to `DATETIME`, timezone-aware | `'YYYY-MM-DD HH:MM:SS'` |
| `TIME`      | Stores only time                      | `'HH:MM:SS'`            |
| `YEAR`      | Stores year only                      | `'YYYY'`                |

```sql
--  Storing Dates
CREATE TABLE Events (
    EventID INT PRIMARY KEY,
    EventName VARCHAR(100),
    EventDate DATE
);


INSERT INTO Events (EventID, EventName, EventDate)
VALUES (1, 'Hackathon', '2025-07-01');


--  Storing Date and Time
CREATE TABLE Messages (
    MessageID INT PRIMARY KEY,
    Content TEXT,
    SentAt DATETIME
);


INSERT INTO Messages (MessageID, Content, SentAt)
VALUES (1, 'Hello!', '2025-06-14 12:34:56');

```

**Common MySQL Date Functions**
```sql
--  NOW(): Returns current date and time.
SELECT NOW();  -- 2025-06-14 15:45:30

-- CURDATE(): Returns current date.
SELECT CURDATE();  -- 2025-06-14

-- CURTIME(): Returns current time.
SELECT CURTIME();  -- 15:45:30

-- DATE_ADD() and DATE_SUB() : Add or subtract time intervals.

-- Add 7 days to today
SELECT DATE_ADD(CURDATE(), INTERVAL 7 DAY);

-- Subtract 1 month from today
SELECT DATE_SUB(CURDATE(), INTERVAL 1 MONTH);


-- DATEDIFF(): Returns the number of days between two dates.

SELECT DATEDIFF('2025-07-01', '2025-06-14');  -- 17

-- DATE_FORMAT(): Formats a date value into a readable string.
SELECT DATE_FORMAT(NOW(), '%W, %M %d, %Y'); 
-- Output: 'Saturday, June 14, 2025'

```

**Filtering by Date**
```sql
SELECT * FROM Events
WHERE EventDate = '2025-07-01';

-- Events in June 2025
SELECT * FROM Events
WHERE MONTH(EventDate) = 6 AND YEAR(EventDate) = 2025;
```

**Auto-inserting the Current Timestamp**
```sql
CREATE TABLE Logs (
    LogID INT PRIMARY KEY AUTO_INCREMENT,
    Message TEXT,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO Logs (Message) VALUES ('User logged in');
```

**Comparing Dates**
```sql
-- Find events after today
SELECT * FROM Events
WHERE EventDate > CURDATE();

-- Find messages sent in the last 7 days
SELECT * FROM Messages
WHERE SentAt >= DATE_SUB(NOW(), INTERVAL 7 DAY);
```

**Extracting Parts of Dates**
```sql
SELECT 
    YEAR(EventDate) AS Year,
    MONTH(EventDate) AS Month,
    DAY(EventDate) AS Day
FROM Events;
```
**📌 Best Practices**
- Always use DATE, DATETIME, or TIMESTAMP based on precision needs.
- Use UTC timestamps if working in global systems.
- Avoid storing dates as strings unless strictly necessary.

--- 

### ❓ What is a NULL Value in SQL?
- In SQL, a NULL value represents missing, unknown, or undefined data. 
- It is not the same as 0, an empty string (''), or FALSE.

**Key Points**
- NULL means "no value" or "value unknown".
- It is used when a value has not been entered or does not exist.
- Any operation with NULL results in NULL.

```sql
CREATE TABLE Students (
    ID INT,
    Name VARCHAR(50),
    Email VARCHAR(100)  -- Email is optional
);

INSERT INTO Students (ID, Name, Email)
VALUES (1, 'Alice', NULL);
```

**Checking for NULL**
```sql
-- This WON'T work
SELECT * FROM Students WHERE Email = NULL;


-- This WILL work
SELECT * FROM Students WHERE Email IS NULL;

-- Opposite: Check non-NULL
SELECT * FROM Students WHERE Email IS NOT NULL;

-- NULL in Expressions: Any arithmetic or string operation involving NULL returns NULL.
SELECT NULL + 10;        -- NULL
SELECT 'abc' || NULL;    -- NULL (concatenation with NULL is NULL)

-- NULL with Aggregate Functions
SELECT COUNT(*) FROM Students;          -- All rows
SELECT COUNT(Email) FROM Students;      -- Only rows with non-NULL Email
```

**Common Functions to Handle NULL**
| Function       | Description                                 |
| -------------- | ------------------------------------------- |
| `IS NULL`      | Checks if value is NULL                     |
| `IS NOT NULL`  | Checks if value is not NULL                 |
| `COALESCE()`   | Returns first non-NULL value                |
| `IFNULL(a, b)` | Returns `a` if not NULL; else returns `b`   |
| `NULLIF(a, b)` | Returns `NULL` if `a = b`; else returns `a` |

```sql
SELECT COALESCE(Email, 'Not provided') AS DisplayEmail FROM Students;
```

---

## 🔍 SELECTION in SQL
- Selection in SQL refers to the process of retrieving specific rows from a table that meet certain conditions. 
- It's done using the SELECT statement along with the WHERE clause.
> Selection = Filtering Rows
- Selection chooses rows (records) from a table.
- It does not alter the table—it only returns a result set.

**📌 1. SELECT with WHERE Clause**
```sql
SELECT column1, column2, ...
FROM table_name
WHERE condition;

SELECT * FROM Customers
WHERE Name = 'Alice';

```
- If you omit the WHERE clause, it selects all rows.

**🔧 2. Operators in WHERE**
| Operator     | Meaning                     | Example                      |
| ------------ | --------------------------- | ---------------------------- |
| `=`          | Equals                      | `Name = 'Alice'`             |
| `<>` or `!=` | Not equal                   | `Name <> 'Bob'`              |
| `>`          | Greater than                | `Price > 100`                |
| `<`          | Less than                   | `Stock < 50`                 |
| `>=`         | Greater than or equal       | `Price >= 200`               |
| `<=`         | Less than or equal          | `Stock <= 20`                |
| `AND`        | Combine multiple conditions | `Price > 100 AND Stock < 10` |
| `OR`         | Either condition true       | `Price < 10 OR Stock > 100`  |
| `NOT`        | Negates condition           | `NOT Name = 'Alice'`         |


**🧮 3. Aggregate Functions**
- Aggregate functions operate on a set of values and return a single result.
| Function  | Description          |
| --------- | -------------------- |
| `COUNT()` | Total number of rows |
| `SUM()`   | Total sum            |
| `AVG()`   | Average value        |
| `MAX()`   | Maximum value        |
| `MIN()`   | Minimum value        |

```sql
SELECT COUNT(*) FROM Products;
SELECT AVG(Price) FROM Products WHERE Stock > 0;
```

**🔎 4. LIKE Operator**
- Used for pattern matching with % (any sequence) and _ (single character).
```sql
-- Names starting with A
SELECT * FROM Customers
WHERE Name LIKE 'A%';

-- Emails ending in .com
SELECT * FROM Customers
WHERE Email LIKE '%.com';
```

**📋 5. IN Operator**
- Checks if a value is in a list of values.
```sql
SELECT * FROM Products
WHERE Name IN ('Mouse', 'Keyboard', 'Monitor');
```

**6. BETWEEN Operator**
```sql
SELECT * FROM Products
WHERE Price BETWEEN 50 AND 200;
```

**📑 7. ORDER BY Clause**
- Sorts result by one or more columns.
```sql
SELECT * FROM Products
ORDER BY Price DESC;
```
- ASC = ascending (default)
- DESC = descending


**📏 8. LIMIT Clause**
- Limits the number of records returned.
- Often used for pagination or performance.
```sql
SELECT * FROM Products
ORDER BY Price DESC
LIMIT 3;
```

```sql
SELECT Name, Price, Stock
FROM Products
WHERE Price BETWEEN 50 AND 200
  AND Name LIKE 'M%'
ORDER BY Stock DESC
LIMIT 5;
```

---
## GROUP BY & HAVING Clauses
- GROUP BY is used to group rows that have the same values in specified columns and apply aggregate functions (SUM(), COUNT(), AVG(), etc.) to each group.

```sql
SELECT
    c1, c2,..., cn, aggregate_function(ci)
FROM
    table
WHERE
    where_conditions
GROUP BY c1 , c2,...,cn;

-- Total number of orders by each customer
SELECT CustomerID, COUNT(*) AS OrderCount
FROM Orders
GROUP BY CustomerID;

```

- Every column in the SELECT list must be:
  - Aggregated (SUM(), COUNT(), etc.), or
  - Included in the GROUP BY clause.

- The grouping can be done on one or more columns.

**🔹 HAVING Clause**
- HAVING is used to filter grouped results. It works like WHERE, but is applied after the aggregation.

```sql
SELECT column_name, AGGREGATE_FUNCTION(column_name)
FROM table_name
GROUP BY column_name
HAVING condition;

-- Only show customers who made more than 3 orders
SELECT CustomerID, COUNT(*) AS OrderCount
FROM Orders
GROUP BY CustomerID
HAVING COUNT(*) > 3;

```

**🆚 WHERE vs HAVING**
| Feature    | `WHERE`                      | `HAVING`                       |
| ---------- | ---------------------------- | ------------------------------ |
| Filters    | **Before** grouping          | **After** grouping             |
| Works with | Any column                   | Only aggregate or grouped data |
| Used with  | `SELECT`, `UPDATE`, `DELETE` | Always with `GROUP BY`         |


```sql
-- Show average price of in-stock products by category,
-- only if the average is above $50
SELECT Category, AVG(Price) AS AvgPrice
FROM Products
WHERE Stock > 0
GROUP BY Category
HAVING AVG(Price) > 50;
```

--- 
## UPDATION
- The UPDATE statement is used to modify existing records in a table.
```sql
UPDATE table_name
SET column1 = value1, column2 = value2, ...
WHERE condition;


-- ⚠️ Important: Always use a WHERE clause to avoid updating all rows.
```

**🧾 Example 1 – Update a single record:**
```sql
UPDATE Employees
SET Age = 30
WHERE EmpID = 101;
```

**🧾 Example 2 – Update multiple fields:**
```sql
UPDATE Employees
SET Age = 35, City = 'Mumbai'
WHERE EmpID = 102;
```
**🧾 Example 3 – Update all rows (use with caution):**
```sql
UPDATE Employees
SET Status = 'Active';
```

--- 

## DELETION
- The DELETE statement is used to remove one or more records from a table.
```sql
DELETE FROM table_name
WHERE condition;

-- ⚠️ Important: Using DELETE without WHERE will remove all data from the table.
```

**🧾 Example 1 – Delete a specific record:**
```sql
DELETE FROM Employees
WHERE EmpID = 103;
```

**🧾 Example 2 – Delete multiple records:**
```sql
DELETE FROM Employees
WHERE City = 'Delhi';
```

**🧾 Example 3 – Delete all records from a table:**
```sql
DELETE FROM Employees;
```

**Comparison with TRUNCATE (for deletion)**
| Command    | Deletes All Rows | Can Use WHERE | Resets Auto-Increment | Faster |
| ---------- | ---------------- | ------------- | --------------------- | ------ |
| `DELETE`   | ✅                | ✅             | ❌                     | Slower |
| `TRUNCATE` | ✅                | ❌             | ✅                     | Faster |


