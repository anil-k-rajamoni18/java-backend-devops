## Spring Boot MVC – Banking App
- A minimal, runnable Spring Boot Web MVC starter that manages Accounts, Loans, and Transactions with Thymeleaf views and H2 in‑memory DB.

**High-Level Architecture**
- Layers (MVC + service + persistence):

1. Presentation Layer (Web MVC)
    - Controllers (REST or Thymeleaf if UI-based)
    - Handles incoming HTTP requests, delegates to services.

2. Service Layer (Business Logic)
    - Core banking logic (account management, loan processing, transaction handling).
    - Validation, rules, transaction workflows.

3. Persistence Layer (Repository)
- JPA/Hibernate repositories for database access.
- Entities mapping (Account, Loan, Transaction).

4. Database
- Relational DB (MySQL/PostgreSQL).
- Tables for accounts, loans, transactions, customers.

5. Security Layer
- Spring Security (login, roles: Admin, Customer).

**Project Structure**
```less
banking-app/
├─ pom.xml
├─ src/main/java/com/example/banking/
│  ├─ BankingApplication.java
│  ├─ config/SecurityConfig.java
│  ├─ controller/
│  │  ├─ DashboardController.java
│  │  ├─ AccountController.java
│  │  ├─ TransactionController.java
│  │  ├─ LoanController.java
│  │  └─ UserController.java        
│  ├─ dto/
│  │  ├─ TransactionForm.java
│  │  ├─ LoanForm.java
│  │  └─ UserRegistrationForm.java 
│  ├─ entity/
│  │  ├─ Account.java
│  │  ├─ Transaction.java
│  │  ├─ Loan.java
│  │  └─ User.java                
│  ├─ repository/
│  │  ├─ AccountRepository.java
│  │  ├─ TransactionRepository.java
│  │  ├─ LoanRepository.java
│  │  └─ UserRepository.java       
│  └─ service/
│     ├─ AccountService.java
│     ├─ TransactionService.java
│     ├─ LoanService.java
│     └─ UserService.java          
├─ src/main/resources/
│  ├─ application.properties
│  └─ templates/
│     ├─ layout.html
│     ├─ index.html
│     ├─ accounts/
│     ├─ transactions/
│     ├─ loans/
│     └─ users/                    
│        ├─ login.html
│        └─ register.html
└─ src/test/java/... (optional)
```

### **📝 Entity Mapping**

**User**
```java
User {
  Long id (PK)
  String username (unique)
  String password
  String role (ADMIN, CUSTOMER)
  String email
  OneToMany<Account> accounts
  OneToMany<Loan> loans
}
```

**Account**
```java
Account {
  Long id (PK)
  String type (SAVINGS, CHECKING)
  Double balance
  ManyToOne<User> owner
  OneToMany<Transaction> transactions
}
```

**Transaction**
```java
Transaction {
  Long id (PK)
  LocalDateTime timestamp
  Double amount
  String type (CREDIT, DEBIT)
  ManyToOne<Account> account
}
```

**Loan**
```java
Loan {
  Long id (PK)
  Double amount
  Double interestRate
  String status (PENDING, APPROVED, REJECTED)
  ManyToOne<User> borrower
}
```

### 🌐 REST Endpoint Design

```less
User (Auth & Management)
    GET /users → list users (admin only)
    GET /users/{id} → get user details
    POST /users/register → create new user
    POST /users/login → login (Spring Security handles session)

Accounts
    GET /accounts → list all accounts of current user
    GET /accounts/{id} → account details (balance, transactions)
    POST /accounts → create new account
    DELETE /accounts/{id} → close account

Transactions
    GET /transactions → list all transactions (for logged-in user’s accounts)
    GET /transactions/{id} → transaction details
    POST /transactions → perform transaction (deposit/withdraw/transfer)

Loans
    GET /loans → list user’s loans
    GET /loans/{id} → loan details
    POST /loans → apply for a loan
    PUT /loans/{id}/approve → approve loan (admin only)
    PUT /loans/{id}/reject → reject loan (admin only)

```