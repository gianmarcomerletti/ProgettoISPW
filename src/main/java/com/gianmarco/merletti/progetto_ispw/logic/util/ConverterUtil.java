package com.gianmarco.merletti.progetto_ispw.logic.util;

import java.sql.Date;
import java.time.LocalDate;

import javafx.scene.control.DatePicker;

public class ConverterUtil {

	private ConverterUtil() {
		//empty constructor
	}

	public static Date dateFromDatePicker(DatePicker eventDatePicker) {
		LocalDate ld = eventDatePicker.getValue();
		if (ld != null) {
			Date date = Date.valueOf(ld);
			return date;
		}
		return null;
	}

}
