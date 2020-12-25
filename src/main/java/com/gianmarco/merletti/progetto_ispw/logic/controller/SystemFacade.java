package com.gianmarco.merletti.progetto_ispw.logic.controller;

import java.util.List;

import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBeanView;
import com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean;
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

	public boolean createEvent(EventBeanView eventBean) {
		return new EventController().createEvent(eventBean);
	}

	public List<EventBeanView> getEventsByCity(CityEnum cityEnum) {
		return new LoadEventsController().getEventsByCity(cityEnum);
	}

	public List<EventBeanView> getMyEvents() {
		return new LoadEventsController().getMyEvents();
	}


	public List<EventBeanView> getJoinEvents() {
		return new LoadEventsController().getJoinEvents();
	}

	public boolean sendRequest(RequestBean requestBean) {
		return new RequestController().creteRequest(requestBean);
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

}
