## Level 1: Spring Core Basics

**Goal:** Understand basic IoC and bean creation.

## 1. XML-based Bean Configuration
- Create a `Car` class with fields `brand` and `speed`.
- Configure it as a bean using `beans.xml`.
- Load the bean from `ClassPathXmlApplicationContext` and print its properties.

## 2. Java-based Configuration (Annotation)
- Create a `Bike` class.
- Use `@Configuration` and `@Bean` to register it in Spring.
- Retrieve the bean from `AnnotationConfigApplicationContext`.

## 3. Constructor vs Setter Injection
- Create a `User` class with `name` and `Address` dependency.
- Demonstrate **constructor injection** and **setter injection** in XML config.
- Print the values to verify injection.

---

## Level 2: Bean Scopes & Lifecycle

**Goal:** Explore bean scopes, init/destroy methods, and lifecycle callbacks.

## 4. Singleton vs Prototype Scope
- Create a `RandomNumberService` that returns a random number in its constructor.
- Configure it as **singleton** and **prototype** beans.
- Retrieve the bean multiple times and observe the difference.

## 5. Init and Destroy Methods
- Create a `DatabaseConnection` class with `init()` and `close()` methods.
- Configure them in XML using `init-method` and `destroy-method`.
- Test with `registerShutdownHook()` to see destroy method call.

## 6. `@PostConstruct` and `@PreDestroy`
- Create a `MailService` bean.
- Annotate init and cleanup methods with `@PostConstruct` and `@PreDestroy`.
- Verify behavior in annotation-based config.

---

## Level 3: Dependency Injection Variants

**Goal:** Work with different injection types and qualifiers.

## 7. `@Autowired` with Multiple Beans
- Create two beans: `PaypalPayment` and `StripePayment` implementing `PaymentService`.
- Autowire `PaymentService` in a `ShoppingCart` class.
- Use `@Qualifier` to pick one.

## 8. Injecting Collections
- Create a `Playlist` bean that contains a `List<Song>`.
- Inject the list using XML or `@Autowired`.
- Iterate and print all songs.

## 9. Using `@Value` for Properties
- Store app settings in `application.properties`.
- Inject values into a bean using `@Value("${propertyName}")`.

---

## Level 4: Advanced Bean Wiring

**Goal:** Master bean post-processors, factory beans, and profiles.

## 10. BeanFactoryPostProcessor
- Create a `PropertyOverrideBeanFactoryPostProcessor` example.
- Override bean property values before initialization.

## 11. FactoryBean
- Implement a custom `FactoryBean<Date>` that returns current date.
- Retrieve the bean and verify the date.

## 12. Spring Profiles
- Create `DevDatabaseConfig` and `ProdDatabaseConfig` with different DB URLs.
- Load a profile using `-Dspring.profiles.active=dev` and verify output.

---

## 💡 Bonus Challenge: Mini Project

**Goal:** Put all concepts together.

Create a `LibraryManagementSystem`:
- `BookService` (prototype scope, loads books from a fake repo)
- `NotificationService` (singleton, sends email/SMS)
- Use `@Autowired`, `@Qualifier`, `@Value` from properties file.
- Have lifecycle methods log when beans are created/destroyed.
