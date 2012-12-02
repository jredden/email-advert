package com.zenred.eadvert.aop;

import java.util.Arrays;
import java.util.Enumeration;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.caucho.server.http.HttpRequest;

@Aspect
public class LoggingAspect {
	
	private void displayController(ProceedingJoinPoint proceedingJoinPoint, Logger logger){
		int argc = 0;
		for (Object _obj :proceedingJoinPoint.getArgs()){
			System.out.println("arg"+argc+":"+_obj+":"+_obj.getClass().getName());
			++argc;
		}
		System.out.println(Arrays.toString(proceedingJoinPoint.getArgs()));
		logger.info(Arrays.toString(proceedingJoinPoint.getArgs()));
		/*  really bugs resin 4.0.x
		HttpRequest httpRequest = (HttpRequest) proceedingJoinPoint.getArgs()[0];
		Enumeration<String> enumer = httpRequest.getParameterNames();
		while(enumer.hasMoreElements()){
			String name = enumer.nextElement();
			System.out.println(name + ":"+ httpRequest.getParameterValues(name)[0]);
			logger.info(name + ":"+ httpRequest.getParameterValues(name)[0]);
		}
		 */
	}
	private void displayService(ProceedingJoinPoint proceedingJoinPoint, Logger logger){
		int argc = 0;
		for (Object _obj :proceedingJoinPoint.getArgs()){
			if(null == _obj){
				System.out.println("arg"+argc+":"+_obj+":");
			}
			else{
				System.out.println("arg"+argc+":"+_obj+":"+_obj.getClass().getName());
			}
			++argc;
		}
		System.out.println(Arrays.toString(proceedingJoinPoint.getArgs()));
		logger.info(Arrays.toString(proceedingJoinPoint.getArgs()));
	}
	
	private void displayDao(ProceedingJoinPoint proceedingJoinPoint, Logger logger){
		int argc = 0;
		for (Object _obj :proceedingJoinPoint.getArgs()){
			if(null == _obj){
				System.out.println("arg"+argc+":"+_obj+":");
			}
			else{
				System.out.println("arg"+argc+":"+_obj+":"+_obj.getClass().getName());
			}
			++argc;
		}
		System.out.println(Arrays.toString(proceedingJoinPoint.getArgs()));
		logger.info(Arrays.toString(proceedingJoinPoint.getArgs()));
	}
	
