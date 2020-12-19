package com.gianmarco.merletti.progetto_ispw.logic.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.sql.Date;

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
