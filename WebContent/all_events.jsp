<%@page import="com.gianmarco.merletti.progetto_ispw.logic.exception.RequestException"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean"%>
<%@page import="java.util.Base64"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.util.Rating"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.exception.ReviewException"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.bean.ReviewBean"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.util.LevelEnum"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.view.SessionView"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.bean.EventListElementBean"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<%
	String eventId = request.getParameter("event");
	String requestMessage = request.getParameter("inputRequestMessage");
	if (eventId != null) {
		SystemFacade facade = new SystemFacade();
		EventBean selectedEvent = new EventBean();
		selectedEvent.setEventId(Integer.parseInt(eventId));
		RequestBean requestBean = new RequestBean();
		requestBean.setRequestEvent(selectedEvent);
		requestBean.setRequestCreationDate(new java.sql.Date(new java.util.Date().getTime()));
		requestBean.setRequestUser(SessionView.getUsername());
		if (requestMessage != "")
			requestBean.setRequestMessage(requestMessage);

		try {
			facade.sendRequest(requestBean);
			out.println("<script>alert('Requested to join send!');</script>");
		} catch (RequestException e) {
			out.println("<script>alert('You are already a participant of the event or your request is in pending status!');</script>");
		}

	}


	List<EventListElementBean> events = new SystemFacade().getAllEvents();

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
	<nav id="navbar" class="navbar navbar-dark bg-primary" aria-label="nav_all_events">
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
			<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom" aria-label="nav2_all_events">
				<div class="input-group" style="width:200px;">
					<div class="input-group-prepend">
    					<label class="input-group-text" for="inputGroupSelect">Filter by</label>
  					</div>
  					<select class="custom-select" id="inputGroupSelect">
    					<option selected value="0">Title</option>
    					<option value="3">City</option>
   						<option value="5">Organizer</option>
  					</select>
				</div>

				<input class="form-control" id="myInput" onkeyup="myFunction()" type="text" style="width:500px; margin-left: 10px;" placeholder="Search..">

				<a href="logout.jsp" class="btn btn-sm btn-danger ml-auto mr-1"
					role="button"> Logout
				</a>
			</nav>

			<div class="tab-content">
			<form action="all_events.jsp" method="GET" id="form">
				<input type="hidden" name="event" id="eventId" value="">
				<input type="hidden" name="inputRequestMessage" id="inputRequestMessageId" value="">
				<table id="myTable" class="table table-bordered table-hover">
					<caption>All Events</caption>
					<thead class="thead-dark">
   						<tr>
      						<th scope="col">Title</th>
      						<th scope="col">Type</th>
      						<th scope="col">Level</th>
      						<th scope="col">City</th>
      						<th scope="col">Date</th>
      						<th scope="col">User</th>
    					</tr>
  					</thead>
  					<tbody>
					<%
					for (EventListElementBean ev : events) {
					%>
    					<tr class="clickable-row" id="<%out.print(ev.getElemEventId());%>" style="cursor:pointer">
      						<td><strong>
      						<%
      							out.println(ev.getElemEventTitle());
      						%>
      						</strong></td>
      						<td>
      						<%
      							out.println(ev.getElemEventType());
      						%>
      						</td>
      						<td>
      						<%
      							out.println(ev.getElemEventLevel());
      						%>
      						</td>
      						<td>
      						<%
      							out.println(ev.getElemEventCity());
      						%>
      						</td>
      						<td>
      						<%
      							out.println(ev.getElemEventDate());
      						%>
      						</td>
      						<td>
      						<%
      							out.println(ev.getElemEventRating());
      						%>
      						</td>
    					</tr>
    				<%
					}
    				%>
  					</tbody>
				</table>
			</form>
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

	$("tbody tr").click(function () {
	  var evId = this.id;
	  document.getElementById("eventId").value = evId;
	  var form = document.getElementById("form");
	  var message = prompt("Enter a message for the organizer user", "");
	  if (message != null) {
		  document.getElementById("inputRequestMessageId").value = message;
		  form.submit();
	  } else {
		  return;
	  }
	});

</script>

<script>

	function myFunction() {
  		// Declare variables
 	 	var input, filter, table, tr, td, i, txtValue, sel;
 		input = document.getElementById("myInput");
 		filter = input.value.toUpperCase();
  		table = document.getElementById("myTable");
  		tr = table.getElementsByTagName("tr");
  		sel = document.getElementById("inputGroupSelect");

 		// Loop through all table rows, and hide those who don't match the search query
  		for (i = 0; i < tr.length; i++) {
    		td = tr[i].getElementsByTagName("td")[sel.value];
    		if (td) {
     			txtValue = td.textContent || td.innerText;
      			if (txtValue.toUpperCase().indexOf(filter) > -1) {
        			tr[i].style.display = "";
      			} else {
        			tr[i].style.display = "none";
     	 		}
    		}
  		}
	}

</script>

</html>