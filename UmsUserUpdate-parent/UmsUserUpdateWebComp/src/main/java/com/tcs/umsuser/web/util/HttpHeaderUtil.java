/**
 * 
 */
package com.tcs.umsuser.web.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tcs.umsuser.exception.cmo.IntegrationTechnicalException;
import com.tcs.umsuser.vo.cmo.Header;


@Component("httpHeaderUtil")
public class HttpHeaderUtil {
	private static final Logger LOGGER = Logger.getLogger("empPortalDB");

	/**
	 * Instantiates a new web util.
	 */
	public HttpHeaderUtil() {
		//default constructor
	}

	/**
	 * 
	 * @param httpHeaderProperties
	 * @param eventID
	 * @return
	 * @throws IntegrationTechnicalException
	 */
	public Header generateSpringHeader(
			String eventID) throws IntegrationTechnicalException {
		Header header = new Header();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date todaysDate= new Date();
		String currentDate=df.format(todaysDate);
		header.setTodaysDate(currentDate);
		header.setEventID(eventID);
		LOGGER.info("EventId : " + header.getEventID());
		return header;
	}
}
