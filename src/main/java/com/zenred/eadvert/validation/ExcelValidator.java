package com.zenred.eadvert.validation;

import java.util.ArrayList;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.zenred.eadvert.validation.ValidatorDS;
import com.zenred.eadvert.model.domain.EmailAdvertSheet;

public class ExcelValidator implements Validator {

	private static final Log log = LogFactory.getLog(ExcelValidator.class
			.getName());

	public boolean supports(Class clazz) {
		return clazz.isAssignableFrom(EmailAdvertSheet.class);
	}

	public void validate(Object object, Errors errors) {

		log.info("EmailAdvertExcelValidator - validate");

		String currentPath = errors.getNestedPath();
		log.info("EmailAdvertExcelValidator - validate getNestedPath");

		EmailAdvertSheet emailForm = (EmailAdvertSheet) object;
		log.info(emailForm);

		if (ValidatorDS.isNameValid(emailForm.getFirstName())) {
			errors.rejectValue("firstName", "invalid.firstName",
					"  First name is invalid.");
		}

		if (ValidatorDS.isNameValid(emailForm.getLastName())) {
			errors.rejectValue("lastName", "invalid.lastName",
					"  Last name is invalid.");
		}

		if (ValidatorDS.isNameValid(emailForm.getMiddleName())) {
			errors.rejectValue("middleName", "invalid.middleName",
					"  Middle Name is invalid.");
		}

		if(ValidatorDS.isAddressOneValid(emailForm.getAddress1())) {
			errors.rejectValue("address1", "invalid.address1",
					"  Address is invalid.");
		}

		if(ValidatorDS.isAddressTwoValid(emailForm.getAddress2())) {
			errors.rejectValue("address2", "invalid.address2",
					"  Address is invalid.");
		}

	    if(ValidatorDS.isAddressCityValid(emailForm.getCity())) {
			errors.rejectValue("city", "invalid.city", "  City is invalid. ");
		}

	    if(ValidatorDS.isAddressCityValid(emailForm.getState())) {
			errors
					.rejectValue("state", "invalid.state",
							"  State is invalid. ");
		}

		if(ValidatorDS.isAddressUsZipValid(emailForm.getZip())) {
			errors.rejectValue("zip", "invalid.zip", "  Zip is invalid. ");
		}

		if (emailForm.getEmailAddress() == null
				|| emailForm.getEmailAddress().equals("")
				|| emailForm.getEmailAddress().equals(" ")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress",
					"required.emailAddress", "  Email Address is required");
		} else if (emailForm.getEmailAddress() != null
				&& !ValidatorDS.isEmailValid(emailForm.getEmailAddress())) {
			errors.rejectValue("emailAddress", "invalid.emailaddress",
					"  Email Address is invalid.");
		}

		/*if (emailForm.getMobileAreaCode() == null || emailForm.getPhone().equals("")
				|| emailForm.getPhone().equals(" ")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneAreaCode",
					"required.phoneAreaCode", "  Phone Area Code is required");
		} else if (emailForm.getPhone() != null
				&& !ValidatorDS.isEmailPhoneValid(emailForm.getPhone())) {
			errors.rejectValue("phoneAreaCode", "invalid.phoneAreaCode",
					"  Phone Area Code is invalid.");
		}

		if (emailForm.getMobile() == null || emailForm.getMobile().equals("")
				|| emailForm.getMobile().equals(" ")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileAreaCode",
					"required.mobilAreaCode", "  Mobile Area Code is required");
		} else if (emailForm.getMobile() != null
				&& !ValidatorDS.isEmailPhoneValid(emailForm.getMobile())) {
			errors.rejectValue("mobileAreaCode", "invalid.mobileAreaCode",
					"  Mobile Area Code is invalid.");
		}
*/
		errors.setNestedPath(currentPath);
	}

}