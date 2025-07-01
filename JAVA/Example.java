import java.io.File;
import java.io.IOException;



public class Example  {

    public static void main(String[] args) { 
        Example obj = new Example();
        System.out.println("Hashcode: " + obj.hashCode());
        obj = null;
        System.gc();
        System.out.println("End of garbage collection");
    }

    @Override
    protected void finalize() {
        System.out.println("Finalize method called");
    }
}

