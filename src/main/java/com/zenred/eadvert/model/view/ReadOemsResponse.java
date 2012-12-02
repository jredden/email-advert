package com.zenred.eadvert.model.view;

import com.thoughtworks.xstream.annotations.XStreamAlias;
@XStreamAlias("oem")

public class ReadOemsResponse {
	
	private String Oem;

	/**
	 * @return the oem
	 */
	public String getOem() {
		return Oem;
	}

	/**
	 * @param oem the oem to set
	 */
	public void setOem(String oem) {
		this.Oem = oem;
	}


}
