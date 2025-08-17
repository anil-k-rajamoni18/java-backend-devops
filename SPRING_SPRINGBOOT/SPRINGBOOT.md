## Spring Boot

### 🎯 What is Spring Boot?
- Spring Boot is a framework built on top of the Spring Framework.
- Its main purpose:
    - Simplify configuration ⚙️
    - Provide ready-to-use defaults 🚀
    - Make production-ready apps faster 🏃‍♂️

- 👉 Think of Spring Boot as a “Spring with batteries included 🔋”.
- You don’t need to assemble all parts yourself; Spring Boot provides an opinionated setup.
- 👉 Example:
    - Instead of writing hundreds of XML lines for bean configuration, in Spring Boot you can just use annotations like @SpringBootApplication and it auto-configures things.

---

### 🎯 Why was Spring Boot developed?

**Problems with Traditional Spring:**
1. Complex Configuration: XML hell (hundreds of lines).
2. Server Dependency: Had to manually deploy to Tomcat/Jetty.
3. Dependency Management: Adding libraries individually (Spring MVC, Jackson, JPA, Hibernate, etc.).
4. Boilerplate Code: A lot of redundant setup.

**Spring Boot Solution ✅**
- Convention over configuration → Defaults provided.
- Embedded servers → Run with java -jar.
- Starter dependencies → Pre-bundled dependencies.
- Actuator → Built-in monitoring endpoints.
- Microservices ready → REST APIs made easy.

*👉 Real-world analogy:*
- Spring = Buying a car kit 🛠️ and assembling yourself.
- Spring Boot = Buying a car 🚗 ready to drive.

> Spring Boot = Spring + Auto-Configuration + Embedded Servers + Starter Dependencies + Actuator.

---

### Spring vs Spring Boot
| Feature          | 🌿 Spring Framework            | 🚀 Spring Boot                   |
| ---------------- | ------------------------------ | -------------------------------- |
| **Setup**        | Complex, needs XML/Java config | Easy, auto-configured            |
| **Server**       | Needs external (Tomcat/Jetty)  | Embedded server                  |
| **Dependencies** | Add manually                   | Starter dependencies             |
| **Deployment**   | WAR file on server             | JAR file → `java -jar`           |
| **Monitoring**   | External setup required        | Actuator endpoints               |
| **Best for**     | Large enterprise apps          | Microservices, cloud-native apps |


*👉 Example:*
- Spring app: Needs WAR deployment to Tomcat.
- Spring Boot app: Just run`java -jar myapp.jar`.

---

### How to Setup a Project?

**🔹 Method 1: Spring Initializr (most common)**
1. Go to 👉 start.spring.io
2. Choose:
    - Project: Maven/Gradle
    - Language: Java/Kotlin
    - Spring Boot version
    - Dependencies (Spring Web, JPA, MySQL, etc.)
3. Download and import into IDE (IntelliJ, Eclipse, VSCode).
4. Run DemoApplication.java with @SpringBootApplication.

*👉 Example:*
```java
@SpringBootApplication  
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

**🔹 Method 2: IDE Plugins**
- IntelliJ / Eclipse → “New Spring Starter Project”.

**🔹 Method 3: CLI**
```bash
spring init --dependencies=web,data-jpa,mysql demo-app
```
---

### 📦 Starter Dependencies & Other Dependencies
> Starter Dependencies = Pre-defined dependency bundles to kickstart projects.

**⭐ Common Starter Dependencies**
- spring-boot-starter-web 🌐 → REST APIs, Spring MVC, embedded Tomcat.
- spring-boot-starter-data-jpa 💾 → Database access (Hibernate + JPA).
- spring-boot-starter-security 🔐 → Security + Authentication.
- spring-boot-starter-thymeleaf 🎨 → Web UI templating.
- spring-boot-starter-actuator 📊 → Monitoring & health checks.
- spring-boot-starter-test 🧪 → JUnit + Mockito + Spring Test.

*👉 Real-time Example:*
- You want to build a shopping app 🛍️. Instead of adding:
    - Spring MVC
    - Jackson
    - Tomcat
    - Validation libs
- You just add:
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
- ✅ Everything included.
---
### Spring Architecture
![](https://docs.spring.io/spring-framework/docs/3.0.x/spring-framework-reference/html/images/spring-overview.png)

- Spring follows a layered architecture. At its core:

```less
   Application Layer (Controllers, Services, Repositories)
   ↓
   Core Container (IoC, Dependency Injection)
   ↓
   AOP (Aspect Oriented Programming)
   ↓
   Data Access/Integration (JDBC, ORM, JMS)
   ↓
   Web (Spring MVC, WebFlux)
   ↓
   Test (JUnit, Mock objects)
