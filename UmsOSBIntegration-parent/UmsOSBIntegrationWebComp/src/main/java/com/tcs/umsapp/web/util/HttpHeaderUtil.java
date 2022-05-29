package com.tcs.umsapp.web.util;
import com.tcs.umsapp.osb.vo.cmo.Header;

public class HttpHeaderUtil {

	public HttpHeaderUtil() {
	}
	/**
	 * 
	 * @param userId
	 * @param firstname
	 * @param memberOf
	 * @param eventID
	 * @return
	 */
	public Header generateSpringHeader(String userId, String firstname,String memberOf,
			String eventID){
		Header header = new Header();
		header.setEventID(eventID);
		header.setEmployeeName(firstname);
		header.setEmployeeId(userId);
		return header;	
	}
}