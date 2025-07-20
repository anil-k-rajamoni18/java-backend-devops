# 🧠 SQL JOINs

**📌 What is a JOIN in SQL?**
- A JOIN in SQL is used to combine rows from two or more tables, based on a related column between them.

**📌 Why Use JOINs?**
- To retrieve meaningful information from multiple related tables.
- Reduce data duplication via normalization, and reassemble the data through JOINs.
- Perform analytics, generate reports, and handle complex queries involving multiple entities.

**🔹 Real-World Example:**
- In a company:
    - Employee data is in the employees table.
    - Department info is in the departments table.

- If you want to know which employee works in which department, you need to JOIN those tables.

**📌 Where Are JOINs Used?**
- Dashboard and report generation (e.g., employee names with department names).
- Business logic (e.g., calculating total orders per customer).
- API integrations and backend queries (e.g., show user profile with account history).

## 📌 Types of SQL JOINs

**SQL Table Structure and Test Data**
```sql
CREATE TABLE departments (
    id INT PRIMARY KEY AUTO_INCREMENT,
    department_name VARCHAR(100) NOT NULL
);

INSERT INTO departments (department_name) VALUES
('HR'),
('Engineering'),
('Marketing'),
('Finance');

CREATE TABLE employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    salary DECIMAL(10, 2),
    dept_id INT,
    manager_id INT,
    FOREIGN KEY (dept_id) REFERENCES departments(id),
    FOREIGN KEY (manager_id) REFERENCES employees(id)
);

INSERT INTO employees (name, email, salary, dept_id, manager_id) VALUES
('Alice',   'alice@company.com',   60000, 1, NULL),     -- HR
('Bob',     'bob@company.com',     55000, NULL, 1),     -- No department
('Charlie', 'charlie@company.com', 75000, 2, 1),        -- Engineering
('David',   'david@company.com',   50000, 3, 3),        -- Marketing
('Eve',     'eve@company.com',     80000, 2, 1),        -- Engineering
('Frank',   'frank@company.com',   40000, 4, NULL);     -- Finance


```
### ✔️ 1. INNER JOIN
- Returns only the rows with matching keys in both tables.
- Most commonly used join.
```sql
SELECT e.name, d.department_name
FROM employees e
INNER JOIN departments d
ON e.dept_id = d.id;

-- Only employees who are assigned to a valid department will be shown.
```

### ✔️ 2. LEFT JOIN (LEFT OUTER JOIN)
- Returns all rows from the left table and the matching rows from the right table.
- If there’s no match, NULLs are shown from the right table.
```sql
SELECT e.name, d.department_name
FROM employees e
LEFT JOIN departments d
ON e.dept_id = d.id;

--  Shows all employees, even if they don't belong to any department.
```

### ✔️ 3. RIGHT JOIN (RIGHT OUTER JOIN)
- Opposite of LEFT JOIN.
- Returns all rows from the right table and matching rows from the left.
```sql
SELECT e.name, d.department_name
FROM employees e
RIGHT JOIN departments d
ON e.dept_id = d.id;

--  Shows all departments, even if no employee is assigned to them.
```

### ✔️ 4. FULL OUTER JOIN (Not directly supported in MySQL)
- Returns all rows from both tables, matching where possible.
- Use UNION of LEFT and RIGHT JOIN in MySQL.
```sql
SELECT e.name, d.department_name
FROM employees e
LEFT JOIN departments d ON e.dept_id = d.id
UNION
SELECT e.name, d.department_name
FROM employees e
RIGHT JOIN departments d ON e.dept_id = d.id;
```

### ✔️ 5. SELF JOIN
- A table joined with itself.
```sql
SELECT a.name AS employee, b.name AS manager
FROM employees a
JOIN employees b
ON a.manager_id = b.id;

--  Find who reports to whom within the same employees table.
```

### ✔️ 6. CROSS JOIN
- Cartesian product of two tables.
- Each row from the first table is paired with every row from the second table.
```sql
SELECT color.name, size.name
FROM color
CROSS JOIN size;
```

----
# 📘 Subqueries / Nested Queries

**🧠 What is a Subquery?**
- A subquery (also called a nested query) is a query inside another query.
    - It can return a single value, a row, or a table.
    - Often used to filter, compute, or derive data based on dynamic logic.

