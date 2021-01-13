<%@page import="com.gianmarco.merletti.progetto_ispw.logic.exception.UserNotFoundException"%>
<%@ page import="com.gianmarco.merletti.progetto_ispw.logic.view.SessionView"%>
<%@ page import="com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade"%>
<%@ page import="com.gianmarco.merletti.progetto_ispw.logic.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<%
	String username = new String();
	String password = new String();
	username = (String) request.getParameter("inputUsername");
	password = (String) request.getParameter("inputPassword");
	UserBean userBean = new UserBean();

	if (username != null && password != null && username != "" && password != "") {
		userBean.setUsername(username);
		userBean.setPassword(password);
		UserBean responseUserBean;
		try {
			responseUserBean = new SystemFacade().isSignedUp(userBean);
			SessionView.setUsername(username);
			SessionView.setCityEnum(responseUserBean.getCity());
			SessionView.setLevelEnum(responseUserBean.getLevel());
			response.sendRedirect("home_user.jsp");
		} catch (UserNotFoundException e) {
			String errMessage = "<script>alert('User not registred')</script>";
			out.println(errMessage);
		}

	}

%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<link rel="stylesheet" href="style/background.css">

<title>Login</title>
</head>

<body>
	<div class="container-fluid h-100" id="login-bg">
		<div class="container w-25 h-100 p-3 valign-center" id="login-box">
			<div class="container">
				<div class="row">
					<div class="col-sm" style="padding:50px;">
						<h1 id="welcome" align="center" style="color:white;">TogetherRun</h1>
					</div>
				</div>
				<form action="login.jsp" method="GET">
					<div class="row">
						<div class="col">
							<div class="form-group">
								<input type="text" class="form-control w-100" name="inputUsername"
									placeholder="Username">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div class="form-group">
								<input type="password" class="form-control w-100" name="inputPassword"
									placeholder="Password">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<button type="submit" class="btn btn-primary w-100" style="margin-bottom:5px;">Login</button>
							<button type="button" class="btn btn-secondary w-100" onclick="location.href='signup.jsp';">Sign up</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js" crossorigin="anonymous"></script>
	<script src="./bootstrap-italia.min.js"></script>

</body>

</html>