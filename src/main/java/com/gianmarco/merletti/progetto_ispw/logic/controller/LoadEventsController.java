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
		//it can be empty
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

		eventBean.setEventViewCreationDate(event.getCreationDate());
		eventBean.setEventViewDate(event.getDate());
		eventBean.setEventViewTitle(event.getTitle());
		eventBean.setEventViewDescription(event.getDescription());
		try {eventBean.setEventViewTime(event.getTime().toString());
		} catch (InvalidFieldException e) {}
		eventBean.setEventViewLatitude(event.getLatitude());
		eventBean.setEventViewLongitude(event.getLongitude());
		eventBean.setEventViewAddress(event.getAddress());
		eventBean.setEventViewLevel(event.getLevel());
		eventBean.setEventViewDistance(event.getDistance());
		eventBean.setEventViewType(event.getType());
		eventBean.setEventViewOrganizer(event.getOrganizerUser().getUsername());
		eventBean.setEventViewCity(event.getCity());
		return eventBean;
	}


}
