package com.gianmarco.merletti.progetto_ispw.logic.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.gianmarco.merletti.progetto_ispw.logic.util.Constants.*;

public class DBConnect {

	// this is a SINGLETON

	private static DBConnect instance;
	private Connection connection;

	private DBConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(URL_DB, USERNAME_DB, "");
			Logger.getLogger("together_run").log(Level.INFO, "Database connesso");
		} catch (ClassNotFoundException | SQLException e) {
			Logger.getLogger("together_run").log(Level.SEVERE, "Impossibile connettersi al database");
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public static DBConnect getInstance() {
		try {
			if (instance == null || instance.getConnection().isClosed())
				instance = new DBConnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return instance;
	}
}
