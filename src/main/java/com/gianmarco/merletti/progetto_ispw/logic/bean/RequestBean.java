package com.gianmarco.merletti.progetto_ispw.logic.bean;

import java.sql.Date;

public class RequestBean {

	private Integer requestId;
	private Date requestCreationDate;
	private String requestUser;
	private EventBean requestEvent;
	private String requestMessage;
	private String requestStatus;

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

	public void setRequestEvent(EventBean event) {
		this.requestEvent = event;
	}

	public String getRequestMessage() {
		return requestMessage;
	}

	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}


}
