# 🚨 Exceptional Handling in Java

## ❗ What is an Error?

- Errors are **serious problems** that usually cannot be fixed by your code.
- They are used by the **Java Virtual Machine (JVM)** to indicate problems typically beyond your application’s control.
- `Error` is a subclass of `Throwable` that represents severe issues a reasonable application **should not try to catch**.

**Example of Error:**

```java
int[] bigArray = new int[Integer.MAX_VALUE]; // might cause an OutOfMemoryError
```

**Types**
- OutOfMemoryError 🧠: JVM runs out of memory
- StackOverflowError ↩️: Caused by deep or infinite recursion
- VirtualMachineError 🖥️: Problems with the JVM itself

## ⚠️ What is an Exception?
- Exceptions are problems that occur during program execution that can be caught and handled.
- An exception is an unwanted or unexpected event at runtime disrupting normal program flow.

> Exception is also a subclass of Throwable that represents conditions your code might catch and handle.

### Types of Exceptions:

**1. Checked / Compile-Time Exceptions ✅**
- Exception occurred or checked at compile time.
- Must be handled (with try-catch or throws).
- Subclasses of Exception (excluding RuntimeException).
- *Examples: IOException, SQLException, FileNotFoundException*

**2. Unchecked / Runtime Exceptions 🚨**
- Checked at runtime, not compile-time
- Subclasses of RuntimeException, often due to programming errors
- *Examples: NullPointerException, ArrayIndexOutOfBoundsException, ArithmeticException*


## 💡 What is Exception Handling?
- A powerful mechanism to handle runtime errors so the program can continue its normal flow.
- When an exception occurs, an exception object is created that contains:
    - name of the exception
    - description of the exception
    - program state when it occurred

#### ✅ Benefits of Exception Handling:
- Robustness: prevents app crashes
- Maintainability: separates error handling from regular code
- Resource Management: helps manage resources with finally
- Flexibility: supports propagation and custom exceptions
- Debugging: provides helpful debugging info


---
## 🌳 Java Exception Hierarchy
```plaintext
Throwable
│
├── Error
│   ├── OutOfMemoryError
│   ├── StackOverflowError
│   └── VirtualMachineError
│
└── Exception
    ├── IOException
    │   ├── FileNotFoundException
    │   └── EOFException
    ├── SQLException
    ├── ClassNotFoundException
    ├── ParseException
    └── RuntimeException
        ├── NullPointerException
        ├── ArrayIndexOutOfBoundsException
        ├── ArithmeticException
        ├── IllegalArgumentException
        └── IllegalStateException
```

- Throwable: Base class for all exceptions and errors
- Error: Serious issues, not meant to be handled
- Exception: Conditions that applications may catch and handle  
    - Checked Exceptions: must be caught or declared
    - Unchecked Exceptions: subclasses of RuntimeException representing bugs


## Java Exception Handling Keywords
Java provides five keywords to handle exceptions:

`try 🔍`
Defines a block to place code that might throw an exception. Must be followed by catch or finally.

`catch`
Used to handle exceptions from the associated try block.

`finally`
Code here is always executed, whether an exception occurred or not.

`throw`
Used to explicitly throw an exception.

`throws`
Used to declare exceptions a method might throw (does not throw them itself).

### Examples
**Unhandled Exception:**
```java
public class ExceptionExample {
    public static void main(String[] args) {
        int data = 100;
        int result = data/0; // will throw ArithmeticException
        System.out.println(result);
        System.out.println(data + data * data);
        System.out.println("Hello Welcome to Exceptional Handling..");  
    }
}
```

**Handled Exception:**
```java
public class ExceptionExample {
    public static void main(String[] args) {
        int data = 100;
        try {  
            int result = data/0;  
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println(e);
        }  
        System.out.println("rest of the code...");
        System.out.println(data + data * data);
        System.out.println("Hello Exceptional Handling.."); 
    }
}
```

---
#### 🚨 Common Scenarios of Java Exceptions

---

1. **ArithmeticException** ➗  
   Occurs when dividing by zero.  
   ```java
   int a = 50 / 0; // ArithmeticException
   ```

