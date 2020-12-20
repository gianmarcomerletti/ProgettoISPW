package com.gianmarco.merletti.progetto_ispw.logic.controller;

import java.util.List;

import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBeanView;
import com.gianmarco.merletti.progetto_ispw.logic.bean.UserBean;
import com.gianmarco.merletti.progetto_ispw.logic.exception.ConnectToGeolocationServiceException;
import com.gianmarco.merletti.progetto_ispw.logic.exception.InvalidFieldException;
import com.gianmarco.merletti.progetto_ispw.logic.exception.UserNotFoundException;

public class SystemFacade {

	public UserBean isSignedUp(UserBean userBean) throws UserNotFoundException {
		return new LoginController().checkUser(userBean);
	}

	public boolean signupUser(UserBean user) {
		return new LoginController().signupUser(user);
	}

	public boolean createEvent(EventBeanView eventBean) throws InvalidFieldException {
		return new EventController().createEvent(eventBean);
	}

	public List<EventBeanView> getEventsFiltered() {
		return new LoadEventsController().getEventsFiltered();
	}

	public List<EventBeanView> getMyEvents() {
		return new LoadEventsController().getMyEvents();
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

}
