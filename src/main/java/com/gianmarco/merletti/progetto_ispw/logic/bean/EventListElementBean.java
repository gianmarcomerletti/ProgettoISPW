package com.gianmarco.merletti.progetto_ispw.logic.bean;

import java.text.SimpleDateFormat;

import com.gianmarco.merletti.progetto_ispw.logic.model.Event;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EventListElementBean {

	private SimpleIntegerProperty elemEventId;
	private SimpleStringProperty elemEventDate;
	private SimpleStringProperty elemEventTitle;
	private SimpleStringProperty elemEventAddress;
	private SimpleIntegerProperty elemEventDistance;
	private SimpleStringProperty elemEventType;
	private SimpleStringProperty elemEventRating;
	private SimpleStringProperty elemEventCity;

	public EventListElementBean(Event event) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		this.elemEventId = new SimpleIntegerProperty(event.getId());
		this.elemEventDate = new SimpleStringProperty(dateFormat.format(event.getDate()) + " " + timeFormat.format(event.getTime()));
		this.elemEventTitle = new SimpleStringProperty(event.getTitle());
		this.elemEventAddress = new SimpleStringProperty(event.getAddress());
		this.elemEventDistance = new SimpleIntegerProperty(event.getDistance());
		this.elemEventType = new SimpleStringProperty(event.getType());
		this.elemEventRating = new SimpleStringProperty(event.getOrganizerUser().getUsername());
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

	public Integer getElemEventDistance() {
		return elemEventDistance.get();
	}

	public void setElemEventDistance(Integer elemEventDistance) {
		this.elemEventDistance.set(elemEventDistance);
	}

	public String getElemEventType() {
		return elemEventType.get();
	}

	public void setElemEventType(String elemEventType) {
		this.elemEventType.set(elemEventType);
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
