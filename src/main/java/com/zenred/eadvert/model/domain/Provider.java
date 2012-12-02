package com.zenred.eadvert.model.domain;

public class Provider extends User implements IProvider {
	
	private String provider;

	/* (non-Javadoc)
	 * @see com.zenred.eadvert.model.domain.IProvider#getProvider()
	 */
	@Override
	public String getProvider() {
		return provider;
	}

	/* (non-Javadoc)
	 * @see com.zenred.eadvert.model.domain.IProvider#setProvider(java.lang.String)
	 */
	@Override
	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	/* (non-Javadoc)
	 * @see com.zenred.eadvert.model.domain.IProvider#toString()
	 */
	@Override
	public String toString(){
		return super.toString() + "provider:" + provider + "\n";
	}

}
