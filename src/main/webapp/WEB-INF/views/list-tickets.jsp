<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

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
			<h2 style="text-align: center">TICKET SUPPORT SYSTEM - DASHBOARD</h2>
			<hr />



		
				<div class="row">
					<div class="col">
						<h2>Open Tickets = ${openTickets}</h2>
					</div>
					<div class="col">
						<h2>Close Tickets = ${closeTickets}</h2>
					</div>
					<div class="col">
						<h2>Total Tickets = ${openTickets + closeTickets}</h2>
					</div>
				</div>
		




		
					<table class="table table-striped table-bordered table-custom">
						<tr>
							<th class="th-custom">TICKET NUMBER</th>
							<th class="th-custom">TICKET SUBJECT</th>
							<th class="th-custom">TICKET STATUS</th>
							<th class="th-custom">ACTION</th>
						</tr>


						<c:forEach var="tempTicket" items="${Tickets}">


							<c:url var="deleteLink" value="/ticket/deleteTicket">
								<c:param name="ticketId" value="${tempTicket.id}" />
							</c:url>

							<c:url var="detailLink" value="/ticket/ticketDetail">
								<c:param name="ticketId" value="${tempTicket.id}" />
							</c:url>


							<tr>
								<td class="td-custom">${tempTicket.code}</td>
								<td class="td-custom">${tempTicket.ticketSubject}</td>
								<td class="td-custom">${tempTicket.ticketStatus}</td>

								<td class="td-custom"><a href="${deleteLink}"
									onclick="if (!(confirm('Are you sure you want to delete this ticket?'))) return false">Delete</a>
									<a href="${detailLink}">Track</a></td>

							</tr>

						</c:forEach>

					</table>

				</div>
				
			</div>
	

	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<jsp:include page="/WEB-INF/views/common/scripts.jsp"/>

</body>
</html>
