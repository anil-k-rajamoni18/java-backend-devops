import java.util.*;

public class Example  {
  public static void main(String[] args) {

  // Creating a HashSet
        HashSet<String> fruits = new HashSet<>();

        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add(null);
        fruits.add(null);
        fruits.add("Apple"); // Duplicate, won't be added

        System.out.println(fruits);

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

