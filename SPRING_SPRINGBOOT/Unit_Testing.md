## Testing
- Testing is the process of evaluating a software application to ensure it:
    - ✅ Works as intended
    - 🐞 Is free from bugs
    - ⚡ Performs efficiently
    - 🔒 Is secure
    - 🎯 Meets user requirements

- 👉 Think of testing like examining a car before selling it 🚗— checking the engine, brakes, lights, and speed to ensure it’s safe and reliable.

### Types of Testing
- There are several categories

`🔹 1. Unit Testing`
- Tests small parts of the application (like functions, methods, classes).
- Done by developers.
- Example: Testing if add(2,3) returns 5.

`🔹 2. Integration Testing`
- Tests how different modules interact with each other.
- Example: Checking if login service connects correctly with the database.

`🔹 3. System Testing`
- Tests the entire system as a whole.
- Example: Running the full e-commerce site and checking if browsing → adding to cart → checkout works.

`🔹 4. Acceptance Testing`
- Done to check if the software meets user/business requirements.
- Example: Client checks if the app works as per agreed features.


`🔹 5. Regression Testing`
- Ensures that new changes haven’t broken old features.
- Example: After adding a “Dark Mode” feature 🌙, checking if login/signup still works.

`🔹 6. Performance Testing`
- Tests how the app behaves under load.
- Example: Can the website handle 10,000 users at once?

`🔹 7. Security Testing`
- Ensures the app is safe from hackers.
- Example: Testing login for SQL injection attacks.


### Unit Testing
- Unit testing is the process of testing small, isolated pieces of code (units like methods, classes, or components).
- Unit Testing = testing the smallest testable parts of an application in isolation.
- Objective: Verify that individual units of logic behave as expected.
- Unit ≠ System: Unlike integration or system tests, unit tests do not depend on databases, APIs, or external services.
- For example:
    - In a calculator app, testing if the add(a, b) method correctly returns the sum.

- 👉 Analogy: Like tasting one ingredient (🍅 tomato, 🧄 garlic) before cooking the whole dish 🍲.

**Benefits of Unit Testing**
- Catches bugs early 🐞 before deployment.
- Makes refactoring safe (code changes won’t break existing logic).
- Improves design (forces modular, loosely coupled code).
- Supports TDD (Test Driven Development): Write tests before writing code.
- Facilitates CI/CD pipelines 🤖 (automation friendly).


**The Testing Pyramid 🏔️**
- Unit Tests (70%) – Small, fast, isolated.
- Integration Tests (20%) – Multiple components working together.
- UI/End-to-End Tests (10%) – Full flow from user interaction.


### Frameworks for Unit Testing in Java
- JUnit → The most widely used testing framework in Java.
- TestNG → Similar to JUnit but with more advanced features (like parallel test execution).
- Mockito → For creating mock objects to test interactions between classes.
- Hamcrest → Provides matchers for writing readable test assertions.
- AssertJ → Fluent assertions for more readable test cases.


### unit testing terminology
`1️⃣ Unit`
- The smallest testable part of code (e.g., a method, class, or function).
- 👉 Example: int add(int a, int b)

`2️⃣ Test Case`
- A single test designed to verify a specific behavior.
- Contains input, expected output, and assertion.
- 👉 Example: testAdditionOfTwoPositiveNumbers()

`3️⃣ Test Suite`
- A collection of test cases that are run together.
- 👉 Example: CalculatorTestSuite may include testAdd(), testSubtract(), testDivide().

`4️⃣ Test Fixture`
- A fixed environment set up for running tests.
- Includes test data, mock objects, config.
- 👉 Example: Creating a Calculator object before each test.
```java
@BeforeEach
void setup() {
    calculator = new Calculator();
}
```

`5️⃣ Test Runner`
- The tool/framework that runs test cases and reports results.
- 👉 Example: JUnit runner, Maven/Gradle, or IDE test runner.

`6️⃣ Assertion`
- A statement that checks expected vs actual result.
- 👉 Example:
`- assertEquals(5, calc.add(2, 3));

`7️⃣ Mock`
- A fake object that imitates a real dependency, while also verifying interactions.
- 👉 Example: Check if emailService.send() was called once.

`8️⃣ Stub`
- A fake object that provides predefined responses (no behavior verification).
- 👉 Example: bankApiStub.transferMoney() always returns true.

`9️⃣ Fake`
- A lightweight implementation that works, but is simplified (not production-ready).
- 👉 Example: An in-memory database instead of a real DB.

`🔟 Spy`
- A partial mock → real object but tracks how it was used.
- 👉 Example: Use real List but check how many times add() was called.

`1️⃣1️⃣ Test Double`
- General term for any fake object used in testing (Stub, Mock, Fake, Spy).
- 🧪 Analogy: Like stunt doubles 🎬 in movies — they "stand in" for the real actors.

`1️⃣2️⃣ Code Coverage`
- Measure of how much production code is executed by tests.
- 👉 Types: Line coverage, Branch coverage, Method coverage.

---
## JUnit
- JUnit is a unit testing framework for Java.
- It helps developers write and run repeatable automated tests.
- Encourages Test Driven Development (TDD).
- Comes with annotations and assertions for easy test writing.
- 👉 Think of JUnit as a toolbox 🧰 for testing small parts of your program.


**Why JUnit?**
- ✅ Ensures code correctness
- 🐞 Detects bugs early
- 🔄 Easy regression testing (re-run tests after changes)
- ⚡ Automates testing (saves time vs manual testing)
- 📊 Integrates with build tools (Maven, Gradle) and CI/CD pipelines (Jenkins, GitHub Actions)
- 👉 Example: Running mvn test automatically executes all JUnit test cases.

**JUnit Architecture**
- JUnit 5 = 3 modules:
    - JUnit Platform → Launching framework (runs tests).
    - JUnit Jupiter → Provides new annotations + test API.
    - JUnit Vintage → Backward compatibility (run JUnit 3/4 tests).
```java
JUnit Platform
 ┣━ JUnit Jupiter (new tests)
 ┗━ JUnit Vintage (old tests)
