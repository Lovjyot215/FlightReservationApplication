<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login</title>
</head>
<body>
<h2>Login Page</h2>
<form action="login" method="post">
<pre>
Username: <input type="text" name="username"/>
Password: <input type="password" name="password"/>
<input type="submit" value="login"/>
${msg}
</pre>
</form>

</body>
</html>