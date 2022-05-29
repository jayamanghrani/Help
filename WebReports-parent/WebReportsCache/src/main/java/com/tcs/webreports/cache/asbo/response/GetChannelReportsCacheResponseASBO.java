package com.tcs.webreports.cache.asbo.response;

import java.util.List;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

public class GetChannelReportsCacheResponseASBO extends WebReportsResponseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 180973726487119703L;
	
	private List<String> reportNames;

	/**
	 * @return the reportNames
	 */
	public List<String> getReportNames() {
		return reportNames;
	}

	/**
	 * @param reportNames the reportNames to set
	 */
	public void setReportNames(List<String> reportNames) {
		this.reportNames = reportNames;
	}

	
}
