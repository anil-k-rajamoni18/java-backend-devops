import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("TCP Server is running on port 8080...");

        Socket socket = serverSocket.accept(); // Wait for a client
        System.out.println("Client connected!");

        // Read data from client
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String clientMessage = in.readLine();
        System.out.println("Client says: " + clientMessage);

        // Send response to client
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("Hello from TCP Server!");

        // Close resources
        in.close();
        out.close();
        socket.close();
        serverSocket.close();
    }
}