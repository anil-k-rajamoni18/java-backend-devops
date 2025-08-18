## Level 1: Spring Data Basics

**Goal:** Understand entity mapping, repositories, and basic CRUD.

### 1. Create a Simple Entity
- Create a `Product` entity with fields: `id`, `name`, `price`.
- Use `@Entity` and `@Id` with `@GeneratedValue`.
- Map to a `products` table.

### 2. Basic Repository Interface
- Create a `ProductRepository` interface extending `JpaRepository<Product, Long>`.
- Test basic CRUD operations using Spring Boot test or a simple command-line runner.

### 3. Application Startup Data
- Use `CommandLineRunner` to insert a few products on application startup.
- Fetch and print all products.

---

## Level 2: Query Methods & Pagination

**Goal:** Use Spring Data query derivation and pagination.

### 4. Query Method by Naming Convention
- Add a method `List<Product> findByName(String name)` in `ProductRepository`.
- Test it with multiple records.

### 5. Custom Query with `@Query`
- Create a method to find products with price greater than a given value.
- Use JPQL or native SQL with `@Query`.

### 6. Pagination and Sorting
- Use `Pageable` to fetch products in pages of size 2.
- Sort by `price` in descending order.

---

## Level 3: Relationships & Transactions

**Goal:** Work with entity relationships and transaction management.

### 7. One-to-Many Relationship
- Create a `Category` entity with `id`, `name`.
- A category can have multiple products.
- Map with `@OneToMany` and `@ManyToOne`.

### 8. Transactional Service Layer
- Create a `ProductService` with a `@Transactional` method.
- In the method, insert a product and intentionally throw an exception.
- Verify rollback behavior.

### 9. Lazy Loading vs Eager Loading
- Configure relationships to use lazy loading.
- Fetch a category and try accessing its products after closing the session to see `LazyInitializationException`.

---

## Level 4: Advanced Spring Data Features

**Goal:** Use projections, specifications, and auditing.

### 10. Interface-based Projections
- Create a projection `ProductView` with `getName()` and `getPrice()`.
- Return only these fields from a query method.

### 11. Spring Data Specifications
- Implement `Specification<Product>` to search by multiple dynamic criteria (e.g., name contains, price range).
- Test with `JpaSpecificationExecutor`.

### 12. Auditing Fields
- Add `createdDate` and `lastModifiedDate` to `Product`.
- Enable JPA auditing with `@EnableJpaAuditing`.
- Verify automatic updates.# 🟢 Level 1: Spring Data Basics


## 💡 Bonus Challenge: Mini Project

**Goal:** Combine all concepts into a small app.

Create an **Order Management System**:
- Entities: `Order`, `Customer`, `OrderItem`, `Product`.
- Relationships: `Customer` → `Order` (One-to-Many), `Order` → `OrderItem` (One-to-Many), `OrderItem` → `Product` (Many-to-One).
- Features:
  - Insert sample data at startup.
  - Use custom queries to get orders for a given customer.
  - Paginate order listings.
  - Use projections to show only necessary fields in reports.
  - Enable auditing for creation/update timestamps.
