<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<style>
.navbar {
	margin: 0;
	border-radius: 0;
}

.navbar-custom {
	background-color: #2b2a47;
}

.navbar-custom .navbar-brand , .navbar-custom .nav-link{
	color: white;
}

.navbar-custom .nav-item:hover .nav-link{
	color:  #2b2a47;
}

</style>

<nav class="navbar navbar-custom">
	<div class="container-fluid">
		
		
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href='<c:url value="/" />'>LOGO</a>
		</div>
		
		
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li class="nav-item active"><a class="nav-link" href='<c:url value="/" />'>Home</a></li>

				<security:authorize access="hasRole('USER')">
					<li class="nav-item active"><a class="nav-link" href='<c:url value="/ticket/track" />'>Track</a></li>
					<li class="nav-item active"><a class="nav-link" href='<c:url value="/ticket/createTicket" />'>Create</a></li>
				</security:authorize>

				<security:authorize access="hasRole('ADMIN')">
					<li class="nav-item active"><a class="nav-link" href='<c:url value="/employee/list" />'>Employees</a></li>
				</security:authorize>

			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="authenticated">
					<li class="nav-item active"><a class="nav-link" href='<c:url value="/ticket/listTickets" />'>Dashboard</a></li>
				</security:authorize>

				<security:authorize access="authenticated" var="authenticated" />

				<c:choose>
					<c:when test="${authenticated}">
						<li class="nav-item active"><a class="nav-link" href="<c:url value="/logout" />"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
					</c:when>

					<c:otherwise>
						<li class="nav-item active"><a class="nav-link" href="<c:url value="/login" />"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
					</c:otherwise>
				</c:choose>
			</ul>	
			
		</div>


	</div>
</nav>