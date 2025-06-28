# 🚀 MULTI-THREADING IN JAVA

### Task
- A task is a unit of work or an operation performed by a program or system.
- Tasks can range from simple calculations to complex workflows.
- They are managed and executed to achieve specific objectives.


### Single Tasking
- In single-tasking, the system performs one task at a time.
- It cannot switch to another task until the current task finishes.
- Example: A calculator app processing one calculation at a time.

### Multi-Tasking
- Multi-tasking allows a system to handle multiple tasks simultaneously.
- It achieves this by switching between tasks to maximize CPU and resource utilization.
- Multi-tasking is categorized as:
    - Process-based multitasking (multiprocessing)
    - Thread-based multitasking (multithreading)
- *Example: A web browser running multiple tabs, playing music, and downloading files all at once.*


### Multi-Processing
- Involves multiple processors or cores running separate processes concurrently.
- Each process runs independently and has its own memory space.
- Processes are considered heavyweight.
- *Example: A server with multiple cores handling parallel client requests.*
- Real-World Uses of Multiprocessing:
    - Server systems
    - Big data processing (MapReduce, Spark)
    - Scientific simulations
    - Database management (PostgreSQL, MySQL)
    - Operating systems
    - Multi-Threading

### Multi-Threading
- Involves multiple threads (smaller units of a process) running concurrently within the same process.
- Threads share the same memory space but run independently.
- A thread is a lightweight subprocess, the smallest unit of execution.
- Both multiprocessing and multithreading achieve multitasking.
- *Examples: web browsers, messaging apps, media players*
- Real-World Uses of Multithreading:
    - Web browsers
    - Messaging apps (e.g., WhatsApp, Slack)
    - Games
    - Download managers
    - Video/audio players


**Summary**
- Task: A unit of work performed by a system
- Single Tasking: One task at a time
- Multi-Tasking: Multiple tasks handled simultaneously
- Multi-Processing: Multiple processors executing multiple processes
- Multi-Threading: Multiple threads executing concurrently within a single process

---
## THREAD IN JAVA
- A thread is a lightweight subprocess — the smallest unit of execution.
- It defines an independent path of execution within a process.
- Multiple threads can run within a single process, sharing resources.
- The system performs context switching between threads.
- One process can contain multiple threads.

```plaintext
✅ Modern consumer laptops (say, typical i5, i7, Ryzen 5, Ryzen 7 processors) usually have:

Cores: Typically 4 to 12 cores

    e.g., an Intel Core i7-12700H has 14 cores (6 performance + 8 efficiency cores) a Ryzen 7 7840HS might have 8 cores

Threads: Usually double the core count due to hyper-threading / simultaneous multithreading

    for example, 8 cores with hyper-threading → 16 threads
    12 cores with hyper-threading → 20 threads (some architectures may not double every core)


// Example 

✅ CPU Model: AMD Ryzen 5 3550H with Radeon Vega Mobile Graphics
✅ Cores: 4
✅ Logical Processors (Threads): 8

This means it supports Simultaneous Multi-Threading (SMT), where each physical core can handle 2 threads.

In simple terms:
4 physical cores
8 logical threads

```

---
## Java Thread Class
- Java provides the Thread class to enable multithreading.
- This class includes constructors and methods to create and manage threads.
- It extends the Object class and implements the Runnable interface.
- It is part of the java.lang package.

**Constructors:**
```java
Thread()
Thread(String name)
Thread(Runnable target)
Thread(Runnable target, String name)
Thread(Runnable target, String name, long stackSize, int priority)
```

**Key Methods:**
- Thread lifecycle: start(), run(), sleep(long), interrupt(), join()
- Thread information: getId(), getName(), getState(), setName(String), isAlive(), currentThread()
- Thread priority: setPriority(int), getPriority()
- Synchronization: wait(), notify(), notifyAll() (from Object class)
- Deprecated: stop(), suspend(), resume(), checkAccess()

---
## 🔄 THREAD LIFE CYCLE
In Java, a thread can be in one of these states:

1. **New**
- Thread is created but not started yet.
```java
Thread thread = new Thread(); // New state
```

