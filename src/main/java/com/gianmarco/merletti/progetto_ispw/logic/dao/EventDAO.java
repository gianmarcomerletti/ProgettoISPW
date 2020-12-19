package com.gianmarco.merletti.progetto_ispw.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ispw.logic.model.Event;
import com.gianmarco.merletti.progetto_ispw.logic.model.Request;
import com.gianmarco.merletti.progetto_ispw.logic.model.User;
import com.gianmarco.merletti.progetto_ispw.logic.util.DBConnect;

public class EventDAO {

	public Event addEvent(EventBean eventBean) {
		Event eventToAdd = new Event();
		eventToAdd.setFromBean(eventBean);

		String query = ("INSERT INTO event (title, description, creation_date, date, time, address, city, latitude, longitude, "
				+ "organizer, level, distance, type) "
				+ "VALUES ('"
				+ eventToAdd.getTitle() + "', '"
				+ eventToAdd.getDescription() + "', '"
				+ eventToAdd.getCreationDate() + "', '"
				+ eventToAdd.getDate() + "', '"
				+ eventToAdd.getTime() + "', '"
				+ eventToAdd.getAddress() + "', '"
				+ eventToAdd.getCity() + "', '"
				+ eventToAdd.getLatitude() + "', '"
				+ eventToAdd.getLongitude() + "', '"
				+ eventToAdd.getOrganizerUser().getUsername() + "', '"
				+ eventToAdd.getLevel() + "', '"
				+ eventToAdd.getDistance() + "', '"
				+ eventToAdd.getType() + "');");
		try (Connection conn = DBConnect.getInstance().getConnection();
				PreparedStatement st = conn.prepareStatement(query)){
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventToAdd;
	}

	public Event findById(Integer id) {
		String query = "SELECT * FROM event "
				+ "WHERE (ID='" + id.toString() + "');";
		try (Connection conn = DBConnect.getInstance().getConnection();
				PreparedStatement st = conn.prepareStatement(query)){

			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Event eventFound = new Event();
				eventFound.setId(rs.getInt(1));
				eventFound.setTitle(rs.getString(2));
				eventFound.setDescription(rs.getString(3));
				eventFound.setCreationDate(rs.getDate(4));
				eventFound.setDate(rs.getDate(5));
				eventFound.setTime(rs.getTime(6));
				eventFound.setAddress(rs.getString(7));
				eventFound.setCity(rs.getString(8));
				eventFound.setLatitude(rs.getDouble(9));
				eventFound.setLongitude(rs.getDouble(10));
				eventFound.setOrganizerUser(new UserDAO().findUserFromUsername(rs.getString(11)));
				eventFound.setLevel(rs.getString(12));
				eventFound.setDistance(rs.getInt(13));
				eventFound.setType(rs.getString(14));
				return eventFound;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Event> findAll() {

		String query = "SELECT * FROM event;";
		try (Connection conn = DBConnect.getInstance().getConnection();
				PreparedStatement st = conn.prepareStatement(query)) {
			ResultSet rs = st.executeQuery();
			List<Event> result = new ArrayList<Event>();

			while (rs.next()) {
				Event event = new Event();
				event.setId(rs.getInt(1));
				event.setTitle(rs.getString(2));
				event.setDescription(rs.getString(3));
				event.setCreationDate(rs.getDate(4));
				event.setDate(rs.getDate(5));
				event.setTime(rs.getTime(6));
				event.setAddress(rs.getString(7));
				event.setCity(rs.getString(8));
				event.setLatitude(rs.getDouble(9));
				event.setLongitude(rs.getDouble(10));
				event.setOrganizerUser(new UserDAO().findUserFromUsername(rs.getString(11)));
				event.setLevel(rs.getString(12));
				event.setDistance(rs.getInt(13));
				event.setType(rs.getString(14));
				result.add(event);
			}
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void assignRequest(Integer requestId, Integer eventId) {
		Event event = findById(eventId);
		Request request = new RequestDAO().findById(requestId);

		List<Request> requests = event.getRequests();
		requests.add(request);
		event.setRequests(requests);
		//updateEvent(event);
	}

	public Event findByLatLong(Double latitude, Double longitude) {
		String query = "SELECT * FROM event "
				+ "WHERE (latitude='" + latitude + "' AND "
				+ "longitude='" + longitude + "');";
		try (Connection conn = DBConnect.getInstance().getConnection();
				PreparedStatement st = conn.prepareStatement(query)) {
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Event event = new Event();
				event.setId(rs.getInt(1));
				event.setTitle(rs.getString(2));
				event.setDescription(rs.getString(3));
				event.setCreationDate(rs.getDate(4));
				event.setDate(rs.getDate(5));
				event.setTime(rs.getTime(6));
				event.setAddress(rs.getString(7));
				event.setCity(rs.getString(8));
				event.setLatitude(rs.getDouble(9));
				event.setLongitude(rs.getDouble(10));
				event.setOrganizerUser(new UserDAO().findUserFromUsername(rs.getString(11)));
				event.setLevel(rs.getString(12));
				event.setDistance(rs.getInt(13));
				event.setType(rs.getString(14));
				return event;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
