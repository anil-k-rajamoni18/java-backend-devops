## 🌐 Basic Networking in Java

**🧠 What is Networking in Java?**
- Java Networking is the concept of connecting two or more computing devices together to share resources using Java APIs.
- Java provides the java.net package to support network communication via TCP, UDP, and HTTP.

**✅ What is HTTP?**
- HyperText Transfer Protocol
- A stateless, application-level protocol used for data communication on the web.
- Operates over TCP/IP.
- Client (usually browser) sends a request → Server sends a response.

**📬 Common HTTP Request Methods**
| Method   | Purpose                  | Use Case                          |
| -------- | ------------------------ | --------------------------------- |
| `GET`    | Request data from server | Retrieve a webpage, fetch records |
| `POST`   | Submit data to server    | Login forms, registration forms   |
| `PUT`    | Update existing data     | Update a user profile             |
| `DELETE` | Delete a resource        | Remove a database record          |


**🚦 Difference between GET and POST:**
| Feature    | GET                   | POST                     |
| ---------- | --------------------- | ------------------------ |
| Data Sent  | In URL (query string) | In HTTP body             |
| Visibility | Visible in URL        | Hidden                   |
| Size Limit | Limited               | Larger payload supported |
| Use Case   | Fetch data            | Submit sensitive data    |
| Idempotent | Yes                   | No                       |


**🧾 HTTP Status Codes (Common Ones)**
| Code | Meaning               | Description                          |
| ---- | --------------------- | ------------------------------------ |
| 200  | OK                    | Request succeeded                    |
| 301  | Moved Permanently     | Resource has moved to a new URL      |
| 400  | Bad Request           | Malformed request                    |
| 401  | Unauthorized          | Authentication needed                |
| 403  | Forbidden             | Access denied                        |
| 404  | Not Found             | Resource not available               |
| 500  | Internal Server Error | Server failed to process the request |
| 503  | Service Unavailable   | Server temporarily overloaded/down   |


### Client-Server Communication

**🧭 Architecture:**
- Client: Requests resources (e.g., browser, Postman)
- Server: Listens for requests and sends responses
-  ➡️ The communication usually follows the Request-Response model.

- Example:
    - Client sends an HTTP GET request to https://example.com/index.html
    - Server locates the file and responds with HTTP 200 OK + file content
    - Client renders or processes the response


**📦 java.net Package – Basics**
- The java.net package provides classes for:
    - IP addressing
    - TCP/UDP sockets
    - HTTP connections

**Common Classes:**
| Class               | Purpose                                    |
| ------------------- | ------------------------------------------ |
| `InetAddress`       | Represents an IP address                   |
| `URL`               | Represents a Uniform Resource Locator      |
| `URLConnection`     | Communicates with a URL                    |
| `HttpURLConnection` | HTTP-specific version of URLConnection     |
| `Socket`            | Client-side TCP connection                 |
| `ServerSocket`      | Server-side TCP listener                   |
| `DatagramSocket`    | UDP socket for sending/receiving datagrams |


**Simple Example: Making an HTTP Request using HttpURLConnection**
```java
import java.io.*;
import java.net.*;

public class SimpleHttpGet {
    public static void main(String[] args) throws Exception {
        URL url = new URL("https://example.com");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        int status = con.getResponseCode();
        System.out.println("Response Code: " + status);

        BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        System.out.println("Response Body: " + content);
    }
}
```

### 📦 TCP in Action
- Establishes a three-way handshake:
    - `SYN → SYN-ACK → ACK`
- Guarantees that data reaches destination in correct order.
- Resends lost or corrupted packets.
- 🔍 Used by:
    - HTTP/HTTPS (web browsing)
    - FTP (file transfer)
    - SMTP/IMAP/POP3 (email)

### 📦 UDP in Action
- Sends data directly without a handshake.
- No delivery guarantees – faster, but riskier.
- Ideal where speed is critical and occasional loss is acceptable.
- 🔍 Used by:
    - Video/audio streaming (YouTube, Zoom)
    - Online gaming
    - DNS lookups
    - Live broadcasts


**🎯 Quick Analogy**
- TCP = Sending a certified letter (requires confirmation of receipt)
- UDP = Dropping a flyer in every mailbox (fast, but no guarantee anyone got it)

