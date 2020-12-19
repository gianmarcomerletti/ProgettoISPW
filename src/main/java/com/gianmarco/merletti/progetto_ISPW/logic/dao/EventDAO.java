package com.gianmarco.merletti.progetto_ISPW.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gianmarco.merletti.progetto_ISPW.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ISPW.logic.model.Event;
import com.gianmarco.merletti.progetto_ISPW.logic.model.Request;
import com.gianmarco.merletti.progetto_ISPW.logic.model.User;
import com.gianmarco.merletti.progetto_ISPW.logic.util.DBConnect;

public class EventDAO {

	public Event addEvent(EventBean eventBean) {
		Event event = new Event();
		event.setFromBean(eventBean);

		Connection conn = DBConnect.getConnection();
		String query = ("INSERT INTO event (title, description, creation_date, date, time, address, city, latitude, longitude, "
				+ "organizer, level, distance, type) "
				+ "VALUES ('"
				+ event.getTitle() + "', '"
				+ event.getDescription() + "', '"
				+ event.getCreationDate() + "', '"
				+ event.getDate() + "', '"
				+ event.getTime() + "', '"
				+ event.getAddress() + "', '"
				+ event.getCity() + "', '"
				+ event.getLatitude() + "', '"
				+ event.getLongitude() + "', '"
				+ event.getOrganizerUser().getUsername() + "', '"
				+ event.getLevel() + "', '"
				+ event.getDistance() + "', '"
				+ event.getType() + "');");
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			System.out.println("DB QUERY OK");
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return event;
	}

	public Event findById(Integer id) {
		Connection conn = DBConnect.getConnection();
		String query = "SELECT * FROM event "
				+ "WHERE (ID='" + id.toString() + "');";
		try {
			PreparedStatement st = conn.prepareStatement(query);
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

				st.close();
				conn.close();
				return event;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Event> findAll() {

		Connection conn = DBConnect.getConnection();
		String query = "SELECT * FROM event;";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			System.out.println("DB QUERY OK");
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

			st.close();
			conn.close();
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
		Connection conn = DBConnect.getConnection();
		String query = "SELECT * FROM event "
				+ "WHERE (latitude='" + latitude + "' AND "
				+ "longitude='" + longitude + "');";
		try {
			PreparedStatement st = conn.prepareStatement(query);
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

				st.close();
				conn.close();
				return event;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
