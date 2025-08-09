<%@ page session="true" %>
<html>
<head>
    <title>User Registration</title>
    <link rel="stylesheet" href="css/styles.css" />
</head>
<body>
<div class="container">
<h2>Create Account</h2>

<form action="register" method="post">

    <% if (request.getAttribute("error") != null) { %>
        <div style="color: red;">
            <%= request.getAttribute("error") %>
        </div>
    <% } %>

    <label for="username">Username:</label>
    <input type="text" name="username" required />

    <label for="password">Password:</label>
    <input type="password" name="password" required />

    <label for="role">Role:</label>
    <select name="role">
        <option value="user">User</option>
        <option value="admin">Admin</option> <!-- Optional -->
    </select>

    <button type="submit">Register</button>
</form>

<p>Already have an account? <a href="login.jsp">Login here</a></p>
</div>
</body>
</html>
