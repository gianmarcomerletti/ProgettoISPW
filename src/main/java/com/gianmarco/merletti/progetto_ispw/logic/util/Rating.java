package com.gianmarco.merletti.progetto_ispw.logic.util;

public class Rating {

	private Rating() {
		//empty constructor
	}

	public static String getStringRating(Integer value) {
		switch (value) {
		case 1:
			return "★☆☆☆☆";
		case 2:
			return "★★☆☆☆";
		case 3:
			return "★★★☆☆";
		case 4:
			return "★★★★☆";
		case 5:
			return "★★★★★";
		default:
			return "☆☆☆☆☆";
		}
	}
}
