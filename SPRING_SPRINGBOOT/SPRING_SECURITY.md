## Spring Security

### 🚀 Introduction to Spring Security
- Spring Security is a powerful and highly customizable authentication and access-control framework for Java applications, particularly Spring-based applications.

**Key Features ✨**
- Authentication - Who are you?
- Authorization - What can you do?
- Protection against attacks - CSRF, session fixation, clickjacking
- Servlet API integration
- Optional integration with Spring Web MVC

**Why Spring Security?**
- Built-in support for authentication & authorization.
- Comprehensive security services
- Extensive customization options
- Active community and regular updates
- Seamless Spring ecosystem integration
- Enterprise-ready features
- Protects against CSRF, XSS, Session Fixation, Clickjacking.
- Works seamlessly with Spring Boot.


**Default Behavior in Spring Boot:**
- When spring-boot-starter-security is added:
    - A login form is auto-generated.
    - Default user with a random password is created.
    - All endpoints require authentication.


**Fundamentals of Spring Security**
- Authentication vs Authorization
    - *Authentication*: Verifying identity (e.g., username/password, token).
    - *Authorization*: Verifying what authenticated user can access (roles/permissions).
- Principal: The logged-in user (an object in SecurityContext).
- GrantedAuthority: Represents permissions/roles assigned.
- SecurityContextHolder: Holds security details for the current request (ThreadLocal).
- Password Encoders: Always store hashed passwords (BCryptPasswordEncoder is recommended).

---
### 🏗️ Spring Security Architecture
**Core Components 🧩**
1.  `SecurityContext and SecurityContextHolder`
```less
SecurityContextHolder
    └── SecurityContext
        └── Authentication
            ├── Principal (User details)
            ├── Credentials (Password)
            └── Authorities (Roles/Permissions)
```

2. `Authentication Manager`
- AuthenticationManager: Main strategy interface for authentication, Delegates to one or more AuthenticationProviders.
- ProviderManager: Default implementation
- AuthenticationProvider: Performs specific authentication type
    - Example: DaoAuthenticationProvider (uses UserDetailsService + PasswordEncoder).

3. ` Filter Chain Architecture `
- A chain of servlet filters executed for each request.
- Core filter: `DelegatingFilterProxy` delegates to Spring-managed filters.
```less
HTTP Request → Security Filter Chain → DispatcherServlet
```
- Key Filters:
    - SecurityContextPersistenceFilter
    - UsernamePasswordAuthenticationFilter (handles login form or Basic Auth).
    - BasicAuthenticationFilter
    - JwtAuthenticationFilter ((handles JWT).)
    - AuthorizationFilter
    - ExceptionTranslationFilter (handles exceptions).
    - FilterSecurityInterceptor (final authorization check).

```less
Client Request
    ↓
Security Filter Chain
    ↓
Authentication Manager
    ↓
Authentication Provider
    ↓
UserDetailsService
    ↓
Security Context
    ↓
Authorization
    ↓
Protected Resource
```

