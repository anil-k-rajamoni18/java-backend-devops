## Spring Data JPA

### 1️⃣ JDBC (Java Database Connectivity) 🖧
- Low-level API to connect Java apps to databases.
- Requires writing SQL manually.
- Steps:
    - Load Driver
    - Establish Connection
    - Create Statement
    - Execute Query
    - Process Results
    - Close Resources

- Example:
```java
Connection con = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/schooldb", "root", "1234");

Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM students");

while(rs.next()) {
    System.out.println(rs.getString("name"));
}
con.close();
```

**⚠️ Limitations of JDBC:**
- Too much boilerplate.
- Hard-coded SQL.
- No Object mapping (manual conversion of ResultSet to Java objects).


*JDBC flow: Java App → JDBC Driver → Database.*

---
### 2️⃣ Hibernate (ORM Framework) 🔄
- An ORM (Object Relational Mapping) framework.
- Maps Java objects ↔ Database tables.
- Removes boilerplate JDBC code.
- Key Features:
    - Automatic SQL generation.
    - Caching.
    - Supports relationships.

- Example Entity (Hibernate):
```java
@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
}
```

*Hibernate mapping: Student.class ↔ students table.*

---
### 3️⃣ JPA (Java Persistence API) 🏛️
- Specification (not implementation).
- Defines annotations (@Entity, @Id, etc.).
- Hibernate is the most popular implementation.

**Why JPA?**
- Standardization.
- Decouples code from specific ORM providers.

- Example:
```java
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);
}
```

*JPA = Contract, Hibernate = Implementation.*

---
### 4️⃣ Spring Data JPA 🌱
- A part of Spring Data project.
- A Spring project that builds on JPA and makes repository abstraction super easy
- Helps us work with databases using JPA (Java Persistence API).
- Removes the need to write lots of boilerplate code (DAOs, SQL).
- Just define an interface (repository), Spring Data JPA creates the database queries for you.
- Benefits:
    - Reduces DAO layer boilerplate.
    - CRUD methods auto-implemented.
    - Supports derived queries, JPQL, native queries.

