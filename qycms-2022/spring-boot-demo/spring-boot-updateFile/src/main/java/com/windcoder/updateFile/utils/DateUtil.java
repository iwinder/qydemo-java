package com.windcoder.updateFile.utils;

import org.joda.time.DateTime;

public class DateUtil {
	public static String getDayStr(){
		DateTime dte = DateTime.now();
		String monthStr;
		if (dte.getMonthOfYear() > 9) {
			monthStr = String.valueOf(dte.getMonthOfYear());
		} else {
			monthStr = "0" + String.valueOf(dte.getMonthOfYear());
		}
		String dayStr;
		int day = dte.getDayOfMonth()-1;
		if (day > 9) {
			dayStr = String.valueOf(day);
		} else {
			dayStr = "0" + String.valueOf(day);
		}
		return String.valueOf(dte.getYear()) + monthStr + dayStr;
	}
}
