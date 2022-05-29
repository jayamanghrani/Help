package com.tcs.employeeportal.config.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * The Class ConfigPropertiesUtil.
 * 
 * @author 738566
 */ 
public  class PropertiesUtil {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(PropertiesUtil.class);
	
	/**
	 * Gets the property.
	 * 
	 * @param property
	 *            the property
	 * @return the property
	 */
	public static String getConfigProperty(String property) {
		Properties prop = new Properties();
		InputStream input = null;
		try {

			input = PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties");
			prop.load(input);
			return prop.getProperty(property);
		} catch (IOException ex) {
			System.out.println("Failed to read properties file");
			LOGGER.error("Failed to read properties file", ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException ex) {
					LOGGER.error("Failed to read properties file", ex);
				}
			}
		}
		return property;
	}

	/**
	 * Gets the message.
	 *
	 * @param property the property
	 * @return the message
	 */
	public static String getMessage(String property) {
		Properties prop = new Properties();
		InputStream input = null;
		try {

			input = PropertiesUtil.class.getClassLoader().getResourceAsStream(
					"messages.properties");
			prop.load(input);
			return prop.getProperty(property);
		} catch (IOException ex) {
			LOGGER.info("Failed to read properties file", ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException ex) {
					LOGGER.info("Failed to read properties file", ex);
				}
			}
		}
		return property;
	}

	
	/**
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getConfigData(String key) {
		
		Map<String, String> dataMap = null;
		String value = null;
		
		if(null != LocalCache.dataMap.get("PORTAL_CONFIG_DATA")) {
			
			dataMap = (Map<String, String>) LocalCache.dataMap.get("PORTAL_CONFIG_DATA");
			value = dataMap.get(key);
		}
		
		return value;
	}
}
