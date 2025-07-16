import java.util.*;
import java.util.function.Function;

class Student {
    int id;
    String name;

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return id + " - " + name;
    }
}



public class Example  {
  public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(2, "Alice"));
        students.add(new Student(1, "John"));
        students.add(new Student(3, "Bob"));

        
        Collections.sort(students, (s1, s2) -> s1.name.compareTo(s2.name));  
        System.out.println("Sorted by ID: " + students);

        List<String> names = Arrays.asList("John", "Alice", "Bob");
        names.forEach(name -> System.out.println("Name: " + name));

      
    }           
}

