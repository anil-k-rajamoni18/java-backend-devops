## 🌐 Basics of HTTP/HTTPS

**What is HTTP?**
- HTTP (HyperText Transfer Protocol) is the foundation of data communication on the web. 
- It is a stateless, application-layer protocol used for transmitting hypermedia documents, such as HTML.

**Key Concepts:**
- Client-Server Model: The client (browser) sends a request, and the server responds.
- Statelessness: Each request is independent; the server does not retain user information between requests.
- Methods:
    - GET: Retrieve data
    - POST: Submit data
    - PUT: Update data
    - DELETE: Remove data
    - HEAD, OPTIONS, PATCH

**Example:**
```php
GET /index.html HTTP/1.1
Host: www.example.com
```

**What is HTTPS?**
- HTTPS (HTTP Secure) is HTTP over SSL/TLS. It encrypts the data to ensure secure communication.
- Benefits of HTTPS:
    - Encryption: Protects data from eavesdropping.
    - Data Integrity: Prevents data tampering.
    - Authentication: Confirms the identity of the website.


### Difference between HTTP and HTTPS

| Feature        | HTTP                                      | HTTPS                                         |
|----------------|-------------------------------------------|-----------------------------------------------|
| Full Form      | HyperText Transfer Protocol               | HyperText Transfer Protocol Secure            |
| Security       | Not secure; data sent in plain text       | Secure; data encrypted using SSL/TLS          |
| Port           | Default port 80                           | Default port 443                              |
| URL Prefix     | http://                                   | https://                                      |
| Data Integrity | No protection against tampering           | Ensures data integrity                        |
| Authentication | No server authentication                  | Server authentication via certificates        |
| Use Case       | Non-sensitive data, public websites       | Sensitive data, login, banking, e-commerce    |

---

## What is SSL and TLS?

- **SSL (Secure Sockets Layer):**
  - A cryptographic protocol designed to secure communication over a computer network.
  - Provides encryption, data integrity, and authentication.
  - Now considered deprecated due to security vulnerabilities.

- **TLS (Transport Layer Security):**
  - The successor to SSL; more secure and widely used today.
  - Provides the same security features as SSL but with improved algorithms and protocols.
  - Used to encrypt data between client and server in HTTPS.

**Summary:**  
HTTPS uses SSL/TLS to encrypt data, making web communication secure. TLS is the modern, secure version of SSL.

