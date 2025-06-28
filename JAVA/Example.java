abstract class Animal {
    protected String name; // Protected variable
    abstract void makeSound();      // Abstract method
    abstract void displayInfo(); // Another abstract method
    void eat() {                      // Concrete method
        System.out.println("This animal eats food.");
    }
}

class Dog extends Animal {
    public Dog() { // Constructor
        System.out.println("Dog class constructor called.");
    }

    @Override
    void makeSound() {                // Implementing abstract method
        System.out.println("Woof! Woof!");
    }

    @Override
    void displayInfo() {              // Implementing another abstract method
        System.out.println("This is a dog. Name: " + name);   
    }
}

public class Example  {
    public static void main(String[] args)  { 
        Animal myDog = new Dog(); // Creating an instance of Dog
        myDog.makeSound();                  // Calling the abstract method
        myDog.eat();                        // Calling the concrete method
        myDog.displayInfo();                // Calling the other abstract method
        
    }
}

