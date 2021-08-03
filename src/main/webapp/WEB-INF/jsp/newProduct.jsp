<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="/css/error.css" rel="stylesheet">
<link href="/css/products.css" rel="stylesheet">
</head>
<body>
	<div align="center">
		<h3>Create New Product</h3>
		<form:form method="POST" action="/products" modelAttribute="product">
			<table border=0 cellpadding=10>
				<tr>
					<td> <label for="name"> Name: </label> </td>
					<td> <form:input id="name" path="name"/> <form:errors path="name" cssClass="error" /> </td>
				</tr>
				<tr>
					<td> <label for="brand"> Brand: </label> </td>
					<td> <form:input id="brand" path="brand"/> <form:errors path="brand" cssClass="error" /> </td>
				</tr>
				<tr>
					<td> <label for="madein"> Made In: </label> </td>
					<td> <form:input id="madein" path="madein"/> <form:errors path="madein" cssClass="error" /> </td>
				</tr>
				<tr>
					<td> <label for="price">Price: $</label> </td>
					<td> <form:input type="number" step="0.01" min="0" id="price" path="price"/> <form:errors path="price" cssClass="error" /> </td>
				</tr>	
				<tr>
					 <td> <label for="quant">Quantity: </label> </td>
					 <td> <form:input type="number" min="0" id="quant" path="quantity" /> <form:errors path="quantity" cssClass="error" /> </td>
				</tr>
			</table>
			<input type="submit" value="add"/>
		</form:form>
		<br>
		<a href="/products">cancel</a>
	</div>
</body>
</html>