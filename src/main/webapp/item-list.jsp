<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Kottu Labs</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" >
</head>
<body style="background-image: url('https://images.unsplash.com/photo-1620296595801-3cd364a12807?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=923&q=80'); background-repeat: none; background-size: cover">
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark bg-gradient">
		    <div class="container-fluid">
		      <a class="navbar-brand ps-4" href="#">Kottu Labs</a>
		      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
		        <span class="navbar-toggler-icon"></span>
		      </button>
		      <div class="collapse navbar-collapse d-flex justify-content-center" id="navbarNavAltMarkup">
		        <div class="navbar-nav">
		          <a class="nav-link px-5 border-end border-2" href="<%=request.getContextPath()%>/list">Item List</a>
		          <a class="nav-link px-5 border-end border-2" >Order Now</a>
		          <a class="nav-link px-5 border-end border-2" >About Us</a>
		        </div>
		      </div>
		    </div>	
		</nav>
	</header>
	
	<div class="row mx-5 mt-5 bg-light" style="box-shadow: 0px 0px 5px 10px rgba(255, 255, 255, 1), 10px 6px 100px 10px rgba(255, 255, 255, 0.19)">
		<div class="container m-5">
			<h3 class="text-cnter">Item List</h3>
			<hr>
			<div class="container test-left">
				<a href="<%=request.getContextPath()%>/new" class="btn btn-dark">Add Item</a>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Item Code</th>
						<th>Name</th>
						<th>Price</th>
						<th>Type</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${listItem}">
						<tr>
							<td><c:out value="${item.itemCode}"/></td>
							<td><c:out value="${item.name}"/></td>
							<td><c:out value="${item.price}"/></td>
							<td><c:out value="${item.type}"/></td>
							<td>
								<a href="edit?itemCode=<c:out value='${item.itemCode}'/>" class="btn btn-warning">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="delete?itemCode=<c:out value='${item.itemCode}'/>" class="btn btn-danger">Delete</a>	
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>






<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>