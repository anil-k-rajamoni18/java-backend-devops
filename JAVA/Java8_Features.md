# 📘 Java 8 Features
- Java 8 is a major release of the Java programming language by Oracle (March 2014).
- It introduced functional programming features to Java, making code more concise, readable, and parallel-friendly.
- Emphasis on behavior passing, stream processing, and immutable data structures


**📋 List of Java 8 Features**
| Feature                   | Description                                        |
| ------------------------- | -------------------------------------------------- |
| Lambda Expressions        | Treat code as data (functions as objects)          |
| Functional Interfaces     | Interface with a single abstract method (SAM)      |
| Default & Static Methods  | Methods in interfaces with default/static impl     |
| Method References         | Shortcut to refer method with `::`                 |
| Streams API               | Process collections in a functional style          |
| Optional Class            | Handle null values without `NullPointerException`  |
| New Date and Time API     | Better date/time handling (immutable, thread-safe) |
| Nashorn JavaScript Engine | Execute JavaScript code in JVM                     |
| Collectors                | Combine the result of Stream processing            |
| Parallel Streams          | Perform operations concurrently                    |


**❓ Why Java 8 Was Introduced**
| Problem in Java 7 & Earlier       | Java 8 Solution                   |
| --------------------------------- | --------------------------------- |
| Verbose anonymous classes         | Lambda Expressions                |
| Lack of functional programming    | Functional Interfaces + Lambdas   |
| Poor Stream/data pipeline support | Streams API                       |
| NullPointerExceptions everywhere  | Optional Class                    |
| Old Date API was buggy            | New Date & Time API (`java.time`) |
| No behavior passing               | Method references + Lambdas       |
| No concurrent collection handling | Parallel Streams                  |


---
## 🔶 1. Lambda Expressions
- A Lambda Expression is a short block of code which takes in parameters and returns a value. 
- It’s like a method, but without a name.
- Syntax:
```java
(parameters) -> { expression or block of code }
```
- Example:
```java
(int a, int b) -> { return a + b; }
```

**Why Use Lambda Expressions?**
- Reduce boilerplate code
- Enhance readability
- Allow behavior to be passed as parameter
- Support functional programming


**Functional Interface Requirement**
- Lambda expressions can only be used with functional interfaces (interfaces with a single abstract method).

```java
@FunctionalInterface
interface MyInterface {
    void sayHello();  // Single abstract method
}
```

**Lambda Syntax Variants**
| Lambda Type         | Syntax Example                               |
| ------------------- | -------------------------------------------- |
| No parameters       | `() -> System.out.println("Hello")`          |
| Single parameter    | `x -> x * x`                                 |
| Multiple parameters | `(x, y) -> x + y`                            |
| Block body          | `(x, y) -> { int sum = x + y; return sum; }` |
| With return keyword | `(a, b) -> { return a * b; }`                |
| No return (void)    | `x -> System.out.println(x)`                 |

**Runnable (No Parameter)**
```java
// Regular:
Runnable r = new Runnable() {
    public void run() {
        System.out.println("Running...");
    }
};

// Lambda:
Runnable r = () -> System.out.println("Running...");

```

**Comparator (Two Parameters, Returns Int)**
```java
// Regular:
Comparator<String> comp = new Comparator<String>() {
    public int compare(String a, String b) {
        return a.compareTo(b);
    }
};

// Lambda:
Comparator<String> comp = (a, b) -> a.compareTo(b);

```

**Consumer (One Parameter, No Return)**
```java
// Regular:
Consumer<String> printer = new Consumer<String>() {
    public void accept(String s) {
        System.out.println(s);
    }
};


// Lambda:
Consumer<String> printer = s -> System.out.println(s);
```

**Function (One Parameter, Returns Value)**
```java
// Regular:
Function<Integer, Integer> square = new Function<Integer, Integer>() {
    public Integer apply(Integer x) {
        return x * x;
    }
};

// Lambda:
Function<Integer, Integer> square = x -> x * x;
```

**Predicate (One Parameter, Returns Boolean)**
```java
// Regular:
Predicate<String> isLong = new Predicate<String>() {
    public boolean test(String s) {
        return s.length() > 5;
    }
};


// Lambda:
Predicate<String> isLong = s -> s.length() > 5;
```




