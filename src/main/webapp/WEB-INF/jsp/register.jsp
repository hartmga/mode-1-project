<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link href="/css/error.css" rel="stylesheet">
<link href="/css/products.css" rel="stylesheet">
</head>
<body>
	<div align="center">
		<h1>Create New User</h1>
		<div class="error">${error}</div>
		<form method="POST" action="/register">
			<label for="uname"><strong>Username: </strong></label>
			<input type="text" id="uname" name="username"/>
			<br>
			<br>
			<label for="pwd"><strong>Password: </strong></label>
			<input type="password" id="pwd" name="password"/>
			<br>
			<br>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input type="submit"/>
		</form>
		<br>
		<br>
		<p>Existing users: <a href="/login">login</a></p>
	</div>
</body>
</html>