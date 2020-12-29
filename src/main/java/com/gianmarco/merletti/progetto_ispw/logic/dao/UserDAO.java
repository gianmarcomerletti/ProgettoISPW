package com.gianmarco.merletti.progetto_ispw.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gianmarco.merletti.progetto_ispw.logic.bean.UserBean;
import com.gianmarco.merletti.progetto_ispw.logic.model.User;
import com.gianmarco.merletti.progetto_ispw.logic.util.DBConnect;
import static com.gianmarco.merletti.progetto_ispw.logic.util.Constants.*;

public class UserDAO {

	public void insertUser(UserBean userBean) {
		Connection conn = DBConnect.getInstance().getConnection();
		User userToInsert = new User();
		userToInsert.setFromBean(userBean);
		try (PreparedStatement statement = conn.prepareStatement(SQL_ADD_USER)) {
			statement.setString(1, userToInsert.getUsername());
			statement.setString(2, userToInsert.getPwd());
			statement.setString(3, userToInsert.getName());
			statement.setString(4, userToInsert.getSurname());
			statement.setString(5, userToInsert.getLevel());
			statement.setString(6, userToInsert.getCity());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean userExists(String username) {
		Connection conn = DBConnect.getInstance().getConnection();
		boolean result = false;
		try (PreparedStatement statement = conn.prepareStatement(SQL_FIND_USER_FROM_USERNAME);) {
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public User findUser(UserBean userBean) {
		Connection conn = DBConnect.getInstance().getConnection();
		try (PreparedStatement statement = conn.prepareStatement(SQL_FIND_USER_FROM_CREDENTIALS)) {
			statement.setString(1, userBean.getUsername());
			statement.setString(2, userBean.getPassword());
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				User userFound = new User();
				userFound.setUsername(rs.getString(COLUMN_USERNAME));
				userFound.setPwd(rs.getString(COLUMN_PASSWORD));
				userFound.setName(rs.getString(COLUMN_NAME));
				userFound.setSurname(rs.getString(COLUMN_SURNAME));
				userFound.setLevel(rs.getString(COLUMN_LEVEL));
				userFound.setCity(rs.getString(COLUMN_CITY));
				return userFound;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public User findUserFromUsername(String username) {
		Connection conn = DBConnect.getInstance().getConnection();
		try (PreparedStatement statement = conn.prepareStatement(SQL_FIND_USER_FROM_USERNAME)) {
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				User userFoundFromUsername = new User();
				userFoundFromUsername.setUsername(rs.getString(COLUMN_USERNAME));
				userFoundFromUsername.setPwd(rs.getString(COLUMN_PASSWORD));
				userFoundFromUsername.setName(rs.getString(COLUMN_NAME));
				userFoundFromUsername.setSurname(rs.getString(COLUMN_SURNAME));
				userFoundFromUsername.setLevel(rs.getString(COLUMN_LEVEL));
				userFoundFromUsername.setCity(rs.getString(COLUMN_CITY));
				return userFoundFromUsername;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
