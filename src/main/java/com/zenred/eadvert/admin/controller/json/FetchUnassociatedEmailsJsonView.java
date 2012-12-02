package com.zenred.eadvert.admin.controller.json;

import com.zenred.eadvert.model.view.ReadUnassocEmailsResponse;

public class FetchUnassociatedEmailsJsonView extends AbstractJsonView {

	public FetchUnassociatedEmailsJsonView() {
		super(ReadUnassocEmailsResponse.class);
	}

}
