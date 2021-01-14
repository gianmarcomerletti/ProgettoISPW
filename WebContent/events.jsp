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
<%@ page language="java" contentType="text/html; charset=UTF-8"
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


	List<EventBean> myEvents = new SystemFacade().getMyActiveEvents();
	List<EventBean> joinEvents = new SystemFacade().getJoinEvents();
	List<EventBean> myPastEvents = new SystemFacade().getMyPastEvents();

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
				<div class="tab-pane fade show active" id="createdEvents">
				<form action="events.jsp" method="GET">
				<input type="hidden" name="btnAction" id="btnActionId" value="">
				<input type="hidden" name="event" id="eventId" value="">
					<ul class="list-group">
						<!-- Single Row for JSP -->
						<%
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
											<strong><%	out.println(event.getEventType().toString());	%></strong>
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
				<input type="hidden" name="btnAction" id="btnActionId2" value="">
				<input type="hidden" name="event" id="eventId2" value="">
					<ul class="list-group">
						<!-- Single Row for JSP -->
						<%
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

											<span><em>
											<%	out.println("&nbsp;&nbsp;created by " + joinEvent.getEventOrganizer().getUsername());	%>
											</em></span>

										</div>
										<div class="row">
										<h2>
											<%	out.println(joinEvent.getEventDistance().toString() + " KM");	%>
										</h2>
										</div>
										<div class="row">
											<strong><%	out.println(joinEvent.getEventType().toString());	%></strong>
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
					<ul class="list-group">
						<!-- Single Row for JSP -->
						<%
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
											<span><em>
											<%	out.println("&nbsp;&nbsp;created by " + event.getEventOrganizer().getUsername());	%>
											</em></span>
										</div>
										<div class="row">
										<h2>
											<%	out.println(event.getEventDistance().toString() + " KM");	%>
										</h2>
										</div>
										<div class="row">
											<strong><%	out.println(event.getEventType().toString());	%></strong>
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
											<button id="sendReview" onclick='selectEventforReview(<%out.println(event.getEventId());%>)'
												data-toggle="modal" data-target="#newReview"
												class="btn btn-outline-primary" type="button">
												Review
											</button>
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

				<!-- Review Event Modal -->
				<div class="modal fade" id="newReview" role="dialog"  aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<!-- Modal Header -->
							<div class="modal-header">
								<h3 class="modal-title">Send a Review</h3>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<!-- Modal body -->
							<form action="events.jsp" method="POST">
							<input type="hidden" name="btnAction" id="btnActionId3" value="">
							<input type="hidden" name="event" id="eventId3" value="">
							<input type="hidden" value="" id="base64ImageReviewId" name="base64ImageReview" value="">
							<div class="modal-body">
								<div class="row">
	 								<div class="col-sm">
										<div class="form-group">
											<label for="inputTextReview">Text of the review</label>
											<textarea class="form-control" required placeholder="Text of the review"
												name="inputTextReview" rows="4"></textarea>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-sm">
										<div class="form-group">
											<label class="control-label" for="rating">
	   											<span class="field-label-header">Rate</span><br>
	    										<span class="field-label-info"></span>
	    										<input type="hidden" id="selected_rating" name="selected_rating" value="" required="required">
	   										</label>
	   										<h2 class="bold rating-header" style="">
	    										<span class="selected-rating">0</span><small> / 5</small>
	    									</h2>
	    									<button type="button" class="btnrating btn btn-default btn-lg" data-attr="1" id="rating-star-1">
	        									<i class="fa fa-star" aria-hidden="true"></i>
	    									</button>
	    									<button type="button" class="btnrating btn btn-default btn-lg" data-attr="2" id="rating-star-2">
	        									<i class="fa fa-star" aria-hidden="true"></i>
	   									 	</button>
	    									<button type="button" class="btnrating btn btn-default btn-lg" data-attr="3" id="rating-star-3">
	        									<i class="fa fa-star" aria-hidden="true"></i>
	    									</button>
	    									<button type="button" class="btnrating btn btn-default btn-lg" data-attr="4" id="rating-star-4">
	        									<i class="fa fa-star" aria-hidden="true"></i>
	    									</button>
	    									<button type="button" class="btnrating btn btn-default btn-lg" data-attr="5" id="rating-star-5">
	        									<i class="fa fa-star" aria-hidden="true"></i>
	    									</button>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-sm">
										<div class="form-group">
											<label for="inputReviewPictureId">Add a picture</label> <br>
											<input type="file" accept="image/png, image/jpeg"
												id="inputReviewPictureId" name="inputReviewPicture">
										</div>
									</div>
								</div>
							</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<div class="row">
									<div class="col-sm">
										<input type="submit" onclick='reviewEvent()'
											class="btn btn-primary w-100" value='Send'>
									</div>
								</div>
							</div>
							</form>
						</div>
					</div>
				</div>
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
			document.getElementById("eventId2").value = evId;

		} else
			document.getElementById("btnActionId").value = "";
	}

	function reviewEvent() {
		document.getElementById("btnActionId3").value = "REVIEW";
	}

	function getBase64(file) {
		var reader = new FileReader();
		reader.readAsBinaryString(file);
		reader.onload = function() {
			console.log(reader.result);
			document.getElementById('base64ImageReviewId').value = btoa(reader.result);
			response.sendRedirect("index.jsp");
		};
		reader.onerror = function(error) {
			alert("Invalid file");
		};
	}

	function selectEventforReview(evId) {
		document.getElementById("eventId3").value = evId;
	}

	$("#inputReviewPictureId").change(function() {
		var file = document.getElementById('inputReviewPictureId').files[0];
		getBase64(file);
	});
</script>

</html>