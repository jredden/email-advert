package com.zenred.eadvert.model.view;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("execDeleteMessage")
public class ExecDeleteMessageResponse {
	
	private String deleteMessage;

	/**
	 * @return the deleteMessage
	 */
	public String getDeleteMessage() {
		return deleteMessage;
	}

	/**
	 * @param deleteMessage the deleteMessage to set
	 */
	public void setDeleteMessage(String deleteMessage) {
		this.deleteMessage = deleteMessage;
	}
	
	

}
