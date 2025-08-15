# 🌱 Spring Framework

### Introduction to Spring Framework
- Spring Framework is a lightweight, open-source Java framework that helps you build enterprise-level applications easily and flexibly.
- It provides tools, libraries, and patterns that make application development faster, cleaner, and more maintainable.
- Think of it like a toolbox for Java developers 🧰 — it gives you:
    - Ready-to-use tools for dependency injection (DI)
    - Support for multiple architectures (web, batch, microservices)
    - Integration with databases, security, messaging, etc.

- It’s not just a library — it’s a whole ecosystem with:
    - Core (Dependency Injection, Bean management)
    - Spring MVC (Web framework)
    - Spring Data (Database access)
    - Spring Security (Authentication & authorization)
    - Spring Boot (Rapid application development)
    - Spring Cloud (Microservices)

> "Make Java development simpler and more productive."

**History 🕰️**
| Year          | Event                                                                                                                     |
| ------------- | ------------------------------------------------------------------------------------------------------------------------- |
| **2002**      | Rod Johnson publishes his book *Expert One-on-One J2EE Design and Development* – criticizes complexity of Java EE (EJBs). |
| **2003**      | First Spring Framework release (v1.0).                                                                                    |
| **2004–2006** | Becomes popular as an alternative to heavyweight J2EE.                                                                    |
| **2014**      | Spring Boot introduced 🚀 — makes Spring projects easier to start.                                                        |
| **Now**       | Backbone for many enterprise apps, powering companies like Netflix, Amazon, and Alibaba.                                  |


**Why was it revolutionary?**
- Because at the time, Java EE was painfully complex, bloated with XML configuration, and required heavyweight servers like WebLogic or WebSphere.


### Why Spring Was Developed 🤔
- Back in early 2000s, Java EE (J2EE) was:
    - 🏋️ Too heavy — required huge XML configs and complex EJBs.
    - ❌ Hard to test — business logic tightly coupled to infrastructure code.
    - 🐌 Slow to develop — lots of boilerplate.
- *Rod Johnson thought:* "Why not make something lightweight, modular, testable, and easier to use?"
    - That’s how Spring was born.

- Spring was developed to solve three main pain points of early enterprise Java:

| Problem                | Before Spring                                     | Spring’s Solution                           |
| ---------------------- | ------------------------------------------------- | ------------------------------------------- |
| **Complexity**         | Heavy EJBs, lots of boilerplate                   | Lightweight POJOs (Plain Old Java Objects)  |
| **Tight Coupling**     | Hard to change components without breaking others | Loose coupling via **Dependency Injection** |
| **Configuration Hell** | Thousands of lines of XML                         | Annotations & Java-based config             |

**Real-world example:**
- `Before Spring`: Changing a database from MySQL to PostgreSQL meant changing lots of code in many files.
- `With Spring`: You just change a single configuration property — the code stays the same.

---
##  Core Concepts of Spring

###  Inversion of Control (IoC)
- Instead of your code creating objects directly, you let Spring’s IoC container create and manage them for you.
- Real-life analogy: Instead of a chef buying his own ingredients, the restaurant’s supplier delivers them ready to use Similarly, Spring creates and wires objects so you can just use them.
- Why: Promotes loose coupling.
- *Short definition* — IoC means you stop controlling the creation and wiring of objects; something else (a framework or container) does it for you.


**IoC vs Dependency Injection vs DIP (short clarifications)**

1. `IoC (Inversion of Control)` : reverse the responsibility of control (creation, lifecycle, flow).

2. `DI (Dependency Injection)` — a common pattern/implementation of IoC where dependencies are injected into objects (constructor, setter, field).

3. `DIP (Dependency Inversion Principle — SOLID)` — design principle: high-level modules should not depend on low-level modules; both should depend on abstractions. DI often helps you follow DIP.

#### How IoC looks in code

**Without IoC (tight coupling — bad for testing)**
```java
public class UserService {
    private EmailService email = new EmailService(); // concrete wiring inside class

    public void register(User u) {
        // ...business logic...
        email.sendWelcome(u.getEmail());
    }
}
```
- UserService is tightly coupled to EmailService. Hard to replace or test.

**With IoC / Dependency Injection (loose coupling — better)**
```java
public interface MessageService {
    void send(String to, String body);
}

public class EmailService implements MessageService {
    public void send(String to, String body) {
        // send email
    }
}

public class UserService {
    private final MessageService messageService;

    // constructor injection — preferred
    public UserService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void register(User u) {
        // ...business logic...
        messageService.send(u.getEmail(), "Welcome!");
    }
}
```
- Now the creation of EmailService happens outside UserService. A container or test harness can provide a different MessageService (e.g., mock or SMS).

