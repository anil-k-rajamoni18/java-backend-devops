# 📝 Hands-on Exercise Questions: Spring RESTful API Development

---

## Section 1: Spring Data JPA & Entities

1. Create two entities `Student` and `Course` with a **Many-to-One** relationship (many students can enroll in one course).  
2. Create repositories (`StudentRepository`, `CourseRepository`) using `JpaRepository`.  
3. Write a custom JPA query to fetch:  
   - All students older than a given age.  
   - All courses containing a given keyword in their title.  
4. Implement **pagination & sorting** for the `findAllStudents` endpoint.  

---

## Section 2: RESTful Controllers

5. Create a REST controller for `Student` with endpoints:  
   - `POST /students` → Create a new student  
   - `GET /students` → Get all students (with pagination & sorting)  
   - `GET /students/{id}` → Get student by ID  
   - `PUT /students/{id}` → Update a student’s details  
   - `DELETE /students/{id}` → Delete a student  

6. Create a REST controller for `Course` with endpoints to add, search, and list all courses.  
7. Create an endpoint `/students/search?age=20` to return students older than 20.  

---

## Section 3: Exception Handling

8. Define a custom exception `ResourceNotFoundException`.  
9. Implement a `@ControllerAdvice` with `@ExceptionHandler` to return a **JSON error response** when a student or course is not found.  
10. Create a custom exception for invalid inputs (e.g., negative age for student).  

---

## Section 4: Filters & Interceptors

11. Implement a **Filter** that logs each request URI and execution time.  
12. Implement an **Interceptor** that checks for a custom header `X-API-KEY`. If missing or invalid, return **403 Forbidden**.  
13. Add an **Interceptor** that logs the authenticated user info before processing a request.  

---

## Section 5: Logging

14. Add **SLF4J logging** in service and controller layers for:  
   - When a new student is created.  
   - When a student is updated.  
   - When a student is not found (**log as ERROR**).  

15. Configure logging to print **SQL queries** in the console.  
16. Write logs with different levels (`INFO`, `DEBUG`, `ERROR`) and test them.  

---

## Section 6: Bonus (Advanced)

17. Add **HATEOAS links** in the `Student` response (e.g., link to enrolled course).  
18. Secure one endpoint using **Spring Security** (only logged-in users can access `/courses`).  
19. Enable **Spring Boot Actuator** and explore `/actuator/health` and `/actuator/metrics`.  
20. Add **global exception logging** using `@Slf4j` and ensure stack traces are logged.  
