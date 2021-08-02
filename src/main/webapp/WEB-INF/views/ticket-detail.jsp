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

	<c:url var="changeStatusLink" value="changeStatus">
		<c:param name="ticketId" value="${ticket.id}" />
	</c:url>


	<div class="container">
		<div>
			<h2 class="text-center">TICKET SUPPORT SYSTEM - TRACK TICKET</h2>
			<div class="panel panel-custom">
				<div class="panel-heading">
					<div class="panel-title" align=center>Ticket Details</div>
				</div>


				<div class="panel-body">

					<label for="ticketnumber" class="col-md-3 control-label">Ticket
						Number</label>
					<div class="col-lg-6">${ticket.code }</div>

					<div>${ticket.ticketStatus }</div>
					<br> <label for="ticketsubject" class="col-md-3 control-label">Subject</label>

					<div>${ticket.ticketSubject}</div>
					<br> <label for="ticketnumber" class="col-md-3 control-label">User</label>
					<div class="col-lg-6">${ticket.createdBy}</div>

					<div>
						<a href='<c:url value="${changeStatusLink}" />'><button
								class="btn btn-primary">Change Status</button></a>
					</div>
					<br>

				</div>
			</div>
		</div>
	



	
		<div class="panel panel-custom">
			<div class="panel-heading">
				<div class="panel-title">Conversations</div>
			</div>
			<div class="panel-body">


				<c:forEach var="tempConversation" items="${conversations}">

					<c:url var="downloadLink" value="downloadAttachment">
						<c:param name="conversationId" value="${tempConversation.id}" />
					</c:url>

					<ul class="list-group" style="margin-bottom:60px;">
						<li class="list-group-item">Message :
							${tempConversation.message}</li>
						<li class="list-group-item">Attachment :
							${tempConversation.attachment.fileName}</li>
						<li class="list-group-item"><a
							href='<c:url value="${downloadLink}" />'><button
									class="btn btn-primary btn-md">Download Attachment</button></a></li>
						<li class="list-group-item pull-right">Posted By :
							${tempConversation.createdBy}</li>
					</ul>
				</c:forEach>

				<c:choose>

					<c:when
						test='${ticket.ticketStatus=="OPEN" || ticket.ticketStatus=="REOPENED"}'>

						<form:form action="${ticketId}/addConversation"
							cssClass="form-horizontal" method="post"
							modelAttribute="conversationDto" enctype="multipart/form-data">


							<form:hidden path="id" />

							<div class="form-group">
								<label for="message" class="col-md-3 control-label">
									Message</label>
								<div class="col-md-9">
									<form:input path="message" cssClass="form-control" />
								</div>
							</div>


							<form:hidden path="attachment.id" />


							<div class="form-group">
								<label for="attachmentFile" class="col-md-3 control-label">Attachment
									File</label>
								<div class="col-md-9">
									<form:input type="file" path="attachmentFile"
										cssClass="form-control" />
								</div>
							</div>


							<div class="form-group">
								<div class="col-md-offset-3 col-md-9">
									<form:button cssClass="btn btn-primary">Submit</form:button>
								</div>
							</div>

						</form:form>

					</c:when>

				</c:choose>


			</div>
		</div>
		
	</div>


<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	
	<jsp:include page="/WEB-INF/views/common/scripts.jsp" />
</body>
</html>

