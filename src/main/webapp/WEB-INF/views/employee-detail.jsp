<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>TicketSMS</title>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href='<c:url value="/resource/css/tables.css" />'>

</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />

	<c:url var="downloadLink" value="downloadImage">
		<c:param name="employeeId" value="${employee.id}" />
	</c:url>

	<c:url var="showLink" value="showImage">
		<c:param name="employeeId" value="${employee.id}" />
	</c:url>

	<div class="container">

		<h2 class="text-center">TICKET SUPPORT SYSTEM - EMPLOYEE DETAIL</h2>
		<%--  <div class="panel panel-info">
    <div class="panel-heading">
     <div class="panel-title" align=center>Employee Details</div>
    </div>
    <div class="panel-body">
    
    <div class="col-md-12 controls">
   			  <div class="col-md-6">
   			<div class="card" style="width: 18rem;">
  <br>
   <h3>Profile Photo</h3>
   <br>
  <div class="card-body">
  <br>
  <h4 class="card-title">  <strong>EMPLOYEE NAME</strong> </h4>
  </div>
  <ul class="list-group list-group-flush">
    <li class="list-group-item">EMPLOYEE CODE</li>
    <li class="list-group-item">DATE OF BIRTH</li>
    <li class="list-group-item">USERNAME</li>
     <li class="list-group-item">ID PROOF</li>
  </ul>
  <div class="card-body">
    
    
  </div>
  
   </div> 
</div>
 			  <div class="col-md-6">
   			<div class="card" style="width: 18rem;">
  <img class="card-img-top" src="<c:url value="${showLink}" />" alt="Card image cap" width="100" height="100">
  <div class="card-body">
  <br>
    <h4> <strong>${employee.firstName} ${employee.lastName} </strong> </h4>
  </div>
  <ul class="list-group list-group-flush">
    <li class="list-group-item">${employee.employeeCode}</li>
    <li class="list-group-item">${employee.dateOfBirth}</li>
    <li class="list-group-item">${employee.username}</li>
     <li class="list-group-item">${employee.idProof}</li>
  </ul>
  <div class="card-body">
  <a href='<c:url value="${downloadLink}" />' class="card-link"><button class="btn btn-primary">Download ID Proof</button></a>
    <!-- <a href="" class="card-link">DOWNLOAD ID PROOF</a> -->
    
  </div>
  </div>
    
</div>
 </div>
   			
    </div>
   </div> --%>





		<div class="card">
			<div class="card-img"
				style="margin: auto; width: 50%; text-align: center;">

				<img class="img-responsive" src="<c:url value="${showLink}" />"
					alt="Card image cap" width="300" height="300"
					style="margin-left: auto; margin-right: auto;">


			</div>

			<div class="card-caption"
				style="margin: auto; width: 50%; text-align: center;">
				<h2>
					<strong>${employee.firstName} ${employee.lastName} </strong>
				</h2>
			</div>

			<div class="card-body"
				style="margin: auto; width: 50%; text-align: center;">

				<table class="table table-striped table-bordered table-custom">
					<tr>
						<th class="th-custom">Employee Code</th>
						<th class="th-custom">${employee.employeeCode}</th>
					</tr>
					<tr>
						<td class="td-custom">Date of Birth</td>
						<td class="td-custom">${employee.dateOfBirth}</td>
					</tr>
					<tr>
						<td class="td-custom">Username</td>
						<td class="td-custom">${employee.username}</td>
					</tr>
					<tr>
						<td class="td-custom">Id Proof</td>
						<td class="td-custom">${employee.idProof}</td>
					</tr>
					<tr>
						<td class="td-custom">Download Id Proof</td>
						<td class="td-custom"><a
							href='<c:url value="${downloadLink}" />' class="card-link"><button
									class="btn btn-primary">Download ID Proof</button></a></td>
					</tr>
				</table>




			</div>
		</div>





		

	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<jsp:include page="/WEB-INF/views/common/scripts.jsp" />

</body>
</html>