2. **Active**
- After start(), the thread becomes active.
- It transitions to runnable (waiting for CPU) or running (executing on CPU).
```java
thread.start(); // Runnable
```

3. **Blocked**
- Thread is waiting for a resource that is currently unavailable (e.g., a lock).
```java
synchronized(resource) {
  // thread might block here waiting for the lock
}
```

4. **Waiting**
- Thread waits indefinitely for another thread to notify it.
```java
synchronized(lock) {
  lock.wait(); // enters Waiting state
}
```

5. **Timed Waiting**
- Thread waits for a specific time before transitioning.
```java
Thread.sleep(1000); // Timed waiting
```

6. **Terminated**
- Thread has finished execution and cannot restart.
- *Example:* After run() finishes, the thread is terminated.

```text
New
  |
  v
Runnable <-----> Blocked
  |                  |
  v                  |
Waiting <-----> Timed Waiting
  |
  v
Terminated
```

**Summary**
- New: Created, not started
- Runnable: Ready for execution, waiting for CPU
- Blocked: Waiting for a resource
- Waiting: Waiting indefinitely for another thread
- Timed Waiting: Waiting for a set amount of time
- Terminated: Execution finished

**Example Code: Thread Life Cycle**
```java
public class ThreadLifeCycleExample {
    private static Object resource = new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                synchronized(resource) {
                    System.out.println(Thread.currentThread() + " is going to WAIT state.");
                    resource.wait(); // enters Waiting state
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("After creation: " + thread.getState()); // New
        thread.start();
        System.out.println("After start: " + thread.getState()); // Runnable

        Thread.sleep(500); // give time for the thread to enter waiting
        System.out.println("While waiting: " + thread.getState()); // Waiting or Blocked

        synchronized(resource) {
            System.out.println(Thread.currentThread() + " is notifying");
            resource.notify(); // wakes up thread
        }

        thread.join(); // wait for the thread to finish
        System.out.println("After completion: " + thread.getState()); // Terminated
    }
}
```

---
## 🚀 CUSTOM THREAD CREATION IN JAVA 
Java supports two primary ways to create threads:
- ✅ a) By extending the Thread class
- ✅ b) By implementing the Runnable interface


#### 1️⃣ Extending the Thread Class
- Extend Thread Class: Create your own class that extends Thread.
- Override run(): Define the logic you want the thread to execute inside the run() method.
- Create & Start: Make an instance of your thread class and call start() to begin execution.

```java
// Step 1: Extend Thread class
public class MyThread extends Thread {
    @Override
    public void run() { // Step 2: Override run()
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " executing task: " + i);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread thread = new MyThread(); // Step 3: Create
        thread.start();                   // Start the thread
    }
}
```

#### 2️⃣ Implementing the Runnable Interface
- Implement Runnable: Create a class that implements Runnable.
- Override run(): Provide the task code in the run() method.
- Thread + Runnable: Create a Thread object and pass your Runnable class instance to its constructor, then call start().

```java
// Step 1: Implement Runnable
public class MyRunnable implements Runnable {
    @Override
    public void run() { // Step 2: Override run()
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " executing task: " + i);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable); // Step 3
        thread.start();
    }
}
```

---
## ⚙️ THREAD SCHEDULER
- The Thread Scheduler in Java is the component responsible for deciding which thread to run and which to keep waiting.
- Only threads in the runnable state are chosen by the scheduler.
- If multiple threads are runnable, the scheduler chooses one based on certain rules.

**🧩 Factors affecting thread scheduling:**
- ✅ Priority (1 to 10): Higher priority threads are more likely to run first.
- ✅ Time of Arrival: If two threads have the same priority, their arrival order is considered.

**🛠 Common Scheduling Algorithms:**
- First-Come-First-Serve
- Time-Slicing
- Preemptive-Priority

---
##  💤 Thread.sleep()

- Thread.sleep() pauses the current thread for a given time (in ms).
- It is a static method of the Thread class.
- It throws InterruptedException if interrupted during sleep.

**Why use Thread.sleep()?**
- To introduce delays in code
- To simulate network or I/O latency

