/**
 * 
 */
package com.tcs.webreports.integration.controller;

import java.text.ParseException;

import org.apache.log4j.Logger;

import com.oracle.xmlns.oxp.service.v2.OperationFailedException;
import com.tcs.webreports.asbo.report.request.ChannelReportsRequestASBO;
import com.tcs.webreports.asbo.report.request.ReportGetParameterRequestASBO;
import com.tcs.webreports.asbo.report.request.ReportRequestASBO;
import com.tcs.webreports.asbo.report.response.ReportGetParameterResponseASBO;
import com.tcs.webreports.asbo.report.response.ReportResponseASBO;
import com.tcs.webreports.cache.asbo.request.ChannelReportsCacheRequestASBO;
import com.tcs.webreports.cache.asbo.request.GetChannelReportsCacheRequestASBO;
import com.tcs.webreports.cache.asbo.response.ChannelReportsCacheResponseASBO;
import com.tcs.webreports.cache.asbo.response.GetChannelReportsCacheResponseASBO;
import com.tcs.webreports.cache.service.ReportsChannelCacheService;
import com.tcs.webreports.component.integrator.CacheManagerIntegrator;
import com.tcs.webreports.component.integrator.GetParameterReportsChannelIntegrator;
import com.tcs.webreports.component.integrator.OAMOIDChannelIntegrator;
import com.tcs.webreports.component.integrator.ReportsChannelIntegrator;
import com.tcs.webreports.exception.cmo.ErrorVO;
import com.tcs.webreports.exception.cmo.IntegrationTechnicalException;
import com.tcs.webreports.exception.cmo.IntegrationTransformationException;
import com.tcs.webreports.oamoid.asbo.response.GetChannelOAMOIDResponseASBO;
import com.tcs.webreports.report.transformers.ChannelReportsMappingTransformer;
import com.tcs.webreports.report.transformers.GetChannelReportsTransformer;
import com.tcs.webreports.report.transformers.GetParameterReportsTransformers;
import com.tcs.webreports.reports.asbo.response.ReportsWSDLGetParameterResponseASBO;
import com.tcs.webreports.reports.asbo.response.ReportsWSDLResponseASBO;
import com.tcs.webreports.util.ExceptionUtil;
import com.tcs.webreports.util.UtilConstants;
import com.tcs.webreports.util.ValidationUtil;
import com.tcs.webreports.vo.cmo.Header;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

/**
 * @author 738796
 *
 */
public class GetParameterReportIntegrationController 
{
	 private static final Logger LOGGER = Logger.getLogger(GetParameterReportIntegrationController.class);
	 private  GetParameterReportsChannelIntegrator getParameterReportsChannelIntegrator;
	 private OAMOIDChannelIntegrator oamoidChannelIntegrator;
	 private CacheManagerIntegrator cacheManagerIntegrator;
	 private ReportsChannelIntegrator reportsChannelIntegrator;
	 public GetParameterReportIntegrationController() 
	 {
		 this.getParameterReportsChannelIntegrator =new GetParameterReportsChannelIntegrator();
		 this.oamoidChannelIntegrator = new OAMOIDChannelIntegrator();
		 this.cacheManagerIntegrator=new CacheManagerIntegrator();
		 this.reportsChannelIntegrator =new ReportsChannelIntegrator();
	 }

