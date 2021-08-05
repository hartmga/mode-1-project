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
	<div align="center">

		<h3> Currently logged in as "<%= ((com.hcl.model.MyUserDetails) request.getAttribute("user")).getUsername() %>" </h3>
		<form method="GET" action="/logout">
			<input type=submit value="logout"/>
		</form>
		
		<hr>
	
		<c:set var="isAdmin" scope="request" value="false"/>
		<sec:authorize access='hasRole("ADMIN")'>
			<c:set var="isAdmin" scope="request" value="true"/>
		</sec:authorize>
		
		<h3>All Products</h3>
	
		<table border="1" cellpadding="10">
			<thead>
				<tr>
					<th>id</th>
					<th>name</th>
					<th>brand</th>
					<th>made in</th>
					<th>price</th>
					<th>quantity</th>
					<th>total</th>
					<th>Actions</th>
				</tr>
			</thead>
			<c:forEach var="prod" items="${products}">
				<tr>
					<td><c:out value="${prod.getId()}"/></td>
					<td><c:out value="${prod.getName()}"/></td>
					<td><c:out value="${prod.getBrand()}"/></td>
					<td><c:out value="${prod.getMadein()}"/></td>
					<td><c:out value='${String.format("$%.2f", prod.getPrice())}'/></td>
					<td><c:out value="${prod.getQuantity()}"/></td>
					<td><c:out value='${String.format("$%.2f", prod.getPrice() * prod.getQuantity())}'/></td>
					<td>
						<c:choose>
							<c:when test="${isAdmin}">
								<c:set var="editFormPath" value="/updateProduct/${prod.getId()}" scope="request"/>
							</c:when>
							<c:otherwise>
								<c:set var="editFormPath" value="/updateQuantity/${prod.getId()}" scope="request"/>
							</c:otherwise>
						</c:choose>
						<a href="${editFormPath}">edit</a>
						<c:choose>
							<c:when test="${isAdmin}">
								<a href="/products/delete/${prod.getId()}">delete</a>
							</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td>
					<c:choose>
						<c:when test="${isAdmin}">
							<form method="GET" action="/addProduct">
								<input type="submit" value="+new item"/>
							</form>
						</c:when>
					</c:choose>
				</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><strong>${String.format("$%.2f", total)}</strong></td>
				<td></td>
			</tr>
		</table>
	</div>
</body>
</html>