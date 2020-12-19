package com.gianmarco.merletti.progetto_ispw.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import com.gianmarco.merletti.progetto_ispw.logic.bean.UserBean;
import com.gianmarco.merletti.progetto_ispw.logic.model.User;
import com.gianmarco.merletti.progetto_ispw.logic.util.DBConnect;

public class UserDAO {

	public void insertUser(UserBean userBean) {
		User user = new User();
		user.setFromBean(userBean);
		System.out.println(userBean.getFirstName());
		Connection conn = DBConnect.getConnection();
		String query = ("INSERT INTO progettoispw.user "
				+ "VALUES ('"
				+ user.getUsername() + "', '"
				+ user.getPwd() + "', '"
				+ user.getName() + "', '"
				+ user.getSurname() + "', '"
				+ user.getLevel() + "', '"
				+ user.getCity() + "');");
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.executeUpdate();
			System.out.println("DB QUERY OK");
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean userExists(String username) {
		Connection conn = DBConnect.getConnection();
		boolean result = false;
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM user "
					+ "WHERE (username='" + username
					+ "');");
			ResultSet rs = statement.executeQuery();
			result = rs.next();
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public User findUser(UserBean userBean) {
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM user "
					+ "WHERE (username='" + userBean.getUsername() + "' AND "
					+ "password='" + userBean.getPassword()
					+ "');");
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				User result = new User();
				result.setUsername(rs.getString("username"));
				result.setPwd(rs.getString("password"));
				result.setName(rs.getString("name"));
				result.setSurname(rs.getString("surname"));
				result.setLevel(rs.getString("level"));
				result.setCity(rs.getString("city"));
				conn.close();
				return result;
			}
			statement.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public User findUserFromUsername(String username) {
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM user "
					+ "WHERE (username='" + username
					+ "');");
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				User result = new User();
				result.setUsername(rs.getString("username"));
				result.setPwd(rs.getString("password"));
				result.setName(rs.getString("name"));
				result.setSurname(rs.getString("surname"));
				result.setLevel(rs.getString("level"));
				result.setCity(rs.getString("city"));
				conn.close();
				return result;
			}
			statement.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
