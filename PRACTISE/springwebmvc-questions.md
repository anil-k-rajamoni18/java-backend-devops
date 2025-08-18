## Level 1: Spring MVC Basics

**Goal:** Understand controllers, request mapping, and view resolution.

#### 1. Hello World Controller
- Create a Spring MVC project.
- Create a `HelloController` that handles `/hello` requests.
- Return a JSP/Thymeleaf view displaying `"Hello, Spring MVC!"`.

#### 2. Path Variables and Request Parameters
- Create a controller method that handles `/greet/{name}`.
- Display `"Hello, {name}"` on the page.
- Add another method that takes `age` as a request parameter and displays it.

#### 3. Model and View Usage
- Create a `ProductController` that:
  - Returns product details via `ModelAndView`.
  - Uses a view template to display product name and price.

---

## Level 2: Form Handling & Validation

**Goal:** Handle user input and perform server-side validation.

#### 4. Simple HTML Form Submission
- Create a `UserForm` page with name and email fields.
- Handle form submission in a controller.
- Display submitted values in the result view.

#### 5. `@ModelAttribute` and Binding
- Use `@ModelAttribute` to bind form fields to a `User` object.
- Populate the form with default values when the page loads.

#### 6. Form Validation with `@Valid`
- Add JSR-380 annotations (e.g., `@NotNull`, `@Email`) to the `User` class.
- Use `BindingResult` to display validation errors in the form view.

---

## Level 3: Advanced Controller Features

**Goal:** Work with interceptors, exception handling, and file uploads.

#### 7. Custom Exception Handling
- Create a custom exception `UserNotFoundException`.
- Use `@ControllerAdvice` and `@ExceptionHandler` to return a custom error page.

#### 8. Spring MVC Interceptor
- Implement a `HandlerInterceptor` that logs request URLs and timestamps.
- Register it in the Spring MVC config.

#### 9. File Upload Handling
- Create an upload form to accept image files.
- Store uploaded files on the server.
- Display the uploaded image in a view.

---

## Level 4: REST APIs with Spring MVC

**Goal:** Create REST endpoints and consume JSON.

#### 10. Simple REST Controller
- Create a `@RestController` with a `/api/products` endpoint.
- Return a list of products in JSON format.

#### 11. Consuming Request Body
- Create a POST endpoint `/api/products` that accepts product JSON.
- Store it in an in-memory list and return a success message.

#### 12. Exception Handling in REST
- Handle invalid JSON input with a custom `RestExceptionHandler`.
- Return proper HTTP status codes and error messages.

---

## 💡 Bonus Challenge: Mini Project

**Goal:** Combine all concepts into a small app.

Create a `Student Management System`:
- **Views:** JSP/Thymeleaf pages for listing, adding, and editing students.
- **Forms:** Use `@ModelAttribute` and `@Valid` for form handling and validation.
- **REST API:** Provide `/api/students` endpoints for JSON data.
- **Interceptor:** Log request processing time.
- **File Upload:** Allow students to upload profile pictures.