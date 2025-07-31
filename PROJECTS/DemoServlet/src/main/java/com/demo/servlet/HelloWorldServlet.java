package com.demo.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;

public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");

        // Write HTML response
        PrintWriter out = response.getWriter();
        ZonedDateTime dateTime = ZonedDateTime.now();
        String timeTag = String.format("<p> current date: %s </p>", dateTime);
        out.println("<h1>Hello, World to Servlets! 🌍</h1><br>" + timeTag);
    }
}