**Syntax**
```java
public static void sleep(long mls) throws InterruptedException  
public static void sleep(long mls, int n) throws InterruptedException

// ⚠️ Note: Sleeping with negative time → IllegalArgumentException
```
**Example:**
```java
public class SleepExample {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000); // 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

**🧩 Try these questions?**
- Can we call Thread.sleep() on the main thread? 
- Can we call start() twice? 
- Can we call run() directly? 

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Running in: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.run(); 
        System.out.println("Back in: " + Thread.currentThread().getName());
    }
}

```

---
##  🔗 join() METHOD
- The join() method lets one thread wait until another finishes.
- It ensures the current thread halts until the specified thread completes.
- Like sleep(), join timing is subject to the OS.

**What does join() do?**
- Waits for a thread to finish
- Synchronizes workflow between threads

**Syntax**
```java
public final void join() throws InterruptedException  
public final synchronized void join(long millis) throws InterruptedException  
public final synchronized void join(long millis, int nanos) throws InterruptedException

//  join(long millis) — after the time is up, thread switching can occur.
```

**Example**
```java
class ThreadJoin extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(500);
                System.out.println("Thread " + Thread.currentThread().getName() + ", executing TASK - " + i);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class Example {
    public static void main(String[] args) {
        ThreadJoin t1 = new ThreadJoin();
        ThreadJoin t2 = new ThreadJoin();
        ThreadJoin t3 = new ThreadJoin();

        t1.start();
        try {
            System.out.println("Main waiting for t1");
            t1.join();
        } catch (Exception e) { e.printStackTrace(); }

        t2.start();
        try {
            System.out.println("Main waiting for t2");
            t2.join();
        } catch (Exception e) { e.printStackTrace(); }

        t3.start();
    }
}
```

---
##  🏷️ NAMING THREADS
- Threads have default names like Thread-0, Thread-1, etc.
- You can use setName() or the constructor to change the name.

**Methods**
```java
public String getName()
public void setName(String name)
public static Thread currentThread()
```

**Example**
```java
class UserThread extends Thread {
    UserThread(String threadName) {
        super(threadName);
    }
    public void run() {
        System.out.println(Thread.currentThread());
        System.out.println(Thread.currentThread().getName() + " is executing..");
    }
}

public class Example {
    public static void main(String[] args) {
        UserThread ut1 = new UserThread("Slave Thread-1");
        UserThread ut2 = new UserThread("Slave Thread-2");

        System.out.println(ut1.getName());
        ut1.setName("Test Thread-1");
        ut1.start();
        ut2.start();
    }
}
```

---
## ⚖️ THREAD PRIORITY
- Thread priority in Java ranges from 1 (lowest) to 10 (highest).
- Higher-priority threads are more likely to be scheduled sooner, but it’s not guaranteed.
- Default is Thread.NORM_PRIORITY (5).

> ⚠️ Values outside 1–10 → IllegalArgumentException

**Methods**
```java
public final int getPriority()
public final void setPriority(int newPriority)
```

**Example**
```java
class Task implements Runnable {
    private String name;
    public Task(String name) { this.name = name; }
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " running iteration " + i);
            try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}

public class Example {
    public static void main(String[] args) {
        Thread highPriorityThread = new Thread(new Task("High Priority"), "High Priority Thread");
        Thread lowPriorityThread = new Thread(new Task("Low Priority"), "Low Priority Thread");

        highPriorityThread.setPriority(Thread.MAX_PRIORITY);
        lowPriorityThread.setPriority(Thread.MIN_PRIORITY);

        highPriorityThread.start();
        lowPriorityThread.start();
    }
}
```
---
## ☕️ DAEMON THREAD IN JAVA
- A daemon thread in Java is a background thread designed to support user threads by providing services.
- Its lifetime depends on active user threads — as soon as all user threads finish, the JVM will automatically terminate the daemon thread.
- There are many built-in daemon threads in Java, like the garbage collector (gc) and finalizer threads.
- Daemon threads typically have lower priority.
- Their primary role is to provide background services to user threads.
- Since their job is to support user threads, there’s no reason for the JVM to keep them alive when no user thread is running, so the JVM stops them automatically.

*🔎 Use Case: Daemon threads are perfect for background tasks that shouldn’t block the application from shutting down.*

