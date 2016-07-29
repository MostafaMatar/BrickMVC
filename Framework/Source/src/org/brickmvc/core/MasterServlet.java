package org.brickmvc.core;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brickmvc.core.services.Service;
import org.brickmvc.core.validation.ValidationProcess;

/**
 * The master container for BrickMVC applications
 * 
 * @author Mostafa
 * 
 */
@WebServlet(description = "The master container of the framework handling all requests to service classes.", urlPatterns = { "/services/*" })
public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Reads configuration file and loads it in memory.
	 * 
	 * @see Servlet#init(ServletConfig)
	 */
	@Override
	public void init() throws ServletException {
		String path = this.getServletContext().getRealPath(
				"/WEB-INF/brick-config.xml");
		Map<String, String> conf;
		try {
			conf = ConfigurationManager
					.loadConfigurationFile(path);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		getServletContext().setAttribute("config", conf);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.service(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.service(req, resp);
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String requestUrl = request.getPathInfo();
			Map<String, String> config = (Map<String, String>) getServletContext()
					.getAttribute("config");
			if (config.containsKey(requestUrl)) {
				String serviceClasses = config.get(requestUrl);
				String[] classes = serviceClasses.split(",");
				if (classes.length == 2) {
					ValidationProcess validator = (ValidationProcess) WebComponentLoader
							.createWebComponent(classes[0]);
					if (validator.validateRequest(request)) {
						Service service = (Service) WebComponentLoader
								.createWebComponent(classes[1]);
						service.initService();
						String responsePage = service.executeService(request);
						service.closeService();
						response.sendRedirect(request.getServletContext().getContextPath()+"/"+responsePage);
					} else {
						response.sendRedirect(request.getServletContext().getContextPath()+"/"+validator.getErrorPage());
					}
				} else if (classes.length == 1) {
					Service service = (Service) WebComponentLoader
							.createWebComponent(classes[0]);
					service.initService();
					String responsePage = service.executeService(request);
					service.closeService();
					response.sendRedirect(request.getServletContext().getContextPath()+"/"+responsePage);
				} else {
					throw new ServletException("Error reading configuration file");
				}
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
