package com.minibookstore.servlet;

import com.minibookstore.dao.BookDAO;
import com.minibookstore.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final Logger logger =  Logger.getLogger("home");
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Book> books = BookDAO.getAllBooks();
        request.setAttribute("books", books);
        logger.info("HomeServlet: doGet(): Fetched all books from database, redirecting to home.jsp");
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}