2. **NullPointerException 🚫**
   Triggered when operating on a null reference.

   ```java
    String name = null;  
    System.out.println(name.length()); // NullPointerException
   ```

3. **NumberFormatException 🔢**
    Happens if number parsing fails due to an invalid format.
    ```java
    String s = "Mr.Bean";  
    int i = Integer.parseInt(s); // NumberFormatException
    ```

4. **ArrayIndexOutOfBoundsException 📏**
    Occurs when accessing elements outside array bounds.
    ```java
        int a[] = new int[5];  
        a[10] = 50; // ArrayIndexOutOfBoundsException
    ```

---
## 🧩 try & catch Blocks
`try 🔍`: encloses code that might throw an exception
- cannot be used alone
- must be followed by catch or finally

`catch 🪤`: handles the exception
- declares the exception type as parameter
- ideally, catch the specific exception rather than its parent class

```java
try {
    // code that may throw an exception
} catch (ExceptionClass reference) {
    // handle the exception
}


// or

try {
    // code that may throw an exception
} finally {
    // always executed whether exception occurs or not
}

```

**➡️ JVM behavior:**
- If no exception handler is provided, JVM uses its default handler to:
    - print exception description
    - print stack trace
    - terminate the program
- If the exception is handled by the developer, the program continues its normal flow.

```java
import java.io.*;

public class ExceptionExample {
    public static void main(String[] args) {
        File file = null;
        try {
            file = new File("Example.txt");
            if (file.createNewFile()) 
                System.out.println("New file created");
            else    
                System.out.println("File already exists");
        } catch (IOException ex) {
            System.out.println("IOException handled..");
        }
    }
}
```

---

### 🪟 Multi-Catch Block
- A try block can have multiple catch blocks
- Each catch block handles a different exception
- Only one exception can occur at a time, so only one catch block is executed
- Order matters: catch more specific exceptions before more general ones

```java
public class ExceptionExample {
    public static void main(String[] args) {
        try {
            int a[] = new int[Integer.MAX_VALUE];
            a[5] = 30 / 2;
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception occurs");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBounds Exception occurs");
        } catch (Exception e) {
            System.out.println("Parent Exception occurs");
        } catch (Error error) {
            System.out.println("Handled Error");
        }
        System.out.println("Rest of the code");
    }
}
```

###  Nested try-catch Blocks
- Java allows try blocks inside another try block (nested)
- Useful for:
    - complex error handling
    - isolating exceptions
    - resource management

**How it works?**
- Outer try-catch handles exceptions in its scope
- Inner try-catch handles exceptions in its own scope
- Exceptions from inner try are not propagated to the outer catch unless explicitly rethrown

```java
public class NestedTryCatchExample {
    public static void main(String[] args) {
        try {
            System.out.println("Outer try block");

            try {
                System.out.println("Inner try block");
                int result = 10 / 0;
            } catch (ArithmeticException e) {
                System.out.println("Caught ArithmeticException in inner catch block: " + e.getMessage());
            }

            int[] numbers = new int[5];
            numbers[10] = 25;

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException in outer catch block: " + e.getMessage());
        }

        System.out.println("After the nested try-catch blocks");
    }
}
```
---
### finally Block
- The finally block is always executed, regardless of whether an exception occurs
- Used to write important cleanup code such as:
    - closing database connections
    - releasing file or stream handles
    - logging
    - resetting variables

**When to Use finally?**
- ✅ Resource Cleanup
- ✅ Restoring State
- ✅ Logging

> Note: If an exception is not handled, JVM still executes the finally block before terminating the program.

```java 
try {
    // risky code
} catch (ExceptionType e) {
    // handle the exception
} finally {
    // always executed
}
```

```java
import java.io.FileWriter;
import java.io.IOException;

public class FinallyExample {
    public static void main(String[] args) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("example.txt");
            fileWriter.write("Hello, World!");
        } catch (IOException e) {
            System.out.println("Caught an IOException: " + e.getMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Caught an IOException while closing file writer: " + e.getMessage());
                }
            }
        }
    }
}
```

**Returning from Finally**
- If a return happens in the finally block, it overrides any return from try or catch.
```java
public static int testFinally() {
    try {
        return 1;
    } finally {
        return 2; // will override the try return
    }
}
```


