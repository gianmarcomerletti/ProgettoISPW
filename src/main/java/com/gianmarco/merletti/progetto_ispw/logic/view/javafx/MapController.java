package com.gianmarco.merletti.progetto_ispw.logic.view.javafx;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.EventListener;

import com.gianmarco.merletti.progetto_ispw.logic.bean.AddressBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBeanView;
import com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade;
import com.gianmarco.merletti.progetto_ispw.logic.dao.CityDAO;
import com.gianmarco.merletti.progetto_ispw.logic.dao.UserDAO;
import com.gianmarco.merletti.progetto_ispw.logic.model.City;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderStroke;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import net.java.html.boot.fx.FXBrowsers;
import net.java.html.leaflet.Icon;
import net.java.html.leaflet.IconOptions;
import net.java.html.leaflet.LatLng;
import net.java.html.leaflet.Map;
import net.java.html.leaflet.Marker;
import net.java.html.leaflet.MarkerOptions;
import net.java.html.leaflet.Point;
import net.java.html.leaflet.Polygon;
import net.java.html.leaflet.Popup;
import net.java.html.leaflet.PopupOptions;
import net.java.html.leaflet.TileLayer;
import net.java.html.leaflet.TileLayerOptions;
import net.java.html.leaflet.event.MouseEvent;
import net.java.html.leaflet.event.MouseEvent.Type;
import net.java.html.leaflet.event.MouseListener;

public class MapController {

	private WebView mapView;
	private MouseListener mouseListener, markerListener;
	private Map map;
	private City city;
	private Marker markerSetPosMap;
	private MarkerOptions markerOpt, markerEventOpt, markerMyEventOpt;
	private Icon icon, iconEvent, iconMyEvent;

	public MapController(WebView mapView) {
		this.setMapView(mapView);
	}

	public void loadMap() {
		city = new CityDAO().getCity(SessionView.getCityEnum());
		SessionView.setAddressSetOnMap(null);
		SessionView.setEventSetOnMap(null);

		FXBrowsers.load(mapView, MapBoundary.class.getResource("index.html"), new Runnable() {

			@Override
			public void run() {
				map = new Map("map");

				// Map setup
				TileLayer tileLayer = new TileLayer(
						"https://2.base.maps.ls.hereapi.com/maptile/2.1/maptile/newest/normal.day/{z}/{x}/{y}/256/png8"
						+ "?apiKey=jcr3K96ee99COTmahBGt_FvhIAv2c12ePbnKWBukCLk",
						new TileLayerOptions()
							.setAttribution("TogetherRun | Map data &copy; <a href='https://developer.here.com/products/maps/'>HERE Api</a>")
							.setMaxZoom(18)
							.setId("togetherrun.ia9c2p12"));
				map.addLayer(tileLayer);
				map.setView(new LatLng(41.77579144, 13.2721022), 20);

				// Set city
				LatLng[] borderArray = new LatLng[city.getBorders().length];
				for (int i=0; i<borderArray.length; i++) {
					borderArray[i] = new LatLng(city.getBorders()[i][1], city.getBorders()[i][0]);
				}
				Polygon borderPolygon = new Polygon(borderArray);
				int zoom = map.getBoundsZoom(borderPolygon.getBounds());
				map.setView(new LatLng(city.getLatitude(), city.getLongitude()), zoom);
				map.addLayer(borderPolygon);

				// Initialize listeners
				initListeners();
				map.addMouseListener(MouseEvent.Type.CLICK, mouseListener);

				// Setup markers
				icon = new Icon(
						new IconOptions("leaflet-0.7.2/images/marker-icon-community.png").setIconSize(new Point(25, 41))
								.setIconAnchor(new Point(12.5, 41)).setPopupAnchor(new Point(0, -20)));
				iconEvent = new Icon(
						new IconOptions("leaflet-0.7.2/images/marker-icon.png").setIconSize(new Point(25, 41))
						.setIconAnchor(new Point(12.5, 41)).setPopupAnchor(new Point(0, -20)));
				iconMyEvent = new Icon(
						new IconOptions("leaflet-0.7.2/images/marker-icon-red.png").setIconSize(new Point(25, 41))
						.setIconAnchor(new Point(12.5, 41)).setPopupAnchor(new Point(0, -20)));
				markerOpt = new MarkerOptions().setIcon(icon);
				markerEventOpt = new MarkerOptions().setIcon(iconEvent);
				markerMyEventOpt = new MarkerOptions().setIcon(iconMyEvent);

				// Loading events on map
				addEveryEvent(map);
			}
		});

	}

