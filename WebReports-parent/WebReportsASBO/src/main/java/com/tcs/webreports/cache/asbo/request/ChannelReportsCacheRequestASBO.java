/**
 * 
 */
package com.tcs.webreports.cache.asbo.request;

import com.tcs.webreports.vo.cmo.WebReportsRequestObject;

/**
 * @author 738566
 *
 */
public class ChannelReportsCacheRequestASBO extends WebReportsRequestObject {

	private static final long serialVersionUID = -6767800754977223050L;
	
	private String channelName;

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
	

}
