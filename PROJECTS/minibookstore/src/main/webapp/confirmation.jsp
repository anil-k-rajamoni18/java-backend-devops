<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="css/styles.css" />
    <title>Order Confirmation</title>
</head>
<body>
<div class="container">
    <h2>Order Confirmation</h2>
    <p><%= request.getAttribute("message") %></p>
    <a href="home"><button>Back to Bookstore</button></a>
</div>
</body>
</html>