**Exception in Finally**
- If an exception occurs in finally, it overrides any exception from the try block.
- Example 1:
```java
public static int testFinally() {
    try {
        return 1 / 0;
    } catch (Exception ex) {
        return 3;
    }
    finally {
        int arr[] = new int[5];
        arr[0] = 10;
    }
}
```

```java
public static int testFinally() {
    try {
        return 1 / 0;
    } catch (Exception ex) {
        return 3;
    }
    finally {
        int arr[] = new int[5];
        arr[0] = 10;
        return arr[0] + arr[5]; // throws exception, overrides previous
    }
}
```

---
## 🚀 CUSTOM EXCEPTIONS
- In Java, custom exceptions let you create your own exception types suited to your application’s specific needs.
- Defining your own exceptions helps deliver more meaningful error messages and address scenarios that built-in exceptions might not cover.

**👉 How to define custom exceptions?**
- Checked exception: extend the Exception class — forces callers to handle it.
- Unchecked exception: extend the RuntimeException class — does not force callers to handle it.

**✨ Example (Custom Checked Exception):**
```java
public class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}
```

**✨ Example (Custom Unchecked Exception):**
```java
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
```


---
## 🎯 THROW EXCEPTION
- The throw keyword is used to explicitly throw an exception.
- You can throw either checked or unchecked exceptions.
- It’s often used to throw custom exceptions.
- Syntax:
```java
throw new ExceptionType("error message");
```

```java
throw new IOException("Device error occurred");
```

**⚡ Example 1: Throwing Unchecked Exception**
```java
public class Example {
  public static void validate(int age) {
    if (age < 18) {
      throw new ArithmeticException("Person is not eligible to vote");
    } else {
      System.out.println("Person is eligible to vote!");
    }
  }
  
  public static void main(String[] args) {
    validate(13);
    System.out.println("rest of the code...");
  }
}
```

**⚡ Example 2: Throwing Checked Exception**
```java
import java.io.*;
public class Example {
  public static void fileRead() throws IOException {
    File file = new File("sample.txt");
    if (!file.canRead()) {
      throw new IOException("File is not readable");
    }
  }
  
  public static void main(String[] args) {
    try {
      fileRead();
    } catch (IOException e) {
      System.out.println("Handled IOException");
    }
  }
}
```

**⚡ Example 3: Throwing User-defined Exception**
```java
public class Validator {
    public void validateAge(int age) throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Age cannot be negative: " + age);
        } else {
            System.out.println("Valid Age");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Validator validator = new Validator();
        try {
            validator.validateAge(-1);
        } catch (InvalidAgeException e) {
            System.out.println("Caught InvalidAgeException: " + e.getMessage());
        }
    }
}
```

---
## 🔗 EXCEPTION PROPAGATION
- Exception propagation means passing exceptions up the call stack until they are handled.
- If a method throws an exception and does not catch it, the exception is automatically passed to the calling method.

**👉 How it works?**
- Exception is first thrown from the top of the stack.
- If it is not handled there, it moves down to the next method in the call chain,
repeating until caught or until reaching the bottom of the stack.

**✨ Example:**
```java
class TestException {
  void m() {
    int data = 50 / 0;
  }
  
  void n() {
    m();
  }
  
  void p() {
    try {
      n();
    } catch (Exception e) {
      System.out.println("exception handled");
    }
  }
  
  public static void main(String[] args) {
    TestException obj = new TestException();
    obj.p();
    System.out.println("normal flow...");
  }
}
```

**⚠️ NOTE:**
- By default, unchecked exceptions propagate automatically.
- Checked exceptions do not propagate unless declared.

---
## 📣 THROWS KEYWORD
- The throws keyword is used to declare that a method might throw exceptions.
- It informs the caller about possible exceptions, mostly for checked exceptions.
- If an unchecked exception (e.g., NullPointerException) occurs, that’s a coding fault, not a requirement for throws.
-  Syntax:
```java
type method_name() throws ExceptionType {
    // method code
}
```
- 👉 Rule: If you call a method that declares an exception, you must either catch or declare it.

