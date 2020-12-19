package com.gianmarco.merletti.progetto_ISPW.logic.model;

public class City {
	private String name;
	private Double latitude;
	private Double longitude;
	private Double[][] borders;


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Double[][] getBorders() {
		return borders;
	}
	public void setBorders(Double[][] borders) {
		this.borders = borders;
	}


}