![](https://miro.medium.com/v2/resize:fit:1200/1*82ysQBEAXL13t3mUm-Q6wQ.png)

**Repositories Provided**
- CrudRepository → Basic CRUD (Create, Read, Update, Delete).
- JpaRepository → Adds pagination, sorting, batch operations.
- PagingAndSortingRepository → Pagination + sorting support.


**Key Features of Spring Data JPA:**

`1. Repository Abstractions`
- Provides CrudRepository, JpaRepository, and PagingAndSortingRepository to handle common database operations (CRUD, pagination, sorting).

```java
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByLastName(String lastName);
}
```
- Spring Data JPA will automatically generate the query for findByLastName.

`2. Derived Query Methods`
- You can write methods like findByEmail, countByStatus, deleteByAgeGreaterThan, and Spring will generate queries automatically.

`3. Custom Queries`
- If derived queries aren’t enough, you can use JPQL or native SQL via the @Query annotation.
```less
@Query("SELECT u FROM User u WHERE u.email = ?1")
User findByEmail(String email);
```

`4. Pagination and Sorting`
- Built-in support with Pageable and Sort objects.
```java
Page<User> findByActive(boolean active, Pageable pageable);
```

`5. Auditing`
- Automatic handling of createdDate, lastModifiedDate, createdBy, and lastModifiedBy.

`6. Integration with Spring`
- Works seamlessly with Spring Boot → just define an interface and Spring Boot auto-configures the repository.


**Why use Spring Data JPA?**
- Less boilerplate: No need to implement DAOs manually.
- Readable queries: Derived query methods look like natural language.
- Scalable: Handles complex queries, joins, projections, and specifications.
- Testable: Repository interfaces are easy to mock in tests.

---
## 📌 JPA Annotations (Standard)

#### 🔹 Entity & Table Mapping
- `@Entity` → Marks a class as a JPA entity (mapped to a DB table).  
- `@Table(name="...")` → Specifies the table name (default = class name).  

#### 🔹 Primary Key
- `@Id` → Marks a field as the primary key.  
- `@GeneratedValue` → Defines how primary key values are generated.  
  **Strategies**:  
  - `GenerationType.AUTO` → Let Hibernate decide.  
  - `GenerationType.IDENTITY` → Auto-increment (DB handles it).  
  - `GenerationType.SEQUENCE` → Uses a sequence (mainly Oracle, PostgreSQL).  
  - `GenerationType.TABLE` → Uses a separate table to generate ids.  

- `@SequenceGenerator` → Defines a DB sequence for IDs.  
- `@TableGenerator` → Defines a table for ID generation.  

#### 🔹 Column Mapping
- `@Column(name="...")` → Maps a field to a column (default = field name).  
  Options: `nullable`, `unique`, `length`, `precision`, `scale`.  
- `@Lob` → Large Object (CLOB/BLOB).  
- `@Transient` → Field is ignored (not persisted).  
- `@Enumerated(EnumType.STRING)` → Stores enums as string or ordinal.  
- `@Temporal` (old, for `Date`) → Maps `java.util.Date` / `Calendar`.  
  Values: `DATE`, `TIME`, `TIMESTAMP`.  

#### 🔹 Relationships
- `@OneToOne` → One entity relates to one entity.  
- `@OneToMany` → One entity relates to many entities.  
- `@ManyToOne` → Many entities relate to one entity.  
- `@ManyToMany` → Many entities relate to many entities.  

**Extras**:  
- `mappedBy` → Defines the owner of the relationship.  
- `cascade` → Defines cascade operations (`PERSIST`, `MERGE`, `REMOVE`, etc.).  
- `fetch` → Defines fetch type:  
  - `EAGER` (load immediately)  
  - `LAZY` (load on demand)  

#### 🔹 Join & Mapping Tables
- `@JoinColumn(name="...")` → Defines foreign key column.  
- `@JoinTable` → Defines join table for many-to-many.  

#### 🔹 Inheritance
- `@Inheritance(strategy=...)` → Defines inheritance mapping.  
  - `SINGLE_TABLE` → All classes in one table (with a discriminator column).  
  - `JOINED` → Separate tables joined by PK.  
  - `TABLE_PER_CLASS` → Each class gets its own table.  

- `@DiscriminatorColumn` → Column to differentiate subclasses.  
- `@DiscriminatorValue` → Value stored for subclass.  

#### 🔹 Other
- `@Embedded` → Marks a value object (reusable fields).  
- `@Embeddable` → Used in reusable classes (no separate table).  
- `@ElementCollection` → Collection of basic or embeddable types.  
- `@Version` → Optimistic locking (adds version column).  

---

## 📌 Hibernate-specific Annotations (Extra Features)

#### 🔹 ID & Primary Key
- `@GenericGenerator` → Custom ID generator.  
- `@NaturalId` → Marks a field as a natural (business) key.  

#### 🔹 Column & Table
- `@Formula` → Maps a field to an SQL formula (calculated column).  
- `@CreationTimestamp` → Auto-set field when row is created.  
- `@UpdateTimestamp` → Auto-update field when row is updated.  
- `@ColumnDefault` → Sets default column value in DB.  
- `@DynamicInsert` / `@DynamicUpdate` → SQL includes only changed columns.  
- `@Immutable` → Entity is read-only.  

#### 🔹 Fetching & Performance
- `@BatchSize` → Defines batch size for lazy loading.  
- `@Fetch(FetchMode.JOIN / SELECT / SUBSELECT)` → Custom fetch strategy.  
- `@LazyCollection(LazyCollectionOption.TRUE/FALSE)` → Hibernate lazy loading.  

#### 🔹 Caching
- `@Cache` → Enables caching for an entity or collection.  
- `@Cacheable` → Marks entity as cacheable.  

#### 🔹 Proxy & Filters
- `@Proxy(lazy=true/false)` → Enables/disables proxy.  
- `@Filter` / `@FilterDef` → Dynamic filtering of entities.  
- `@Where` → Adds an SQL WHERE clause for entity/collection.  

#### 🔹 Others
- `@Type` → Maps custom Hibernate types.  
- `@Any` / `@ManyToAny` → Polymorphic associations.  
- `@Cascade` → Hibernate-specific cascade (extension of JPA’s cascade).  


---
## 📌 Spring Data JPA Properties
`1. Database Connection`
- These are actually Spring Boot + JDBC properties, but required for JPA.
- application.properties
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=secret
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

`2. Hibernate Dialect`
- In Hibernate, the dialect works like a translator, enabling Hibernate to interact seamlessly with different databases.
- A Dialect is a class that provides the mapping between Java data types (JDBC types) and database-specific SQL types.
- Tells Hibernate which SQL dialect to use (depends on DB).
```properties
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```
`3. DDL Auto (Schema Generation)`
- Controls how Hibernate handles table creation/updation.
```properties
spring.jpa.hibernate.ddl-auto=update
```
- Options:
    - none → Do nothing (default in production).
    - validate → Validate schema with entities.
    - update → Update schema automatically (safe for dev).
    - create → Drop & create tables on startup.
    - create-drop → Create tables on startup, drop on shutdown.

`4. Show SQL & Formatting`
- For debugging queries.
```properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

`5. Naming Strategy`
- Defines how entity/field names map to table/column names.
```properties
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```

`6. Open-in-View & Batch Size / Performance, Second-Level Cache`
- Keeps the Hibernate session open for the entire request (to avoid LazyInitializationException).
```properties
spring.jpa.open-in-view=true
```

- For tuning Hibernate fetch behavior.
```properties
spring.jpa.properties.hibernate.jdbc.batch_size=30
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true
```

- If caching is enabled.
```properties
spring.jpa.properties.hibernate.cache.use_second_level_cache=true
spring.jpa.properties.hibernate.cache.use_query_cache=true
```
--- 
### 🚀 Core Concepts
`1. Setup & Dependencies`
- Add dependency in build.gradle:
```gradle
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    runtimeOnly 'com.mysql:mysql-connector-j'
}
```
- Configure in application.properties:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/schooldb
spring.datasource.username=root
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

2. 🏛️ Entity Mapping
- Create an Entity (mapped to DB table).
```java
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private int age;
}
```

3. 📂 Repository Layer
- Use JpaRepository for CRUD operations.
- 👉 No SQL needed, Spring generates queries automatically!
```java
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByName(String name);
    List<Student> findByAgeGreaterThan(int age);
}
```

4. ⚡ CRUD Operations
- Create
```java
Student s = new Student("Ram", "Ram@gmail.com", 20);
studentRepository.save(s);
```

- Read
```java
List<Student> students = studentRepository.findAll();
```

- Update
```java
Student s = studentRepository.findById(1L).get();
s.setEmail("newmail@gmail.com");
studentRepository.save(s);
```

- Delete
```java
studentRepository.deleteById(1L);
```

5. 🔍 Derived Query Methods
- Spring generates queries from method names.
```java
List<Student> findByEmailContaining(String keyword);
List<Student> findByAgeBetween(int start, int end);
List<Student> findTop3ByOrderByAgeDesc();
```

6. 📝 JPQL & Native Queries
- JPQL Example
```java
@Query("SELECT s FROM Student s WHERE s.age >= :age")
List<Student> getStudentsOlderThan(@Param("age") int age);
```

- Native SQL Example
```java
@Query(value = "SELECT * FROM students WHERE name LIKE %:name%", nativeQuery = true)
List<Student> searchByName(@Param("name") String name);
```

7. 🔗 Relationships (One-to-One, One-to-Many, Many-to-Many)

    🔹 1. One-to-One
    - One record in Entity A is linked to exactly one record in Entity B.
    - Example
        - A Person has exactly one Passport. One Passport belongs to exactly one Person.

```java
@Entity
public class Person {
    @Id
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "passport_id")  // foreign key in Person table
    private Passport passport;
}

