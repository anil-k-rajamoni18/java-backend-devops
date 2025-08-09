<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <link rel="stylesheet" href="css/styles.css" />
    <title>Online Book Store</title>
</head>
<body>
<div class="container">
    <% String user = (String) session.getAttribute("user"); %>
    <h2>Welcome to the Online Bookstore 📚</h2>
    <% if (user != null) { %>
        <p>Hello, <strong><%= user %></strong>!</p>
        <% if ("admin".equals(session.getAttribute("role"))) { %>
            <script>window.location.href = 'admin';</script>
        <% } else { %>
            <script>window.location.href = 'home';</script>
        <% } %>
    <% } %>
    <p>Choose your path:</p>
    <div class="actions">
        <a href="login.jsp"><button>Login</button></a>
        <a href="home"><button>Browse Books</button></a>
        <a href="cart.jsp"><button>View Cart</button></a>
        <a href="admin"><button>Admin Panel</button></a>
    </div>

    <% if (session.getAttribute("user") != null) { %>
        <p>
            Logged in as <strong><%= session.getAttribute("user") %></strong> |
            <a href="logout"><button>Logout</button></a>
        </p>
    <% } %>
</div>
</body>
</html>