**Examples**
```java
// Example 1: Simple Lambda with No Parameters
Runnable r = () -> System.out.println("Hello from Lambda!");
r.run();


// Example 2: Lambda with One Parameter
Consumer<String> printer = name -> System.out.println("Hello " + name);
printer.accept("Java");


// Example 3: Lambda with Multiple Parameters and Return Value
BinaryOperator<Integer> sum = (a, b) -> a + b;
System.out.println("Sum: " + sum.apply(10, 20));  // Output: 30


//  Example 4: Using Lambda in Collections
List<String> names = Arrays.asList("John", "Alice", "Bob");
names.forEach(name -> System.out.println("Name: " + name));

```

**Behind the Scenes (How It Works)**
- When you write:
```java
Runnable r = () -> System.out.println("Hello");
```
> The compiler creates an anonymous class instance that implements Runnable and overrides run() with the lambda body.


**🔹 Method Reference vs Lambda**
```java
// Lambda:

list.forEach(item -> System.out.println(item));


// Method Reference:
list.forEach(System.out::println);


```

**🔹 Benefits of Lambda Expressions**
| Benefit                    | Description                           |
| -------------------------- | ------------------------------------- |
| Concise Code               | Removes boilerplate anonymous classes |
| Functional Programming     | Enables passing behavior              |
| Better Collection Handling | Works seamlessly with Streams API     |
| Improved Readability       | More expressive and readable          |


**🔹 Lambda Use Cases**
- Iterating collections (forEach)
- Filtering & mapping using Streams
- GUI Event Handlers
- Runnable and Callable tasks
- Comparators for sorting


---
## Method References
- A method reference is a shorthand notation of a lambda expression to call a method directly by its name.
- It is used to refer to a method without executing it.
- It improves code readability and cleanliness when the lambda only calls an existing method.
- Syntax
```java
ClassName/ObjectName::methodName
```

- Equivalent Lambda:
```java
(args) -> object.method(args)
```


**Why Use Method References?**
- Less boilerplate than lambdas
- Improves readability
- Works only with functional interfaces


**🔸 Types of Method References**
| Type                                                                           | Syntax                      | Example               | Equivalent Lambda            |
| ------------------------------------------------------------------------------ | --------------------------- | --------------------- | ---------------------------- |
| 1. Reference to a static method                                                | `ClassName::staticMethod`   | `Math::abs`           | `x -> Math.abs(x)`           |
| 2. Reference to an instance method of a particular object                      | `object::instanceMethod`    | `System.out::println` | `x -> System.out.println(x)` |
| 3. Reference to an instance method of an arbitrary object of a particular type | `ClassName::instanceMethod` | `String::toLowerCase` | `s -> s.toLowerCase()`       |
| 4. Reference to a constructor                                                  | `ClassName::new`            | `Employee::new`       | `() -> new Employee()`       |



**Static Method Reference**
```java
class Utility {
    public static void greet(String name) {
        System.out.println("Hello, " + name);
    }
}
Consumer<String> greeter = Utility::greet; // Equivalent: Consumer<String> greeter = name -> Utility.greet(name);
greeter.accept("Alice");  // Output: Hello, Alice
```

**Instance Method of a Particular Object**
```java
List<String> names = Arrays.asList("John", "Jane", "Jack");
names.forEach(System.out::println); // Equivalent: names.forEach(name -> System.out.println(name));
```

**Instance Method of an Arbitrary Object of a Particular Type**
```java
List<String> names = Arrays.asList("JAVA", "SPRING", "BOOT");
names.stream()
     .map(String::toLowerCase) // Equivalent: .map(str -> str.toLowerCase())
     .forEach(System.out::println);

```

**Constructor Reference**
```java
interface EmployeeFactory {
    Employee create(int id, String name);
}

class Employee {
    int id;
    String name;
    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
EmployeeFactory factory = Employee::new; // Equivalent: EmployeeFactory factory = (id, name) -> new Employee(id, name);
Employee e = factory.create(101, "Tom");

```

**🧠 When to Use Method References Instead of Lambdas**
- The lambda only calls a method.
- The method signature matches the functional interface's method.
- You want cleaner, shorter code.


---
## Java 8 Streams API
> A Stream is a sequence of elements that supports functional-style operations on collections (like map, filter, reduce).

- Streams API allows us to process collections (like List, Set) in a declarative, functional, and chainable way.
- Introduced in Java 8 in java.util.stream package.
- Streams don’t store data; they operate on data from a source (like a Collection, array).
- Streams are lazy, meaning computation happens only when needed.


