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
		Connection conn = DBConnect.getInstance().getConnection();
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
		try (PreparedStatement st = conn.prepareStatement(query)){
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventToAdd;
	}

	public Event findById(Integer id) {
		Connection conn = DBConnect.getInstance().getConnection();
		String query = "SELECT * FROM event "
				+ "WHERE (ID='" + id.toString() + "');";
		try (PreparedStatement st = conn.prepareStatement(query)){

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
				eventFound.setLevel(rs.getString(12));
				eventFound.setDistance(rs.getInt(13));
				eventFound.setType(rs.getString(14));
				eventFound.setOrganizerUser(new UserDAO().findUserFromUsername(rs.getString(11)));

				return eventFound;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Event> findAll() {
		Connection conn = DBConnect.getInstance().getConnection();
		List<Event> result = new ArrayList<Event>();
		List<String> organizers = new ArrayList<String>();
		String query = "SELECT * FROM event;";
		try (PreparedStatement st = conn.prepareStatement(query)) {
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Event eventElem = new Event();
				eventElem.setId(rs.getInt(1));
				eventElem.setTitle(rs.getString(2));
				eventElem.setDescription(rs.getString(3));
				eventElem.setCreationDate(rs.getDate(4));
				eventElem.setDate(rs.getDate(5));
				eventElem.setTime(rs.getTime(6));
				eventElem.setAddress(rs.getString(7));
				eventElem.setCity(rs.getString(8));
				eventElem.setLatitude(rs.getDouble(9));
				eventElem.setLongitude(rs.getDouble(10));
				organizers.add(rs.getString(11));
				eventElem.setLevel(rs.getString(12));
				eventElem.setDistance(rs.getInt(13));
				eventElem.setType(rs.getString(14));
				result.add(eventElem);
			}

			UserDAO dao = new UserDAO();
			for (int i=0; i<organizers.size(); i++) {
				User organizerUser = dao.findUserFromUsername(organizers.get(i));
				result.get(i).setOrganizerUser(organizerUser);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public void assignRequest(Integer requestId, Integer eventId) {
		Event event = findById(eventId);
		Request request = new RequestDAO().findById(requestId);

		List<Request> requests = event.getRequests();
		requests.add(request);
		event.setRequests(requests);
	}

	public Event findByLatLong(Double latitude, Double longitude) {
		Connection conn = DBConnect.getInstance().getConnection();
		String query = "SELECT * FROM event "
				+ "WHERE (latitude='" + latitude + "' AND "
				+ "longitude='" + longitude + "');";
		try (PreparedStatement st = conn.prepareStatement(query)) {
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Event eventFoundByLatLong = new Event();
				eventFoundByLatLong.setId(rs.getInt(1));
				eventFoundByLatLong.setTitle(rs.getString(2));
				eventFoundByLatLong.setDescription(rs.getString(3));
				eventFoundByLatLong.setCreationDate(rs.getDate(4));
				eventFoundByLatLong.setDate(rs.getDate(5));
				eventFoundByLatLong.setTime(rs.getTime(6));
				eventFoundByLatLong.setAddress(rs.getString(7));
				eventFoundByLatLong.setCity(rs.getString(8));
				eventFoundByLatLong.setLatitude(rs.getDouble(9));
				eventFoundByLatLong.setLongitude(rs.getDouble(10));
				eventFoundByLatLong.setLevel(rs.getString(12));
				eventFoundByLatLong.setDistance(rs.getInt(13));
				eventFoundByLatLong.setType(rs.getString(14));
				eventFoundByLatLong.setOrganizerUser(new UserDAO().findUserFromUsername(rs.getString(11)));
				return eventFoundByLatLong;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