	public void addEveryEvent(Map map) {
		List<EventBeanView> myEvents = new SystemFacade().getMyEvents();
		List<EventBeanView> eventsFiltered = new SystemFacade().getEventsFiltered();

		for (EventBeanView myEvent : myEvents) {

			new Marker(new LatLng(myEvent.getEventLatitude(), myEvent.getEventLongitude()), markerMyEventOpt)
			.bindPopup(new Popup(new PopupOptions().setMaxWidth(200)).setContent("<b><u>" + myEvent.getEventTitle()
				+ "</u></b><br>" + myEvent.getEventDescription()
				+ "<br><b>" + myEvent.getEventDistance() + " KM</b>"
				+ "<br><i>created on "
				+ new SimpleDateFormat("dd-MM-yyyy").format(myEvent.getEventCreationDate()) + " by "
				+ myEvent.getEventOrganizer() + "</i>"))
			.addMouseListener(MouseEvent.Type.CLICK, markerListener)
			.addTo(map);
		}

		for (EventBeanView event : eventsFiltered) {
			new Marker(new LatLng(event.getEventLatitude(), event.getEventLongitude()), markerEventOpt)
					.bindPopup(new Popup(new PopupOptions().setMaxWidth(200)).setContent("<b>" + event.getEventTitle()
						+ "</b><br>" + event.getEventDescription() + "<br><i>created on "
						+ new SimpleDateFormat("dd-MM-yyyy").format(event.getEventCreationDate()) + " by "
						+ event.getEventOrganizer() + "</i>"))
					.addTo(map);
		}
	}

	private void initListeners() {
		markerListener = (MouseEvent ev) -> {
			Double latitude = Double.valueOf(ev.getLatLng().getLatitude());
			Double longitude = Double.valueOf(ev.getLatLng().getLongitude());
			new SystemFacade().setEventForRequest(latitude, longitude);

		};

		mouseListener = (MouseEvent ev) -> {
			SessionView.setEventSetOnMap(null);

			double x = ev.getLatLng().getLatitude();
			double y = ev.getLatLng().getLongitude();
			Double[][] vs = city.getBorders();

			boolean inside = false;
			int i = 0;
			int j = (vs.length - 1);
			for (; i < vs.length; j = i++) {
				double xi = vs[i][0];
				double yi = vs[i][1];

				double xj = vs[j][0];
				double yj = vs[j][1];

				boolean intersect = ((yi > y) != (yj > y)) && (x < (xj - xi) * (y - yi) / (yj - yi) + xi);
				if (intersect)
					inside = !inside;
			}
/*
			if (!inside) {
				new Alert(AlertType.INFORMATION, "Out of city bounds! You can only pick locations inside the polygon.", ButtonType.OK).showAndWait();
				return;
			}
*/
			double latD = ev.getLatLng().getLatitude();
			double longD = ev.getLatLng().getLongitude();

			Double latitude = Double.valueOf(latD);
			Double longitude = Double.valueOf(longD);

			try {
				System.out.println("LATITUDE = " + latitude.toString() + ", LONGITUDE = " + longitude.toString());
				new SystemFacade().setAddressForEvent(longitude, latitude);
			} catch (Exception e) {
				new Alert(AlertType.INFORMATION, "Could not connect to geolocation service!", ButtonType.OK).showAndWait();
			}

			AddressBean addr = SessionView.getAddressSetOnMap();

			Popup popup = new Popup(new PopupOptions().setMaxWidth(200))
					.setContent("<b>" + addr.getRoad() + ", " + addr.getCity() + ", " + addr.getCountry() + "</b>");

			if (markerSetPosMap == null)
				markerSetPosMap = new Marker(new LatLng(latD, longD), markerOpt);
			else
				markerSetPosMap.setLatLng(new LatLng(latD, longD));
			markerSetPosMap.setOpacity(1);
			markerSetPosMap.bindPopup(popup).addTo(map);
			markerSetPosMap.openPopup();

		};
	}


	private void setMapView(WebView mapView) {
		this.mapView = mapView;
	}
}
