package com.tcs.employeeportal.controller.util;




import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tcs.employeeportal.controller.util.ControllerUtil;


public class ControllerUtil {
	
	
	/** The gson. */
	private static Gson gson;

	/**
	 * Instantiates a new controller util.
	 */
	private ControllerUtil() {
		gson = getGson();
	}

	/**
	 * Gets the gson.
	 * 
	 * @return the gson
	 */
	public static Gson getGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.disableHtmlEscaping();
		gson = gsonBuilder.setDateFormat("dd-MMM-yy").create();
		return gson;
	}

	/**
	 * Sets the gson.
	 * 
	 * @param gson
	 *            the gson to set
	 */
	public static void setGson(Gson gson) {
		ControllerUtil.gson = gson;
	}
	
}
