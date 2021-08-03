<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="/css/error.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
	<h3>Update Product #${id}</h3>
	<form:form method="POST" action="/products/${id}" modelAttribute="product">
		<table border=0 cellpadding=10>
			<tr>
				<td> <label for="name"> Name: </label> </td>
				<td> <form:input id="name" path="name"/> <form:errors path="name" cssClass="error" /> </td>
			</tr>
			<tr>
				<td> <label for="price">Price: $</label> </td>
				<td> <form:input type="number" step="0.01" min="0" id="price" path="price"/> <form:errors path="price" cssClass="error" /> </td>
			</tr>	
			<tr>
				 <td> <label for="quant">Quantity: </label> </td>
				 <td> <form:input type="number" min="0" id="quant" path="quantity" /> <form:errors path="quantity" cssClass="error" /> </td>
			</tr>
			<tr>
				<td> <input type="submit" value="add"/> </td>
			</tr>
		</table>
	</form:form>
	<a href="/products">cancel</a>
</body>
</html>