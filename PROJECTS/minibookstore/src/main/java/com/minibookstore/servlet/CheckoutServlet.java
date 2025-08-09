package com.minibookstore.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("cart") == null) {
            response.sendRedirect("home");
            return;
        }

        // Optionally: Save order to database here

        // Clear the cart
        session.removeAttribute("cart");

        // Display confirmation
        request.setAttribute("message", "Thank you for your purchase! Your order has been placed. ✅");
        request.getRequestDispatcher("confirmation.jsp").forward(request, response);
    }
}

