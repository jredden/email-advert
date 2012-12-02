package com.zenred.eadvert.model.domain;

public class EmailPlusContext extends EMail {

	private String name;
	private String eventName;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}
	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public String toString(){
		return new StringBuffer(super.toString())
		.append("name:"+name+"\n")
		.append("eventName:"+eventName+"\n")
		.toString();
	}

}
