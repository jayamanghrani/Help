/**
 * 
 */
package com.tcs.webreports.reports.asbo.response;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;
/**
 * @author 738796
 *
 */
public class ReportsWSDLResponseASBO extends WebReportsResponseObject{
	private static final long serialVersionUID = -6421542384336532162L;
	private String reportContentType;
	private String reportFileID;
	private String reportURL;
	/**
	 * @return the reportURL
	 */
	public String getReportURL() {
		return reportURL;
	}
	/**
	 * @param reportURL the reportURL to set
	 */
	public void setReportURL(String reportURL) {
		this.reportURL = reportURL;
	}
	/**
	 * @return the reportContentType
	 */
	public String getReportContentType() {
		return reportContentType;
	}
	/**
	 * @param reportContentType the reportContentType to set
	 */
	public void setReportContentType(String reportContentType) {
		this.reportContentType = reportContentType;
	}
	/**
	 * @return the reportFileID
	 */
	public String getReportFileID() {
		return reportFileID;
	}
	/**
	 * @param reportFileID the reportFileID to set
	 */
	public void setReportFileID(String reportFileID) {
		this.reportFileID = reportFileID;
	}
}