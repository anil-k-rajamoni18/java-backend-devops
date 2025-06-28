## Packages and Libraries in Java

####  1. What is a Package in Java?
- A package is a namespace that organizes a set of related classes and interfaces.
- Types
    - Built-in Packages – Provided by Java (e.g., java.util, java.io, java.lang)
    - User-defined Packages – Created by the developer to organize code

```java
package mypackage;

public class MyClass {
    public void display() {
        System.out.println("Hello from MyClass");
    }
}
```

**Importing Packages**
- Importing a Specific Class:
```java
import java.util.Scanner;
```

-  Importing the Entire Package:
```java
import java.util.*;
```
---

### Creating a User-defined Package
1. Create a .java file with a package declaration at the top.
2. Compile with:
```bash
javac -d . MyClass.java
```
3. Use the class in another file with import
```java
import mypackage.MyClass;
```

### Access Modifiers in Packages
| Modifier    | Same Class | Same Package | Subclass (Other Package) | Other Packages |
| ----------- | ---------- | ------------ | ------------------------ | -------------- |
| `private`   | ✅          | ❌            | ❌                        | ❌              |
| *(default)* | ✅          | ✅            | ❌                        | ❌              |
| `protected` | ✅          | ✅            | ✅                        | ❌              |
| `public`    | ✅          | ✅            | ✅                        | ✅              |

---
### 🔹 Java Libraries
> A library in Java is a collection of pre-written classes and methods grouped in packages, meant to provide reusable functionality.

**Common Java Standard Libraries:**
| Package     | Purpose                                   |
| ----------- | ----------------------------------------- |
| `java.lang` | Core classes (String, Math, Object, etc.) |
| `java.util` | Collections, Date, Scanner, etc.          |
| `java.io`   | Input/output (File, Reader, Writer)       |
| `java.net`  | Networking (Socket, URL)                  |
| `java.sql`  | Database (JDBC API)                       |
| `java.time` | Date and Time API                         |


###  Using External Libraries (JARs)
- A JAR (Java ARchive) file is a package file format used to aggregate Java classes and associated metadata/resources.

**Adding a JAR to a Project:**
1. Download the .jar file.
2. Compile with:
```bash
javac -cp path/library.jar MyProgram.java
```
3. Run with:
```bash
java -cp path/library.jar MyProgram
```

- In IDEs (e.g., IntelliJ, Eclipse): Add JARs via "Project Structure" or "Build Path".

---
## ✅ What is a Method?

- A **method** is a way to perform some task.
- It is a **block of code** or a **collection of statements** grouped together to perform a specific operation.
- Methods **improve code readability** and **make modification easier**.
- A method is executed **only when it is called or invoked**.
- Every method goes through **two phases**: **Declaration** and **Calling**.

---

## ✏️ Method Declaration

- The method declaration provides details about the method's:
  - Attributes
  - Visibility
  - Return type
  - Name
  - Parameters (arguments)

### Example:
```java
public int sum(int a, int b) {
    // body
    return a + b;
}
```

**Method Signature**
- Every method has a method signature.
- A method signature includes the method name and the parameter list.


**🔐 Access Specifiers**
- Access specifiers (or modifiers) define the visibility of the method. Java provides:
    - public: Accessible by all classes.
    - private: Accessible only within the same class.
    - protected: Accessible within the same package or subclasses in different packages.
    - (default): No specifier means default access, accessible within the same package.

**➕ Non-access Modifiers**
- Java also provides non-access modifiers such as:
    - static, final, abstract, synchronized, transient, volatile, native

### 📚 Types of Methods

**1. Predefined Methods**
- These are built-in methods from Java's standard libraries.
- Also known as standard library methods or built-in methods.
- Examples: length(), equals(), compareTo(), Math.sqrt(), Math.max(), Math.min()

```java
System.out.print("The maximum number is: " + Math.max(9, 7));
```

**2. User-defined Methods**
- Created by the programmer to perform specific tasks.
- Can be of types:
    - Static Method
    - Instance Method
    - Abstract Method

**Static Methods**
- Declared using the static keyword.
- Bound to the class, not an object.
- Called directly using the class name.


**Instance Methods**
- No static keyword used.
- Bound to an object (instance).
- Must be called using the object name.


---

# 💡 Object-Oriented Programming (OOP) in Java

---

## 🧠 What is OOP?

- OOP is a **programming paradigm** that uses **objects** to design software.
- It focuses on grouping **data** and **methods** into **classes** to represent real-world entities.
- Helps manage code by organizing it into meaningful units.
- Real-world modeling makes applications intuitive and maintainable.

### 🧬 History & Origin

- **Simula**: First object-oriented language.
- **Smalltalk**: First *truly* object-oriented programming language.
- Common OOP Languages: **Java, C#, Python, PHP, C++**

---

## ✅ Advantages of OOP

- 🔐 **Modularity** – Encapsulation, code reusability  
- 🔧 **Maintainability** – Clean organization of code  
- 🔁 **Extensibility** – Through inheritance and polymorphism  
- 🎭 **Abstraction** – Hides complexity, exposes essentials  

---

## 💻 Programming Paradigms

| Paradigm | Description | Examples |
|----------|-------------|----------|
| **Imperative** | Describes *how* to perform tasks via commands that change program state | C, Java, Python |
| **Procedural** | Organizes code into reusable functions or procedures | C, Pascal, Fortran |
| **Object-Oriented** | Code organized around *objects* and *classes* | Java, C++, Python, C# |
| **Functional** | Emphasizes immutability and first-class functions | Haskell, Scala, Lisp |

---

## 🔑 OOP Principles (Pillars)

- **Class**: Blueprint or template for objects
- **Object**: Instance of a class (represents a real-world entity)
- **Encapsulation**: Bundling data + methods and restricting access
- **Inheritance**: Deriving new classes from existing ones
- **Polymorphism**: One interface, multiple implementations
- **Abstraction**: Hiding internal details and showing only essentials

---

## 🏗 CLASS 

- A **user-defined** data type that groups data and functions.
- Logical representation of data.
- Declared using the `class` keyword.
- Should be written in **PascalCase** (e.g., `Student`, `Car`).

### 🔧 Syntax

```java
class ClassName {
    // Fields
    // Methods
    // Constructors
    // Blocks
    // Nested Classes/Interfaces
}
```