@Entity
public class Passport {
    @Id
    private Long id;
    private String passportNumber;
}

// person → has a passport_id column (FK).
// passport → primary key.
```

- 🔹 2. One-to-Many (and Many-to-One)
    - One record in Entity A can be linked to many records in Entity B, but each record in B belongs to only one record in A.
    - Example
        - A Department has many Employees. But each Employee belongs to only one Department.

```java
@Entity
public class Department {
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "department")  // one department -> many employees
    private List<Employee> employees;
}

@Entity
public class Employee {
    @Id
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")  // foreign key in Employee table
    private Department department;
}

// department → primary key.
// employee → has department_id column (FK).
```

- 🔹 3. Many-to-Many
    - Multiple records in Entity A can be linked to multiple records in Entity B.
    - Example:
        - A Student can enroll in many Courses. A Course can have many Students.

```java
@Entity
public class Student {
    @Id
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(
        name = "student_course",                   // join table
        joinColumns = @JoinColumn(name = "student_id"),   // FK to Student
        inverseJoinColumns = @JoinColumn(name = "course_id") // FK to Course
    )
    private List<Course> courses;
}

@Entity
public class Course {
    @Id
    private Long id;
    private String title;

    @ManyToMany(mappedBy = "courses")  // owned by Student
    private List<Student> students;
}
```

**🗂️ Tables**
    - student
    - course
    - student_course (join table with student_id, course_id).


8. ⚙️ Pagination & Sorting
- Pagination → Instead of fetching all rows at once, fetch results page by page (e.g., 10 users at a time).
- Sorting → Retrieve results in ascending/descending order by one or more fields.
- ✅ Helps improve performance and user experience (especially with large datasets).
- Spring Data JPA provides built-in classes for this:   
    - Pageable → Represents page info (page number, page size, sort).
    - Page<T> → Holds the result with extra metadata (total pages, total elements, current page, etc.).
    - Slice<T> → Similar to Page but lighter (doesn’t calculate total count).
    - Sort → Defines sorting order.

```java
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findByActive(boolean active, Pageable pageable);
}