**🔁 TCP vs UDP**
| Feature             | **TCP (Transmission Control Protocol)**            | **UDP (User Datagram Protocol)**               |
| ------------------- | -------------------------------------------------- | ---------------------------------------------- |
| **Connection Type** | Connection-oriented (establishes connection first) | Connectionless (no setup needed)               |
| **Reliability**     | Reliable (ensures delivery, order, no duplicates)  | Unreliable (no guarantee of delivery or order) |
| **Speed**           | Slower (more overhead due to error checking)       | Faster (minimal overhead)                      |
| **Data Ordering**   | Maintains order of data packets                    | No ordering of packets                         |
| **Error Checking**  | Yes (with acknowledgment, retransmission)          | Basic error checking (no retransmission)       |
| **Header Size**     | Larger (\~20 bytes)                                | Smaller (\~8 bytes)                            |
| **Use Cases**       | Web apps, email, file transfer (HTTP, FTP, SMTP)   | Video streaming, gaming, DNS, VoIP             |


**TCP Client (Java)**
- Connects to the TCP server on a specific port.
- Sends a message.
- Receives and prints the server’s response.

```java
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
```

**🔌 TCP Server (Java)**
- Waits for a connection from a client.
- Reads a message sent by the client.
- Sends a response back.
```java
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
```

----
**📡 ✅ UDP Client (Java)**
- Sends a datagram to the UDP server.
- Receives the reply.
- Prints the server’s response.
```java
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
```

**📡 UDP Server (Java)**
- Listens for incoming datagrams (packets).
- Receives a message from the client.
- Sends a reply datagram.
```java
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
```

---

## 🌐 HTML & Web Fundamentals
**🔤 What is HTML?**
- HTML (HyperText Markup Language) is the standard language for creating web pages.
- It structures content (text, images, forms, links, etc.).
- Web browsers read and render HTML content.

### 🧱 Common HTML Elements

**🔤 Text Content Elements**
| Element          | Description                  | Example                              |
| ---------------- | ---------------------------- | ------------------------------------ |
| `<h1>` to `<h6>` | Headings (h1 is largest)     | `<h1>Welcome</h1>`                   |
| `<p>`            | Paragraph                    | `<p>This is a paragraph.</p>`        |
| `<span>`         | Inline container for styling | `<span style="color:red">Red</span>` |
| `<strong>`       | Bold text                    | `<strong>Important</strong>`         |
| `<em>`           | Italic text                  | `<em>Emphasized</em>`                |
| `<br>`           | Line break                   | `Line 1<br>Line 2`                   |

**📋 Lists**
| Element | Description             | Example                   |
| ------- | ----------------------- | ------------------------- |
| `<ul>`  | Unordered list          | `<ul><li>Item</li></ul>`  |
| `<ol>`  | Ordered (numbered) list | `<ol><li>First</li></ol>` |
| `<li>`  | List item               | `<li>List Item</li>`      |

**🔗 Links and Navigation**
| Element | Description                             | Example                                   |
| ------- | --------------------------------------- | ----------------------------------------- |
| `<a>`   | Anchor (hyperlink)                      | `<a href="https://example.com">Visit</a>` |
| `<nav>` | Semantic container for navigation links | `<nav><a href="#">Home</a></nav>`         |


**🖼️ Media**
| Element   | Description       | Example                                            |
| --------- | ----------------- | -------------------------------------------------- |
| `<img>`   | Displays an image | `<img src="logo.png" alt="Logo">`                  |
| `<video>` | Embeds a video    | `<video controls><source src="video.mp4"></video>` |
| `<audio>` | Embeds audio      | `<audio controls src="song.mp3"></audio>`          |


**📦 Containers & Layout**
| Element     | Description                     | Example                             |
| ----------- | ------------------------------- | ----------------------------------- |
| `<div>`     | Block-level container (generic) | `<div class="box">Content</div>`    |
| `<span>`    | Inline container                | `<span>Inline</span>`               |
| `<section>` | Section of a document           | `<section><h2>Title</h2></section>` |
| `<article>` | Self-contained content          | `<article>Blog post</article>`      |
| `<header>`  | Top part of a section/page      | `<header>Logo + Nav</header>`       |
| `<footer>`  | Bottom part of a section/page   | `<footer>Copyright</footer>`        |
| `<main>`    | Main content area               | `<main>Central content</main>`      |

**📝 Forms & Input**
| Element      | Description                     | Example                                 |
| ------------ | ------------------------------- | --------------------------------------- |
| `<form>`     | Form container                  | `<form action="/submit"></form>`        |
| `<input>`    | Input field (text, email, etc.) | `<input type="text" name="name">`       |
| `<textarea>` | Multi-line input                | `<textarea></textarea>`                 |
| `<button>`   | Clickable button                | `<button>Click Me</button>`             |
| `<select>`   | Dropdown menu                   | `<select><option>One</option></select>` |
| `<label>`    | Label for input                 | `<label for="name">Name</label>`        |


