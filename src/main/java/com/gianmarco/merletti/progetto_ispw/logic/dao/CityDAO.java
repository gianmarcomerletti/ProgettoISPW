package com.gianmarco.merletti.progetto_ispw.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import com.gianmarco.merletti.progetto_ispw.logic.model.City;
import com.gianmarco.merletti.progetto_ispw.logic.util.CityEnum;
import com.gianmarco.merletti.progetto_ispw.logic.util.DBConnect;
import static com.gianmarco.merletti.progetto_ispw.logic.util.Constants.*;

public class CityDAO {

	public City getCity(CityEnum cityEnum) {
		Connection conn = DBConnect.getInstance().getConnection();
		try (PreparedStatement statement = conn.prepareStatement(SQL_FIND_CITY)) {

			statement.setString(1, cityEnum.toString());
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				City result = new City();
				result.setName(rs.getString(COLUMN_NAME));
				result.setLatitude(rs.getDouble(COLUMN_LATITUDE));
				result.setLongitude(rs.getDouble(COLUMN_LONGITUDE));

				String bordersArray = rs.getString(COLUMN_BORDERS);
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
