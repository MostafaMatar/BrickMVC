package org.brickmvc.core;

import java.io.IOException;
import java.util.ArrayList;
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
		Map<String, ArrayList<String>> conf;
		try {
			conf = ConfigurationManager.loadConfigurationFile(path);
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
			Map<String, ArrayList<String>> config = (Map<String, ArrayList<String>>) getServletContext()
					.getAttribute("config");
			if (config.containsKey(requestUrl)) {
				ArrayList<String> classes = config.get(requestUrl);
				if (classes.size() == 2) {
					ValidationProcess validator = (ValidationProcess) WebComponentLoader
							.createWebComponent(classes.get(0));
					if (validator.validateRequest(request)) {
						
						ArrayList<Service> services = this.getServiceClasses(classes.get(1));
						
						// executing services logic according to order
						String responsePage = "";
						for (int j = 0; j < services.size(); j++) {
							services.get(j).initService();
							responsePage = services.get(j).executeService(request);
						}

						// executing the closing algorithm of the services
						int z = services.size() - 1;
						while (z >= 0) {
							services.get(z).closeService();
							z--;
						}
						// redirecting to the URL of the last executed service
						response.sendRedirect(request.getServletContext().getContextPath() + "/" + responsePage);
					} else {
						response.sendRedirect(request.getServletContext().getContextPath()+ "/"+ validator.getErrorPage());
					}
				} else if (classes.size() == 1) {
					ArrayList<Service> services = this.getServiceClasses(classes.get(0));
					// executing services logic according to order
					String responsePage = "";
					for (int j = 0; j < services.size(); j++) {
						services.get(j).initService();
						responsePage = services.get(j).executeService(request);
					}

					// executing the closing algorithm of the services
					int z = services.size() - 1;
					while (z >= 0) {
						services.get(z).closeService();
						z--;
					}
					response.sendRedirect(request.getServletContext()
							.getContextPath() + "/" + responsePage);
				} else {
					throw new ServletException(
							"Error reading configuration file");
				}
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private ArrayList<Service> getServiceClasses(String classes) throws Exception {
		String[] serviceClasses = classes.split(",");
		ArrayList<Service> services=new ArrayList<Service>();
		for (int i = 0; i < serviceClasses.length; i++) {
			Service service = (Service) WebComponentLoader
					.createWebComponent(serviceClasses[i]);
			services.add(service);
		}
		return services;

	}
}
