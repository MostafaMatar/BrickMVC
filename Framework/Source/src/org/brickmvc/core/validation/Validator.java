package org.brickmvc.core.validation;

/**
 * The base interface for any custom validator class.
 * @author Mostafa
 *
 */
public interface Validator {
	boolean validateValue(String value);
}