**Methods**
```java
public void setDaemon(boolean status)
public boolean isDaemon()
```

**Example**
```java
public class DaemonThreadExample {
    public static void main(String[] args) {
        Thread daemonThread = new Thread(new Task());
        daemonThread.setDaemon(true); // Make this thread a daemon
        daemonThread.start();

        // Main thread work
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Main thread is running, iteration " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread finished. Exiting program.");
    }
}

class Task implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Daemon thread is running...");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

```

--- 

## JAVA THREAD POOL
- A thread pool is essentially a group of reusable threads for executing multiple tasks efficiently.
- Instead of creating a new thread for every single task, thread pools reuse existing threads to save resources.

**🔑 Key Concepts of a Thread Pool:**
- ✅ Reusable Threads
- ✅ Task Queue
- ✅ Fixed Number of Threads
- ✅ Managed and Controlled Execution

**👉 Ways to create a thread pool in Java:**
- ExecutorService – the main interface for managing thread pools
- ThreadPoolExecutor – provides advanced customization
- Executors class – provides convenient factory methods to build thread pools

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Create a pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit 10 tasks to the pool
        for (int i = 0; i < 10; i++) {
            Runnable task = new Task(i);
            executor.submit(task);
        }

        // Shut down the pool after tasks finish
        executor.shutdown();
    }
}

class Task implements Runnable {
    private int taskId;

    public Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println("Task " + taskId + " running in " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000); // Simulate work
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```

### ✅ What is Callable?

> In Java, Callable is an interface (part of java.util.concurrent) that is similar to Runnable, but with two key enhancements:

- It can return a result (generic type V)
-  It can throw a checked exception

```java
public interface Callable<V> {
    V call() throws Exception;
}
```
- So its call() method returns a value and allows throwing checked exceptions.

**Callable vs Runnable — Difference**
| Feature            | `Runnable`                      | `Callable`                                 |
| ------------------ | ------------------------------- | ------------------------------------------ |
| Return Value       | Cannot return a result          | Can return a result                        |
| Exception Handling | Cannot throw checked exceptions | Can throw checked exceptions               |
| Method to override | `run()`                         | `call()`                                   |
| Integrated with    | `Thread` class                  | Used with `ExecutorService` + `Future`     |
| Usage              | Fire-and-forget tasks           | Tasks where you need a result / exceptions |


**When to use which?**
- Use Runnable
    - 👉 When you do not need a result
    - 👉 Simple tasks, where you just execute logic and forget

- Use Callable
    - 👉 When you need to get a return value from the task
    - 👉 When you want to propagate checked exceptions
    - 👉 Useful for tasks involving calculations, I/O results, etc.

**Runnable**
```java
Runnable task = () -> {
    System.out.println("Running a Runnable");
};
new Thread(task).start();
```

**Callable with Future:**
```java
Callable<Integer> task = () -> {
    return 42;
};
ExecutorService executor = Executors.newSingleThreadExecutor();
Future<Integer> future = executor.submit(task);
System.out.println("Result: " + future.get());
executor.shutdown();
```

---
### ExecutorService Interface
- The ExecutorService is a core interface in Java’s java.util.concurrent package, designed to manage and control thread pools. 


**Lifecycle Management**
- `shutdown()`: Starts an orderly shutdown; executes submitted tasks but rejects new ones.
- `shutdownNow()`: Tries to stop active tasks and cancels waiting ones, returning pending tasks.
- `isShutdown()`: Checks if shutdown has been initiated.
- `isTerminated()`: Checks if all tasks finished after shutdown.
- `awaitTermination(timeout, unit)`: Waits for task completion after shutdown or until timeout.

**Task Submission**
- `<T> Future<T> submit(Callable<T> task)`: Submits a task that returns a value, with a Future for its result.
- `<T> Future<T> submit(Runnable task, T result)`: Submits a runnable, returning a Future with the specified result.
- `Future<?> submit(Runnable task)`: Submits a runnable, returning a Future representing it.
- `void execute(Runnable command)`: Launches a runnable command for execution.

**Batch Task Execution**
- `<T> List<Future<T>> invokeAll(tasks)`: Runs all tasks, returning Futures to track their results.
- `<T> List<Future<T>> invokeAll(tasks, timeout, unit)`: Runs all tasks with a timeout limit.
- `<T> T invokeAny(tasks)`: Runs tasks, returning the first successful result and cancelling the rest.
- `<T> T invokeAny(tasks, timeout, unit)`:` Runs tasks, returning one successful result within the timeout.



---

### **Executors Class — Methods**

**Thread pool creation**

`newFixedThreadPool(int nThreads)`
→ Creates a thread pool with a fixed number of threads.

`newCachedThreadPool()`
→ Creates a thread pool with an unbounded number of threads that are created as needed and reused when possible.

`newSingleThreadExecutor()`
→ Creates a thread pool with a single worker thread.

`newScheduledThreadPool(int corePoolSize)`
→ Creates a thread pool that can schedule commands to run after a delay or periodically.

`newSingleThreadScheduledExecutor()`
→ A single-threaded version of the scheduled executor.

**Factory methods for thread factories and callables**

`defaultThreadFactory()`
→ Returns a simple thread factory used to create new threads.

`privilegedThreadFactory()`
→ Returns a thread factory that creates threads with privileges of the current access control context.


**Utility for converting Runnables to Callables**

`callable(Runnable task, T result)`
→ Wraps a Runnable into a Callable returning the given result.

`callable(PrivilegedAction<T> action)`
→ Wraps a PrivilegedAction as a Callable.

`callable(PrivilegedExceptionAction<T> action)`
→ Wraps a PrivilegedExceptionAction as a Callable.

---

## SYNCHRONIZATION IN JAVA
- In Java, synchronization is a mechanism that controls access to shared resources when multiple threads are involved, ensuring thread safety and avoiding data inconsistencies.

- When multiple threads modify shared data at the same time, synchronization prevents conflicts and maintains data integrity.

**Why Synchronization is Needed?**
- 🗂️ Data Consistency: Prevents corruption or inconsistency when multiple threads update shared data concurrently.
- 🔒 Thread Safety: Ensures only one thread accesses a critical code section at a time.
- 🛑 Prevents thread interference.

**Types of Synchronization**
- 🖥️ Process Synchronization
- 🧵 Thread Synchronization

### 🔸 Thread Synchronization
Thread synchronization is divided into:
1. Mutual Exclusion
2. Inter-thread Communication


#### 1️⃣ Mutual Exclusion
- Mutual exclusion ensures threads don’t disturb each other while accessing shared data.
    - a) Synchronized Method
    - b) Synchronized Block
    - c) Static Synchronization

