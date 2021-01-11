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
	String eventId = request.getParameter("event");
	String textReview = request.getParameter("inputTextReview");
	String valueReview = request.getParameter("selected_rating");
	String revBase64Image = request.getParameter("base64ImageReview");

	if (action != "" && eventId != null) {
		SystemFacade facade = new SystemFacade();
		EventBean bean = new EventBean();
		bean.setEventId(Integer.parseInt(eventId));
		if (action.equalsIgnoreCase("CANCEL"))
			facade.cancelEvent(bean);
		else if (action.equalsIgnoreCase("REVIEW")) {
			ReviewBean reviewBean = new ReviewBean();
			reviewBean.setEventBean(bean);
			reviewBean.setTextBean(textReview);
			reviewBean.setValueBean(Integer.parseInt(valueReview));
			reviewBean.setImageBean(Base64.getDecoder().decode(revBase64Image));
			try {
				facade.sendReview(reviewBean);
			} catch (ReviewException e) {
				out.println("<script>alert('You are already reviewed this event!');</script>");
			}
		}

	}


	List<RequestBean> requests = new SystemFacade().getMyRequests();
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
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

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
				<table class="table table-striped table-bordered table-hover">
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
    					<tr class="table-row" data-href="http://tutorialsplane.com">
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

    					<!-- Review Event Modal -->
						<div class="modal fade" id="viewRequest">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">

									<!-- Modal Header -->
									<div class="modal-header">
										<h3 class="modal-title">
										<%
											out.println(req.getRequestEvent().getEventTitle());
										%>
										</h3>
										<button type="button" class="close" data-dismiss="modal">&times;</button>
									</div>

									<!-- Modal body -->
									<form action="events.jsp" method="GET">
										<input type="hidden" name="btnAction" id="btnActionId" value="">
										<input type="hidden" name="event" id="eventId" value="">

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

											<div class="row">
												<div class="col-sm">
													<div class="form-group">
														<label for="inputMessage"><h5>Message from user</h5></label>
														<textarea readonly class="form-control" required placeholder="No message!"
															name="inputMessage" rows="5"></textarea>
													</div>
												</div>
											</div>
										</div>
									</form>

									<!-- Modal footer -->
									<div class="modal-footer">
										<div class="row">
											<div class="col-sm">
												<input type="submit" onclick=''
													class="btn btn-danger w-100" value='Reject'>
											</div>
											<div class="col-sm">
												<input type="submit" onclick=''
													class="btn btn-success w-100" value='Accept'>
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
	jQuery(document).ready(function($){

		$(".btnrating").on('click',(function(e) {

			var previous_value = $("#selected_rating").val();

			var selected_value = $(this).attr("data-attr");
			$("#selected_rating").val(selected_value);

			$(".selected-rating").empty();
			$(".selected-rating").html(selected_value);

			for (i = 1; i <= selected_value; ++i) {
				$("#rating-star-"+i).toggleClass('btn-warning');
				$("#rating-star-"+i).toggleClass('btn-default');
			}

			for (ix = 1; ix <= previous_value; ++ix) {
				$("#rating-star-"+ix).toggleClass('btn-warning');
				$("#rating-star-"+ix).toggleClass('btn-default');
			}

		}));
	});
</script>

<script>
	function cancelEvent(evId) {
		if (confirm("Are you sure you want to cancel your participation in the event. "
				+ "If you are the organizer, the event will be deleted.")) {
			document.getElementById("btnActionId").value = "CANCEL";
			document.getElementById("btnActionId2").value = "CANCEL";
			document.getElementById("eventId").value = evId;
			document.getElementById("eventId2").value = evid;

		} else
			document.getElementById("btnActionId").value = "";
	}

	function reviewEvent(evId) {
		document.getElementById("btnActionId3").value = "REVIEW";
		document.getElementById("eventId3").value = evId;
	}

	function getBase64(file) {
		var reader = new FileReader();
		reader.readAsBinaryString(file);
		reader.onload = function() {
			console.log(reader.result);
			document.getElementById('base64ImageReportId').value = btoa(reader.result);
			response.sendRedirect("index.jsp");
		};
		reader.onerror = function(error) {
			alert("Invalid file");
		};
	}

	$("#inputReportPictureId").change(function() {
		var file = document.getElementById('inputReportPictureId').files[0];
		getBase64(file);
	});

	$(document).ready(function() {
	    $(".table-row").click(function() {
	    	document.getElementById("eventId").value =
	    	$("#viewRequest").modal();
	    });
	});

</script>

</html>