<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Kottu Lab</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" >
</head>
<body style="background-image: url('https://images.unsplash.com/photo-1529940316268-e245e031bcd1?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80');">

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

	<div class="position-absolute top-50 start-50 translate-middle bg-light p-3 rounded" style="box-shadow: 0px 0px 5px 10px rgba(255, 255, 255, 1), 10px 6px 100px 10px rgba(255, 255, 255, 0.19)">
	    <div class="form-area d-flex justify-content-center">
	      <c:if test="${item != null}">
	      	<form method="post" action="update" id="contactform" class="row g-3">
	      </c:if>
	      <c:if test="${item == null}">
	      	<form method="post" action="insert" id="contactform" class="row g-3">
	      </c:if>
		      <div class="display-6">
		    	<c:if test="${item != null}">
		      		Edit Item
		      	</c:if>
		      	<c:if test="${item == null}">
		      		Add New Item
		      	</c:if>
		      </div>
		      
		      <c:if test="${item != null}">
		      	<div class="form-group d-none">
		           <label for="itemCode" class="form-label">Item Code</label>
		           <input type="number" class="form-control" name="itemCode" id="itemCode" value='${item.itemCode}'>
		        </div>
		      </c:if>
	
		      <div class="form-group">
		        <label for="name" class="form-label">Name</label>
		        <input type="text" class="form-control" name="name" id="name" value="<c:out value='${item.name}'/>">
		      </div>
		      <div class="form-group">
		        <label for="price" class="form-label">Price</label>
		        <input type="number" class="form-control" name="price" id="price" value="<c:out value='${item.price}'/>">
		      </div>
		      <div class="">
		        <label for="type" class="form-label">Category</label>
		        <input type="text" class="form-control" name="type" id="type" value="<c:out value='${item.type}'/>">
		      </div>
		      <div class="d-flex justify-content-center">
		        <button class="btn btn-dark submit" type="submit">Add item</button>
		      </div>
	    	</form>
	   	</div>
   	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>