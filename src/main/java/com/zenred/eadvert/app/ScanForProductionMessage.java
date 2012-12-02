package com.zenred.eadvert.app;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import com.zenred.eadvert.exception.DataAccessException;
import com.zenred.eadvert.exception.MessageServiceException;
import com.zenred.eadvert.model.domain.EmailAddressMessage;
import com.zenred.eadvert.service.ReportService;
import com.zenred.eadvert.service.SubscriberService;

public class ScanForProductionMessage {
	
	private BeanFactory beanFactory;
	private SubscriberService subscriberService;
	private ReportService reportService;
	private static ScanForProductionMessage scanForProductionMessage;
	
	private void doIt() throws MessageServiceException{
		StringBuffer status = new StringBuffer();
		beanFactory = new XmlBeanFactory(new FileSystemResource("../admin-controller-servlet.xml"));
		subscriberService = (SubscriberService)beanFactory.getBean("subscriberService");
		reportService = (ReportService)beanFactory.getBean("reportService");
		List<EmailAddressMessage> messageList = null;
		try{
			messageList = subscriberService.lookForMessagesReadyToSendToSubscribers();
		}
		catch (DataAccessException dae){
			dae.printStackTrace();
		}
		for(EmailAddressMessage emailAddressMessage : messageList){status.append(emailAddressMessage.getStatus());}
		reportService.buildReportBackEnd(messageList);
	}

	/**
	 * @param args
	 * @throws MessageServiceException 
	 */
	public static void main(String[] args) throws MessageServiceException {
		scanForProductionMessage = new ScanForProductionMessage();
		scanForProductionMessage.doIt();
	}

}
