/**
 * 
 */
package com.tcs.employeeportal.web.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.log.cmo.LogDetails;
import com.tcs.employeeportal.util.UtilConstants;
import com.tcs.employeeportal.vo.cmo.EmployeePortalSessionVO;
import com.tcs.employeeportal.vo.cmo.Header;
import com.tcs.employeeportal.web.util.HttpHeaderProperty;
import com.tcs.employeeportal.web.util.HttpHeaderUtil;

/**
 * @author 738566
 *
 */
@Component("httpHeaderUtil")
public class HttpHeaderUtil {
	private static final Logger LOGGER = Logger.getLogger("empPortalLogger");

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
	public Header generateSpringHeader(/*HttpHeaderProperty httpHeaderProperties,*/
			String eventID) throws IntegrationTechnicalException {
		Header header = new Header();
		/*	List<String> applicationIds = httpHeaderProperties.getProperty().get(
				"applicationid");
		if (applicationIds == null || applicationIds.isEmpty()) {
			throw new IntegrationTechnicalException(
					"header with application id missing");
		} else {
			header.setApplicationId((applicationIds.get(0)));
		}*/
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date todaysDate= new Date();
		String currentDate=df.format(todaysDate);
		
		header.setTodaysDate(currentDate);
		header.setEventID(eventID);
		// omkar added for alfesco doc search
		//header.setEmployeeId(employeeId);
		/*List<String> coRelationIds = httpHeaderProperties.getProperty().get("coRelationId");
		header.setCoRelationId(coRelationIds.get(0));
		EmployeePortalSessionVO employeePortalSessionVO = generateSpringSession(httpHeaderProperties);
		header.setEmployeePortalSessionVO(employeePortalSessionVO);*/
		LOGGER.info("EventId : " + header.getEventID());
		return header;
	}


	public Header generateSpringHeaderLogin(String userId, String firstname,String memberOf,
			String eventID) throws IntegrationTechnicalException {
		LOGGER.info("Inside generateSpringHeaderLogin");
		Header header = new Header();
		/*List<String> applicationIds = httpHeaderProperties.getProperty().get(
				"applicationid");
		if (applicationIds == null || applicationIds.isEmpty()) {
			throw new IntegrationTechnicalException(
					"header with application id missing");
		} else {
			header.setApplicationId((applicationIds.get(0)));
		}


		 */

		String pattern1 = "cn=";
		String pattern2 = ",cn=groups";
		String text =memberOf;
		LOGGER.info("Inside generateSpringHeaderLogin"+memberOf);
		//	String text = "cn=employee,cn=groups,dc=newindia,dc=co,dc=in:cn=iimswl,cn=groups,dc=newindia,dc=co,dc=in:cn=reports,cn=groups,dc=newindia,dc=co,dc=in";
		String result = memberOf.replaceAll("\\\\","");		
		LOGGER.info("text result: " + result);

		Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));
		Matcher m = p.matcher(result);
		String group="";
		while (m.find()) {
			LOGGER.info(m.group(1));
		//	if(!(m.group(1).equalsIgnoreCase("employee")))
				group=group.concat(m.group(1)+",");
		}
		
		LOGGER.info("group--"+group);

		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date todaysDate= new Date();
		/*String date1=df.format(todaysDate);
		LOGGER.info("date1--"+date1);
		Date date2=new Date();
		try {
			date2 = df.parse(date1);
		} catch (ParseException e) {
			e.printStackTrace();
			LOGGER.error(e.getStackTrace());
		}*/
		String currentDate=df.format(todaysDate);

		LOGGER.info("currentDate--"+currentDate);
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
		LOGGER.info("Groups : " + header.getGroups());
		return header;
	}



	public Header generateSpringHeaderPwdExpiry(String pwdChangeDt) throws IntegrationTechnicalException {
		LOGGER.info("Inside generateSpringHeaderLogin");
		Header header = new Header();

		return header;
	}

	/**
	 * 
	 * @param httpHeaderProperty
	 * @return
	 */
	/*	public EmployeePortalSessionVO generateSpringSession(
			HttpHeaderProperty httpHeaderProperty) {
		EmployeePortalSessionVO employeePortalSessionVO = new EmployeePortalSessionVO();
		LOGGER.info("hhttp header properties...." + httpHeaderProperty);
		// extract all these fields from headers

		employeePortalSessionVO.setRolecode("CUSTOMER");
		employeePortalSessionVO.setSessionStatus("");
		employeePortalSessionVO.setUserCode("");
		employeePortalSessionVO.setUserName("SUPERUSER");
		return employeePortalSessionVO;

	}*/

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
		logDetails.setDebugLevel(UtilConstants.DEBUG);
		logDetails.setDeviceName(null);
		logDetails.setEventName(header.getEventID());
		logDetails.setMessage(message);
		logDetails.setMethodName(methodName);
		return logDetails;
	}
	


}
