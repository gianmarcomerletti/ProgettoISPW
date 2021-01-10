package com.gianmarco.merletti.progetto_ispw.logic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.gianmarco.merletti.progetto_ispw.logic.util.Constants.*;
import com.gianmarco.merletti.progetto_ispw.logic.bean.ReviewBean;
import com.gianmarco.merletti.progetto_ispw.logic.model.Event;
import com.gianmarco.merletti.progetto_ispw.logic.model.Review;
import com.gianmarco.merletti.progetto_ispw.logic.util.DBConnect;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;

public class ReviewDAO {

	public List<Review> findUserReviews(String username) {
		Connection conn = DBConnect.getInstance().getConnection();
		List<Review> result = new ArrayList<>();
		List<Integer> events = new ArrayList<>();
		try (PreparedStatement st = conn.prepareStatement(SQL_FIND_REVIEWS_FROM_USERNAME)) {
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Review reviewUserElem = new Review();
				reviewUserElem.setUser(username);
				reviewUserElem.setValue(rs.getInt(COLUMN_VALUE));
				reviewUserElem.setText(rs.getString(COLUMN_TEXT));
				reviewUserElem.setImage(rs.getBytes(COLUMN_IMAGE));
				events.add(rs.getInt(COLUMN_IDEVENT));
				result.add(reviewUserElem);
			}

			EventDAO dao = new EventDAO();
			for (int i=0; i<events.size(); i++) {
				Event event = dao.findById(events.get(i));
				result.get(i).setEvent(event);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<Review> findReceivedReviews(String username) {
		Connection conn = DBConnect.getInstance().getConnection();
		List<Review> result = new ArrayList<>();
		List<Integer> events = new ArrayList<>();
		try (PreparedStatement st = conn.prepareStatement(SQL_FIND_REVIEWS_FROM_ORGANIZER)) {
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Review reviewElem = new Review();
				reviewElem.setUser(username);
				reviewElem.setValue(rs.getInt(COLUMN_VALUE));
				reviewElem.setText(rs.getString(COLUMN_TEXT));
				reviewElem.setImage(rs.getBytes(COLUMN_IMAGE));
				events.add(rs.getInt(COLUMN_IDEVENT));
				result.add(reviewElem);
			}

			EventDAO dao = new EventDAO();
			for (int i=0; i<events.size(); i++) {
				Event event = dao.findById(events.get(i));
				result.get(i).setEvent(event);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean checkReview(Integer eventId, String username) {
		Connection conn = DBConnect.getInstance().getConnection();
		boolean result = false;

		try (PreparedStatement statement = conn.prepareStatement(SQL_FIND_REVIEW);) {
			statement.setInt(1, eventId);
			statement.setString(2, username);
			ResultSet rs = statement.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Review addReview(ReviewBean reviewBean) {
		Connection conn = DBConnect.getInstance().getConnection();
		Review review = new Review();
		review.setFromBean(reviewBean);
		review.setUser(SessionView.getUsername());

		try (PreparedStatement st = conn.prepareStatement(SQL_ADD_REVIEW);) {
			st.setString(1, review.getUser());
			st.setInt(2, review.getEvent().getId());
			st.setInt(3, review.getValue());
			st.setString(4, review.getText());
			st.setBytes(5, review.getImage());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return review;
	}

	public Integer getUserRating(String username) {
		Connection conn = DBConnect.getInstance().getConnection();
		Integer sum = 0;
		Integer count = 0;

		try (PreparedStatement st = conn.prepareStatement(SQL_FIND_REVIEWS_FROM_USERNAME)) {
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				sum += rs.getInt(COLUMN_VALUE);
				count++;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (count == 0)
			return sum;
		return (int) Math.round(sum.doubleValue()/count);
	}
}
