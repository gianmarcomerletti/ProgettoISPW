<%@page import="com.gianmarco.merletti.progetto_ispw.logic.model.Request"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.dao.RequestDAO"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.util.Status"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean"%>
<%@page import="java.util.Base64"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.exception.ReviewException"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.bean.ReviewBean"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.util.LevelEnum"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.view.SessionView"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<%
	String action = request.getParameter("btnAction");
	String requestId = request.getParameter("request");
	String selected = request.getParameter("selected");

	if (action != "" && requestId != null) {
		SystemFacade facade = new SystemFacade();
		RequestBean bean = new RequestBean();
		bean.setRequestId(Integer.parseInt(requestId));

		if (action.equalsIgnoreCase("ACCEPT")) {

			facade.acceptRequest(bean);
		}
		else if (action.equalsIgnoreCase("REJECT")) {
			facade.rejectRequest(bean);
		}
	}


	List<RequestBean> requests = new SystemFacade().getMyRequests();
%>

<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="style/background.css">

<title>TogetherRun</title>
</head>

<body>
	<!--  Navbar -->
	<nav id="navbar" class="navbar navbar-dark bg-primary">
		<ul class="nav navbar-nav navbar-left">
			<li><span class="navbar-brand mb-0 h1">TogetherRun</span></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><span class="navbar-brand mb-0 h1">
			<% out.println(SessionView.getUsername());	%>
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
			<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
				<a href="logout.jsp" class="btn btn-sm btn-danger ml-auto mr-1"
				role="button"> Logout
				</a>
			</nav>

			<div class="tab-content">
			<form action="requests.jsp" method="GET">
				<input type="hidden" name="btnAction" id="btnActionId" value="">
				<input type="hidden" name="request" id="requestId" value="">
				<table class="table table-bordered table-hover">
					<caption>My Requests</caption>
					<thead class="thead-dark">
   						<tr>
      						<th scope="col">Event</th>
      						<th scope="col">User</th>
      						<th scope="col">Status</th>
    					</tr>
  					</thead>
  					<tbody>
					<%
					for (RequestBean req : requests) {
					%>
    					<tr class="clickable-row" data-toggle="modal" data-target="#viewRequest<%out.println(req.getRequestId());%>" style="cursor:pointer">
      						<th scope="row">
      						<%
      							out.println(req.getRequestEvent().getEventTitle());
      						%>
      						</th>
      						<td>
      						<%
      							out.println(req.getRequestUser());
      						%>
      						</td>
      						<td>
      						<%
      							out.println(req.getRequestStatus());
      						%>
      						</td>
    					</tr>

    					<!-- Request Event Modal -->
						<div class="modal fade" id="viewRequest<%out.println(req.getRequestId());%>" role="dialog"  aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">

									<!-- Modal Header -->
									<div class="modal-header">
										<h3 class="modal-title">
										<% out.println(req.getRequestEvent().getEventTitle()); %>
										</h3>
										<button type="button" class="close" data-dismiss="modal">&times;</button>
									</div>

									<!-- Modal body -->
									<div class="modal-body">
											<div class="row">
												<div class="col-sm">
													<h5>Date</h5>
												</div>
												<div class="col">
													<p><%	out.println(new SimpleDateFormat("dd/MM/yyyy").format(req.getRequestEvent().getEventDate()) + " " +
															req.getRequestEvent().getEventTime().toLocalTime().toString());	%></p>
												</div>
											</div>

											<div class="row">
												<div class="col-sm">
													<h5>Address</h5>
												</div>
												<div class="col">
													<p><%	out.println(req.getRequestEvent().getEventAddress() + ", " +
															req.getRequestEvent().getEventCity());	%></p>
												</div>
											</div>

											<div class="row">
												<div class="col-sm">
													<h5>Type & Distance</h5>
												</div>
												<div class="col">
													<p><%	out.println(req.getRequestEvent().getEventType().toString() + " - " +
													req.getRequestEvent().getEventDistance() + " KM");	%></p>
												</div>
											</div>

											<hr>

											<div class="row">
												<div class="col-sm">
													<h5>Message from user</h5>
												</div>
											</div>


											<div class="row">
												<div class="col">
													<div class="alert alert-info">
													<% if (req.getRequestMessage() != null) {
														out.println(req.getRequestMessage());
													} else {
														out.println("No message!");
													}
													%>
													</div>
												</div>
											</div>
										</div>

										<!-- Modal footer -->
										<div class="modal-footer">
											<div class="row">
												<div class="col">
													<input type="submit" onclick='rejectRequest(<% out.println(req.getRequestId()); %>)'
														class="btn btn-danger w-100" value='Reject'
														<% if (!req.getRequestStatus().equals(Status.PENDING.toString())) { %>
														disabled
														<% } %>
													>
												</div>
												<div class="col">
													<input type="submit" onclick='acceptRequest(<% out.println(req.getRequestId()); %>)'
														class="btn btn-success w-100" value='Accept'
														<% if (!req.getRequestStatus().equals(Status.PENDING.toString())) { %>
														disabled
														<% } %>
														>
												</div>
											</div>
										</div>

								</div>
							</div>
						</div>
    				<%
					}
    				%>
  					</tbody>
				</table>




			</form>
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