package com.gianmarco.merletti.progetto_ispw.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean;
import com.gianmarco.merletti.progetto_ispw.logic.model.Request;
import com.gianmarco.merletti.progetto_ispw.logic.util.DBConnect;
import com.gianmarco.merletti.progetto_ispw.logic.util.Status;

public class RequestDAO {

	public Request addRequest(RequestBean requestBean) {
		Connection conn = DBConnect.getInstance().getConnection();
		Request request = new Request();
		request.setFromBean(requestBean);
		request.setStatus(Status.PENDING.toString());

		String query = ("INSERT INTO request (creation_date, id_event, user, message, status) "
				+ "VALUES ('"
				+ request.getCreationDate() + "', '"
				+ request.getEvent().getId() + "', '"
				+ request.getUser() + "', '"
				+ request.getMessage() + "', '"
				+ request.getStatus() + "');");

		try (PreparedStatement st = conn.prepareStatement(query);) {
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request;
	}

	public Request findById(Integer id) {
		Connection conn = DBConnect.getInstance().getConnection();
		String query = "SELECT * FROM request "
				+ "WHERE (idrequest='" + id.toString() + "');";
		try (PreparedStatement st = conn.prepareStatement(query);) {
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Request request = new Request();
				request.setIdRequest(rs.getInt(1));
				request.setCreationDate(rs.getDate(2));
				request.setEvent(new EventDAO().findById(rs.getInt(3)));
				request.setUser(rs.getString(4));
				request.setMessage(rs.getString(5));
				request.setStatus(rs.getString(6));
				return request;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Request> findByUser(String username) {
		Connection conn = DBConnect.getInstance().getConnection();
		List<Request> result = new ArrayList<>();
		String query = "SELECT * FROM request "
				+ "WHERE (user='" + username + "');";
		try (PreparedStatement st = conn.prepareStatement(query)) {
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Request requestElem = new Request();
				requestElem.setIdRequest(rs.getInt(1));
				requestElem.setCreationDate(rs.getDate(2));
				requestElem.setEvent(new EventDAO().findById(rs.getInt(3)));
				requestElem.setUser(rs.getString(4));
				requestElem.setMessage(rs.getString(5));
				requestElem.setStatus(rs.getString(6));
				result.add(requestElem);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
