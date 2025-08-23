## 📚 Spring Web MVC
- Spring Web MVC is a web application framework built on the Model-View-Controller design pattern.
- It provides:
    - Clear separation of concerns (data, logic, UI).
    - Easy integration with Spring ecosystem.
    - Powerful request handling for both web pages and REST APIs.
- Provides tools to build both:
    - Web applications (JSP/Thymeleaf views)
    - RESTful APIs (JSON/XML responses).

**💡 Why use it?**
- Reduces boilerplate code.
- Flexible view technologies (JSP, Thymeleaf, FreeMarker, JSON, XML).
- Easy to test & maintain.

**MVC Architecture**
```less
[ Client Request ]
        |
        v
  +-------------------+
  | DispatcherServlet |  <-- Front Controller 🚦
  +-------------------+
        |
        v
+-----------------+
|   Controller    |  <-- Request Handler 🎮
+-----------------+
        |
        v
+-----------------+
|     Model       |  <-- Business/Data 🗄️
+-----------------+
        |
        v
+-----------------+
|      View       |  <-- UI (HTML/JSP/JSON) 👀
+-----------------+

Model → Data & business logic (e.g., database entities).
View → UI representation (HTML, JSP, Thymeleaf).
Controller → Handles HTTP requests, processes data, and decides the view.
```

### Core Components of Spring MVC

**DispatcherServlet 🚦**
- Acts as the front controller.
- Every request goes through it.
- Delegates request handling to controllers.
- Configured in web.xml (if using old style) OR auto-configured in Spring Boot.
- 👉 Real-life example: Like a receptionist in an office – all visitors must check in here before meeting anyone else.

**Controller 🎮**
- Java class handling web requests.
- Contains request handling methods.
- Annotated with @Controller or @RestController.
- ✅ Example (Web Page):
```java
@Controller
public class HomeController {

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("title", "Welcome to Spring MVC 🏠");
        return "home"; // returns home.jsp or home.html
    }
}
```

- ✅ Example (REST API):
```java
@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from API 🌐";
    }
}
```

**Handler Mapping 🧭**
- Maps requests (like /home) to the correct controller.
- Uses @RequestMapping, @GetMapping, etc.

```java
@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/users")
    public List<String> getUsers() {
        return List.of("Alice", "Bob", "Charlie");
    }
}
```

**View Resolver 👀**
- Maps logical view names → actual template files.
- Example: "home" → /WEB-INF/views/home.jsp.
- 👉 In Spring Boot, Thymeleaf or FreeMarker automatically acts as the view resolver.
```properties
spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
```
- Returning "home" → resolves to /WEB-INF/views/home.jsp.

**Model 🗄️**
- Holds application data.
- Data container shared between Controller and View.
- Passed to the view using Model or ModelAndView.
- Implemented using Model, Map, or ModelAndView.


- Example:
```java
@GetMapping("/user")
public String user(Model model) {
    model.addAttribute("name", "Alice");
    model.addAttribute("role", "Admin");
    return "user"; // user.jsp
}
```

- View (JSP/Thymeleaf):
```html
<h1>User: ${name} 👩‍💻</h1>
<p>Role: ${role}</p>
```

**View 👀**
- Presentation layer.
- Supported: JSP, Thymeleaf, FreeMarker, JSON, XML.
- Example with Thymeleaf:
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<body>
   <h1 th:text="${title}"></h1>
</body>
</html>
```

----
### Request Flow in Spring MVC
```less
[Browser] --> [DispatcherServlet] --> [Controller] --> [Service] --> [DAO/DB]
                    |
                    v
                 [ViewResolver]
                    |
                    v
                [HTML/JSP/JSON]
```

*👉 Real-time Example:*
1. User requests `/products`.
2. DispatcherServlet forwards it to ProductController.
3. Controller fetches data from ProductService.
4. Service fetches from DB via ProductRepository.
5. Controller returns "products".
6. ViewResolver maps it to products.html.

----

### Spring MVC Annotations

| Annotation        | Purpose                 | Example                                          |
| ----------------- | ----------------------- | ------------------------------------------------ |
| `@Controller`     | Marks controller        | `@Controller class MyController`                 |
| `@RestController` | Returns JSON/XML        | `@RestController class ApiController`            |
| `@RequestMapping` | Maps request paths      | `@RequestMapping("/api")`                        |
| `@GetMapping`     | GET request             | `@GetMapping("/users")`                          |
| `@PostMapping`    | POST request            | `@PostMapping("/add")`                           |
| `@PathVariable`   | Extracts from URL       | `/user/{id}` → `@PathVariable int id`            |
| `@RequestParam`   | Extracts query param    | `/search?name=abc` → `@RequestParam String name` |
| `@ModelAttribute` | Binds form → object     | `@ModelAttribute User user`                      |
| `@ResponseBody`   | Sends response directly | `@ResponseBody String`                           |

---

### Real-time Use Cases

**🔹 Building a Web Page (with JSP/Thymeleaf)**
```java
@Controller
public class PageController {
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("company", "Tech Corp 🌐");
        return "about";
    }
}
```
- 👉 Output in browser: about.html showing "Tech Corp 🌐"

**🔹 Building a REST API**
```java
@RestController
@RequestMapping("/api")
public class ProductController {

