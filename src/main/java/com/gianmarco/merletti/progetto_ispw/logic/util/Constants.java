package com.gianmarco.merletti.progetto_ispw.logic.util;

public final class Constants {

	private Constants(){
		// private constructor
	}

	public static final String USERNAME_DB = "root";
	public static final String PSW_DB = "2202";
	public static final String URL_DB = "jdbc:mysql://localhost:3306/progettoispw?serverTimezone=UTC";

	private static final String SELECT_ALL_FROM = "SELECT * FROM ";
	private static final String INSERT_INTO = "INSERT INTO ";
	private static final String DELETE_FROM = "DELETE FROM ";
	private static final String UPDATE = "UPDATE ";
	private static final String WHERE_COND = " WHERE (";
	private static final String END_STRING = "=?);";
	private static final String AND_STRING = "=? AND ";

	private static final String TABLE_CITY = "city";
	private static final String TABLE_EVENT = "event";
	private static final String TABLE_JOIN = "organization";
	private static final String TABLE_REQUEST = "request";
	private static final String TABLE_USER = "user";

	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_LATITUDE = "latitude";
	public static final String COLUMN_LONGITUDE = "longitude";
	public static final String COLUMN_BORDERS = "borders";
	public static final String COLUMN_IDEVENT = "idevent";
	public static final String COLUMN_TITLE = "title";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_CREATION_DATE = "creation_date";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_TIME = "time";
	public static final String COLUMN_ADDRESS = "address";
	public static final String COLUMN_CITY = "city";
	public static final String COLUMN_ORGANIZER = "organizer";
	public static final String COLUMN_LEVEL = "level";
	public static final String COLUMN_DISTANCE = "distance";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_IDREQUEST = "idrequest";
	public static final String COLUMN_USER = "user";
	public static final String COLUMN_MESSAGE = "message";
	public static final String COLUMN_STATUS = "status";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_USERNAME = "username";
	public static final String COLUMN_SURNAME = "surname";


	public static final String SQL_FIND_CITY = SELECT_ALL_FROM + TABLE_CITY
				+ WHERE_COND + COLUMN_NAME + END_STRING;

	public static final String SQL_ADD_EVENT = INSERT_INTO + TABLE_EVENT
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

	public static final String SQL_FIND_EVENTS = SELECT_ALL_FROM + TABLE_EVENT + ";";

	public static final String SQL_FIND_EVENT_FROM_ID = SELECT_ALL_FROM + TABLE_EVENT
			+ WHERE_COND + COLUMN_IDEVENT + END_STRING;

	public static final String SQL_FIND_JOIN_EVENTS_FROM_USERNAME = SELECT_ALL_FROM + TABLE_EVENT
			+ " INNER JOIN " + TABLE_JOIN + " ON organization.idevent=event.idevent"
			+ WHERE_COND + COLUMN_USERNAME + END_STRING;

	public static final String SQL_FIND_EVENT_FROM_LATLONG = SELECT_ALL_FROM + TABLE_EVENT
			+ WHERE_COND + COLUMN_LATITUDE + AND_STRING + COLUMN_LONGITUDE + END_STRING;

	public static final String SQL_JOIN_EVENT = INSERT_INTO + TABLE_JOIN
			+ " VALUES (?,?);";

	public static final String SQL_DELETE_JOIN = DELETE_FROM + TABLE_JOIN
			+ WHERE_COND + COLUMN_IDEVENT + AND_STRING + COLUMN_USERNAME + END_STRING;

	public static final String SQL_DELETE_EVENT = DELETE_FROM + TABLE_EVENT
			+ WHERE_COND + COLUMN_IDEVENT + END_STRING;

	public static final String SQL_FIND_JOIN_FROM_USERNAME = SELECT_ALL_FROM + TABLE_JOIN
			+ WHERE_COND + COLUMN_IDEVENT + AND_STRING + COLUMN_USERNAME + END_STRING;

	public static final String SQL_FIND_JOIN_FROM_EVENT = SELECT_ALL_FROM + TABLE_JOIN
			+ WHERE_COND + COLUMN_IDEVENT + END_STRING;

	public static final String SQL_ADD_REQUEST = INSERT_INTO + TABLE_REQUEST
			+ " (" + COLUMN_CREATION_DATE + ", "
			+ COLUMN_IDEVENT + ", "
			+ COLUMN_USER + ", "
			+ COLUMN_MESSAGE + ", "
			+ COLUMN_STATUS + ") VALUES (?,?,?,?,?);";

	public static final String SQL_FIND_REQUEST_FROM_ID = SELECT_ALL_FROM + TABLE_REQUEST
			+ WHERE_COND + COLUMN_IDREQUEST + END_STRING;

	public static final String SQL_FIND_REQUESTS_FROM_USER = SELECT_ALL_FROM + TABLE_REQUEST
			+ " INNER JOIN " + TABLE_EVENT + " ON event.idevent=request.idevent"
			+ WHERE_COND + COLUMN_ORGANIZER + END_STRING;

	public static final String SQL_UPDATE_REQUEST_STATUS = UPDATE + TABLE_REQUEST
			+ " SET " + COLUMN_STATUS + "=? "
			+ WHERE_COND + COLUMN_IDREQUEST + END_STRING;

	public static final String SQL_ADD_USER = INSERT_INTO + TABLE_USER
			+ " VALUES (?,?,?,?,?,?);";

	public static final String SQL_FIND_USER_FROM_USERNAME = SELECT_ALL_FROM + TABLE_USER
			+ WHERE_COND + COLUMN_USERNAME + END_STRING;

	public static final String SQL_FIND_USER_FROM_CREDENTIALS = SELECT_ALL_FROM + TABLE_USER
			+ WHERE_COND + COLUMN_USERNAME + AND_STRING + COLUMN_PASSWORD + END_STRING;

}