**🎯 Why Use Streams?**
| Without Streams           | With Streams                        |
| ------------------------- | ----------------------------------- |
| Imperative (loops, ifs)   | Declarative (what to do, not how)   |
| Verbose and error-prone   | Short, readable, chainable code     |
| Not easily parallelizable | Parallelism with `.parallel()`      |
| External iteration        | Internal iteration (handled by JVM) |


### **Methods of Stream**

- Intermediary (Intermediate) Methods – return Stream

```java
| Method       | Description                           |
| ------------ | ------------------------------------- |
| `filter()`   | Filters elements based on a condition |
| `map()`      | Transforms each element               |
| `sorted()`   | Sorts the stream                      |
| `distinct()` | Removes duplicates                    |
| `limit(n)`   | Limits to first n elements            |
| `skip(n)`    | Skips first n elements                |
| `peek()`     | Used for debugging (like `forEach`)   |
```

- Terminal Methods – return a result or side effect

| Method                    | Description                            |
| ------------------------- | -------------------------------------- |
| `forEach()`               | Performs action on each element        |
| `collect()`               | Converts Stream into List/Set/Map etc. |
| `toArray()`               | Converts Stream to array               |
| `reduce()`                | Reduces stream to a single value       |
| `count()`                 | Returns number of elements             |
| `min()`/`max()`           | Returns min/max using Comparator       |
| `anyMatch()`/`allMatch()` | Matching elements based on Predicate   |


### Different Ways to Create Stream

🔹 Using Stream.of()
```java
Stream<String> stream = Stream.of("A", "B", "C");
```

🔹 Using Stream.generate() or Stream.iterate()
```java
Stream<Integer> stream = Stream.iterate(1, n -> n + 1).limit(5);
```
🔹 From Collections
```java
List<String> list = Arrays.asList("A", "B", "C");
Stream<String> stream = list.stream();
```

🔹 From Arrays
```java
int[] arr = {1, 2, 3};
IntStream stream = Arrays.stream(arr);
```


**Functional Interfaces Used in Streams**
| Interface           | Used With                    | Description                  |
| ------------------- | ---------------------------- | ---------------------------- |
| `Predicate<T>`      | `filter()`                   | Returns boolean              |
| `Function<T,R>`     | `map()`                      | Converts T to R              |
| `Consumer<T>`       | `forEach()`                  | Performs an action           |
| `Supplier<T>`       | `generate()`                 | Supplies elements            |
| `BinaryOperator<T>` | `reduce()`                   | Combines two values into one |
| `Comparator<T>`     | `sorted()`, `min()`, `max()` | Compare elements             |


**Example**
```java
List<String> names = Arrays.asList("John", "Jane", "Jack", "Jill");

// Filter names starting with 'J', convert to uppercase, and collect
List<String> result = names.stream()
    .filter(name -> name.startsWith("J"))
    .map(String::toUpperCase)
    .sorted()
    .collect(Collectors.toList());

System.out.println(result);  // [JACK, JANE, JILL, JOHN]
```

---
## Collectors (java.util.stream.Collectors)
- Used with collect() terminal operation to transform stream to collection or summary result.

**Common Collector Methods:**
| Method                | Description                              |
| --------------------- | ---------------------------------------- |
| `toList()`, `toSet()` | Collect elements into a List or Set      |
| `joining()`           | Join strings                             |
| `groupingBy()`        | Group elements by a classifier           |
| `partitioningBy()`    | Partition elements by a predicate        |
| `counting()`          | Count elements                           |
| `summarizingInt()`    | Summary stats: count, min, max, avg, sum |


```java
List<String> names = Arrays.asList("Apple", "Banana", "Avocado");

String joined = names.stream()
    .collect(Collectors.joining(", "));  // Apple, Banana, Avocado
```

---
## Parallel Streams
- Used to process data in parallel threads, improves performance with large datasets.
- Can be created by:
```java
  list.parallelStream()
```

- Automatically splits data into chunks and processes them concurrently.
- Example:
```java
list.parallelStream()
    .filter(s -> s.length() > 3)
    .forEach(System.out::println);
```

**⚠️ Note:**
- Use parallelStream() only when:
    - Data size is large
    - Task is CPU-intensive
    - Order of execution is not important

