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
		<h2>Are you sure you want to log out?</h2>
		<form method="POST" action="/logout">
			<input type="submit" value="logout"/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
	</div>
</body>
</html>