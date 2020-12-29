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
import com.gianmarco.merletti.progetto_ispw.logic.model.User;
import com.gianmarco.merletti.progetto_ispw.logic.util.DBConnect;
import static com.gianmarco.merletti.progetto_ispw.logic.util.Constants.*;

public class EventDAO {

	public Event addEvent(EventBean eventBean) {
		Connection conn = DBConnect.getInstance().getConnection();
		Event eventToAdd = new Event();
		eventToAdd.setFromBean(eventBean);

		try (PreparedStatement st = conn.prepareStatement(SQL_ADD_EVENT, Statement.RETURN_GENERATED_KEYS))	{
			st.setString(1, eventToAdd.getTitle());
			st.setString(2, eventToAdd.getDescription());
			st.setDate(3, eventToAdd.getCreationDate());
			st.setDate(4, eventToAdd.getDate());
			st.setTime(5, eventToAdd.getTime());
			st.setString(6, eventToAdd.getAddress());
			st.setString(7, eventToAdd.getCity());
			st.setDouble(8, eventToAdd.getLatitude());
			st.setDouble(9, eventToAdd.getLongitude());
			st.setString(10, eventToAdd.getOrganizerUser().getUsername());
			st.setString(11, eventToAdd.getLevel());
			st.setInt(12, eventToAdd.getDistance());
			st.setString(13, eventToAdd.getType());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			rs.next();
			eventToAdd.setId(rs.getInt(COLUMN_IDEVENT));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return eventToAdd;
	}

	public Event findById(Integer id) {
		Connection conn = DBConnect.getInstance().getConnection();

		try (PreparedStatement st = conn.prepareStatement(SQL_FIND_EVENT_FROM_ID)){
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Event eventFound = new Event();
				eventFound.setId(rs.getInt(COLUMN_IDEVENT));
				eventFound.setTitle(rs.getString(COLUMN_TITLE));
				eventFound.setDescription(rs.getString(COLUMN_DESCRIPTION));
				eventFound.setCreationDate(rs.getDate(COLUMN_CREATION_DATE));
				eventFound.setDate(rs.getDate(COLUMN_DATE));
				eventFound.setTime(rs.getTime(COLUMN_TIME));
				eventFound.setAddress(rs.getString(COLUMN_ADDRESS));
				eventFound.setCity(rs.getString(COLUMN_CITY));
				eventFound.setLatitude(rs.getDouble(COLUMN_LATITUDE));
				eventFound.setLongitude(rs.getDouble(COLUMN_LONGITUDE));
				eventFound.setLevel(rs.getString(COLUMN_LEVEL));
				eventFound.setDistance(rs.getInt(COLUMN_DISTANCE));
				eventFound.setType(rs.getString(COLUMN_TYPE));
				eventFound.setOrganizerUser(new UserDAO().findUserFromUsername(rs.getString(COLUMN_ORGANIZER)));

				return eventFound;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Event> findAll() {
		Connection conn = DBConnect.getInstance().getConnection();
		List<Event> result = new ArrayList<>();
		List<String> organizers = new ArrayList<>();
		try (PreparedStatement st = conn.prepareStatement(SQL_FIND_EVENTS)) {
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Event eventElem = new Event();
				eventElem.setId(rs.getInt(COLUMN_IDEVENT));
				eventElem.setTitle(rs.getString(COLUMN_TITLE));
				eventElem.setDescription(rs.getString(COLUMN_DESCRIPTION));
				eventElem.setCreationDate(rs.getDate(COLUMN_CREATION_DATE));
				eventElem.setDate(rs.getDate(COLUMN_DATE));
				eventElem.setTime(rs.getTime(COLUMN_TIME));
				eventElem.setAddress(rs.getString(COLUMN_ADDRESS));
				eventElem.setCity(rs.getString(COLUMN_CITY));
				eventElem.setLatitude(rs.getDouble(COLUMN_LATITUDE));
				eventElem.setLongitude(rs.getDouble(COLUMN_LONGITUDE));
				organizers.add(rs.getString(COLUMN_ORGANIZER));
				eventElem.setLevel(rs.getString(COLUMN_LEVEL));
				eventElem.setDistance(rs.getInt(COLUMN_DISTANCE));
				eventElem.setType(rs.getString(COLUMN_TYPE));
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

	public List<Event> findJoinEvent(String username) {
		Connection conn = DBConnect.getInstance().getConnection();
		List<Event> result = new ArrayList<>();
		List<String> organizers = new ArrayList<>();
		try (PreparedStatement st = conn.prepareStatement(SQL_FIND_JOIN_EVENTS_FROM_USERNAME)) {
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Event eventJoinElem = new Event();
				eventJoinElem.setId(rs.getInt(COLUMN_IDEVENT));
				eventJoinElem.setTitle(rs.getString(COLUMN_TITLE));
				eventJoinElem.setDescription(rs.getString(COLUMN_DESCRIPTION));
				eventJoinElem.setCreationDate(rs.getDate(COLUMN_CREATION_DATE));
				eventJoinElem.setDate(rs.getDate(COLUMN_DATE));
				eventJoinElem.setTime(rs.getTime(COLUMN_TIME));
				eventJoinElem.setAddress(rs.getString(COLUMN_ADDRESS));
				eventJoinElem.setCity(rs.getString(COLUMN_CITY));
				eventJoinElem.setLatitude(rs.getDouble(COLUMN_LATITUDE));
				eventJoinElem.setLongitude(rs.getDouble(COLUMN_LONGITUDE));
				organizers.add(rs.getString(COLUMN_ORGANIZER));
				eventJoinElem.setLevel(rs.getString(COLUMN_LEVEL));
				eventJoinElem.setDistance(rs.getInt(COLUMN_DISTANCE));
				eventJoinElem.setType(rs.getString(COLUMN_TYPE));
				result.add(eventJoinElem);
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

	public Event findByLatLong(Double latitude, Double longitude) {
		Connection conn = DBConnect.getInstance().getConnection();
		try (PreparedStatement st = conn.prepareStatement(SQL_FIND_EVENT_FROM_LATLONG)) {
			st.setDouble(1, latitude);
			st.setDouble(2, longitude);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Event eventFoundByLatLong = new Event();
				eventFoundByLatLong.setId(rs.getInt(COLUMN_IDEVENT));
				eventFoundByLatLong.setTitle(rs.getString(COLUMN_TITLE));
				eventFoundByLatLong.setDescription(rs.getString(COLUMN_DESCRIPTION));
				eventFoundByLatLong.setCreationDate(rs.getDate(COLUMN_CREATION_DATE));
				eventFoundByLatLong.setDate(rs.getDate(COLUMN_DATE));
				eventFoundByLatLong.setTime(rs.getTime(COLUMN_TIME));
				eventFoundByLatLong.setAddress(rs.getString(COLUMN_ADDRESS));
				eventFoundByLatLong.setCity(rs.getString(COLUMN_CITY));
				eventFoundByLatLong.setLatitude(rs.getDouble(COLUMN_LATITUDE));
				eventFoundByLatLong.setLongitude(rs.getDouble(COLUMN_LONGITUDE));
				eventFoundByLatLong.setLevel(rs.getString(COLUMN_LEVEL));
				eventFoundByLatLong.setDistance(rs.getInt(COLUMN_DISTANCE));
				eventFoundByLatLong.setType(rs.getString(COLUMN_TYPE));
				eventFoundByLatLong.setOrganizerUser(new UserDAO().findUserFromUsername(rs.getString(COLUMN_ORGANIZER)));
				return eventFoundByLatLong;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean joinEvent(String username, Integer eventId) {
		Connection conn = DBConnect.getInstance().getConnection();

		try (PreparedStatement st = conn.prepareStatement(SQL_JOIN_EVENT)){
			st.setInt(1, eventId);
			st.setString(2, username);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;



	}

	public boolean cancelParticipation(String username, Integer eventId) {
		Connection conn = DBConnect.getInstance().getConnection();
		try (PreparedStatement st = conn.prepareStatement(SQL_DELETE_JOIN)){
			st.setInt(1, eventId);
			st.setString(2, username);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteEvent(Integer eventId) {
		Connection conn = DBConnect.getInstance().getConnection();
		try (PreparedStatement st = conn.prepareStatement(SQL_DELETE_EVENT)){
			st.setInt(1, eventId);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkParticipation(String username, Integer eventId) {
		Connection conn = DBConnect.getInstance().getConnection();
		boolean result = false;

		try (PreparedStatement statement = conn.prepareStatement(SQL_FIND_JOIN_FROM_USERNAME);) {
			statement.setInt(1, eventId);
			statement.setString(2, username);
			ResultSet rs = statement.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Integer getNumberParticipants(Integer eventId) {
		Connection conn = DBConnect.getInstance().getConnection();
		int counter = 0;
		try (PreparedStatement statement = conn.prepareStatement(SQL_FIND_JOIN_FROM_EVENT);) {
			statement.setInt(1, eventId);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
				counter++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}

}
