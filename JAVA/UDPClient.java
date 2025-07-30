import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");
        int serverPort = 8080;

        // Send message to server
        String message = "Hello from UDP Client!";
        byte[] sendData = message.getBytes();

        DatagramPacket request = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
        socket.send(request);
        System.out.println("Message sent to UDP server.");

        // Receive response
        byte[] buffer = new byte[1024];
        DatagramPacket response = new DatagramPacket(buffer, buffer.length);
        socket.receive(response);

        String reply = new String(response.getData(), 0, response.getLength());
        System.out.println("Server says: " + reply);

        socket.close();
    }
}
