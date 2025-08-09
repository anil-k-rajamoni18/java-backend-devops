<html>
<head>
    <link rel="stylesheet" href="css/styles.css" />
</head>
<body>
<form action="login" method="post">
    <h2>Login</h2>

    <% if (request.getAttribute("error") != null) { %>
        <div style="color: red;">
            <%= request.getAttribute("error") %>
        </div>
    <% } %>

    Username: <input name="username"/><br/>
    Password: <input type="password" name="password"/><br/>
    <button type="submit">Login</button>
</form>
</body>
</html>