<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="/css/error.css" rel="stylesheet">
</head>
<body>
	<h3>Update Product #${id}</h3>
	<form:form method="POST" action="/products/${id}" modelAttribute="product">
		<%-- <form:errors path="*" element="div" cssClass="errors" /> --%>
		Name: <form:input path="name"/><form:errors path="name" cssClass="error" /> <br>
		Price: $<form:input type="number" step="0.01" min="0" path="price"/> <form:errors path="price" cssClass="error" /><br>
		Quantity: <form:input type="number" min="0" path="quantity" /> <form:errors path="quantity" cssClass="error" /><br>
		<input type="submit" value="update" />
	</form:form>
	<a href="/products">cancel</a>
</body>
</html>