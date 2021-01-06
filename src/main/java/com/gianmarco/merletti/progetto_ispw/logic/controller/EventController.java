package com.gianmarco.merletti.progetto_ispw.logic.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.logging.Logger;

import com.gianmarco.merletti.progetto_ispw.logic.bean.AddressBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.ReviewBean;
import com.gianmarco.merletti.progetto_ispw.logic.dao.EventDAO;
import com.gianmarco.merletti.progetto_ispw.logic.dao.ReviewDAO;
import com.gianmarco.merletti.progetto_ispw.logic.exception.ConnectToGeolocationServiceException;
import com.gianmarco.merletti.progetto_ispw.logic.exception.ReviewException;
import com.gianmarco.merletti.progetto_ispw.logic.exception.UserNotFoundException;
import com.gianmarco.merletti.progetto_ispw.logic.model.Event;
import com.gianmarco.merletti.progetto_ispw.logic.model.Review;
import com.gianmarco.merletti.progetto_ispw.logic.util.LevelEnum;
import com.gianmarco.merletti.progetto_ispw.logic.util.TypeEnum;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class EventController {

	public boolean createEvent(EventBean eventBean) {
		EventDAO dao = new EventDAO();

		EventBean temp = new EventBean();
		temp.setEventAddress(eventBean.getEventAddress());
		temp.setEventCity(eventBean.getEventCity());
		temp.setEventCreationDate(eventBean.getEventCreationDate());
		temp.setEventDate(eventBean.getEventDate());
		temp.setEventTitle(eventBean.getEventTitle());
		temp.setEventDescription(eventBean.getEventDescription());
		temp.setEventLatitude(eventBean.getEventLatitude());
		temp.setEventLongitude(eventBean.getEventLongitude());
		temp.setEventLevel(LevelEnum.valueOf(eventBean.getEventOrganizer().getLevel()));
		temp.setEventDistance(eventBean.getEventDistance());
		temp.setEventType(eventBean.getEventType());
		temp.setEventTime(eventBean.getEventTime());
		temp.setEventOrganizer(eventBean.getEventOrganizer());

		Event event = dao.addEvent(temp);
		if (dao.joinEvent(SessionView.getUsername(), event.getId()))
			return (event != null);
		return false;
	}

	public void setEventForRequest(Double latitude, Double longitude) {
		EventDAO dao = new EventDAO();
		Event ev = dao.findByLatLong(latitude, longitude);

		EventBean evBean = new EventBean();
		evBean.setEventId(ev.getId());
		evBean.setEventTitle(ev.getTitle());
		evBean.setEventAddress(ev.getAddress());
		evBean.setEventLatitude(ev.getLatitude());
		evBean.setEventLongitude(ev.getLongitude());
		evBean.setEventDescription(ev.getDescription());
		evBean.setEventCreationDate(ev.getCreationDate());
		evBean.setEventDate(ev.getDate());
		evBean.setEventTime(ev.getTime());
		evBean.setEventDistance(ev.getDistance());
		evBean.setEventLevel(LevelEnum.valueOf(ev.getLevel()));
		evBean.setEventType(TypeEnum.valueOf(ev.getType()));
		evBean.setEventCity(ev.getCity());
		evBean.setEventOrganizer(ev.getOrganizerUser());

		SessionView.setEventSetOnMap(evBean);
		Logger.getLogger("together_run").info("Evento selezionato");
	}

	public void setAddressForEvent(Double longitude, Double latitude)
			throws ConnectToGeolocationServiceException {
		SessionView.setLatitudeSetOnMap(latitude);
		SessionView.setLongitudeSetOnMap(longitude);

		AddressBean addr = new AddressBean();

		URL url;
		JsonReader jRdr;

		try {
			String req = "https://reverse.geocoder.ls.hereapi.com/6.2/reversegeocode.json"
					+ "?apiKey=jcr3K96ee99COTmahBGt_FvhIAv2c12ePbnKWBukCLk" + "&mode=retrieveAddresses" + "&prox="
					+ latitude.doubleValue() + "," + longitude.doubleValue() + "&maxresults=1";
			url = new URL(req);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();

			if (responsecode != 200)
				throw new ConnectToGeolocationServiceException();

			jRdr = new JsonReader(new InputStreamReader(url.openStream()));

			JsonObject addrJson = JsonParser.parseReader(jRdr).getAsJsonObject().get("Response").getAsJsonObject()
					.get("View").getAsJsonArray().get(0).getAsJsonObject().get("Result").getAsJsonArray().get(0)
					.getAsJsonObject().get("Location").getAsJsonObject().get("Address").getAsJsonObject();

			addr.setCity(addrJson.get("City").getAsString());
			addr.setCountry(addrJson.get("AdditionalData").getAsJsonArray().get(0).getAsJsonObject().get("value")
					.getAsString());
			String num = "";
			if (addrJson.get("HouseNumber") != null)
				num = addrJson.get("HouseNumber").getAsString();
			if (num.isEmpty())
				addr.setRoad(addrJson.get("Street").getAsString());
			else
				addr.setRoad(num + ", " + addrJson.get("Street").getAsString());

			SessionView.setAddressSetOnMap(addr);

		} catch (IOException e) {
			e.getLocalizedMessage();
		}
	}

	public boolean cancelEvent(EventBean eventBean) {
		EventDAO dao = new EventDAO();
		Event event = dao.findById(eventBean.getEventId());
		if (event.getOrganizerUser().getUsername().equals(SessionView.getUsername())) {
			return dao.deleteEvent(eventBean.getEventId());
		}

		return dao.cancelParticipation(SessionView.getUsername(), eventBean.getEventId());
	}

	public Integer getNumberParticipants(EventBean eventBean) {
		EventDAO dao = new EventDAO();
		return dao.getNumberParticipants(eventBean.getEventId());
	}

	public boolean addReview(ReviewBean reviewBean) throws ReviewException {
		ReviewDAO dao = new ReviewDAO();
		if (dao.checkReview(reviewBean.getEventBean().getEventId(), SessionView.getUsername())) {
			throw new ReviewException();
		}
		Review review = dao.addReview(reviewBean);
		return (review != null);
	}

}
