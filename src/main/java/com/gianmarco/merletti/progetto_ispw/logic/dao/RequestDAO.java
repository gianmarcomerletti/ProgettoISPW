package com.gianmarco.merletti.progetto_ispw.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean;
import com.gianmarco.merletti.progetto_ispw.logic.model.Request;
import com.gianmarco.merletti.progetto_ispw.logic.util.DBConnect;
import com.gianmarco.merletti.progetto_ispw.logic.util.Status;
import static com.gianmarco.merletti.progetto_ispw.logic.util.Constants.*;

public class RequestDAO {

	public Request addRequest(RequestBean requestBean) {
		Connection conn = DBConnect.getInstance().getConnection();
		Request request = new Request();
		request.setFromBean(requestBean);
		request.setStatus(Status.PENDING.toString());

		try (PreparedStatement st = conn.prepareStatement(SQL_ADD_REQUEST, Statement.RETURN_GENERATED_KEYS)) {
			st.setDate(1, request.getCreationDate());
			st.setInt(2, request.getEvent());
			st.setString(3, request.getUser());
			st.setString(4, request.getMessage());
			st.setString(5, request.getStatus());
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			rs.next();
			request.setIdRequest(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request;
	}

	public Request findById(Integer id) {
		Connection conn = DBConnect.getInstance().getConnection();
		try (PreparedStatement st = conn.prepareStatement(SQL_FIND_REQUEST_FROM_ID);) {
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				Request request = new Request();
				request.setIdRequest(rs.getInt(COLUMN_IDREQUEST));
				request.setCreationDate(rs.getDate(COLUMN_CREATION_DATE));
				request.setEvent(rs.getInt(COLUMN_IDEVENT));
				request.setUser(rs.getString(COLUMN_USER));
				request.setMessage(rs.getString(COLUMN_MESSAGE));
				request.setStatus(rs.getString(COLUMN_STATUS));
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
		try (PreparedStatement st = conn.prepareStatement(SQL_FIND_REQUESTS_FROM_USER)) {
			st.setString(1, username);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Request requestElem = new Request();
				requestElem.setIdRequest(rs.getInt(COLUMN_IDREQUEST));
				requestElem.setCreationDate(rs.getDate(COLUMN_CREATION_DATE));
				requestElem.setEvent(rs.getInt(COLUMN_IDEVENT));
				requestElem.setUser(rs.getString(COLUMN_USER));
				requestElem.setMessage(rs.getString(COLUMN_MESSAGE));
				requestElem.setStatus(rs.getString(COLUMN_STATUS));
				result.add(requestElem);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Request activateRequest(Integer reqId) {
		Connection conn = DBConnect.getInstance().getConnection();
		Request request = findById(reqId);
		try (PreparedStatement st = conn.prepareStatement(SQL_UPDATE_REQUEST_STATUS);) {
			st.setString(1, Status.ACCEPTED.toString());
			st.setInt(2, request.getIdRequest());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request;
	}

	public Request refuseRequest(Integer reqId) {
		Connection conn = DBConnect.getInstance().getConnection();
		Request request = findById(reqId);
		try (PreparedStatement st = conn.prepareStatement(SQL_UPDATE_REQUEST_STATUS);) {
			st.setString(1, Status.REJECTED.toString());
			st.setInt(2, request.getIdRequest());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return request;
	}

	public boolean checkRequest(RequestBean bean) {
		Connection conn = DBConnect.getInstance().getConnection();
		boolean result = false;

		try (PreparedStatement st = conn.prepareStatement(SQL_FIND_PENDING_REQUEST_FROM_USER);) {
			st.setString(3, Status.PENDING.toString());
			st.setInt(2, bean.getRequestEvent().getEventId());
			st.setString(1, bean.getRequestUser());
			ResultSet rs = st.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

}
