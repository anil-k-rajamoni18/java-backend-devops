package com.minibookstore.servlet;

import com.minibookstore.dao.BookDAO;
import com.minibookstore.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private static final Logger logger =  Logger.getLogger("admin");

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Book> books = BookDAO.getAllBooks();
        logger.info("AdminServlet: doGet(): Fetched all books from database, redirecting to admin.jsp");
        request.setAttribute("books", books);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            double price = Double.parseDouble(request.getParameter("price"));

            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setPrice(price);

            BookDAO.addBook(book);

        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            BookDAO.deleteBook(id);
        }

        response.sendRedirect("admin"); // Refresh
    }
}


