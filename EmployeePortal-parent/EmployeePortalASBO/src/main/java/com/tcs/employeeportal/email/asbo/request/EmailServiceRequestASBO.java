/**
 * 
 */
package com.tcs.employeeportal.email.asbo.request;

import java.util.Map;

import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;

/**
 * @author 738566
 *
 */
public class EmailServiceRequestASBO extends EmployeePortalRequestObject{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5504503188906619388L;
	
	/** The data. */
	private Map<String, String> data;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Map<String, String> getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the data to set
	 */
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	
	
}
