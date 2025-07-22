import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClientJBDCApp {
    public static void main(String[] args) {
        try {
            // 1. Load the Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish Connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "root123");

            DatabaseMetaData meta = conn.getMetaData();
            System.out.println(meta.getDatabaseProductName());
            System.out.println(meta.getDatabaseProductVersion());


            
            conn.close();

        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
}
