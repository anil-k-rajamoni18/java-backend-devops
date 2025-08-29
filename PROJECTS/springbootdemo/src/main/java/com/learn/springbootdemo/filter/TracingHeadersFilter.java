// src/main/java/com/learn/springbootdemo/filter/TracingHeadersFilter.java
package com.learn.springbootdemo.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TracingHeadersFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        try {
            chain.doFilter(req, res);
        } finally {
            // add after the handler, MDC still populated on the same thread
            String traceId = MDC.get("traceId");
            String spanId  = MDC.get("spanId");
            if (traceId != null) res.setHeader("Trace-Id", MDC.get("traceId"));
            if (spanId  != null) res.setHeader("Span-Id", MDC.get("spanId"));
        }
    }
}
