package com.minibookstore.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/books")
public class BookListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> books = new ArrayList<>();
        books.add("The Alchemist");
        books.add("Atomic Habits");
        books.add("Clean Code");
        books.add("Java: The Complete Reference");

        request.setAttribute("books", books);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

