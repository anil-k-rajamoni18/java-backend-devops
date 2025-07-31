package com.demo.servlet;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        // Hardcoded credentials
        if ("admin".equals(user) && "password123".equals(pass)) {
            // Redirect to welcome page
            response.sendRedirect("welcome.html");
        } else {
            // Return to login with error
            response.setContentType("text/html");
            response.getWriter().println("<script>alert('Invalid credentials!'); window.location.href='login.html';</script>");
        }
    }
}
