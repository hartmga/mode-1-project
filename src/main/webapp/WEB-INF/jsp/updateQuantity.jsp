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
	<table border=0 cellpadding=10>
		<tr>
			<td><strong>Product name: </strong></td>
			<td>${product.getName()}</td>
		</tr>
		<tr>
			<td><strong>Product Price: </strong></td>
			<td>$${String.format("%.2f", product.getPrice())}</td>
		</tr>
		<form:form method="POST" action="/products/quant/${id}" modelAttribute="product">
			<tr>
				<td> <label for="quantity">Quantity: </label> </td>
				<td> <form:input type="number" min="0" id="quantity" path="quantity"/> <form:errors path="quantity" cssClass="error"/> </td>
			</tr>
			<tr>
				<td> <input type="submit" value="update"/> </td>
			</tr>
		</form:form>
	</table>
	<br>
	<a href="/products">cancel</a>
</body>
</html>