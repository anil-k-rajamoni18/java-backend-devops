<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.minibookstore.model.Book" %>
<html>
<head>
    <link rel="stylesheet" href="css/styles.css" />
    <title>Your Cart</title>
</head>
<body>
<div class="container">
<h2>Your Cart 🛒</h2>

<%
    List<Book> cart = (List<Book>) session.getAttribute("cart");
    if (cart != null && !cart.isEmpty()) {
%>
    <ul>
        <% for (Book b : cart) { %>
            <li><%= b.getTitle() %> - $<%= b.getPrice() %></li>
        <% } %>
    </ul>
<% if (cart != null && !cart.isEmpty()) { %>
    <form action="checkout" method="post">
        <button type="submit">Checkout ✅</button>
    </form>
<% } %>
<%
    } else {
%>
    <p>Your cart is empty.</p>
<%
    }
%>
<a href="home"><button>Back to Home</button></a>

<% if (session.getAttribute("user") != null) { %>
    <p>
        Logged in as <strong><%= session.getAttribute("user") %></strong> |
        <a href="logout"><button>Logout</button></a>
    </p>
<% } %>
</div>
</body>
</html>
