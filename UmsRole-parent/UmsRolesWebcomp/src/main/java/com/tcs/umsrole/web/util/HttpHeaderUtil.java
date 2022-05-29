package com.tcs.umsrole.web.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tcs.umsrole.vo.cmo.Header;



public class HttpHeaderUtil {

	public HttpHeaderUtil() {
	}

	
	/*This Method call from service /getRequestTrackerDetailPost */
	public Header generateSpringHeader(String userId, String eventID){
		Header header = new Header();
		
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date todaysDate= new Date();
		String currentDate=df.format(todaysDate);

		header.setEventID(eventID);
		header.setEmployeeId(userId);
		header.setTodaysDate(currentDate);
		
		return header;
		
	}
	
}
