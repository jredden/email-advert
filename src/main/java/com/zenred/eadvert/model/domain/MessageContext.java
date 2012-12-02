package com.zenred.eadvert.model.domain;

public class MessageContext {
	public enum State {qa, production, sent}; 

	
	
	private String subject;
	private State mc_state;
	private String contentMD5;
	private String plenusMD5;
	/**
	 * @param mcState the mc_state to set
	 */
	public void setMc_state(State mcState) {
		mc_state = mcState;
	}
	public void setMc_state(String mcState) {
		if(mcState.equals(State.qa.toString()))mc_state = State.qa;
		if(mcState.equals(State.production.toString()))mc_state = State.production;
		if(mcState.equals(State.sent.toString()))mc_state = State.sent;
	}
	/**
	 * @param contentMD5 the contentMD5 to set
	 */
	public void setContentMD5(String contentMD5) {
		this.contentMD5 = contentMD5;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the plenusMD5
	 */
	public String getPlenusMD5() {
		return plenusMD5;
	}
	/**
	 * @param plenusMD5 the plenusMD5 to set
	 */
	public void setPlenusMD5(String plenusMD5) {
		this.plenusMD5 = plenusMD5;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @return the mc_state
	 */
	public State getMc_state() {
		return mc_state;
	}
	/**
	 * @return the contentMD5
	 */
	public String getContentMD5() {
		return contentMD5;
	}
	
	public String toString() {
		return new StringBuffer().append("subject:" + subject + "\n").append(
				"mc_state:" + mc_state + "\n").append(
				"contentMD5:" + contentMD5 + "\n").append(
				"plenusMD5:" + plenusMD5 + "\n").toString();
	}

}