![](https://cf-assets.www.cloudflare.com/slt3lc6tev37/5aYOr5erfyNBq20X5djTco/3c859532c91f25d961b2884bf521c1eb/tls-ssl-handshake.png)

**Explanation:**
- The client requests a secure connection.
- The server provides its SSL/TLS certificate.
- The client verifies the certificate.
- Both perform a TLS handshake to establish encryption keys.
- All HTTP data is then exchanged in encrypted form.


---
## 🔌 What is an API?
- API (Application Programming Interface) is a set of rules and protocols that allows one software application to interact with another. 
- It defines how requests and responses should be structured.


**🔸 Real-time Usage Examples**
- Weather App: Uses an API to fetch weather data from a server.
- Payment Gateway: E-commerce sites call APIs from payment providers like Stripe or PayPal.
- Social Login: “Login with Google” uses Google’s OAuth API.
- Stock Market App: Uses APIs to get real-time stock prices.

```php
GET https://api.weather.com/v3/weather/forecast?location=Hyderabad
```

![](https://media.geeksforgeeks.org/wp-content/uploads/20230216170349/What-is-an-API.png)

**🧰 API Usage Types**
- Web APIs: Accessed over HTTP (e.g., REST, GraphQL)
- Library APIs: Provided by software libraries (e.g., Java Collections API)
- Operating System APIs: Interact with OS features (e.g., Windows API)
- Hardware APIs: Communicate with hardware (e.g., camera, GPS)

### 🌐 What is REST?
- REST (Representational State Transfer) is an architectural style for designing networked applications using HTTP.

**REST Principles:**
- Stateless: Each request is independent.
- Client-Server: Separation of concerns.
- Cacheable: Responses can be cached.
- Uniform Interface: Standardized way to interact with resources.

**REST API Design Standards**
1. Use Nouns for Resources: APIs should represent resources (objects), not actions.
```php
✅ Good:
GET /users
POST /orders

❌ Bad:
GET /getUsers
POST /createOrder

```
2. Use HTTP Methods Properly:

| HTTP Method | Action            | Example Endpoint      |
|-------------|-------------------|----------------------|
| GET         | Read              | /products            |
| POST        | Create            | /products            |
| PUT         | Update (full)     | /products/123        |
| PATCH       | Update (partial)  | /products/123        |
| DELETE      | Delete            | /products/123        |

3. Use Plural Naming:
```php
✅ Good:
GET /books
GET /books/42

❌ Bad:
GET /book
GET /book?id=42 
```

4. Versioning: Use URI versioning to avoid breaking changes.
```php
GET /api/v1/users
```


5. Use HTTP Status Codes: Indicate success or failure clearly

| Code | Meaning                  | When to Use                           |
|------|--------------------------|---------------------------------------|
| 200  | OK                       | Successful GET, PUT, DELETE           |
| 201  | Created                  | Successful POST                       |
| 204  | No Content               | Successful DELETE with no response    |
| 400  | Bad Request              | Invalid input                         |
| 401  | Unauthorized             | Missing/invalid authentication        |
| 403  | Forbidden                | Authenticated but not allowed         |
| 404  | Not Found                | Resource doesn’t exist                |
| 500  | Internal Server Error    | Unexpected server error               |

6. Support Filtering, Sorting, Pagination:
```php
GET /products?category=electronics&sort=price&page=2&limit=10
```

7. Use Consistent Naming Conventions
- Use camelCase or snake_case for JSON keys.
- Use lowercase and hyphens in URLs.

```json
{
  "userId": 123,
  "firstName": "John"
}

```

8. Return Standardized Error Responses
```json
{
  "timestamp": "2025-07-14T10:00:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "User not found",
  "path": "/users/999"
}
```
9. Secure Your API
- Use HTTPS only.
- Implement authentication (e.g., OAuth2, JWT).
- Use rate limiting and input validation.

---
### Ways to Consume REST APIs in Java
1. Using HttpURLConnection (Core Java):
    Basic, low-level HTTP client
2. Apache HttpClient:
More flexible and powerful

3. Spring RestTemplate:
Simplified REST client in Spring

4. Spring WebClient (Reactive):
Non-blocking, asynchronous client

5. Retrofit (by Square):
Type-safe HTTP client for Android and Java
OkHttp:
Efficient HTTP client with connection pooling


---
### 🔐 How to Consume Private APIs
- Private APIs are not publicly accessible and usually require authentication and authorization. 

1. Understand API Requirements
- Check the API documentation.
- Identify:
    - Base URL
    - Required authentication (API key, OAuth, etc.)
    - Header structure
    - Required parameters (query/path/body)

2. Set Required Headers
- Most private APIs need:
  - Authorization: with a token or API key
  - Content-Type: often application/json
  - Any custom headers specific to the service

**3. Make the API Call (Java Example using RestTemplate)**
```java
HttpHeaders headers = new HttpHeaders();
headers.set("Authorization", "Bearer YOUR_TOKEN");
headers.set("Content-Type", "application/json");

HttpEntity<String> entity = new HttpEntity<>(headers);

ResponseEntity<String> response = restTemplate.exchange(
    "https://api.example.com/private/data",
    HttpMethod.GET,
    entity,
    String.class
);
```

**🔐 Types of API Authentication**
| Type                     | Description                                                                             |
| ------------------------ | --------------------------------------------------------------------------------------- |
| **API Key**              | A static token passed via headers or query params. Simple but less secure.              |
| **Basic Auth**           | Username and password encoded in Base64: `Authorization: Basic base64(user:pass)`       |
| **Bearer Token**         | Access token passed in header: `Authorization: Bearer <token>`                          |
| **OAuth 2.0**            | Most secure and flexible. Used for delegated access. Supports token refresh and scopes. |
| **JWT (JSON Web Token)** | Token-based, stateless authentication. Often used with OAuth.                           |
| **Mutual TLS (mTLS)**    | Certificate-based authentication. Very secure, used in enterprise setups.               |


**🧾 What Are Request Headers?**
- Request headers are key-value pairs sent by the client (your code) to the server.

| Header          | Purpose                                               |
| --------------- | ----------------------------------------------------- |
| `Authorization` | Sends credentials (API key, bearer token, etc.)       |
| `Content-Type`  | Format of the request body (e.g., `application/json`) |
| `Accept`        | Expected response format (e.g., `application/json`)   |
| `User-Agent`    | Info about the client making the request              |
| `Cookie`        | Sends stored cookies to the server                    |

**📬 What Are Response Headers?**
- Response headers are sent by the server to the client, along with the status code and body.

| Header                                      | Purpose                                                |
| ------------------------------------------- | ------------------------------------------------------ |
| `Content-Type`                              | Format of the returned data (e.g., `application/json`) |
| `Set-Cookie`                                | Stores a cookie on the client                          |
| `Cache-Control`                             | Instructions on how/if the response should be cached   |
| `WWW-Authenticate`                          | Sent with 401 errors to indicate required auth         |
| `RateLimit-Limit` / `X-RateLimit-Remaining` | Rate-limiting info (used in APIs like GitHub)          |



----

### 🔹 1. SOAP (Simple Object Access Protocol)
**🧾 Overview:**
- A protocol for exchanging structured information using XML.
- Designed with strict standards and security in mind.
- Operates over HTTP, SMTP, TCP, etc.

**✅ Pros**:
- Strong security (WS-Security, encryption, etc.)
- Built-in contract via WSDL (Web Services Description Language)
- Reliable messaging, good for enterprise systems (e.g., banking, telcos)

**❌ Cons**:
- Verbose XML messages
- Harder to debug and implement
- Less flexible than REST or GraphQL

**📦 Example Use Case:**
- Enterprise integrations
- Financial institutions
- Legacy systems


### 🔹 2. REST (Representational State Transfer)
**🧾 Overview:**
- Architectural style (not a protocol).
- Uses HTTP methods like GET, POST, PUT, DELETE to interact with resources.
- Resources are typically represented as JSON (can also be XML, HTML, e

**✅ Pros:**
- Lightweight and easy to use
- Stateless communication
- Works well with web standards (e.g., browsers, HTTP caching)
- Widely supported and understood

**❌ Cons:**
- Multiple endpoints for related data
- Over-fetching or under-fetching data
- No built-in schema (unless you use tools like OpenAPI/Swagger)

**📦 Example Use Case:**
- CRUD APIs for web/mobile apps
- Public APIs (e.g., Twitter, GitHub, Spotify)

### 🔹 3. GraphQL

**🧾 Overview:**
- A query language for APIs developed by Facebook.
- Allows clients to specify exactly what data they need.
- Single /graphql endpoint for all requests.

**✅ Pros:**
- Prevents over-fetching and under-fetching
- Strongly typed schema (self-documenting)
- Ideal for complex or nested data
- Real-time support via subscriptions

**❌ Cons:**
- More complex to set up than REST
- Caching is harder (compared to REST’s HTTP caching)
- Requires custom tooling and mindset

**📦 Example Use Case:**
Mobile apps with varying data needs
Applications with complex data relationships
Real-time apps (e.g., messaging, dashboards)

**🔍 Summary Comparison:**
| Feature           | SOAP               | REST                | GraphQL                 |
| ----------------- | ------------------ | ------------------- | ----------------------- |
| Protocol or Style | Protocol           | Architectural style | Query language          |
| Data Format       | XML only           | JSON, XML, etc.     | JSON                    |
| Transport         | HTTP, SMTP, etc.   | HTTP                | HTTP (usually POST)     |
| Schema            | WSDL               | Optional (OpenAPI)  | Strongly typed schema   |
| Flexibility       | Low                | Medium              | High                    |
| Real-time Support | No                 | Limited (Webhooks)  | Yes (via subscriptions) |
| Best For          | Enterprise systems | Web/mobile APIs     | Dynamic, flexible apps  |


---
###  gRPC
- gRPC (Google Remote Procedure Call) is a high-performance, open-source RPC framework developed by Google.
- It allows services to communicate with each other efficiently, using Protocol Buffers (Protobuf) for data serialization.


**🔧 Key Features:**
- Uses HTTP/2 for transport (supports multiplexing, streaming, compression)
- Protobuf (binary format) for compact, fast message serialization
- Strongly typed APIs defined using .proto files
- Supports synchronous and asynchronous communication
- Built-in authentication, streaming, and deadlines/timeouts

**🔍 gRPC vs REST**
| Feature                 | gRPC                                     | REST                                        |
| ----------------------- | ---------------------------------------- | ------------------------------------------- |
| **Transport Protocol**  | HTTP/2                                   | HTTP/1.1 (commonly)                         |
| **Data Format**         | Protocol Buffers (binary)                | JSON, XML                                   |
| **API Definition**      | `.proto` file (strongly typed)           | No strict schema (OpenAPI/Swagger optional) |
| **Speed/Performance**   | Faster (binary + HTTP/2)                 | Slower (text-based + HTTP/1.1)              |
| **Streaming Support**   | Yes (client, server, bidirectional)      | Limited (manual work via WebSockets)        |
| **Browser Support**     | Limited (needs gRPC-Web or proxy)        | Excellent (native HTTP)                     |
| **Human Readability**   | No (binary messages)                     | Yes (JSON is readable)                      |
| **Tooling & Debugging** | More complex (special tools needed)      | Easier (can use Postman, browser, curl)     |
| **Use Case Fit**        | Internal microservices, high performance | Public APIs, web/mobile applications        |
| **Authentication**      | Built-in (SSL/TLS, token, etc.)          | Custom (OAuth, API keys, etc.)              |


#### **🧪 Example Use Cases**
**Use gRPC when:**
- You need high performance (e.g., low latency, high throughput)
- Services are talking to each other (microservices architecture)
- You want streaming or bi-directional communication
- You're working in a polyglot environment (Java, Go, C++, etc.)

**Use REST when:**
- You’re building public-facing APIs
- You need easy debugging and testing
- You want broad compatibility (e.g., browser support, third-party use)
- Your team is familiar with HTTP/JSON and wants simplicity
