package com.gianmarco.merletti.progetto_ispw.logic.util;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
			e.printStackTrace();
		}

		return b;
	}

	public static String md5FromString(String input) {
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");

			byte[] messageDigest = md.digest(input.getBytes());

			BigInteger no = new BigInteger(1, messageDigest);

			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0".concat(hashtext);
			}
			return hashtext;
		}

		catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

}
