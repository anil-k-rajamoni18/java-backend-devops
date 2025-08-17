## Exception Handling in Spring Boot

**What is an Exception?**
- An Exception in Java is an event that disrupts the normal flow of a program.
- It occurs when something unexpected happens (e.g., invalid input, missing file, DB connection failure).
- In Java, all exceptions are objects of classes that inherit from Throwable.

```less
java.lang.Object
   └── java.lang.Throwable
         ├── java.lang.Error (serious issues, not recoverable)
         └── java.lang.Exception
               ├── Checked Exceptions (must handle)
               └── RuntimeException (Unchecked)

```
**Why Do We Need Exception Handling?**
- Stability → Prevents application from crashing abruptly.
- User Experience → Converts technical errors into user-friendly responses.
- Security → Hides sensitive system details (stack traces, DB info).
- Maintainability → Centralizes error handling instead of duplicating logic.
- HTTP Compliance → Maps Java exceptions to proper HTTP status codes.

**Types of Exceptions**
1. Checked Exceptions (Compile-time)
- Must be handled or declared using throws.
- Example: IOException, SQLException.
- Often represent recoverable conditions.

2. Unchecked Exceptions (Runtime)
- Subclass of RuntimeException.
- Don’t require explicit handling.
- Example: NullPointerException, IllegalArgumentException.
- Often represent programming errors.

3. Errors
 - Subclasses of Error.
 - Indicate serious issues (JVM problems like OutOfMemoryError).
 - Not meant to be handled by applications.

4. Spring MVC / Spring Boot Exceptions
- HttpMessageNotReadableException → invalid JSON input.
- MethodArgumentNotValidException → validation errors.
- HttpRequestMethodNotSupportedException → wrong HTTP method.
- MissingServletRequestParameterException → missing query params.

5. Custom Exceptions
- Application/domain-specific (e.g., UserNotFoundException).
- Encapsulate business logic failures.

**Creating a Custom Exception**
```java
public class UserNotFoundException extends RuntimeException {
    
    public UserNotFoundException(String message) {
        super(message);
    }
}

public class ApiException extends RuntimeException {
    private final String errorCode;
    private final HttpStatus status;
    private final LocalDateTime timestamp;
    private final String path;
    private final Map<String, String> details;

    public ApiException(String message, String errorCode, HttpStatus status, String path, Map<String, String> details) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.details = details;
    }
}

```
### Exception Handling Techniques

**a) @ExceptionHandler (Local Handling)**
- Handles exceptions at controller level only.
```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService; 

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
```
- Good for controller-specific logic, but not reusable across app.

**b) @ControllerAdvice (Global Handling)**
- Centralized exception handling, data binding, and model attribute handling for multiple controllers.
- Works like AOP (cross-cutting concern).
- A specialization of @Component that allows you to handle exceptions across the whole application in one global handling component.
- Response Type:
    - By default, it works like @Controller.
    - If you return an object, it will try to resolve it to a view (e.g., JSP, Thymeleaf).
    - To return JSON/XML, you must explicitly annotate handler methods with @ResponseBody.

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class) // fallback
    @ResponseBody
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Something went wrong!");
    }
}
```

**@RestControllerAdvice**
- A composed annotation that combines @ControllerAdvice + @ResponseBody.
- Simplifies creating global exception handlers for REST APIs.
- Response Type:
    - Always returns JSON/XML response (serialized using Jackson or other message converters).
    - No need to add @ResponseBody on every handler method.

```java
@RestControllerAdvice
public class RestGlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
```

| Feature              | `@ControllerAdvice`                             | `@RestControllerAdvice`              |
| -------------------- | ----------------------------------------------- | ------------------------------------ |
| Inherits from        | `@Controller`                                   | `@RestController`                    |
| Auto `@ResponseBody` | ❌ No (must add manually)                        | ✅ Yes (automatic JSON/XML response)  |
| Best suited for      | Web apps with **views (Thymeleaf/JSP)**         | **REST APIs** (JSON/XML responses)   |
| Typical use case     | Global handling for MVC apps (HTML error pages) | Global handling for RESTful services |
| Introduced in        | Spring 3.2                                      | Spring 4.3 (for convenience)         |


**c) @ResponseStatus on Exception**
- Simplest way to bind exceptions to HTTP status.
```java
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
```
- Spring automatically returns 404 when exception is thrown.
- Not flexible if you need custom response bodies.

**d) Extend ResponseEntityExceptionHandler**
- Gives fine-grained control for Spring MVC exceptions.
```java
@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
```
- Useful for validation errors, JSON parsing issues, etc.

---
**Designing Custom Error Responses**
- Instead of raw messages, return a structured error response object:
```java
@Data
@AllArgsConstructor
public class ApiError {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private String path;
}
```
- Then in handler:
```java
@ExceptionHandler(UserNotFoundException.class)
public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex, WebRequest request) {
    ApiError error = new ApiError(
            HttpStatus.NOT_FOUND.value(),
            ex.getMessage(),
            LocalDateTime.now(),
            request.getDescription(false)
    );
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}
```

**Mapping Exceptions to HTTP Status Codes**
| Exception Type            | Recommended Status          |
| ------------------------- | --------------------------- |
| Invalid input             | `400 BAD REQUEST`           |
| Missing resource          | `404 NOT FOUND`             |
| Unauthorized (no login)   | `401 UNAUTHORIZED`          |
| Forbidden (no permission) | `403 FORBIDDEN`             |
| Conflict (duplicate)      | `409 CONFLICT`              |
| Server failure            | `500 INTERNAL SERVER ERROR` |


**Best Practices**
1. ✅ Use @ControllerAdvice for global consistency.
2. ✅ Create custom exceptions for business logic.
3. ✅ Return structured JSON error objects.
4. ✅ Log errors (with SLF4J/Logback), but don’t leak internal details.
5. ✅ Differentiate between client errors (4xx) and server errors (5xx).
6. ⚠️ Don’t expose stack traces to the client.
7. ⚡ In Spring Boot 3+, consider ProblemDetail API (RFC 7807 standard).


**Spring Boot 3.x & ProblemDetail (Modern Way)**
- Spring Boot 3 introduces ProblemDetail for standardized error responses.
```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFound(UserNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Resource Not Found");
        problem.setDetail(ex.getMessage());
        return problem;
    }
}
```

**Flow of Exception Handling in Spring Boot**
- Exception is thrown inside controller/service.
- Spring checks for local @ExceptionHandler.
- If not found → Spring looks into @ControllerAdvice.
- If still not found → handled by DefaultErrorAttributes → returns default JSON (/error endpoint).