**❓Why Use Subqueries?**
- To break down complex logic into manageable pieces.
- To avoid temporary tables for intermediate results.
- For comparisons (e.g., against aggregated values).
- To encapsulate reusable conditions in reporting, filtering, and calculations.

**📍 Where Can You Use Subqueries?**
| Location                     | Use Case Example              |
| ---------------------------- | ----------------------------- |
| `WHERE` clause               | Filtering rows                |
| `FROM` clause                | Derived tables                |
| `SELECT` clause              | Inline calculations           |
| `HAVING` clause              | Filter group results          |
| `INSERT`, `UPDATE`, `DELETE` | Modify using subquery results |


## 🧩 Types of Subqueries
### 🔹 1. Single Row Subquery
- Returns one value or row.
```sql
SELECT name, salary
FROM employees
WHERE salary > (
    SELECT AVG(salary)
    FROM employees
);
```

### 🔹 2. Multiple Row Subquery
- Returns multiple values or rows.

```sql
SELECT name
FROM employees
WHERE department_id IN (
    SELECT id FROM departments WHERE location = 'New York'
);
```

### 🔹 3. Correlated Subquery
- References columns from the outer query. Runs once for every row in the outer query.
```sql
SELECT name
FROM employees e
WHERE salary > (
    SELECT AVG(salary)
    FROM employees
    WHERE department_id = e.department_id
);
```

### 4. Subquery in FROM Clause (Derived Table / Inline View)
```sql
SELECT department_id, AVG(salary) as avg_salary
FROM (
    SELECT department_id, salary
    FROM employees
    WHERE active = 1
) AS active_emps
GROUP BY department_id;
```

### 🔹 5. Subquery in SELECT Clause
```java
SELECT name,
       (SELECT COUNT(*) FROM projects WHERE emp_id = employees.id) AS project_count
FROM employees;
```

**🧪 Test Data**
```sql
-- Drop if exists
DROP TABLE IF EXISTS employees, departments, projects;

-- Create departments
CREATE TABLE departments (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    location VARCHAR(50)
);

-- Insert departments
INSERT INTO departments VALUES
(1, 'HR', 'New York'),
(2, 'IT', 'San Francisco'),
(3, 'Finance', 'New York');

-- Create employees
CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    salary DECIMAL(10,2),
    department_id INT,
    active BOOLEAN,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

-- Insert employees
INSERT INTO employees VALUES
(101, 'Alice', 60000, 1, 1),
(102, 'Bob', 75000, 2, 1),
(103, 'Charlie', 55000, 3, 1),
(104, 'David', 82000, 2, 0),
(105, 'Eve', 40000, 1, 1);

-- Create projects
CREATE TABLE projects (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    emp_id INT,
    FOREIGN KEY (emp_id) REFERENCES employees(id)
);

-- Insert projects
INSERT INTO projects VALUES
(201, 'Website', 101),
(202, 'Mobile App', 102),
(203, 'Finance System', 103),
(204, 'Payroll System', 103),
(205, 'Cloud Infra', 104);
```

- Example 1: Employees earning more than average salary
```sql
SELECT name, salary
FROM employees
WHERE salary > (
    SELECT AVG(salary)
    FROM employees
);
```

- Example 2: Employees in departments located in New York
```sql
SELECT name
FROM employees
WHERE department_id IN (
    SELECT id FROM departments WHERE location = 'New York'
);
```

- Example 3: Department-wise average salary (Derived Table)
```sql
SELECT department_id, AVG(salary) as avg_salary
FROM (
    SELECT department_id, salary
    FROM employees
    WHERE active = 1
) AS active_emps
GROUP BY department_id;
```

- Example 4: Count projects per employee (Subquery in SELECT)
```sql
SELECT name,
       (SELECT COUNT(*) FROM projects WHERE emp_id = employees.id) AS project_count
FROM employees;
```

- Example 5: Correlated Subquery – Employees earning above their department’s average
```sql
SELECT name, salary
FROM employees e
WHERE salary > (
    SELECT AVG(salary)
    FROM employees
    WHERE department_id = e.department_id
);
```

