package com.tcs.webreports.report.transformers;

import org.apache.log4j.Logger;
import com.google.gson.Gson;
import com.oracle.xmlns.oxp.service.v2.ReportRequest;
import com.tcs.webreports.asbo.report.request.ReportGetParameterRequestASBO;
import com.tcs.webreports.asbo.report.response.ReportGetParameterResponseASBO;
import com.tcs.webreports.cache.service.ReportsPropertiesCacheService;
import com.tcs.webreports.exception.cmo.IntegrationTransformationException;
import com.tcs.webreports.reports.asbo.request.ReportsWSDLGetParameterRequestASBO;
import com.tcs.webreports.reports.asbo.response.ReportsWSDLGetParameterResponseASBO;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

/**
 * @author 738796
 * 
 */
public class GetParameterReportsTransformers {
	private static final Logger LOGGER = Logger.getLogger(GetParameterReportsTransformers.class);
	private ReportGetParameterRequestASBO reportGetParameterRequestASBO;
	private ReportGetParameterResponseASBO reportGetParameterResponseASBO;
	private ReportsWSDLGetParameterRequestASBO reportWSDLGetParameterRequestASBO;
	private ReportsWSDLGetParameterResponseASBO reportWSDLGetParameterResponseASBO;

	public GetParameterReportsTransformers() {
		this.reportGetParameterResponseASBO = new ReportGetParameterResponseASBO();
		this.reportWSDLGetParameterRequestASBO = new ReportsWSDLGetParameterRequestASBO();
	}

	/**
	 * Method transformRequest is to set the header for report get parameter
	 * @param reportGetParameterRequestASBO
	 * @return
	 * @throws IntegrationTransformationException
	 */
	public WebReportsRequestObject transformRequest(
			ReportGetParameterRequestASBO reportGetParameterRequestASBO)
			throws IntegrationTransformationException {
		{
			this.reportGetParameterRequestASBO = reportGetParameterRequestASBO;
			this.reportWSDLGetParameterRequestASBO.setHeader(this.reportGetParameterRequestASBO.getHeader());
		//	this.reportWSDLGetParameterRequestASBO.setName(this.reportGetParameterRequestASBO.getReportName());
			transformRequest();
			return this.reportWSDLGetParameterRequestASBO;
		}
	}

	/**
	 * This is to set the report request parameters for report
	 */
	private void transformRequest() {
		ReportRequest reportRequest = new ReportRequest();
		LOGGER.info("Inside get parameter transformer"
				+ this.reportGetParameterRequestASBO.getReportName());
		reportRequest.setReportAbsolutePath(ReportsPropertiesCacheService
				.getAbsolutePath(this.reportGetParameterRequestASBO
						.getReportName().replaceAll("\\s", "").toLowerCase()));
		reportRequest.setAttributeFormat("xml");
		LOGGER.info("Report req for get param : " + reportRequest);
		this.reportWSDLGetParameterRequestASBO.setReportRequest(reportRequest);
	}

	/**
	 * 
	 * @param reportWSDLGetParameterResponseASBO
	 * @return
	 * @throws IntegrationTransformationException
	 */
	public WebReportsResponseObject transformResponse(
			ReportsWSDLGetParameterResponseASBO reportWSDLGetParameterResponseASBO)
			throws IntegrationTransformationException {
		this.reportWSDLGetParameterResponseASBO = reportWSDLGetParameterResponseASBO;
		Gson gson = new Gson();
		String json = gson.toJson(this.reportWSDLGetParameterResponseASBO);
		LOGGER.info("json tranformer parameter " + json);
		this.reportGetParameterResponseASBO.setHeader(this.reportWSDLGetParameterResponseASBO.getHeader());
		this.reportGetParameterResponseASBO.setParamNameValue(this.reportWSDLGetParameterResponseASBO.getParamNameValue());
		this.reportGetParameterResponseASBO.setName(this.reportWSDLGetParameterResponseASBO.getName());
		return this.reportGetParameterResponseASBO;
	}
}