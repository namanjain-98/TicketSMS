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
   <h2 class="text-center">TICKET SUPPORT SYSTEM - ADD TICKET</h2>
   <div class="panel panel-info panel-custom">
    <div class="panel-heading">
     <div class="panel-title" align=center>Add Ticket</div>
    </div>
    <div class="panel-body">
     <form:form action="saveTicket" cssClass="form-horizontal"
      method="post" modelAttribute="ticketDto" enctype="multipart/form-data">

      <form:hidden path="id" />
      
  

      <div class="form-group">
       <label for="ticketsubject" class="col-md-3 control-label">Ticket Subject</label>
       <div class="col-md-9">
        <form:input path="ticketSubject" cssClass="form-control" />
       </div>
      </div>
   
      
      <form:hidden path="ticketStatus" value="OPEN" />  

		      <div class="form-group">
       <label for="message" class="col-md-3 control-label">Message</label>
       <div class="col-md-9">
        <form:input path="message" cssClass="form-control" />
       </div>
      </div>
		
		      <div class="form-group">
       <label for="attachment" class="col-md-3 control-label">Attachment</label>
       <div class="col-md-9">
        <form:input type="file" path="attachmentFile" cssClass="form-control" />
       </div>
      </div>

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

