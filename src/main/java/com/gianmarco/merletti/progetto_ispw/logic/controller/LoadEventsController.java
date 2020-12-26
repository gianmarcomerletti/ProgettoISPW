package com.gianmarco.merletti.progetto_ispw.logic.controller;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBeanView;
import com.gianmarco.merletti.progetto_ispw.logic.dao.EventDAO;
import com.gianmarco.merletti.progetto_ispw.logic.exception.InvalidFieldException;
import com.gianmarco.merletti.progetto_ispw.logic.model.Event;
import com.gianmarco.merletti.progetto_ispw.logic.util.CityEnum;
import com.gianmarco.merletti.progetto_ispw.logic.util.LevelEnum;
import com.gianmarco.merletti.progetto_ispw.logic.util.TypeEnum;
import com.gianmarco.merletti.progetto_ispw.logic.view.SessionView;

public class LoadEventsController {

	public LoadEventsController() {
		//it can be empty
	}

	public List<EventBeanView> getEventsByCity(CityEnum city) {
		EventDAO dao = new EventDAO();
		List<Event> events = dao.findAll();
		ArrayList<EventBeanView> eventBeanList = new ArrayList<>();

		for (Event event : events) {
			if	((event.getCity().equals(city.toString())) &&
					(!event.getOrganizerUser().getUsername().equals(SessionView.getUsername()))) {
				EventBeanView eventBean = getEventBeanViewFromEvent(event);
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
				EventBeanView eventBean = getEventBeanViewFromEvent(event);
				eventBeanList.add(eventBean);
			}
		}
		return eventBeanList;
	}


	public List<EventBeanView> getJoinEvents() {
		EventDAO dao = new EventDAO();
		List<Event> events = dao.findJoinEvent(SessionView.getUsername());
		ArrayList<EventBeanView> eventBeanList = new ArrayList<>();

		for (Event event : events) {
			EventBeanView eventBean = getEventBeanViewFromEvent(event);
			eventBeanList.add(eventBean);
		}

		return eventBeanList;
	}

	private EventBeanView getEventBeanViewFromEvent(Event event) {
		EventBeanView eventBean = new EventBeanView();

		eventBean.setEventViewCreationDate(event.getCreationDate());
		eventBean.setEventViewDate(event.getDate());
		eventBean.setEventViewTitle(event.getTitle());
		eventBean.setEventViewDescription(event.getDescription());
		try {eventBean.setEventViewTime(event.getTime().toString());
		} catch (InvalidFieldException e) {
			e.printStackTrace();
		}
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

	public EventBean getEventBeanFromEvent(Event event) {
		EventBean eventBean = new EventBean();

		eventBean.setEventCreationDate(event.getCreationDate());
		eventBean.setEventDate(event.getDate());
		eventBean.setEventTitle(event.getTitle());
		eventBean.setEventDescription(event.getDescription());
		eventBean.setEventTime(event.getTime());
		eventBean.setEventLatitude(event.getLatitude());
		eventBean.setEventLongitude(event.getLongitude());
		eventBean.setEventAddress(event.getAddress());
		eventBean.setEventLevel(LevelEnum.valueOf(event.getLevel()));
		eventBean.setEventDistance(event.getDistance());
		eventBean.setEventType(TypeEnum.valueOf(event.getType()));
		eventBean.setEventOrganizer(event.getOrganizerUser());
		eventBean.setEventCity(event.getCity());
		return eventBean;
	}
}