```

**Core Container:**
- Beans → Defines objects (beans).
- Core → Dependency Injection.
- Context → ApplicationContext for bean lifecycle.
- Expression Language → Dynamic value injection.

**Data Access/Integration:**
- JDBC, ORM, JPA support.

**Web Layer:**
- Spring MVC (Servlet API based).
- Spring WebFlux (Reactive).

**AOP (Aspect Oriented Programming):**
- Cross-cutting concerns (logging, security, transactions).

**Testing:**
- Support for JUnit, TestNG, Mockito, Spring TestContext.

*👉 Real-world analogy:*
- Spring = A big toolkit 🧰 → you pick modules as needed. But you must assemble it manually.

---

### 🚀 Spring Boot Architecture
- Spring Boot builds on top of Spring and provides auto-configuration, embedded servers, and starter dependencies.
```less
Client (Browser/Postman)
   ↓
Controller (@RestController)
   ↓
Service (@Service)
   ↓
Repository (@Repository)
   ↓
Database (MySQL/Postgres)

--- Managed by ---
Spring Boot Framework
   ↓
Spring Framework (IoC, DI, AOP, MVC, Data)
   ↓
AutoConfiguration (based on classpath)
   ↓
Embedded Server (Tomcat/Jetty/Undertow)
   ↓
