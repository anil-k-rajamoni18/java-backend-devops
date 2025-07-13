## 1️⃣ What is a Collection?
- A collection in Java is an object that groups multiple elements into a single unit.
- It is used to store, retrieve, manipulate, and communicate aggregate data.
- Think of it like a container to hold objects (e.g., a list of names, a set of IDs).
-  Example: *List<String> names = new ArrayList<>();*

---
## 2️⃣ What is the Collection Framework?
> ✅ The Java Collection Framework is a unified architecture to store and manipulate groups of objects.

- The Java Collections Framework makes it easy to work with groups of objects in an efficient and organized manner. 
- It simplifies application development by providing built-in methods to add, remove, search, and sort objects effectively.

-  It provides:
    - interfaces (e.g., List, Set, Map)
    - concrete implementations (e.g., ArrayList, HashSet, HashMap)
    - algorithms (e.g., sorting, searching)
- Benefits: reusable data structures, consistent API, easier to learn and maintain.

---
## 3️⃣ Why do we need the Collection Framework?
- Before Java 1.2, developers used arrays and custom data structures, which were error-prone and inflexible.
- The framework standardizes how collections behave:
    - improves productivity
    - reduces programming effort
    - ensures type-safety (with generics)
    - supports algorithm reuse (sorting, searching, etc.)

---
## 4️⃣ Collection Framework Hierarchy
> 🗂️ The hierarchy is built around key interfaces:
- Collection (root interface)
    - List (ordered, allows duplicates)
        - ArrayList, LinkedList, Vector
    - Set (no duplicates)
        - HashSet, LinkedHashSet, TreeSet
    - Queue (FIFO, ordering)
    - PriorityQueue, LinkedList

- Map (separate hierarchy)
    - HashMap, LinkedHashMap, TreeMap

![](https://cdn.hashnode.com/res/hashnode/image/upload/v1736532451482/6ef571c1-afe0-4314-9038-b472b06f4065.webp)

-  The Iterable interface is the root, enabling iteration over elements.
-  Collection extends Iterable, adding methods to manage elements.
-  List, Set, and Queue further extend Collection, with classes like ArrayList and HashSet implementing them.
-  Map is part of the framework but does not extend Collection.
-  All these types belong to the java.util package.


- *👉 Note:* Many people get confused between Collection and Collections. Collection is an interface within the framework, while Collections is a utility class that offers static methods to operate on collection elements.


---

### Collection Interface
- The Collection interface is the root interface of the Java Collections Framework (except for maps).
- It represents a group of objects known as elements.
- Provides basic methods for adding, removing, and querying elements.
- It is implemented by more specific interfaces like List, Set, and Queue.
- Belongs to `java.util.Collection`


> 👉 Think of it as the common contract for working with groups of objects.

**✅ Key Methods**
- **`boolean add(E e)`**  
  Adds an element to the collection.

- **`boolean addAll(Collection<? extends E> c)`**  
  Adds all elements from another collection.

- **`void clear()`**  
  Removes every element from the collection.

- **`boolean contains(Object o)`**  
  Checks if a specific element exists in the collection.

- **`boolean containsAll(Collection<?> c)`**  
  Checks if all elements from another collection exist in this collection.

- **`boolean isEmpty()`**  
  Checks if the collection has no elements.

- **`Iterator<E> iterator()`**  
  Returns an iterator for traversing the collection’s elements.

- **`boolean remove(Object o)`**  
  Removes a specified element from the collection.

- **`boolean removeAll(Collection<?> c)`**  
  Removes all elements present in another collection from this one.

- **`boolean retainAll(Collection<?> c)`**  
  Keeps only the elements also found in another collection, removing the rest.

- **`int size()`**  
  Returns the total number of elements in the collection.

- **`Object[] toArray()`**  
  Converts the collection to an array of objects.

- **`<T> T[] toArray(T[] a)`**  
  Copies the elements into a provided array of the specified type.

---
### List Interface
- List is a subinterface of Collection in the Java Collections Framework.
- It represents an ordered collection (also called a sequence) that can contain duplicate elements.
- Elements can be accessed by their index (position).

> 👉 Think of it like an array, but resizable and with more features.


**Properties**
- Ordered — preserves insertion order
- Allows duplicates
- Supports positional access (indexes)
- Can contain null elements (depending on implementation)
- A List is not synchronized by default

**Methods**
- **`add(E e)`**  
  Adds an element to the end of the list.

- **`add(int index, E element)`**  
  Inserts an element at a specific position.

- **`get(int index)`**  
  Retrieves the element at the specified index.

- **`set(int index, E element)`**  
  Replaces the element at the given index.

- **`remove(int index)`**  
  Removes the element at the specified index.

- **`indexOf(Object o)`**  
  Finds the first occurrence index of the given element.

- **`lastIndexOf(Object o)`**  
  Finds the last occurrence index of the given element.

- **`subList(int fromIndex, int toIndex)`**  
  Returns a view of a portion of the list between two indexes.

- **`listIterator()`**  
  Provides a list iterator for bidirectional traversal.


#### Implementation Classes of List
The main classes that implement the List interface are:

a) ArrayList
- Resizable array
- Fast random access
- Slower for inserts/removals in the middle

b) LinkedList
- Doubly linked list
- Faster inserts/removals in the middle
- Slower random access

c) Vector
- Synchronized
- Legacy class, rarely used now

d) Stack
- Subclass of Vector implementing LIFO (last-in, first-out)


----
### ArrayList 
- ArrayList is a resizable array implementation of the List interface.
- Part of the java.util package.
- Allows duplicate elements and maintains insertion order.
- Supports random access since elements are indexed.

> 👉 Think of it as a dynamic array that can grow or shrink as needed.

**📍 Where is ArrayList used?**
- ✅ When you need:
  - Fast read/search (using indices) 🏃‍♂️
  - Dynamic array behavior (size changes) 📈
  - Ordered collection of elements 📑
  - Storing duplicate values (unlike Set) 🔁

- ⚠️ Not ideal for frequent insertions/deletions in the middle of the list, since shifting elements is costly.