## 🧱 OBJECT 
- Runtime entity; created using a class.
- Represents a person, place, thing, etc.
- Has 3 key characteristics:
    - 🟦 State (via variables)
    - ⚙️ Behavior (via methods)
    - 🆔 Identity (via memory address/hashcode)

- 📌 Example: A Pen object
    - State → Brand: Reynolds, Color: White
    - Behavior → Writing

**🚀 Instantiation (Creating Objects)**
- Done using the new keyword
- Allocated in Heap memory

```java
ClassName objectName = new ClassName();

Student student = new Student(); // example
```

- Object references will be created in Stack Memory 
- Objects memory(data+method) will be created in Heap Area.

```java
public class Car {
    private String color;
    private String model;

    public Car() {}

    public Car(String color, String model) {
        this.color = color;
        this.model = model;
    }

    public void displayInfo() {
        System.out.println("Model: " + model + ", Color: " + color);
    }
}
```

**Terminologies**
| Term    | Meaning                |
| ------- | ---------------------- |
| Fields  | Variables / Attributes |
| Methods | Member Functions       |
| Object  | Instance of a class    |

**Ways to Initialize Object Attributes**
1. ✅ Constructor
2. ✅ Methods (Setters/Getters)
3. ✅ Reference Variable (if not private)

---

**🕵️‍♂️ Anonymous Object in Java**

- An **anonymous object** is an object that **does not have a reference name**.
- The term *anonymous* simply means **nameless**.
- These objects are **used only once**, typically **at the time of creation**.
- Useful when you don’t need to reuse the object.

-  📌 Example:
```java
new Car().model;
```

- In this example, a Car object is created and immediately used without storing it in a variable.

---
## 🧱 Types of Classes

### 1️⃣ Concrete / Standard Class

- A **normal class** that can be instantiated directly.
- Provides full implementation of its methods.

### 🔧 Example

```java
public class ConcreteClass {
    public void display() {
        System.out.println("This is a concrete class.");
    }
}
```

### 2️⃣ Abstract Class
- Cannot be instantiated on its own.
- Intended to be extended by other classes.
- Can contain:
    - Abstract methods (without body)
    - Concrete methods (with implementation)
- Acts as a base class with common logic for subclasses.

```java
public abstract class AbstractClass {
    public abstract void abstractMethod(); // Must be implemented by subclass

    public void concreteMethod() {
        System.out.println("Concrete method in abstract class.");
    }
}
```

### 3️⃣ Interface
- A reference type that defines a contract for classes to implement.
- Can contain:
    - Constants (public static final)
    - Abstract methods (public abstract)
    - Default methods
    - Static methods
    - Nested types
- Cannot contain instance fields or constructors.

```java
public interface Drawable {
    void draw(); // Abstract method

    default void defaultMethod() {
        System.out.println("Default method in interface.");
    }
}
```

### 4️⃣ Enum Class
- A special class used to define a fixed set of constants.
- Can also include:
    - Instance variables
    - Constructors
    - Methods

```java
public enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}
```

### 5️⃣ Inner Classes
> A class defined within another class.

**🔸 Member Inner Class**
- Non-static inner class.
- Has access to outer class’s members.

```java
OuterClass.InnerClass obj = new OuterClass().new InnerClass();
```
**🔸 Static Nested Class**
- Static inner class.
- Cannot access outer class’s instance members.
```java
OuterClass.StaticNestedClass obj = new OuterClass.StaticNestedClass();
```

**🔸 Method Local Inner Class**
- Defined within a method.
- Accessible only within that method.

**🔸 Anonymous Inner Class**
- Class without a name.
- Often used for implementing interfaces or extending classes concisely.

```java
public class OuterClass {
    private String outerField = "Outer";

    // Member Inner Class
    public class InnerClass {
        public void display() {
            System.out.println("Inner class accessing: " + outerField);
        }
    }

    // Static Nested Class
    public static class StaticNestedClass {
        public void display() {
            System.out.println("Static nested class.");
        }
    }

    public void method() {
        // Method Local Inner Class
        class LocalInnerClass {
            public void display() {
                System.out.println("Local inner class.");
            }
        }
        LocalInnerClass local = new LocalInnerClass();
        local.display();
    }
}
```

**Anonymous Inner Class Usage**

```java
public class Test {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous inner class.");
            }
        };

        // OR using Lambda (for functional interface)
        Runnable runnable2 = () -> System.out.println("Anonymous inner class.");
        runnable.run();
    }
}
```

### 6️⃣ Singleton Class
- Ensures only one instance of the class exists.
- Used in scenarios where a global instance is needed.

```java
    public class Singleton {
        private static Singleton instance;

        private Singleton() {
            // private constructor to prevent instantiation
        }

        public static Singleton getInstance() {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }
```

# 📌 Important Points

---

## 🧠 Memory Allocation

- **Object references** are stored in **Stack Memory**.
- **Objects themselves** (including data and methods) are created in the **Heap Memory**.

---

## 🔧 Variables & Methods

### ➤ Access Modifiers:
- `public`: Accessible from anywhere.
- `protected`: Accessible within the same package and by subclasses.
- `default` (Package-Private): Accessible only within the same package.
- `private`: Accessible only within the same class.

### ➤ Non-Access Modifiers:
- `static`: Belongs to the class rather than instances.
- `final`: Constant or non-overridable/inheritable element.
- `abstract`: Declares methods/classes that require implementation.
- `synchronized`: Manages thread access to ensure thread safety.
- `volatile`: Ensures variable updates are visible across threads.
- `transient`: Prevents field from being serialized.
- `native`: Indicates method is implemented in platform-dependent code (like C/C++).
- `strictfp`: Enforces strict IEEE 754 floating-point standards.

---

## 🏛 Outer Class

### ➤ Access Modifiers:
- `public`: Accessible from any class or package.
- `default` (Package-Private): Accessible only within the same package.

### ➤ Non-Access Modifiers:
- `abstract`: Cannot be instantiated; may contain abstract methods.
- `final`: Cannot be subclassed.
- `strictfp`: Enforces strict floating-point operations.

---

## 🧩 Inner Classes

### ➤ Access Modifiers:
- `public`: Accessible from anywhere.
- `protected`: Accessible within the same package and subclasses.
- `default`: Accessible only within the same package.
- `private`: Accessible only within the enclosing (outer) class.

