<%@page import="com.gianmarco.merletti.progetto_ispw.logic.view.SessionView"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.util.LevelEnum"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.util.CityEnum"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.bean.UserBean"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<%
	String firstName = request.getParameter("inputFirstName");
	String lastName = request.getParameter("inputLastName");
	String username = request.getParameter("inputUsername");
	String password = request.getParameter("inputPassword");
	String city = request.getParameter("inputCity");
	String level = request.getParameter("inputLevel");

	if (firstName != null && lastName != null && username != null && password != null &&
			city != null && level != null) {
		UserBean userBean = new UserBean();
		userBean.setFirstName(firstName);
		userBean.setLastName(lastName);
		userBean.setUsername(username);
		userBean.setPassword(password);
		userBean.setCity(CityEnum.valueOf(city.toUpperCase()));
		userBean.setLevel(LevelEnum.valueOf(level.toUpperCase()));

		if (new SystemFacade().signupUser(userBean)) {
			SessionView.setUsername(userBean.getUsername());
			SessionView.setCityEnum(userBean.getCity());
			SessionView.setLevelEnum(userBean.getLevel());
			response.sendRedirect("home_user.jsp");
		}
	}

	List<String> cityList = Stream.of("ROMA", "MILANO", "TORINO", "ALATRI")
		.collect(Collectors.toList());
	List<String> levelList = Stream.of("BEGINNER", "INTERMEDIATE", "PRO")
		.collect(Collectors.toList());
%>

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<link rel="stylesheet" href="style/background.css">

<title>TogetherRun</title>
</head>

<body>
	<div class="container-fluid h-100" id="login-bg">
		<div class="container w-25 p-3 valign-center" id="signup-box">
			<div class="container">
				<div class="row">
					<div class="col-sm" style="padding:10px;">
						<h5 id="welcome" align="center">Registration</h5>
					</div>
				</div>
				<form id="signupForm" action="signup.jsp" method="GET">
					<div class="row">
						<div class="col">
							<div class="form-group">
								<input type="text" class="form-control w-100" name="inputFirstName"
									placeholder="First name">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div class="form-group">
								<input type="text" class="form-control w-100" name="inputLastName"
									placeholder="Last name">
							</div>
						</div>
					</div>
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
							<div class="form-group">
								<select
									required class="form-control" name="inputCity"
									id="inputCityId">
										<%	for (String cityItem : cityList) {	%>
									<option>
										<%		out.println(cityItem);	%>
									</option>
											<%	}	%>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col">
							<div class="form-group">
								<label for="inputLevelId">Level</label> <select
									required class="form-control" name="inputLevel"
									id="inputLevelId">
										<%	for (String levelItem : levelList) {	%>
									<option>
										<%		out.println(levelItem);	%>
									</option>
											<%	}	%>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm">
							<button type="button" class="btn btn-secondary w-100" style="margin-bottom:5px;" onclick="clearValues()">Clear fields</button>
							<button type="submit" class="btn btn-primary w-100" style="margin-bottom:5px;">Submit</button>
							<button type="button" class="btn btn-outline-danger w-100" onclick="location.href='login.jsp';">Back</button>
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

	<script type="text/javascript">

		function clearValues() {
			document.getElementById('signupForm').reset();
		}

	</script>

</body>
</html>