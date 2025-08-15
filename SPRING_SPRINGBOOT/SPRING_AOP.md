## Aspect-Oriented Programming (AOP)
> Aspect-Oriented Programming (AOP) is a programming paradigm that aims to separate cross-cutting concerns from the main business logic.

- Cross-cutting concerns = functionalities that span multiple modules and are not part of the core business logic but are still needed.
- Examples: Logging, Security, Transaction management, Caching, Performance monitoring.

**Without AOP:**
- These concerns get duplicated in many places → code clutter → harder to maintain.

**With AOP:**
- They are implemented once in a centralized module (Aspect) and applied where needed automatically.


### Why AOP in Spring?
- Spring AOP helps:
    - Reduce boilerplate code
    - Improve modularity
    - Make changes in one place for cross-cutting logic
    - Keep business logic clean and focused

**Real-time Analogy**
Think of airport security:
    - Every passenger must go through security before boarding — regardless of their destination.
    - The security check process is not part of the flight’s core function (flying), but it’s applied uniformly.
    - AOP works like this — it applies certain logic to multiple methods automatically.


### Core Concepts and Terminology
| Term             | Definition                                                      | Example                                              |
| ---------------- | --------------------------------------------------------------- | ---------------------------------------------------- |
| **Aspect**       | A module containing cross-cutting logic                         | Logging module                                       |
| **Advice**       | Action taken by an aspect at a particular join point            | "Log before method executes"                         |
| **Join Point**   | A point during program execution where an aspect can be applied | Method call, exception thrown                        |
| **Pointcut**     | An expression that matches join points                          | "All methods in service package"                     |
| **Advice Types** | Different ways to run advice                                    | Before, After, Around, AfterReturning, AfterThrowing |
| **Weaving**      | Linking aspects with target objects                             | Happens at runtime in Spring AOP                     |


### Types of Advice in Spring AOP

`a) @Before`
- Runs before the target method
```java
@Before("execution(* com.example.service.*.*(..))")
public void logBefore() {
    System.out.println("Before method execution");
}
```

`b) @After`
- Runs after method execution (whether successful or not).
```java
@After("execution(* com.example.service.*.*(..))")
public void logAfter() {
    System.out.println("After method execution");
}
```

`c) @AfterReturning`
```java
@AfterReturning(pointcut = "execution(* com.example.service.*.*(..))", returning = "result")
public void logReturn(Object result) {
    System.out.println("Method returned: " + result);
}
```

`e) @Around`
- Runs before and after the method; allows you to control method execution.
```java
@Around("execution(* com.example.service.*.*(..))")
public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("Before method: " + joinPoint.getSignature());
    Object result = joinPoint.proceed();
    System.out.println("After method: " + joinPoint.getSignature());
    return result;
}
```


**Example: Logging Aspect**
```java
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.service.OrderService.*(..))")
    public void logBefore() {
        System.out.println("Logging before OrderService method");
    }

    @AfterReturning(
        pointcut = "execution(* com.example.service.OrderService.*(..))",
        returning = "result"
    )
    public void logAfterReturning(Object result) {
        System.out.println("Method returned value: " + result);
    }
}
```

**Advantages of AOP**

Cleaner code: Business logic and cross-cutting logic are separated.

Reusability: One aspect can be applied across multiple modules.

Centralized changes: Modify logging/security in one place.

Consistent behavior: Ensures certain rules are always applied.


**When to Use AOP**

Logging across all service methods

Security checks for certain endpoints

Transaction management in data access

Performance monitoring (execution time)

Exception handling


**Spring AOP vs Full AOP Frameworks**

Spring AOP → Proxy-based, runtime weaving, method-level only.

AspectJ → Compile-time or load-time weaving, works on methods, fields, constructors.

**Key Takeaways**

AOP helps separate cross-cutting concerns from business logic.

Main building blocks: Aspect, Advice, Pointcut, Join Point.

Spring uses proxies for AOP — by default only method-level interception.

Makes code more maintainable, readable, and reusable.