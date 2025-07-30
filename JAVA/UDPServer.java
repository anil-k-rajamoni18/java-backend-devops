import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(8080);
        System.out.println("UDP Server is listening on port 8080...");

        byte[] buffer = new byte[1024];

        // Receive packet from client
        DatagramPacket request = new DatagramPacket(buffer, buffer.length);
        socket.receive(request);
        String message = new String(request.getData(), 0, request.getLength());
        System.out.println("Client says: " + message);

        // Send response to client
        String reply = "Hello from UDP Server!";
        byte[] replyData = reply.getBytes();

        DatagramPacket response = new DatagramPacket(
            replyData,
            replyData.length,
            request.getAddress(),
            request.getPort()
        );

        socket.send(response);
        socket.close();
    }
}