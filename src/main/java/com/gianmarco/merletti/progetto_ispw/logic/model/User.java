package com.gianmarco.merletti.progetto_ispw.logic.model;

import com.gianmarco.merletti.progetto_ispw.logic.bean.UserBean;

public class User {
	private String username;
	private String pwd;
	private String name;
	private String surname;
	private String level;
	private String city;

	public User() {
		//empty constructor
	}

	public void setFromBean(UserBean userBean) {
		this.setUsername(userBean.getUsername());
		this.setPwd(userBean.getPassword());
		this.setName(userBean.getFirstName());
		this.setSurname(userBean.getLastName());
		this.setLevel(userBean.getLevel().toString());
		this.setCity(userBean.getCity().toString());
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

}
