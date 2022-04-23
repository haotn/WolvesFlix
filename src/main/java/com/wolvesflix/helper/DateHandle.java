package com.wolvesflix.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandle {
	private static SimpleDateFormat sdf = new SimpleDateFormat();

	public static Date addDays(Date date, long days) {
		date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
		return date;
	}

	public static Date convertFormat(Date date, String pattern) {
		sdf.applyPattern(pattern);
		String dateString = sdf.format(date);
		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
