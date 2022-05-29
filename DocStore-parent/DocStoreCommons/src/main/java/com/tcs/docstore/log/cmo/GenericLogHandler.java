/**
 * 
 */
package com.tcs.docstore.log.cmo;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;


/**
 * @author 738566
 *
 */
public class GenericLogHandler {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(GenericLogHandler.class);

	/**
	 * 
	 * @param logDetails
	 */
	public void handle(LogDetails logDetails) {

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("methodName", logDetails.getMethodName());
		jsonObject.addProperty("eventName", logDetails.getEventName());
		/*jsonObject.addProperty("channelName", logDetails.getChannelName());
		jsonObject.addProperty("deviceName", logDetails.getDeviceName());*/
		jsonObject.addProperty("message", logDetails.getMessage());
		LOGGER.info(jsonObject);
	}
}
