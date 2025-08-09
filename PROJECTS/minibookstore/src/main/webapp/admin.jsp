<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.minibookstore.model.Book" %>
<html>
<head>
    <link rel="stylesheet" href="css/styles.css" />
</head>
<body>
<div class="container">
<h2>Admin Panel</h2>
<form method="post" action="admin">
    <input type="hidden" name="action" value="add"/>
    Title: <input name="title"/>
    Author: <input name="author"/>
    Price: <input name="price"/>
    <button type="submit">Add Book</button>
</form>

<h3>All Books:</h3>
<% List<Book> books = (List<Book>) request.getAttribute("books"); %>
<ul>
<% for (Book b : books) { %>
    <li><%= b.getTitle() %> - $<%= b.getPrice() %>
        <form method="post" action="admin" style="display:inline;">
            <input type="hidden" name="action" value="delete"/>
            <input type="hidden" name="id" value="<%= b.getId() %>"/>
            <button type="submit">Delete</button>
        </form>
    </li>
<% } %>
</ul>

<% if (session.getAttribute("user") != null) { %>
    <p>
        Logged in as <strong><%= session.getAttribute("user") %></strong> |
        <a href="logout"><button>Logout</button></a>
    </p>
<% } %>
</div>
</body>
</html>