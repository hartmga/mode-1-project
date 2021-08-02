<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="/css/error.css" rel="stylesheet">
</head>
<body>
	<%= "/products/quant/"+request.getAttribute("id") %>
	<%= request.getAttribute("product") %>
	<h3>Update Product #${id} Quantity</h3>
	<strong>Product name: </strong><span>${product.getName()}</span> <br>
	<strong>Product Price: </strong><span>$${String.format("%.2f", product.getPrice())}</span> <br>
	<form method="POST" action="/products/quant/${id}">
		<label for="quantity"></label><input type="number" min=0 name="quant" id="quantity" value="${product.getQuantity()}"/> <br>
		<input type="submit" value="update"/>
	</form>
	<a href="/products">cancel</a>
</body>
</html>