**Summary Table**
| Type of Subquery    | Used In       | Returns        | Use Case Example |
| ------------------- | ------------- | -------------- | ---------------- |
| Single-row Subquery | WHERE, SELECT | One row/value  | Compare to AVG   |
| Multi-row Subquery  | IN, EXISTS    | List of values | Filter rows      |
| Correlated Subquery | WHERE         | Depends on row | Row-wise logic   |
| Subquery in SELECT  | SELECT        | Value per row  | Count, compute   |
| Subquery in FROM    | FROM          | Derived table  | Inline dataset   |


---
# 🧠 Stored Procedures & Functions in SQL

**📌 Stored Procedure:**
- A stored procedure is a precompiled group of SQL statements stored on the server and executed as a program.
> Think of it like a backend function in an application — but running inside the database.

**📌 Function:**
> A function is a subprogram that returns a single value and can be used within SQL expressions.


### **Why Use Stored Procedures and Functions?**
| Feature                   | Stored Procedure                        | Function                              |
| ------------------------- | --------------------------------------- | ------------------------------------- |
| Reusability               | Yes                                     | Yes                                   |
| Returns Value             | Optional (can return multiple rows)     | Mandatory (returns a scalar value)    |
| Use in SQL expressions    | ❌ No                                    | ✅ Yes                                 |
| Modularity & Maintainable | ✅ Break complex logic into components   | ✅ Abstract repeated expressions       |
| Performance               | ✅ Faster for repeated tasks             | ✅ Cached result for same input        |
| Security                  | ✅ Permissions can be limited to execute | ✅ Cannot modify data (pure functions) |


### **🔹 Where Are They Used?**
- ✅ Use Stored Procedures:
    - Business logic (e.g., payroll processing)
    - Batch operations (e.g., update salaries)
    - Automated tasks (e.g., archive old logs)
    - API endpoints (via database connector)

- ✅ Use Functions:
    - Column calculations (e.g., full name, tax)
    - Filters (e.g., WHERE age = GetAge())
    - Derived fields in reports


### 🔹 Types

**📘 Stored Procedures**
| Type          | Description                                        |
| ------------- | -------------------------------------------------- |
| Simple        | Just executes queries                              |
| Parameterized | Accepts `IN`, `OUT`, or `INOUT` parameters         |
| Transactional | Includes `START TRANSACTION`, `COMMIT`, `ROLLBACK` |
| Cursor-based  | Iterates through rows using CURSORs                |


**📘 Functions**
| Type              | Description                                                |
| ----------------- | ---------------------------------------------------------- |
| Scalar Function   | Returns one value (most common)                            |
| Deterministic     | Returns same output for same input (important for caching) |
| Non-deterministic | Uses random, time, etc. (not cacheable)                    |


**🧪 Test Data**
```sql
CREATE DATABASE company_db;
USE company_db;

CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    department VARCHAR(100),
    salary DECIMAL(10, 2),
    hire_date DATE
);

INSERT INTO employees (first_name, last_name, department, salary, hire_date)
VALUES 
('John', 'Doe', 'HR', 50000, '2020-01-15'),
('Jane', 'Smith', 'IT', 80000, '2019-03-10'),
('Bob', 'Johnson', 'Finance', 60000, '2021-07-01'),
('Alice', 'Williams', 'IT', 75000, '2020-11-20');

```

### 🛠 Stored Procedure – Examples

**1. Simple Procedure**
```sql
DELIMITER $$

CREATE PROCEDURE GetAllEmployees()
BEGIN
    SELECT * FROM employees;
END $$

DELIMITER ;

CALL GetAllEmployees();
```

**2. Parameterized Procedure**
```sql
DELIMITER $$

CREATE PROCEDURE GetEmployeeByDept(IN dept_name VARCHAR(100))
BEGIN
    SELECT * FROM employees WHERE department = dept_name;
END $$

DELIMITER ;

CALL GetEmployeeByDept('IT');
```

**3. Update Procedure with Logic**
```sql
DELIMITER $$

CREATE PROCEDURE IncreaseSalary(IN dept_name VARCHAR(100), IN percent INT)
BEGIN
    UPDATE employees 
    SET salary = salary + (salary * percent / 100)
    WHERE department = dept_name;
END $$

DELIMITER ;

CALL IncreaseSalary('Finance', 10);
```

