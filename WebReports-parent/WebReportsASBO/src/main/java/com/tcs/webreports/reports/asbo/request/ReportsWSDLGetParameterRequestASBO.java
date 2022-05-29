/**
 * 
 */
package com.tcs.webreports.reports.asbo.request;

import com.oracle.xmlns.oxp.service.v2.ReportRequest;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;

/**
 * @author 738796
 *
 */
public class ReportsWSDLGetParameterRequestASBO extends WebReportsRequestObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7385103569816518521L;
	private ReportRequest reportRequest;
    private String name;
    
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the reportRequest
	 */
	public ReportRequest getReportRequest() {
		return reportRequest;
	}

	/**
	 * @param reportRequest the reportRequest to set
	 */
	public void setReportRequest(ReportRequest reportRequest) {
		this.reportRequest = reportRequest;
	} 
}