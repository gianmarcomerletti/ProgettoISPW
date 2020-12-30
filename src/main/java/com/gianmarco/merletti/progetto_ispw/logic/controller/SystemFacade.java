package com.gianmarco.merletti.progetto_ispw.logic.controller;

import java.util.List;

import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.ReviewBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.UserBean;
import com.gianmarco.merletti.progetto_ispw.logic.exception.ConnectToGeolocationServiceException;
import com.gianmarco.merletti.progetto_ispw.logic.exception.UserNotFoundException;
import com.gianmarco.merletti.progetto_ispw.logic.util.CityEnum;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;

public class SystemFacade {

	public UserBean isSignedUp(UserBean userBean) throws UserNotFoundException {
		return new LoginController().checkUser(userBean);
	}

	public boolean signupUser(UserBean user) {
		return new LoginController().signupUser(user);
	}

	public boolean createEvent(EventBean eventBean) {
		return new EventController().createEvent(eventBean);
	}

	public List<EventBean> getEventsByCity(CityEnum cityEnum) {
		return new LoadEventsController().getEventsByCity(cityEnum);
	}

	public List<EventBean> getMyActiveEvents() {
		return new LoadEventsController().getMyActiveEvents();
	}

	public List<EventBean> getMyPastEvents() {
		return new LoadEventsController().getMyPastEvents();
	}

	public List<EventBean> getJoinEvents() {
		return new LoadEventsController().getJoinEvents();
	}

	public boolean sendRequest(RequestBean requestBean) {
		return new RequestController().createRequest(requestBean);
	}

	public List<RequestBean> getMyRequests() {
		return new RequestController().loadRequests(SessionView.getUsername());
	}

	public void setAddressForEvent(Double longitude, Double latitude)
			throws ConnectToGeolocationServiceException {
		new EventController().setAddressForEvent(longitude, latitude);
	}

	public void setEventForRequest(Double longitude, Double latitude) {
		new EventController().setEventForRequest(longitude, latitude);
	}

	public void logout() {
		new LoginController().logout();
	}

	public boolean acceptRequest(RequestBean requestSelected) {
		return new RequestController().acceptRequest(requestSelected);
	}

	public boolean rejectRequest(RequestBean requestSelected) {
		return new RequestController().rejectRequest(requestSelected);
	}

	public boolean cancelEvent(EventBean event) {
		return new EventController().cancelEvent(event);

	}

	public Integer getEventParticipants(EventBean event) {
		return new EventController().getNumberParticipants(event);
	}



	public boolean sendReview(ReviewBean review) {
		return new EventController().addReview(review);

	}

}
