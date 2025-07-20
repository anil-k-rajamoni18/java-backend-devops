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
- Example: A weather app uses an API to fetch weather data from a remote server:

```php
GET https://api.weather.com/v3/weather/forecast?location=Hyderabad
```


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

