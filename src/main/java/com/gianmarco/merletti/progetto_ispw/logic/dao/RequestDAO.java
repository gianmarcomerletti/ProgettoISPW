package com.gianmarco.merletti.progetto_ispw.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean;
import com.gianmarco.merletti.progetto_ispw.logic.model.Event;
import com.gianmarco.merletti.progetto_ispw.logic.model.Request;
import com.gianmarco.merletti.progetto_ispw.logic.util.DBConnect;

public class RequestDAO {

	public Request addRequest(RequestBean requestBean) {
		Request request = new Request();
		request.setFromBean(requestBean);

		String query = ("INSERT INTO request (creation_date, ID_event) "
				+ "VALUES ('"
				+ request.getCreationDate() + "', '"
				+ request.getEvent().getId() + "');");
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request;
	}

	public Request findById(Integer id) {
		String query = "SELECT * FROM request "
				+ "WHERE (ID='" + id.toString() + "');";
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Request request = new Request();
				request.setIdRequest(rs.getInt(1));
				request.setCreationDate(rs.getDate(2));
				request.setEvent(new EventDAO().findById(rs.getInt(3)));
				return request;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
