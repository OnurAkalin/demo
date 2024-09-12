package com.example.demo.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateHelper {
	public static Date addYearsToInstanceOfDate(int years) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}

	public static Date addYearsToGivenDate(Date date, int years) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}
}
