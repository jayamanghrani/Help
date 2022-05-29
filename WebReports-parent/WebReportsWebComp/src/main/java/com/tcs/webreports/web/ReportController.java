package com.tcs.webreports.web;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tcs.webreports.asbo.report.request.ChannelReportsRequestASBO;
import com.tcs.webreports.asbo.report.request.ReportGetParameterRequestASBO;
import com.tcs.webreports.asbo.report.request.ReportRequestASBO;
import com.tcs.webreports.exception.cmo.ErrorVO;
import com.tcs.webreports.exception.cmo.IntegrationTechnicalException;
import com.tcs.webreports.integration.controller.GetParameterReportIntegrationController;
import com.tcs.webreports.integration.controller.ReportIntegrationController;
import com.tcs.webreports.security.util.HttpHeaderProperty;
import com.tcs.webreports.security.util.HttpHeaderUtil;
import com.tcs.webreports.util.ExceptionUtil;
import com.tcs.webreports.util.UtilConstants;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

@Component
@RestController
@RequestMapping("/reports")
public class ReportController {
	private static final Logger LOGGER = Logger
			.getLogger(ReportController.class);

	/**
	 * Method to get reports
	 * @param httpServletRequest
	 * @param reportRequestASBO
	 * @return
	 */
	@RequestMapping(value = { "/getReport" }, method = { RequestMethod.POST })
	public ResponseEntity<WebReportsResponseObject> requestReport(
			HttpServletRequest httpServletRequest,
			@RequestBody ReportRequestASBO reportRequestASBO){

		HttpHeaderProperty httpHeaderProperty = (HttpHeaderProperty) httpServletRequest
				.getAttribute("HTTP_HEADERS");
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		try {
			reportRequestASBO.setHeader(httpHeaderUtil.generateSpringHeader(
					httpHeaderProperty,UtilConstants.GET_REPORT));
			reportRequestASBO.setPartyCode(httpServletRequest.getHeader("userid"));
			LOGGER.info(reportRequestASBO);
		} catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil
					.getApplicationIdErrorVO(integrationTechnicalException);
			return new ResponseEntity<WebReportsResponseObject>(errorVO, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<WebReportsResponseObject>(
				new ReportIntegrationController()
						.processReportRequest(reportRequestASBO),
				HttpStatus.OK);
	}
	
/**
 * Method to get reports parameter
 * @param httpServletRequest
 * @param reportGetParameterRequestASBO
 * @return
 */
	@RequestMapping(value = { "/getReportParameter" }, method = { RequestMethod.POST })
	public ResponseEntity<WebReportsResponseObject> getReportParameter(HttpServletRequest httpServletRequest,
	@RequestBody ReportGetParameterRequestASBO reportGetParameterRequestASBO)
	{
		HttpHeaderProperty httpHeaderProperty = (HttpHeaderProperty) httpServletRequest
				.getAttribute("HTTP_HEADERS");
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		try {
			
			reportGetParameterRequestASBO.setHeader(httpHeaderUtil.generateSpringHeader(httpHeaderProperty, UtilConstants.GET_REPORT_PARAMETER)); 
			LOGGER.info(reportGetParameterRequestASBO);
	}
		catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil
					.getApplicationIdErrorVO(integrationTechnicalException);
			return new ResponseEntity<WebReportsResponseObject>(errorVO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<WebReportsResponseObject>(
				new GetParameterReportIntegrationController()
						.processGetParameterRequest(reportGetParameterRequestASBO),
				HttpStatus.OK);
	}
	
	/**
	 * Method to get reports for channel
	 * @param channelReportsRequestASBO
	 * @param httpServletRequest
	 * @return
	 */
	 @RequestMapping(value={"/getChannelReports"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  public ResponseEntity<WebReportsResponseObject> getChannelReports(@RequestBody ChannelReportsRequestASBO channelReportsRequestASBO, HttpServletRequest httpServletRequest)
	  {
	    HttpHeaderProperty httpHeaderProperty = (HttpHeaderProperty)httpServletRequest.getAttribute("HTTP_HEADERS");

	    HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
	    try {
	    	channelReportsRequestASBO.setHeader(httpHeaderUtil.generateSpringHeader(httpHeaderProperty, UtilConstants.GET_CHANNEL_REPORTS));
	    }
	    catch (IntegrationTechnicalException integrationTechnicalException) {
	      ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
	      return new ResponseEntity<WebReportsResponseObject>(errorVO, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    if(channelReportsRequestASBO.getSessionToken()==null || channelReportsRequestASBO.getSessionToken().equals("")) {
	    channelReportsRequestASBO.setSessionToken(httpServletRequest.getHeader("X-Auth-Token"));
	    }
	    return new ResponseEntity<WebReportsResponseObject>(new GetParameterReportIntegrationController().fetchChannelReports(channelReportsRequestASBO), HttpStatus.OK);
	  }
}