### ➤ Non-Access Modifiers:
- `static`: Independent of outer class instances.
- `final`: Cannot be extended (subclassed).
- `abstract`: Cannot be instantiated; may include abstract methods.
- `strictfp`: Maintains strict floating-point calculation behavior.

---

## 🔄 Interfaces

- **Constants**: All fields are implicitly `public static final`.
- **Methods**:
  - Traditional: `public abstract`
  - **Java 8+:**
    - `default` methods (with body)
    - `static` methods
    - `private` methods (Java 9+)

### ➤ Modifiers:
- Access: `public`, `default`
- Non-access: `abstract`, `default`, `static`, `strictfp`, `private`

---

## 🎯 Enums

### ➤ Modifiers:
- Access: `public`, `default`
- Non-access: `abstract`, `final`, `static`, `strictfp`

---

# 🚧 Constructors in Java

## 📌 What is a Constructor?

- A **constructor** is a special method in Java used to **initialize objects**.
- It shares the **same name as the class**.
- Called **automatically** when an object is created using the `new` keyword.
- Can be used to assign initial values to object fields.
- If no constructor is explicitly defined, the **JVM automatically provides a default no-argument constructor**.

> ⚠️ Note: It's not mandatory to define a constructor. If omitted, Java provides a default one (only if no other constructors are defined).


---

## 🆚 Constructor vs Method

| Feature                     | Constructor                                   | Method                                  |
|----------------------------|-----------------------------------------------|------------------------------------------|
| Name                       | Same as class name                            | Can have any name                        |
| Return Type                | No return type (not even void)                | Must have a return type or void          |
| Invocation                 | Automatically called during object creation   | Called explicitly                        |
| Frequency of Call          | Called once per object creation               | Can be called multiple times             |

---


## 🧪 Validity Check: Constructor or Method?

```java
class Hello {
    // Not a constructor, it's a method
    public void Hello() {
        System.out.println("This is a method.");
    }

    // Valid constructor
    public Hello() {
        System.out.println("Constructor called.");
    }

    // ❌ Invalid: constructors can't return values (not even 'this')
    public Hello() {
        System.out.println("This will cause a compile-time error.");
        return this;
    }
}
```

**Constructor Example**
```java
class Student {
    String name = "Ram";
    int rollNumber = 172972625;

    Student() {
        super(); // Calls the constructor of Object class
    }

    public static void main(String[] args) {
        Student ram = new Student(); // Constructor is invoked here
    }
}
```

### 📏 Rules for Constructors
- Name must match the class name exactly.
- Cannot be abstract, final, static, synchronized, volatile, native, or strictfp.
- Can use any access modifier (public, private, protected, or package-private).
- First statement inside a constructor is either this() or super().

### 🔣 Types of Constructors
**1️⃣ Default Constructor**
- Automatically created by the compiler if no constructor is defined.
- Has no parameters and no implementation body.
- Invisible in code.

**2️⃣ No-Argument Constructor**
- Explicitly defined constructor without parameters.
- If any constructor is defined (even with parameters), the compiler does not create a default constructor.
```java
class Student {
    int rollno;
    String name;

    public Student() {
        System.out.println("No-Args Constructor called");
    }
}
```

**3️⃣ Parameterized Constructor**
- Accepts arguments to initialize fields with specific values.
```java
class Student {
    int rollno;
    String name;

    public Student(int rollno, String name) {
        this.rollno = rollno;
        this.name = name;
    }
}
```

**Constructor Overloading**
> A class can have multiple constructors with different signatures.

```java
class Employee {
    String name;
    int id;
    double salary;

    // Default constructor
    Employee() {
        System.out.println("Default Constructor: No employee data provided.");
    }

    // Constructor with one parameter
    Employee(String name) {
        this.name = name;
        System.out.println("Employee created with name: " + name);
    }

    // Constructor with two parameters
    Employee(String name, int id) {
        this.name = name;
        this.id = id;
        System.out.println("Employee created with name: " + name + ", ID: " + id);
    }

    // Constructor with all parameters
    Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        System.out.println("Employee created with name: " + name + ", ID: " + id + ", Salary: $" + salary);
    }
}

public class Main {
    public static void main(String[] args) {
        // Different constructor calls (constructor overloading in action)
        Employee emp1 = new Employee();                       // No data
        Employee emp2 = new Employee("Alice");                // Name only
        Employee emp3 = new Employee("Bob", 101);             // Name + ID
        Employee emp4 = new Employee("Charlie", 102, 75000);  // Full data
    }
}
```

---
# 🧬 Inheritance in Java

### 🔍 What is Inheritance?

Inheritance allows one class (**child/subclass**) to acquire the properties and behaviors (fields and methods) of another class (**parent/superclass**). It’s a key feature of **object-oriented programming** that models real-world relationships.

> 🧠 Think of a **Dog** being a type of **Animal**. A `Dog` class can inherit behaviors like `eat()` and `sleep()` from an `Animal` class.

---

## ✅ Why Use Inheritance?

- **Code Reusability**: Reuse existing code in new classes.
- **Runtime Polymorphism**: Enable method overriding.
- **Abstraction**: Hide implementation details from users.

---

## 📘 Key Terminology

| Term        | Description                                                                 |
|-------------|-----------------------------------------------------------------------------|
| **Class**   | A blueprint for objects that share the same properties and behavior.        |
| **Subclass** (Child) | A class that inherits from another class.                         |
| **Superclass** (Parent) | The class being inherited from.                               |


---
## 🧱 Syntax

```java
class SubClassName extends SuperClassName {
    // fields and methods
}
```

**Example**
```java
class Parent {
    public void greet() {
        System.out.println("Hello from Parent");
    }
}

class Child extends Parent {
    public void play() {
        System.out.println("Child is playing");
    }
}
```

**⚖️ Can You Inherit From Multiple Classes?**
- ❌ Java does not support multiple inheritance with classes (extends one class only).
- ✅ But Java does support multiple interface implementation.
- ✅ Interfaces can also extend multiple interfaces.