**Constructors**
| Constructor               | Description                                              |
| ------------------------- | -------------------------------------------------------- |
| `ArrayList()`             | Creates an empty list with default capacity (10)         |
| `ArrayList(int capacity)` | Creates an empty list with given initial capacity        |
| `ArrayList(Collection c)` | Creates a list containing elements of another collection |




**Methods**
```java
🔹 add(E e) – Add element to the end
🔹 add(int index, E element) – Insert at specific position
🔹 get(int index) – Get element at position
🔹 set(int index, E element) – Replace element at index
🔹 remove(int index) – Remove by index
🔹 remove(Object o) – Remove by object
🔹 size() – Get current size
🔹 isEmpty() – Check if empty
🔹 clear() – Remove all elements
🔹 contains(Object o) – Check if element exists
🔹 indexOf(Object o) – Get first index of element
🔹 lastIndexOf(Object o) – Get last index of element
🔹 toArray() – Convert to array
🔹 iterator() – Get an iterator
```

**🔗 Key Points**
- ✅ Allows null elements
- ✅ Not synchronized (not thread-safe by default) — use Collections.synchronizedList() if needed
- ✅ Grows its internal array automatically
- ✅ Better for read-heavy scenarios




**Program Example**
```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ArrayListDemo {
    public static void main(String[] args) {

        // Constructor 1: Default capacity
        ArrayList<String> list1 = new ArrayList<>();

        // Using add() to insert elements
        list1.add("Apple");
        list1.add("Banana");
        list1.add("Cherry");

        // Constructor 2: With initial capacity
        ArrayList<String> list2 = new ArrayList<>(5);
        list2.add("Date");
        list2.add("Elderberry");

        // Constructor 3: Using another collection
        ArrayList<String> list3 = new ArrayList<>(list1); // Cloning list1

        // Using add(index, element)
        list3.add(1, "Blueberry");

        // Using get() and set()
        System.out.println("Element at index 2: " + list3.get(2));
        list3.set(2, "Coconut");

        // Remove elements
        list3.remove("Banana"); // by value
        list3.remove(0);        // by index

        // Using contains(), size(), isEmpty()
        System.out.println("Contains 'Apple'? " + list3.contains("Apple"));
        System.out.println("Size: " + list3.size());
        System.out.println("Is empty? " + list3.isEmpty());

        // Iterating the list
        System.out.println("Final list3 elements:");
        for (String fruit : list3) {
            System.out.println(fruit);
        }

        // Conversion: ArrayList to Array
        String[] arrayFromList = list3.toArray(new String[0]);
        System.out.println("Converted to Array: " + Arrays.toString(arrayFromList));

        // Conversion: Array to ArrayList
        String[] fruitsArray = {"Grapes", "Honeydew", "Indian Fig"};
        ArrayList<String> listFromArray = new ArrayList<>(Arrays.asList(fruitsArray));
        System.out.println("Array to ArrayList: " + listFromArray);

        // Making list thread-safe
        ArrayList<String> syncList = new ArrayList<>(listFromArray);
        Collections.synchronizedList(syncList);

        // Clear the list
        list3.clear();
        System.out.println("After clear, list3 is empty? " + list3.isEmpty());
    }
}

```

**🚀 Performance Considerations**
| Operation           | Time Complexity |
| ------------------- | --------------- |
| `add()` (amortized) | O(1)            |
| `get()` / `set()`   | O(1)            |
| `remove()` (index)  | O(n)            |
| `contains()`        | O(n)            |


---
## LinkedList 
- LinkedList is a doubly-linked list implementation of the List and Deque interfaces.
- Found in java.util package.
- Allows sequential access and manipulation of elements.

**🔍 Why & Where to Use LinkedList**
- ✅ Use When:
  - You need frequent insertions and deletions in the middle of the list.
  - Memory reallocation (like in arrays) is a concern.
  - You need queue, stack, or deque behavior (as LinkedList also implements Deque).

- ❌ Avoid When:
  - You need frequent random access, as LinkedList has linear time complexity for indexing.


**🔄 LinkedList vs ArrayList**
| Feature            | `ArrayList`          | `LinkedList`              |
| ------------------ | -------------------- | ------------------------- |
| Backing structure  | Dynamic array        | Doubly linked list        |
| Access time        | O(1) (random access) | O(n) (sequential access)  |
| Insertion/deletion | Slower (O(n) worst)  | Faster (O(1) at ends)     |
| Memory usage       | Less (no node links) | More (node + links)       |
| Best for           | Access-heavy tasks   | Insert/delete-heavy tasks |


**🏗️ Constructors**
```java
LinkedList<E> list = new LinkedList<>();
LinkedList<E> list2 = new LinkedList<>(Collection<? extends E> c);
```

**🧰 Common Methods**
| Method                                 | Description                    |
| -------------------------------------- | ------------------------------ |
| `add(E e)`                             | Adds at end                    |
| `add(int index, E e)`                  | Inserts at index               |
| `addFirst(E e)`                        | Adds at beginning              |
| `addLast(E e)`                         | Adds at end                    |
| `getFirst()` / `getLast()`             | Access first/last              |
| `remove()`                             | Removes first element          |
| `remove(int index)`                    | Removes at index               |
| `removeFirst()` / `removeLast()`       | Remove from ends               |
| `peek()`, `peekFirst()` / `peekLast()` | View elements without removing |
| `poll()`, `pollFirst()` / `pollLast()` | Retrieve and remove            |
| `offer(E e)`                           | Add element (as queue)         |
| `contains(E e)`                        | Checks for presence            |
| `clear()`                              | Empties the list               |


**🚀 Performance**
| Operation              | Time Complexity                                     |
| ---------------------- | --------------------------------------------------- |
| Access (by index)      | O(n)                                                |
| Add/remove (head/tail) | O(1)                                                |
| Add/remove (middle)    | O(n)                                                |
| Search                 | O(n)                                                |
| Space                  | O(n) + extra for node links (2 references per node) |


**💡 Key Points**
- Implements List, Deque, Queue.
- Allows null, duplicates, and maintains insertion order.
- Can be used as Queue, Stack, or Deque.
- Not thread-safe — use Collections.synchronizedList() or external synchronization.
- Avoid using get(index) in loops – performance hit!

