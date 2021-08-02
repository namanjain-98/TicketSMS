<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Track Ticket</title>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href='<c:url value="/resource/css/tables.css" />'>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menu.jsp" />

	<div class="container" style="text-align: center;">

		<h2>TICKET SUPPORT SYSTEM - TRACK TICKET</h2>

		<div class="panel panel-custom">
			<div class="panel-heading">
				<div class="panel-title" align=center>Track Ticket</div>
			</div>

			<div class="panel-body">

				<form class="form-horizontal" 
					action='<c:url value="/ticket/trackTicket" />' method="post">
							
						<div class="form-group" style="height:50px;margin-top:20px;">
							<label for="code" class="col-md-4 control-label">Enter the Ticket Code</label>
							<div class="col-md-4">
								<input type="text" name="code" class="form-control" />
							</div>
						</div>
						
						
						<div class="form-group" style="height:50%;">
							<div class="col-md-offset-4 col-md-4">
								<button class="btn btn-primary btn-md btn-block">Track</button>
							</div>
						</div>
						
				</form>
			</div>
			
		</div>





	</div>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />


	<jsp:include page="/WEB-INF/views/common/scripts.jsp" />
</body>
</html>