---
### Stream API – Real-Time Examples
```java
class Employee {
    int id;
    String name;
    double salary;
    String department;

    Employee(int id, String name, double salary, String department) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
    }

    public String toString() {
        return id + " - " + name + " - " + salary + " - " + department;
    }

    public double getSalary() { return salary; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
}


// Let's assume this list:
List<Employee> employees = Arrays.asList(
    new Employee(101, "Amit Sharma", 55000, "IT"),
    new Employee(102, "Priya Verma", 62000, "Finance"),
    new Employee(103, "Ravi Kumar", 48000, "HR"),
    new Employee(104, "Sneha Iyer", 75000, "Marketing"),
    new Employee(105, "Ankit Gupta", 42000, "IT"),
    new Employee(106, "Neha Joshi", 83000, "Finance"),
    new Employee(107, "Rahul Mehta", 51000, "Sales"),
    new Employee(108, "Kavita Reddy", 69000, "Marketing"),
    new Employee(109, "Manish Singh", 57000, "HR"),
    new Employee(110, "Divya Nair", 61000, "Sales")
);

```

1. Q: List all employees in the "IT" department
```java
List<Employee> itEmployees = employees.stream()
    .filter(e -> e.getDepartment().equals("IT"))
    .collect(Collectors.toList());
```

 2. Q: Get names of all employees in uppercase
 ```java
List<String> upperNames = employees.stream()
    .map(e -> e.getName().toUpperCase())
    .collect(Collectors.toList());
 ```
 
 3. Q: Get list of departments without duplicates
 ```java
List<String> departments = employees.stream()
    .map(Employee::getDepartment)
    .distinct()
    .collect(Collectors.toList());
 ```

4. Q: Get employee with the highest salary
```java
Optional<Employee> highest = employees.stream()
    .max(Comparator.comparing(Employee::getSalary));
```

5. Q: Get total salary of all employees
```java
double totalSalary = employees.stream()
    .map(Employee::getSalary)
    .reduce(0.0, Double::sum);
```

6. Q: Count employees in each department
```java
Map<String, Long> countByDept = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
```

7. Q: Group employees by department
```java
Map<String, List<Employee>> grouped = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));
```

 8. Q: Partition employees by salary > 60000
 ```java
Map<Boolean, List<Employee>> partitioned = employees.stream()
    .collect(Collectors.partitioningBy(e -> e.getSalary() > 60000));
 ```

9. Q: Get average salary of all employees
```java
double avgSalary = employees.stream()
.collect(Collectors.averagingDouble(Employee::getSalary));
```

10. Q: Get employee names joined with comma
```java
String joinedNames = employees.stream()
    .map(Employee::getName)
    .collect(Collectors.joining(", "));
```

11. Q: Check if any employee is in “Admin” department
```java
boolean anyAdmin = employees.stream()
.anyMatch(e -> e.getDepartment().equals("Admin"));

```

12. Q: Are all employees earning above 40000?
```java
boolean allAbove40k = employees.stream()
    .allMatch(e -> e.getSalary() > 40000);
```

13. Q: Print top 3 highest-paid employees
```java
List<Employee> top3 = employees.stream()
    .sorted(Comparator.comparing(Employee::getSalary).reversed())
    .limit(3)
    .collect(Collectors.toList());
```

14. Q: Skip top 2 earners and get the rest
```java
List<Employee> rest = employees.stream()
    .sorted(Comparator.comparing(Employee::getSalary).reversed())
    .skip(2)
    .collect(Collectors.toList());
```

15. Q: Create a Set of all employee names
```java
Set<String> nameSet = employees.stream()
    .map(Employee::getName)
    .collect(Collectors.toSet());
```

16. Q: Find min and max salary using
```java
DoubleSummaryStatistics stats = employees.stream()
    .collect(Collectors.summarizingDouble(Employee::getSalary));

System.out.println("Min: " + stats.getMin());
System.out.println("Max: " + stats.getMax());
System.out.println("Avg: " + stats.getAverage());
```

17. Q: Print names of employees using forEach
```java
employees.stream()
    .map(Employee::getName)
    .forEach(System.out::println);
```

18. Q: Debug stream using peek() before filtering
```java
List<Employee> filtered = employees.stream()
    .peek(e -> System.out.println("Before filter: " + e))
    .filter(e -> e.getSalary() > 60000)
    .peek(e -> System.out.println("After filter: " + e))
    .collect(Collectors.toList());
```

 19. Q: Use parallelStream() to list employees in Finance dept
 ```java
employees.parallelStream()
    .filter(e -> e.getDepartment().equals("Finance"))
    .forEach(System.out::println);
 ```

 20. Q: Get department-wise average salary
 ```java
Map<String, Double> avgByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.averagingDouble(Employee::getSalary)
    ));
 ```

 ---

 ## 📦 Optional Class
