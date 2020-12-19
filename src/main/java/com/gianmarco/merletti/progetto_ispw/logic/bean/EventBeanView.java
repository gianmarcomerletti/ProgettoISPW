package com.gianmarco.merletti.progetto_ispw.logic.bean;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.gianmarco.merletti.progetto_ispw.logic.exception.InvalidFieldException;
import com.gianmarco.merletti.progetto_ispw.logic.model.User;

public class EventBeanView {

	private Date eventCreationDate;
	private Date eventDate;
	private String eventTitle;
	private String eventDescription;
	private Time eventTime;
	private Double eventLatitude;
	private Double eventLongitude;
	private String eventAddress;
	private String eventLevel;
	private Integer eventDistance;
	private String eventType;
	private String eventOrganizer;
	private String eventCity;

	public Date getEventCreationDate() {
		return eventCreationDate;
	}
	public void setEventCreationDate(Date eventCreationDate) {
		this.eventCreationDate = eventCreationDate;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public Time getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) throws InvalidFieldException {
		try {
			SimpleDateFormat df = new SimpleDateFormat("HH:mm");
			long ms = df.parse(eventTime).getTime();
			Time time = new Time(ms);
			this.eventTime = time;
		} catch (ParseException e) {
			throw new InvalidFieldException();
		}
	}

	public Double getEventLatitude() {
		return eventLatitude;
	}
	public void setEventLatitude(Double eventLatitude) {
		this.eventLatitude = eventLatitude;
	}
	public Double getEventLongitude() {
		return eventLongitude;
	}
	public void setEventLongitude(Double eventLongitude) {
		this.eventLongitude = eventLongitude;
	}
	public String getEventAddress() {
		return eventAddress;
	}
	public void setEventAddress(String eventAddress) {
		this.eventAddress = eventAddress;
	}
	public String getEventLevel() {
		return eventLevel;
	}
	public void setEventLevel(String eventLevel) {
		this.eventLevel = eventLevel;
	}

	public Integer getEventDistance() {
		return eventDistance;
	}
	public void setEventDistance(Integer eventDistance) {
		this.eventDistance = eventDistance;
	}
	public String getEventOrganizer() {
		return eventOrganizer;
	}
	public void setEventOrganizer(String eventOrganizer) {
		this.eventOrganizer = eventOrganizer;
	}
	public String getEventCity() {
		return eventCity;
	}
	public void setEventCity(String eventCity) {
		this.eventCity = eventCity;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

}