	 /**
	  *Method to retrieve parameters for report request. 
	  * @param reportGetParameterRequestASBO
	  * @return
	  */
	 public WebReportsResponseObject processGetParameterRequest(ReportGetParameterRequestASBO reportGetParameterRequestASBO)
		 {
			 Header header = reportGetParameterRequestASBO.getHeader();
			 GetParameterReportsTransformers getParameterReportsTransformers = new GetParameterReportsTransformers();
			 WebReportsRequestObject webReportsRequestObject = null;
			 ReportsWSDLGetParameterResponseASBO reportsWSDLGetParameterResponseASBO=null;
			 ReportGetParameterResponseASBO reportGetParameterResponseASBO=null;
			 String errorMessage=ValidationUtil.reportNameValidation(reportGetParameterRequestASBO.getReportName());
				if(!errorMessage.equalsIgnoreCase("pass"))
				{
					LOGGER.info("Date should not be null");
					ErrorVO errorVO = new ErrorVO();
					errorVO.setErrorMessage(errorMessage);
					LOGGER.info("Date should not be null");
					return errorVO;
				}
		if(errorMessage.equalsIgnoreCase("pass"))
				{
				 try {
					webReportsRequestObject = getParameterReportsTransformers.transformRequest(reportGetParameterRequestASBO);
				} catch (IntegrationTransformationException e) {
					return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
				}
				}
			 try
		    {
			
			 reportsWSDLGetParameterResponseASBO  = (ReportsWSDLGetParameterResponseASBO)this.getParameterReportsChannelIntegrator.execute(webReportsRequestObject);
		    }
		    catch (IntegrationTechnicalException e) {
		      return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
		    }
		 if(reportsWSDLGetParameterResponseASBO!=null)
		 {
			 reportsWSDLGetParameterResponseASBO.setHeader(reportGetParameterRequestASBO.getHeader());
		    try
		    {
		    	reportGetParameterResponseASBO = (ReportGetParameterResponseASBO) getParameterReportsTransformers.transformResponse(reportsWSDLGetParameterResponseASBO);
		    }
		    catch (IntegrationTransformationException e) 
		    {
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
		     return reportGetParameterResponseASBO;
		 }

	 /**
	  * Method to get reports for channel
	  * @param channelReportsRequestASBO
	  * @return
	  */
	 public WebReportsResponseObject fetchChannelReports(
				ChannelReportsRequestASBO channelReportsRequestASBO) {
		    GetChannelReportsTransformer transformer = new GetChannelReportsTransformer();
		    WebReportsRequestObject webReportsRequestObject = null;
		    Header header = channelReportsRequestASBO.getHeader();
		    ChannelReportsCacheRequestASBO channelReportsCacheRequestASBO=new ChannelReportsCacheRequestASBO();
		    ChannelReportsCacheResponseASBO cacheResponseASBO=null;
		    ReportRequestASBO  reportRequestASBO=new ReportRequestASBO();
		    ReportResponseASBO reportResponseASBO=null;
		    GetChannelReportsCacheResponseASBO channelReportsCacheResponseASBO=null;
		    try {
			      webReportsRequestObject = transformer.transformRequest(channelReportsRequestASBO);
			    }
			    catch (IntegrationTransformationException e) {
			      return ExceptionUtil.getTransformationErrorVO(header, "request", e);
			    }

			    GetChannelOAMOIDResponseASBO getChannelOAMOIDResponseASBO = null;
			    try
			    {
			    	getChannelOAMOIDResponseASBO = (GetChannelOAMOIDResponseASBO)this.oamoidChannelIntegrator.execute(webReportsRequestObject);
			    }
			    catch (IntegrationTechnicalException e) {
			      return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
			    }

			    LOGGER.info("getChannelOAMOIDResponseASBO--"+getChannelOAMOIDResponseASBO.getGroup());
			   
			    channelReportsCacheRequestASBO.setChannelName(getChannelOAMOIDResponseASBO.getGroup());
			    channelReportsCacheRequestASBO.setHeader(channelReportsRequestASBO.getHeader());
				try {
					webReportsRequestObject = transformer
							.transformCacheRequest(channelReportsCacheRequestASBO);
				} catch (IntegrationTransformationException e) {
					 return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
				}
				
				try
			    {
					channelReportsCacheResponseASBO = (GetChannelReportsCacheResponseASBO)this.cacheManagerIntegrator.execute(webReportsRequestObject);
			    }
			    catch (IntegrationTechnicalException e) {
			      return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
			    }
			    
				if(channelReportsCacheResponseASBO.getReportNames()==null)
				{
					ErrorVO err=new ErrorVO();
					LOGGER.info("Report mapping Not found in cache for channel : "+channelReportsCacheRequestASBO.getChannelName().toLowerCase());
					reportRequestASBO.setHeader(channelReportsRequestASBO.getHeader());
					try {
						LOGGER.info("Calling service to update reports channels mapping");
						reportResponseASBO=(ReportResponseASBO) processChannelMappingRequest(reportRequestASBO);
					} catch (OperationFailedException e) {
						
						return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
					}
					if(reportResponseASBO.getReportContentType()==null)
					{
						LOGGER.error("Reports Service failure");
						err.setErrorCode(UtilConstants.ERROR_CODE_NORESPONSE_TechnichalException);
						err.setHeader(header);
						err.setErrorMessage("Reports Service fails");
						err.setReason("Reports Service fails");
						return err;
					}else
					{
						GetChannelReportsCacheRequestASBO requestASBO=new GetChannelReportsCacheRequestASBO();
						ReportsChannelCacheService cacheService=new ReportsChannelCacheService();
						cacheService.cacheChannelAndReportsList();
						requestASBO.setHeader(channelReportsRequestASBO.getHeader());
						requestASBO.setChannelName(channelReportsCacheRequestASBO.getChannelName().toLowerCase());
						channelReportsCacheResponseASBO=cacheService.getChannelReportsList(requestASBO);
						if(channelReportsCacheResponseASBO.getReportNames()==null)
						{
						LOGGER.error("Reports Not found in Service");
						err.setHeader(header);
						err.setErrorMessage("Reports Not found in Service");
						err.setReason("Reports Not found in Service");
						err.setErrorCode(UtilConstants.ERROR_CODE_NODATA_TechnicalException);
						return err;
						}
					}
							
				}
			    
				channelReportsCacheResponseASBO.setHeader(channelReportsRequestASBO.getHeader());
			    try {
					cacheResponseASBO = (ChannelReportsCacheResponseASBO)transformer.transformResponse(channelReportsCacheResponseASBO);
					cacheResponseASBO.setChannelName(channelReportsCacheRequestASBO.getChannelName());
				} catch (IntegrationTransformationException e) {
					return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
				}
			return cacheResponseASBO;
		}
	 
	 /**
	  * Method to call channel reports service.
	  * @param reportRequestASBO
	  * @return
	  * @throws OperationFailedException
	  */
	 public WebReportsResponseObject processChannelMappingRequest(ReportRequestASBO  reportRequestASBO) throws OperationFailedException
	 {
		 Header header = reportRequestASBO.getHeader();
		 header.setEventID(UtilConstants.GET_REPORT_CHANNEL_MAPPING);
		 reportRequestASBO.setHeader(header);
		 ChannelReportsMappingTransformer reportsTransformer = new ChannelReportsMappingTransformer();
		 WebReportsRequestObject webReportsRequestObject = null;
		 ReportResponseASBO reportResponseASBO=null;
		 ReportsWSDLResponseASBO reportsWSDLResponseASBO=null;
		 try {
		      webReportsRequestObject = reportsTransformer.transformRequest(reportRequestASBO);
		    }
		    catch (IntegrationTransformationException e) {
		      return ExceptionUtil.getTransformationErrorVO(header, "request", e);
		    } catch (ParseException e) {
				e.printStackTrace();
			}
		 try
		    {
			 reportsWSDLResponseASBO = (ReportsWSDLResponseASBO)this.reportsChannelIntegrator.execute(webReportsRequestObject);
		    }
		    catch (IntegrationTechnicalException e) {
		      return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
		    }
		 
		    reportsWSDLResponseASBO.setHeader(reportRequestASBO.getHeader());
		    try
		    {
		    	reportResponseASBO = (ReportResponseASBO) reportsTransformer.transformResponse(reportsWSDLResponseASBO);
		    }
		    catch (IntegrationTransformationException e) {
		      return ExceptionUtil.getTransformationErrorVO(header, "response", e);
		    }
		    
		 return reportResponseASBO;		 
	 }
		
	}

	 
	 
	 
	 
	 

