package org.brickmvc.core.services;

import javax.servlet.http.HttpServletRequest;

import org.brickmvc.core.WebComponent;

/**
 * The base class for all service classes
 * @author Mostafa
 *
 */
public abstract class Service extends WebComponent{
	/**
	 * initializes the service
	 */
	public abstract void initService();
	/**
	 * executes the business logic of the service
	 * @param request the user request
	 * @return the name of the view to load after service is done
	 */
	public abstract String executeService(HttpServletRequest request);
	/**
	 * disposes of any service resources
	 */
	public abstract void closeService();
}
