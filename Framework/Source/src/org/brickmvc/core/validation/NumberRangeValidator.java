package org.brickmvc.core.validation;

/**
 * A custom validator checking if a value is in a given range
 * @author Mostafa
 *  
 */
public class NumberRangeValidator implements Validator {

	/**
	 * minimum alue of range
	 */
	private double minValue;
	/**
	 * maximum value of range
	 */
	private double maxValue;

	/**
	 * @return the minValue
	 */
	public double getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue
	 * the minValue to set
	 */
	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	/**
	 * @return the maxValue
	 */
	public double getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue
	 * the maxValue to set
	 */
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * validates if a value is in a given range
	 * @param value the value to be validated
	 * @return true if in range and false if not
	 */
	@Override
	public boolean validateValue(String value) {
		double d = Double.parseDouble(value);
		return (d >= this.minValue && d <= this.maxValue);
	}
}