	@Around("execution(* com.zenred.eadvert.admin.controller.json.StoreCampaignController.*(..))")
	public Object logStoreCampaignController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.StoreCampaignController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.StoreCampaignController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.StoreCampaignController:"+_ret+"::");
		return _ret;
	}
	
	@Around("execution(* com.zenred.eadvert.admin.controller.json.StoreOemController.*(..))")
	public Object logStoreOemController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.StoreOemController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.StoreOemController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.StoreOemController:"+_ret+"::");
		return _ret;
	}

	@Around("execution(* com.zenred.eadvert.admin.controller.PrimaryFileUploadController.*(..))")
	public Object logPrimaryFileUploadController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.PrimaryFileUploadController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.PrimaryFileUploadController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.PrimaryFileUploadController:"+_ret+"::");
		return _ret;
	}	

	@Around("execution(* com.zenred.eadvert.admin.controller.json.FileUploadController.*(..))")
	public Object logFileUploadController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.FileUploadController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.FileUploadController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.FileUploadController:"+_ret+"::");
		return _ret;
	}	
	
	@Around("execution(* com.zenred.eadvert.admin.controller.json.FetchCampaignController.*(..))")
	public Object logFetchCampaignController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.FetchCampaignController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.FetchCampaignController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.FetchCampaignController:"+_ret+"::");
		return _ret;
	}

	@Around("execution(* com.zenred.eadvert.admin.controller.json.FetchOemsController.*(..))")
	public Object logFetchOemsController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.FetchOemsController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.FetchOemsController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.FetchOemsController:"+_ret+"::");
		return _ret;
	}
	
	@Around("execution(* com.zenred.eadvert.admin.controller.json.UpdateCampaignController.*(..))")
	public Object logUpdateCampaignController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.UpdateCampaignController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.UpdateCampaignController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.UpdateCampaignController:"+_ret+"::");
		return _ret;
	}

	@Around("execution(* com.zenred.eadvert.admin.controller.json.StoreCampaignUpdatesController.*(..))")
	public Object logStoreCampaignUpdatesController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.StoreCampaignUpdatesController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.StoreCampaignUpdatesController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.StoreCampaignUpdatesController:"+_ret+"::");
		return _ret;
	}
	
	@Around("execution(* com.zenred.eadvert.admin.controller.json.DeleteUserController.*(..))")
	public Object logDeleteUserController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.DeleteUserController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.DeleteUserController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.DeleteUserController:"+_ret+"::");
		return _ret;
	}

	@Around("execution(* com.zenred.eadvert.admin.controller.PrimaryLoginController.*(..))")
	public Object logPrimaryLogController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.PrimaryLogController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.PrimaryLogController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.PrimaryLogController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.PrimaryTemplateReaderController.*(..))")
	public Object logPrimaryTemplateReaderController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.PrimaryTemplateReaderController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.PrimaryTemplateReaderController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.PrimaryTemplateReaderController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.NewMessageController.*(..))")
	public Object logNewMessageController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.NewMessageController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.NewMessageController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.NewMessageController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.FetchUnassociatedEmailAddressesController.*(..))")
	public Object logFetchUnassociatedEmailAddressesController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.FetchUnassociatedEmailAddressesController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.FetchUnassociatedEmailAddressesController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.FetchUnassociatedEmailAddressesController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.AssociateEmailAddressController.*(..))")
	public Object logAssociateEmailAddressController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.AssociateEmailAddressController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.AssociateEmailAddressController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.AssociateEmailAddressController:"+_ret+"::");
		return _ret;
	}
	
	@Around("execution(* com.zenred.eadvert.admin.controller.json.MultiPartFileUploadController.*(..))")
	public Object logMultiPartFileUploadController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.MultiPartFileUploadController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.MultiPartFileUploadController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.MultiPartFileUploadController:"+_ret+"::");
		return _ret;
	}

	@Around("execution(* com.zenred.eadvert.admin.controller.json.MessageReviewOrUpdateController.*(..))")
	public Object logMessageReviewOrUpdateController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.MessageReviewOrUpdateController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.MessageReviewOrUpdateController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.MessageReviewOrUpdateController:"+_ret+"::");
		return _ret;
	}
	
	@Around("execution(* com.zenred.eadvert.admin.controller.json.OneMessageReviewOrUpdateController.*(..))")
	public Object logOneMessageReviewOrUpdateController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.OneMessageReviewOrUpdateController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.OneMessageReviewOrUpdateController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.OneMessageReviewOrUpdateController:"+_ret+"::");
		return _ret;
	}
	
	@Around("execution(* com.zenred.eadvert.admin.controller.PrimaryOptionOutController.*(..))")
	public Object logPrimaryOptionOutController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.PrimaryOptionOutController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.PrimaryOptionOutController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.PrimaryOptionOutController:"+_ret+"::");
		return _ret;
	}
	
	@Around("execution(* com.zenred.eadvert.admin.controller.PrimaryPromoteController.*(..))")
	public Object logPrimaryPromoteController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.PrimaryPromoteController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.PrimaryPromoteController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.PrimaryPromoteController:"+_ret+"::");
		return _ret;
	}

	@Around("execution(* com.zenred.eadvert.admin.controller.json.SubmitToQAController.*(..))")
	public Object logSubmitToQAController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.SubmitToQAController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.SubmitToQAController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.SubmitToQAController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.PromoteMessageToQAController.*(..))")
	public Object logPromoteMessageToQAController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.PromoteMessageToQAController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.PromoteMessageToQAController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.PromoteMessageToQAController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.ProductionPromotionController.*(..))")
	public Object logProductionPromotionController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.ProductionPromotionController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.ProductionPromotionController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.ProductionPromotionController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.PromoteMessageToProductionController.*(..))")
	public Object logPromoteMessageToProductionController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.PromoteMessageToProductionController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.PromoteMessageToProductionController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.PromoteMessageToProductionController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.DeleteMessageController.*(..))")
	public Object logDeleteMessageController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.DeleteMessageController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.DeleteMessageController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.DeleteMessageController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.ExecDeleteMessageController.*(..))")
	public Object logExecDeleteMessageController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.ExecDeleteMessageController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.ExecDeleteMessageController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.ExecDeleteMessageController:"+_ret+"::");
		return _ret;
	}
	
	@Around("execution(* com.zenred.eadvert.admin.controller.PrimaryMessageDeleteController.*(..))")
	public Object logPrimaryMessageDeleteController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.PrimaryMessageDeleteController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.PrimaryMessageDeleteController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.PrimaryMessageDeleteController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.PrimaryInterestGroupController.*(..))")
	public Object logPrimaryInterestGroupController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.PrimaryInterestGroupController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.PrimaryInterestGroupController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.PrimaryInterestGroupController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.NewAnonymousInterestGroupController.*(..))")
	public Object logNewAnonymousInterestGroupController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.NewAnonymousInterestGroupController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.NewAnonymousInterestGroupController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.NewAnonymousInterestGroupController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.AssociateCampaignToInterestGroupController.*(..))")
	public Object logAssociateCampaignToInterestGroupController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.AssociateCampaignToInterestGroupController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.AssociateCampaignToInterestGroupController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.AssociateCampaignToInterestGroupController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.FetchProviderUsersController.*(..))")
	public Object logFetchProviderUsersController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.FetchProviderUsersController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.FetchProviderUsersController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.FetchProviderUsersController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.UpdatePasswordController.*(..))")
	public Object logUpdatePasswordController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.UpdatePasswordController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.UpdatePasswordController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.UpdatePasswordController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.FetchUsersCampaignsController.*(..))")
	public Object logFetchUsersCampaignsController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.FetchUsersCampaignsController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.FetchUsersCampaignsController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.FetchUsersCampaignsController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.app.controller.json.AddEmailToInterestGroupController.*(..))")
	public Object logAddEmailToInterestGroupController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.AddEmailToInterestGroupController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.app.controller.json.AddEmailToInterestGroupController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.AddEmailToInterestGroupController:"+_ret+"::");
		return _ret;
	}

	@Around("execution(* com.zenred.eadvert.admin.controller.PrimaryReportsController.*(..))")
	public Object logPrimaryReportsController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.PrimaryReportsController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.ReportsGroupController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.PrimaryReportsController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.FetchAllInterestGroupInfoController.*(..))")
	public Object logFetchAllInterestGroupInfoController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.FetchAllInterestGroupInfoController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.FetchAllInterestGroupInfoController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.FetchAllInterestGroupInfoController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.FetchSentEMailInTimeFrameController.*(..))")
	public Object logFetchSentEMailInTimeFrameController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.FetchSentEMailInTimeFrameController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.FetchSentEMailInTimeFrameController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.FetchSentEMailInTimeFrameController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.FetchProviderInterestGroupInfoController.*(..))")
	public Object logFetchProviderInterestGroupInfoController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.FetchProviderInterestGroupInfoController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.FetchProviderInterestGroupInfoController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.FetchProviderInterestGroupInfoController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.FetchSentEMailInTimeFrameProviderController.*(..))")
	public Object logFetchSentEMailInTimeFrameProviderController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.FetchSentEMailInTimeFrameProviderController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.FetchSentEMailInTimeFrameProviderController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.FetchSentEMailInTimeFrameProviderController:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.admin.controller.json.AllEmailAddressIntrestGroupsAndProvidersController.*(..))")
	public Object logAllEmailAddressIntrestGroupsAndProvidersController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.AllEmailAddressIntrestGroupsAndProvidersController:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.admin.controller.json.AllEmailAddressIntrestGroupsAndProvidersController");
		displayController(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.AllEmailAddressIntrestGroupsAndProvidersController:"+_ret+"::");
		return _ret;
	}
	
	@Around("execution(* com.zenred.eadvert.service.CampaignService.*(..))")
	public Object logCampaignService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.CampaignService:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.service.CampaignService");
		displayService(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.CampaignService:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.service.EmailService.*(..))")
	public Object logEmailService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.EmailService:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.service.EmailService");
		displayService(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.EmailService:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.service.TemplateService.*(..))")
	public Object logTemplateService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.TemplateService:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.service.TemplateService");
		displayService(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.TemplateService:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.service.UserService.*(..))")
	public Object logUserService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.UserService:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.service.UserService");
		displayService(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.UserService:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.service.SubscriberService.*(..))")
	public Object logSubscriberService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.SubscriberService:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.service.SubscriberService");
		displayService(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.SubscriberService:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.service.InterestService.*(..))")
	public Object logInterestService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.InterestService:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.service.InterestService");
		displayService(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.InterestService:"+_ret+"::");
		return _ret;
	}

	@Around("execution(* com.zenred.eadvert.dao.CampaignDao.*(..))")
	public Object logCampaignDao(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.CampaignDao:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.dao.CampaignDao");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.CampaignDao:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.dao.OemDao.*(..))")
	public Object logOemDao(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.OemDao:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.dao.OemDao");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.OemDao:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.dao.DateEventDao.*(..))")
	public Object logDateEventDao(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.DateEventDao:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.dao.DateEventDao");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.DateEventDao:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.dao.EmailDao.*(..))")
	public Object logEmailDao(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.EmailDao:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.dao.EmailDao");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.EmailDao:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.dao.TemplateDao.*(..))")
	public Object logTemplateDao(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.TemplateDao:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.dao.TemplateDao");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.TemplateDao:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.dao.UserDAO.*(..))")
	public Object logUserDAO(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.UserDAO:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.dao.UserDAO");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.UserDAO:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.dao.MessageDao.*(..))")
	public Object logMessageDAO(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.MessageDAO:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.dao.MessageDAO");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.MessageDAO:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.dao.RuntimeDao.*(..))")
	public Object logRuntimeDAO(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.RuntimeDAO:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.dao.RuntimeDAO");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.RuntimeDAO:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.dao.UploadFileDao.*(..))")
	public Object UploadFileDAO(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.UploadFileDAO:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.dao.UploadFileDAO");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.UploadFileDAO:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.dao.HashMapDao.*(..))")
	public Object HashMapDAO(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.HashMapDAO:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.dao.HashMapDAO");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.HashMapDAO:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.dao.ExcelReaderDao.*(..))")
	public Object ExcelReaderDAO(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.ExcelReaderDAO:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.dao.ExcelReaderDAO");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.ExcelReaderDAO:"+_ret+"::");
		return _ret;
	}	
	@Around("execution(* com.zenred.eadvert.dao.ContactDao.*(..))")
	public Object logContactDAO(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.ContactDAO:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.dao.ContactDAO");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.ContactDAO:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.dao.ContactAddressDao.*(..))")
	public Object logContactAddressDAO(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.ContactAddressDAO:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.dao.ContactAddressDAO");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.ContactAddressDAO:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.dao.ContactNameDao.*(..))")
	public Object logContactNameDAO(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.ContactNameDAO:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.dao.ContactNameDAO");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.ContactNameDAO:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.dao.ContactPhoneDao.*(..))")
	public Object logContactPhoneDAO(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.ContactPhoneDAO:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.dao.ContactPhoneDAO");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.ContactPhoneDAO:"+_ret+"::");
		return _ret;
	}
	@Around("execution(* com.zenred.eadvert.dao.InterestGroupDao.*(..))")
	public Object logInterestGroupDAO(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		String signatureString = proceedingJoinPoint.getSignature().toLongString();
		System.out.println("before.InterestGroupDAO:"+signatureString+"::");
		Logger logger = Logger.getLogger("com.zenred.eadvert.dao.InterestGroupDAO");
		displayDao(proceedingJoinPoint, logger);
		Object _ret = proceedingJoinPoint.proceed();
		System.out.println("after.InterestGroupDAO:"+_ret+"::");
		return _ret;
	}
}
