package com.gianmarco.merletti.progetto_ispw.logic.bean;

import java.text.SimpleDateFormat;

import com.gianmarco.merletti.progetto_ispw.logic.controller.SystemFacade;
import com.gianmarco.merletti.progetto_ispw.logic.dao.ReviewDAO;
import com.gianmarco.merletti.progetto_ispw.logic.model.Event;
import com.gianmarco.merletti.progetto_ispw.logic.util.Rating;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EventListElementBean {

	private SimpleIntegerProperty elemEventId;
	private SimpleStringProperty elemEventDate;
	private SimpleStringProperty elemEventTitle;
	private SimpleStringProperty elemEventAddress;
	private SimpleStringProperty elemEventType;
	private SimpleStringProperty elemEventLevel;
	private SimpleStringProperty elemEventRating;
	private SimpleStringProperty elemEventCity;

	public EventListElementBean(Event event) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		this.elemEventId = new SimpleIntegerProperty(event.getId());
		this.elemEventDate = new SimpleStringProperty(dateFormat.format(event.getDate()) + " " + timeFormat.format(event.getTime()));
		this.elemEventTitle = new SimpleStringProperty(event.getTitle());
		this.elemEventAddress = new SimpleStringProperty(event.getAddress());
		this.elemEventType = new SimpleStringProperty(event.getDistance() + " KM " + event.getType());
		this.elemEventLevel = new SimpleStringProperty(event.getLevel());
		this.elemEventRating = new SimpleStringProperty(event.getOrganizerUser().getUsername() + " " +
									Rating.getStringRating(new SystemFacade().getUserRating(event.getOrganizerUser().getUsername())));
		this.elemEventCity = new SimpleStringProperty(event.getCity());
	}

	public Integer getElemEventId() {
		return elemEventId.get();
	}

	public void setElemEventId(Integer elemEventId) {
		this.elemEventId.set(elemEventId);
	}

	public String getElemEventDate() {
		return elemEventDate.get();
	}

	public void setElemEventDate(String elemEventDate) {
		this.elemEventDate.set(elemEventDate);
	}

	public String getElemEventTitle() {
		return elemEventTitle.get();
	}

	public void setElemEventTitle(String elemEventTitle) {
		this.elemEventTitle.set(elemEventTitle);
	}

	public String getElemEventAddress() {
		return elemEventAddress.get();
	}

	public void setElemEventAddress(String elemEventAddress) {
		this.elemEventAddress.set(elemEventAddress);
	}

	public String getElemEventType() {
		return elemEventType.get();
	}

	public void setElemEventType(String elemEventType) {
		this.elemEventType.set(elemEventType);
	}

	public String getElemEventLevel() {
		return elemEventLevel.get();
	}

	public void setElemEventLevel(String elemEventLevel) {
		this.elemEventLevel.set(elemEventLevel);
	}

	public String getElemEventRating() {
		return elemEventRating.get();
	}

	public void setElemEventRating(String elemEventRating) {
		this.elemEventRating.set(elemEventRating);
	}

	public String getElemEventCity() {
		return elemEventCity.get();
	}

	public void setElemEventCity(String elemEventCity) {
		this.elemEventCity.set(elemEventCity);
	}



}
