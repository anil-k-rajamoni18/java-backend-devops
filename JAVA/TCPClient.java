import java.io.*;
import java.net.*;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        System.out.println("Connected to TCP server!");

        // Send message to server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Hello from TCP Client!");

        // Read response from server
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String response = in.readLine();
        System.out.println("Server says: " + response);

        // Close resources
        in.close();
        out.close();
        socket.close();
    }
}