<%@page import="java.time.ZoneId"%>
<%@page import="java.sql.Date"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.stream.Stream"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.bean.EventBeanView"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.dao.CityDAO"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.model.City"%>
<%@page import="com.gianmarco.merletti.progetto_ispw.logic.view.SessionView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<%
	// new event values
	String titleEvent = request.getParameter("inputTitleEvent");
	String descriptionEvent = request.getParameter("inputEventDescription");
	String dateEvent = request.getParameter("inputDateEvent");
	String timeEvent = request.getParameter("inputTimeEvent");
	String distanceEvent = request.getParameter("inputDistanceEvent");
	String typeEvent = request.getParameter("inputEventLevel");

	// map click values
	String evLatitude = (String) request.getParameter("latitudeInput");
	String evLongitude = (String) request.getParameter("longitudeInput");
	String evAddress = request.getParameter("addressInput");

	SystemFacade facade = new SystemFacade();
	if (evLatitude != null && evLongitude != null && evAddress != null &&
			titleEvent != null && descriptionEvent != null && dateEvent != null && timeEvent != null && distanceEvent != null) {
		EventBeanView bean = new EventBeanView();
		bean.setEventViewTitle(titleEvent);
		bean.setEventViewDescription(descriptionEvent);
		bean.setEventViewDate(new java.sql.Date(new SimpleDateFormat("dd-MM-yyyy").parse(dateEvent).getTime()));
		bean.setEventViewTime(timeEvent);
		bean.setEventViewDistance(Integer.parseInt(distanceEvent));
		bean.setEventViewAddress(evAddress);
		bean.setEventViewLatitude(Double.parseDouble(evLatitude));
		bean.setEventViewLongitude(Double.parseDouble(evLongitude));
		bean.setEventViewCity(SessionView.getCityEnum().toString());
		bean.setEventViewCreationDate(new java.sql.Date(new java.util.Date().getTime()));
		bean.setEventViewOrganizer(SessionView.getUsername());
		bean.setEventViewLevel(SessionView.getLevelEnum().toString());
		bean.setEventViewType(typeEvent);
		if (facade.createEvent(bean))
			out.println("<script>alert('Event created');</script>");
	}

	City city = new CityDAO().getCity(SessionView.getCityEnum());
	List<EventBeanView> events = new SystemFacade().getEventsFiltered();
	List<String> typeList = Stream.of("LENTO", "MEDIO", "VELOCE", "FARTLEK", "RIPETUTE")
			.collect(Collectors.toList());
	List<EventBeanView> myEvents = new SystemFacade().getMyEvents();
	List<EventBeanView> eventsFiltered = new SystemFacade().getEventsFiltered();
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

<!-- Leaflet CSS -->
 <link rel="stylesheet" href="https://unpkg.com/leaflet@1.7.1/dist/leaflet.css"
   integrity="sha512-xodZBNTC5n17Xt2atTPuE1HxjVMSvLVW9ocqUKLsCC5CXdbqCmblAshOMAS6/keqq/sMZMZ19scR4PsZChSR7A=="
   crossorigin=""/>

<!-- Load Esri Leaflet Geocoder from CDN -->
<link rel="stylesheet"
	href="https://unpkg.com/esri-leaflet-geocoder@2.3.2/dist/esri-leaflet-geocoder.css"
	integrity="sha512-IM3Hs+feyi40yZhDH6kV8vQMg4Fh20s9OzInIIAc4nx7aMYMfo+IenRUekoYsHZqGkREUgx0VvlEsgm7nCDW9g=="
	crossorigin="">

<link rel="stylesheet" href="style/background.css">

