package org.brickmvc.core.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a custom validator able to validate emails.
 * @author Mostafa
 *
 */
public class EmailValidator implements Validator {
	/**
	 * The regular expression for an email address.
	 */
	private final String EMAIL_REGEX = "([_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+)";
	
	/**
	 * Validates and email address given as a String and returns a boolean as the result.
	 * @param value A string representing the email address.
	 * @return true if value is an email and false if not
	 */
	@Override
	public boolean validateValue(String value) {
		Pattern pattern = Pattern.compile(this.EMAIL_REGEX);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
}
