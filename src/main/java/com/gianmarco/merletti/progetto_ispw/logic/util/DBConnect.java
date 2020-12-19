package com.gianmarco.merletti.progetto_ispw.logic.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBConnect {

	// this is a SINGLETON

	private static DBConnect instance;
	private Connection connection;

	private String username = "root";
	private String psw = "2202";
	private String jdbcURL = "jdbc:mysql://93.42.111.41:3306/progettoispw?serverTimezone=UTC";

	private DBConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(jdbcURL, username, psw);
			Logger.getLogger("together_run").log(Level.FINE, "Database connesso");
		} catch (ClassNotFoundException | SQLException e) {
			Logger.getLogger("together_run").log(Level.SEVERE, "Impossibile connettersi al database");
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static DBConnect getInstance() throws SQLException {
		if (instance == null || instance.getConnection().isClosed())
			instance = new DBConnect();
		return instance;
	}
}