    @GetMapping("/products")
    public List<String> getProducts() {
        return List.of("Laptop", "Phone", "Tablet");
    }
}
```
- 👉 Output in browser/Postman:
```json
["Laptop", "Phone", "Tablet"]
```

**🔹 Handling Form Submission**
```java
@PostMapping("/register")
public String registerUser(@ModelAttribute User user, Model model) {
    model.addAttribute("user", user);
    return "success";  // success.jsp
}
```

Form (HTML):
```html
<form action="/register" method="post">
   <input type="text" name="name"/>
   <input type="email" name="email"/>
   <button type="submit">Register ✅</button>
</form>
```

---
## Advanced Concepts
**Exception Handling ⚡**
```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleError(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}
```

**Interceptors 🚦**
```java
public class LoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {
        System.out.println("Request: " + req.getRequestURI());
        return true;
    }
}
```


**File Upload 📂**
```java
@PostMapping("/upload")
public String upload(@RequestParam("file") MultipartFile file) {
    System.out.println("Uploaded file: " + file.getOriginalFilename());
    return "uploadSuccess";
}
```

HTML Form:
```html
<form method="post" enctype="multipart/form-data" action="/upload">
  <input type="file" name="file"/>
  <button type="submit">Upload 📤</button>
</form>
```

---
### Spring MVC vs Spring Boot
| Feature      | Spring MVC               | Spring Boot           |
| ------------ | ------------------------ | --------------------- |
| Setup        | Manual (xml/java config) | Auto-configured ⚡     |
| Server       | Needs external Tomcat    | Embedded Tomcat/Jetty |
| Productivity | Slower                   | Faster 🚀             |
| Usage        | Traditional web apps     | Modern microservices  |

---
## Healthcare System 🏥
- Goal: A robust, secure, and scalable system to manage Patients, Doctors, Appointments, and related modules (staff, specialties, prescriptions, documents, notifications) using Spring Web MVC.

### 1) High-Level Overview**

**Primary Users & Roles 👥**

- Admin: manage users/roles, hospital configuration
- Doctor: manage availability, view patient records, write notes/prescriptions
- Receptionist: register patients, schedule/reschedule appointments, billing support
- Patient: view/update profile, book appointments, view prescriptions & reports

**Key Modules 🧩**
- Identity & Access (AuthN/Z, RBAC)
- Patient Management
- Doctor & Specialty Management
- Appointment Scheduling
- Medical Records (encounters, notes, prescriptions)
- Documents (uploads: reports, scans)
- Notifications (email/SMS/push)
- Billing (optional extension)
- Admin Console & Audit Logs

**Non-Functional Requirements ✅**
- Security-first (RBAC, encryption at rest/in-transit)
- Consistency & concurrency control (appointment booking)
- Observability (logs, metrics, traces)
- Performance (caching, pagination, N+1 prevention)
- Internationalization (i18n), time zones
- Testability (unit/integration/E2E)


### 2) Reference Architecture (Layered Monolith) 🏛️
```less
           ┌────────────────────────────────────────────────────┐
           │                    Client Channels                 │
           │  Web (Thymeleaf/JSP)  |  REST (JSON)  |  Mobile   │
           └───────────────▲───────────────────────▲───────────┘
                           │                       │
                     Spring Web MVC (DispatcherServlet)
                           │                       │
                   ┌───────┴────────┐       ┌──────┴───────┐
                   │     Controllers │       │   REST APIs  │
                   └───────┬────────┘       └──────┬───────┘
                           │                         
                   ┌───────▼──────────────────────────────────┐
                   │               Service Layer               │
                   │  Orchestration, business rules, DTO maps │
                   └───────┬──────────────────────────────────┘
                           │
                   ┌───────▼─────────┐
                   │  Repository/DAO │  (Spring Data JPA)
                   └───────┬─────────┘
                           │
                   ┌───────▼──────────────────────────────────┐
                   │         Data & Integration Layer         │
                   │  PostgreSQL | Redis | MQ | File Storage │
                   └──────────────────────────────────────────┘
```

**Request Flow 🔁**
```less
[Browser/Mobile] → [DispatcherServlet] → [HandlerMapping]
                      │
                      └→ [Controller] → [Service] → [Repository] → [DB]
                                           │
                                     [Cache/Queue/Files]
                      ↓
                [ViewResolver] → [View (Thymeleaf/JSP) or JSON]
