package com.tcs.webreports.report.transformers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;

import com.oracle.xmlns.oxp.service.v2.ParamNameValue;
import com.oracle.xmlns.oxp.service.v2.ParamNameValues;
import com.oracle.xmlns.oxp.service.v2.ReportRequest;
import com.tcs.webreports.asbo.report.request.ReportRequestASBO;
import com.tcs.webreports.asbo.report.response.ReportResponseASBO;
import com.tcs.webreports.cache.service.ReportsPropertiesCacheService;
import com.tcs.webreports.config.utils.UtilProperties;
import com.tcs.webreports.enums.ReportParameterEnum;
import com.tcs.webreports.exception.cmo.IntegrationTransformationException;
import com.tcs.webreports.reports.asbo.request.ReportsWSDLRequestASBO;
import com.tcs.webreports.reports.asbo.response.ReportsWSDLResponseASBO;
import com.tcs.webreports.util.UtilConstants;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

public class ReportsTransformer {
	private static final Logger LOGGER = Logger
			.getLogger(ReportsTransformer.class);
	private ReportRequestASBO reportRequestASBO;
	private ReportResponseASBO reportResponseASBO;
	private ReportsWSDLRequestASBO reportsWSDLRequestASBO;
	private ReportsWSDLResponseASBO reportsWSDLResponseASBO;

	public ReportsTransformer() {
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
	public WebReportsRequestObject transformRequest(
			ReportRequestASBO reportRequestASBO)
			throws IntegrationTransformationException, ParseException {
		this.reportRequestASBO = reportRequestASBO;
		this.reportsWSDLRequestASBO.setHeader(this.reportRequestASBO
				.getHeader());
		transformRequest();
		return this.reportsWSDLRequestASBO;
	}

	/**
	 * Method to set the request parameter for report Premium Register.
	 * 
	 * @throws ParseException
	 */
	private void transformRequest() throws ParseException {
		SimpleDateFormat myFormat = new SimpleDateFormat(
				UtilConstants.DATE_FORMAT);
		
		/*DateFormat dfm = new SimpleDateFormat("dd-mm-yy hh:mm:ss");  

        long unixtime = 0;
        dfm.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));*/
        
        
        
        
		Date fromDt = myFormat.parse(this.reportRequestASBO.getFromDate());
		Date toDt = myFormat.parse(this.reportRequestASBO.getToDate());
		myFormat = new SimpleDateFormat("MM-dd-yyyy");
		myFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
		System.out.println(myFormat);
		String startDate = myFormat.format(fromDt);
		String endDate = myFormat.format(toDt);
		LOGGER.info("startDate : " + startDate);
		ParamNameValue fromDate = new ParamNameValue();
		fromDate.setName(ReportParameterEnum.FROMDATE.toString());
		fromDate.setValues(new String[] { startDate });
		LOGGER.info("endDate : " + endDate);
		ParamNameValue toDate = new ParamNameValue();
		toDate.setName(ReportParameterEnum.TODATE.toString());
		toDate.setValues(new String[] { endDate });
		ParamNameValue partyCode = new ParamNameValue();
		partyCode.setName(ReportParameterEnum.PARTYCODE.toString());
		partyCode.setValues(new String[] { this.reportRequestASBO
				.getPartyCode() });
		ReportRequest reportRequest = new ReportRequest();
		ParamNameValues paramNameValues = new ParamNameValues();
		ParamNameValue[] listOfParamNameValues = { fromDate, toDate, partyCode };
		paramNameValues.setListOfParamNameValues(listOfParamNameValues);
		reportRequest.setParameterNameValues(paramNameValues);
		reportRequest.setAttributeFormat(this.reportRequestASBO
				.getReportFormat().toLowerCase());
		String reportOutputPath = UtilProperties.getReportOutputPath()
				+ UtilConstants.getRandomFileName() + "."
				+ this.reportRequestASBO.getReportFormat().toLowerCase();
		reportRequest.setReportAbsolutePath(ReportsPropertiesCacheService
				.getAbsolutePath(this.reportRequestASBO.getReportName().replaceAll("\\s","").toLowerCase()));
		LOGGER.info("path : " + reportRequest.getReportAbsolutePath());
		reportRequest.setReportOutputPath(reportOutputPath);
		LOGGER.info("paramNameValues : "
				+ paramNameValues.getListOfParamNameValues());
	/*TimeZone timeZone = TimeZone.getDefault();
	timeZone.getDisplayName();
	System.out.println(timeZone.getDisplayName());
	
	reportRequest.setAttributeTimezone(TimeZone.getTimeZone("GMT+5:30"));*/

		this.reportsWSDLRequestASBO.setReportRequest(reportRequest);
		
		
	
	}

	/**
	 * Method to set parameter for response of report Premium Register.
	 * 
	 * @param reportsWSDLResponseASBO
	 * @return
	 * @throws IntegrationTransformationException
	 */
	public WebReportsResponseObject transformResponse(
			ReportsWSDLResponseASBO reportsWSDLResponseASBO)
			throws IntegrationTransformationException {
		this.reportsWSDLResponseASBO = reportsWSDLResponseASBO;
		this.reportResponseASBO.setHeader(this.reportsWSDLResponseASBO
				.getHeader());
		this.reportResponseASBO
				.setReportContentType(this.reportsWSDLResponseASBO
						.getReportContentType());
		LOGGER.info("Fileid : " + reportsWSDLResponseASBO.getReportFileID());
		this.reportResponseASBO.setReportFileID(this.reportsWSDLResponseASBO
				.getReportFileID());
		String reporturl= reportsWSDLResponseASBO.getReportURL();
		int lastIndex = reporturl.lastIndexOf("/");
		String s2 =reporturl.substring(lastIndex + 1);
		this.reportResponseASBO.setReportURL(UtilProperties.getReportURL()+s2);
		LOGGER.info("URL : "
				+ UtilProperties.getReportURL() +s2);

		return this.reportResponseASBO;
	}
}