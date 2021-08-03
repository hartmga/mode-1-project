<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Products</title>
<link href="/css/products.css" rel="stylesheet">

</head>
<body>

	<c:set var="isAdmin" scope="request" value="false"/>
	<sec:authorize access='hasRole("ADMIN")'>
		<c:set var="isAdmin" scope="request" value="true"/>
	</sec:authorize>

	<div class="content">
		<h3>All Products</h3>
	
		<table border="1" cellpadding="10">
			<tr>
				<th>id</th>
				<th>name</th>
				<th>price</th>
				<th>quantity</th>
				<th>total</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="prod" items="${products}">
				<tr>
					<td><c:out value="${prod.getId()}"/></td>
					<td><c:out value="${prod.getName()}"/></td>
					<td><c:out value='${String.format("$%.2f", prod.getPrice())}'/></td>
					<td><c:out value="${prod.getQuantity()}"/></td>
					<td><c:out value='${String.format("$%.2f", prod.getPrice() * prod.getQuantity())}'/></td>
					<td>
						<c:choose>
							<c:when test="${isAdmin}">
								<a href="/updateProduct/${prod.getId()}">edit</a>
							</c:when>
							<c:otherwise>
								<a href="/updateQuantity/${prod.getId()}">edit</a>
							</c:otherwise>
						</c:choose>
						<sec:authorize access='hasRole("ADMIN")'>
							<a href="/products/delete/${prod.getId()}">delete</a>
						</sec:authorize>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td>
					<sec:authorize access='hasRole("ADMIN")'>
						<a href="/addProduct">+add new</a>
					</sec:authorize>
				</td>
				<td></td>
				<td></td>
				<td></td>
				<td>${String.format("$%.2f", total)}</td>
			</tr>
		</table>
	</div>

</body>
</html>