<title>TogetherRun</title>
</head>
<body>

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
				<a href="home_citizen.jsp" class="list-group-item list-group-item-action bg-light">Map</a>
				<a href="all_events.jsp" class="list-group-item list-group-item-action bg-light">All Events</a>
				<a href="my_events.jsp" class="list-group-item list-group-item-action bg-light">My Events</a>
				<a href="my_requests.jsp" class="list-group-item list-group-item-action bg-light">My Requests</a>
			</div>
		</div>

		<!-- Page Content -->
		<div id="page-content">
			<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
				<form class="form-inline">
					<button id="createEventModalBtnId" disabled
						data-toggle="modal" data-target="#newEvent"
						class="btn btn-sm btn-primary" type="button" style="margin-right:5px;">
						Create an Event
					</button>
					<button id="sendRequestModalBtnId" disabled
						data-toggle="modal" data-target="#newRequest"
						class="btn btn-sm btn-primary" type="button">
						Send a Request to Join
					</button>
				</form>
				<a href="logout.jsp" class="btn btn-sm btn-danger ml-auto mr-1"
				role="button"> Logout
				</a>
			</nav>

			<div class="container-fluid p-0">
				<div id="mapid"></div>
			</div>
		</div>
	</div>

	<!-- Form modals -->

	<!-- Create Request -->
	<div class="modal" id="newRequest">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h3 class="modal-title">Send Request</h3>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<form action="home_user.jsp" method="GET"
					id="sendRequestFormId">
					<div class="modal-body">
						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<input type="text" required class="form-control"
										name="inputReportTitle" placeholder="Title">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<textarea required class="form-control"
										name="inputReportDescription" placeholder="Description"
										rows="10"></textarea>
								</div>
							</div>
						</div>



						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="inputReportPictureId">Add a picture</label> <br>
									<input required type="file" accept="image/png, image/jpeg"
										id="inputReportPictureId" name="inputReportPicture">
								</div>
							</div>
						</div>



					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<div class="row">
							<div class="col-sm">
								<button type="submit" class="btn btn-primary w-100">Send</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Create Event -->
	<div class="modal fade" id="newEvent">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h3 class="modal-title">Create New Event</h3>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<form action="home_user.jsp" method="GET">
					<input type="hidden" value="" id="latitudeInputId"
						name="latitudeInput">
					<input type="hidden" value="" id="longitudeInputId"
						name="longitudeInput">
					<input type="hidden" value="" id="addressInputId"
						name="addressInput">

					<div class="modal-body">
						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="inputTitleEvent">Title</label>
									<input type="text" class="form-control" required
										name="inputTitleEvent" placeholder="Title of the Event">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="inputEventDescription">Description</label>
									<textarea class="form-control" required placeholder="Description of the Event"
										name="inputEventDescription" rows="2"></textarea>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="inputDateEvent">Date</label>
									<input type="date" required class="form-control"
										name="inputDateEvent">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="inputTimeEvent">Time</label>
									<input type="time" required class="form-control"
										name="inputTimeEvent">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="inputEventDistance">Distance</label>
									<input type="number" class="form-control" required
										name="inputDistanceEvent" placeholder="km">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm">
								<div class="form-group">
									<label for="inputEventLevelId">Category</label> <select
										required class="form-control" name="inputEventLevel"
										id="inputLevelId">
											<%	for (String type : typeList) {	%>
										<option>
											<%		out.println(type);	%>
										</option>
												<%	}	%>
									</select>
								</div>
							</div>
						</div>
					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<div class="row">
							<div class="col-sm">
								<button type="submit" class="btn btn-primary w-100">Create</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
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
<script
	src="https://cdn.rawgit.com/hayeswise/Leaflet.PointInPolygon/v1.0.0/wise-leaflet-pip.js"></script>

<script>
	$("#menu-toggle").click(function(e) {
		e.preventDefault();
		$("#wrapper").toggleClass("toggled");
	});
</script>

<!-- Map Javascript -->
<script src="https://unpkg.com/leaflet@1.6.0/dist/leaflet.js"
	integrity="sha512-gZwIG9x3wUXg2hdXF6+rVkLF/0Vi9U8D2Ntg4Ga5I5BZpVkVxlJWbSQtXPSiUTtC0TjtGOmxa1AJPuV0CPthew=="
	crossorigin=""></script>
<script src="lib/Control.Coordinates.js"></script>


<!-- Load Esri Leaflet from CDN -->
<script src="https://unpkg.com/esri-leaflet@2.2.3/dist/esri-leaflet.js"
	integrity="sha512-YZ6b5bXRVwipfqul5krehD9qlbJzc6KOGXYsDjU9HHXW2gK57xmWl2gU6nAegiErAqFXhygKIsWPKbjLPXVb2g=="
	crossorigin=""></script>


<script
	src="https://unpkg.com/esri-leaflet-geocoder@2.2.13/dist/esri-leaflet-geocoder.js"
	integrity="sha512-zdT4Pc2tIrc6uoYly2Wp8jh6EPEWaveqqD3sT0lf5yei19BC1WulGuh5CesB0ldBKZieKGD7Qyf/G0jdSe016A=="
	crossorigin=""></script>

<script
	src="https://cdn.rawgit.com/hayeswise/Leaflet.PointInPolygon/v1.0.0/wise-leaflet-pip.js"></script>


