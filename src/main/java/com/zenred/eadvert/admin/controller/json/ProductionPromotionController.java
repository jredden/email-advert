package com.zenred.eadvert.admin.controller.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zenred.eadvert.exception.CampaignServiceException;
import com.zenred.eadvert.model.domain.ProductionMessage;
import com.zenred.eadvert.model.view.ProductionPromotionResponse;
import com.zenred.eadvert.service.CampaignService;
import com.zenred.eadvert.service.EmailService;

public class ProductionPromotionController implements Controller {
	
	private EmailService emailService;
	private CampaignService campaignService;

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	public void setCampaignService(CampaignService campaignService) {
		this.campaignService = campaignService;
	}
	public static String URI = "uri";
	public static String DATE = "date";
	public static String SUBMIT = "submit";
	public static String UNKNOWN = "unknown";
	
	protected interface Action{
		public void doProductionMessage(ProductionMessage productionMessage, String attributesForMessage);
	}
	
	private static HashMap<String,Action> actionMap = new HashMap<String, ProductionPromotionController.Action>();
	
	static {
		actionMap.put(DATE, new Action(){
			public void doProductionMessage(
					ProductionMessage productionMessage,
					String attributesForMessage) {
				if(attributesForMessage.isEmpty()){
					return;
				}
				productionMessage.setSendOffDate(attributesForMessage);
			}
		});
		actionMap.put(SUBMIT, new Action(){
			public void doProductionMessage(
					ProductionMessage productionMessage,
					String attributesForMessage) {
				return;  // a submit ... do nothing 
			}
		});
		actionMap.put(URI, new Action(){
			public void doProductionMessage(
					ProductionMessage productionMessage,
					String attributesForMessage) {
				System.out.println("attributesForMessage:"+attributesForMessage);
				String [] theParameters = attributesForMessage.split("\\|");
				productionMessage.setCampaign(theParameters[0]);
				productionMessage.setUri(theParameters[1]);
				productionMessage.setVersion(theParameters[2]);
			}
			
		});
		actionMap.put(UNKNOWN, new Action(){
			public void doProductionMessage(
					ProductionMessage productionMessage,
					String attributesForMessage) {
				System.err.println("bogus key:"+attributesForMessage);
				return; 
			}
		});
	}
	
	private String keyId(String key){
		if(key.startsWith(DATE)){return DATE;}
		if(key.startsWith(SUBMIT)){return SUBMIT;}
		if(key.startsWith(URI)){return URI;}
		return UNKNOWN;
	}
	
	private String isolateKey(String rawKey){
		String [] key_pieces = rawKey.split("\\|");
		System.out.println("keyPieces:"+key_pieces[0]+":"+key_pieces.length);
		if(key_pieces.length<3){return "NONE";}
		String answer = key_pieces[1]+key_pieces[2];
		System.out.println("isolateKey:"+answer);
		return answer;
	}
	

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Map<String, String[]> parameterMap = request.getParameterMap();
		Map<String, ProductionMessage> messageListToGo = new HashMap<String, ProductionMessage>();
		List<ProductionPromotionResponse> responseList = new ArrayList<ProductionPromotionResponse>();
		ModelAndView modelAndView = new ModelAndView(new ProductionPromotionJsonView());

		Set<String> keys = parameterMap.keySet();
		Iterator<String> iter = keys.iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			String mapKey = isolateKey(key);
			ProductionMessage productionMessage = null;
			if(messageListToGo.containsKey(mapKey)){
				productionMessage = messageListToGo.get(mapKey);
			}
			else{
				productionMessage = new ProductionMessage();
			}
			actionMap.get(keyId(key)).doProductionMessage(productionMessage, parameterMap.get(key)[0]);
			System.out.println("ProductionMessage:"+productionMessage);
			boolean decide = (null == productionMessage.getUri() && null == productionMessage.getVersion() && null == productionMessage.getSendOffDate());
			if(!decide){
				messageListToGo.put(mapKey, productionMessage);
			}
			
//			String [] theParameters = parameterMap.get(key)[0].split("\\|");
//			for(String parameter : theParameters){
//				System.out.println("ProductionPromotionController:"+parameter+":key:"+key+":");
//			}
			
		}
		System.out.println("ProductionMessageMap:"+messageListToGo);
		
		//  now that message list has been built, don't let a message go through that has
		// 	not been checked by the user
		
		Set<String> keys2 = messageListToGo.keySet();
		Iterator<String> iter2 = keys2.iterator();
		while (iter2.hasNext()) {
			String key2 = iter2.next();
			if (null == messageListToGo.get(key2).getUri()){
				ProductionPromotionResponse productionPromotionResponse = new ProductionPromotionResponse();
				productionPromotionResponse.setStata("Check box not checked!:" + key2);
				responseList.add(productionPromotionResponse);
				modelAndView.addObject(ProductionPromotionJsonView.JSON_ROOT, responseList);
				return modelAndView;
			}
			if(campaignService.dateChecker( messageListToGo.get(key2).getSendOffDate())){
				throw new CampaignServiceException("date " + messageListToGo.get(key2).getSendOffDate() + " fails validation");
			}
		}
		
		List<String> stata= emailService.promoteToProduction(messageListToGo);
		
		for(String statum : stata){
			ProductionPromotionResponse productionPromotionResponse = new ProductionPromotionResponse();
			productionPromotionResponse.setStata(statum);
			responseList.add(productionPromotionResponse);
		}
		modelAndView.addObject(ProductionPromotionJsonView.JSON_ROOT, responseList);
		return modelAndView;
	}

}