- Optional<T> is a container object used to avoid NullPointerException. It may or may not contain a non-null value.
- Introduced in: Java 8
- Package: java.util

**🔹 Why Optional?**
- Helps in writing cleaner, null-safe code
- Eliminates the need for explicit null checks
- Promotes functional programming practices

**🔹 Common Methods in Optional**
| Method                | Description                           |
| --------------------- | ------------------------------------- |
| `of(value)`           | Returns Optional with non-null value  |
| `ofNullable(value)`   | Allows null or non-null value         |
| `empty()`             | Returns an empty Optional             |
| `isPresent()`         | Returns true if value is present      |
| `ifPresent(Consumer)` | Executes action if value exists       |
| `get()`               | Returns value (throws if empty!)      |
| `orElse(default)`     | Returns value or default              |
| `orElseGet(Supplier)` | Lazy default value                    |
| `orElseThrow()`       | Throws exception if value not present |
| `map(Function)`       | Transforms value if present           |
| `flatMap(Function)`   | Like map but avoids nested Optionals  |

**Example**
```java
Optional<String> name = Optional.of("Amit");

// Basic usage
System.out.println(name.isPresent()); // true
System.out.println(name.get());       // Amit

// Safe retrieval
String value = name.orElse("Default");

// Functional style
name.ifPresent(n -> System.out.println(n.toUpperCase()));

```

**Example: Null Safety with Optional**
```java
public Optional<Employee> findById(int id) {
    return employeeRepo.stream()
        .filter(e -> e.getId() == id)
        .findFirst(); // returns Optional<Employee>
}

// Usage
Optional<Employee> result = findById(101);

result.ifPresent(emp -> System.out.println(emp.getName()));

```

---
## 🗓️ New Date and Time API – `java.time`

**🔹 Why new API?**
- Old APIs like Date and Calendar were:
    - Mutable (not thread-safe)
    - Poorly designed with confusing month indexes
    - Lacked timezone and formatting support

**🔹 New Package: java.time**
- Immutable, thread-safe classes
- Clear, readable, well-structured

**🔹 Key Classes**
| Class               | Description                                 |
| ------------------- | ------------------------------------------- |
| `LocalDate`         | Date only (no time)                         |
| `LocalTime`         | Time only (no date)                         |
| `LocalDateTime`     | Date + Time                                 |
| `ZonedDateTime`     | Date + Time + TimeZone                      |
| `Instant`           | Machine timestamp (UTC)                     |
| `Period`            | Date-based difference (years, months, days) |
| `Duration`          | Time-based difference (seconds, nanos)      |
| `DateTimeFormatter` | Formatting and parsing                      |


**🔹 Examples: LocalDate, LocalTime, LocalDateTime**
```java
LocalDate date = LocalDate.now();  // 2025-07-13
LocalTime time = LocalTime.now();  // 11:43:15.315
LocalDateTime dt = LocalDateTime.now();

LocalDate birth = LocalDate.of(1990, 1, 1);
```

**🔹 Date Plus / Minus Operations**
```java
LocalDate today = LocalDate.now();
LocalDate nextWeek = today.plusWeeks(1);
LocalDate pastMonth = today.minusMonths(1);
```

**🔹 Comparisons**
```java
if (birth.isBefore(today)) { ... }
if (today.isAfter(birth)) { ... }
```

**🔹 Period & Duration**
```java
// Period: Date-based
Period p = Period.between(LocalDate.of(2000,1,1), LocalDate.now());
System.out.println(p.getYears() + " years");

// Duration: Time-based
Duration d = Duration.between(LocalTime.of(9,0), LocalTime.now());
System.out.println(d.toHours() + " hours");
```

**🔹 Formatting Dates**
```java
DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
String formatted = LocalDate.now().format(fmt);
System.out.println(formatted); // e.g., 13-07-2025
```

**🔹 Parsing Dates**
```java
String input = "25-12-2025";
LocalDate date = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
```

**🔹 ZonedDateTime and Time Zones**
```java
ZonedDateTime zoned = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
System.out.println(zoned);

Set<String> zones = ZoneId.getAvailableZoneIds(); // All timezones

```