// Usage in Service Layer:
Pageable pageable = PageRequest.of(0, 5); // page 0 (first), 5 records per page
Page<User> page = userRepository.findByActive(true, pageable);

System.out.println("Total Pages: " + page.getTotalPages());
System.out.println("Total Records: " + page.getTotalElements());

List<User> users = page.getContent(); // actual data

```

**Pagination with Sorting**

```java
// 👉 Fetch first 5 users sorted by name ascending.
Pageable pageable = PageRequest.of(0, 5, Sort.by("name").ascending());
Page<User> page = userRepository.findAll(pageable);

// Multiple fields sorting:
Pageable pageable = PageRequest.of(0, 5, Sort.by("lastName").descending().and(Sort.by("firstName")));
```

**Only Sorting (No Pagination)**
```java
List<User> users = userRepository.findAll(Sort.by("email").descending());
```

**Summary**
- Pagination → Load data in chunks (improves performance).
- Sorting → Order results by one or more fields.
- Use Pageable, Page<T>, and Sort in repositories.


9. 📌 Transactions in Spring Data JPA
- A transaction is a unit of work in a database that must be executed completely or not at all (all-or-nothing).
- 💡 Example: Transferring money from Account A → Account B.
    - Debit A
    - Credit B
    - If debit succeeds but credit fails → transaction rolls back (nothing is saved).


**ACID Properties of Transactions**
- Transactions ensure data consistency using ACID:
    - Atomicity → All or nothing.
    - Consistency → Database moves from one valid state to another.
    - Isolation → Multiple transactions don’t affect each other.
    - Durability → Once committed, changes are permanent.


**Spring Manages Transactions**
- Spring provides declarative transaction management using @Transactional.
```java
@Service
public class PaymentService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public void transferMoney(Long fromId, Long toId, double amount) {
        Account from = accountRepository.findById(fromId).get();
        Account to = accountRepository.findById(toId).get();

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        accountRepository.save(from);
        accountRepository.save(to);

        // If anything fails here → rollback happens automatically
    }
}
```

**Transaction Propagation**
- Defines how transactions behave when a method is called inside another transactional method.
    - REQUIRED (default) → Join existing transaction, or create a new one.
    - REQUIRES_NEW → Always start a new transaction (suspends existing one).
    - MANDATORY → Must run inside an existing transaction.
    - SUPPORTS → Runs inside transaction if available, otherwise non-transactional.
    - NOT_SUPPORTED → Run without transaction (suspend existing).
    - NEVER → Must not run inside a transaction.
    - NESTED → Creates nested transaction (rollback affects only inner part).

**Rollback Rules**
- By default, Spring rolls back on RuntimeException or Error.
- You can customize:
```java
@Transactional(rollbackFor = Exception.class)
```

**Isolation Levels**
- Controls how transactions see each other’s data.
    - READ_UNCOMMITTED → Can see uncommitted changes (dirty reads).
    - READ_COMMITTED (default in most DBs) → Only committed data is visible.
    - REPEATABLE_READ → Prevents non-repeatable reads.  
    - SERIALIZABLE → Highest isolation, safest but slowest.
```java
@Transactional(isolation = Isolation.SERIALIZABLE)
```

