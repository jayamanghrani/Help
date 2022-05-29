package com.tcs.umsapp.web.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tcs.umsapp.upload.exception.cmo.IntegrationTechnicalException;
import com.tcs.umsapp.upload.vo.cmo.Header;


public class HttpHeaderUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpHeaderUtil.class);
	
	public HttpHeaderUtil() {
	}

	
	/*This Method call from service /uploadFile */
	public Header generateHeader(String userId, String firstname,String memberOf,
			String eventID) throws IntegrationTechnicalException {
		LOGGER.info("Inside generateHeader");
		Header header = new Header();

		String pattern1 = "cn=";
		String pattern2 = ",cn=groups";
		String result = memberOf.replaceAll("\\\\","");		
		
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
		header.setGroups(group);
		header.setEventID(eventID);
		header.setEmployeeName(firstname);
		header.setEmployeeId(userId);
		header.setTodaysDate(currentDate);

		return header;
	}


	
	
}
