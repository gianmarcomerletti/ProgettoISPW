<%@page import="java.util.Base64"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.bean.UserBean"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.bean.ReviewBean"%>
<%@page import="java.util.List"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.util.Rating"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.view.SessionView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<%
	String action = request.getParameter("btnAction");
	String requestId = request.getParameter("request");
	String selected = request.getParameter("selected");

	UserBean currentUser = new SystemFacade().getUserData(SessionView.getUsername());
	List<ReviewBean> reviews = new SystemFacade().getMyReviews();
%>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="style/background.css">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">


<title>TogetherRun</title>
</head>

<body>
	<!--  Navbar -->
	<nav id="navbar" class="navbar navbar-dark bg-primary" aria-label="nav_profile">
		<ul class="nav navbar-nav navbar-left">
			<li><span class="navbar-brand mb-0 h1">TogetherRun</span></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><span class="navbar-brand mb-0 h1">
			<% out.println(SessionView.getUsername()
					+ "  " + Rating.getStringRating(new SystemFacade().getUserRating(SessionView.getUsername())));	%>
			</span>
			<span class="navbar-brand mb-0 h1" id="label-level">
			<% out.println(SessionView.getLevelEnum());	%>
			</span></li>
		</ul>
	</nav>
	<div class="d-flex">
		<!-- Sidebar -->
		<div class="bg-light border-right" id="sidenav">
			<div class id="sidenav-heading" >Menu</div>
			<div class="list-group list-group-flush">
				<a href="home_user.jsp" class="list-group-item list-group-item-action bg-light">Map</a>
				<a href="all_events.jsp" class="list-group-item list-group-item-action bg-light">All Events</a>
				<a href="events.jsp" class="list-group-item list-group-item-action bg-light">My Events</a>
				<a href="requests.jsp" class="list-group-item list-group-item-action bg-light">My Requests</a>
			</div>
		</div>

		<!-- Page Content -->
		<div id="page-content">
			<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom" aria-label="nav2_profile">
				<form class="form-inline ml-auto mr-1">
					<a href="profile.jsp" class="btn btn-sm btn-outline-primary ml-auto mr-1"
						role="button"> Profile
					</a>
					<a href="logout.jsp" class="btn btn-sm btn-danger ml-auto mr-1"
						role="button"> Logout
					</a>
				</form>
			</nav>

			<div class="container-fluid">
				<div class="row">
    				<div class="col">
      					<h1>PROFILE</h1>
   				 	</div>
   				 </div>
   				 <div class="row">
    				<div class="col-sm-2">
      					<h5>First name</h5>
    				</div>
    				<div class="col">
      					<% out.println(currentUser.getFirstName()); %>
    				</div>
    				<div class="col-sm-2">
      					<h5>Last name</h5>
    				</div>
    				<div class="col">
      					<% out.println(currentUser.getLastName()); %>
    				</div>
  				</div>
  				<div class="row">
    				<div class="col-sm-2">
      					<h5>Username</h5>
    				</div>
    				<div class="col">
      					<% out.println(currentUser.getUsername()); %>
    				</div>
  				</div>
  				<div class="row">
    				<div class="col-sm-2">
      					<h5>City</h5>
    				</div>
    				<div class="col">
      					<% out.println(currentUser.getCity().toString()); %>
    				</div>
    				<div class="col-sm-2">
      					<h5>Level</h5>
    				</div>
    				<div class="col">
      					<% out.println(currentUser.getLevel().toString()); %>
    				</div>
  				</div>

  				<hr>

  				<div class="row">
    				<div class="col ml-4 mb-2">
      					<strong>Reviews</strong>
   				 	</div>
   				 </div>


  				<div class="container-fluid">
  					<ul class="list-group">
						<!-- Single Row for JSP -->
						<%
						for (ReviewBean review : reviews) {
						%>
						<li class="list-group-item">
							<div class="container-fluid">
								<div class="row">
									<div class="col-sm-auto">
										<img src="data:image/jpeg;base64, <%out.println(new String(Base64.getEncoder().encodeToString(review.getImageBean())));%>"
											width="150" height="auto" alt="">
									</div>
									<div class="col-lg">
										<div class="row">
											<h5><% out.println(review.getEventBean().getEventTitle()); %></h5>
										</div>
										<div class="row-lg">
											<% out.println(review.getTextBean()); %>
										</div>
										<div class="row-sm" style="position:absolute; bottom: 0px; width: 100%;">
											<strong><% out.println("Review from " + review.getUserBean().getUsername()); %></strong>
										</div>
									</div>

									<div class="col-sm-auto">
										<div class="row justify-content-end mt-2">
											<div class="form-group">
											<label class="control-label" for="rating">
	    										<input type="hidden" id="selected_rating" name="selected_rating" value="<%out.print(review.getValueBean());%>" required="required">
	   										</label>
	   										<span class="field-label-header">Rate</span><br>
	   										<h2 class="bold rating-header" style="">
	    										<span class="selected-rating"><%out.println(review.getValueBean());%></span><small> / 5</small>
	    									</h2>
	    								<%	for (int i=0; i<review.getValueBean(); i++)	{ %>
	    										<button type="button" style="pointer-events:none;" class="btnrating btn btn-warning btn-lg">
	        										<i class="fa fa-star" aria-hidden="true"></i>
	    										</button>
	    								<%	} %>
	    								<%	for (int i=review.getValueBean(); i<5; i++)	{ %>
	    										<button type="button" style="pointer-events:none;" class="btnrating btn btn-default btn-lg">
	        										<i class="fa fa-star" aria-hidden="true"></i>
	    										</button>
	    								<%	} %>

											</div>
										</div>

									</div>

								</div>

							</div>
						</li>
						<%
							}
						%>
						<!-- End of Single Row for JSP  -->
					</ul>
  				</div>
			</div>
		</div>
</body>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>


<script>

	function acceptRequest(reqId) {
		document.getElementById("btnActionId").value = "ACCEPT";
		document.getElementById("requestId").value = reqId;
	}

	function rejectRequest(reqId) {
		document.getElementById("btnActionId").value = "REJECT";
		document.getElementById("requestId").value = reqId;
	}

</script>

</html>