import java.util.*;

public class Example  {
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