```

### 3) Domain Model (Core Entities) 🧬
```less
Patient (1)------(M) Appointment (M)------(1) Doctor
   |                                   |
   |                                   +--(1) Specialty
   |
   +--(M) Document

Doctor (1)------(M) AvailabilitySlot

Patient (1)------(M) Encounter (visit)
Encounter (1)------(M) Prescription
```

- Patient: id, mrn (unique), firstName, lastName, dob, gender, phone, email, address, emergencyContact, insuranceId, createdAt, updatedAt, status

- Doctor: id, firstName, lastName, email, phone, specialtyId, registrationNumber, yearsOfExperience, room, createdAt, updatedAt, status

- Specialty: id, code, name, description

- AvailabilitySlot: id, doctorId, dayOfWeek, startTime, endTime, capacity (per slot), location

- Appointment: id, patientId, doctorId, startDateTime, endDateTime, status (BOOKED/CANCELLED/COMPLETED/NO_SHOW), reason, notes, source (WEB/RECEPTION), version

- Encounter: id, appointmentId, patientId, doctorId, vitals(json), diagnosis, notes, createdAt

- Prescription: id, encounterId, drugName, dosage, frequency, duration, instructions

- Document: id, patientId, type (LAB_REPORT/SCAN/OTHER), fileKey, originalName, mimeType, uploadedBy, uploadedAt

- User: id, username, passwordHash, role (ADMIN/DOCTOR/RECEPTIONIST/PATIENT), isActive, lastLogin

- Notification: id, recipientType (PATIENT/DOCTOR), recipientId, channel (EMAIL/SMS/PUSH), templateKey, payload(json), status, sentAt

### 4) API Design (Representative Endpoints) 🌐
```less
Base URL: /api/v1

Auth & Users 🔐
    POST /auth/login → issue JWT
    POST /auth/refresh → refresh token
    GET /users/me → current profile

Patients 🧑‍⚕️
    GET /patients (q, page, size, sort)
    POST /patients (create)
    GET /patients/{id}
    PUT /patients/{id} (update)
    GET /patients/{id}/documents
    POST /patients/{id}/documents (multipart upload)

Doctors 👨‍⚕️👩‍⚕️
    GET /doctors (specialty, name, page,...)
    POST /doctors
    GET /doctors/{id}
    GET /doctors/{id}/availability?from=&to=
    POST /doctors/{id}/availability (create slots)

Appointments 📅
    GET /appointments (doctorId, patientId, status, from, to)
    POST /appointments (book)
    GET /appointments/{id}
    PUT /appointments/{id} (reschedule)
    POST /appointments/{id}:cancel
    POST /appointments/{id}:complete

Encounters & Prescriptions 📝💊
    POST /encounters (open encounter)
    GET /encounters/{id}
    POST /encounters/{id}/prescriptions
    GET /patients/{id}/encounters

Notifications ✉️
    POST /notifications/test (admin)
    Webhooks for delivery receipts (optional)

```

### 5) Spring MVC Layering & Components 🧱

**Controllers (Web Layer)**
- PatientController, DoctorController, AppointmentController, EncounterController, DocumentController, AuthController
- Use @RestController, @RequestMapping("/api/v1/patients"), @GetMapping, @PostMapping etc.
- DTOs for requests/responses; never expose entities directly.

**Services (Business Layer)**
- PatientService, DoctorService, AppointmentService, EncounterService, DocumentService, NotificationService
- Transaction boundaries with @Transactional (write ops)
- Domain validations & policies (e.g., doctor availability)

**Repositories (Persistence)**
- Spring Data JPA: PatientRepository, DoctorRepository, AppointmentRepository...
- Custom queries using @Query or Spec/Criteria for filtering.

**Infrastructure Adapters**
- Email/SMS: NotificationGateway
- Object Storage: DocumentStorage (S3/MinIO)
- Cache: Redis (availability lookups)
- Message Broker: Kafka/RabbitMQ (async notifications, audit)


### 6) Technology Stack
- Framework: Spring Boot (with Spring Web MVC). (If classic config: Spring MVC + Java Config)
- View: Thymeleaf (preferred) or JSP.
- Persistence: Spring Data JPA + Hibernate; DB: PostgreSQL.
- Security: Spring Security (Form Login for web, JWT for APIs).
- Validation: Bean Validation (Jakarta/Hibernate Validator).
- Build: Maven or Gradle.
- Caching: Spring Cache (Redis provider).
- Messaging (optional): RabbitMQ/Kafka for async notifications.
- Object Mapping: MapStruct or manual mappers.
- Observability: Micrometer + Prometheus/Grafana, Logback + JSON logs, OpenTelemetry (optional).
- Testing: JUnit 5, Spring Boot Test, Testcontainers, MockMVC.