```java
class TestException {
  void m() throws IOException {
    throw new IOException("device error");
  }
  void n() throws IOException {
    m();
  }
  void p() {
    try {
      n();
    } catch (Exception e) {
      System.out.println("exception handled");
    }
  }
  public static void main(String[] args) {
    TestException obj = new TestException();
    obj.p();
    System.out.println("normal flow...");
  }
}
```

### 🎯 THROW vs THROWS
| **throw**                                      | **throws**                                           |
| ---------------------------------------------- | ---------------------------------------------------- |
| Used to explicitly throw an exception          | Used to declare that a method might throw exceptions |
| Inside method, constructor, or block           | In the method signature                              |
| Forces the exception to occur                  | Warns the caller about possible exceptions           |
| Must handle/propagate with try-catch or throws | Caller must handle/declare                           |


---
## 🏁 FINAL, FINALLY, FINALIZE

**1️⃣ final keyword**
- Final variables cannot change once assigned.
```java
final int MAX = 100;
```

- Final methods cannot be overridden by subclasses.
```java
public final void show() { }
```

- Final classes cannot be extended.
```java
public final class Helper { }
```

**2️⃣ finally block 🔒**
- Always executes after a try block, whether or not an exception occurs.
- Used for closing resources like files or database connections.

**3️⃣ finalize method**
- Called by the garbage collector before an object is destroyed.
- Can release system resources or cleanup.
- Rarely used nowadays due to better patterns (try-with-resources, explicit closing).

**✨ Example of finalize():**
```java
public class Test {
    public static void main(String[] args) {
        Test obj = new Test();
        System.out.println("Hashcode: " + obj.hashCode());
        obj = null;
        System.gc();
        System.out.println("End of garbage collection");
    }
    protected void finalize() {
        System.out.println("finalize() method called");
    }
}
```

---
## ⚙️ EXCEPTION HANDLING WITH METHOD OVERRIDING

**✅ 1. If the superclass method does not declare an exception**
- The subclass cannot declare new checked exceptions in its overridden method.
- But the subclass can declare unchecked (runtime) exceptions.

```java
class Parent {
    void msg() {
        System.out.println("Parent msg method");
    }
}

class Child extends Parent {
    void msg() throws ArithmeticException {
        System.out.println("Child msg method");
    }
}
```

**✅ 2. If the superclass method does declare an exception**
- The subclass’s overridden method can:
    - declare the same exception
    - declare a subclass of that exception
    - or declare no exception at all
- But it cannot declare a broader (parent) exception than the one declared in the superclass.


**✨ Example 1: Same exception type**
```java
class Parent {
    void msg() throws ArithmeticException {
        System.out.println("Parent msg method");
    }
}

class Child extends Parent {
    void msg() throws ArithmeticException {
        System.out.println("Child msg method");
    }
}
```

**✨ Example 2: Parent & child exceptions**
```java
class Parent {
    void msg() throws IOException {
        System.out.println("Parent msg method with IOException");
    }
}

class Child extends Parent {
    void msg() throws FileNotFoundException {
        System.out.println("Child msg method with FileNotFoundException");
    }
}
```

**✨ Example 3: Superclass declares, subclass removes**
```java
class Parent {
    void msg() throws Exception {
        System.out.println("Parent msg method with Exception");
    }
}

class Child extends Parent {
    void msg() {
        System.out.println("Child msg method with no exception");
    }
}
```

**✨ Example 4: Subclass declares broader exception (⚠️ not allowed)**
```java
class Parent {
    void msg() throws ArithmeticException {
        System.out.println("Parent msg method with ArithmeticException");
    }
}

class Child extends Parent {
    void msg() throws Exception {
        System.out.println("Child msg method with Exception");
    }
}
```

**✨ Example 5: Superclass with checked, subclass with unchecked**
```java
class Parent {
    void msg() throws IOException {
        System.out.println("Parent msg method with IOException");
    }
}

class Child extends Parent {
    void msg() throws ArithmeticException {
        System.out.println("Child msg method with ArithmeticException");
    }
}
```

**💡 NOTE:**
- If the parent method declares a checked exception, the subclass method can choose to declare an unchecked exception instead — that is allowed.
- But if the parent declares an unchecked exception, the subclass cannot introduce a new checked exception in its overridden method.


