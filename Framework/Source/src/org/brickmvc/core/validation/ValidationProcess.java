package org.brickmvc.core.validation;

import javax.servlet.http.HttpServletRequest;

import org.brickmvc.core.WebComponent;

/**
 * The base class for any validation process class
 * @author Mostafa
 *
 */
public abstract class ValidationProcess extends WebComponent{
	public abstract boolean validateRequest(HttpServletRequest request);
	public abstract String getErrorPage();
}
