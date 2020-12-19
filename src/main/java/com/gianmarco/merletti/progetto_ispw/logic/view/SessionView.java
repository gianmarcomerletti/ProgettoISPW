package com.gianmarco.merletti.progetto_ispw.logic.view;

import com.gianmarco.merletti.progetto_ispw.logic.bean.AddressBean;
import com.gianmarco.merletti.progetto_ispw.logic.bean.EventBean;
import com.gianmarco.merletti.progetto_ispw.logic.model.Event;
import com.gianmarco.merletti.progetto_ispw.logic.util.CityEnum;
import com.gianmarco.merletti.progetto_ispw.logic.util.LevelEnum;

public class SessionView {
	private static String username;
	private static LevelEnum levelEnum;
	private static CityEnum cityEnum;
	private static Double latitudeSetOnMap;
	private static Double longitudeSetOnMap;
	private static AddressBean addressSetOnMap;
	private static Event eventSetOnMap;

	private SessionView() {
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		SessionView.username = username;
	}

	public static LevelEnum getLevelEnum() {
		return levelEnum;
	}

	public static void setLevelEnum(LevelEnum level) {
		SessionView.levelEnum = level;
	}

	public static CityEnum getCityEnum() {
		return cityEnum;
	}

	public static void setCityEnum(CityEnum cityEnum) {
		SessionView.cityEnum = cityEnum;
	}

	public static Double getLatitudeSetOnMap() {
		return latitudeSetOnMap;
	}

	public static void setLatitudeSetOnMap(Double latitudeSetOnMap) {
		SessionView.latitudeSetOnMap = latitudeSetOnMap;
	}

	public static Double getLongitudeSetOnMap() {
		return longitudeSetOnMap;
	}

	public static void setLongitudeSetOnMap(Double longitudeSetOnMap) {
		SessionView.longitudeSetOnMap = longitudeSetOnMap;
	}

	public static AddressBean getAddressSetOnMap() {
		return addressSetOnMap;
	}

	public static void setAddressSetOnMap(AddressBean addressSetOnMap) {
		SessionView.addressSetOnMap = addressSetOnMap;
	}

	public static Event getEventSetOnMap() {
		return eventSetOnMap;
	}

	public static void setEventSetOnMap(Event eventSetOnMap) {
		SessionView.eventSetOnMap = eventSetOnMap;
	}

}
