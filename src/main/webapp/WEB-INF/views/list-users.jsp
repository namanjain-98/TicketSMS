<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1" isELIgnored="false" %>
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
  <div class="col-md-offset-1 col-md-10">
   <h2>TICKET SUPPORT SYSTEM - USERS</h2>
   <hr />

   <input type="button" value="Add User"
    onclick="window.location.href='add'; return false;"
    class="btn btn-primary" />
    <br/><br/>
   <div class="panel panel-info">
    <div class="panel-heading">
     <div class="panel-title">User List</div>
    </div>
    <div class="panel-body">
     <table class="table table-striped table-bordered">
      <tr>
       <th>First Name</th>
       <th>Last Name</th>
       <th>Email</th>
       <th>Action</th>
      </tr>


      <c:forEach var="tempUser" items="${Users}">

      
       <c:url var="updateLink" value="/user/updateForm">
        <c:param name="userId" value="${tempUser.id}" />
       </c:url>


       <c:url var="deleteLink" value="/user/delete">
        <c:param name="userId" value="${tempUser.id}" />
       </c:url>


       <tr>
        <td>${tempUser.firstName}</td>
        <td>${tempUser.lastName}</td>
        <td>${tempUser.username}</td>

        <td>
        <a href="${updateLink}">Update</a>
        <a href="${deleteLink}"
         onclick="if (!(confirm('Are you sure you want to delete this user?'))) return false">Delete</a>
        </td>

       </tr>

      </c:forEach>

     </table>

    </div>
   </div>
  </div>
 
 </div>
 
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
 <jsp:include page="/WEB-INF/views/common/scripts.jsp"/>
 
</body>
</html>