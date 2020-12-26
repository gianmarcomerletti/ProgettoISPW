package com.gianmarco.merletti.progetto_ispw.logic.bean;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.gianmarco.merletti.progetto_ispw.logic.exception.InvalidFieldException;

public class EventBeanView {

	private Date eventViewCreationDate;
	private Date eventViewDate;
	private String eventViewTitle;
	private String eventViewDescription;
	private Time eventViewTime;
	private Double eventViewLatitude;
	private Double eventViewLongitude;
	private String eventViewAddress;
	private String eventViewLevel;
	private Integer eventViewDistance;
	private String eventViewType;
	private String eventViewOrganizer;
	private String eventViewCity;

	public Date getEventViewCreationDate() {
		return eventViewCreationDate;
	}
	public void setEventViewCreationDate(Date eventViewCreationDate) {
		this.eventViewCreationDate = eventViewCreationDate;
	}
	public Date getEventViewDate() {
		return eventViewDate;
	}
	public void setEventViewDate(Date eventViewDate) {
		this.eventViewDate = eventViewDate;
	}
	public String getEventViewTitle() {
		return eventViewTitle;
	}
	public void setEventViewTitle(String eventViewTitle) {
		this.eventViewTitle = eventViewTitle;
	}
	public String getEventViewDescription() {
		return eventViewDescription;
	}
	public void setEventViewDescription(String eventViewDescription) {
		this.eventViewDescription = eventViewDescription;
	}
	public Time getEventViewTime() {
		return eventViewTime;
	}

	public void setEventViewTime(String eventViewTime) throws InvalidFieldException {
		try {
			SimpleDateFormat df = new SimpleDateFormat("HH:mm");
			df.setTimeZone(TimeZone.getTimeZone("GMT+2"));
			long ms = df.parse(eventViewTime).getTime();
			Time time = new Time(ms);
			this.eventViewTime = time;
		} catch (ParseException e) {
			throw new InvalidFieldException();
		}
	}

	public Double getEventViewLatitude() {
		return eventViewLatitude;
	}
	public void setEventViewLatitude(Double eventViewLatitude) {
		this.eventViewLatitude = eventViewLatitude;
	}
	public Double getEventViewLongitude() {
		return eventViewLongitude;
	}
	public void setEventViewLongitude(Double eventViewLongitude) {
		this.eventViewLongitude = eventViewLongitude;
	}
	public String getEventViewAddress() {
		return eventViewAddress;
	}
	public void setEventViewAddress(String eventViewAddress) {
		this.eventViewAddress = eventViewAddress;
	}
	public String getEventViewLevel() {
		return eventViewLevel;
	}
	public void setEventViewLevel(String eventViewLevel) {
		this.eventViewLevel = eventViewLevel;
	}

	public Integer getEventViewDistance() {
		return eventViewDistance;
	}
	public void setEventViewDistance(Integer eventViewDistance) {
		this.eventViewDistance = eventViewDistance;
	}
	public String getEventViewOrganizer() {
		return eventViewOrganizer;
	}
	public void setEventViewOrganizer(String eventViewOrganizer) {
		this.eventViewOrganizer = eventViewOrganizer;
	}
	public String getEventViewCity() {
		return eventViewCity;
	}
	public void setEventViewCity(String eventViewCity) {
		this.eventViewCity = eventViewCity;
	}
	public String getEventViewType() {
		return eventViewType;
	}
	public void setEventViewType(String eventViewType) {
		this.eventViewType = eventViewType;
	}

}
