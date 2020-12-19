package com.gianmarco.merletti.progetto_ispw.logic.controller;

import java.util.ArrayList;
import java.util.List;

import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBeanView;
import com.gianmarco.merletti.progetto_ispw.logic.dao.EventDAO;
import com.gianmarco.merletti.progetto_ispw.logic.exception.InvalidFieldException;
import com.gianmarco.merletti.progetto_ispw.logic.model.Event;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;

public class LoadEventsController {

	public LoadEventsController() {
	}

	public List<EventBeanView> getEventsFiltered() {
		EventDAO dao = new EventDAO();
		List<Event> events = dao.findAll();
		ArrayList<EventBeanView> eventBeanList = new ArrayList<>();

		for (Event event : events) {
			if	((event.getCity().equals(SessionView.getCityEnum().toString())) &&
					(!event.getOrganizerUser().getUsername().equals(SessionView.getUsername()))) {
				EventBeanView eventBean = getEventBeanFromEvent(event);
				eventBeanList.add(eventBean);
			}
		}
		return eventBeanList;
	}

	public List<EventBeanView> getMyEvents() {
		EventDAO dao = new EventDAO();
		List<Event> events = dao.findAll();
		ArrayList<EventBeanView> eventBeanList = new ArrayList<>();

		for (Event event : events) {
			if	(event.getOrganizerUser().getUsername().equals(SessionView.getUsername())) {
				EventBeanView eventBean = getEventBeanFromEvent(event);
				eventBeanList.add(eventBean);
			}
		}
		return eventBeanList;
	}

	private EventBeanView getEventBeanFromEvent(Event event) {
		EventBeanView eventBean = new EventBeanView();

		eventBean.setEventCreationDate(event.getCreationDate());
		eventBean.setEventDate(event.getDate());
		eventBean.setEventTitle(event.getTitle());
		eventBean.setEventDescription(event.getDescription());
		try {eventBean.setEventTime(event.getTime().toString());
		} catch (InvalidFieldException e) {}
		eventBean.setEventLatitude(event.getLatitude());
		eventBean.setEventLongitude(event.getLongitude());
		eventBean.setEventAddress(event.getAddress());
		eventBean.setEventLevel(event.getLevel());
		eventBean.setEventDistance(event.getDistance());
		eventBean.setEventType(event.getType());
		eventBean.setEventOrganizer(event.getOrganizerUser().getUsername());
		eventBean.setEventCity(event.getCity());
		return eventBean;
	}


}
