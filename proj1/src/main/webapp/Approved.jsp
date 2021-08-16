<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Reimbursement Management Application</title>
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

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Reimbursements</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/menu" class="btn btn-success">Main Menu</a>
					<a href="<%=request.getContextPath()%>/denied" class="btn btn-success">View Denied Reimbursements</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Amount</th>
						<th>Description</th>
						<th>Reimbursement Type</th>
						<th>Time</th>
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
							<td><c:out value="${riem.time}" /></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>