```

**JUnit Lifecycle Methods ⏳**
- These are methods that run before/after tests:

| Annotation    | Runs when?            | Purpose                                         |
| ------------- | --------------------- | ----------------------------------------------- |
| `@BeforeAll`  | Once before all tests | Setup expensive resources (e.g., DB connection) |
| `@AfterAll`   | Once after all tests  | Cleanup resources                               |
| `@BeforeEach` | Before each test case | Initialize test data                            |
| `@AfterEach`  | After each test case  | Cleanup test data                               |
| `@Test`       | Marks a test method   | The actual test                                 |
| `@Disabled`   | Skips a test          | Used to ignore temporarily                      |


**Assertions in JUnit ✅❌**
- Assertions compare expected vs actual results.
    - assertEquals(expected, actual)
    - assertNotEquals(expected, actual)
    - assertTrue(condition) / assertFalse(condition)
    - assertNull(object) / assertNotNull(object)
    - assertThrows(Exception.class, () -> { ... })
    - assertAll() → Group multiple assertions
- 👉 If assertion fails, test fails 🚨.

**Example: Simple Test Case**
- Code Under Test (Calculator.java)
```java
public class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
    public int divide(int a, int b) {
        return a / b;
    }
}
```

- JUnit Test (CalculatorTest.java)
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    Calculator calc = new Calculator();

    @Test
    void testAddition() {
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    void testDivision() {
        assertEquals(2, calc.divide(10, 5));
    }

    @Test
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }
}
```
- Run the same test with multiple inputs.
```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class ParameterizedExampleTest {

    @ParameterizedTest
    @ValueSource(strings = {"radar", "level", "madam"})
    void testPalindrome(String word) {
        assertTrue(isPalindrome(word));
    }

    boolean isPalindrome(String str) {
        return new StringBuilder(str).reverse().toString().equals(str);
    }
}
```

**JUnit 4 vs JUnit 5**
| Feature       | JUnit 4                        | JUnit 5                      |
| ------------- | ------------------------------ | ---------------------------- |
| Package       | `org.junit`                    | `org.junit.jupiter`          |
| Setup         | `@Before` / `@After`           | `@BeforeEach` / `@AfterEach` |
| Run once      | `@BeforeClass` / `@AfterClass` | `@BeforeAll` / `@AfterAll`   |
| Parameterized | Extra dependency               | Built-in                     |
| Extensions    | Limited                        | Flexible extension model     |

- 👉 Today, JUnit 5 is recommended.

### **Best Practices 🏆**
- Follow AAA pattern → Arrange, Act, Assert.
- Write independent tests (no dependency between them).
- Use meaningful test names → testAdditionOfPositiveNumbers().
- Cover positive, negative, and edge cases.
- Mock external dependencies (use Mockito).
- Run tests in CI/CD pipelines.

---
## Spring Boot Testing
> Spring Boot makes testing easier by providing:
- ✅ Spring TestContext Framework (loads ApplicationContext for tests).
- ✅ Support for JUnit 5 (default since Spring Boot 2.2).
- ✅ Integration with Mockito (mocking dependencies).
- ✅ Annotations for slicing tests (@WebMvcTest, @DataJpaTest, etc.).

**Core Tools**
- JUnit 5 (Jupiter) → Modern Java test framework.
- Mockito → Mocking framework to simulate dependencies.
- AssertJ → Fluent assertion library (assertThat(...)).
- Spring Boot Test Starter → Pre-packaged dependencies:
    - Spring Test
    - Spring Boot Test
    - JUnit
    - Mockito
    - JSONassert / Hamcrest

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

**Structure of a Test**
- Setup (Arrange): Prepare data & dependencies.
- Execution (Act): Call the method under test.
- Assertion (Assert): Verify expected vs actual results.
- Cleanup (Optional): Reset states.

### **Mockito Fundamentals 🧑‍💻**
- Why Mock?
    - Real DB/API calls make tests slow & fragile.
    - Mocks allow you to simulate behavior of dependencies.

