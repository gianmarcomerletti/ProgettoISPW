package com.gianmarco.merletti.progetto_ispw.logic.model;

import java.sql.Date;

import com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean;

public class Request {

	private Integer idRequest;
	private Date creationDate;
	private String user;
	private Event event;
	private String message;
	private String status;

	public Request() {
		//empty constructor
	}

	public void setFromBean(RequestBean requestBean) {
		Event ev = new Event();
		ev.setFromBean(requestBean.getRequestEvent());
		this.setIdRequest(requestBean.getRequestId());
		this.setUser(requestBean.getRequestUser());
		this.setCreationDate(requestBean.getRequestCreationDate());
		this.setEvent(ev);
		this.setMessage(requestBean.getRequestMessage());

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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
