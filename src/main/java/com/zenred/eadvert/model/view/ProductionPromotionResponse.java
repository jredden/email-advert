package com.zenred.eadvert.model.view;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("productionPromotion")

public class ProductionPromotionResponse {
	
	private String stata;

	public String getStata() {
		return stata;
	}

	public void setStata(String stata) {
		this.stata = stata;
	}

}