**🧪 Scripting and Metadata**
| Element    | Description                    | Example                                    |
| ---------- | ------------------------------ | ------------------------------------------ |
| `<script>` | Embeds JavaScript              | `<script>console.log("Hi")</script>`       |
| `<link>`   | Links to external CSS          | `<link rel="stylesheet" href="style.css">` |
| `<style>`  | Internal CSS                   | `<style>p { color: red; }</style>`         |
| `<meta>`   | Metadata (charset, viewport)   | `<meta charset="UTF-8">`                   |
| `<title>`  | Webpage title (in browser tab) | `<title>My Page</title>`                   |


**`<form>`**
- Used to collect user input and submit it to a server.
```html 
<form action="/submit" method="POST">
  <input type="text" name="username">
  <button type="submit">Submit</button>
</form>
```
- action: URL to send data
- method: HTTP method (usually GET or POST)

**`<input>`**
- Collects different types of user input.
```html
<input type="text" name="username">
<input type="password" name="pwd">
<input type="email" name="email">
<input type="checkbox" name="agree">
<input type="submit" value="Register">
```
**`<button>`**
- Triggers form submission or actions via JavaScript.
```html
<button type="submit">Submit</button>
```

**`<a>` (Anchor)**
- Creates hyperlinks to navigate between pages.
```html
<a href="https://example.com">Visit Example</a>
```

**📝 How Forms Work**
- User enters data into fields.
- Form is submitted to a server (via GET or POST).
- Server processes the data (e.g., with Servlets).
- Server sends back a response (HTML or redirect).

**🧾 GET vs POST in Forms**
| Feature       | `GET`                          | `POST`                |
| ------------- | ------------------------------ | --------------------- |
| Data Location | In URL as query string         | In body of request    |
| Visibility    | Visible in browser address bar | Hidden                |
| Use Case      | Search, links, bookmarks       | Login, sensitive data |


**URL Structure and Query Parameters**
```js
https://example.com/search?q=java&page=2
```
| Part             | Description              |
| ---------------- | ------------------------ |
| `https://`       | Protocol                 |
| `example.com`    | Domain name              |
| `/search`        | Path (resource location) |
| `?q=java&page=2` | Query parameters         |


**🎨 What is CSS?**
- CSS (Cascading Style Sheets) controls the presentation and layout of HTML elements.

**Examples**
```html
<style>
  h1 {
    color: blue;
    font-size: 24px;
  }
  .button {
    background-color: green;
    color: white;
    padding: 10px;
  }
</style>
```

- Inline: `<p style="color:red;">Hello</p>`
- Internal: `<style> ... </style> in <head>`
- External: `<link rel="stylesheet" href="style.css">`

**🖥️ Web Server**
- A software or hardware that:
    - Hosts websites/web apps
    - Processes requests (e.g., from browsers)
    - Sends responses (HTML, JSON, files)
- Examples: Apache, Nginx, Tomcat (Java Servlet container)


**🌐 Web Browser**
- A client-side software that:
    - Sends HTTP requests to servers
    - Renders HTML/CSS/JavaScript
    - Provides a user-friendly interface
- Examples: Chrome, Firefox, Safari, Edge

**🖥️ Web Server vs Application Server**

| Feature                  | **Web Server**                                                             | **Application Server**                                            |
| ------------------------ | -------------------------------------------------------------------------- | ----------------------------------------------------------------- |
| **Purpose**              | Handles **HTTP requests/responses**, serves static content (HTML, CSS, JS) | Handles **business logic**, dynamic content (Servlets, JSP, APIs) |
| **Content Served**       | Static content                                                             | Dynamic + static content                                          |
| **Languages Supported**  | Primarily HTML/CSS/JavaScript                                              | Supports multiple: Java, Python, .NET, etc.                       |
| **Request Handling**     | HTTP only (GET, POST, etc.)                                                | HTTP + additional protocols (RMI, JMS, etc.)                      |
| **Example Technologies** | Apache HTTP Server, Nginx                                                  | Apache Tomcat, JBoss (WildFly), GlassFish                         |
| **Servlet Support**      | ❌ Not by default                                                           | ✅ Fully supports Java Servlets and JSP                            |
| **Performance**          | Faster for static content                                                  | Slightly slower (handles more complex logic)                      |
| **Use Case**             | Static websites, load balancers, reverse proxies                           | Full-stack enterprise apps, microservices                         |


**🧱 Tomcat**
- Apache Tomcat is technically an application server, but is often referred to as a web container (specialized for Servlets and JSP).
- It includes both web server features and support for running Java web apps.


**🎯 Analogy**
- A `web server` is like a waiter delivering food (static files).
- An `application server` is like the chef who cooks the meal (processes business logic), then hands it to the waiter.

