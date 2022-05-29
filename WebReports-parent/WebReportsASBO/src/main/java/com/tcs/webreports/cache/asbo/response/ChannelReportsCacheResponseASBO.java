/**
 * 
 */
package com.tcs.webreports.cache.asbo.response;

import java.util.List;

import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

/**
 * @author 738566
 *
 */
public class ChannelReportsCacheResponseASBO extends WebReportsResponseObject {

	private static final long serialVersionUID = -6205874857063598365L;
	
	private String channelName;
	private List<String> reportsName;
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
	 * @return the reportsName
	 */
	public List<String> getReportsName() {
		return reportsName;
	}
	/**
	 * @param reportsName the reportsName to set
	 */
	public void setReportsName(List<String> reportsName) {
		this.reportsName = reportsName;
	}
	

}