- Mockito is a mocking framework for Java.
- Used to create mock objects → simulate dependencies instead of using real ones.
- Useful in Spring Boot for isolating service, repository, or external API calls.

**Key Annotations:**
| Annotation                            | Purpose                                      |
| ------------------------------------- | -------------------------------------------- |
| `@Mock`                               | Creates a mock object                        |
| `@InjectMocks`                        | Injects mock dependencies into a real object |
| `@ExtendWith(MockitoExtension.class)` | Enables Mockito with JUnit 5                 |
| `when(...).thenReturn(...)`           | Stubbing method behavior                     |
| `verify(...)`                         | Verify method interactions                   |


**Spring Boot Testing Annotations**
| Annotation              | Purpose                                            |
| ----------------------- | -------------------------------------------------- |
| `@SpringBootTest`       | Loads full Spring application context              |
| `@WebMvcTest`           | Loads only controller + web layer beans            |
| `@DataJpaTest`          | Loads only JPA components (repositories, entities) |
| `@MockBean`             | Adds a Mockito mock to Spring ApplicationContext   |
| `@AutoConfigureMockMvc` | Enables `MockMvc` for REST API testing             |


**Example 1: Unit Test with Mockito (Service Layer)**
```java
@Repository
public class UserRepository {
    public User findById(Long id);
    public String findUserNameById(int id) {
        return "RealUser"; // Assume DB call
    }
}

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}

// UserServiceTest class 
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserById() {
        User mockUser = new User(1L, "John");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        User result = userService.getUserById(1L);

        assertEquals("John", result.getName());
        verify(userRepository, times(1)).findById(1L);
    }
}
```

**Example 2: Spring Boot Integration Test (REST Controller)**
```java
// Controller
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}


// Test with MockMvc

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetUser() throws Exception {
        User mockUser = new User(1L, "Alice");
        when(userService.getUserById(1L)).thenReturn(mockUser);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
    }
}
```

**Example 3: Repository Test with JPA**
```java
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    void testSaveAndFindUser() {
        User user = new User(null, "Mike");
        User saved = repository.save(user);

        assertThat(repository.findById(saved.getId())).isPresent();
    }
}
```

**Example 4 Test Doubles**
```less
We have a OrderService that:
    Places an order.
    Deducts money from customer’s bank account (via PaymentGateway).
    Sends a confirmation email (via EmailService).

But:
    PaymentGateway is an external API 💳 (slow, costly, not always available).
    EmailService actually sends real emails 📧 (we don’t want that in testing).

👉 So, we use Test Doubles instead of real services.

```
```java
// The Real Service Code
public class OrderService {
    private final PaymentGateway paymentGateway;
    private final EmailService emailService;

    public OrderService(PaymentGateway paymentGateway, EmailService emailService) {
        this.paymentGateway = paymentGateway;
        this.emailService = emailService;
    }

    public String placeOrder(String itemId, double amount) {
        if (paymentGateway.charge(amount)) {
            emailService.sendEmail("Order placed successfully!");
            return "SUCCESS";
        }
        return "FAILED";
    }
}

// Using Stubs: A Stub returns predefined data. 
// 👉 Example: Stub the PaymentGateway to always succeed.
// Used for controlling dependency output.

class PaymentGatewayStub implements PaymentGateway {
    @Override
    public boolean charge(double amount) {
        return true; // Always succeeds
    }
}


// Using Mocks: A Mock verifies if a method was called correctly.
// Used for interaction verification.
@Test
void testOrderWithMockEmail() {
    PaymentGateway paymentStub = amount -> true; // Stub
    EmailService emailMock = mock(EmailService.class); // Mock

    OrderService orderService = new OrderService(paymentStub, emailMock);

    String result = orderService.placeOrder("Book123", 100);

    assertEquals("SUCCESS", result);
    verify(emailMock, times(1)).sendEmail("Order placed successfully!"); // Mock verifies
}

// Using a Fake: A Fake has a working but simplified implementation.
// 👉 Example: In-memory email service (stores messages instead of sending).
// Used for quick but not production-ready testing.

class FakeEmailService implements EmailService {
    List<String> sentMessages = new ArrayList<>();

    @Override
    public void sendEmail(String message) {
        sentMessages.add(message);
    }
}

// Using a Spy: A Spy wraps a real object but also tracks interactions.
// Used when you want real behavior + interaction tracking.
@Test
void testOrderWithSpy() {
    PaymentGateway paymentStub = amount -> true;
    FakeEmailService emailSpy = spy(new FakeEmailService());

    OrderService orderService = new OrderService(paymentStub, emailSpy);

    orderService.placeOrder("Book123", 100);

    verify(emailSpy, times(1)).sendEmail("Order placed successfully!");
}
```

**🚀 Summary**
- `Stub` → Provides fake, fixed output (PaymentGatewayStub).
- `Mock` → Verifies interactions (verify(emailMock)).
- `Fake` → Simplified working version (FakeEmailService).
- `Spy` → Real object + track calls (spy(new FakeEmailService())).
- 👉 In real projects, you’ll mix these doubles to test services without hitting real databases, APIs, or sending emails.

