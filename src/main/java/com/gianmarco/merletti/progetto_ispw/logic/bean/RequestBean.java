package com.gianmarco.merletti.progetto_ispw.logic.bean;

import java.sql.Date;

import com.gianmarco.merletti.progetto_ispw.logic.model.User;

public class RequestBean {

	private Integer requestId;
	private Date requestCreationDate;
	private String requestUser;
	private EventBean requestEvent;

	public RequestBean() {
		//it can be empty
	}

	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Date getRequestCreationDate() {
		return requestCreationDate;
	}

	public void setRequestCreationDate(Date requestCreationDate) {
		this.requestCreationDate = requestCreationDate;
	}

	public String getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(String requestUser) {
		this.requestUser = requestUser;
	}

	public EventBean getRequestEvent() {
		return requestEvent;
	}

	public void setRequestEvent(EventBean requestEvent) {
		this.requestEvent = requestEvent;
	}

}
