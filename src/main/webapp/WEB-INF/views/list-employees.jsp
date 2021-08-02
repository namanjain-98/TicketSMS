<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>TicketSMS</title>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<link rel="stylesheet" href='<c:url value="/resource/css/tables.css" />'>

</head>
<body>

	<jsp:include page="/WEB-INF/views/common/menu.jsp" />

	<div class="container">
		<div style="margin: auto; text-align: center;">
			<h2>TICKET SUPPORT SYSTEM - EMPLOYEES</h2>
			<hr />

			<input type="button" value="Add Employee"
				onclick="window.location.href='add'; return false;"
				class="btn btn-primary" /> <br />
			<br />
		

					<table class="table table-striped table-bordered table-custom">
						<tr>
							<th class="th-custom">EMPLOYEE CODE</th>
							<th class="th-custom">NAME</th>
							<th class="th-custom">TOTAL TICKETS</th>
							<th class="th-custom">ACTION
						</tr>


						<c:forEach var="tempEmployee" items="${Employees}">


							<c:url var="updateLink" value="/employee/updateForm">
								<c:param name="employeeId" value="${tempEmployee.id}" />
							</c:url>


							<c:url var="deleteLink" value="/employee/delete">
								<c:param name="employeeId" value="${tempEmployee.id}" />
							</c:url>


							<c:url var="detailLink" value="/employee/detail">
								<c:param name="employeeId" value="${tempEmployee.id}" />
							</c:url>


							<tr>
								<td class="td-custom">${tempEmployee.employeeCode}</td>
								<td class="td-custom">${tempEmployee.firstName} ${tempEmployee.lastName}</td>
								<td class="td-custom">${tempEmployee.totalTickets}</td>

								<td class="td-custom"><a href="${updateLink}">Update</a> <a
									href="${deleteLink}"
									onclick="if (!(confirm('Are you sure you want to delete this employee?'))) return false">Delete</a>
									<a href="${detailLink}">View</a></td>

							</tr>

						</c:forEach>

					</table>

				</div>

	</div>

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<jsp:include page="/WEB-INF/views/common/scripts.jsp" />

</body>
</html>