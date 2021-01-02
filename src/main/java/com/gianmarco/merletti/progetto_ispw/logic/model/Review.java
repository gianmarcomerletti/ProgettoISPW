package com.gianmarco.merletti.progetto_ispw.logic.model;

import com.gianmarco.merletti.progetto_ispw.logic.bean.ReviewBean;
import com.gianmarco.merletti.progetto_ispw.logic.dao.EventDAO;

public class Review {

	private String user;
	private Event event;
	private Integer value;
	private String text;
	private byte[] image;

	public Review() {
		//empty constructor
	}

	public void setFromBean(ReviewBean reviewBean) {
		Event ev = new Event();
		ev = new EventDAO().findById(reviewBean.getEventBean().getEventId());
		this.setEvent(ev);
		this.setValue(reviewBean.getValueBean());
		this.setText(reviewBean.getTextBean());
		this.setImage(reviewBean.getImageBean());
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

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}



}
