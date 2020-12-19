package com.gianmarco.merletti.progetto_ispw.logic.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.gianmarco.merletti.progetto_ispw.logic.bean.AddressBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBeanView;
import com.gianmarco.merletti.progetto_ispw.logic.dao.EventDAO;
import com.gianmarco.merletti.progetto_ispw.logic.dao.UserDAO;
import com.gianmarco.merletti.progetto_ispw.logic.exception.InvalidFieldException;
import com.gianmarco.merletti.progetto_ispw.logic.model.Event;
import com.gianmarco.merletti.progetto_ispw.logic.model.User;
import com.gianmarco.merletti.progetto_ispw.logic.util.LevelEnum;
import com.gianmarco.merletti.progetto_ispw.logic.util.TypeEnum;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class EventController {

	public boolean createEvent(EventBeanView eventBean) throws InvalidFieldException {
		EventDAO dao = new EventDAO();
		User organizer = new UserDAO().findUserFromUsername(eventBean.getEventOrganizer());

		EventBean bean = new EventBean();
		bean.setEventAddress(eventBean.getEventAddress());
		bean.setEventCity(eventBean.getEventCity());
		bean.setEventCreationDate(eventBean.getEventCreationDate());
		bean.setEventDate(eventBean.getEventDate());
		bean.setEventTitle(eventBean.getEventTitle());
		bean.setEventDescription(eventBean.getEventDescription());
		bean.setEventLatitude(eventBean.getEventLatitude());
		bean.setEventLongitude(eventBean.getEventLongitude());
		bean.setEventLevel(LevelEnum.valueOf(organizer.getLevel()));
		bean.setEventDistance(eventBean.getEventDistance());
		bean.setEventType(TypeEnum.valueOf(eventBean.getEventType()));
		bean.setEventTime(eventBean.getEventTime());
		bean.setEventOrganizer(organizer);

		Event event = dao.addEvent(bean);
//		dao.joinEvent(SessionView.getUsername(), event.getId());
		return (event != null);
	}

	public void setEventForRequest(Double latitude, Double longitude) {
		EventDAO dao = new EventDAO();
		Event ev = dao.findByLatLong(latitude, longitude);
		SessionView.setEventSetOnMap(ev);
		System.out.println("FIRED " + ev.getTitle());

	}

	public void setAddressForEvent(Double longitude, Double latitude)
			throws Exception {
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
				throw new Exception();

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

			String label = addr.getRoad() + ", " + addr.getCity() + ", " + addr.getCountry();
			SessionView.setAddressSetOnMap(addr);

		} catch (IOException e) {
			e.getLocalizedMessage();
		}
	}
}
