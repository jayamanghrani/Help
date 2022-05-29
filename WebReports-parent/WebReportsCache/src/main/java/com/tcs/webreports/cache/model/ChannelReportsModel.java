package com.tcs.webreports.cache.model;

import java.io.Serializable;

public class ChannelReportsModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2755414130441930436L;
	
	private String channelName;
	private String reportName;
	/**
	 * @return the channelName
	 */
	public String getChannelName() {
		return channelName;
	}
	/**
	 * @param channelName the channelName to set
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	/**
	 * @return the reportName
	 */
	public String getReportName() {
		return reportName;
	}
	/**
	 * @param reportName the reportName to set
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	

}
