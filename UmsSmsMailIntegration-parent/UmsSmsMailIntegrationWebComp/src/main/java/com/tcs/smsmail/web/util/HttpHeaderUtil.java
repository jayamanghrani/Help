package com.tcs.smsmail.web.util;
import com.tcs.umsapp.smsmail.vo.cmo.Header;

public class HttpHeaderUtil {

	public HttpHeaderUtil() {
	}

	public Header generateSpringHeader(String userId, String firstname,String memberOf,
			String eventID){
		Header header = new Header();
		header.setEventID(eventID);
		header.setEmployeeName(firstname);
		header.setEmployeeId(userId);
		return header;
	}
	
}
