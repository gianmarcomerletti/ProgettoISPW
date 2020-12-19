package com.gianmarco.merletti.progetto_ispw.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import com.gianmarco.merletti.progetto_ispw.logic.model.City;
import com.gianmarco.merletti.progetto_ispw.logic.util.CityEnum;
import com.gianmarco.merletti.progetto_ispw.logic.util.DBConnect;

public class CityDAO {

	public City getCity(CityEnum cityEnum) {
		Connection conn = DBConnect.getInstance().getConnection();
		String query = "SELECT * FROM City "
				+ "WHERE (name='" + cityEnum.toString() + "');";
		try (PreparedStatement statement = conn.prepareStatement(query)) {

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

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
