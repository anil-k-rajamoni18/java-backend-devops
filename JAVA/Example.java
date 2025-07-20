import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

public class Example  {
    public static void main(String[] args) {        
        ZonedDateTime zoned = ZonedDateTime.now(ZoneId.of("America/Chicago"));
        System.out.println(zoned);

        Set<String> zones = ZoneId.getAvailableZoneIds(); // All timezones
        System.out.println(zones);

    }
    
   public static List<Employee> getEmployees() {
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
        return employees;
   }
}

