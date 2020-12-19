package com.gianmarco.merletti.progetto_ispw.logic.bean;

import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.Time;

import com.gianmarco.merletti.progetto_ispw.logic.exception.InvalidFieldException;
import com.gianmarco.merletti.progetto_ispw.logic.model.User;
import com.gianmarco.merletti.progetto_ispw.logic.util.LevelEnum;
import com.gianmarco.merletti.progetto_ispw.logic.util.TypeEnum;

public class EventBean {

	private Integer eventId;
	private Date eventCreationDate;
	private Date eventDate;
	private String eventTitle;
	private String eventDescription;
	private Time eventTime;
	private Double eventLatitude;
	private Double eventLongitude;
	private String eventAddress;
	private LevelEnum eventLevel;
	private Integer eventDistance;
	private TypeEnum eventType;
	private User eventOrganizer;
	private String eventCity;

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

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

	public void setEventTime(Time eventTime) throws InvalidFieldException {
		this.eventTime = eventTime;
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

	public User getEventOrganizer() {
		return eventOrganizer;
	}

	public void setEventOrganizer(User eventOrganizer) {
		this.eventOrganizer = eventOrganizer;
	}

	public String getEventCity() {
		return eventCity;
	}

	public void setEventCity(String eventCity) {
		this.eventCity = eventCity;
	}

	public String getEventAddress() {
		return eventAddress;
	}

	public void setEventAddress(String eventAddress) {
		this.eventAddress = eventAddress;
	}

	public LevelEnum getEventLevel() {
		return eventLevel;
	}

	public void setEventLevel(LevelEnum eventLevel) {
		this.eventLevel = eventLevel;
	}

	public Integer getEventDistance() {
		return eventDistance;
	}

	public void setEventDistance(Integer eventDistance) {
		this.eventDistance = eventDistance;
	}

	public TypeEnum getEventType() {
		return eventType;
	}

	public void setEventType(TypeEnum eventType) {
		this.eventType = eventType;
	}

}
