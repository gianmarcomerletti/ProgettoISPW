package com.gianmarco.merletti.progetto_ispw.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gianmarco.merletti.progetto_ispw.logic.bean.UserBean;
import com.gianmarco.merletti.progetto_ispw.logic.model.User;
import com.gianmarco.merletti.progetto_ispw.logic.util.DBConnect;

public class UserDAO {

	private static final String SELECTQUERY_STRING = "SELECT * FROM user ";
	private static final String USERNAME_COND_STRING = "WHERE (username='";

	public void insertUser(UserBean userBean) {
		Connection conn = DBConnect.getInstance().getConnection();
		User userToInsert = new User();
		userToInsert.setFromBean(userBean);
		String query = ("INSERT INTO user "
				+ "VALUES ('"
				+ userToInsert.getUsername() + "', '"
				+ userToInsert.getPwd() + "', '"
				+ userToInsert.getName() + "', '"
				+ userToInsert.getSurname() + "', '"
				+ userToInsert.getLevel() + "', '"
				+ userToInsert.getCity() + "');");
		try (PreparedStatement statement = conn.prepareStatement(query)) {

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean userExists(String username) {
		Connection conn = DBConnect.getInstance().getConnection();
		String query = SELECTQUERY_STRING
				+ USERNAME_COND_STRING + username + "');";
		boolean result = false;

		try (PreparedStatement statement = conn.prepareStatement(query);) {
			ResultSet rs = statement.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public User findUser(UserBean userBean) {
		Connection conn = DBConnect.getInstance().getConnection();
		String query = SELECTQUERY_STRING
					+ USERNAME_COND_STRING + userBean.getUsername() + "' AND "
					+ "password='" + userBean.getPassword() + "');";
		try (PreparedStatement statement = conn.prepareStatement(query)) {

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				User userFound = new User();
				userFound.setUsername(rs.getString("username"));
				userFound.setPwd(rs.getString("password"));
				userFound.setName(rs.getString("name"));
				userFound.setSurname(rs.getString("surname"));
				userFound.setLevel(rs.getString("level"));
				userFound.setCity(rs.getString("city"));
				return userFound;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public User findUserFromUsername(String username) {
		Connection conn = DBConnect.getInstance().getConnection();
		String query = SELECTQUERY_STRING
				+ USERNAME_COND_STRING + username + "');";

		try (PreparedStatement statement = conn.prepareStatement(query)) {

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				User userFoundFromUsername = new User();
				userFoundFromUsername.setUsername(rs.getString("username"));
				userFoundFromUsername.setPwd(rs.getString("password"));
				userFoundFromUsername.setName(rs.getString("name"));
				userFoundFromUsername.setSurname(rs.getString("surname"));
				userFoundFromUsername.setLevel(rs.getString("level"));
				userFoundFromUsername.setCity(rs.getString("city"));
				return userFoundFromUsername;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
