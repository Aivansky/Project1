<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	<%
	String type = request.getParameter("type");
	%>
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
            			Add New Reimbursement
            		</c:if>
					</h2>
				</caption>

				<c:if test="${riemb!= null}">
					<input type="hidden" name="id" value="<c:out value='${riemb.id}' />" />
				</c:if>
				<c:if test="${riemb!= null}">
						<input type="hidden" name="status" value="0"/>${riemb.reimb_status}
				</c:if>



				<fieldset class="form-group">
					<label>Amount</label> <input type="number"
						value="<c:out value='${riemb.amount}' />" class="form-control"
						name="amount" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Description</label> <input type="text"
						value="<c:out value='${riemb.description}' />" class="form-control"
						name="description" required="required">
				</fieldset>
		<%--		
				<fieldset class="form-group">
					<label>type</label> <input type="number"
					 value='${riemb.type}' 
					 name="type">
				</fieldset>

				<fieldset class="form-group">
					<label>Reimbursement Type</label> 
		 	<br>
		 	       <label for="lodging">Lodging</label> <input type="radio"
		 	        value='${riemb.type}' id="lodging" 
		 	        name="type" >
  		 </fieldset>
            <br><input type="radio" id="travel" name="type" value='${riemb.type}'>
            <label for="travel">Travel</label>
            <br><input type="radio" id="food" name="type" value='${riemb.type}'>
            <label>Food</label>
            <br><input type="radio" id="other" name="type" value='${riemb.type}'>
            <label for="other">Other</label><br><br>
            
    
					</fieldset>
		
						<td><a href="edit?id=<c:out value='${riem.id}' />">Lodging</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="deny?id=<c:out value='${riem.id}' />">Travel</a></td>
										<td><a href="approve?id=<c:out value='${riem.id}' />">Food</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="deny?id=<c:out value='${riem.id}' />">Other</a></td>
					
		--%>	
		    <br>
		    <tr>
		    <td>Type</td>
		    <br>    <br>
		    <td>
		    	<input type="radio" name="type" value="Lodgingdsfsd"/>Lodging
		    	${riemb.type}
            </td>
             <td>
		    	<input type="radio" name="type" value="Lodgingdsfsd"/>Travel
		    	${riemb.type}
            </td>
             <td>
		    	<input type="radio" name="type" value="Lodgingdsfsd"/>Food
		    	${riemb.type}
            </td>
             <td>
		    	<input type="radio" name="type" value="Lodgingdsfsd"/>Other
		    	${riemb.type}
            </td>
            <br>
		</tr>
		
		
				<button type="submit" class="btn btn-success">Save</button>
			
			</div>
		</div>
	</div>
</body>
</html>