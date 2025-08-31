package com.learn.todoapp.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class APIMetricsFilter extends OncePerRequestFilter {

    private static final List<String> ALLOWED_PREFIXES =
            Arrays.asList("/api/", "/h2", "/v3/api-docs", "/swagger-ui", "/actuator");

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        long startTime = System.currentTimeMillis();
        String uri = request.getRequestURI();
        String method = request.getMethod();

        log.info("Incoming Request: {} {}", method, uri);

        if (isAllowed(uri)) {
            filterChain.doFilter(request, response); // delegate request to DispatcherServlet
            long latency = System.currentTimeMillis() - startTime;
            log.info("📊 API Metric - Method: {}, URI: {}, Status: {}, ClientIP: {}, Host: {}, Latency: {} ms",
                    request.getMethod(),
                    request.getRequestURI(),
                    response.getStatus(),
                    request.getRemoteAddr(),
                    request.getRemoteHost(),
                    latency
            );
        } else {
            log.warn("Blocked request to URI: {}", uri);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Unauthorized endpoint\"}");
        }
    }

    private boolean isAllowed(String uri) {
        // allow root "/"
        if ("/".equals(uri)) {
            return true;
        }
        // allow if URI starts with any of the allowed prefixes
        return ALLOWED_PREFIXES.stream().anyMatch(uri::startsWith);
    }
}