<script type="text/javascript">
	var icon = L.icon({
		iconUrl: "lib/leaflet-0.7.2/images/marker-icon-community.png",
		iconSize:		[25,41],
		iconAnchor:		[12.5,41],
		popupAnchor:	[0,-20]
	});
	var iconEvent = L.icon({
		iconUrl: "lib/leaflet-0.7.2/images/marker-icon.png",
		iconSize:		[25,41],
		iconAnchor:		[12.5,41],
		popupAnchor:	[0,-20]
	});
	var iconMyEvent = L.icon({
		iconUrl: "lib/leaflet-0.7.2/images/marker-icon-red.png",
		iconSize:		[25,41],
		iconAnchor:		[12.5,41],
		popupAnchor:	[0,-20]
	});

	var cityBorder = [
		<%	int len = city.getBorders().length;
			for (int i = 0; i < len; i++) {
				out.println("[" + Double.toString(city.getBorders()[i][1]) + ","
						+ Double.toString(city.getBorders()[i][0]) + "]");
				if (i < len - 1) {
					out.println(", ");
				}
			}
		%>
	];

	var mymap = L.map('mapid').setView(
			[
<%out.println(city.getLatitude());%>
	,
<%out.println(city.getLongitude());%>
	], 13);
	L.tileLayer(
		"https://1.base.maps.ls.hereapi.com/maptile/2.1/maptile/newest/normal.day/{z}/{x}/{y}/256/png8"
			+ "?apiKey=XUbEajSB94rqlnuoXCZkfMe_n3bUeeGghpHSejZkC50",
					{
						attribution : 'TogetherRun | HERE Maps',
						maxZoom : 18,
						id : 'togetherrun.ia9c2p12',
						accessToken : 'jcr3K96ee99COTmahBGt_FvhIAv2c12ePbnKWBukCLk'
					}).addTo(mymap);
<%	for (EventBeanView myEvent : myEvents) {
		out.println("L.marker([" + myEvent.getEventViewLatitude() + ", " + myEvent.getEventViewLongitude()
				+ "], {icon: iconMyEvent}).addTo(mymap).bindPopup('<b><u>" + myEvent.getEventViewTitle() + "</u></b><br>" + myEvent.getEventViewAddress()
				+ "<br>Date: <b>" + new SimpleDateFormat("dd-MM-yyyy").format(myEvent.getEventViewDate()) + "</b>"
				+ "<br>Time: <b>" + new SimpleDateFormat("HH:mm").format(myEvent.getEventViewTime()) + "</b>"
				+ "<br><b>" + myEvent.getEventViewDistance() + " KM</b> - " + myEvent.getEventViewType()
				+ "<br>Level: <b>" + myEvent.getEventViewLevel() + "</b>"
				+ "<br><i>created on "
				+ new SimpleDateFormat("dd-MM-yyyy").format(myEvent.getEventViewCreationDate()) + " by "
				+ myEvent.getEventViewOrganizer() + "</i>')");
	}

	for (EventBeanView event : eventsFiltered) {
		out.println("L.marker([" + event.getEventViewLatitude() + ", " + event.getEventViewLongitude()
				+ "], {icon: iconEvent}).addTo(mymap).bindPopup('<b>" + event.getEventViewTitle() + "</b><br>" + event.getEventViewAddress()
				+ "<br>Date: <b>" + new SimpleDateFormat("dd-MM-yyyy").format(event.getEventViewDate()) + "</b>"
				+ "<br>Time: <b>" + new SimpleDateFormat("HH:mm").format(event.getEventViewTime()) + "</b>"
				+ "<br><b>" + event.getEventViewDistance() + " KM</b> - " + event.getEventViewType()
				+ "<br>Level: <b>" + event.getEventViewLevel() + "</b>"
				+ "<br><i>created on "
				+ new SimpleDateFormat("dd-MM-yyyy").format(event.getEventViewCreationDate()) + " by "
				+ event.getEventViewOrganizer() + "</i>');");
	}
%>

	var geocodeService = L.esri.Geocoding.geocodeService();

	var polygon = L.polygon(cityBorder, {
		radius : 8,
		fillColor : "#343a40",
		color : "blue",

		opacity : 0.5,
		fillOpacity : 0.1

	}).addTo(mymap);

	mymap.fitBounds(polygon.getBounds());

	var selectionMarker = {};



	mymap
		.on('click',
		function(e) { geocodeService
				.reverse()
				.latlng(e.latlng)
				.run( function(error, result) {

						mymap
							.removeLayer(selectionMarker);
						selectionMarker = L.marker(result.latlng, {icon: icon})
											.addTo(mymap)
											.bindPopup(result.address.Match_addr)
											.openPopup();
						document.getElementById("latitudeInputId").value = e.latlng.lat;
						document.getElementById("longitudeInputId").value = e.latlng.lng;
						document.getElementById("addressInputId").value = result.address.Match_addr;
						document.getElementById("createEventModalBtnId").disabled = false;
				});
	});

	function responsiveMap() {
		wrapperSize = $("#page-content-wrapper").height() - 140;

		document.getElementById("mapid").style.height = wrapperSize + "px";
		mymap.invalidateSize(true);

	}

	window.addEventListener("resize", responsiveMap);
	$(document).ready(function() {
		responsiveMap();
	});
</script>


</body>
</html>