package com.tcs.webreports.cache.asbo.request;

import com.tcs.webreports.vo.cmo.WebReportsRequestObject;

public class GetChannelReportsCacheRequestASBO extends WebReportsRequestObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8930389600099892740L;
	
	private  String channelName;

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
