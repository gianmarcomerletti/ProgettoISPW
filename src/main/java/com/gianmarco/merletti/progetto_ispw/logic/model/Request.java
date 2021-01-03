package com.gianmarco.merletti.progetto_ispw.logic.model;

import java.sql.Date;

import com.gianmarco.merletti.progetto_ispw.logic.bean.RequestBean;

public class Request {

	private Integer idRequest;
	private Date creationDate;
	private String user;
	private Integer event;
	private String message;
	private String status;

	public Request() {
		//empty constructor
	}

	public void setFromBean(RequestBean requestBean) {
		this.setEvent(requestBean.getRequestEvent().getEventId());
		this.setIdRequest(requestBean.getRequestId());
		this.setUser(requestBean.getRequestUser());
		this.setCreationDate(requestBean.getRequestCreationDate());
		this.setMessage(requestBean.getRequestMessage());
		this.setStatus(requestBean.getRequestStatus());

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

	public Integer getEvent() {
		return event;
	}

	public void setEvent(Integer event) {
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