**Code Example**
```java
import java.util.*;

public class LinkedListDemo {
    public static void main(String[] args) {

        // Constructor 1: Empty LinkedList
        LinkedList<String> list1 = new LinkedList<>();

        // Add elements
        list1.add("Alpha");
        list1.add("Bravo");
        list1.addLast("Delta");
        list1.addFirst("Start");
        list1.add(2, "Charlie");

        // Constructor 2: Using another collection
        LinkedList<String> list2 = new LinkedList<>(list1);

        // Access elements
        System.out.println("First: " + list2.getFirst());
        System.out.println("Last: " + list2.getLast());
        System.out.println("Element at 2: " + list2.get(2));

        // Remove elements
        list2.remove();           // removes first
        list2.removeLast();       // removes last
        list2.remove("Bravo");    // removes by value
        list2.remove(1);          // removes by index

        // Offer and poll (Queue-like behavior)
        list2.offer("Echo");
        System.out.println("Polled: " + list2.poll());

        // Peek without removing
        System.out.println("Peek: " + list2.peek());

        // Contains, size, isEmpty
        System.out.println("Contains 'Alpha'? " + list2.contains("Alpha"));
        System.out.println("Size: " + list2.size());
        System.out.println("Is Empty? " + list2.isEmpty());

        // Convert to Array
        Object[] array = list2.toArray();
        System.out.println("Converted to Array: " + Arrays.toString(array));

        // Convert from Array
        String[] arr = {"X", "Y", "Z"};
        LinkedList<String> list3 = new LinkedList<>(Arrays.asList(arr));
        System.out.println("From Array: " + list3);

        // Clear list
        list2.clear();
        System.out.println("After clear: " + list2);
    }
}
```

---
## 📚 Stack 
- Stack is a Last-In-First-Out (LIFO) data structure.
- In Java, it is implemented as a class in java.util package, which extends Vector.
- Elements are added (pushed) and removed (popped) from the top of the stack.


**❓Why & Where to Use Stack**
- ✅ Why Stack?
  - Easy to manage nested structures (function calls, brackets, expressions).
  - LIFO nature suits undo operations, backtracking, etc.

- 📍 Where Stack is Used:
  - Parsing expressions (infix → postfix)
  - Undo functionality in editors
  - Backtracking algorithms (DFS)
  - Syntax validation (e.g., parentheses matching)
  - Web browsers (back/forward navigation)

**🏗️ Constructors**
```java
Stack<Type> stack = new Stack<>();
```
- No arguments; initializes an empty stack.

**🔧 Common Methods:**
| Method             | Description                             |
| ------------------ | --------------------------------------- |
| `push(E item)`     | Adds item to top                        |
| `pop()`            | Removes and returns top element         |
| `peek()`           | Returns top without removing            |
| `empty()`          | Checks if stack is empty                |
| `search(Object o)` | Returns 1-based position from top or -1 |
| `size()`           | Returns number of elements              |


**🚀 Performance**
| Operation  | Time Complexity | Space Complexity |
| ---------- | --------------- | ---------------- |
| `push()`   | O(1)            | O(1)             |
| `pop()`    | O(1)            | O(1)             |
| `peek()`   | O(1)            | O(1)             |
| `search()` | O(n)            | O(1)             |


**Example**
```java
import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {

        // Creating a stack
        Stack<String> stack = new Stack<>();

        // Pushing elements
        stack.push("Java");
        stack.push("Python");
        stack.push("C++");

        // Peeking top element
        System.out.println("Top element: " + stack.peek()); // C++

        // Searching an element
        System.out.println("Position of 'Python': " + stack.search("Python")); // 2

        // Popping elements
        System.out.println("Popped: " + stack.pop()); // C++

        // Checking size
        System.out.println("Current size: " + stack.size()); // 2

        // Checking if empty
        System.out.println("Is empty? " + stack.empty()); // false

        // Popping remaining elements
        stack.pop();
        stack.pop();

        // Now it's empty
        System.out.println("Is empty now? " + stack.empty()); // true
    }
}

```

**Key Points**
- Stack is a class, not an interface (unlike List, Set, etc.).
- It extends Vector, so inherits methods like add(), remove(), etc. – but prefer using stack-specific methods like push() and pop() for clarity.
- Thread-safe (because Vector is synchronized), but less efficient than Deque (via ArrayDeque) in single-threaded contexts.
- ArrayDeque is often preferred in modern Java for stack operations.

---
## 📚 Queue Interface
- Queue is a First-In-First-Out (FIFO) data structure.
- Defined in the java.util package as an interface.
- Used to hold elements prior to processing.
- Offers methods for insertion, removal, and inspection.


**❓Why & Where to Use Queue**
- ✅ Why Queue?
  - Maintains the order of processing.
  - Efficient for producer-consumer or breadth-first search patterns.

- 📍 Where Queue is Used:
  - Task scheduling (e.g., job queues, print queues)
  - BFS (Breadth-First Search) in graphs/trees
  - Message passing between threads
  - Buffering (e.g., keyboard buffer, IO)


### **Implementation Class: PriorityQueue**
- An implementation of Queue interface that orders elements using natural ordering or a custom comparator.
- It does not guarantee FIFO; highest-priority element is served first.
- Backed by a heap-based structure.

**Constructors**
```java
PriorityQueue<Integer> pq = new PriorityQueue<>(); // natural order
PriorityQueue<String> pq2 = new PriorityQueue<>(Comparator.reverseOrder()); // custom comparator
PriorityQueue<Integer> pq3 = new PriorityQueue<>(initialCapacity);
PriorityQueue<Integer> pq4 = new PriorityQueue<>(Collection);
```

**📌 Queue Interface Methods (via PriorityQueue)**
| Method                    | Description                      |
| ------------------------- | -------------------------------- |
| `add(E e)` / `offer(E e)` | Inserts element                  |
| `poll()`                  | Retrieves and removes head       |
| `peek()`                  | Retrieves head without removing  |
| `remove()`                | Removes head or specific element |
| `contains(Object o)`      | Checks existence                 |
| `size()`                  | Number of elements               |
| `clear()`                 | Empties the queue                |
| `isEmpty()`               | Checks if queue is empty         |