**4. OUT Parameter Procedure**
```sql
DELIMITER $$

CREATE PROCEDURE CountEmployeesByDept(IN dept_name VARCHAR(100), OUT emp_count INT)
BEGIN
    SELECT COUNT(*) INTO emp_count
    FROM employees
    WHERE department = dept_name;
END $$

DELIMITER ;

-- Call with variable
SET @total = 0;
CALL CountEmployeesByDept('IT', @total);
SELECT @total;
```

---
### 🧠 Function – Examples
**1. Full Name Function**
```sql
DELIMITER $$

CREATE FUNCTION GetFullName(emp_id INT)
RETURNS VARCHAR(200)
DETERMINISTIC
BEGIN
    DECLARE full_name VARCHAR(200);
    SELECT CONCAT(first_name, ' ', last_name)
    INTO full_name
    FROM employees WHERE id = emp_id;
    RETURN full_name;
END $$

DELIMITER ;

SELECT GetFullName(2); -- Output: Jane Smith
```

**2. Annual Bonus Calculator**
```sql
DELIMITER $$

CREATE FUNCTION CalculateBonus(salary DECIMAL(10,2))
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    RETURN salary * 0.10;
END $$

DELIMITER ;

SELECT first_name, salary, CalculateBonus(salary) AS bonus
FROM employees;
```

**3. Years Worked Function**
```sql
DELIMITER $$

CREATE FUNCTION YearsWorked(hire_date DATE)
RETURNS INT
DETERMINISTIC
BEGIN
    RETURN TIMESTAMPDIFF(YEAR, hire_date, CURDATE());
END $$

DELIMITER ;

SELECT first_name, YearsWorked(hire_date) AS years
FROM employees;
```

### **🔐 Best Practices**
- Prefix procedures/functions clearly (e.g., sp_, fn_).
- Avoid writing business logic in the application and in procedures — choose one.
- Use DETERMINISTIC for functions that don’t vary — helps optimizer.
- Avoid side effects in functions; they should be pure (read-only).
- Procedures can call other procedures (modularize complex workflows).

---
## 📘 Schema
- A schema in SQL is a logical container or namespace that holds database objects like:
    - Tables
    - Views
    - Indexes
    - Stored Procedures
    - Triggers
    - Functions

- In MySQL, a schema is synonymous with a database. It defines structure: tables, views, indexes, procedures, etc.

**🔹 Why Use Schema?**
| Purpose             | Description                                   |
| ------------------- | --------------------------------------------- |
| **Organization**    | Helps group related objects (e.g., HR, Sales) |
| **Security**        | Apply user permissions at schema level        |
| **Maintainability** | Easy to manage or isolate changes             |
| **Modularity**      | Enables versioning and separation of concerns |


**🔹 Schema in MySQL**
- In MySQL, a database = schema. You use CREATE SCHEMA or CREATE DATABASE.
```sql
-- Creating a new schema
CREATE SCHEMA employee_mgmt;

-- Using the schema
USE employee_mgmt;
```


---

##  INDEX in SQL
- An index in SQL is a data structure that improves the speed of data retrieval operations on a table.
- Think of it like a book’s table of contents or an index in the back of a book – it helps find data faster without scanning every page (row).

**🔹 Why Use Indexes?**
| Benefit                   | Description                              |
| ------------------------- | ---------------------------------------- |
| ✅ **Faster SELECTs**      | Speeds up reads and lookups              |
| ✅ **Efficient Filtering** | Improves WHERE, JOIN, ORDER BY, GROUP BY |
| ✅ **Better Performance**  | Especially in large tables               |


**⚠️ Index Trade-Offs**
| Drawback                   | Description                                    |
| -------------------------- | ---------------------------------------------- |
| ❌ **Slower INSERT/UPDATE** | Index must be updated each time                |
| ❌ **Extra Storage**        | Indexes consume disk space                     |
| ❌ **Too Many Indexes**     | Can reduce performance instead of improving it |

**🔹 Where to Use Indexes?**
- Columns used in WHERE, JOIN, ORDER BY, or GROUP BY
- Foreign keys and primary keys are usually indexed automatically

