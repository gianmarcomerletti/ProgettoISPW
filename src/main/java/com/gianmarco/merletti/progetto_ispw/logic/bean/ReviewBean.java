package com.gianmarco.merletti.progetto_ispw.logic.bean;

import com.gianmarco.merletti.progetto_ispw.logic.model.Event;
import com.gianmarco.merletti.progetto_ispw.logic.model.User;

public class ReviewBean {

	private Integer idReviewBean;
	private User userBean;
	private EventBean eventBean;
	private Integer valueBean;
	private String textBean;
	private byte[] imageBean;
	public Integer getIdReviewBean() {
		return idReviewBean;
	}
	public void setIdReviewBean(Integer idReviewBean) {
		this.idReviewBean = idReviewBean;
	}
	public User getUserBean() {
		return userBean;
	}
	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}
	public EventBean getEventBean() {
		return eventBean;
	}
	public void setEventBean(EventBean eventBean) {
		this.eventBean = eventBean;
	}
	public Integer getValueBean() {
		return valueBean;
	}
	public void setValueBean(Integer valueBean) {
		this.valueBean = valueBean;
	}
	public String getTextBean() {
		return textBean;
	}
	public void setTextBean(String textBean) {
		this.textBean = textBean;
	}
	public byte[] getImageBean() {
		return imageBean;
	}
	public void setImageBean(byte[] imageBean) {
		this.imageBean = imageBean;
	}



}