**Note:** offer() is preferred over add() as it does not throw exceptions when capacity is exceeded.

**🚀 Performance**
| Operation             | Time Complexity | Space Complexity |
| --------------------- | --------------- | ---------------- |
| `offer()` / `add()`   | O(log n)        | O(n)             |
| `poll()` / `remove()` | O(log n)        | O(n)             |
| `peek()`              | O(1)            | O(n)             |
| `contains()`          | O(n)            | O(n)             |

> Internally uses a binary heap.


**Code Example**
```java
import java.util.PriorityQueue;
import java.util.Comparator;

public class QueueExample {
    public static void main(String[] args) {

        // Natural ordering (min-heap)
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Adding elements
        pq.add(30);
        pq.offer(10);
        pq.add(20);

        // Peeking head element
        System.out.println("Head: " + pq.peek()); // 10

        // Removing elements
        System.out.println("Removed: " + pq.poll()); // 10
        System.out.println("Now head: " + pq.peek()); // 20

        // Size and contains
        System.out.println("Size: " + pq.size()); // 2
        System.out.println("Contains 30? " + pq.contains(30)); // true

        // Custom comparator (max-heap)
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
        maxPQ.offer(5);
        maxPQ.offer(1);
        maxPQ.offer(9);

        System.out.println("Max-PQ Head: " + maxPQ.peek()); // 9

        // Iterating
        System.out.println("All elements in maxPQ:");
        while (!maxPQ.isEmpty()) {
            System.out.println(maxPQ.poll());
        }

        // Clear queue
        pq.clear();
        System.out.println("Is pq empty after clear? " + pq.isEmpty()); // true
    }
}
```

**📝 Key Points**
- Queue is an interface; PriorityQueue is a common concrete implementation.
- Does not allow null elements.
- Elements are ordered based on natural order or comparator.
- PriorityQueue is not thread-safe — use ConcurrentLinkedQueue or PriorityBlockingQueue for concurrency.
- Insertion/removal is O(log n) because of the heap structure.
- Use offer() and poll() over add() and remove() to avoid exceptions.



---
## Deque Interface 

- Deque stands for Double-Ended Queue.
- It allows insertion and deletion of elements from both ends (front and rear).
- Part of the java.util package, introduced in Java 6.
- Pronounced as "deck".

> Deque<E> extends Queue<E> interface.

**❓Why & Where to Use Deque**
- ✅ Why Use Deque?
  - More flexible than a regular queue or stack.
  - Can behave as both Queue (FIFO) and Stack (LIFO).
  - Avoids overhead of using both Stack and Queue separately.

- 📍 Where It’s Used:
  - Palindrome checkers
  - Undo/redo systems
  - Sliding window problems
  - Backtracking algorithms
  - Task scheduling (priority at front or end)


### 🧱 Implementations of Deque
| Implementation Class | Description                                         |
| -------------------- | --------------------------------------------------- |
| **`ArrayDeque`**     | Resizable-array implementation. Fast and efficient. |
| **`LinkedList`**     | Doubly-linked list. Supports `Deque` and `List`.    |


**🏗️ Constructors**

- ✅ ArrayDeque Constructors:
```java
Deque<String> deque1 = new ArrayDeque<>();
Deque<Integer> deque2 = new ArrayDeque<>(50); // with initial capacity
```

- ✅ LinkedList Constructor:
```java
Deque<String> deque3 = new LinkedList<>();
```

#### **Common Deque Methods**

**Insertion Methods:**
| Method            | Description                                   |
| ----------------- | --------------------------------------------- |
| `addFirst(E e)`   | Inserts at front                              |
| `addLast(E e)`    | Inserts at end                                |
| `offerFirst(E e)` | Same as addFirst but returns false on failure |
| `offerLast(E e)`  | Same as addLast but returns false on failure  |


**❌ Removal Methods:**
| Method          | Description                          |
| --------------- | ------------------------------------ |
| `removeFirst()` | Removes front element                |
| `removeLast()`  | Removes last element                 |
| `pollFirst()`   | Removes front, returns null if empty |
| `pollLast()`    | Removes last, returns null if empty  |


**👀 Access Methods:**
| Method                       | Description  |
| ---------------------------- | ------------ |
| `getFirst()` / `peekFirst()` | Access front |
| `getLast()` / `peekLast()`   | Access end   |


**🚀Performance**
| Operation               | ArrayDeque | LinkedList |
| ----------------------- | ---------- | ---------- |
| Add/Remove at both ends | O(1)       | O(1)       |
| Access by index         | O(n)       | O(n)       |
| Space                   | O(n)       | O(n)       |


**Note**: ArrayDeque does not allow null elements, whereas LinkedList does.


**Code Example**
```java
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.LinkedList;

public class DequeExample {
    public static void main(String[] args) {

        // Using ArrayDeque
        Deque<String> deque = new ArrayDeque<>();

        // Adding elements at both ends
        deque.addFirst("Java");
        deque.addLast("Python");
        deque.offerFirst("C++");
        deque.offerLast("Go");

        System.out.println("Deque after additions: " + deque);

        // Accessing elements
        System.out.println("First Element: " + deque.peekFirst());
        System.out.println("Last Element: " + deque.peekLast());

        // Removing elements
        deque.removeFirst(); // C++
        deque.pollLast();    // Go

        System.out.println("Deque after removals: " + deque);

        // Using LinkedList as Deque
        Deque<Integer> intDeque = new LinkedList<>();
        intDeque.offer(1);
        intDeque.offerLast(2);
        intDeque.offerFirst(0);

        System.out.println("Integer Deque: " + intDeque);
    }
}

```

**📝 Key Points**
- Deque is versatile: supports both queue and stack operations.
- Prefer ArrayDeque over Stack or LinkedList for better performance.
- Null elements are not allowed in ArrayDeque but allowed in LinkedList.
- Not thread-safe — use ConcurrentLinkedDeque if needed in concurrent contexts.
- Method naming: add, offer, remove, and poll pairs differ in exception vs null behavior.