#### Forms of dependency injection (DI) ✅
1. Constructor injection (preferred for required dependencies)
```java
public class A {
    private final B b;
    public A(B b) { 
        this.b = b; 
    }
}
```
- immutable dependencies, easy to test, clearly expresses required deps.

2. Setter injection (optional/replaceable dependencies)
```java
public class A {
    private B b;
    public void setB(B b) { 
        this.b = b; 
    }
}
```
- Pros: good for optional deps.
- Cons: possible partially constructed state.

3. Field injection (convenient, less testable)
```java
public class A {
    @Autowired
    private B b;
}
```
- Cons: hides dependencies, hard to unit test without framework support. Prefer constructor injection in most cases.

----
### IoC in Spring
**XML-based wiring (old style)**
```xml
<bean id="emailService" class="com.example.EmailService"/>
<bean id="userService" class="com.example.UserService">
    <constructor-arg ref="emailService"/>
</bean>
```

**Annotation-based (modern, typical)**
```java
@Service
public class EmailService implements MessageService { ... }

@Service // or @Component
public class UserService {
    private final MessageService messageService;

    @Autowired // optional on single constructor (Spring 4+)
    public UserService(MessageService messageService) {
        this.messageService = messageService;
    }
}
```

**Java config (@Configuration) style**
```java
@Configuration
public class AppConfig {
    @Bean
    public MessageService messageService() {
        return new EmailService();
    }

    @Bean
    public UserService userService(MessageService messageService) {
        return new UserService(messageService);
    }
}

```

**Bootstrapping (get bean from context)**
```java
AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
UserService us = ctx.getBean(UserService.class);
```

- Or with Spring Boot:
```java
@SpringBootApplication
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
```

--- 
## ApplicationContext
- In Spring, ApplicationContext is the central IoC container that:
    - Creates, configures, and manages beans.
    - Resolves dependencies between beans.
    - Provides application-wide services like internationalization, event publishing, and resource loading.
- 📌 Think of it as the “brain” 🧠 of the Spring Framework — it knows what beans exist, how they’re connected, and when to create/destroy them.

- It extends the `BeanFactory` interface (which is the basic IoC container) but adds enterprise-level features.

**ApplicationContext vs BeanFactory**
| Feature                             | BeanFactory | ApplicationContext            |
| ----------------------------------- | ----------- | ----------------------------- |
| Lazy bean loading                   | ✅           | ✅ (but can also eagerly load) |
| Dependency injection                | ✅           | ✅                             |
| Internationalization (i18n)         | ❌           | ✅                             |
| Event publishing                    | ❌           | ✅                             |
| Bean post-processors auto-detection | ❌           | ✅                             |
| Used in real apps                   | Rare        | Almost always                 |

- 💡 In short — use ApplicationContext in modern Spring apps, BeanFactory only for ultra-lightweight scenarios.

### **Types of ApplicationContext Implementations**
- Spring provides multiple implementations of ApplicationContext depending on your configuration style and environment.

**A) ClassPathXmlApplicationContext**
- Loads context definition from XML file in the classpath.
```java
ApplicationContext context =
    new ClassPathXmlApplicationContext("beans.xml");

MyService service = context.getBean(MyService.class);
```
- XML file must be inside `src/main/resources`.


**B) FileSystemXmlApplicationContext**
- Loads XML configuration from a file system path (absolute or relative).
- Useful when XML is outside your app’s classpath.
```java
ApplicationContext context =
    new FileSystemXmlApplicationContext("C:/spring/beans.xml");
```

**C) AnnotationConfigApplicationContext**
- Used for Java-based configuration (@Configuration classes, @Bean methods).
- Preferred in modern Spring (no XML required).
```java
ApplicationContext context =
    new AnnotationConfigApplicationContext(AppConfig.class);

MyService service = context.getBean(MyService.class);
```

**D) AnnotationConfigServletWebServerApplicationContext (Spring Boot)**
- Used internally by Spring Boot for web apps.
- Automatically loads configuration classes and starts an embedded web server (Tomcat/Jetty).
- You usually don’t instantiate this manually — SpringApplication.run() does it.

**E) WebApplicationContext**
- A specialized ApplicationContext for web applications.
- Integrated with ServletContext in Java EE environments.
- Used by Spring MVC — each web application typically has one root WebApplicationContext.

**How it works internally**
1. Reads configuration (XML, Java classes, or annotations).
2. Creates beans according to scope (singleton, prototype, etc.).
3. Resolves dependencies via DI (constructor/setter/field).
4. Manages lifecycle (init, destroy methods).
5. Provides services:
    - 📜 Bean lookup by name or type.
    - 🌍 Internationalization with MessageSource.
    - 📢 Event publication with ApplicationEventPublisher.
    - 📁 Resource loading (getResource()).

*Real-life analogy*
- Imagine a smart warehouse:
    - Inventory list = bean definitions.
    - Workers = IoC container logic creating and delivering objects.
    - Warehouse manager = ApplicationContext — knows everything about what's inside and how to deliver it.

