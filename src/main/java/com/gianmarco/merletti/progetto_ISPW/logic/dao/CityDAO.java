package com.gianmarco.merletti.progetto_ISPW.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import com.gianmarco.merletti.progetto_ISPW.logic.model.City;
import com.gianmarco.merletti.progetto_ISPW.logic.util.CityEnum;
import com.gianmarco.merletti.progetto_ISPW.logic.util.DBConnect;

public class CityDAO {

	public City getCity(CityEnum cityEnum) {
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM City "
					+ "WHERE (name='" + cityEnum.toString()
					+ "');");
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				City result = new City();
				result.setName(rs.getString("name"));
				result.setLatitude(rs.getDouble("latitude"));
				result.setLongitude(rs.getDouble("longitude"));

				String bordersArray = rs.getString("borders");
				String[] borders = bordersArray.split("/");

				Double[][] db = new Double[borders.length][];
				int r = 0;
				for (String border:borders) {
					db[r++] = Arrays.stream(border.split(","))
									.map(Double::valueOf)
									.toArray(Double[]::new);
				}
				result.setBorders(db);
				return result;
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}