```java
class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }

    public void sleep() {
        System.out.println(name + " is sleeping.");
    }

    public void makeSound() {
        System.out.println(name + " makes a sound.");
    }
}


// Dog class (Child of Animal)
class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " barks.");
    }

    public void fetch() {
        System.out.println(name + " is fetching a ball.");
    }
}

// Cat class (Child of Animal)
class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " meows.");
    }

    public void scratch() {
        System.out.println(name + " is scratching.");
    }
}

//  Main Class
public class Example {
    public static void main(String[] args) {
        Dog myDog = new Dog("Rover");
        Cat myCat = new Cat("Whiskers");

        myDog.eat();         // Inherited
        myDog.sleep();       // Inherited
        myDog.makeSound();   // Overridden
        myDog.fetch();       // Dog-specific

        myCat.eat();         // Inherited
        myCat.sleep();       // Inherited
        myCat.makeSound();   // Overridden
        myCat.scratch();     // Cat-specific

        // Polymorphism using parent reference
        Animal myAnimal;

        myAnimal = new Dog("Buddy");
        myAnimal.makeSound(); // Dog's version called

        myAnimal = new Cat("Luna");
        myAnimal.makeSound(); // Cat's version called
    }
}
```
---

### **Object Reference Rules**
- ✅ A parent class reference can refer to child class objects:
```java
Animal pet = new Dog("Buddy");
pet.makeSound();  // Dog's method is called
```

- ❌ A child class reference cannot point to a parent class object:
```java
Dog d = new Animal("Some animal"); // ❌ Compile-time error
```

---
## 🧬 Types of Inheritance

### 🔹 1. Single Inheritance
- A class inherits from **only one superclass**.  
- Most basic and common type of inheritance.

> A **Car** is a type of **Vehicle**.

```java
class Vehicle {
    protected String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    public void start() {
        System.out.println(brand + " vehicle is starting.");
    }
}

class Car extends Vehicle {
    public Car(String brand) {
        super(brand);
    }

    public void honk() {
        System.out.println(brand + " car is honking.");
    }
}
```

**🔹 2. Multilevel Inheritance**
- A class inherits from another class, which in turn inherits from another.
- Forms a chain of inheritance.

```java
class Employee {
    protected String name;

    public Employee(String name) {
        this.name = name;
    }

    public void work() {
        System.out.println(name + " is working.");
    }
}

class Manager extends Employee {
    public Manager(String name) {
        super(name);
    }

    public void manage() {
        System.out.println(name + " is managing.");
    }
}

class TeamLead extends Manager {
    public TeamLead(String name) {
        super(name);
    }

    public void leadTeam() {
        System.out.println(name + " is leading the team.");
    }
}
```


**🔹 3. Hierarchical Inheritance**
- Multiple classes inherit from a single common superclass.
- Example
    - Person → Student, Person → Teacher
```java
class Person {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public void introduce() {
        System.out.println("Hi, I am " + name + ".");
    }
}

class Student extends Person {
    public Student(String name) {
        super(name);
    }

    public void study() {
        System.out.println(name + " is studying.");
    }
}

class Teacher extends Person {
    public Teacher(String name) {
        super(name);
    }

    public void teach() {
        System.out.println(name + " is teaching.");
    }
}
```

**🔹 4. Multiple Inheritance (via Interfaces)**
- Java doesn't allow multiple inheritance with classes to avoid ambiguity (Diamond Problem).
- But Java supports multiple inheritance through interfaces.
- Example
    - SmartPhone → Camera + Phone
    - PE Teacher → Teacher + Coach

```java
interface Camera {
    void takePhoto();
    void recordVideo();
}

interface Phone {
    void makeCall(String number);
    void receiveCall(String caller);
}

class SmartPhone implements Camera, Phone {

    @Override
    public void takePhoto() {
        System.out.println("Photo captured with 108MP camera.");
    }

    @Override
    public void recordVideo() {
        System.out.println("Recording video in 4K resolution.");
    }

    @Override
    public void makeCall(String number) {
        System.out.println("Calling " + number + "...");
    }

    @Override
    public void receiveCall(String caller) {
        System.out.println("Incoming call from " + caller + ".");
    }

    public void browseInternet() {
        System.out.println("Browsing internet on 5G network.");
    }
}

public class Main {
    public static void main(String[] args) {
        SmartPhone myPhone = new SmartPhone();
        myPhone.makeCall("9876543210");
        myPhone.receiveCall("Alice");
        myPhone.takePhoto();
        myPhone.recordVideo();
        myPhone.browseInternet();
    }
}

```

**🔹 5. Hybrid Inheritance**
- Hybrid inheritance is a combination of two or more types of inheritance.

```vbnet
       Device (Interface)
         /       \
    Camera     Phone (Interface)
         \       /
        SmartDevice (Implements both)
             |
         SmartPhone (Extends SmartDevice)

```

```java
interface Device {
    void powerOn();
}

interface Camera extends Device {
    void takePhoto();
}

interface Phone extends Device {
    void makeCall(String number);
}

class SmartDevice implements Camera, Phone {

    @Override
    public void powerOn() {
        System.out.println("SmartDevice is powering on...");
    }

    @Override
    public void takePhoto() {
        System.out.println("Taking a photo with SmartDevice.");
    }

    @Override
    public void makeCall(String number) {
        System.out.println("Calling " + number + " using SmartDevice.");
    }
}

class SmartPhone extends SmartDevice {
    public void browseInternet() {
        System.out.println("Browsing internet on SmartPhone.");
    }
}

public class Main {
    public static void main(String[] args) {
        SmartPhone phone = new SmartPhone();

        phone.powerOn();          // From Device
        phone.takePhoto();        // From Camera
        phone.makeCall("12345");  // From Phone
        phone.browseInternet();   // From SmartPhone
    }
}

```

----
####  Summary Table
| Type                   | Real-Life Example                                            | Java Keyword Used      |
| ---------------------- | ------------------------------------------------------------ | ---------------------- |
| Single Inheritance     | `SmartPhone → Device`                                        | `extends`              |
| Multilevel Inheritance | `Laptop → Computer → ElectronicDevice`                       | `extends`              |
| Hierarchical           | `Person → Student`, `Person → Teacher`                       | `extends`              |
| Multiple (Interfaces)  | `FlyingCar → Drivable + Flyable`                             | `implements`           |
| Hybrid                 | `LeadSoftwareEngineer → Engineer + Manager` (via interfaces) | `extends + implements` |


