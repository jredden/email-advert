package com.zenred.eadvert.model.domain;

public class TimeSent {
	
	private String dayOfWeek;
	private String day;
	private String month;
	private String year;
	private String hour;
	private String minute;
	private String second;
	
	public String toString(){
		return new StringBuffer()
		.append("dayOfWeek:").append(dayOfWeek).append("\n")
		.append("day:").append(day).append("\n")
		.append("month:").append(month).append("\n")
		.append("year:").append(year).append("\n")
		.append("hour:").append(hour).append("\n")
		.append("minute:").append(minute).append("\n")
		.append("second:").append(second).append("\n")
		.toString();
	}
}
