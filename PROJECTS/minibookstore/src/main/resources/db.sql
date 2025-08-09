-- Create database
CREATE DATABASE IF NOT EXISTS bookstore;
USE bookstore;

-- Create books table
CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

-- Sample data
INSERT INTO books (title, author, price) VALUES
('Effective Java', 'Joshua Bloch', 45.99),
('Clean Code', 'Robert C. Martin', 39.99),
('Head First Java', 'Kathy Sierra', 29.50),
('Java: The Complete Reference', 'Herbert Schildt', 49.00);



CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(10) NOT NULL
);