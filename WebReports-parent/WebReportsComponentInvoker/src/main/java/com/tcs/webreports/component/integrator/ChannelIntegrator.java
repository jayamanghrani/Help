/**
 * 
 */
package com.tcs.webreports.component.integrator;

import org.apache.log4j.Logger;

import com.tcs.webreports.component.invoker.CacheManagerInvoker;
import com.tcs.webreports.component.invoker.OAMOIDInvoker;
import com.tcs.webreports.component.invoker.ReportsSOAPInvoker;
/*import com.tcs.webreports.component.invoker.CacheManagerInvoker;
import com.tcs.webreports.component.invoker.WebReportsDBInvoker;
import com.tcs.webreports.component.invoker.MailComponentInvoker;
import com.tcs.webreports.component.invoker.MessageComponentInvoker;*/
import com.tcs.webreports.exception.cmo.IntegrationTechnicalException;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

/**
 * @author 738566
 *
 */
public abstract class ChannelIntegrator {

	private static final Logger LOGGER = Logger.getLogger(ChannelIntegrator.class);

	/**
	 * Cache manager invoker.
	 * 
	 * @param requestVO
	 *            the request vo
	 * @return the ba ncs integration response vo
	 * @throws IntegrationTechnicalException
	 *             the integration technichal exception
	 */
	public WebReportsResponseObject cacheManagerInvoker(WebReportsRequestObject requestVO)
			throws IntegrationTechnicalException {
		return new CacheManagerInvoker().invokeCache(requestVO);

	}

	/**
	 * @param requestVO
	 * @return
	 * @throws IntegrationTechnicalException
	 */
	protected WebReportsResponseObject OIDOAMInvoker(WebReportsRequestObject requestVO)throws IntegrationTechnicalException{
		return new OAMOIDInvoker().invokeOIDOAM(requestVO);
	}
	
	/**
	 * Abstract method to executes Audit Log asynchronously.
	 * 
	 * @param request
	 *            the request
	 * @return WebReportsResponseObject
	 * @throws IntegrationTechnicalException
	 *             the integration technichal exception
	 */
	public abstract WebReportsResponseObject execute(WebReportsRequestObject request)
			throws IntegrationTechnicalException;

	/**
	 * Gets the logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return LOGGER;
	}
	
	/**
	 * email invoker.
	 *
	 * @param requestVO the request vo
	 * @return the employeeportal integration response vo
	 * @throws IntegrationTechnicalException the integration technical exception
	 */
/*	protected WebReportsResponseObject EmailInvoker(WebReportsRequestObject requestVO)throws IntegrationTechnicalException{
		return new MailComponentInvoker().invokeMailService(requestVO);
	}
	
	protected WebReportsResponseObject MessageInvoker(WebReportsRequestObject requestVO)throws IntegrationTechnicalException{
		return new MessageComponentInvoker().invokeMessageService(requestVO);
	}*/
	
	/**
	 * @param requestVO
	 * @return
	 * @throws IntegrationTechnicalException
	 */
	protected WebReportsResponseObject ReportRequestInvoker(WebReportsRequestObject requestVO)throws IntegrationTechnicalException{
		return new ReportsSOAPInvoker().invokeReportRequest(requestVO);
	}

}
