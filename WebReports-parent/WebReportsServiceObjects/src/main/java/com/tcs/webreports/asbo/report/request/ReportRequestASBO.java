package com.tcs.webreports.asbo.report.request;

import com.tcs.webreports.vo.cmo.WebReportsRequestObject;

/**
 * @author 738796
 *
 */
public class ReportRequestASBO extends WebReportsRequestObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fromDate;
	private String toDate;
	private String partyCode;
	private String reportFormat;
	private String reportName;
	private String reportOutputPath;

	/**
	 * @return the reportOutputPath
	 */
	public String getReportOutputPath() {
		return reportOutputPath;
	}
	/**
	 * @param reportOutputPath the reportOutputPath to set
	 */
	public void setReportOutputPath(String reportOutputPath) {
		this.reportOutputPath = reportOutputPath;
	}
	
	/**
	 * @return the fromDate
	 */
	public String getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the toDate
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return the partyCode
	 */
	public String getPartyCode() {
		return partyCode;
	}
	/**
	 * @param partyCode the partyCode to set
	 */
	public void setPartyCode(String partyCode) {
		this.partyCode = partyCode;
	}
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
