package com.gianmarco.merletti.progetto_ISPW.logic.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnect {
	private final static String username = "root";
	private final static String psw = "2202";
	private final static String jdbcURL = "jdbc:mysql://93.42.111.41:3306/progettoispw?serverTimezone=UTC";

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Connection conn = DriverManager.getConnection(jdbcURL, username, psw);
			System.out.println("Connessione al database OK");
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Impossibile connettersi al database");
		}
		return null;
	}
}