---
## 📚 Set Interface
- Set is a collection that contains no duplicate elements.
- Part of the java.util package.
- A subinterface of Collection.
- Does not maintain insertion order (depends on implementation).


**❓Why & Where to Use Set**
- ✅ Why Set?
  - Ensures uniqueness of elements.
  - Efficient for membership tests (contains()).
  - Useful in removing duplicates from data.

- 📍 Where Set is Used:
  - Storing unique IDs, usernames, or tags
  - Ensuring no duplicates in a dataset
  - Performing set operations (union, intersection, difference)

**🏗️ Implementation Classes of Set**
| Class           | Description                      |
| --------------- | -------------------------------- |
| `HashSet`       | Unordered, backed by hash table  |
| `LinkedHashSet` | Maintains insertion order        |
| `TreeSet`       | Sorted, backed by Red-Black tree |

> 👉 Focus HashSet (commonly used)


**📌 Common Methods:**
| Method                 | Description                               |
| ---------------------- | ----------------------------------------- |
| `add(E e)`             | Adds element if not already present       |
| `addAll(Collection c)` | Adds all elements from another collection |
| `remove(Object o)`     | Removes the specified element             |
| `contains(Object o)`   | Checks if the element exists              |
| `size()`               | Returns the number of elements            |
| `isEmpty()`            | Checks if the set is empty                |
| `clear()`              | Removes all elements                      |
| `iterator()`           | Returns an iterator over the set          |

**Note: Duplicate add() calls have no effect.**

**🚀 Performance**
| Operation                           | `HashSet`        | `LinkedHashSet` | `TreeSet`   |
| ----------------------------------- | ---------------- | --------------- | ----------- |
| `add()` / `remove()` / `contains()` | O(1) average     | O(1)            | O(log n)    |
| `iteration order`                   | Unordered        | Insertion order | Sorted      |
| `null elements`                     | Allowed (1 null) | Allowed         | Not allowed |


> Space Complexity: O(n)


### ✅ Constructors (for HashSet):
```java
Set<String> set1 = new HashSet<>();
Set<String> set2 = new HashSet<>(20); // with initial capacity
Set<String> set3 = new HashSet<>(Collection<? extends E> c);
```

**Code Example**
```java
import java.util.*;

public class SetExample {
    public static void main(String[] args) {
        // Creating a HashSet
        Set<String> fruits = new HashSet<>();

        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Apple"); // Duplicate, won't be added

        // Checking size and contents
        System.out.println("Set size: " + fruits.size());
        System.out.println("Contains Banana? " + fruits.contains("Banana"));

        // Removing element
        fruits.remove("Banana");

        // Iterating using for-each loop
        System.out.println("Elements in Set:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        // addAll()
        Set<String> tropicalFruits = new HashSet<>(Arrays.asList("Mango", "Pineapple"));
        fruits.addAll(tropicalFruits);

        // Final set after addAll
        System.out.println("After addAll:");
        System.out.println(fruits);

        // Clear the set
        fruits.clear();
        System.out.println("Is set empty now? " + fruits.isEmpty());
    }
}
```

**Key Points**
- Set ensures uniqueness – no duplicates allowed.
- HashSet is unordered, fast for lookups and inserts.
- LinkedHashSet maintains insertion order.
- TreeSet maintains natural (sorted) order or custom comparator.
- Set allows at most one null element (only in HashSet and LinkedHashSet).
- Best choice when order doesn't matter and duplicates must be avoided.

---
### 🔗 LinkedHashSet 
- A subclass of HashSet.
- Maintains insertion order.
- Backed by a hash table + doubly-linked list.

**🔧 Constructors:**
```java
LinkedHashSet<Type> lhs = new LinkedHashSet<>();
LinkedHashSet<Type> lhs2 = new LinkedHashSet<>(initialCapacity);
LinkedHashSet<Type> lhs3 = new LinkedHashSet<>(Collection);
```

**🛠️ Key Features:**
| Feature            | Description                                     |
| ------------------ | ----------------------------------------------- |
| Maintains Order    | ✅ Yes, **insertion order**                      |
| Allows null        | ✅ Yes (only one `null` value)                   |
| Duplicate Handling | ❌ No duplicates allowed                         |
| Thread-safe        | ❌ No (use synchronization externally if needed) |

**🚀 Performance**
| Operation                         | Time Complexity |
| --------------------------------- | --------------- |
| `add()`, `remove()`, `contains()` | O(1)            |
| `iteration`                       | O(n)            |


**✅ Use Case:**
- When you need fast performance like HashSet but also want to preserve the order in which elements were inserted.


**🧑‍💻 LinkedHashSet**
```java
import java.util.*;

public class LinkedHashSetDemo {
    public static void main(String[] args) {
        LinkedHashSet<String> lhs = new LinkedHashSet<>();

        lhs.add("Java");
        lhs.add("Python");
        lhs.add("C++");
        lhs.add("Java"); // Duplicate, ignored

        System.out.println("LinkedHashSet (insertion order):");
        for (String lang : lhs) {
            System.out.println(lang);
        }
    }
}
```

### 🌲 TreeSet 
- Implements NavigableSet (a subtype of SortedSet).
- Maintains elements in sorted (natural or custom) order.
- Backed by a Red-Black Tree.

**🔧 Constructors:**
```java
TreeSet<Type> ts = new TreeSet<>();
TreeSet<Type> ts2 = new TreeSet<>(Comparator);
TreeSet<Type> ts3 = new TreeSet<>(Collection);
```

**🛠️ Key Features:**
| Feature            | Description                          |
| ------------------ | ------------------------------------ |
| Maintains Order    | ✅ Yes, **sorted order**              |
| Allows null        | ❌ No (throws `NullPointerException`) |
| Duplicate Handling | ❌ No duplicates allowed              |
| Thread-safe        | ❌ No                                 |

**🚀 Performance**
| Operation                         | Time Complexity |
| --------------------------------- | --------------- |
| `add()`, `remove()`, `contains()` | O(log n)        |
| `iteration` (sorted)              | O(n)            |


