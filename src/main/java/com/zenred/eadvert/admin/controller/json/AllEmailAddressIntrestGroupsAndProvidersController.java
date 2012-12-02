package com.zenred.eadvert.admin.controller.json;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.view.AddressInterestGroupProviderResponse;
import com.zenred.eadvert.service.ReportService;

public class AllEmailAddressIntrestGroupsAndProvidersController implements
		Controller {

	private ReportService reportService;

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<AddressInterestGroupProviderResponse> addressInterestGroupProviderResponseList = reportService
				.allEmailAddressesWithInterestGroupAndProvider();
		ModelAndView modelAndView = new ModelAndView(new AddressInterestGroupProviderView());
		modelAndView.addObject(AddressInterestGroupProviderView.JSON_ROOT, addressInterestGroupProviderResponseList);
		return modelAndView;
	}

}
