## REST APIs with Spring Boot

**REST (Representational State Transfer)**
- Architectural style for building scalable web services.
- Uses HTTP methods: GET, POST, PUT, DELETE.
- Resource-based: /api/users, /api/products.

**Spring Boot**
- Simplifies Spring configuration.
- Auto-configuration + embedded servers (Tomcat, Jetty).
- Starter dependencies (spring-boot-starter-web, spring-boot-starter-data-jpa).

### ⚙️ Setting Up a Spring Boot Project
- Use Spring Initializr: https://start.spring.io
- Dependencies:
    - Spring Web 🌐
    - Spring Data JPA 💾
    - Lombok ✨
    - H2 (for demo DB)

**📦 Project Structure (Maven/Gradle):**
```java
src/main/java
 └── com.example.demo
      ├── controller
      ├── service
      ├── repository
      ├── dto
      ├── mapper
      └── exception
```

### Creating REST Endpoints
- Use @RestController + @RequestMapping
```less
/api/users
    ├── GET   /             → list users (pagination + filter)
    ├── GET   /{id}         → get single user
    ├── GET   /search       → search by name
    ├── POST  /             → create
    ├── PUT   /{id}         → update
    ├── PATCH /{id}         → partial update
    └── DELETE/{id}         → delete

```

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<UserDto> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(required = false) Boolean active
    ) {
        return userService.getAllUsers(page, size, sort, active);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/search")
    public List<UserDto> searchUsers(@RequestParam String name) {
        return userService.searchUsersByName(name);
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id,
                              @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @PatchMapping("/{id}")
    public UserDto updateUserEmail(@PathVariable Long id,
                                   @RequestParam String email) {
        return userService.updateUserEmail(id, email);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
```
---
### 📦 DTOs (Data Transfer Objects)
- A Data Transfer Object (DTO) is a simple POJO (Plain Old Java Object) used to carry data between layers (Controller ↔ Service ↔ Repository) without exposing the internal domain/entity model.
- DTOs usually only contain fields + getters/setters, no business logic.
- DTOs are like public wrappers of your data models — you control what leaves your system and what comes in.

> Think of DTOs as the package 📦 you send through the API — it contains just the info the client needs, nothing more.

**🔹 Why Use DTOs?**
- Encapsulation & Security → Hide sensitive fields (e.g., password, internal IDs).
- Shaping API Responses → Different clients may need different data structures.
- Reduce Payload Size → Send only required data.
- Decoupling → Prevents exposing JPA entities directly, making your code more flexible.
- Validation → Add annotations (@NotNull, @Email, etc.) for incoming data.

**Entity vs DTO**
- 🎯 Entity (Database Model)
```java
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password; // Should NOT be exposed in API
}
```
- 🎯 DTO (API Model)
```java
public class UserDto {
    private Long id;
    private String name;
    private String email;
}
```
- 👉 Notice: password is missing in DTO — clients never see it.

**🔹 Mapping Between Entity & DTO**
- Automatic Mapping (with MapStruct / ModelMapper)
- Manual Mapping
```java
public class UserMapper {
    public static UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    public static User toEntity(UserDto dto) {
        return new User(dto.getId(), dto.getName(), dto.getEmail());
    }
}
```

---
### 🔍 Filters in Spring Boot
- A Filter is a component that sits between the client request and the controller.
- It can inspect, modify, or block HTTP requests/responses before they reach the controller or after leaving it.
- Part of the Servlet API → works at a lower level than Spring MVC.
- Think of a filter like a security guard 🚓 at the building entrance: It checks who comes in, logs them, and sometimes denies entry.

**🔹 Filter Lifecycle**
1. Client sends request → passes through filters 🔍
2. Request reaches DispatcherServlet → Controller 🎯
3. Controller returns response → goes back through filters ↩️
4. Response sent to client 🌐

```css
Client → [Filter(s)] → Controller → Service → Repository → DB
         ← [Filter(s)] ← Controller Response ←
```
**🔹 Why Use Filters?**
- Request Logging (URI, headers, method, etc.)
- Security (authentication, authorization)
- Request modification (e.g., trimming spaces, sanitizing input)
- Response modification (e.g., adding headers)
- Cross-cutting concerns (things that apply to every request)

**Real Use Cases**
1. Authentication Filter → Check JWT or API key.
2. CORS Filter → Allow cross-origin requests.
3. Request/Response Logging → Debugging/tracing.
4. Compression Filter → Compress response data.

### 🔍 Different Ways to Create Filters in Spring Boot

**1️⃣ Using Filter Interface (Servlet API way)**
- Implement jakarta.servlet.Filter.
- Override init(), doFilter(), destroy().
- Register with @WebFilter + @ServletComponentScan.
```java
@WebFilter(urlPatterns = "/*")
public class CustomFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("➡️ Filter via @WebFilter");
        chain.doFilter(request, response);
    }
}
```
- 👉 Needs @ServletComponentScan in your main @SpringBootApplication.

**2️⃣ Using OncePerRequestFilter (Spring’s recommended way)**
- Spring provides this abstract class to ensure filter runs once per request.
- Most common approach in Spring Boot apps.
```java
@Component
public class LoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        System.out.println("➡️ Filter via OncePerRequestFilter");
        filterChain.doFilter(request, response);
    }
}
```
- 👉 Automatically picked up as a Spring Bean (@Component).


**3️⃣ Using FilterRegistrationBean (Programmatic Registration)**
- Provides more control over order & URL patterns.
```java
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CustomFilter> loggingFilter() {
        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new CustomFilter());   // Your custom filter
        registrationBean.addUrlPatterns("/api/*");        // Apply only to /api/*
        registrationBean.setOrder(1);                     // Filter execution order

        return registrationBean;
    }
}
```
- 👉 Great when you want ordering between multiple filters.

**4️⃣ Using Spring Security Filters (if security is enabled)**
- Spring Security internally registers many filters (AuthenticationFilter, CsrfFilter, etc.).
- You can add custom filters into the security filter chain.
- Best for auth / security–related filters.
```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .addFilterBefore(new CustomFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
        .build();
}
```

**5️⃣ Using DelegatingFilterProxy (Bridge to Spring Beans)**
- Legacy style → delegates to a Spring Bean that implements Filter.
- Used often with Spring Security (springSecurityFilterChain).
```java
@Bean
public FilterRegistrationBean<DelegatingFilterProxy> delegatingFilterProxy() {
    FilterRegistrationBean<DelegatingFilterProxy> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new DelegatingFilterProxy("mySpringBeanFilter"));
    return registrationBean;
}
```

**Custom Logging Filter**
```java
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("➡️ Incoming request: " + request.getMethod() + " " + request.getRequestURI());

        filterChain.doFilter(request, response); // Pass request to next filter/controller

        System.out.println("⬅️ Outgoing response: " + response.getStatus());
    }
}
```
- @Component → Registers filter as a Spring Bean.
- OncePerRequestFilter → Ensures the filter runs only once per request.
- FilterChain → Passes control to the next filter or controller.


```java
@Component
public class ApiMetricFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(ApiMetricFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        try {
            filterChain.doFilter(request, response);
        } finally {
            long latency = System.currentTimeMillis() - startTime;

            log.info("📊 API Metric - Method: {}, URI: {}, Status: {}, ClientIP: {}, Host: {}, Latency: {} ms",
                    request.getMethod(),
                    request.getRequestURI(),
                    response.getStatus(),
                    request.getRemoteAddr(),
                    request.getRemoteHost(),
                    latency
            );
        }
    }
}
```

---
## 🚦 Interceptors in Spring Boot
- An Interceptor is like a “Spring-aware middleware” that can intercept requests before they hit the controller and after the controller has executed.
- It works at the Spring MVC level (unlike filters which are at the servlet level).
- Typically used for cross-cutting concerns such as:
    - Authentication & authorization 🔑
    - Logging request/response 📊
    - Performance monitoring ⏱️
    - Modifying model attributes before sending to the view 🎨
- 👉 Analogy: If filters are the security guards at the building entrance, interceptors are the floor supervisors inside the office.

**🔹 How Do Interceptors Work?**
- Spring provides the HandlerInterceptor interface with 3 main methods:
```java
public interface HandlerInterceptor {

    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler);

    void postHandle(HttpServletRequest request, HttpServletResponse response, 
                    Object handler, ModelAndView modelAndView);

    void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
                         Object handler, Exception ex);
}
```
`preHandle()`
- Runs before controller method executes.
- Return true → continue request; false → block request.
- Example: check authentication token.

`postHandle()`
- Runs after controller method, but before view rendering.
- Modify model data before sending response.

`afterCompletion()`
- Runs after the entire request is complete (view rendered).
- Useful for cleanup, logging, performance monitoring.

**🔹 Example: Logging Interceptor**
```java

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("➡️ PreHandle: " + request.getMethod() + " " + request.getRequestURI());
        return true; // continue
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, 
                           Object handler, org.springframework.web.servlet.ModelAndView modelAndView) {
        System.out.println("🔹 PostHandle: Status = " + response.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
                                Object handler, Exception ex) {
        System.out.println("✅ AfterCompletion: Request completed");
    }
}
```

```java
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.equals("valid-token")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }
}
```

**🔹 Registering an Interceptor**
- Interceptors must be registered via WebMvcConfigurer:
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor)
                .addPathPatterns("/api/**")   // apply to specific paths
                .excludePathPatterns("/api/public/**"); // exclude paths
    }
}
```
**🔹 Filters vs Interceptors**
| Aspect    | Filter                                    | Interceptor                                                   |
| --------- | ----------------------------------------- | ------------------------------------------------------------- |
| Level     | Servlet (lower-level)                     | Spring MVC (higher-level)                                     |
| Execution | Before servlet & after response leaves    | Before controller, after controller, after request completion |
| Access    | Request/Response only                     | Request/Response + Handler (controller info)                  |
| Use Cases | Security, logging, request transformation | Authentication, metrics, modifying response, business rules   |


