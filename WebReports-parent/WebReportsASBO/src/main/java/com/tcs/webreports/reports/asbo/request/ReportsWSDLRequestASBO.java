/**
 * 
 */
package com.tcs.webreports.reports.asbo.request;
import com.oracle.xmlns.oxp.service.v2.ReportRequest;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;
;
/**
 * @author 738796
 *
 */
public class ReportsWSDLRequestASBO extends WebReportsRequestObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9149223346346654737L;
	/**
	 * 
	 */
//	
//	
//	private String reportFormat;
//	private String reportAbsolutePath;
//	private String reportOutputPath;

	private ReportRequest reportRequest; 
	
	
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
	/**
	 * @return the reportFormat
	 *//*
	public String getReportFormat() {
		return reportFormat;
	}
	*//**
	 * @param reportFormat the reportFormat to set
	 *//*
	public void setReportFormat(String reportFormat) {
		this.reportFormat = reportFormat;
	}
	*//**
	 * @return the reportAbsolutePath
	 *//*
	public String getReportAbsolutePath() {
		return reportAbsolutePath;
	}
	*//**
	 * @param reportAbsolutePath the reportAbsolutePath to set
	 *//*
	public void setReportAbsolutePath(String reportAbsolutePath) {
		this.reportAbsolutePath = reportAbsolutePath;
	}
	*//**
	 * @return the reportOutputPath
	 *//*
	public String getReportOutputPath() {
		return reportOutputPath;
	}
	*//**
	 * @param reportOutputPath the reportOutputPath to set
	 *//*
	public void setReportOutputPath(String reportOutputPath) {
		this.reportOutputPath = reportOutputPath;
	}
	*/
	
	
	

		
	
	
	
	

	
	
	
}
