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
	String eventId = request.getParameter("event");

	if (action != "" && eventId != null) {
		SystemFacade facade = new SystemFacade();
		EventBean bean = new EventBean();

		bean.setEventId(Integer.parseInt(eventId));
		if (action.equalsIgnoreCase("CANCEL"))
			facade.cancelEvent(bean);
	}






%>

<head>
<meta charset="utf-8">
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

				<ul class="nav nav-pills">
  					<li class="nav-item">
  						<a class="nav-link active" data-toggle="tab" href="#createdEvents">Created events</a>
  					</li>
  					<li class="nav-item">
  						<a class="nav-link" data-toggle="tab" href="#joinEvents">Join events</a>
  					</li>
 					<li class="nav-item">
 						<a class="nav-link" data-toggle="tab" href="#pastEvents">Past events</a>
 					</li>
				</ul>

				<a href="logout.jsp" class="btn btn-sm btn-danger ml-auto mr-1"
				role="button"> Logout
				</a>
			</nav>



			<div class="tab-content">
				<div class="tab-pane active" id="createdEvents">
				<form action="events.jsp" method="GET">
					<input type="hidden" name="btnAction" id="btnActionId" value="">
					<input type="hidden" name="event" id="eventId" value="">

					<ul class="list-group">
						<!-- Single Row for JSP -->
						<%
						List<EventBean> myEvents = new SystemFacade().getMyActiveEvents();
						for (EventBean event : myEvents) {
						%>
						<li class="list-group-item">
							<div class="container-fluid">
								<div class="row">
									<div class="col">
										<div class="row">
											<h5>
											<%
												LevelEnum level = event.getEventLevel();
												switch (level) {
												case BEGINNER:
											%>		<span class="badge badge-success">
													<%	out.println(level.toString()); %>
													</span>
											<% 		break;
												case INTERMEDIATE:
											%>		<span class="badge badge-primary">
													<%	out.println(level.toString()); %>
													</span>
											<% 		break;
												case PRO:
											%>		<span class="badge badge-danger">
													<%	out.println(level.toString()); %>
													</span>
											<% 		break;
												default:
													break;
												}
											%>
											<span>
											<%	out.println(event.getEventTitle());	%>
											</span>

											</h5>


										</div>
										<div class="row">
										<h2>
											<%	out.println(event.getEventDistance().toString() + " KM");	%>
										</h2>
										</div>
										<div class="row">
											<b><%	out.println(event.getEventType().toString());	%></b>
										</div>
										<div class="row">
											<%	out.println(event.getEventDescription());	%>
										</div>
										<div class="row">
											<%	out.println(event.getEventAddress());	%>
										</div>
										<div class="row">
											<%	out.println(new SimpleDateFormat("dd-MM-yyyy").format
													(event.getEventDate()) + " - " +
													new SimpleDateFormat("HH:mm").format
															(event.getEventTime()));	%>
										</div>
									</div>

									<div class="col">
										<div class="row justify-content-end mt-2">
											<img src="style/img/icon-participants-24.png" alt="" width="24"
												height="24">
											<%
												out.println(new SystemFacade().getEventParticipants(event));
											%>
										</div>
										<div class="row justify-content-end mt-2">
											<input type="submit" onclick='cancelEvent(<% out.println(event.getEventId()); %>)'
												class="btn btn-outline-danger" value='Cancel'>
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
				</form>
				</div>

				<div class="tab-pane fade" id="joinEvents">
				<form action="events.jsp" method="GET">
					<input type="hidden" name="btnAction" id="btnActionId" value="">
					<input type="hidden" name="event" id="eventId" value="">

					<ul class="list-group">
						<!-- Single Row for JSP -->
						<%
						List<EventBean> joinEvents = new SystemFacade().getJoinEvents();
						for (EventBean joinEvent : joinEvents) {
						%>
						<li class="list-group-item">
							<div class="container-fluid">
								<div class="row">
									<div class="col">
										<div class="row">
											<h5>
											<%
												LevelEnum level = joinEvent.getEventLevel();
												switch (level) {
												case BEGINNER:
											%>		<span class="badge badge-success">
													<%	out.println(level.toString()); %>
													</span>
											<% 		break;
												case INTERMEDIATE:
											%>		<span class="badge badge-primary">
													<%	out.println(level.toString()); %>
													</span>
											<% 		break;
												case PRO:
											%>		<span class="badge badge-danger">
													<%	out.println(level.toString()); %>
													</span>
											<% 		break;
												default:
													break;
												}
											%>
											<span>
											<%	out.println(joinEvent.getEventTitle());	%>
											</span>
											</h5>

											<span><i>
											<%	out.println("&nbsp;&nbsp;created by " + joinEvent.getEventOrganizer().getUsername());	%>
											</i></span>

										</div>
										<div class="row">
										<h2>
											<%	out.println(joinEvent.getEventDistance().toString() + " KM");	%>
										</h2>
										</div>
										<div class="row">
											<b><%	out.println(joinEvent.getEventType().toString());	%></b>
										</div>
										<div class="row">
											<%	out.println(joinEvent.getEventDescription());	%>
										</div>
										<div class="row">
											<%	out.println(joinEvent.getEventAddress());	%>
										</div>
										<div class="row">
											<%	out.println(new SimpleDateFormat("dd-MM-yyyy").format
													(joinEvent.getEventDate()) + " - " +
													new SimpleDateFormat("HH:mm").format
															(joinEvent.getEventTime()));	%>
										</div>
									</div>

									<div class="col">
										<div class="row justify-content-end mt-2">
											<img src="style/img/icon-participants-24.png" alt="" width="24"
												height="24">
											<%
												out.println(new SystemFacade().getEventParticipants(joinEvent));
											%>
										</div>
										<div class="row justify-content-end mt-2">
											<input type="submit" onclick='cancelEvent(<% out.println(joinEvent.getEventId()); %>)'
												class="btn btn-outline-danger" value='Cancel'>
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
				</form>
				</div>

				<div class="tab-pane fade" id="pastEvents">
				<form action="events.jsp" method="GET">
					<input type="hidden" name="btnAction" id="btnActionId" value="">
					<input type="hidden" name="event" id="eventId" value="">

					<ul class="list-group">
						<!-- Single Row for JSP -->
						<%
						List<EventBean> myPastEvents = new SystemFacade().getMyPastEvents();
						for (EventBean event : myPastEvents) {
						%>
						<li class="list-group-item">
							<div class="container-fluid">
								<div class="row">
									<div class="col">
										<div class="row">
											<h5>
											<%
												LevelEnum level = event.getEventLevel();
												switch (level) {
												case BEGINNER:
											%>		<span class="badge badge-success">
													<%	out.println(level.toString()); %>
													</span>
											<% 		break;
												case INTERMEDIATE:
											%>		<span class="badge badge-primary">
													<%	out.println(level.toString()); %>
													</span>
											<% 		break;
												case PRO:
											%>		<span class="badge badge-danger">
													<%	out.println(level.toString()); %>
													</span>
											<% 		break;
												default:
													break;
												}
											%>
											<span>
											<%	out.println(event.getEventTitle());	%>
											</span>
											</h5>
											<span><i>
											<%	out.println("&nbsp;&nbsp;created by " + event.getEventOrganizer().getUsername());	%>
											</i></span>
										</div>
										<div class="row">
										<h2>
											<%	out.println(event.getEventDistance().toString() + " KM");	%>
										</h2>
										</div>
										<div class="row">
											<b><%	out.println(event.getEventType().toString());	%></b>
										</div>
										<div class="row">
											<%	out.println(event.getEventDescription());	%>
										</div>
										<div class="row">
											<%	out.println(event.getEventAddress());	%>
										</div>
										<div class="row">
											<%	out.println(new SimpleDateFormat("dd-MM-yyyy").format
													(event.getEventDate()) + " - " +
													new SimpleDateFormat("HH:mm").format
															(event.getEventTime()));	%>
										</div>
									</div>

									<div class="col">
										<div class="row justify-content-end mt-2">
											<img src="style/img/icon-participants-24.png" alt="" width="24"
												height="24">
											<%
												out.println(new SystemFacade().getEventParticipants(event));
											%>
										</div>
										<div class="row justify-content-end mt-2">
											<input type="submit" onclick='cancelEvent(<% out.println(event.getEventId()); %>)'
												class="btn btn-outline-danger" value='Cancel'>
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
				</form>
				</div>

			</div>

		</div>
	</div>

</body>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<script>
	function cancelEvent(evId) {
		if (confirm("Are you sure you want to cancel your participation in the event. "
				+ "If you are the organizer, the event will be deleted.")) {
			document.getElementById("btnActionId").value = "CANCEL";
			document.getElementById("eventId").value = evId;
		} else
			document.getElementById("btnActionId").value = "";
	}
</script>

</html>