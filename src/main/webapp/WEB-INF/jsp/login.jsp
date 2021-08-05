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
		<h1>Log In</h1>
		<div class="success">${msg}</div>
		<div class="error">${errorMsg}</div>
		<form method="POST" action="/login">
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
		<p>New users: <a href="/register">register</a></p>
	</div>
</body>
</html>