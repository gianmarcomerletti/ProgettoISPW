package com.gianmarco.merletti.progetto_ispw.logic.bean;

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

	public EventListElementBean(EventBean bean) {
		this.elemEventId = new SimpleIntegerProperty(bean.getEventId());
		this.elemEventDate = new SimpleStringProperty(bean.getEventDate().toString() + " - " + bean.getEventTime().toString());
		this.elemEventTitle = new SimpleStringProperty(bean.getEventTitle());
		this.elemEventAddress = new SimpleStringProperty(bean.getEventAddress());
		this.elemEventDistance = new SimpleIntegerProperty(bean.getEventDistance());
		this.elemEventType = new SimpleStringProperty(bean.getEventType().toString());
		this.elemEventRating = new SimpleStringProperty(bean.getEventOrganizer().getUsername());
		this.elemEventCity = new SimpleStringProperty(bean.getEventCity());
	}

	public Integer getEventId() {
		return elemEventId.get();
	}

	public void setEventId(Integer elemEventId) {
		this.elemEventId.set(elemEventId);
	}

	public String getEventDate() {
		return elemEventDate.get();
	}

	public void setEventDate(String elemEventDate) {
		this.elemEventDate.set(elemEventDate);
	}

	public String getEventTitle() {
		return elemEventTitle.get();
	}

	public void setEventTitle(String elemEventTitle) {
		this.elemEventTitle.set(elemEventTitle);
	}

	public String getEventAddress() {
		return elemEventAddress.get();
	}

	public void setEventAddress(String elemEventAddress) {
		this.elemEventAddress.set(elemEventAddress);
	}

	public Integer getEventDistance() {
		return elemEventDistance.get();
	}

	public void setEventDistance(Integer elemEventDistance) {
		this.elemEventDistance.set(elemEventDistance);
	}

	public String getEventType() {
		return elemEventType.get();
	}

	public void setEventType(String elemEventType) {
		this.elemEventType.set(elemEventType);
	}

	public String getEventRating() {
		return elemEventRating.get();
	}

	public void setEventRating(String elemEventRating) {
		this.elemEventRating.set(elemEventRating);
	}

	public String getEventCity() {
		return elemEventCity.get();
	}

	public void setEventCity(String elemEventCity) {
		this.elemEventCity.set(elemEventCity);
	}



}
