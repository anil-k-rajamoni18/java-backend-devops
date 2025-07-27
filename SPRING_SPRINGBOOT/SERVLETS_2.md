# 🌐 Java Servlets

### 🌐 What is Servlet Technology?
- 🛠️ Servlet technology helps create web apps that run on the server side 🖥️ and generate dynamic web pages 📄.
- It's powerful 💪 and flexible 🔄 because it's built using the Java programming language ☕.
- Before Servlets, people used CGI (Common Gateway Interface) scripts 🧾 for server-side programming, but Servlets are faster and better 🚀.
- The Servlet API includes many important tools 
    - Servlet 📦 – The main interface
    - GenericServlet 🔄 – A basic version for general use
    - HttpServlet 🌐 – Used for handling HTTP requests
    - ServletRequest 📥 – Represents incoming request data
    - ServletResponse 📤 – Represents outgoing response data

### 🧠 What is a Servlet?

> A Servlet is a Java program that runs on a Java-enabled web server (Servlet container) and handles HTTP requests and responses.
- Part of Java EE (Jakarta EE).
- Used to create dynamic web content.
- Operates on the server-side.

**Key Features:**
- Platform-independent.
- Uses Java for server-side logic.
- Handles HTTP methods like GET, POST, PUT, DELETE.

![](https://camo.githubusercontent.com/6c0350b5fcc4d0b1e817667e4de1b4756fda0ea24d7ce684f9e07d0db823a4da/68747470733a2f2f692e696d6775722e636f6d2f61566a6b326a522e706e67)


**📍 Where is a Servlet Used?**
- Servlets are typically used in:
    - Web Applications built in Java.
    - Enterprise Applications (e.g., e-commerce, CRMs).
    - As a controller in MVC frameworks like Spring.
    - RESTful APIs (basic implementations before using Spring Boot).

**🎯 Why Use Servlets?**
- To extend web server functionality using Java.
- Better performance than CGI (Common Gateway Interface).
- Reusability and scalability.
- Direct access to HTTP and session objects.
- Control over request/response flow.


**🧠 What is a Servlet Container?**
> A Servlet Container (also called a web container) is like a manager or helper 🧑‍💼 for Servlets. It:

-  Stores and runs Servlets inside a web server.
- Understands how to handle HTTP requests 🌐 and responses 📩.
- Controls the lifecycle of a Servlet (like starting, running, and stopping it).
- Handles security, multithreading 🧵, and memory management automatically.
- Connects the client (browser) to the server-side Java code using HTTP.

**🏠 Think of it like this:**
- If your Servlet is the chef 👨‍🍳, then the Servlet Container is the kitchen 🍳. 
- It gives the chef all the tools and ingredients to cook and serve delicious web pages 🍽️ to the customers (users).

**🛠️ Examples of Servlet Containers:**
- Apache Tomcat 🐱
- Jetty 🚀
- GlassFish 🐠

![](https://www.simplilearn.com/ice9/free_resources_article_thumb/Java-Servlets-java-servlet-architecture.png)

---

### 🔄 Servlet Lifecycle – Step by Step!
- The Servlet Lifecycle describes how a servlet is created, used, and destroyed by the servlet container (like Tomcat 🐱). 
- There are 3 main stages:

**1. 🛠️ Initialization (init() method)**
- Called only once when the servlet is first loaded.
- The servlet container creates the servlet object and calls init().
- It's like setting up your workspace before doing the job.

> 🗣️ "Hey Servlet, get ready!"

**2. ⚙️ Service (service() method)**
- Called every time a client sends a request (like clicking a link or submitting a form).
- The service() method handles the request (GET, POST, etc.) and sends back a response.

> 🗣️ "Hey Servlet, here’s a request — do your job!"

**3. 🧹 Destruction (destroy() method)**
- Called once when the servlet is being removed (like when the server is shutting down).
- It’s used to clean up resources (like closing files or database connections).

> 🗣️ "Hey Servlet, your job is done — time to go!"

| Stage      | Method      | Purpose                       |
| ---------- | ----------- | ----------------------------- |
| 🛠️ Init   | `init()`    | Setup servlet (runs once)     |
| ⚙️ Service | `service()` | Handle requests (runs often)  |
| 🧹 Destroy | `destroy()` | Cleanup before removal (once) |


---
### 📦 Servlet API
- The Servlet API is a collection of interfaces and classes in Java ☕ that you use to create and manage servlets on a web server 🌐.
- Think of it as a toolbox 🧰 that gives you everything you need to build server-side web apps with Java.


*8🔑 What does it provide?**
- 🛠️ Create servlets easily
- 📥 Receive requests from clients (like browsers)
- 📤 Send responses back to clients
- ⚙️ Control servlet behavior (init, service, destroy)
- 🔐 Manage sessions, cookies, security, and more

**🧩 Important Interfaces & Classes in Servlet API:**
| Type         | Name              | What it does                               |
| ------------ | ----------------- | ------------------------------------------ |
| 📄 Interface | `Servlet`         | The base interface for all servlets        |
| 🔄 Class     | `GenericServlet`  | A simple, general-purpose servlet          |
| 🌐 Class     | `HttpServlet`     | A servlet that handles HTTP (web) requests |
| 📥 Interface | `ServletRequest`  | Info about the incoming request            |
| 📤 Interface | `ServletResponse` | Info about the outgoing response           |
| 📂 Interface | `ServletConfig`   | Config info for a servlet                  |
| 📁 Interface | `ServletContext`  | Shared info for the whole web app          |

--- 
### 🌐 What is HttpServlet?
- HttpServlet is a Java class that makes it easy to handle HTTP requests and responses in a web application.
- It belongs to the Servlet API and is part of javax.servlet.http package 📦.

**🧠 Why use HttpServlet?**
- Because most web apps use HTTP (Hypertext Transfer Protocol) 🌐 to talk between the browser (client) and server — and HttpServlet is designed exactly for that!
- It gives us built-in methods to handle different HTTP request types:

**Common Methods:**
- doGet(HttpServletRequest req, HttpServletResponse res)
- doPost(HttpServletRequest req, HttpServletResponse res)
- doPut(), doDelete(), doHead() etc.

| Request Type | Method in `HttpServlet` | Use Case                                               |
| ------------ | ----------------------- | ------------------------------------------------------ |
| 📨 GET       | `doGet()`               | When the browser **asks** for data (e.g., view a page) |
| 📝 POST      | `doPost()`              | When the browser **sends** data (e.g., submit a form)  |

### 🗂️ Project Structure
```pgsql
MyServletApp/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── HelloServlet.java   👈 Your servlet class
│       └── webapp/
│           ├── index.html                  👈 Optional front page
│           └── WEB-INF/
│               └── web.xml                 👈 Deployment descriptor
├── pom.xml                                 👈 Maven dependencies & build
```

**Basic Structure of HttpServlet**
```java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello from doGet()! 👋</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name"); // Get form input
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello, " + name + "! 👋</h1>");
    }
}
```

**🧩 How it works:**
1. Browser goes to http://localhost:8080/app/hello
2. HttpServlet's doGet() method is called
3. Server responds with HTML → Browser shows it
4. If you submit a form with method="POST":
5. doPost() will run instead!



---
### **📄 What is web.xml (Deployment Descriptor)?**
- web.xml is a special XML file used in Java web applications
- It tells the web container (like Apache Tomcat 🐱) how to run your web app
- Located inside the WEB-INF folder of your project 📂

**🛠️ What does web.xml do?**
- 📛 Name your servlets
= 🔗 Map URLs to servlets (e.g. /login, /hello)
- ⚙️ Set init parameters
- ⏳ Configure session timeouts
- 🔐 Set up security, filters, welcome pages, etc.


**📍 Where is web.xml located?**
```plaintext
YourProject/
 └── WEB-INF/
     └── web.xml
```
- web.xml
```xml
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" version="3.1">
  
  <servlet>
    <servlet-name>HelloServlet</servlet-name>
    <servlet-class>HelloWorldServlet</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>HelloServlet</servlet-name>
    <url-pattern>/hello</url-pattern>
  </servlet-mapping>

</web-app>
```

**🚀 Do we always need it?**
- Old way (before Java EE 6): Required
- New way (Java EE 6 and later): Optional ✅ You can use annotations (@WebServlet) instead

---
### HelloWorld Servlet Example
```java
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Set response content type
        response.setContentType("text/html");

        // Write HTML response
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello, World! 🌍</h1>");
    }
}
```

**web.xml Configuration (Deployment Descriptor)**
- If you're not using annotations, register the servlet in web.xml:
```xml
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" version="3.1">
    <servlet>
        <servlet-name>HelloServlet</servlet-name>
        <servlet-class>HelloWorldServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>HelloServlet</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
</web-app>
```

- Now when you go to:
```bash
http://localhost:8080/YourProjectName/hello
```

**Using Annotation Instead (No web.xml needed)**
```java
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h1>Hello from Annotation! ✨</h1>");
    }
}
```

**🧪 How to Run This?**
- Use a Java web server like Apache Tomcat 🐱.
- Deploy your project (WAR file or exploded directory).
- Open your browser and go to http://localhost:8080/YourApp/hello.

---
## ServletConfig vs ServletContext
| Feature     | ServletConfig 🛠️                              | ServletContext 🏢                                |
| ----------- | ---------------------------------------------- | ------------------------------------------------ |
| Scope       | Single **servlet only**                        | Shared across **whole application**              |
| Purpose     | Pass **init parameters** to a specific servlet | Share **common data/config** across all servlets |
| Accessed by | `getServletConfig()`                           | `getServletContext()`                            |
| Lifetime    | Exists as long as the servlet exists           | Exists as long as the application is running     |

**📦 Example: ServletConfig**
- In web.xml:
```xml
<servlet>
  <servlet-name>MyServlet</servlet-name>
  <servlet-class>MyServlet</servlet-class>
  <init-param>
    <param-name>company</param-name>
    <param-value>OpenAI</param-value>
  </init-param>
</servlet>
```

- In your servlet:
```java
String company = getServletConfig().getInitParameter("company");
```

**🏢 Example: ServletContext**
- In any servlet:
```java
ServletContext context = getServletContext();
String path = context.getRealPath("/index.html");
```

----
## 📥📤 Request and Response Objects
- These are part of servlet methods like doGet() and doPost().

**HttpServletRequest – 📥 Request Object**
- Gets info from the client (browser):
    - Form data
    - URL parameters
    - Request headers
    - Cookies
- Methods: getParameter(), getHeader(), getCookies()
- Examples:
```java
String name = request.getParameter("name");
String userAgent = request.getHeader("User-Agent");
```

**HttpServletResponse – 📤 Response Object**
- Sends info back to the client:
    - HTML content
    - Redirects
    - Setting cookies
    - Status codes

- Methods: setContentType(), getWriter().print()
```java
response.setContentType("text/html");
PrintWriter out = response.getWriter();
out.println("<h1>Hello!</h1>");
```
--- 
###  Session Management
- Session = A way to store user data between multiple requests 🧠
- When a user visits your site, the server can assign them a unique session using an ID stored in a cookie.
- `HttpSession` object helps manage session data:
- 🔸 Create/Get session:
```java
HttpSession session = request.getSession();
```
- 🔸 Store data in session:
```java
session.setAttribute("username", "Alice");
```

- 🔸 Retrieve data:
```java
String user = (String) session.getAttribute("username");
```
- 🔸 End session:
```java
session.invalidate();
```

**🔐 Why Sessions Are Important?**
- Keeps users logged in
- Tracks items in a shopping cart 🛒
- Stores temporary user data 🔄

---
### 🧹 Servlet Filters
- A Filter is used to intercept requests and responses to do something before or after a servlet runs.
- 👉 Think of it like a security guard 👮, cleaner 🧼, or logger 📝.

**🔄 Filter Lifecycle**
 Filter Lifecycle
- init() – called once when filter is created
- doFilter() – called every time a request comes
- destroy() – called when filter is destroyed

**🛠️ What can Filters do?**
- 🔐 Authentication (check login)
- 📝 Logging requests
- 🧼 Data formatting (e.g., trimming whitespace)
- 🚫 Blocking requests

**Example**
```java
public class MyFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        System.out.println("Filter called before servlet");

        chain.doFilter(request, response); // Pass to servlet

        System.out.println("Filter called after servlet");
    }
}
```

- In web.xml:
```xml
<filter>
  <filter-name>MyFilter</filter-name>
  <filter-class>MyFilter</filter-class>
</filter>

<filter-mapping>
  <filter-name>MyFilter</filter-name>
  <url-pattern>/*</url-pattern>
</filter-mapping>
```

---
###  Request Dispatcher
- Used to forward a request to another servlet or include output from another resource (like JSP).

**🔸 Methods**
| Method      | Use                                                                   |
| ----------- | --------------------------------------------------------------------- |
| `forward()` | Transfers request to another resource **without returning to caller** |
| `include()` | Includes output of another resource **and returns to caller**         |


**🧪 Example**
```java
RequestDispatcher rd = request.getRequestDispatcher("servletB");
rd.forward(request, response);  // Or: rd.include(request, response);
```

**🚀 Use Cases**
- Moving between pages 🧭
- Loading headers/footers dynamically 🧩
- Conditionally forwarding requests


---
###  Exception Handling in Servlets
- Handling errors (like NullPointerException, IOException, etc.) in servlets using:
    - try-catch blocks in code
    - Or a custom error page using web.xml

**🔒 Option 1: try-catch in Servlet**
```java
try {
    // Some risky code
} catch (Exception e) {
    e.printStackTrace();
    response.getWriter().println("Something went wrong! ❌");
}
```\

**📄 Option 2: Error Handling in web.xml**
```xml
<error-page>
  <exception-type>java.lang.Exception</exception-type>
  <location>/error.jsp</location>
</error-page>

<error-page>
  <error-code>404</error-code>
  <location>/notfound.jsp</location>
</error-page>
```

**🎯 Use Cases**
- Displaying user-friendly error pages 🧑‍💻
- Logging errors for debugging 🧾
- Preventing app crashes from unhandled exceptions 💥


----
### Servlet Annotations (Modern Approach)
- @WebServlet(urlPatterns={"/hello"})
- @WebInitParam(name="user", value="admin")
- Avoids manual web.xml editing.


###  JSP vs Servlet
| Feature    | JSP              | Servlet                   |
| ---------- | ---------------- | ------------------------- |
| Used For   | View             | Controller/Business Logic |
| Syntax     | HTML + Java tags | Pure Java                 |
| Maintained | By Designers     | By Developers             |



### 🐱 What is Tomcat?
- Apache Tomcat is a free and open-source web server and Servlet container developed by the Apache Software Foundation.
-  It runs Java web applications (like Servlets and JSP).
-  It is not a full Java EE server like WildFly or GlassFish — it's a lightweight and fast servlet engine.

**🧱 Tomcat = Web Server + Servlet Container**
| Part                     | Description                                          |
| ------------------------ | ---------------------------------------------------- |
| 🌐 **Web Server**        | Handles HTTP requests and responses                  |
| 🧪 **Servlet Container** | Loads, manages, and executes Servlets (Java classes) |


**🚀 What Tomcat Does**
-  When you visit:
```js
http://localhost:8080/myapp/hello
```
- Tomcat does the following:
    - Receives HTTP request
    - Finds the mapped servlet
    - Calls doGet() or doPost() of your HttpServlet
    - Sends back an HTTP response

```bash
Browser 🌐
   ↓
Request (http://localhost:8080/hello)
   ↓
[ Apache Tomcat ]
   ↳ Finds the mapped Servlet
   ↳ Calls doGet()/doPost()
   ↳ Sends back response HTML
   ↑
Response (<h1>Hello</h1>)

```
**⚙️ Key Features of Tomcat**
| Feature                    | Purpose                                              |
| -------------------------- | ---------------------------------------------------- |
| ✅ **Servlet API Support**  | Supports Jakarta Servlet, JSP                        |
| 🛠️ **Deployment**         | You deploy `.war` files (Web Application Archives)   |
| 🔁 **Request Handling**    | Maps URLs to servlets using `web.xml` or annotations |
| 🧩 **Expandable**          | Lightweight and can integrate with tools like Spring |
| 🧪 **Testing Environment** | Great for local development & testing                |

**📁 Tomcat Directory Structure**
```plaintext
apache-tomcat/
├── bin/            # Startup & shutdown scripts
├── conf/           # Configuration files (server.xml, web.xml)
├── webapps/        # Deploy your .war files here!
├── logs/           # Server logs
├── lib/            # JARs used by Tomcat
└── work/           # Temp compiled JSP and cached files
```

**🔧 Common Runtime Configs (with -D option):**
| Property                        | Purpose                                                       |
| ------------------------------- | ------------------------------------------------------------- |
| `catalina.home`                 | 📁 Tomcat **installation** directory                          |
| `catalina.base`                 | 📦 Project-specific **instance** (custom configs, logs, apps) |
| `java.io.tmpdir`                | 🗂️ Temp directory for files like compiled JSPs               |
| `file.encoding`                 | 📝 Sets **character encoding** (e.g., UTF-8)                  |
| `java.util.logging.config.file` | 🛠️ Path to **logging configuration** file                    |
| `java.util.logging.manager`     | 📋 Sets Tomcat's custom logging manager                       |
| `user.timezone`                 | 🌍 JVM timezone (e.g., UTC, IST)                              |

**🧠 What is Catalina in Tomcat?**
- Catalina is the servlet container (engine) inside Apache Tomcat 🚀
- It’s the core component that:
    - Loads and manages servlets
    - Handles HTTP requests and responses
    - Manages sessions, filters, lifecycle, etc.


**Terms to Know**
| Term                  | Meaning                                             |
| --------------------- | --------------------------------------------------- |
| **Servlet**           | Java class that handles HTTP requests               |
| **Servlet Container** | Engine inside Tomcat that loads & manages servlets  |
| **WAR File**          | Packaged web app (Web ARchive) you deploy to Tomcat |
| **Context Path**      | The URL prefix for your app, e.g., `/myapp`         |
| **HTTP Methods**      | Like `GET`, `POST`, handled in servlet methods      |                                               |
| 🧱 Tomcat             | Whole web server and servlet container             |
| 🔥 Catalina           | The **engine** that runs servlets (core of Tomcat) |
| 🌐 Connector          | Handles incoming HTTP requests                     |



**🔥 Why Use Tomcat?**
- ✅ Lightweight & fast
- ✅ Easy to set up
- ✅ Great for learning Servlets & JSP
- ✅ Backed by Apache — open source and trusted
- ✅ Works with IDEs like IntelliJ, Eclipse

----
### 🧠 What Is Smart Tomcat Plugin?
- Smart Tomcat is a JetBrains plugin that integrates Apache Tomcat with IntelliJ, allowing you to:
    - 🟢 Run/Debug your servlet or JSP project easily
    - ⚙️ Automatically set up deployment paths
    - 📂 Avoid modifying Tomcat's original webapps/ folder
    - 🧼 Keep your project’s deployment isolated (clean and manageable)


**🧭 How to Use It (Quick Steps)**
1. Install Smart Tomcat plugin (Settings → Plugins → "Smart Tomcat")
2. Go to Run > Edit Configurations
3.  Click + → Select Smart Tomcat
4. Set:
    - Tomcat path
    - Project name
    - Port (default: 8080)
    - Webapp resource path (src/main/webapp)
5. Click Run ▶️ or Debug 🐞

#### **⚙️ How Smart Tomcat Works Internally**

- 🔹1. You Select a Tomcat Installation
    - You tell Smart Tomcat where your apache-tomcat-x.x.xx/ is located.
    - It uses:
        - bin/startup.bat or startup.sh to launch Tomcat
        - conf/server.xml for ports and settings

- 🔹2. Sets catalina.base to a Project-Specific Directory
    - Instead of modifying the original Tomcat installation, Smart Tomcat creates:
    -  `C:\Users\<YourName>\.SmartTomcat\<yourAppName>\`
    - This includes:
        - conf/
        - logs/
        - temp/
        - webapps/ ← your .war or expanded app is deployed here!
    -  Keeps your Tomcat install untouched



- 🔹 3. Runs a Custom Java Command (Behind the Scenes)
    - It launches Tomcat like this
    ```bash
        java -Dcatalina.base=<project-dir> -Dcatalina.home=<tomcat-install> ... org.apache.catalina.startup.Bootstrap start
    ```

- 🔹 4. Hot Deploys Your App
    - Copies your app’s compiled files (like WEB-INF/classes, .jsp, etc.)
    - Builds a .war-like structure into:
    ```bash
        ~/.SmartTomcat/myservletapp/webapps/
    ```
    - You can access your servlet at: `http://localhost:8080/<your-app-name>/<servlet-url>`

- 🔹 5. Enables IntelliJ Debugging


**✅ Benefits of Smart Tomcat**
| Feature                      | Benefit                                         |
| ---------------------------- | ----------------------------------------------- |
| 💻 No external configuration | You don’t touch the real Tomcat config manually |
| 📦 Isolated deployment       | Doesn’t mess with `/webapps/` of main Tomcat    |
| 🐞 Built-in debugger         | Works seamlessly with IntelliJ breakpoints      |
| 🔄 Easy redeployment         | Just press **Run** or **Debug** button again    |

---
## Online Book Store (Mini Amazon📚)

**Project Goal:**
- Build a basic online bookstore where users can:
    - View books
    - Register/Login
    - Add books to cart 🛒
    - Checkout with session handling
    - Admin can add/remove books

**🔧 Tech Stack:**
- Java (Servlets & JSP)
- HTML/CSS
- Tomcat (with Smart Tomcat plugin)
- IntelliJ IDEA
-  Optional: JDBC + MySQL (for persistence)

**📁 Project Modules (and Concepts Covered):**
| Module                 | Features                    | Concepts Covered                                       |
| ---------------------- | --------------------------- | ------------------------------------------------------ |
| **Home Page**          | List all books 📚           | `Servlet`, `JSP`, `RequestDispatcher`, MVC             |
| **Login/Signup**       | User authentication 👤      | `HttpServlet`, `doPost()`, `Session`, `ServletContext` |
| **Book Details Page**  | View single book 📖         | `HttpServletRequest`, `Query Params`                   |
| **Cart Page**          | Add to cart 🛒              | `HttpSession`, `ArrayList`, Session Management         |
| **Checkout Page**      | Confirm purchase ✅          | Form submission, `doPost`, `Redirect`                  |
| **Admin Page**         | Add/Delete books 🛠️        | Role-based control, `ServletConfig`                    |
| **Filters**            | Block unauthorized pages 🔐 | `Servlet Filter`                                       |
| **Exception Handling** | Show error page 💥          | `web.xml`, `<error-page>`, try-catch                   |
| **Logout**             | Invalidate session          | `session.invalidate()`                                 |
| **web.xml**            | Configure routes            | `Deployment Descriptor`                                |


**🧱 Sample Folder Structure:**
```pgsql
OnlineBookStore/
│
├── src/
│   └── com.bookstore.*
│       ├── servlet/
│       │   ├── LoginServlet.java
│       │   ├── BookListServlet.java
│       │   ├── AddToCartServlet.java
│       │   └── AdminServlet.java
│       └── filter/
│           └── AuthFilter.java
│
├── WebContent/
│   ├── index.jsp
│   ├── login.jsp
│   ├── register.jsp
│   ├── cart.jsp
│   ├── error.jsp
│   └── WEB-INF/
│       ├── web.xml
│       └── lib/
```

