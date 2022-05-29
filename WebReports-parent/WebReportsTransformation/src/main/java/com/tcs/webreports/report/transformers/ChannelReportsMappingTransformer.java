package com.tcs.webreports.report.transformers;

import java.text.ParseException;

import com.oracle.xmlns.oxp.service.v2.ReportRequest;
import com.tcs.webreports.asbo.report.request.ReportRequestASBO;
import com.tcs.webreports.asbo.report.response.ReportResponseASBO;
import com.tcs.webreports.config.utils.UtilProperties;
import com.tcs.webreports.exception.cmo.IntegrationTransformationException;
import com.tcs.webreports.reports.asbo.request.ReportsWSDLRequestASBO;
import com.tcs.webreports.reports.asbo.response.ReportsWSDLResponseASBO;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

public class ChannelReportsMappingTransformer {
	private ReportRequestASBO reportRequestASBO;
	private ReportResponseASBO reportResponseASBO;
	private ReportsWSDLRequestASBO reportsWSDLRequestASBO;
	private ReportsWSDLResponseASBO reportsWSDLResponseASBO;
	
	public ChannelReportsMappingTransformer() {
		this.reportResponseASBO = new ReportResponseASBO();
		this.reportsWSDLRequestASBO = new ReportsWSDLRequestASBO();
	}
	
	/**
	 * 
	 * @param reportRequestASBO
	 * @return
	 * @throws IntegrationTransformationException
	 * @throws ParseException
	 */
	public WebReportsRequestObject transformRequest(ReportRequestASBO reportRequestASBO)throws IntegrationTransformationException, ParseException
	  {
	    this.reportRequestASBO = reportRequestASBO;
	    this.reportsWSDLRequestASBO.setHeader(this.reportRequestASBO.getHeader());
	    transformRequest();
	    return this.reportsWSDLRequestASBO;
	  }

	/**
	 * 
	 * @throws ParseException
	 */
	private void transformRequest() throws ParseException {
	    ReportRequest reportRequest = new ReportRequest();
	    reportRequest.setAttributeFormat("xlsx");
	    reportRequest.setReportAbsolutePath(UtilProperties.getChannelMapAbsolutePath());
	    reportRequest.setReportOutputPath(UtilProperties.getChannelMapOutputPath());
	    this.reportsWSDLRequestASBO.setReportRequest(reportRequest);
	}

	/**
	 * 
	 * @param reportsWSDLResponseASBO
	 * @return
	 * @throws IntegrationTransformationException
	 */
    public WebReportsResponseObject transformResponse(ReportsWSDLResponseASBO reportsWSDLResponseASBO)throws IntegrationTransformationException
	  {
	    this.reportsWSDLResponseASBO = reportsWSDLResponseASBO;
	    this.reportResponseASBO.setHeader(this.reportsWSDLResponseASBO.getHeader());
	    this.reportResponseASBO.setReportContentType(this.reportsWSDLResponseASBO.getReportContentType());
	    return this.reportResponseASBO;
	  }
}
