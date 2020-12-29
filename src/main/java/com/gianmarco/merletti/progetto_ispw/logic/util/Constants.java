package com.gianmarco.merletti.progetto_ispw.logic.util;

public final class Constants {

	private Constants(){
		// private constructor
	}

	public static String USERNAME_DB = "root";
	public static String PSW_DB = "2202";
	public static String URL_DB = "jdbc:mysql://localhost:3306/progettoispw?serverTimezone=UTC";

	private static String SELECT_ALL_FROM = "SELECT * FROM ";
	private static String INSERT_INTO = "INSERT INTO ";

	private static String TABLE_CITY = "city";
	private static String TABLE_EVENT = "event";
	private static String TABLE_JOIN = "organization";
	private static String TABLE_REQUEST = "request";
	private static String TABLE_USER = "user";

	public static String COLUMN_NAME = "name";
	public static String COLUMN_LATITUDE = "latitude";
	public static String COLUMN_LONGITUDE = "longitude";
	public static String COLUMN_BORDERS = "borders";
	public static String COLUMN_IDEVENT = "idevent";
	public static String COLUMN_TITLE = "title";
	public static String COLUMN_DESCRIPTION = "description";
	public static String COLUMN_CREATION_DATE = "creation_date";
	public static String COLUMN_DATE = "date";
	public static String COLUMN_TIME = "time";
	public static String COLUMN_ADDRESS = "address";
	public static String COLUMN_CITY = "city";
	public static String COLUMN_ORGANIZER = "organizer";
	public static String COLUMN_LEVEL = "level";
	public static String COLUMN_DISTANCE = "distance";
	public static String COLUMN_TYPE = "type";
	public static String COLUMN_IDREQUEST = "idrequest";
	public static String COLUMN_USER = "user";
	public static String COLUMN_MESSAGE = "message";
	public static String COLUMN_STATUS = "status";
	public static String COLUMN_PASSWORD = "password";
	public static String COLUMN_USERNAME = "username";
	public static String COLUMN_SURNAME = "surname";


	public static String SQL_FIND_CITY = SELECT_ALL_FROM + TABLE_CITY
				+ " WHERE (" + COLUMN_NAME + "=?);";

	public static String SQL_ADD_EVENT = INSERT_INTO + TABLE_EVENT
			+ " (" + COLUMN_TITLE + ", "
			+ COLUMN_DESCRIPTION + ", "
			+ COLUMN_CREATION_DATE + ", "
			+ COLUMN_DATE + ", "
			+ COLUMN_TIME + ", "
			+ COLUMN_ADDRESS + ", "
			+ COLUMN_CITY + ", "
			+ COLUMN_LATITUDE + ", "
			+ COLUMN_LONGITUDE + ", "
			+ COLUMN_ORGANIZER + ", "
			+ COLUMN_LEVEL + ", "
			+ COLUMN_DISTANCE + ", "
			+ COLUMN_TYPE + ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";

	public static String SQL_FIND_EVENTS = SELECT_ALL_FROM + TABLE_EVENT + ";";

	public static String SQL_FIND_EVENT_FROM_ID = SELECT_ALL_FROM + TABLE_EVENT
			+ " WHERE (" + COLUMN_IDEVENT + "=?);";

	public static String SQL_FIND_JOIN_EVENTS_FROM_USERNAME = SELECT_ALL_FROM + TABLE_EVENT
			+ " INNER JOIN " + TABLE_JOIN + "ON organization.idevent=event.idevent "
			+ " WHERE (" + COLUMN_USERNAME + "=?);";

	public static String SQL_FIND_EVENT_FROM_LATLONG = SELECT_ALL_FROM + TABLE_EVENT
			+ " WHERE (" + COLUMN_LATITUDE + "=? AND " + COLUMN_LONGITUDE + "=?);";

	public static String SQL_JOIN_EVENT = INSERT_INTO + TABLE_JOIN
			+ " VALUES (?,?);";
}
