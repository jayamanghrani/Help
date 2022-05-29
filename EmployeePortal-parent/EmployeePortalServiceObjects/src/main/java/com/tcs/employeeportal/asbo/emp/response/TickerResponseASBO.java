/**
 * 
 */
package com.tcs.employeeportal.asbo.emp.response;

import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

/**
 * @author 585226
 *
 */
public class TickerResponseASBO extends EmployeePortalResponseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String datevalue;
	private String monthlyPremium;
	private String dailyPremium;
	private String uptoPremium;
	public String getDatevalue() {
		return datevalue;
	}
	public void setDatevalue(String datevalue) {
		this.datevalue = datevalue;
	}
	public String getMonthlyPremium() {
		return monthlyPremium;
	}
	public void setMonthlyPremium(String monthlyPremium) {
		this.monthlyPremium = monthlyPremium;
	}
	public String getDailyPremium() {
		return dailyPremium;
	}
	public void setDailyPremium(String dailyPremium) {
		this.dailyPremium = dailyPremium;
	}
	public String getUptoPremium() {
		return uptoPremium;
	}
	public void setUptoPremium(String uptoPremium) {
		this.uptoPremium = uptoPremium;
	}

}
