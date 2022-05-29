package com.tcs.webreports.asbo.report.request;

import com.tcs.webreports.vo.cmo.WebReportsRequestObject;

public class ReportGetParameterRequestASBO extends WebReportsRequestObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4251928726954884290L;
	private String reportName;
	private String reportFormat;
	 
	/**
	 * @return the reportFormat
	 */
	public String getReportFormat() {
		return reportFormat;
	}

	/**
	 * @param reportFormat the reportFormat to set
	 */
	public void setReportFormat(String reportFormat) {
		this.reportFormat = reportFormat;
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