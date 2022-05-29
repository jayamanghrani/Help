/**
 * 
 */
package com.tcs.docstore.web.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tcs.docstore.exception.cmo.IntegrationTechnicalException;
import com.tcs.docstore.log.cmo.LogDetails;
import com.tcs.docstore.util.UtilConstants;
import com.tcs.docstore.vo.cmo.Header;
import com.tcs.docstore.web.util.HttpHeaderUtil;

/**
 * @author 738566
 *
 */
@Component("httpHeaderUtil")
public class HttpHeaderUtil {
	private static final Logger LOGGER = Logger.getLogger("docStoreLogger");

	/**
	 * Instantiates a new web util.
	 */
	public HttpHeaderUtil() {
	}

	/**
	 * 
	 * @param httpHeaderProperties
	 * @param eventID
	 * @return
	 * @throws IntegrationTechnicalException
	 */
	public Header generateSpringHeader(String eventID) throws IntegrationTechnicalException {
		Header header = new Header();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date todaysDate= new Date();
		String currentDate=df.format(todaysDate);
		
		header.setTodaysDate(currentDate);
		header.setEventID(eventID);
		LOGGER.info("EventId : " + header.getEventID());
		return header;
	}


	public Header generateSpringHeaderLogin(String userId, String firstname,String memberOf,
			String eventID) throws IntegrationTechnicalException {
		LOGGER.info("Inside generateSpringHeaderLogin");
		Header header = new Header();
		String pattern1 = "cn=";
		String pattern2 = ",cn=groups";
		String text =memberOf;
		LOGGER.info("Inside generateSpringHeaderLogin"+memberOf);
		String result = memberOf.replaceAll("\\\\","");		
		LOGGER.info("text result: " + result);

		Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));
		Matcher m = p.matcher(result);
		String group="";
		while (m.find()) {
			LOGGER.info(m.group(1));
				group=group.concat(m.group(1)+",");
		}
		
		LOGGER.info("group--"+group);

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date todaysDate= new Date();

		String currentDate=df.format(todaysDate);

		LOGGER.debug("currentDate--"+currentDate);
		header.setGroups(group);
		header.setEventID(eventID);
		header.setEmployeeName(firstname);
		header.setEmployeeId(userId);
		header.setTodaysDate(currentDate);

		/*List<String> coRelationIds = httpHeaderProperties.getProperty().get("coRelationId");
		header.setCoRelationId(coRelationIds.get(0));
		EmployeePortalSessionVO employeePortalSessionVO = generateSpringSession(httpHeaderProperties);
		header.setEmployeePortalSessionVO(employeePortalSessionVO);*/
		LOGGER.info("EventId : " + header.getEventID());
		LOGGER.debug("Groups : " + header.getGroups());
		return header;
	}



	public Header generateSpringHeaderPwdExpiry(String pwdChangeDt) throws IntegrationTechnicalException {
		LOGGER.info("Inside generateSpringHeaderLogin");
		Header header = new Header();

		return header;
	}


	/**
	 * Generate log details.
	 * 
	 * @param header
	 *            the header
	 * @param message
	 *            the message
	 * @param methodName
	 *            the method name
	 * @return the log details
	 */
	public static LogDetails generateLogDetails(Header header, String message,
			String methodName) {
		LogDetails logDetails = new LogDetails();
		//	logDetails.setChannelName(header.getApplicationId());
		logDetails.setClassName(null);
		logDetails.setDebugLevel(UtilConstants.INFO);
		logDetails.setDeviceName(null);
		logDetails.setEventName(header.getEventID());
		logDetails.setMessage(message);
		logDetails.setMethodName(methodName);
		return logDetails;
	}

}