```java
🧍‍♂️ Single:
Fruit → Food
Refrigerator → Appliance

🏢 Multilevel:
EBook → Book → Item
Dog → Mammal → Animal

🌳 Hierarchical:
Reserve Bank → SBI, ICICI, HDFC
MobileBrands → Samsung, Apple, Realme

🌐 Multiple (Interfaces):
ElectricLuxuryCar → ElectricCar + LuxuryCar
SmartCameraPhone → Camera + Phone

```

---

## 🔄 What Gets Inherited from Superclass to Subclass in Java?


### 1️⃣ Fields (Variables)

✅ **Inherited:**  
- All **non-private** fields (`protected` and `public`) are inherited and can be accessed in the subclass.
  
❌ **Not Inherited:**  
- `private` fields are **not** accessible in the subclass directly — they're encapsulated within the superclass.
  
💡 *Tip:* Use **getter/setter methods** to access private fields.

---

### 2️⃣ Instance Methods

✅ **Inherited:**  
- All **non-private** instance methods are inherited.
- `public` and `protected` methods can be overridden by the subclass to provide specific behavior.

❌ **Not Inherited:**  
- `private` methods cannot be accessed or overridden by the subclass.

---

### 3️⃣ Static Methods

✅ **Technically Inherited, but...**
- Static methods **belong to the class**, not instances.
- They can be accessed using the subclass name, but they are **not polymorphic**.

⚠️ **Important:**  
- If a subclass defines a static method with the same name, it **hides** the superclass method, it doesn't override it.

---

### 4️⃣ Instance Initialization Blocks

❌ **Not Inherited:**
- These blocks run when an instance is created.
- Each class has its own — they're **not passed down**.

🔄 **Order of Execution:**  
- When a subclass object is created, the **superclass’s instance block runs first**, followed by the subclass's.

---

### 5️⃣ Static Initialization Blocks

❌ **Not Inherited:**
- Static blocks run **once**, when the class is first loaded.
- Each class has its own static block(s).

🔄 **Execution Order:**  
- **Superclass static block** runs before the subclass’s static block.

---

### 6️⃣ Static Fields

✅ **Inherited:**
- Static fields are shared among all instances of the class.

⚠️ **Note:**
- A subclass can **hide** a static field from the superclass by declaring a field with the same name (not recommended for clarity).

---

### 7️⃣ Constructors

❌ **Not Inherited:**
- Constructors are **never inherited**.

✅ **Accessible via `super()`**
- A subclass can **call a constructor** of its superclass using `super()` as the first statement in its constructor.

---

### ✅ Summary Table

| Feature              | Inherited? | Notes                                                                 |
|----------------------|------------|-----------------------------------------------------------------------|
| Fields               | ✅          | Only `public` and `protected` fields. `private` are not accessible.   |
| Instance Methods     | ✅          | Except `private`. Can be overridden if `public`/`protected`.         |
| Static Methods       | ✅          | Not overridden — hidden if redefined in subclass.                    |
| Instance Blocks      | ❌          | Each class has its own. Superclass block runs first.                 |
| Static Blocks        | ❌          | Not inherited. Run when the class is loaded.                         |
| Static Fields        | ✅          | Shared across instances. Can be hidden, not overridden.              |
| Constructors         | ❌          | Not inherited. Use `super()` to call superclass constructor.         |

---

## 💡 Real-Time Analogy

Think of a **family**:

- You inherit your **family name (public field)**, and maybe **skills (methods)**, but not your parent’s **private secrets (private fields)**.
- **Static things** are like shared family traditions — you don’t own them individually.
- **Constructors** are like your birth story — it’s personal to how you were created, not something you inherit!

---

## 📌 `this` Keyword in Java

The `this` keyword is a **reference to the current object** — the instance of the class in which it is used. It's very useful to eliminate confusion between class attributes and parameters or to chain methods/constructors.

---

### ✅ When to Use `this`

1. **Distinguishing between instance variables and parameters**  
2. **Calling another constructor in the same class**
3. **Passing the current object to another method/class**
4. **Returning the current object (for method chaining)**

⚠️ You **cannot use `this` in a static context**, because `this` always refers to an object instance.

---

### 💡 Real-Time Example

```java
class Person {
    private String name;
    private int age;

    // Constructor chaining using this()
    public Person() {
        this("Unknown", 0); // calls the other constructor
    }

    public Person(String name, int age) {
        this.name = name; // distinguishes instance variable from parameter
        this.age = age;
    }

    public Person setName(String name) {
        this.name = name;
        return this; // enables method chaining
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public void printDetails(Printer printer) {
        printer.printPerson(this); // passing current object
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age;
    }
}

class Printer {
    public void printPerson(Person person) {
        System.out.println("Printing details from Printer:");
        System.out.println(person); // calls toString() of Person
    }
}
```
---
## 📌 super Keyword in Java
- The super keyword refers to the immediate parent class (superclass) of the current object. 

**✅ Uses of super**
- Calling superclass constructors
- Accessing overridden methods from superclass
- Accessing hidden fields in the superclass

```java
class Base {
    public void show() {
        System.out.println("Base class show()");
    }
}

class Derived extends Base {
    @Override
    public void show() {
        super.show(); // calls the parent class version
        System.out.println("Derived class show()");
    }
}

public class Example {
    public static void main(String[] args) {
        Derived d = new Derived();
        d.show();
    }
}
```

**Summary**
| Keyword | Refers To              | Used For                                                             |
| ------- | ---------------------- | -------------------------------------------------------------------- |
| `this`  | Current class instance | - Distinguish variables<br>- Constructor chaining<br>- Return object |
| `super` | Superclass instance    | - Access parent methods/fields<br>- Call superclass constructor      |


**Real-World Analogy**
- this: Imagine you're writing about yourself in a form — you use "I" to refer to yourself (the current object).
- super: Think of it as saying “my parent” — you’re referring to someone one level above you (your base class).

---
# 📘 Interface in Java
- An interface in Java is a reference type, similar to a class.
- It acts like a blueprint for classes — it defines what a class should do, not how.
- Interfaces are used to achieve 100% abstraction (before Java 8).
- They represent an "IS-A" relationship.
- You cannot create objects (instances) for an interface.
- Starting from:
    - Java 8: Interfaces can have default and static methods.
    - Java 9: Interfaces can also have private methods.

