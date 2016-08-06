package org.brickmvc.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

/**
 * A class responsible for reading configuration files and represent them in
 * code
 * 
 * @author Mostafa
 * 
 */
public class ConfigurationManager {

	/**
	 * reads configuration file, parse it, and represent it in code
	 * 
	 * @param configurationFile
	 *            the path to the configuration file
	 * @return a map representing the configuration file
	 * @throws Exception
	 */
	public static Map<String, ArrayList<String>> loadConfigurationFile(String configurationFile) throws Exception {

		try {
			Map<String, ArrayList<String>> configuration = new HashMap<String, ArrayList<String>>();
			File xmlFile = new File(configurationFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList serviceList = doc.getElementsByTagName("service");
			for (int i = 0; i < serviceList.getLength(); i++) {
				Node service = serviceList.item(i);
				if (!service.hasChildNodes())
					continue;
				NodeList serviceInners = service.getChildNodes();
				String serviceUrl = "";
				ArrayList<String> serviceClasses = new ArrayList<String>();
				String validationClass = "";
				for (int j = 0; j < serviceInners.getLength(); j++) {
					Node serviceInner = serviceInners.item(j);
					String itemName = serviceInner.getNodeName();
					if (itemName.equals("url-pattern")) {
						serviceUrl = serviceInner.getTextContent().trim();
					} else if (itemName.equals("validation-class")) {
						validationClass = serviceInner.getTextContent().trim();
					} else if (itemName.equals("service-class")) {
						serviceClasses.add(serviceInner.getTextContent().trim());
					} else
						continue;
				}
				if (!serviceUrl.equals("") && !serviceClasses.isEmpty()) {
					String classes="";
					ArrayList<String> allClasses=new ArrayList<String>();
					for (String serviceClass : serviceClasses) {
						classes += (serviceClass + ",");
					}
					if (!validationClass.equals("")){ 
						allClasses.add(validationClass);
						allClasses.add(classes.substring(0,classes.lastIndexOf(",")));
						configuration.put(serviceUrl,allClasses);
					}
					else{
						allClasses.add(classes.substring(0,classes.lastIndexOf(",")));
						configuration.put(serviceUrl, allClasses);
					}
				} else
					continue;
			}
			return configuration;
		} catch (Exception e) {
			throw new Exception("Error loading Configuration file.");
		}
	}
}