SpringApplication (Bootstraps everything)
```
**🔹 Components of Spring Boot Architecture**
1. Spring Boot Starters 📦 → Bundled dependencies.
2. Spring Boot AutoConfiguration ⚙️ → Configures beans automatically based on classpath.
3. Spring Boot CLI 💻 → Quick project scaffolding.
4. Spring Boot Actuator 📊 → Production monitoring.
5. Embedded Servers 🌐 → Tomcat, Jetty, Undertow.
6. SpringApplication 🏁 → The entry point for bootstrapping the app.

*👉 Real-world analogy:*
- Spring Boot = Uber 🚖 → Everything (car, driver, fuel, GPS) is ready. You just sit and go.

### **Internal Working of SpringApplication.run()**
- When you start a Spring Boot app:
```java
@SpringBootApplication
public class DemoApplication {
   public static void main(String[] args) {
      SpringApplication.run(DemoApplication.class, args);
   }
}
```

**🛠️ Step-by-Step Execution**
1. Application startup
    - SpringApplication.run() is called.
    - Creates a SpringApplication instance.

2. Prepare Environment 🌍
    - Reads command-line args, application.properties, YAML, system props, env vars.
    - Creates an Environment object with these values.

3. Banner Display 🎉
    - Shows Spring Boot banner (can be customized).

4. ApplicationContext Creation 🏗️
    - Decides context type:
        - AnnotationConfigApplicationContext for non-web apps.
        - AnnotationConfigServletWebServerApplicationContext for web apps.
    - Creates an IoC container.

5. Auto-Configuration ⚡
    - Based on classpath scanning (e.g., if spring-boot-starter-web is found → configure DispatcherServlet,Tomcat).
    - Reads META-INF/spring.factories → loads EnableAutoConfiguration classes.

6. Component Scan 🔎
    - Scans packages for @Component, @Service, @Repository, @Controller, @RestController.
    - Registers them as beans in IoC container.

7. Bean Initialization 🫘
    - Lifecycle hooks executed (@PostConstruct, InitializingBean, etc.).
    - Dependency injection happens.

8. Embedded Web Server Start 🌐
    - If web app, starts Tomcat/Jetty/Undertow.
    - Binds it to configured port (default: 8080).

9. CommandLineRunners / ApplicationRunners ▶️
    - Executes any beans that implement these interfaces.

10. Application Ready ✅
    - Logs: “Started DemoApplication in X seconds”.
    - Application now listens for requests.

*👉 Simplified Flow:*
```less
SpringApplication.run() =
Initialize → Environment → ApplicationContext → AutoConfig → Bean Init → Start Server → Run App
```
---

## 📦 Spring Boot Build JAR Structure
- When you package a Spring Boot app (mvn clean package or gradle build), it creates a self-contained JAR that you can run with:
```java
java -jar myapp-0.0.1-SNAPSHOT.jar
```
- This JAR includes everything needed (your code + dependencies + embedded server).
- Spring Boot JAR (also called a “fat JAR” or “über JAR”)

**📂 Typical Spring Boot JAR Layout**
```less
myapp-0.0.1-SNAPSHOT.jar
│
├── META-INF/                      # Metadata
│   ├── MANIFEST.MF                # Entry point info (Main-Class, Start-Class)
│   └── spring.factories           # Auto-configuration hints
│
├── BOOT-INF/                      # Special folder (Spring Boot convention)
│   ├── classes/                   # Your compiled classes & resources
│   │   ├── com/example/demo/...   # Your Java packages
│   │   └── application.properties # Config files
│   │
│   ├── lib/                       # All dependent JARs (Spring, Hibernate, Jackson, etc.)
│   │   ├── spring-web-6.0.x.jar
│   │   ├── hibernate-core-6.x.jar
│   │   ├── mysql-connector-java.jar
│   │   └── ...
│   │
│   └── classpath.idx              # Index file for classpath
│
└── org/springframework/boot/loader/   # Boot loader classes
    ├── JarLauncher.class
    ├── WarLauncher.class
    └── ExecutableArchiveLauncher.class