**Why Use Interfaces?**
- To achieve abstraction.
- To support multiple inheritance in Java (which classes don't directly support).
- To allow loose coupling in application design.

**Syntax**
```java
interface InterfaceName {
    // Constant fields
    // Abstract methods (method signatures only)
}
```

- Use the interface keyword.
- All fields are by default public, static, and final.
- All methods (unless declared otherwise) are public and abstract.
- A class that implements an interface must provide implementations for all its methods.

**⚠️ Note**
- The compiler automatically adds:
    - public and abstract to methods.
    - public, static, and final to fields.


**💡 Example 1: Simple Interface**
```java
interface Bank {
    float rateOfInterest();
}

class SBI implements Bank {
    public float rateOfInterest() {
        return 9.15f;
    }
}

class PNB implements Bank {
    public float rateOfInterest() {
        return 9.7f;
    }
}

class TestInterface2 {
    public static void main(String[] args) {
        Bank b = new SBI();
        System.out.println("ROI: " + b.rateOfInterest());
    }
}
```

**💡 Example 2: Payment Interface**

```java
interface PaymentMethod {
    void processPayment(double amount);
}

class CreditCard implements PaymentMethod {
    private String cardNumber;

    public CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of Rs " + amount +
                " with card number " + cardNumber);
    }
}

class PayPal implements PaymentMethod {
    private String email;

    public PayPal(String email) {
        this.email = email;
    }

    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of Rs " + amount +
                " with email " + email);
    }
}

public class Example {
    public static void main(String[] args) {
        PaymentMethod creditCard = new CreditCard("1234-5678-9876-5432"); // Interfaces can hold reference to the objects of classes that implement them.
        PaymentMethod payPal = new PayPal("user@example.com");

        creditCard.processPayment(100.00);
        payPal.processPayment(50.00);
    }
}
```


---
## 🆕 Java 8: Default Methods
- Default methods have a method body.
- They allow interfaces to evolve without breaking old implementations.

```java
interface Animal {
    default void eat() {
        System.out.println("Animal is eating.");
    }
}

class Dog implements Animal {
    // Inherits eat() method
}

public class Example {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat();
    }
}
```

---
## 🆕 Java 8: Static Methods
- Static methods in interfaces are like utility/helper methods.
- Can be called using the interface name.

```java
interface MathOperations {
    static int add(int a, int b) {
        return a + b;
    }
}

public class Example {
    public static void main(String[] args) {
        int result = MathOperations.add(5, 10);
        System.out.println("Sum: " + result);
    }
}
```
---
## 🆕 Java 9: Private Methods
- Used to hide common logic shared between default methods.
- Improves code organization and avoids duplication.

```java
interface Vehicle {
    private void startEngine() {
        System.out.println("Engine started.");
    }

    default void drive() {
        startEngine();
        System.out.println("Vehicle is driving.");
    }
}

class Car implements Vehicle {
    // Inherits drive() which uses private method startEngine()
}

public class Example {
    public static void main(String[] args) {
        Vehicle car = new Car();
        car.drive();
    }
}
```

---
**Summary**
| Feature                  | Description                                                       |
| ------------------------ | ----------------------------------------------------------------- |
| **Default Methods**      | Provide method implementations in interfaces (Java 8+)            |
| **Static Methods**       | Utility methods that belong to the interface itself               |
| **Private Methods**      | Encapsulate shared code used by other interface methods (Java 9+) |
| **Multiple Inheritance** | Achieved using interfaces                                         |
| **Abstraction**          | Helps hide implementation details                                 |


---
# 🧠 Abstraction in Java
- Abstraction is a core concept in object-oriented programming.
- It helps you hide complex internal details and show only the necessary features of an object.
- You can focus on what an object does, not how it does it.
- In Java, abstraction is implemented using abstract classes and interfaces.

**🎯 Purpose of Abstraction**
- Simplifies code by hiding unnecessary implementation details.
- Only essential information is visible to the user.
- The abstract keyword is used for abstract classes and abstract methods.

**✅ Two Ways to Achieve Abstraction in Java**
| Method             | Level of Abstraction |
| ------------------ | -------------------- |
| **Abstract Class** | 0% to 100%           |
| **Interface**      | 100% (up to Java 7)  |


**🧱 Abstract Classes and Methods**
- An abstract class:
    - Cannot be instantiated directly.
    - Can have both abstract methods (without a body) and concrete methods (with a body).
    - Can include constructors, static methods, and even final methods.
    - Final methods cannot be overridden by subclasses.

- An abstract method must be overridden by subclasses.
- Access control:
    - Abstract methods must not be private.
    - Concrete (non-abstract) methods can be private.
    - When overriding a method, the access level must be the same or more visible.

**💡 Example**
```java
abstract class Animal {
    public abstract void makeSound(); // Abstract method

    void eat() {                      // Concrete method
        System.out.println("This animal eats food.");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Woof!");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Meow!");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal myDog = new Dog();
        myDog.makeSound();  // Woof!
        myDog.eat();        // This animal eats food.

        Animal myCat = new Cat();
        myCat.makeSound();  // Meow!
        myCat.eat();        // This animal eats food.
    }
}
```

**📝 Try these**
1. ✅ Can an abstract class have zero abstract methods?
2. 🔁 When is the constructor of an abstract class called?

---
### 🆚 ABSTRACT CLASS vs INTERFACE
| Feature                  | **Abstract Class**                                                     | **Interface**                                                                                    |
| ------------------------ | ---------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------ |
| **Methods**              | Can have abstract & concrete methods (implemented and non-implemented) | All methods are implicitly abstract (Java 7), or can have `default` / `static` methods (Java 8+) |
| **Variables**            | Can have instance variables                                            | Only public static final variables (constants)                                                   |
| **Constructors**         | Can have constructors                                                  | Cannot have constructors                                                                         |
| **Multiple inheritance** | Supports single inheritance                                            | Supports multiple inheritance                                                                    |
| **Access modifiers**     | Methods can be `public`, `protected`, etc.                             | All methods are `public` by default                                                              |
| **Inheritance style**    | “is-a” relationship (Dog is a Animal)                                  | “can-do” capability (Dog can Run)                                                                |
| **Use case**             | Sharing code among subclasses                                          | Defining a contract / standard behavior                                                          |


**✅ When to use abstract class?**
- 👉 Use abstract classes when:
    - You want to share common code (implemented methods, fields) among closely related classes
    - You have common state (variables) to share
    - You need to provide base functionality which can be extended
    - You might add non-abstract methods with logic
- Example: Vehicle as an abstract class with shared engine or speed fields.


**✅ When to use interface?**
- 👉 Use interfaces when:
    - You want to define a contract (what a class must do, but not how)
    - You want to allow multiple inheritance of type (since a class can implement many interfaces)
    - You have unrelated classes that share capability
    - You don’t need shared state (variables)

- ➡️ Example: Flyable or Serializable interface — describing “what they can do”


> ✅ Use abstract class for common code & partial implementation.

> ✅ Use interface for defining common behavior across unrelated classes.

---
### 🆚 Anonymous Class vs Interface
| **Aspect**       | **Anonymous Class**                                       | **Interface**                                                                        |
| ---------------- | --------------------------------------------------------- | ------------------------------------------------------------------------------------ |
| **Definition**   | Nameless class defined and instantiated in one step       | Specifies method signatures (no implementation in Java 7; default/static in Java 8+) |
| **Purpose**      | One-time, short-lived implementation                      | Defines common behavior for unrelated classes                                        |
| **Creation**     | At runtime, inline                                        | At design/compile time                                                               |
| **Usage**        | Quick instantiation without declaring a class             | Describes what a class should do                                                     |
| **Reusability**  | Not reusable                                              | Reusable across implementing classes                                                 |
| **Access**       | Can use final/effectively final outer variables           | Cannot access local variables of outer classes                                       |
| **Constructors** | Cannot define constructors                                | Interfaces do not have constructors                                                  |
| **Inheritance**  | Extends one class or implements one interface anonymously | Classes can implement multiple interfaces                                            |

**✅ When to use Anonymous Class?**
- When you need a one-off class with a quick behavior
- To override a method or implement an interface for single-use
- Ideal for event handling or callbacks in GUI programming
```java
Runnable r = new Runnable() {
    public void run() {
        System.out.println("Anonymous run() method");
    }
};
```


---

# 🔄 Polymorphism in Java
- Polymorphism means performing a single action in multiple ways.
- The term comes from the Greek words:
    - "poly" meaning "many"
    - "morph" meaning "forms"
- In Java, it enables objects to behave differently based on their context.

**🧩 Types of Polymorphism**
1. Compile-Time Polymorphism (a.k.a Static Polymorphism)
→ Achieved via Method Overloading

2. Runtime Polymorphism (a.k.a Dynamic Polymorphism)
→ Achieved via Method Overriding

### 🧱 Compile-Time Polymorphism (Method Overloading)
- Multiple methods in the same class share the same name but have different parameter lists.
- Enhances readability and usability of the code.

**Method Overloading Rules:**
- ✅ Method names must be the same
- ✅ Parameter lists must be different (in type, number, or order)
- 🚫 Return type alone cannot differentiate overloaded methods.

**1. Different Number of Parameters**
```java
class Display {
	void show(int a) {
		System.out.println("Value: " + a);
	}

	void show(int a, int b) {
		System.out.println("Values: " + a + " and " + b);
	}
}
```

**2. Different Types of Parameters**
```java
class Printer {
	void print(int a) {
		System.out.println("Integer: " + a);
	}

	void print(double a) {
		System.out.println("Double: " + a);
	}
}
```

**3. Different Order of Parameters**
```java
class Calculator {
	void calculate(int a, double b) {
		System.out.println("Int and Double: " + a + ", " + b);
	}
	
	void calculate(double a, int b) {
		System.out.println("Double and Int: " + a + ", " + b);
	}
}
```

**❌ Incorrect Overloading (Compile-Time Error)**
```java
class Test {
	int getValue() { return 1; }
	double getValue() { return 1.0; }  // Error: same parameter list
}
```

**Fun with Overloading main Method**
```java
class ExampleTest {
	public static void main(String[] args) {
		System.out.println("main with String[]");
	}

	public static void main(String args) {
		System.out.println("main with String");
	}

	public static void main() {
		System.out.println("main without args");
	}
}
```
---
### 🔼 Type Promotion in Overloading
- Java automatically promotes smaller types to larger ones during method resolution.
- Promotion hierarchy:
    - byte → short → char → int → long → float → double

```java
class Calculator {
	void add(int a, int b) {
		System.out.println("int, int");
	}
	
	void add(double a, double b) {
		System.out.println("double, double");
	}
}

public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.add(5, 10);         // Calls add(int, int)
        calc.add(5.0, 10.0);     // Calls add(double, double)
        calc.add(5, 10.0);       // Calls add(double, double) due to promotion
    }
}
```

**Promotion with Different Types**
```java
class Test {
    void display(int a, double b) {
        System.out.println("Method with int and double parameters");
    }

    void display(double a, int b) {
        System.out.println("Method with double and int parameters");
    }
    
    void sum(int a, long b) { 
        System.out.println("Method with int and long parameters called");
    }  

    void sum(long a, int b) {
        System.out.println("Method with long and int parameters called");
    }   
}

public class Main {
    public static void main(String[] args) {
        Test test = new Test();
        
        test.display(5, 10.5);   // Calls method: display(int, double)
        test.display(10.5, 5);   // Calls method: display(double, int)
        
        test.display(5, 10);     // int, int: matches display(int, double) via implicit conversion

        test.sum(20, 20);        // int, int: matches sum(int, long) via implicit conversion
    }
}

```

**⚙️ Method Selection Order**
- When choosing between overloaded methods, Java uses:  
    - Exact Match
    - Type Promotion
    - Varargs (if no match is found)

---
## 🕒 Runtime Polymorphism (Method Overriding)
- A subclass provides a specific implementation of a method defined in its superclass.
- Enables dynamic method dispatch at runtime.

**Rules:**
- Method name and parameters must match exactly.
- The method must exist in both parent and child (IS-A relationship).
- Access modifier in the child class must be equal or more visible than in the parent class.
```java
class Bank {
	double getRateOfInterest() { return 5.6; }
}

class SBI extends Bank {
	double getRateOfInterest() { return 8.5; }
}
```
---
## METHOD HIDING
- Method hiding happens when a subclass defines a static method with the same name and parameters as a static method in its superclass.
- In Java, static methods belong to the class, not to instances (objects) of the class.
- The subclass method does not override the superclass static method—it hides it.
- The method call is resolved based on the reference type, not the actual object at runtime.
- Therefore, method resolution occurs at compile time, not at runtime.

**Are these declarations valid?**
```java
static void display()  // in parent class
void display()         // in child class

// OR 
void display()         // in parent class
static void display()  // in child class

```

**🔁 Method Hiding vs Method Overriding**
| Feature              | Method Overriding         | Method Hiding                 |
| -------------------- | ------------------------- | ----------------------------- |
| Applies to           | Non-static methods        | Static methods                |
| Binding type         | Runtime (dynamic) binding | Compile-time (static) binding |
| Accessed via         | Object reference          | Class reference               |
| Overriding rules     | Must have same signature  | Must have same signature      |
| Access modifier rule | Cannot weaken visibility  | Can be same or more visible   |

**NOTE**
- Static methods are not polymorphic.
- Method hiding is resolved at compile time based on the reference type.
- Static methods should be accessed using the class name, not an object reference.

**Method Hiding Example:**
```java
class SuperClass {
    static void staticMethod() {
        System.out.println("Static method in SuperClass");
    }
}

class SubClass extends SuperClass {
    static void staticMethod() {
        System.out.println("Static method in SubClass");
    }
}

public class Main {
    public static void main(String[] args) {
        SuperClass.staticMethod();     // Calls SuperClass method
        SubClass.staticMethod();       // Calls SubClass method

        SuperClass obj = new SubClass();
        obj.staticMethod();            // Still calls SuperClass method (based on reference type)
    }
}

```

### 🔃 Covariant Return Types
- Allows the overridden method to return a subclass of the original return type.
- Works only with non-primitive (reference) types.

```java
class Animal {
    public Animal getAnimal() { // Method that returns an instance of Animal
        return new Animal();
    }
}

class Dog extends Animal {
    @Override
    public Dog getAnimal() { // Overriding method that returns a Dog (a subclass of Animal)
        return new Dog();
    }
}

public class Example {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        Animal animal = myDog.getAnimal(); // getAnimal() returns a Dog, stored in an Animal reference
        System.out.println("Returned object type: " + animal.getClass().getSimpleName());
    }
}

```

---

### 🔗 Binding in Java
- Binding means connecting a method call to the actual method body.

**1. Static Binding (Early Binding)**
- Happens at compile time
- Applies to:
    - private, static, or final methods
```java
class Dog {
    // Private method accessible only within this class
    private void eat() {
        System.out.println("Dog is eating...");
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.eat(); // Calls the private method within the same class
    }
}

```

**2. Dynamic Binding (Late Binding)**
- Happens at runtime
- Applies to non-static methods overridden in subclasses

```java
class Animal {
    void eat() {
        System.out.println("Animal is eating...");
    }
}

class Dog extends Animal {
    @Override
    void eat() {
        System.out.println("Dog is eating...");
    }
}

class Cat extends Animal {
    @Override
    void eat() {
        System.out.println("Cat is eating...");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal animal;

        animal = new Dog();   // Upcasting Dog to Animal
        animal.eat();        

        animal = new Cat();   // Upcasting Cat to Animal
        animal.eat();         

        animal = null;
        animal.eat();         
    }
}

```

---
## INSTANCE INITIALIZER BLOCK
- An instance initializer block is used to initialize instance variables in Java.
- This block is executed every time an object is created, right after the parent class constructor is called.
- In Java, initialization logic can be placed in:
    - Methods
    - Constructors
    - Instance initializer blocks

```java
{
    // initialization logic
}
```

```java
class Bike {  
    int speed;

    {
        speed = 100;  // Instance initializer block
    }

    Bike() {
        System.out.println("Bike Constructor invoked.");
        System.out.println("Speed is " + speed);
    }

    public static void main(String[] args) {
        Bike b1 = new Bike();
        Bike b2 = new Bike();
    }
}
```

**Which Executes First: Constructor or Instance Initializer Block?**
```java
class Bike {  
    int speed;

    static {
        System.out.println("Static block invoked");
    }

    {
        System.out.println("Instance initializer block invoked");
    }

    Bike() {
        System.out.println("Constructor invoked");
    }
}
```

**Execution Order Rules:**
- Static blocks run once when the class is loaded.
- Instance initializer blocks run every time a new object is created, before the constructor.
- Parent class constructor is called first (via super()), then instance blocks, then the constructor.

**Example with Inheritance:**
```java
class A {
    {
        System.out.println("A class instance initializer block is invoked");
    }

    A() {
        System.out.println("Parent class constructor invoked");
    }
}

class B extends A {
    {
        System.out.println("B class instance initializer block is invoked");
    }

    B() {
        System.out.println("Child class constructor invoked");
    }

    B(int a) {
        System.out.println("Child class constructor invoked with value: " + a);
    }

    public static void main(String[] args) {
        B b1 = new B();
        B b2 = new B(10);
    }
}
```

---
## FINAL KEYWORD
- The final keyword is a modifier that can be applied to:
    - Variables
    - Methods
    - Classes
    - Method parameters
- It enforces immutability or restriction on modification.

**1. Final Variables**
> Once assigned, a final variable’s value cannot be changed.

```java
final double PI = 3.14;
PI = 3.15; // Compile-time error
```

- Final instance variables must be initialized either:
    - At the time of declaration, or
    - In the constructor

- Final static variables must be initialized:
    - At declaration, or
    - Inside a static block

```java
class A {
    final int num;

    A() {
        this.num = 0; // Allowed: initializing in constructor
    }
}
```

```java
final StringBuilder sb = new StringBuilder("Hello");
sb.append(" World"); // Allowed
sb = new StringBuilder("New"); // Compile-time error
```

**2. Final Methods**
- A method marked final cannot be overridden in a subclass.
```java
class Parent {
    public final void display() {
        System.out.println("Final method in Parent");
    }
}

class Child extends Parent {
    public void display() { // Error: Cannot override final method
        System.out.println("Attempting to override");
    }
}
```

**3. Final Classes**
- A class declared as final cannot be extended.
```java
final class FinalClass {
    // Implementation
}

class SubClass extends FinalClass { // Compile-time error
}
```

-Use cases:
- Immutability
- Security
- Preventing inheritance in sensitive classes

**4. Final Parameters**
- Final parameters cannot be reassigned within a method.
```java
public class Example {
    public void method(final int num) {
        num = 10; // Error: Cannot assign a value to final parameter
    }

    public static void main(String[] args) {
        Example example = new Example();
        example.method(5);
    }
}
```