## Hands on Example-1

- 1. Create Tables
```sql
-- Customers Table
CREATE TABLE Customers (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Products Table
CREATE TABLE Products (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(100) NOT NULL,
    Price DECIMAL(10, 2) CHECK (Price > 0),
    Stock INT DEFAULT 10
);

-- Orders Table
CREATE TABLE Orders (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT,
    OrderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

```
<img src = "https://sdmntprwestus.oaiusercontent.com/files/00000000-d190-6230-b81f-dfbcfecd92e4/raw?se=2025-06-14T10%3A01%3A09Z&sp=r&sv=2024-08-04&sr=b&scid=71308403-accc-5aba-8059-370789880ddd&skoid=61180a4f-34a9-42b7-b76d-9ca47d89946d&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-06-13T11%3A13%3A37Z&ske=2025-06-14T11%3A13%3A37Z&sks=b&skv=2024-08-04&sig=lpTCQfkqcau671iKwDbRTKWu6GS5P6pITIEd/1e7YCk%3D" height="600" width="600">

- 2. Insert Customers (Basic, Multiple Rows, Default)
```sql
-- Insert a single customer
INSERT INTO Customers (Name, Email)
VALUES ('Alice', 'alice@example.com');

-- Insert multiple customers
INSERT INTO Customers (Name, Email)
VALUES 
('Bob', 'bob@example.com'),
('Charlie', NULL);  -- Email optional (NULL)
```

- 3. Insert Products (Default Stock, CHECK, NULL)
```sql
-- Insert with default stock
INSERT INTO Products (Name, Price)
VALUES ('Keyboard', 19.99);  -- Stock = 10

-- Insert with explicit stock and NULL
INSERT INTO Products (Name, Price, Stock)
VALUES ('Mouse', 14.99, NULL);  -- Stock is NULL (allowed)

-- Insert multiple products
INSERT INTO Products (Name, Price, Stock)
VALUES 
('Monitor', 199.99, 5),
('USB Cable', 5.49, 100);
```

- 4. Insert Order for a Customer (Foreign Key, Auto Timestamp)

```sql
-- Alice has CustomerID = 1 (AUTO_INCREMENT)
INSERT INTO Orders (CustomerID)
VALUES (1);
```

- 5. Insert from Another Table (Customers without email → Email Archive)
```sql
-- Create email archive table
CREATE TABLE EmailArchive (
    CustomerID INT,
    Email VARCHAR(100)
);

-- Insert customers with known emails
INSERT INTO EmailArchive (CustomerID, Email)
SELECT CustomerID, Email
FROM Customers
WHERE Email IS NOT NULL;
```

- 6. INSERT IGNORE and ON DUPLICATE
```sql
-- Try inserting duplicate email (will be ignored)
INSERT IGNORE INTO Customers (Name, Email)
VALUES ('Alice Clone', 'alice@example.com');  -- Ignored due to UNIQUE constraint

-- Insert or update if email already exists
INSERT INTO Customers (Name, Email)
VALUES ('Alice', 'alice@example.com')
ON DUPLICATE KEY UPDATE Name = 'Alice Updated';
```

- Verification Queries
```sql
-- View customers
SELECT * FROM Customers;

-- View products
SELECT * FROM Products;

-- View orders
SELECT * FROM Orders;

-- View email archive
SELECT * FROM EmailArchive;

```
-- -
## Hands-on Example-2

- Step 1: Create Table
```sql
CREATE TABLE Orders (
    OrderID INT,
    CustomerName VARCHAR(100),
    Region VARCHAR(50),
    Product VARCHAR(100),
    Quantity INT,
    Price DECIMAL(10,2),
    OrderDate DATE
);
```

- Step 2: Insert Sample Data
```sql
INSERT INTO Orders (OrderID, CustomerName, Region, Product, Quantity, Price, OrderDate) VALUES
(1, 'Alice',   'East',  'Laptop',   2, 800.00, '2024-11-01'),
(2, 'Bob',     'West',  'Monitor',  1, 200.00, '2024-11-03'),
(3, 'Charlie', 'North', 'Laptop',   1, 800.00, '2024-11-04'),
(4, 'Alice',   'East',  'Keyboard', 3,  50.00, '2024-11-05'),
(5, 'Bob',     'West',  'Mouse',    4,  25.00, '2024-11-07'),
(6, 'Eve',     'South', 'Monitor',  2, 200.00, '2024-11-10'),
(7, 'Frank',   'North', 'Laptop',   1, 800.00, '2024-11-15');
```

#### Queries 
- Get all orders where quantity > 2 and region is 'East'
```sql
SELECT * FROM Orders
WHERE Quantity > 2 AND Region = 'East';
```

- Customer names starting with 'A'
```sql
SELECT * FROM Orders
WHERE CustomerName LIKE 'A%';
```
- Orders from specific regions
```sql
SELECT * FROM Orders
WHERE Region IN ('East', 'North');
```

- Orders between specific dates
```sql
SELECT * FROM Orders
WHERE OrderDate BETWEEN '2024-11-01' AND '2024-11-10';
```

- Total quantity and average price of all orders
```sql
SELECT SUM(Quantity) AS TotalQuantity, AVG(Price) AS AveragePrice
FROM Orders;
```

- Get the 3 most expensive orders
```sql
SELECT * FROM Orders
ORDER BY Price DESC
LIMIT 3;
```
- Total sales (quantity * price) per product
```sql
SELECT Product, SUM(Quantity * Price) AS TotalSales
FROM Orders
GROUP BY Product;
```

- Show products with total sales greater than 500
```sql
SELECT Product, SUM(Quantity * Price) AS TotalSales
FROM Orders
GROUP BY Product
HAVING SUM(Quantity * Price) > 500;
```

- Get top 3 products (alphabetically) from the North and East regions, where total sales are more than 300, ordered by product name.
```sql
SELECT Product, SUM(Quantity * Price) AS TotalSales
FROM Orders
WHERE Region IN ('North', 'East')
GROUP BY Product
HAVING SUM(Quantity * Price) > 300
ORDER BY Product ASC
LIMIT 3;
```