**🔹 Types of Indexes in SQL**
| Type                    | Description                                                   |
| ----------------------- | ------------------------------------------------------------- |
| **Primary Index**       | Automatically created with PRIMARY KEY                        |
| **Unique Index**        | Ensures all values are unique                                 |
| **Composite Index**     | Multi-column index                                            |
| **Full-Text Index**     | For searching large text (MySQL-specific)                     |
| **Spatial Index**       | For geographic data types                                     |
| **Clustered Index**     | Data stored in index order (Only in some DBs like SQL Server) |
| **Non-Clustered Index** | Separate from the actual table data                           |


**🔹 Creating Indexes**
```sql
-- Create a normal index
CREATE INDEX idx_product_name ON products(name);

-- Unique index
CREATE UNIQUE INDEX idx_unique_email ON users(email);

-- Composite index (multi-column)
CREATE INDEX idx_order_lookup ON orders(product_id, order_date);
```

**🔹 Dropping an Index**
```sql
DROP INDEX idx_product_name ON products;
```

### 🧪 Test Data
```sql
CREATE DATABASE test_store;
USE test_store;

CREATE TABLE customers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    email VARCHAR(100),
    city VARCHAR(50)
);

CREATE TABLE purchases (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    product_name VARCHAR(100),
    purchase_date DATE,
    amount DECIMAL(10, 2),
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);


---
INSERT INTO customers (name, email, city)
VALUES 
('Alice', 'alice@example.com', 'New York'),
('Bob', 'bob@example.com', 'Chicago'),
('Carol', 'carol@example.com', 'Los Angeles');

INSERT INTO purchases (customer_id, product_name, purchase_date, amount)
VALUES
(1, 'Laptop', '2024-12-01', 900.00),
(2, 'Phone', '2024-12-03', 600.00),
(1, 'Tablet', '2024-12-05', 300.00),
(3, 'Monitor', '2024-12-08', 200.00);


---
-- Add index on customer_id for faster JOINs
CREATE INDEX idx_customer_id ON purchases(customer_id);

-- Speed up filtering by city
CREATE INDEX idx_city ON customers(city);

-- Analyze with EXPLAIN
EXPLAIN SELECT * 
FROM purchases
JOIN customers ON purchases.customer_id = customers.id
WHERE customers.city = 'New York';

```

----
## 🔶 Views in SQL
- A view is a virtual table that is based on the result of a SQL SELECT query. 
- It doesn’t store data itself, but fetches data from underlying base tables every time it is queried.

**🎯 Why Use Views?**
| Purpose          | Description                                                    |
| ---------------- | -------------------------------------------------------------- |
| ✅ Abstraction    | Hide complex joins and subqueries                              |
| ✅ Security       | Restrict access to specific columns/rows                       |
| ✅ Reusability    | Reuse complex SQL logic                                        |
| ✅ Simplification | Present clean, business-friendly data models                   |
| ✅ Maintenance    | If logic changes, update view instead of modifying every query |


**🌍 Where Are Views Used?**
- Business dashboards
- Reporting layers (BI tools)
- Access control (limit exposure to sensitive data)
- Logical separation (multi-tenant applications)


**📂 Types of Views**
| Type               | Description                                       |
| ------------------ | ------------------------------------------------- |
| **Simple View**    | Based on a single table, no aggregation           |
| **Complex View**   | Based on multiple tables with joins, aggregations |
| **Updatable View** | Allows `INSERT`, `UPDATE`, `DELETE` (with limits) |
| **Non-Updatable**  | Uses joins, group by, or set functions            |


**🔬 Test Data: Employees and Departments**
```sql
CREATE TABLE departments (
    id INT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    salary DECIMAL(10,2),
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES departments(id)
);

INSERT INTO departments VALUES (1, 'Engineering'), (2, 'HR');

INSERT INTO employees VALUES
(101, 'Alice', 70000, 1),
(102, 'Bob', 80000, 1),
(103, 'Carol', 60000, 2);
```

**Example 1: Simple View**
```sql
CREATE VIEW engineering_employees AS
SELECT name, salary
FROM employees
WHERE dept_id = 1;

--- Usage 
SELECT * FROM engineering_employees;
```

**📌 Example 2: View with Join**
```sql
CREATE VIEW employee_details AS
SELECT e.name, e.salary, d.name AS department
FROM employees e
JOIN departments d ON e.dept_id = d.id;
```

