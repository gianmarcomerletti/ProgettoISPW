package com.gianmarco.merletti.progetto_ispw.logic.controller;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
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

	public List<EventBean> getEventsByCity(CityEnum city) {
		EventDAO dao = new EventDAO();
		List<Event> events = dao.findAll();
		ArrayList<EventBean> eventBeanList = new ArrayList<>();

		for (Event event : events) {
			if	((event.getCity().equals(city.toString())) &&
					(!event.getOrganizerUser().getUsername().equals(SessionView.getUsername()))) {
				EventBean eventBean = getEventBeanFromEvent(event);
				eventBeanList.add(eventBean);
			}
		}
		return eventBeanList;
	}

	public List<EventBean> getMyEvents() {
		EventDAO dao = new EventDAO();
		List<Event> events = dao.findAll();
		ArrayList<EventBean> eventBeanList = new ArrayList<>();

		for (Event event : events) {
			if	(event.getOrganizerUser().getUsername().equals(SessionView.getUsername())) {
				EventBean eventBean = getEventBeanFromEvent(event);
				eventBeanList.add(eventBean);
			}
		}
		return eventBeanList;
	}


	public List<EventBean> getJoinEvents() {
		EventDAO dao = new EventDAO();
		List<Event> events = dao.findJoinEvent(SessionView.getUsername());
		ArrayList<EventBean> eventBeanList = new ArrayList<>();

		for (Event event : events) {
			if (!event.getOrganizerUser().getUsername().equals(SessionView.getUsername())) {
				EventBean eventBean = getEventBeanFromEvent(event);
				eventBeanList.add(eventBean);
			}
		}

		return eventBeanList;
	}

	public EventBean getEventBeanFromEvent(Event event) {
		EventBean eventBean = new EventBean();

		eventBean.setEventId(event.getId());
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
