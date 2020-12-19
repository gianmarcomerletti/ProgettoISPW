package com.gianmarco.merletti.progetto_ispw.logic.model;

import java.util.List;
import java.sql.Date;
import java.sql.Time;

import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;

public class Event {

	private Integer idEvent;
	private Date creationDate;
	private Date date;
	private String title;
	private String address;
	private String description;
	private Double latitude;
	private Double longitude;
	private Time time;
	private String level;
	private Integer distance;
	private String type;
	private User organizerUser;
	private String city;

	private List<Request> requests;

	public Event() {
		//empty constructor
	}

	public void setFromBean(EventBean eventBean) {
		this.setTitle(eventBean.getEventTitle());
		this.setAddress(eventBean.getEventAddress());
		this.setLatitude(eventBean.getEventLatitude());
		this.setLongitude(eventBean.getEventLongitude());
		this.setDescription(eventBean.getEventDescription());
		this.setCreationDate(eventBean.getEventCreationDate());
		this.setDate(eventBean.getEventDate());
		this.setTime(eventBean.getEventTime());
		this.setDistance(eventBean.getEventDistance());
		this.setLevel(eventBean.getEventLevel().toString());
		this.setType(eventBean.getEventType().toString());
		this.setCity(eventBean.getEventCity());
		this.setOrganizerUser(eventBean.getEventOrganizer());
	}

	public Integer getId() {
		return idEvent;
	}

	public void setId(Integer id) {
		this.idEvent = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public User getOrganizerUser() {
		return organizerUser;
	}

	public void setOrganizerUser(User organizerUser) {
		this.organizerUser = organizerUser;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}

}