**🧑‍💻 TreeSet Code**
```java
import java.util.*;

public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet<String> ts = new TreeSet<>();

        ts.add("Banana");
        ts.add("Apple");
        ts.add("Mango");
        // ts.add(null); // Throws NullPointerException

        System.out.println("TreeSet (sorted order):");
        for (String fruit : ts) {
            System.out.println(fruit);
        }

        // Custom comparator (reverse order)
        TreeSet<Integer> tsDesc = new TreeSet<>(Comparator.reverseOrder());
        tsDesc.add(10);
        tsDesc.add(5);
        tsDesc.add(20);

        System.out.println("Descending TreeSet:");
        System.out.println(tsDesc); // [20, 10, 5]
    }
}
```

### 📊 Summary Table: HashSet vs LinkedHashSet vs TreeSet
| Feature           | HashSet    | LinkedHashSet    | TreeSet        |
| ----------------- | ---------- | ---------------- | -------------- |
| Order Maintained  | ❌ No       | ✅ Insertion      | ✅ Sorted       |
| Allows null       | ✅ One null | ✅ One null       | ❌ No           |
| Duplicate Allowed | ❌ No       | ❌ No             | ❌ No           |
| Performance       | O(1)       | O(1)             | O(log n)       |
| Thread-safe       | ❌ No       | ❌ No             | ❌ No           |
| Backed By         | Hash table | Hash table + DLL | Red-Black Tree |


---
# 📚 Map Interface
- Map is a part of the Java Collections Framework in the java.util package.
- It stores key-value pairs, where each key is unique.
- Not a subtype of Collection.
- Keys are used to retrieve, update, or remove values efficiently


**❓Why & Where to Use Map**
- ✅ Why Map?
  - Fast lookup and association between key-value pairs.
  - Useful when data must be uniquely identified by a key.

- 📍 Where Map is Used:
  - Storing user profiles by user ID
  - Caching results (e.g., memoization)
  - Mapping configuration properties
  - Counting frequency of elements (word count, etc.)


## **🏗️ Implementation Classes & Interfaces**

**🔹 Main Implementations of Map:**
| Class           | Order Maintained  | Thread-Safe | Sorted | Allows null                    |
| --------------- | ----------------- | ----------- | ------ | ------------------------------ |
| `HashMap`       | ❌ Unordered       | ❌ No        | ❌ No   | ✅ 1 null key, many null values |
| `LinkedHashMap` | ✅ Insertion order | ❌ No        | ❌ No   | ✅                              |
| `TreeMap`       | ✅ Sorted by keys  | ❌ No        | ✅ Yes  | ❌ No null keys                 |
| `Hashtable`     | ❌ Unordered       | ✅ Yes       | ❌ No   | ❌ No nulls                     |


**🔸 Child Interfaces:**
- SortedMap – keys sorted in natural or comparator-defined order (e.g., TreeMap)
- NavigableMap – extends SortedMap with navigation methods like lowerKey(), ceilingKey()

### HashMap

**🔧 Constructors**
- ✅ Constructors (Example with HashMap):
```java
Map<Integer, String> map1 = new HashMap<>();
Map<Integer, String> map2 = new HashMap<>(20); // initial capacity
Map<Integer, String> map3 = new HashMap<>(existingMap);
```

**📌 Common Methods:**
| Method                      | Description                      |
| --------------------------- | -------------------------------- |
| `put(K key, V value)`       | Adds/updates key-value pair      |
| `get(Object key)`           | Returns value for key            |
| `remove(Object key)`        | Removes entry by key             |
| `containsKey(Object key)`   | Checks if key exists             |
| `containsValue(Object val)` | Checks if value exists           |
| `keySet()`                  | Returns Set of all keys          |
| `values()`                  | Returns Collection of all values |
| `entrySet()`                | Returns Set of key-value pairs   |
| `clear()`                   | Removes all entries              |
| `isEmpty()` / `size()`      | Checks emptiness or size         |


**🚀 Performance (Time & Space Complexity)**
| Operation       | HashMap  | LinkedHashMap | TreeMap  |
| --------------- | -------- | ------------- | -------- |
| `put()`         | O(1) avg | O(1) avg      | O(log n) |
| `get()`         | O(1) avg | O(1) avg      | O(log n) |
| `remove()`      | O(1) avg | O(1) avg      | O(log n) |
| `containsKey()` | O(1)     | O(1)          | O(log n) |
| Memory (space)  | O(n)     | O(n)          | O(n)     |


> HashMap uses hashing, TreeMap uses Red-Black tree, and LinkedHashMap maintains a doubly linked list over HashMap.

**🧑‍💻 Code Example**
```java
import java.util.*;

public class MapExample {
    public static void main(String[] args) {
        // HashMap example
        Map<Integer, String> map = new HashMap<>();

        // put() - adding entries
        map.put(101, "Alice");
        map.put(102, "Bob");
        map.put(103, "Charlie");
        map.put(102, "David"); // Overwrites key 102

        // get()
        System.out.println("Get 101: " + map.get(101)); // Alice

        // containsKey & containsValue
        System.out.println("Contains key 103? " + map.containsKey(103)); // true
        System.out.println("Contains value 'David'? " + map.containsValue("David")); // true

        // keySet(), values(), entrySet()
        System.out.println("Keys: " + map.keySet());       // [101, 102, 103]
        System.out.println("Values: " + map.values());     // [Alice, David, Charlie]
        System.out.println("Entries: " + map.entrySet());  // [101=Alice, 102=David, 103=Charlie]

        // remove()
        map.remove(103);

        // Iterating over entrySet
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }

        // clear() and isEmpty()
        map.clear();
        System.out.println("Map empty? " + map.isEmpty()); // true
    }
}
```

**📝 Key Points**
- Map stores unique keys, each mapping to exactly one value.
- Null keys/values: Allowed in HashMap & LinkedHashMap, but not in TreeMap or Hashtable.
- TreeMap is used for sorted data access.
- Use LinkedHashMap for ordered iteration, and HashMap for fastest performance.
- Not thread-safe by default. Use Collections.synchronizedMap() or ConcurrentHashMap for concurrency.

