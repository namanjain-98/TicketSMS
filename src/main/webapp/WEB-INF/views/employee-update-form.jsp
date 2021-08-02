<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<div class="container">
		<div class="col-md-offset-2 col-md-7">
			<h2 class="text-center">TICKET SUPPORT SYSTEM - ADD EMPLOYEE</h2>
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Add Employee</div>
				</div>
				<div class="panel-body">
					<form:form action="saveEmployee" cssClass="form-horizontal"
						method="post" modelAttribute="employee">

						<form:hidden path="id" />
					
						<div class="form-group">
							<label for="firstname" class="col-md-3 control-label">First
								Name</label>
							<div class="col-md-9">
								<form:input path="firstName" cssClass="form-control" />
							</div>
						</div>
						<div class="form-group">
							<label for="lastname" class="col-md-3 control-label">Last
								Name</label>
							<div class="col-md-9">
								<form:input path="lastName" cssClass="form-control" />
							</div>
						</div>

						<div class="form-group">
							<label for="email" class="col-md-3 control-label">Email</label>
							<div class="col-md-9">
								<form:input path="username" cssClass="form-control" />
							</div>
						</div>



						<div class="form-group">
							<label for="password" class="col-md-3 control-label">Password</label>
							<div class="col-md-9">
								<form:input type="password" path="password"
									cssClass="form-control" />
							</div>
						</div>

						<div class="form-group">
							<label for="DOB" class="col-md-3 control-label">Date Of
								Birth</label>
							<div class="col-md-9">							
								<form:input type="date" path="dateOfBirth" value="2021-04-15" min="1960-01-01" max="2021-12-31"
									cssClass="form-control" />
							</div>
						</div>

						<div class="form-group">
							<label for="idProof" class="col-md-3 control-label">ID Document No.</label>
							<div class="col-md-9">
								<form:input path="idProof" cssClass="form-control" />
							</div>
						</div>
						
						
							<div class="form-group">
							<label for="idProofPath" class="col-md-3 control-label">ID Proof</label>
							<div class="col-md-9">							
								<form:input type="file" path="documentPath" cssClass="form-control" />
							</div>
						</div>

		<div class="form-group">
							<label for="profileImg" class="col-md-3 control-label">Photo</label>
							<div class="col-md-9">							
								<form:input type="file" path="profileImg" cssClass="form-control" />
							</div>
						</div>

						<form:hidden path="role" value="EMPLOYEE" />



						<div class="form-group">
							<!-- Button -->
							<div class="col-md-offset-3 col-md-9">
								<form:button cssClass="btn btn-primary">Submit</form:button>
							</div>
						</div>

					</form:form>
				</div>
			</div>
		</div>
		
	</div>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<jsp:include page="/WEB-INF/views/common/scripts.jsp"/>
</body>
</html>

