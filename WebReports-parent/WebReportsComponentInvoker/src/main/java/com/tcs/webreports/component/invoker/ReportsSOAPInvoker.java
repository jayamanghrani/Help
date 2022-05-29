package com.tcs.webreports.component.invoker;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.oracle.xmlns.oxp.service.v2.ParamNameValues;
import com.oracle.xmlns.oxp.service.v2.ReportRequest;
import com.oracle.xmlns.oxp.service.v2.ReportResponse;
import com.oracle.xmlns.oxp.service.v2.ReportService_ServiceLocator;
import com.tcs.webreports.config.utils.UtilProperties;
import com.tcs.webreports.exception.cmo.IntegrationTechnicalException;
import com.tcs.webreports.reports.asbo.request.ReportsWSDLGetParameterRequestASBO;
import com.tcs.webreports.reports.asbo.request.ReportsWSDLRequestASBO;
import com.tcs.webreports.reports.asbo.response.ReportsWSDLGetParameterResponseASBO;
import com.tcs.webreports.reports.asbo.response.ReportsWSDLResponseASBO;
import com.tcs.webreports.util.UtilConstants;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

public class ReportsSOAPInvoker {
	
	private static final Logger LOGGER = Logger.getLogger(ReportsSOAPInvoker.class);
	/**
	 * Request mapping method 
	 * @param requestVO
	 * @return
	 * @throws IntegrationTechnicalException
	 */
	public WebReportsResponseObject invokeReportRequest(
			WebReportsRequestObject requestVO)
			throws IntegrationTechnicalException {
		if (UtilConstants.GET_REPORT.equalsIgnoreCase(requestVO.getHeader()
				.getEventID())) {
			return getReport(requestVO);
		} else if (UtilConstants.GET_REPORT_PARAMETER
				.equalsIgnoreCase(requestVO.getHeader().getEventID())) {
			return invokeGetParameterRequest(requestVO);
		} else if (UtilConstants.GET_REPORT_CHANNEL_MAPPING
				.equalsIgnoreCase(requestVO.getHeader().getEventID())) {
			return invokeChannelReportMapping(requestVO);
		} else {
			return null;
		}
	}
	
	/**
	 * Method to set the parameter for report Get Parameter Premium Register.
	 * @param requestVO
	 * @return
	 * @throws IntegrationTechnicalException
	 */
	public WebReportsResponseObject invokeGetParameterRequest(
			WebReportsRequestObject requestVO)
			throws IntegrationTechnicalException {
		ReportsWSDLGetParameterRequestASBO reportsWSDLGetParameterRequestASBO = new ReportsWSDLGetParameterRequestASBO();
		ReportService_ServiceLocator locator = new ReportService_ServiceLocator();

		ReportsWSDLGetParameterResponseASBO reportsWSDLGetParameterResponseASBO = new ReportsWSDLGetParameterResponseASBO();
		if ((requestVO instanceof ReportsWSDLGetParameterRequestASBO)) {
			reportsWSDLGetParameterRequestASBO = (ReportsWSDLGetParameterRequestASBO) requestVO;
		} else {
			throw new IntegrationTechnicalException();
		}
		try {
			ParamNameValues param = locator
					.getReportService()
					.getReportParameters(
							reportsWSDLGetParameterRequestASBO
									.getReportRequest(),
							UtilProperties.getReportUserId(),
							UtilProperties.getReportPassword());
			Gson gson = new Gson();
			String json = gson.toJson(param);
			LOGGER.info(json);
			reportsWSDLGetParameterResponseASBO.setName(reportsWSDLGetParameterRequestASBO.getName());
			reportsWSDLGetParameterResponseASBO.setParamNameValue(param);
		} catch (Exception e)
		{
			reportsWSDLGetParameterResponseASBO = null;
			LOGGER.error(e);
		}
		return reportsWSDLGetParameterResponseASBO;
	}

	/**
	 * Method to get report
	 * @param requestVO
	 * @return
	 * @throws IntegrationTechnicalException
	 */
	public WebReportsResponseObject getReport(WebReportsRequestObject requestVO)
			throws IntegrationTechnicalException {

		ReportResponse response = new ReportResponse();
		ReportsWSDLRequestASBO wsdlRequestASBO = null;
		ReportsWSDLResponseASBO wsdlResponseASBO = new ReportsWSDLResponseASBO();
		if ((requestVO instanceof ReportsWSDLRequestASBO))
			wsdlRequestASBO = (ReportsWSDLRequestASBO) requestVO;
		else {
			throw new IntegrationTechnicalException();
		}
		try {

			Gson gson = new Gson();
			String json = gson.toJson(wsdlRequestASBO.getReportRequest());
			LOGGER.info(json);
			response = runReport(wsdlRequestASBO.getReportRequest());
			if (response != null) {
				LOGGER.info("report response" + new Gson().toJson(response));
				wsdlResponseASBO.setReportContentType(response
						.getReportContentType());
				wsdlResponseASBO.setReportFileID(wsdlRequestASBO.getReportRequest().getReportOutputPath());
				wsdlResponseASBO.setReportURL(wsdlRequestASBO
						.getReportRequest().getReportOutputPath());
				LOGGER.info(wsdlResponseASBO.getReportFileID());
			} else {
				wsdlResponseASBO = null;
				LOGGER.equals("Error in service");
			}
		} catch (Exception e) {
			LOGGER.error("Error in service " + e);
		}
		return wsdlResponseASBO;
	}

	/**
	 * Method to invoke runReport service for reports.
	 * @param reportRequest
	 * @return
	 */
	public ReportResponse runReport(ReportRequest reportRequest) {

		ReportService_ServiceLocator locator = new ReportService_ServiceLocator();
		ReportResponse reportResponse = new ReportResponse();
		try {
			reportResponse = locator.getReportService().runReport(
					reportRequest, UtilProperties.getReportUserId(),
					UtilProperties.getReportPassword());
		} catch (RemoteException e) {
			reportResponse = null;
			LOGGER.error(e);
		} catch (ServiceException e) {
			reportResponse = null;
			LOGGER.error(e);
		} catch (Exception e) {
			reportResponse = null;
			LOGGER.error(e);
		}
		return reportResponse;
	}

	/**
	 * Method to invoke channel-reports mapping service
	 * @param requestVO
	 * @return
	 * @throws IntegrationTechnicalException
	 */
	public WebReportsResponseObject invokeChannelReportMapping(
			WebReportsRequestObject requestVO)
			throws IntegrationTechnicalException {
		ReportResponse response = new ReportResponse();
		ReportsWSDLRequestASBO wsdlRequestASBO = null;
		ReportsWSDLResponseASBO wsdlResponseASBO = new ReportsWSDLResponseASBO();
		if ((requestVO instanceof ReportsWSDLRequestASBO))
			wsdlRequestASBO = (ReportsWSDLRequestASBO) requestVO;
		else {
			throw new IntegrationTechnicalException();
		}
		try {
			response = runReport(wsdlRequestASBO.getReportRequest());
			wsdlResponseASBO.setReportContentType(response.getReportContentType());
			wsdlResponseASBO.setReportFileID(response.getReportFileID());
		} catch (Exception e) {
			wsdlRequestASBO=null;
			LOGGER.error("error in service" +e);
		}
		return wsdlResponseASBO;
	}
}