---
## 🔗 LinkedHashMap
- A subclass of HashMap that maintains the insertion order of entries.
- Backed by a hash table + doubly-linked list.
- Implements Map, HashMap, and LinkedHashMap


**🔧 Constructors**
```java
LinkedHashMap<K, V> map = new LinkedHashMap<>();
LinkedHashMap<K, V> map = new LinkedHashMap<>(initialCapacity);
LinkedHashMap<K, V> map = new LinkedHashMap<>(initialCapacity, loadFactor);
LinkedHashMap<K, V> map = new LinkedHashMap<>(initialCapacity, loadFactor, accessOrder);

// 🔁 The accessOrder flag (if true) enables LRU-like access order (used in caching).
```

**🛠️ Key Features**
| Feature          | Description                              |
| ---------------- | ---------------------------------------- |
| Order            | ✅ Maintains insertion or access order    |
| Null keys/values | ✅ One null key, multiple null values     |
| Thread-safe      | ❌ Not thread-safe                        |
| Performance      | Similar to `HashMap` (O(1) for most ops) |


**🧑‍💻 Code Example**
```java
LinkedHashMap<Integer, String> lhm = new LinkedHashMap<>();
lhm.put(1, "One");
lhm.put(2, "Two");
lhm.put(3, "Three");

System.out.println("Insertion Order:");
for (Map.Entry<Integer, String> entry : lhm.entrySet()) {
    System.out.println(entry.getKey() + " = " + entry.getValue());
}
```

**Use Cases**
- Preserving the order in which elements were inserted.
- Building caches using access order (accessOrder = true).


---
## 🌲 TreeMap
- Implements NavigableMap, SortedMap, and Map.
- Stores key-value pairs in sorted order of keys.
- Backed by a Red-Black Tree (self-balancing BST).

**🔧 Constructors**
```java
TreeMap<K, V> map = new TreeMap<>();
TreeMap<K, V> map = new TreeMap<>(Comparator);
TreeMap<K, V> map = new TreeMap<>(SortedMap);
```

**🛠️ Key Features**
| Feature          | Description                              |
| ---------------- | ---------------------------------------- |
| Order            | ✅ Sorted by natural or custom comparator |
| Null keys/values | ❌ No null keys, ✅ null values allowed    |
| Thread-safe      | ❌ Not thread-safe                        |
| Performance      | O(log n) for most operations             |


**Useful Methods**
- firstKey(), lastKey(), higherKey(), lowerKey()
- subMap(fromKey, toKey), tailMap(key), headMap(key)

**🧑‍💻 Code Example**
```java
TreeMap<Integer, String> tm = new TreeMap<>();
tm.put(3, "Three");
tm.put(1, "One");
tm.put(2, "Two");

System.out.println("Sorted by key:");
for (Map.Entry<Integer, String> entry : tm.entrySet()) {
    System.out.println(entry.getKey() + " = " + entry.getValue());
}

// Descending order using comparator
TreeMap<Integer, String> descTm = new TreeMap<>(Comparator.reverseOrder());
descTm.putAll(tm);
System.out.println("Descending order: " + descTm);
```

**Use Cases**
- When you need sorted key-value mappings.
- When you need range views or nearest-key access.


---
## 🔒 Hashtable

- Legacy class, existed before Java Collections Framework.
- Thread-safe version of HashMap using synchronized methods.
- Does not allow null keys or values.

**🔧 Constructors**
```java
Hashtable<K, V> ht = new Hashtable<>();
Hashtable<K, V> ht = new Hashtable<>(Map);
Hashtable<K, V> ht = new Hashtable<>(initialCapacity, loadFactor);
```

**🛠️ Key Features**
| Feature          | Description                          |
| ---------------- | ------------------------------------ |
| Order            | ❌ No ordering guaranteed             |
| Null keys/values | ❌ No null keys/values                |
| Thread-safe      | ✅ Yes (all methods synchronized)     |
| Performance      | Slower than `HashMap` due to locking |


**🧑‍💻 Code Example**
```java
Hashtable<Integer, String> ht = new Hashtable<>();
ht.put(1, "One");
ht.put(2, "Two");
// ht.put(null, "Null"); // Throws NullPointerException

System.out.println("Hashtable Entries:");
for (Map.Entry<Integer, String> entry : ht.entrySet()) {
    System.out.println(entry.getKey() + " = " + entry.getValue());
}
```

**Use Cases**
- Rarely used in modern applications (replaced by ConcurrentHashMap).
- When thread-safety is required and legacy compatibility is needed.

### 📊 Summary Table
| Feature               | `HashMap`                | `LinkedHashMap`         | `TreeMap`                  | `Hashtable`              |
| --------------------- | ------------------------ | ----------------------- | -------------------------- | ------------------------ |
| Maintains Order       | ❌ No order               | ✅ Insertion or Access   | ✅ Sorted (natural/custom)  | ❌ No order               |
| Null Key              | ✅ 1 allowed              | ✅ 1 allowed             | ❌ Not allowed              | ❌ Not allowed            |
| Null Values           | ✅ Allowed                | ✅ Allowed               | ✅ Allowed                  | ❌ Not allowed            |
| Thread-Safe           | ❌ No                     | ❌ No                    | ❌ No                       | ✅ Yes (synchronized)     |
| Performance (put/get) | ✅ O(1) avg               | ✅ O(1) avg              | 🔄 O(log n)                | ✅ O(1) (slower w/ locks) |
| Use Case              | General-purpose, fastest | LRU cache, ordered maps | Sorted maps, range queries | Legacy thread-safe maps  |



---

# 🔄 Iterators in Java
- Iterator is a part of the Java Collections Framework, in java.util package.
- Used to traverse (iterate) elements of a collection sequentially.
- Replaces older methods like Enumeration and supports safe removal of elements during iteration.

**❓ 2. Why & Where to Use Iterator**
- ✅ Why?
  - Collection traversal without exposing internal structure.
  - Supports safe removal of elements (unlike enhanced for-loop).
  - Works with different types of collections (List, Set, Queue, etc.).

