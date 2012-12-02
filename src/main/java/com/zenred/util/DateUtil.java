package com.zenred.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

public  class DateUtil {
	
    public static String getToday(){
    	return Calendar.getInstance().getTime().toString();
    }

    public static String getOurFormatToday(){
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	return simpleDateFormat.format(new Date());
    }
    
	// 02/01/2011, 02/28/2011 as an example
	//
	// Mon Nov 22 10:00:03 PST 2010
	public static String getInternalFormat(String date) {

		String[] dateArray = date.split("/");
		int year = Integer.parseInt(dateArray[2]);
		int month = Integer.parseInt(dateArray[0])-1;
		int day = Integer.parseInt(dateArray[1]);

		// get the supported ids for GMT-08:00 (Pacific Standard Time)
		String[] ids = TimeZone.getAvailableIDs(-8 * 60 * 60 * 1000);
		// create a Pacific Standard Time time zone
		SimpleTimeZone pdt = new SimpleTimeZone(-8 * 60 * 60 * 1000, ids[0]);

		// set up rules for daylight savings time
		pdt.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
		pdt.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY,
				2 * 60 * 60 * 1000);

		// create a GregorianCalendar with the Pacific Daylight time zone
		// and the current date and time
		Calendar calendar = new GregorianCalendar(pdt);

		calendar.set(year, month, day, 0, 0, 0);
		String format = "EEE MMM dd HH:mm:ss z yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(calendar.getTime());

	}

}
