package com.demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class LoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("Executing Filter: before request");
        System.out.println(String.format("URI:%s\n RemoteHost: %s", request.getRequestURI(), request.getRemoteHost()));
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Executing Filter: after request");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
