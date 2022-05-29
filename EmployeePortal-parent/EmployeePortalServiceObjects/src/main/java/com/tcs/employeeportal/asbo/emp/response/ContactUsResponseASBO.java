/**
 * 
 */
package com.tcs.employeeportal.asbo.emp.response;

import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

/**
 * @author 585226
 *
 */
public class ContactUsResponseASBO  extends EmployeePortalResponseObject{
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The status code. */
	private String statusCode;
	
	/** The status message. */
	private String statusMessage;

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	

}
