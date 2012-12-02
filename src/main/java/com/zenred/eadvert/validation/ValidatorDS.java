package com.zenred.eadvert.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 * @author Prashant Salunke Date: Aug 05, 2010
 */

public class ValidatorDS extends ValidationUtils {

	/**
	 * @param phone
	 *            - user input phone number
	 * @return true if the phone could be in the formats as follows:
	 *         (999)999-9999, 999-999-9999, 9990009999, (999)-999-9999
	 */
	public static boolean isPhoneValid(String phone) {
		String expr = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
		CharSequence input = phone;
		Pattern pattern = Pattern.compile(expr);
		Matcher matcher = pattern.matcher(input);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	public static boolean isEmailValid(String email) {
		boolean valid = true;
		String expr = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence input = email;
		Pattern pattern = Pattern.compile(expr, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		if (!matcher.matches()) {
			valid = false;
		}
		if (email.length() > 4 && email.substring(0, 4).equals("www.")) {
			valid = false;
		}
		return valid;
	}

	public static boolean isLetterOnly(String inputStr) {
		return inputStr.matches("[A-Za-z]+[a-zA-Z]*");
	}

	public static boolean isLastNameValid(String lastName) {
		return lastName.matches("[A-Za-z]+([ '-] | \\. | [A-Za-z])*");
	}

	public static boolean isNameValid(String name) {
		return name.matches("[A-Za-z '-\\.]*");
	}

	public static boolean isAddressOneValid(String addressOne) {
		return addressOne.matches("([0-9A-Za-z #_]|\\.)*");
	}

	public static boolean isAddressTwoValid(String addressOne) {
		return addressOne.matches("[#A-Za-z]+\\.?\\s?[A-Za-z0-9]+");
	}

	public static boolean isAddressCityValid(String city) {
		return city.matches("[A-Za-z]+[ ]?[a-zA-Z]*");
	}

	public static boolean isEmailPhoneValid(String phoneMobile) {
		return phoneMobile.matches("[0-9]*");
	}

	public static boolean isAddressUsZipValid(String zipCode) {
		return zipCode.matches("\\d{5}(\\-\\d{4})?");
	}

	public static void rejectNullorEmptry(String field, String fieldValue,
			String errorMessage, Errors errors) {
		if (fieldValue == null) {
			errors.rejectValue(field, "required." + field, errorMessage);
		} else {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, field,
					"required." + field, errorMessage);
		}
	}

	public static void rejectInvalidPhone(Errors errors, String field,
			String errorMessage, String fieldValue) {
		if (!isPhoneValid(fieldValue)) {
			errors.rejectValue(field, field + ".phone", errorMessage);
		}
	}

	public static void rejectInvalidEmail(Errors errors, String field,
			String errorMessage, String fieldValue) {
		if (!isEmailValid(fieldValue)) {
			errors.rejectValue(field, "required.email", errorMessage);
		}
	}
}
