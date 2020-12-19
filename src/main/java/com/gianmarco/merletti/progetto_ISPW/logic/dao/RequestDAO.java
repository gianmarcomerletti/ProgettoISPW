package com.gianmarco.merletti.progetto_ISPW.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gianmarco.merletti.progetto_ISPW.logic.bean.RequestBean;
import com.gianmarco.merletti.progetto_ISPW.logic.model.Event;
import com.gianmarco.merletti.progetto_ISPW.logic.model.Request;
import com.gianmarco.merletti.progetto_ISPW.logic.util.DBConnect;

public class RequestDAO {

	public Request addRequest(RequestBean requestBean) {
		Request request = new Request();
		request.setFromBean(requestBean);

		Connection conn = DBConnect.getConnection();
		String query = ("INSERT INTO request (creation_date, ID_event) "
				+ "VALUES ('"
				+ request.getCreationDate() + "', '"
				+ request.getEvent().getId() + "');");
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request;
	}

	public Request findById(Integer id) {
		Connection conn = DBConnect.getConnection();
		String query = "SELECT * FROM request "
				+ "WHERE (ID='" + id.toString() + "');";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Request request = new Request();
				request.setIdRequest(rs.getInt(1));
				request.setCreationDate(rs.getDate(2));
				request.setEvent(new EventDAO().findById(rs.getInt(3)));

				st.close();
				conn.close();
				return request;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