```

**1️⃣ META-INF/**
- Contains MANIFEST.MF
- Defines:
    - Main-Class → org.springframework.boot.loader.JarLauncher
    - Start-Class → Your @SpringBootApplication main class

```text
Main-Class: org.springframework.boot.loader.JarLauncher
Start-Class: com.example.demo.DemoApplication
```

**2️⃣ BOOT-INF/classes/**
- All your compiled code (.class files).
- Your application.properties or application.yml.
- Any static resources under src/main/resources.

```swift
BOOT-INF/classes/com/example/demo/ProductController.class
BOOT-INF/classes/application.yml
```


**3️⃣ BOOT-INF/lib/**
- All dependency JARs required by your app.
- Example: Spring Web, Jackson, Hibernate, Database Driver.

**4️⃣ Spring Boot Loader Classes**
- Bootstraps the app (special JarLauncher classes).
- Handles custom classloading so that:
    - It can read JARs inside JAR.
    - You can run java -jar app.jar without extracting.

**⚙️ How Execution Works**
1. java -jar app.jar → JVM looks at META-INF/MANIFEST.MF.
2. JarLauncher (from Boot Loader) starts.
3. Boot loader sets up classpath from BOOT-INF/lib + BOOT-INF/classes.
4. Finds your Start-Class (your @SpringBootApplication).
5. Runs SpringApplication.run().
6. Application starts (with embedded Tomcat/Jetty).

*🏷️ Real-time Example*
- Suppose you build a Spring Boot E-commerce API with:
    - Spring Web 🌐
    - Spring Data JPA 💾
    - MySQL Driver 🐬

- Your JAR will contain:
    - BOOT-INF/classes/ → Your ProductController, ProductService, ProductRepo.
    - BOOT-INF/lib/ →
        - spring-web-6.x.jar
        - spring-data-jpa-3.x.jar
        - hibernate-core-6.x.jar
        - mysql-connector-java-8.x.jar

- All bundled into one executable JAR.

**In short:**
```text
A Spring Boot JAR =
👉 Your Code + Config + Resources (BOOT-INF/classes)
👉 All Dependencies (BOOT-INF/lib)
👉 Boot Loader (launcher classes)
👉 Metadata (MANIFEST)
```

#### ⚖️ Fat JAR vs Thin JAR vs WAR
| Feature 🔎                 | **WAR (Traditional)**                      | **Fat JAR (Spring Boot default)**         | **Thin JAR (Alternative)**                          |
| -------------------------- | ------------------------------------------ | ----------------------------------------- | --------------------------------------------------- |
| **Contains Dependencies?** | ❌ No (server provides libs)                | ✅ Yes (all in `BOOT-INF/lib`)             | ❌ No (download at runtime)                          |
| **Embedded Server?**       | ❌ Needs external (Tomcat, JBoss, WebLogic) | ✅ Includes embedded Tomcat/Jetty/Undertow | ✅ Includes embedded server                          |
| **Run Method**             | Deploy on server → `Tomcat/webapps/`       | `java -jar myapp.jar`                     | `java -jar myapp-thin.jar` (downloads dependencies) |
| **Portability**            | Limited (depends on app server)            | High (runs anywhere with JVM)             | Medium (depends on internet/cache)                  |
| **Build Size**             | Small (no dependencies inside)             | Large (all dependencies inside)           | Small (only your code)                              |
| **Best For**               | Legacy enterprise apps                     | Microservices, cloud-native apps          | CI/CD optimization, dependency sharing              |


---

### Spring Boot Annotations

**1️⃣ Core & Configuration Annotations**
- @SpringBootApplication → Entry point (combo of @Configuration, @EnableAutoConfiguration, @ComponentScan).
- @Configuration → Bean definitions.
- @Bean → Declare beans manually.
- @ComponentScan → Auto-detect beans.

*👉 Example:*
```java
@SpringBootApplication
public class MyApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApp.class, args);
    }
}
```
**2️⃣ Stereotype (Layer-specific) Annotations**
- @Component → Generic bean.
- @Service → Service layer.
- @Repository → DAO layer.
- @Controller → MVC controller.
- @RestController → REST controller (@Controller + @ResponseBody).

*👉 Example*
```java
@Service
public class ProductService { ... }

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> { ... }

@RestController
@RequestMapping("/api/products")
public class ProductController { ... }
```

**3️⃣ Web & REST Annotations**
- @RequestMapping("/path") → General mapping.
- @GetMapping, @PostMapping, @PutMapping, @DeleteMapping → REST APIs.
- @PathVariable → Extract path param.
- @RequestParam → Extract query param.
- @RequestBody → JSON → Object.
- @ResponseBody → Object → JSON.
```java
@GetMapping("/users/{id}")
public User getUser(@PathVariable Long id) { ... }
```

**4️⃣ JPA & Database Annotations**
- @Entity → Marks class as DB entity.
- @Table, @Id, @GeneratedValue, @Column → DB mapping.
- @Transactional → Transaction management.

*👉 Example:*
```java
@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
}
```

**5️⃣ Spring Boot Features**
- @EnableAutoConfiguration → Enables auto config.
- @EnableScheduling ⏰ → For cron jobs.
- @EnableCaching ⚡ → Enables caching.
- @EnableAsync 🔄 → Async execution.

**6️⃣ Testing Annotations**
- @SpringBootTest → Boots whole Spring context.
- @WebMvcTest → Test only web layer.
- @DataJpaTest → Test JPA repos only.
- @MockBean → Inject mock objects.


### Spring Boot Actuator (Production Ready)
- Provides health monitoring, metrics, logs, etc.
- Endpoints like:
    - /actuator/health ✅
    - /actuator/metrics 📈
    - /actuator/env ⚙️

