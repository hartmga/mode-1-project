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
	<h3>Update Product #${id} Quantity</h3>
	<strong>Product name: </strong><span>${product.getName()}</span> <br>
	<strong>Product Price: </strong><span>$${String.format("%.2f", product.getPrice())}</span> <br>
	<form:form method="POST" action="/products/quant/${id}" modelAttribute="product">
		<label for="quantity">Quantity: </label><form:input type="number" min="0" id="quantity" path="quantity"/> <form:errors path="quantity" cssClass="error"/> <br>
		<input type="submit" value="update"/>
	</form:form>
	<a href="/products">cancel</a>
</body>
</html>