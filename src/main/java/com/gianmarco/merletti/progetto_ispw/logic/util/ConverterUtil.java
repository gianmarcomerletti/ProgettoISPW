package com.gianmarco.merletti.progetto_ispw.logic.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

	public static byte[] byteArrayFromImage(File imageFile) {
		byte[] b = null;
		try {
			b = Files.readAllBytes(imageFile.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return b;
	}

}
