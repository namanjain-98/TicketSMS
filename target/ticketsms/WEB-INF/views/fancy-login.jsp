<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">

<head>

<title>Login Page</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<jsp:include page="/WEB-INF/views/common/header.jsp" />


<link rel="stylesheet" href='<c:url value="/resource/css/tables.css" />'>
</head>

<body>

	<jsp:include page="/WEB-INF/views/common/menu.jsp" />

	
		<div class="container">
			<div class="col-md-offset-2 col-md-7">
				<div class="panel panel-info panel-custom" style="margin-top:100px">

					<div class="panel-heading">
						<div class="panel-title">Sign In</div>
					</div>

					<div style="padding-top: 30px" class="panel-body">

						<!-- Login Form -->
						<form action="<c:url value='/login' />" method="POST"
							class="form-horizontal">

							<!-- Place for messages: error, alert etc ... -->
							<div class="form-group">
								<div class="col-xs-15">
									<div>

										<!-- Check for login error -->

										<c:if test="${param.error != null}">

											<div class="alert alert-danger col-xs-offset-1 col-xs-10">
												Invalid username and password.</div>

										</c:if>

										<!-- Check for logout -->

										<c:if test="${param.logout != null}">

											<div class="alert alert-success col-xs-offset-1 col-xs-10">
												You have been logged out.</div>

										</c:if>

									</div>
								</div>
							</div>

					<!-- 		<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> 
									<label for="role" class="col-md-3 control-label">LOGIN AS</label>  
									<input list="roles"
									name="role">
								<datalist id="roles">
									<option value="USER">
									<option value="EMPLOYEE">
									<option value="ADMIN">
								</datalist>
							</div>
 -->


							<!-- User name -->
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input type="text"
									name="username" placeholder="username" class="form-control">
							</div>

							<!-- Password -->
							<div style="margin-bottom: 25px" class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-lock"></i></span> <input type="password"
									name="password" placeholder="password" class="form-control">
							</div>



							<div style="margin-top: 10px" class="form-group">
								<div class="col-md-12 controls">
									<div class="col-md-6">
										<button type="submit" class="btn btn-primary btn-lg btn-block">Login</button>
									</div>
									<div class="col-md-6">
										<a class="btn btn-primary btn-lg btn-block"
											href="<c:url value="/user/add" />" role="button">Register</a>
									</div>
								</div>
							</div>

							<!-- I'm manually adding tokens ... Bro! -->

							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />

						</form>

					</div>

				</div>
			</div>

		</div>
			


	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<jsp:include page="/WEB-INF/views/common/scripts.jsp"/>

</body>
</html>