**📌 Example 3: View with Aggregation (Non-updatable)**
```sql
CREATE VIEW avg_salary_per_dept AS
SELECT dept_id, AVG(salary) AS avg_salary
FROM employees
GROUP BY dept_id;
```

**🔒 Security Use Case:**
- Restrict access to only names and departments, not salary
```sql
CREATE VIEW public_employee_info AS
SELECT e.name, d.name AS department
FROM employees e
JOIN departments d ON e.dept_id = d.id;
```

---
## Triggers in SQL
> A trigger is a stored procedure that automatically runs when an event (like INSERT, UPDATE, or DELETE) occurs on a table.


**Why Use Triggers?**
| Purpose             | Description                                |
| ------------------- | ------------------------------------------ |
| ✅ Audit             | Automatically log changes to tables        |
| ✅ Validation        | Prevent invalid data before insert/update  |
| ✅ Cascading Actions | Automatically update related tables        |
| ✅ Automation        | Calculate or populate fields automatically |


**Where Are Triggers Used?**
- Audit logging (who changed what and when)
- Enforcing business rules (e.g., salary can’t go below minimum)
- Auto-calculating derived columns
- Synchronizing related data

**⚡ Trigger Types in MySQL**
| Trigger Type  | Timing        | Description                           |
| ------------- | ------------- | ------------------------------------- |
| BEFORE INSERT | Before insert | Modify or validate data before insert |
| AFTER INSERT  | After insert  | Auto-log or update related tables     |
| BEFORE UPDATE | Before update | Validate new data                     |
| AFTER UPDATE  | After update  | Auto-log or replicate changes         |
| BEFORE DELETE | Before delete | Restrict deletes                      |
| AFTER DELETE  | After delete  | Log deletions                         |


**🔬 Test Data: Audit Table**
```sql
CREATE TABLE employee_audit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    emp_id INT,
    old_salary DECIMAL(10,2),
    new_salary DECIMAL(10,2),
    changed_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

**📌 Example 1: AFTER UPDATE Trigger (Audit Salary Changes)**
```sql
DELIMITER $$

CREATE TRIGGER audit_salary_change
AFTER UPDATE ON employees
FOR EACH ROW
BEGIN
    IF OLD.salary <> NEW.salary THEN
        INSERT INTO employee_audit (emp_id, old_salary, new_salary)
        VALUES (OLD.id, OLD.salary, NEW.salary);
    END IF;
END $$

DELIMITER ;
```

```sql
UPDATE employees SET salary = 85000 WHERE id = 102;

SELECT * FROM employee_audit;
```

**📌 Example 2: BEFORE INSERT Trigger (Validate Salary > 0)**
```sql
DELIMITER $$

CREATE TRIGGER check_salary
BEFORE INSERT ON employees
FOR EACH ROW
BEGIN
    IF NEW.salary <= 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Salary must be greater than zero';
    END IF;
END $$

DELIMITER ;
```

**📌 Example 3: BEFORE DELETE (Prevent HR Deletion)**
```sql
DELIMITER $$

CREATE TRIGGER prevent_hr_deletion
BEFORE DELETE ON employees
FOR EACH ROW
BEGIN
    IF OLD.dept_id = 2 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Cannot delete HR employees';
    END IF;
END $$

DELIMITER ;
```

### 🧠 Best Practices
- ✅ Views
    - Name views clearly (e.g., vw_, public_)
    - Avoid using views for performance-critical queries with aggregation
    - Don’t chain too many nested views (can degrade performance)

- ✅ Triggers
    - Keep logic simple and short
    - Document clearly – triggers are "invisible" and often forgotten
    - Avoid using triggers for critical business logic (prefer application code)

**📌 Summary Table**
| Concept    | Views                                 | Triggers                             |
| ---------- | ------------------------------------- | ------------------------------------ |
| Definition | Virtual table from SELECT query       | Event-based procedure on data change |
| Types      | Simple, Complex, Updatable            | BEFORE/AFTER INSERT, UPDATE, DELETE  |
| Use Cases  | Reporting, Security, Data Abstraction | Logging, Validation, Automation      |
| Limitation | No physical data, slower with joins   | Hidden logic, hard to debug and test |