- 📍 Where?
- Reading or modifying data in collections.
- Filtering or removing elements during traversal.
- In loops when index access isn’t needed.

**🏗️ Types of Iterators**
| Iterator Type  | Used With               | Features                                  |
| -------------- | ----------------------- | ----------------------------------------- |
| `Iterator`     | All collections         | Forward-only traversal, remove supported  |
| `ListIterator` | Lists only              | Bi-directional, add/set/remove            |
| `Enumeration`  | Legacy classes (Vector) | Forward-only, read-only                   |
| `Spliterator`  | Java 8+ Streams         | Parallel iteration (splitting capability) |


**Key Methods**
- 👉 Iterator<E> Interface
```java
Iterator<E> iterator = collection.iterator();
```
| Method      | Description                               |
| ----------- | ----------------------------------------- |
| `hasNext()` | Returns `true` if next element exists     |
| `next()`    | Returns next element                      |
| `remove()`  | Removes the current element (optional op) |


- 👉 ListIterator<E> (List only)
```java
ListIterator<E> listIterator = list.listIterator();
```
| Method                         | Description                    |
| ------------------------------ | ------------------------------ |
| `hasNext()` / `next()`         | Forward traversal              |
| `hasPrevious()` / `previous()` | Backward traversal             |
| `add(E e)`                     | Adds element                   |
| `remove()`                     | Removes last returned element  |
| `set(E e)`                     | Replaces last returned element |


**🧑‍💻 Code Examples**

- Basic Iterator

```java
List<String> list = Arrays.asList("A", "B", "C");
Iterator<String> it = list.iterator();

while (it.hasNext()) {
    String value = it.next();
    System.out.println(value);
}
```

-  Remove using Iterator
```java
List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
Iterator<Integer> it = nums.iterator();

while (it.hasNext()) {
    if (it.next() % 2 == 0) {
        it.remove();  // Safe removal
    }
}
System.out.println(nums); // [1, 3]
```

- ListIterator Example
```java
List<String> names = new ArrayList<>(Arrays.asList("Tom", "Jerry"));
ListIterator<String> lit = names.listIterator();

while (lit.hasNext()) {
    String name = lit.next();
    if (name.equals("Tom")) {
        lit.set("Spike");
    }
}
System.out.println(names); // [Spike, Jerry]
```

### **⚠️ Iterator vs. Enhanced For Loop**
| Feature           | Iterator        | Enhanced For Loop       |
| ----------------- | --------------- | ----------------------- |
| Modify Collection | ✅ Yes (remove)  | ❌ No (throws exception) |
| Direction         | Forward         | Forward                 |
| Flexibility       | ✅ More control  | Simpler syntax          |
| Applicable to     | All collections | Iterable only           |


**⏱️ Performance**
- Iterator itself is lightweight; performance depends on the collection.
- Safer than indexing (list.get(i)) when removing or modifying during traversal.

**📝 Key Points**
- Always use iterator.remove() instead of collection.remove() in a loop.
- ListIterator is more powerful than Iterator but limited to Lists.
- Enumeration is outdated; use Iterator instead.
- Use Spliterator for parallelism in streams (Java 8+).


---

# Java Collections Framework – Popular Interview Questions

## ✅ Core Concept Questions

- What is the Java Collections Framework?
- What is the difference between `Collection` and `Collections` in Java?
- What are the main interfaces in the Java Collections Framework?
- What is the root interface of the Collection hierarchy?
- What is the difference between `List`, `Set`, and `Map`?
- Why is `Map` not a part of the `Collection` interface?

---

## 📚 List Interface

- What is the difference between `ArrayList` and `LinkedList`?
- How does `ArrayList` work internally?
- Which is better for insertion/removal – `ArrayList` or `LinkedList`?
- What are the time complexities of operations in `ArrayList` and `LinkedList`?
- Difference `Arrays.asList()` vs `List.of()` ?
- What is `Vector`? How is it different from `ArrayList`?
- When would you use a `List` over a `Set`?
---

## 🔁 Set Interface

- What is the difference between `HashSet`, `LinkedHashSet`, and `TreeSet`?
- How does `HashSet` ensure uniqueness?
- Can a `Set` contain null elements?
- How does `TreeSet` maintain order?

---

## 📌 Map Interface

- What is the difference between `HashMap`, `LinkedHashMap`, and `TreeMap`?
- Can a `HashMap` have null keys and values?
- What happens when two keys have the same `hashCode()` in a `HashMap`?
- How does `HashMap` handle collisions?
- What is the difference between `HashMap` and `Hashtable`?
- What is the difference between `ConcurrentHashMap` and `Hashtable`?

---

## 🧭 Queue & Stack

- What is the difference between `Queue` and `Deque`?
- How is `PriorityQueue` implemented?
- What is the difference between `Stack` and `Deque`?
- Why is the `Stack` class considered legacy?

---

## 🧵 Synchronization & Thread-Safety

- Which collection classes are synchronized?
- What is the difference between synchronized collections and concurrent collections?
- What is `CopyOnWriteArrayList`?
- How does `ConcurrentHashMap` achieve thread safety?

---

## 🔄 Iterator & Traversal

- What is the difference between `Iterator` and `ListIterator`?
- What is the fail-fast behavior of iterators?
- How does `iterator.remove()` work?
- What is the difference between `Enumeration` and `Iterator`?

---

## 💡 Practical / Scenario-Based Questions

- Which collection would you use for LRU cache implementation?
- If you need to maintain insertion order, which collection would you use?
- How would you sort a `Map` by keys or values?
- Which collection to use when duplicates are not allowed?
- Which data structure to use for constant-time lookup?
- How to safely iterate over a collection while modifying it?

---

## 🧪 Advanced/Source Code-Level Questions (for experienced roles)

- How does `HashMap` work internally (Java 8+)?
- What is the load factor in `HashMap` and why is it 0.75 by default?
- What are the improvements in `HashMap` from Java 7 to Java 8?
- How does `TreeMap` maintain sorting using Red-Black Tree?
- Why is it important to override `equals()` and `hashCode()` when using objects in a `HashSet`/`HashMap`?

---