#### 2️⃣ Cooperation (Inter-thread Communication)
- Every Java object has a monitor lock (also called a mutex).
- When a thread wants to execute synchronized code, it must acquire this lock.

**🔹 a) Synchronized Method**
- Declaring a method synchronized means a thread gets the lock on the object before entering.
- Other threads must wait until the current thread finishes and releases the lock.

```java
class Table {
  synchronized void printTable(int n) {
    for (int i = 1; i <= 10; i++) {
      System.out.printf("%d * %d = %d\n", n, i, n * i);
      try { Thread.sleep(250); } catch (Exception e) {}
    }
  }
}

public class Example {
  public static void main(String[] args) {
    Table table = new Table();
    Thread t1 = new Thread(() -> table.printTable(5));
    Thread t2 = new Thread(() -> table.printTable(10));
    t1.start();
    t2.start();
  }
}
```

**🔹 b) Synchronized Block**
- Synchronizes only part of a method, giving finer control over which section is locked.
- This improves performance by keeping the locked region smaller.

```java
synchronized (object) {
  // critical section
}
```

```java
class Counter {
  private int count = 0;

  public void increment() {
    synchronized (this) {
      count++;
    }
  }

  public int getCount() {
    synchronized (this) {
      return count;
    }
  }
}
```

**🔹 c) Static Synchronization**
- A static synchronized method locks on the class rather than an instance, to protect static resources shared by all objects.
- Prevents interference between threads operating on different instances of the same class.