```less
Client → [Filter(s)] → DispatcherServlet → [Interceptor.preHandle()] → Controller
                                                       ↑
             Response ← [Interceptor.postHandle()] ← Controller
                          [Interceptor.afterCompletion()]


Filters = global request/response preprocessing (servlet level).
Interceptors = controller-level hooks to add logic before/after execution.

```

---
## 🚨 Exception Handling
- Use @ControllerAdvice + @ExceptionHandler
- Centralized error handling

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex) {
        ApiError error = new ApiError("USER_NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        ApiError error = new ApiError("GENERIC_ERROR", "Something went wrong");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
```

**⚡ Custom Exceptions**
- Create specific exceptions for business rules.
```java
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User not found with id " + id);
    }
}
```
---

## 📝 Logging
**🔹 What is Logging?**
- Logging is the process of recording information about an application’s execution.
- Helps developers:
    - Debug issues 🐞
    - Monitor system health 📊
    - Trace requests in production 🔍
    - Audit activities 🛡️
- Think of logging like your black box recorder ✈️ — it tells the story of what happened inside your app.

**🔹 Logging Frameworks in Spring Boot**
> Spring Boot uses:
- Use SLF4J + Logback (default in Spring Boot)
    - SLF4J (Simple Logging Facade for Java) → abstraction layer
    - Logback → default implementation (fast, flexible)
- Other options: Log4j2, JUL, etc.


**🔹 Logging Levels**
-> Spring Boot supports these levels (from most → least verbose):

1. TRACE → very detailed, low-level (method entry/exit).
2. DEBUG → for developers (internal state, flow).
3. INFO → general information (API requests, startup logs).
4. WARN → something unusual, but app still works.
5. ERROR → something failed, requires attention.
6. OFF → no logging.

```java
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public void createUser(String email) {
        log.info("Creating user with email: {}", email);   // INFO
        log.debug("Debug details for user: {}", email);    // DEBUG
        log.warn("Potential issue with email: {}", email); // WARN
        log.error("Failed to create user {}", email);      // ERROR
    }
}
```

```java
private static final Logger log = LoggerFactory.getLogger(UserService.class);

public UserDto createUser(UserDto dto) {
    log.info("Creating user: {}", dto.getEmail());
    return mapper.toDto(repository.save(mapper.toEntity(dto)));
}
```

**🔹 Configure Logging in application.properties**
```properties
# Set log level for entire app
logging.level.root=INFO

# Set log level for specific package
logging.level.com.example.demo=DEBUG

# Write logs to a file
logging.file.name=app.log

# Customize log pattern (console)
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} %-5level [%thread] %logger{36} - %msg%n

```