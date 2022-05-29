/**
 * 
 */
package com.tcs.employeeportal.asbo.emp.request;

import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;

/**
 * @author 585226
 *
 */
public class TickerRequestASBO extends EmployeePortalRequestObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tickerinput;

	/**
	 * @return the tickerinput
	 */
	public String getTickerinput() {
		return tickerinput;
	}

	/**
	 * @param tickerinput the tickerinput to set
	 */
	public void setTickerinput(String tickerinput) {
		this.tickerinput = tickerinput;
	}
	
	

}
