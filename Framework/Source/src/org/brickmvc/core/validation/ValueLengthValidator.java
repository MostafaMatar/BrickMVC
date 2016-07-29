package org.brickmvc.core.validation;

/**
 * A custom validator which checks value length is in a given range
 * @author Mostafa
 *
 */
public class ValueLengthValidator implements Validator {

	/**
	 * min length for the value
	 */
	private int minLength;
	/**
	 * max length fo the value
	 */
	private int maxLength;

	/**
	 * @return the minLength
	 */
	public int getMinLength() {
		return minLength;
	}

	/**
	 * @param minLength
	 * the minLength to set
	 */
	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	/**
	 * @return the maxLength
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * @param maxLength
	 * the maxLength to set
	 */
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	/**
	 * validates if the length of value in a given range
	 * @param value the value to be validated
	 * @return true if length of value is in range and false if not
	 */
	@Override
	public boolean validateValue(String value) {
		int length = value.length();
		return (length >= this.minLength && length <= this.maxLength);
	}
}
