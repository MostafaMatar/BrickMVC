package org.brickmvc.core;

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
	public static Map<String, String> loadConfigurationFile(
			String configurationFile) throws Exception {

		try {
			Map<String, String> configuration = new HashMap<String, String>();
			File fXmlFile = new File(configurationFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			NodeList serviceList = doc.getElementsByTagName("service");
			for (int i = 0; i < serviceList.getLength(); i++) {
				Node service = serviceList.item(i);
				if (!service.hasChildNodes())
					continue;
				NodeList serviceInners = service.getChildNodes();
				String serviceUrl = "";
				String serviceClass = "";
				String validationClass = "";
				for (int j = 0; j < serviceInners.getLength(); j++) {
					Node serviceInner = serviceInners.item(j);
					String itemName = serviceInner.getNodeName();
					if (itemName.equals("url-pattern")) {
						serviceUrl = serviceInner.getTextContent().trim();
					} else if (itemName.equals("validation-class")) {
						validationClass += serviceInner.getTextContent().trim();
					} else if (itemName.equals("service-class")) {
						serviceClass += (serviceInner.getTextContent().trim());
					} else
						continue;
				}
				if (!serviceUrl.equals("") && !serviceClass.equals("") && !validationClass.equals(""))
					configuration.put(serviceUrl, validationClass + ","
							+ serviceClass);
				else if(!serviceUrl.equals("") && !serviceClass.equals("") && validationClass.equals(""))
					configuration.put(serviceUrl,serviceClass);
				else 
					continue;
			}
			return configuration;
		} catch (Exception e) {
			throw new Exception("Error loading Configuration file.");
		}
	}
}