```java
public class ConnectionManager {
  private static int activeConnections = 0;

  public static synchronized void incrementConnections() {
    activeConnections++;
    System.out.println("Active connections: " + activeConnections);
  }

  public static void main(String[] args) {
    Thread t1 = new Thread(() -> {
      for (int i = 0; i < 10; i++) ConnectionManager.incrementConnections();
    });
    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 10; i++) ConnectionManager.incrementConnections();
    });
    t1.start();
    t2.start();
  }
}
```

---
## 🟥 DEADLOCK IN JAVA
> 👉 A deadlock happens when two or more threads wait for each other’s resources, so they never progress.

- 🟦 Example scenario
    - Thread1 holds ResourceA and waits for ResourceB
    - Thread2 holds ResourceB and waits for ResourceA
- ➡️ Both are blocked forever.

**Deadlock conditions**
- ✔️ Mutual Exclusion
- ✔️ Hold and Wait
- ✔️ No Preemption
- ✔️ Circular Wait

```java
public class DeadlockDemo {
  static final Object ResourceA = new Object();
  static final Object ResourceB = new Object();

  public static void main(String[] args) {
    Thread t1 = new Thread(() -> {
      synchronized (ResourceA) {
        System.out.println("Thread1 locked ResourceA");
        try { Thread.sleep(100); } catch (Exception e) {}
        System.out.println("Thread1 waiting for ResourceB");
        synchronized (ResourceB) {
          System.out.println("Thread1 locked ResourceB");
        }
      }
    });

    Thread t2 = new Thread(() -> {
      synchronized (ResourceB) {
        System.out.println("Thread2 locked ResourceB");
        try { Thread.sleep(100); } catch (Exception e) {}
        System.out.println("Thread2 waiting for ResourceA");
        synchronized (ResourceA) {
          System.out.println("Thread2 locked ResourceA");
        }
      }
    });

    t1.start();
    t2.start();
  }
}
```

**Avoiding Deadlocks**
- Avoid nested locks
- Use lock timeouts
- Detect deadlocks
- Use Thread.join()

---
## INTER-THREAD COMMUNICATION IN JAVA

- Inter-thread communication (aka cooperation) lets synchronized threads share information.
- When a thread is paused in a critical section, it can let another thread enter that section.
-  Implemented via Object class methods:
    - wait()
    - notify()
    - notifyAll()

**🔹 a) wait()**
- Causes the current thread to release the lock and wait until notified.
```java
public final void wait()
public final void wait(long timeout)
```

**🔹 b) notify()**
- Wakes up a single thread waiting on the object’s monitor.
```java
public final void notify()
```

**🔹 c) notifyAll()**
- Wakes up all threads waiting on the object’s monitor.
```java
public final void notifyAll()
```

**Example**
```java
public class InterThreadCommunicationDemo {
  static final Object lock = new Object();
  static boolean isReady = false;

  public static void main(String[] args) {
    Thread producer = new Thread(() -> {
      synchronized (lock) {
        try {
          System.out.println("Producer preparing data...");
          Thread.sleep(1000);
          isReady = true;
          System.out.println("Producer notifying consumer...");
          lock.notify();
        } catch (InterruptedException e) {}
      }
    });

    Thread consumer = new Thread(() -> {
      synchronized (lock) {
        while (!isReady) {
          try {
            System.out.println("Consumer waiting for data...");
            lock.wait();
          } catch (InterruptedException e) {}
        }
        System.out.println("Consumer received data!");
      }
    });

    consumer.start();
    producer.start();
  }
}
```

---
## Interrupting a Thread
- If a thread is sleeping or waiting, calling interrupt() will break it out of that state and throw an InterruptedException.
- If the thread is active, calling interrupt() sets its interrupt flag to true.
- Methods
    - interrupt()
    - interrupted()
    - isInterrupted()


```java
class UserThread extends Thread {
  public void run() {
    try {
      Thread.sleep(1000);
      System.out.println("executing task");
    } catch (InterruptedException e) {
      throw new RuntimeException("Thread was interrupted: " + e);
    }
  }
}

public class Example {
  public static void main(String[] args) {
    UserThread t = new UserThread();
    t.start();
    t.interrupt();
  }
}
```