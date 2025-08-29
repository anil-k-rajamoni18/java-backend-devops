package com.learn.todoapp.filter;

import com.learn.todoapp.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * JWT Authentication Filter that intercepts HTTP requests to validate JWT tokens
 * and set the security context for authenticated users.
 *
 * This filter runs once per request and checks for valid JWT tokens in the
 * Authorization header. If a valid token is found, it extracts user information
 * and sets the authentication in the Spring Security context.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    // Header name for JWT token
    private static final String AUTHORIZATION_HEADER = "Authorization";

    // Bearer token prefix
    private static final String BEARER_PREFIX = "Bearer ";

    // Paths that should be excluded from JWT authentication
    private static final List<String> EXCLUDED_PATHS = Arrays.asList(
            "/api/auth/",
            "/swagger-ui/",
            "/v3/api-docs",
            "/actuator/health",
            "/h2-console/",
            "/"
    );

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    /**
     * Main filter method that processes each HTTP request to validate JWT tokens.
     *
     * @param request HTTP request
     * @param response HTTP response
     * @param filterChain Filter chain to continue processing
     * @throws ServletException if servlet error occurs
     * @throws IOException if I/O error occurs
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        logger.debug("Processing authentication for request: {}", request.getRequestURI());

        try {
            // Extract JWT token from request
            String jwt = parseJwt(request);

            // Validate token and set authentication if valid
            if (jwt != null && jwtService.validateJwtToken(jwt)) {
                setAuthenticationFromToken(jwt, request);
            } else if (jwt != null) {
                logger.warn("Invalid JWT token received for request: {}", request.getRequestURI());
                // Optionally, you can set error response here
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\":\"Invalid or expired token\"}");
                response.setContentType("application/json");
                return; // Don't continue with filter chain
            }

        } catch (Exception e) {
            logger.error("Cannot set user authentication for request {}: {}",
                    request.getRequestURI(), e.getMessage(), e);

            // Clear any partial authentication
            SecurityContextHolder.clearContext();

            // Optionally set error response
            if (!response.isCommitted()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.getWriter().write("{\"error\":\"Authentication failed\"}");
                return;
            }
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }

    /**
     * Extracts JWT token from the Authorization header.
     *
     * @param request HTTP request
     * @return JWT token string or null if not found/invalid format
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(AUTHORIZATION_HEADER);

        logger.debug("Authorization header: {}",
                headerAuth != null ? "Bearer [PROTECTED]" : "null");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(BEARER_PREFIX)) {
            String token = headerAuth.substring(BEARER_PREFIX.length());
            logger.debug("Extracted JWT token of length: {}", token.length());
            return token;
        }

        logger.debug("No valid Authorization header found");
        return null;
    }

    /**
     * Sets the authentication context based on the validated JWT token.
     *
     * @param jwt Valid JWT token
     * @param request HTTP request for additional details
     */
    private void setAuthenticationFromToken(String jwt, HttpServletRequest request) {
        try {
            // Extract username from token
            String username = jwtService.getUserNameFromJwtToken(jwt);
            logger.debug("Authenticating user: {}", username);

            // Load user details
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Create authentication token
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

            // Set additional details from request
            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            // Set authentication in security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            logger.debug("Successfully authenticated user: {} with authorities: {}",
                    username, userDetails.getAuthorities());

        } catch (Exception e) {
            logger.error("Failed to set authentication from token: {}", e.getMessage());
            throw e; // Re-throw to be handled by calling method
        }
    }

    /**
     * Determines whether the filter should be applied to the current request.
     *
     * @param request HTTP request
     * @return true if filter should not be applied, false otherwise
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        // allow root "/"
        if ("/".equals(path)) {
            return true;
        }
        // Skip filtering for excluded paths
        boolean shouldExclude = EXCLUDED_PATHS.stream()
                .anyMatch(path::startsWith);

        if (shouldExclude) {
            logger.debug("Skipping JWT authentication for excluded path: {}", path);
        }

        return shouldExclude;
    }

    /**
     * Utility method to check if the current request has a valid JWT token.
     * Can be used by other components to verify authentication state.
     *
     * @param request HTTP request
     * @return true if request has valid JWT token, false otherwise
     */
    public boolean hasValidToken(HttpServletRequest request) {
        try {
            String jwt = parseJwt(request);
            return jwt != null && jwtService.validateJwtToken(jwt);
        } catch (Exception e) {
            logger.debug("Token validation failed: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Extracts the username from JWT token in the request without validating it.
     * Useful for logging and debugging purposes.
     *
     * @param request HTTP request
     * @return username from token or null if not found
     */
    public String getUsernameFromRequest(HttpServletRequest request) {
        try {
            String jwt = parseJwt(request);
            if (jwt != null) {
                return jwtService.getUserNameFromJwtToken(jwt);
            }
        } catch (Exception e) {
            logger.debug("Failed to extract username from request: {}", e.getMessage());
        }
        return null;
    }
}
