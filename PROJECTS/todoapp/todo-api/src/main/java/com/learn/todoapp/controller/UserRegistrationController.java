package com.learn.todoapp.controller;

import com.learn.todoapp.dto.*;
import com.learn.todoapp.enity.ApiClient;
import com.learn.todoapp.enity.User;
import com.learn.todoapp.exception.ErrorResponse;
import com.learn.todoapp.service.ApiClientService;
import com.learn.todoapp.service.JwtService;
import com.learn.todoapp.service.UserService;
import com.learn.todoapp.utils.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for user registration and API client credentials management.
 * Handles user registration and issues client_id and client_secret for API access.
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserRegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ApiClientService apiClientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Register a new user and issue API client credentials.
     *
     * @param registrationRequest User registration details
     * @param bindingResult       Validation results
     * @return Registration response with user info and client credentials
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest registrationRequest,
                                          BindingResult bindingResult) {

        logger.info("Processing user registration request for email: {}", registrationRequest.getEmail());

        try {
            // Check for validation errors
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = new HashMap<>();
                bindingResult.getFieldErrors().forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage()));

                logger.warn("Validation errors in registration request: {}", errors);
                return ResponseEntity.badRequest().body(new ErrorResponse("Validation failed", errors));
            }

            // Check if user already exists
            if (userService.existsByEmail(registrationRequest.getEmail())) {
                logger.warn("Registration attempt with existing email: {}", registrationRequest.getEmail());
                return ResponseEntity.badRequest()
                        .body(new ErrorResponse("Email is already registered!", null));
            }

            if (userService.existsByUsername(registrationRequest.getUsername())) {
                logger.warn("Registration attempt with existing username: {}", registrationRequest.getUsername());
                return ResponseEntity.badRequest()
                        .body(new ErrorResponse("Username is already taken!", null));
            }

            // Create new user
            User user = createUserFromRequest(registrationRequest);
            User savedUser = userService.save(user);

            logger.info("User registered successfully with ID: {}", savedUser.getId());

            // Generate API client credentials
            ApiClient apiClient = createApiClient(savedUser);
            ApiClient savedApiClient = apiClientService.save(apiClient);

            logger.info("API client credentials generated for user: {} with client_id: {}",
                    savedUser.getUsername(), savedApiClient.getClientId());

            // Prepare response
            UserRegistrationResponse response = buildRegistrationResponse(savedUser, savedApiClient);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            logger.error("Error during user registration for email: {}",
                    registrationRequest.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Registration failed. Please try again.", null));
        }
    }

    /**
     * Generate new client credentials for an existing user.
     *
     * @param userId User ID
     * @return New client credentials
     */
    @PostMapping("/users/{userId}/regenerate-credentials")
    public ResponseEntity<?> regenerateClientCredentials(@PathVariable Long userId) {

        logger.info("Regenerating client credentials for user ID: {}", userId);

        try {
            // Check if user exists
            User user = userService.findById(userId);
            if (user == null) {
                logger.warn("User not found for ID: {}", userId);
                return ResponseEntity.notFound().build();
            }

            // Deactivate existing credentials
            apiClientService.deactivateClientsByUserId(userId);

            // Generate new credentials
            ApiClient newApiClient = createApiClient(user);
            ApiClient savedApiClient = apiClientService.save(newApiClient);

            logger.info("New client credentials generated for user: {} with client_id: {}",
                    user.getUsername(), savedApiClient.getClientId());

            ClientCredentialsResponse response = new ClientCredentialsResponse(
                    savedApiClient.getClientId(),
                    savedApiClient.getClientSecret(),
                    savedApiClient.getCreatedAt(),
                    savedApiClient.getExpiresAt(),
                    savedApiClient.isActive()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Error regenerating client credentials for user ID: {}", userId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed to regenerate credentials", null));
        }
    }

    /**
     * Get client credentials for a user.
     *
     * @param userId User ID
     * @return Client credentials information
     */
    @GetMapping("/users/{userId}/credentials")
    public ResponseEntity<?> getUserCredentials(@PathVariable Long userId) {

        logger.info("Retrieving client credentials for user ID: {}", userId);

        try {
            User user = userService.findById(userId);
            if (user == null) {
                logger.warn("User not found for ID: {}", userId);
                return ResponseEntity.notFound().build();
            }

            ApiClient apiClient = apiClientService.findActiveByUserId(userId);
            if (apiClient == null) {
                logger.info("No active credentials found for user ID: {}", userId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("No active credentials found", null));
            }

            // Don't return the actual secret, just masked version
            ClientCredentialsResponse response = new ClientCredentialsResponse(
                    apiClient.getClientId(),
                    maskSecret(apiClient.getClientSecret()),
                    apiClient.getCreatedAt(),
                    apiClient.getExpiresAt(),
                    apiClient.isActive()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Error retrieving client credentials for user ID: {}", userId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed to retrieve credentials", null));
        }
    }

    /**
     * Deactivate client credentials.
     *
     * @param clientId Client ID to deactivate
     * @return Success response
     */
    @PostMapping("/clients/{clientId}/deactivate")
    public ResponseEntity<?> deactivateClient(@PathVariable String clientId) {

        logger.info("Deactivating client with ID: {}", clientId);

        try {
            boolean deactivated = apiClientService.deactivateClient(clientId);

            if (deactivated) {
                logger.info("Client deactivated successfully: {}", clientId);
                return ResponseEntity.ok().body(Map.of("message", "Client deactivated successfully"));
            } else {
                logger.warn("Client not found or already inactive: {}", clientId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse("Client not found or already inactive", null));
            }

        } catch (Exception e) {
            logger.error("Error deactivating client: {}", clientId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed to deactivate client", null));
        }
    }

    /**
     * Issue JWT token based on client credentials.
     */
    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody TokenApiRequest tokenRequest) {
        try {
            String clientId = tokenRequest.clientId();
            String clientSecret = tokenRequest.clientSecret();
            String grantType = tokenRequest.grantType();

            logger.info("Token request received for client_id: {}", clientId);

            if (!"client_credentials".equalsIgnoreCase(grantType)) {
                return ResponseEntity.badRequest()
                        .body(new ErrorResponse("Unsupported grant_type. Only 'client_credentials' is allowed.", null));
            }

            // Validate client
            ApiClient apiClient = apiClientService.findByClientId(clientId);
            if (apiClient == null || !apiClient.isActive()) {
                logger.warn("Invalid or inactive client_id: {}", clientId);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorResponse("Invalid client credentials", null));
            }

            // Validate client_secret
            if (!apiClient.getClientSecret().equals(clientSecret)) {
                logger.warn("Invalid client_secret for client_id: {}", clientId);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorResponse("Invalid client credentials", null));
            }

            // Load user associated with the client
            User authUser = apiClient.getUser();
            if (authUser == null || !authUser.isEnabled()) {
                logger.warn("User not found or disabled for client_id: {}", clientId);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorResponse("User not found or disabled", null));
            }

            // Build authentication principal for token generation
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword())
            );

            // Generate JWT
            TokenApiResponse tokenApiResponse = jwtService.generateJwtToken(authentication);

            return ResponseEntity.ok(tokenApiResponse);

        } catch (Exception e) {
            logger.error("Error generating token", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Token generation failed", null));
        }
    }


    // Helper Methods

    /**
     * Creates a User entity from registration request.
     */
    private User createUserFromRequest(UserRegistrationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRoles(request.getRoles());
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        return user;
    }

    /**
     * Creates an ApiClient entity for the user.
     */
    private ApiClient createApiClient(User user) {
        ApiClient apiClient = new ApiClient();
        apiClient.setUser(user);
        apiClient.setClientId(ClientCredentialsGenerator.generateClientId());
        apiClient.setClientSecret(ClientCredentialsGenerator.generateClientSecret());
        apiClient.setClientName(user.getUsername() + "_api_client");
        apiClient.setActive(true);
        apiClient.setCreatedAt(LocalDateTime.now());
        apiClient.setExpiresAt(LocalDateTime.now().plusYears(1)); // 1 year expiry

        return apiClient;
    }

    /**
     * Builds the registration response with user and client info.
     */
    private UserRegistrationResponse buildRegistrationResponse(User user, ApiClient apiClient) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setUserId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setCreatedAt(user.getCreatedAt());

        // Include client credentials
        ClientCredentialsResponse credentials = new ClientCredentialsResponse(
                apiClient.getClientId(),
                apiClient.getClientSecret(),
                apiClient.getCreatedAt(),
                apiClient.getExpiresAt(),
                apiClient.isActive()
        );
        response.setClientCredentials(credentials);

        return response;
    }

    /**
     * Masks client secret for security when returning in GET requests.
     */
    private String maskSecret(String secret) {
        if (secret == null || secret.length() < 8) {
            return "****";
        }
        return secret.substring(0, 4) + "****" + secret.substring(secret.length() - 4);
    }

}
