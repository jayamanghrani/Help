/**
 * 
 */
package com.tcs.employeeportal.controller;

import org.apache.log4j.Logger;

import com.tcs.employeeportal.asbo.emp.request.TickerRequestASBO;
import com.tcs.employeeportal.asbo.emp.response.TickerResponseASBO;
import com.tcs.employeeportal.component.integrator.ChannelIntegrator;
import com.tcs.employeeportal.component.integrator.DBChannelIntegrator;
import com.tcs.employeeportal.db.asbo.request.TickerDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.TickerDBResponseASBO;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.transformation.ticker.TickerTransformer;
import com.tcs.employeeportal.util.ExceptionUtil;
import com.tcs.employeeportal.util.UtilConstants;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;
import com.tcs.employeeportal.vo.cmo.Header;

/**
 * @author 585226
 *
 */
public class TickerIntegrationController {

	private DBChannelIntegrator dbChannelIntegrator;
	private ChannelIntegrator channelIntegrator;
	public TickerIntegrationController(){
		//	channelIntegrator = new ChannelIntegrator();
		dbChannelIntegrator = new DBChannelIntegrator();
	}
	private static final Logger LOGGER = Logger.getLogger(TickerIntegrationController.class);
	public EmployeePortalResponseObject getTickerValues(
			TickerRequestASBO requestASBO) {
		// TODO Auto-generated method stub
		LOGGER.info("EmployeePortalResponseObject>>>>>>>>>>>getTickerValues<<<<<<<<<<<<<");
		EmployeePortalRequestObject tikerrequestObject = null;
		TickerDBRequestASBO tikerDBreqasbo = new TickerDBRequestASBO();
		TickerTransformer tikerTransformer = new TickerTransformer();
		TickerDBResponseASBO tickerdbresponseasbo = null;
		Header header=requestASBO.getHeader();
		LOGGER.info("###Inside getTickerValues EmployeePortalResponseObject etHeader()###"+requestASBO.getHeader());
		TickerResponseASBO tikerRespasbo = new TickerResponseASBO();


		try {
			tikerDBreqasbo = (TickerDBRequestASBO) tikerTransformer.transformTickerrequest(requestASBO);
		} catch (Exception e) {
			LOGGER.error(e.getStackTrace());

			return ExceptionUtil.getTransformationErrorVOException(header, "response", e);
		}

		try {
			// DATA BASE call to execute the ticker 
			tickerdbresponseasbo = (TickerDBResponseASBO)dbChannelIntegrator.cacheManagerInvoker(tikerDBreqasbo);
			LOGGER.info("Are we comming here??? inside the try for getTickerValues in getTickerValues ");

		}catch (IntegrationTechnicalException integrationTechnicalException) {
			LOGGER.error("ERROR in executing the TICKER "+integrationTechnicalException.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, integrationTechnicalException);
		} catch (Exception exception) {
			exception.printStackTrace();
			LOGGER.error("ERROR in executing the TICKER "+exception.getStackTrace());
			exception.printStackTrace();
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, exception);
		}

		if (null != tickerdbresponseasbo) {
			try{
				LOGGER.info("Are we comming here??? inside the try for getTickerValues in IntegrationController ");
				tikerRespasbo.setHeader(tickerdbresponseasbo.getHeader());
				tikerRespasbo = (TickerResponseASBO)tikerTransformer.transformresponse(tickerdbresponseasbo);
			}catch(Exception exception){
				LOGGER.error("ERROR in executing the TICKER "+exception.getStackTrace());
				exception.printStackTrace();
				return ExceptionUtil.getTransformationErrorVOException(header, "response", exception);
			}
		} else {

			if(LOGGER.isInfoEnabled()) {
				LOGGER.info("Some issue in the getTickerValues! ");
				LOGGER.info("Some issue in the getTickerValues! ");
			}


		}
		LOGGER.info("Code is working  TickerIntegrationController! ");
		LOGGER.info("Code is working  TickerIntegrationController!!! ");
		return tikerRespasbo;



	}

}