**Choosing the right type**
| Use case                           | Preferred Context                                                  |
| ---------------------------------- | ------------------------------------------------------------------ |
| Modern Spring Boot app             | **AnnotationConfigServletWebServerApplicationContext** (automatic) |
| Pure Java-based Spring app         | **AnnotationConfigApplicationContext**                             |
| Legacy XML-based app (classpath)   | **ClassPathXmlApplicationContext**                                 |
| Legacy XML-based app (file system) | **FileSystemXmlApplicationContext**                                |
| Web app with Spring MVC (non-Boot) | **WebApplicationContext**                                          |


---
## Dependency Injection (DI)
- Dependency Injection (DI) is a design pattern where an object’s dependencies are provided from outside rather than the object creating them itself.
- In Spring: DI is implemented by the IoC Container (ApplicationContext), which wires beans together based on configuration (XML, annotations, Java config).

**Why “dependencies” matter**
- Dependency = any other object a class needs to do its work.
- Example: A UserService needs a NotificationService to send welcome emails.

*Without DI:*
```java
public class UserService {
    private NotificationService notificationService = new NotificationService();
}
```
- Here UserService creates its own dependency — this is tight coupling.


**Problems with using new keyword**
- When you create dependencies manually:
    - Tight coupling — The class is bound to a specific implementation.
    - Hard to test — You can’t easily swap in mocks or stubs.
    - Difficult to change — Changing an implementation requires code changes.
    - Code duplication — Multiple places may instantiate the same dependency.
    - No lifecycle control — You can’t manage object creation/destruction globally.
    - Configuration is hard — You can’t change values without recompiling code.

**How Dependency Injection solves this**
- With DI, you declare what you need and let Spring inject it.
- Example with DI:
```java
@Service
public class UserService {
    private final NotificationService notificationService;

    @Autowired
    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
}
```
- Spring’s IoC container creates NotificationService and injects it into UserService.
- The UserService class is now decoupled from the object creation process.

**Types of Dependency Injection in Spring**

1. `Constructor Injection` ✅ (Preferred)
- Clear dependencies
- Works well for immutability
- Easier to test

```java
public class OrderService {
    private final PaymentService paymentService;
    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```

2. `Setter Injection`
```java
public class OrderService {
    private PaymentService paymentService;
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
```
- Good for optional dependencies
- Can change dependencies at runtime

3. Field Injection ⚠️ (Not recommended for core logic)
```java
@Autowired
private PaymentService paymentService;
```
- Short and simple, but hides dependencies and is hard to test without Spring.

**Benefits of DI over new keyword**
| Aspect               | `new` Keyword             | DI                                          |
| -------------------- | ------------------------- | ------------------------------------------- |
| Coupling             | Tightly coupled           | Loosely coupled                             |
| Testability          | Hard to mock dependencies | Easy to inject mocks                        |
| Flexibility          | Fixed implementation      | Easy to switch implementations              |
| Lifecycle Management | Manual                    | Managed by Spring                           |
| Configurability      | Hardcoded                 | Externalized (XML, annotations, properties) |


**Real-world analogy**
- Think of a restaurant kitchen:
    - `Without DI`: The chef must go out and buy ingredients himself 🍅🥩 before cooking.
    - `With DI`: The kitchen staff supplies ingredients — the chef just cooks.
    - Result: Faster, cleaner, and chef (business logic) stays focused on cooking (core task).


**Best Practices**
- Prefer constructor injection for required dependencies.
- Use interfaces for dependencies to enable swapping implementations.
- Use @Qualifier if multiple beans implement the same interface.


Keep your services focused — avoid injecting too many dependencies (signals high coupling).
---
## Spring Beans
- In the Spring Framework, a bean is simply an object that is instantiated, assembled, and managed by the Spring IoC (Inversion of Control) container.
- The container takes responsibility for:
    - Creating bean instances
    - Injecting dependencies into beans
    - Configuring beans (properties, scope, etc.)
    - Managing their complete lifecycle
- This concept is at the heart of Spring’s dependency injection mechanism.


**How Beans Are Defined**
- Beans can be declared in three primary ways:

`a) Annotation-based (Modern Approach)`
- Annotations such as @Component, @Service, @Repository, or @Controller tell Spring to scan and register the class as a bean.

```java
@Component
public class PaymentProcessor { }
```

`b) Java-based Configuration`
- Using @Configuration and @Bean, beans can be declared in a class that acts as a factory.
```java
@Configuration
public class AppConfig {
    @Bean
    public PaymentProcessor paymentProcessor() {
        return new PaymentProcessor();
    }
}
```

`c) XML-based Configuration (Legacy)`
- Older Spring apps used XML to define beans.
```xml
<bean id="paymentProcessor" class="com.example.PaymentProcessor"/>

```

