package com.zenred.eadvert.model.domain;

public class MessageContextPlusRevision extends MessageContext {
	private String revision;

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}
	
	public String toString(){
		return super.toString()+"revision:"+revision+"\n";
	}
}
