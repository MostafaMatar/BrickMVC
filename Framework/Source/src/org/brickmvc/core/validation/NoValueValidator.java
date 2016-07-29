package org.brickmvc.core.validation;

/**
 * A custom validator which checks if a value exists.
 * @author Mostafa
 * 
 */
public class NoValueValidator implements Validator{

	/**
	 * Checks for the existence of a given value
	 * @param value The value to be tested.
	 * @return true if value exists and false if it's null or empty string
	 */
	@Override
	public boolean validateValue(String value) {
		return !(value==null||value.equals(""));
	}
}
