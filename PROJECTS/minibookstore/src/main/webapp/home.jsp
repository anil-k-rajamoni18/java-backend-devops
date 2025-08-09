<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.minibookstore.model.Book" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/styles.css" />
    <title>Browse Books</title>
</head>
<body>
<div class="container">
    <h2>Book Catalog 📚</h2>

    <%
        List<Book> books = (List<Book>) request.getAttribute("books");
        if (books != null && !books.isEmpty()) {
            for (Book b : books) {
    %>
        <div>
            <strong><%= b.getTitle() %></strong> by <%= b.getAuthor() %> - $<%= b.getPrice() %>
            <form action="add-to-cart" method="post" style="display:inline;">
                <input type="hidden" name="bookId" value="<%= b.getId() %>"/>
                <button type="submit">Add to Cart 🛒</button>
            </form>
        </div>
        <br/>
    <%
            }
        } else {
    %>
        <p>No books available.</p>
    <% } %>

    <a href="cart.jsp"><button>Go to Cart 🛒</button></a>

    <% if (session.getAttribute("user") != null) { %>
        <p>
            Logged in as <strong><%= session.getAttribute("user") %></strong> |
            <a href="logout"><button>Logout</button></a>
        </p>
    <% } %>
</div>
</body>
</html>
