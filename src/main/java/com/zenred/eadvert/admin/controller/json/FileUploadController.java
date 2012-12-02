package com.zenred.eadvert.admin.controller.json;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.zenred.eadvert.exception.EmailServiceException;
import com.zenred.eadvert.model.domain.MultiPartFileUpload;
import com.zenred.eadvert.model.view.ExcelUsersResponse;
import com.zenred.eadvert.service.EmailService;
import com.zenred.eadvert.service.SubscriberService;

public class FileUploadController extends SimpleFormController {

	private SubscriberService subscriberService;
	private EmailService emailService;

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */

	public void setSubscriberService(SubscriberService subscriberService) {
		this.subscriberService = subscriberService;
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws ServletException, IOException {
		
		Map<String, List<String>> access;

		// cast the bean
		MultiPartFileUpload bean = (MultiPartFileUpload) command;

		// let's see if there's content there
		byte[] file = bean.getFile();
		
		System.out.println("file size:" + file.length);
		
		String strRealPath = subscriberService.convertByteStreamIntoTempFile(file);
		
		this.subscriberService.excelServer(strRealPath);
		access = this.subscriberService.getCont();

		List<String> e = access.get("EmailAddress");

		List<String> f = access.get("FirstName");
		List<String> m = access.get("MiddleName");
		List<String> l = access.get("LastName");

		List<String> a1 = access.get("AddressList1");
		List<String> a2 = access.get("AddressList2");
		List<String> c = access.get("City");
		List<String> st = access.get("State");

		List<String> z = access.get("Zip");

		List<String> p = access.get("Phone");
		List<String> mp = access.get("MobilePhone");

		for (int i = 0; i < e.size(); i++) {
			if(null == e.get(i)){continue;}
			try {
				this.emailService.addServiceSubscriberEmail(e.get(i));
			} catch (EmailServiceException e1) {
				e1.printStackTrace();
			}
			this.subscriberService.emailIDToContact(e.get(i));
			this.subscriberService.contactPhone(p.get(i), mp.get(i));
			this.subscriberService.contactName(f.get(i), m.get(i), l.get(i));
			this.subscriberService.contactAddress(a1.get(i), a2.get(i), c
					.get(i), st.get(i), z.get(i));

		}

		int noEmailAddresses = this.emailService.fetchNoEmailAddress();

		ExcelUsersResponse updateEmailAddressResponse = new ExcelUsersResponse();

		updateEmailAddressResponse.setEmailAddresses(noEmailAddresses);
		ModelAndView modelAndView = new ModelAndView(new FileUploadJsonView());
		modelAndView.addObject(FileUploadJsonView.JSON_ROOT,
				updateEmailAddressResponse);
		return modelAndView;

	}

	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws ServletException {
		// to actually be able to convert Multipart instance to byte[]
		// we have to register a custom editor
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
		// now Spring knows how to handle multipart object and convert them
	}

}