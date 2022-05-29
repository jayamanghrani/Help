/**
 * 
 */
package com.tcs.webreports.integration.controller;

import java.text.ParseException;
import org.apache.log4j.Logger;
import com.tcs.webreports.asbo.report.request.ReportRequestASBO;
import com.tcs.webreports.asbo.report.response.ReportResponseASBO;
import com.tcs.webreports.component.integrator.ReportsChannelIntegrator;
import com.tcs.webreports.exception.cmo.ErrorVO;
import com.tcs.webreports.exception.cmo.IntegrationTechnicalException;
import com.tcs.webreports.exception.cmo.IntegrationTransformationException;
import com.tcs.webreports.report.transformers.ReportsTransformer;
import com.tcs.webreports.reports.asbo.response.ReportsWSDLResponseASBO;
import com.tcs.webreports.util.ExceptionUtil;
import com.tcs.webreports.util.ValidationUtil;
import com.tcs.webreports.vo.cmo.Header;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

/**
 * @author 738796
 *
 */
public class ReportIntegrationController {
	 private static final Logger LOGGER = Logger.getLogger(ReportIntegrationController.class);
	 private ReportsChannelIntegrator reportsChannelIntegrator;
	 
	 public ReportIntegrationController() {
		this.reportsChannelIntegrator =new ReportsChannelIntegrator();
	}
	
	 /**
	  * Method to call validation report request parameters for reports.
	 * @param reportRequestASBO
	 * @return
	 * @throws ParseException
	 * @throws IntegrationTransformationException
	 */
	public WebReportsResponseObject processReportRequest(ReportRequestASBO  reportRequestASBO)
	 {
		 Header header = reportRequestASBO.getHeader();
		 ReportsTransformer reportsTransformer = new ReportsTransformer();
		 WebReportsRequestObject webReportsRequestObject = null;
		 ReportResponseASBO reportResponseASBO=null;
		 ReportsWSDLResponseASBO reportsWSDLResponseASBO=null;
		 String reportFormat = reportRequestASBO.getReportFormat().toLowerCase();
		   
		   if(!(reportFormat.equalsIgnoreCase("xlsx")||reportFormat.equalsIgnoreCase("html")||reportFormat.equalsIgnoreCase("pdf")))
		   {
			   LOGGER.info("Report format");
			    ErrorVO errorVO = new ErrorVO();
				errorVO.setErrorMessage("Report format can only be in pdf,xlsx,html format");
				errorVO.setReason("Report format can only be in pdf,xlsx,html format");
				return errorVO;
		   	}
		   
		   String errorMessage=ValidationUtil.reportDateValidation(reportRequestASBO.getFromDate(), reportRequestASBO.getToDate(), 30);
			if(!errorMessage.equalsIgnoreCase("pass"))
			{
				LOGGER.info("Date should not be null");
				ErrorVO errorVO = new ErrorVO();
				errorVO.setErrorMessage(errorMessage);
				LOGGER.info("Date should not be null");
				return errorVO;
			}
		String	errorMessage1 =ValidationUtil.partyCodeValidation(reportRequestASBO.getPartyCode());
		if(!errorMessage1.equalsIgnoreCase("pass"))
						{
							ErrorVO errorVO = new ErrorVO();
							errorVO.setErrorMessage(errorMessage1);
							LOGGER.info("Invalid PartyCode");
							return errorVO;
						}
		
		String errorMessage2=ValidationUtil.reportNameValidation(reportRequestASBO.getReportName());
				if(!errorMessage2.equalsIgnoreCase("pass"))
				{
					ErrorVO errorVO = new ErrorVO();
					errorVO.setErrorMessage(errorMessage2);
					LOGGER.info("Date should not be null");
					return errorVO;
				}
		if (errorMessage.equalsIgnoreCase("pass") && errorMessage1.equalsIgnoreCase("pass")&& errorMessage2.equalsIgnoreCase("pass")) 
		{
			try {
				webReportsRequestObject = reportsTransformer.transformRequest(reportRequestASBO);
			} catch (IntegrationTransformationException e) {
				 return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
				
			} catch (ParseException e) {
				 return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
			}
		} 
		 try
		    {
			 reportsWSDLResponseASBO = (ReportsWSDLResponseASBO)this.reportsChannelIntegrator.execute(webReportsRequestObject);
		    }
		    catch (IntegrationTechnicalException e) {
		      return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
		    }
		 if(reportsWSDLResponseASBO!=null)
		 {
		    reportsWSDLResponseASBO.setHeader(reportRequestASBO.getHeader());
		    try
		    {
		    	reportResponseASBO = (ReportResponseASBO) reportsTransformer.transformResponse(reportsWSDLResponseASBO);
		    }
		    catch (IntegrationTransformationException e) {
		      return ExceptionUtil.getTransformationErrorVO(header, "response", e);
		    }
		 }
		 else
		 {
			 ErrorVO errorVO = new ErrorVO();
			 LOGGER.error("Report service failed");
				errorVO.setErrorMessage("Service failed");
				errorVO.setReason("Report service failed");
				return errorVO;
		 }
		 return reportResponseASBO;		  
	 }
}