package com.gianmarco.merletti.progetto_ispw.logic.util;

import java.sql.Date;
import java.time.LocalDate;

import com.gianmarco.merletti.progetto_ispw.logic.exception.InvalidFieldException;

import javafx.scene.control.DatePicker;

public class ConverterUtil {

	private ConverterUtil() {
		//empty constructor
	}

	public static Date dateFromDatePicker(DatePicker eventDatePicker) throws InvalidFieldException {
		LocalDate ld = eventDatePicker.getValue();
		if (ld != null && ld.isAfter(LocalDate.now())) {
			return Date.valueOf(ld);
		} else
			throw new InvalidFieldException();
	}

}