4. `UserDetailsService`
- Loads user info (username, password, roles) from DB or in-memory store.`

5. `PasswordEncoder`
- Encodes/validates passwords.
- Common: BCryptPasswordEncoder.

6. `AccessDecisionManager`
- Decides if a user has permission to access a resource.


---
### Types of Security in Spring Security
| **Type**                    | **Description**                                                     | **Pros**                                                   | **Cons**                                                        | **Best For**                                        |
| --------------------------- | ------------------------------------------------------------------- | ---------------------------------------------------------- | --------------------------------------------------------------- | --------------------------------------------------- |
| **Basic Auth**              | Sends `username:password` in every request header (Base64 encoded). | Simple, supported everywhere.                              | Insecure without HTTPS, credentials exposed.                    | Internal APIs, testing.                             |
| **Form-Based Login**        | Custom login page with username/password.                           | User-friendly, customizable.                               | Requires session management.                                    | Web apps with login pages.                          |
| **Session-Based Auth**      | Server maintains user session (`JSESSIONID`).                       | Easy to manage, widely used.                               | Not scalable in distributed systems (sticky sessions required). | Traditional web apps.                               |
| **Token-Based (JWT)**       | Stateless auth with signed tokens.                                  | Scales easily, no session storage, microservices friendly. | Hard to revoke/rotate tokens, larger payloads.                  | REST APIs, Microservices.                           |
| **OAuth2 / OpenID Connect** | Delegated authentication via identity providers (Google, GitHub).   | SSO, industry standard, secure.                            | Setup complexity, token management.                             | Modern apps, third-party login, enterprise systems. |

**Authorization Types**
- Role-Based Access Control (RBAC)
```java
@PreAuthorize("hasRole('ADMIN')")
public void adminOnlyMethod() { }
```
- Permission-Based Access Control
```java
@PreAuthorize("hasAuthority('READ_USERS')")
public void readUsers() { }
```
- Expression-Based Access Control
```java
@PreAuthorize("authentication.name == #username")
public void updateProfile(String username) { }
```

**⚖️ Authentication vs Authorization**
| **Aspect**            | **Authentication 🔐**                  | **Authorization 🎯**                |
|------------------------|-----------------------------------------|-------------------------------------|
| **Purpose**           | Verify identity                         | Control access                      |
| **Question**          | "Who are you?"                          | "What can you do?"                  |
| **Process**           | Login credentials                       | Check permissions                   |
| **Examples**          | Username/password, biometrics           | Role-based, resource-based          |
| **Spring Security**   | `AuthenticationManager`                 | `AccessDecisionManager`             |
| **Annotations**       | `@Authenticated`                        | `@PreAuthorize`, `@Secured`         |

**Authentication Flow 🔄**
```less
User Credentials → Authentication Filter → Authentication Manager 
→ Authentication Provider → UserDetailsService → Authentication Object
```

**Authorization Flow 🔄**
```less
Authenticated User → Access Request → Authorization Manager 
→ Access Decision Voters → Grant/Deny Access
```

---
### 🎫 JWT Implementation
- JWT (JSON Web Token) is a compact, URL-safe token format used for securely transmitting information between parties.
- It’s commonly used for authentication and authorization in modern web and mobile applications.
- The token is digitally signed, which means the receiver can verify it wasn’t altered.

**📦 Structure of a JWT**
- A JWT consists of three parts, separated by dots (.):
```less
Header.Payload.Signature
```

- `Header`
    - Specifies metadata about the token, usually:
        - alg → Algorithm used for signing (e.g., HS256, RS256).
        - typ → Type, usually "JWT".
```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

- `Payload (Claims)`
    - Contains user data and metadata (called claims).
    - Types of claims:
        - Registered (standard like sub, exp, iss).
        - Public (defined in IANA registry).
        - Private (custom claims between parties).
```json
{
  "sub": "user123",
  "name": "John Doe",
  "iat": 1516239022,
  "exp": 1516325422,
  "roles": ["USER", "ADMIN"]
}
```

- `Signature`
    - Ensures the token hasn’t been tampered with.
    - Created by encoding Header + Payload, then signing with a secret key or private key.
    - Example with HMAC-SHA256:
        ```scss
        HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), secret)
        ```


**How JWT Works in Authentication**
- User logs in with credentials.
- Server verifies credentials and generates a JWT.
- JWT is sent to the client (stored in localStorage, cookies, or memory).
- For each request, the client sends the JWT in the Authorization header:
```makefile 
Authorization: Bearer <jwt_token>
```
- Server verifies the signature → if valid, grants access.


**✅ Advantages of JWT**
- Stateless → No need to store sessions on server.
- Scalable → Perfect for microservices.
- Portable → Can be used across different domains/services.
- Tamper-proof → Signed, so contents can’t be modified undetected.

**⚠️ Limitations of JWT**
- Hard to revoke → Once issued, valid until expiry (unless extra revocation logic added).
- Token size → Larger than a session ID (stored in headers each request).
- No built-in encryption → JWT payload is base64-encoded, not encrypted (sensitive data must be avoided or encrypted separately).

> JWT is a signed token that proves identity and carries user claims, mainly used for stateless authentication in APIs.

---
### JWT Implementation in Spring Boot


`Step 1: Add Dependencies`
```xml 
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.11.5</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.11.5</version>
    <scope>runtime</scope>
</dependency>
```

`Step 2: JWT Utility Class`
```java
@Component
public class JwtUtils {
    private String jwtSecret = "mySecretKey";
    private int jwtExpirationMs = 3600; // 1 hour
    
    public String generateJwtToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
```

`Step 3: JWT Authentication Filter`
```java
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                  HttpServletResponse response, 
                                  FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(userDetails, null, 
                                                          userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }
        
        filterChain.doFilter(request, response);
    }
}

// JWT Security Configuration 🔧
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/test/**").permitAll()
                .anyRequest().authenticated()
            );
        
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}
```

