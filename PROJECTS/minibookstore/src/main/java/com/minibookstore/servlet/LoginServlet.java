package com.minibookstore.servlet;

import com.minibookstore.dao.UserDAO;
import com.minibookstore.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger =  Logger.getLogger("login");
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDAO.getUser(username, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user.getUsername());
            session.setAttribute("role", user.getRole());
            logger.info("user found in database: user role: " + user.getRole());
            if ("admin".equals(user.getRole())) {
                response.sendRedirect("admin");
            } else {
                response.sendRedirect("home");
            }
        } else {
            logger.info("user not found in database, redirecting to login.jsp");
            request.setAttribute("error", "Invalid username or password. Not registered? <a href='register.jsp'>Click here to register</a>.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}



