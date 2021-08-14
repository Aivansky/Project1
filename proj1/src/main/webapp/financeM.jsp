<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #20B2AA">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Reimbursement App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">reimbursements</a></li>
			</ul>
		</nav>
	</header>
			
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${riemb != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${riemb == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${riemb != null}">
            			Edit Reimbursement
            		</c:if>
						<c:if test="${riemb == null}">
            			Approve/Deny reimbursements
            		</c:if>
					</h2>
					<div class="container text-left">
	<a href="<%=request.getContextPath()%>/menu" class="btn btn-success">Main Menu</a>
	</div>
				</caption>
		<%-- 
				<fieldset class="form-group">
					<label>enter id</label> <input type="number"
					 value='${riemb.id}' 
					 name="type">
				</fieldset>

				
  		 <fieldset>
            <br><input type="radio" id="travel" name="type" value='${riemb.type}'>
            <label for="travel">Approve</label>
            <br><input type="radio" id="food" name="type" value='${riemb.type}'>
            <label>Deny</label>
		</fieldset>
			--%>
					<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Amount</th>
						<th>Description</th>
						<th>Reimbursement Type</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="riem" items="${listRiem}">

						<tr>
							<td><c:out value="${riem.id}" /></td>
							<td><c:out value="${riem.amount}" /></td>
							<td><c:out value="${riem.description}" /></td>
							<td><c:out value="${riem.type}" /></td>
							<td><a href="approve?id=<c:out value='${riem.id}' />">Approve</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="deny?id=<c:out value='${riem.id}' />">Deny</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
			
			</div>
		</div>
	</div>
</body>
</html>