package com.gianmarco.merletti.progetto_ispw.logic.model;

import java.sql.Date;
import java.sql.Time;

import com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean;

public class Request {

	private Integer idRequest;
	private Date creationDate;
	private User user;
	private Event event;

	public Request() {
	}

	public void setFromBean(RequestBean requestBean) {
		Event ev = new Event();
		ev.setFromBean(requestBean.getRequestEvent());
		this.setUser(requestBean.getRequestUser());
		this.setCreationDate(requestBean.getRequestCreationDate());
		this.setEvent(ev);

	}

	public Integer getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(Integer idRequest) {
		this.idRequest = idRequest;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
