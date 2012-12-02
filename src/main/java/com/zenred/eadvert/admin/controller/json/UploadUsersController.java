package com.zenred.eadvert.admin.controller.json;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.model.view.ExcelUsersResponse;
import com.zenred.eadvert.service.EmailService;
import com.zenred.eadvert.service.SubscriberService;

public class UploadUsersController implements Controller {

	private String fileName = null;
	private SubscriberService subscriberService;
	private EmailService emailService;

	public EmailService getEmailService() {

		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	private String formView;
	private String successView;

	/**
	 * @param userService
	 *            the userService to set
	 */

	public void setUserService(SubscriberService subscriberService) {
		this.subscriberService = subscriberService;
	}

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ModelAndView modelAndView = null;

		if (request.getMethod().equals("GET")) {

			modelAndView = new ModelAndView(this.getFormView());

		} else if (request.getMethod().equals("POST")) {
			onSubmit(request);

			int noEmailAddresses = this.emailService.fetchNoEmailAddress();

			ExcelUsersResponse updateEmailAddressResponse = new ExcelUsersResponse();

			updateEmailAddressResponse.setEmailAddresses(noEmailAddresses);
			modelAndView = new ModelAndView(new UploadUsersJsonView());
			modelAndView.addObject(UploadUsersJsonView.JSON_ROOT,
					updateEmailAddressResponse);

		} else {
			System.out.println("Method Neither GET or POST");
		}

		return modelAndView;

	}

	protected void onSubmit(HttpServletRequest request) throws Exception {

		Map<String, List<String>> access;
		String orgFileName = null;
		String fileMPath = request.getParameter("file");
		System.out.println("File Path = " + fileMPath);

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Iterator fileNames = multipartRequest.getFileNames();
		System.out.println("File Map = " + multipartRequest.getFileMap());

		if (fileNames.hasNext()) {
			String fileName = (String) fileNames.next();
			System.out.println("***** fileName = " + fileName);
			MultipartFile file = multipartRequest.getFile(fileName);
			System.out.println("***** file size : " + file.getSize());
			byte[] varByte = file.getBytes();
			System.out.println("***** file contents : " + file.getBytes()
					+ "File Name : " + file.getOriginalFilename());

			orgFileName = file.getOriginalFilename();

			System.out.println("File Input Stream => " + file.getInputStream());

			System.out.println("Bytes" + varByte.toString());

		}

		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setSizeThreshold(40960);

		File repositoryPath = new File("/tempPath");
		diskFileItemFactory.setRepository(repositoryPath);

		ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
		upload.setSizeMax(81920);

		List items = null;

		try {
			System.out.println("Request = " + request);
			items = upload.parseRequest(request);
			System.out
					.println("UploadUsersController => List Item => " + items);
		} catch

		(SizeLimitExceededException e) {
			e.printStackTrace();
		}

		String filePath = "C:\\Documents and Settings\\Prashant\\Desktop\\Dashboard\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\email_advert\\";
		// this.subscriberService.uploadExcelFile(filePath, items);

		/*
		  String strRealPath = "C:\\home\\email_advert\\UploadedFiles\\" +
		  orgFileName;
		*/ 
		String strRealPath = "/home/email_advert/userfiles/uploads/matrixXSLFile.xls";

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

			this.emailService.addServiceSubscriberEmail(e.get(i));
			this.subscriberService.emailIDToContact(e.get(i));
			this.subscriberService.contactPhone(p.get(i), mp.get(i));
			this.subscriberService.contactName(f.get(i), m.get(i), l.get(i));
			this.subscriberService.contactAddress(a1.get(i), a2.get(i), c
					.get(i), st.get(i), z.get(i));

		}

	}

	public String getFormView() {
		return formView;
	}

	public String getSuccessView() {
		return successView;

	}

	public SubscriberService getSubscriberService() {
		return subscriberService;
	}

	public void setSubscriberService(SubscriberService subscriberService) {
		this.subscriberService = subscriberService;
	}

	public void setFormView(String formView) {
		this.formView = formView;
	}

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

}