package org.brickmvc.core;

/**
 * @author Mostafa
 * 
 */
public class WebComponentLoader {

	/**
	 * Creates an instance of WebComponent class given the name of a class that
	 * is a web component
	 * 
	 * @param webComponentClass
	 *            the full name of the class (ie. org.test.LoginService)
	 * @return the WebComponent instance or null if the class name is invalid
	 * @throws Exception 
	 * @see WebComponent
	 */
	public static WebComponent createWebComponent(String webComponentClass) throws Exception {

		try {
			Class<WebComponent> c = (Class<WebComponent>) Class
					.forName(webComponentClass);
			return c.newInstance();
		} catch (Exception e) {
			throw new Exception("Error creating an instance of validation or service class.");
		}
	}
}
