/**
 * 
 */
package com.tcs.employeeportal.cache.model;

import com.tcs.employeeportal.cache.key.CacheKey;

/**
 * @author 585226
 *
 */
public class TickerValues implements  CacheKey {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String datevalue;
	private String monthlyPremium;
	private String dailyPremium;
	private String uptoPremium;
	/**
	 * @return the datevalue
	 */
	public String getDatevalue() {
		return datevalue;
	}
	/**
	 * @param datevalue the datevalue to set
	 */
	public void setDatevalue(String datevalue) {
		this.datevalue = datevalue;
	}
	/**
	 * @return the monthlyPremium
	 */
	public String getMonthlyPremium() {
		return monthlyPremium;
	}
	/**
	 * @param monthlyPremium the monthlyPremium to set
	 */
	public void setMonthlyPremium(String monthlyPremium) {
		this.monthlyPremium = monthlyPremium;
	}
	/**
	 * @return the dailyPremium
	 */
	public String getDailyPremium() {
		return dailyPremium;
	}
	/**
	 * @param dailyPremium the dailyPremium to set
	 */
	public void setDailyPremium(String dailyPremium) {
		this.dailyPremium = dailyPremium;
	}
	/**
	 * @return the uptoPremium
	 */
	public String getUptoPremium() {
		return uptoPremium;
	}
	/**
	 * @param uptoPremium the uptoPremium to set
	 */
	public void setUptoPremium(String uptoPremium) {
		this.uptoPremium = uptoPremium;
	}

}