**Bean Naming**
- Default name = class name with lowercase first letter (invoiceService)
- Can be overridden using:
```java
@Component("customBeanName")
public class InvoiceService { }
```

### Bean Scopes
- Scope determines how many instances of a bean exist in the container.
- The scope determines how long a bean exists and how it is shared.

| Scope           | Description                                                                                      | Default?  | Availability                 |
| --------------- | ------------------------------------------------------------------------------------------------ | --------- | ---------------------------- |
| **singleton**   | One bean instance per Spring IoC container. All requests for that bean return the same instance. | ✅ Default | All Spring applications      |
| **prototype**   | A new bean instance is created *every time* it is requested from the container.                  | ❌         | All Spring applications      |
| **request**     | A new bean is created for each HTTP request, valid only during that request.                     | ❌         | Web-aware ApplicationContext |
| **session**     | One bean instance per HTTP session.                                                              | ❌         | Web-aware ApplicationContext |
| **application** | One bean instance per `ServletContext`.                                                          | ❌         | Web-aware ApplicationContext |
| **websocket**   | One bean instance per WebSocket session.                                                         | ❌         | Web-aware ApplicationContext |


```java
@Component
@Scope("prototype")
public class MyService { }
```
- If prototype is set, each getBean() call returns a fresh object.

### Spring Bean Life Cycle
- Spring manages beans through well-defined creation and destruction phases.

**Lifecycle Steps**

`1. Instantiation`
- Spring creates the bean instance (using constructor or factory method).

`2. Populate Properties`
- Spring injects dependencies (via setter, field injection, or constructor args).

`3. Bean Name Aware (optional)`
- If the bean implements BeanNameAware, Spring passes the bean's name.

`4. Bean Factory Aware / Application Context Aware (optional)`
- If implementing BeanFactoryAware or ApplicationContextAware, Spring injects the container reference.

`5. Post-Processors: Before Initialization`
- Any BeanPostProcessor’s postProcessBeforeInitialization() is called.

`6. Custom Initialization Method`
- If implementing InitializingBean.afterPropertiesSet() or defining init-method in config, it’s called here.

`7. Post-Processors: After Initialization`
- Any BeanPostProcessor’s postProcessAfterInitialization() is called.

`8. Bean is Ready for Use`
- The bean is now fully managed and can be used by the application.

`9. Destruction Phase`
- When the container shuts down:
- If the bean implements DisposableBean.destroy() or a destroy-method is configured, that runs.
- @PreDestroy methods are also called.

```java
@Component
public class MyBean implements InitializingBean, DisposableBean {

    @PostConstruct
    public void postConstruct() {
        System.out.println("PostConstruct called");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("InitializingBean afterPropertiesSet");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("PreDestroy called");
    }

    @Override
    public void destroy() {
        System.out.println("DisposableBean destroy");
    }
}
```

**Lifecycle Sequence (Singleton Example)**
1. **Instantiate bean**  
2. **Inject dependencies**  
3. **Set Aware interface properties**  
4. **Call** `BeanPostProcessor.postProcessBeforeInitialization()`  
5. **Call** `@PostConstruct` **or** `afterPropertiesSet()`  
6. **Call** `BeanPostProcessor.postProcessAfterInitialization()`  
7. **Use bean**  
8. **Call** `@PreDestroy` **or** `destroy()` **when shutting down**  

### Bean vs POJO in Java

**1. POJO (Plain Old Java Object)**
- A regular Java object without any special restrictions.
- No need to implement framework-specific interfaces.
- Used for representing data or simple business logic.
- Example 

```java
public class Customer {
    private String name;
    // getters and setters
}
```

**2. Spring Bean**
- A Java object that is managed by the Spring container.
- Created, configured, and destroyed by Spring.
- Requires Spring-specific annotations or XML config.
```java
@Component
public class CustomerService {
    public void saveCustomer(Customer customer) {
        // save logic
    }
}
```

| Feature                  | POJO                                              | Spring Bean                                               |
| ------------------------ | ------------------------------------------------- | --------------------------------------------------------- |
| **Definition**           | Plain object with no special framework dependency | Managed object in Spring IoC container                    |
| **Framework Dependency** | None                                              | Requires Spring Framework                                 |
| **Lifecycle Management** | Controlled by developer                           | Controlled by Spring                                      |
| **Configuration**        | No configuration                                  | Configured via annotations, Java config, or XML           |
| **Scope**                | No concept of scope                               | Has multiple scopes (singleton, prototype, request, etc.) |
| **Dependency Injection** | Done manually                                     | Done automatically by Spring                              |
| **Example Use Case**     | Data transfer object                              | Service or DAO in a Spring app                            |


- *Every Spring Bean is a POJO, but not every POJO is a Spring Bean.* The difference lies in container management and framework integration.
