/**
 * 
 */
package com.tcs.employeeportal.component.integrator;

import org.apache.log4j.Logger;

import com.tcs.employeeportal.component.invoker.CacheManagerInvoker;
import com.tcs.employeeportal.component.invoker.EmployeeDBInvoker;
import com.tcs.employeeportal.component.invoker.MailComponentInvoker;
import com.tcs.employeeportal.component.invoker.MessageComponentInvoker;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;
import com.tcs.employeeportal.access.invoker.OAMOIDInvoker;

/**
 * @author 738566
 *
 */
public abstract class ChannelIntegrator {

	private static final Logger LOGGER = Logger.getLogger(ChannelIntegrator.class);

	/**
	 * Rest invoker.
	 * 
	 * @param requestVO
	 *            the request vo
	 * @return the ba ncs integration response vo
	 * @throws IntegrationTechnicalException
	 *             the integration technichal exception
	 */

/*	protected EmployeePortalResponseObject restInvoker(EmployeePortalRequestObject requestVO)
			throws IntegrationTechnicalException {

		// call bancs rest service

		return new EmployeeRestInvoker().invokeDB(requestVO);

	}*/

	/**
	 * Cache manager invoker.
	 * 
	 * @param requestVO
	 *            the request vo
	 * @return the ba ncs integration response vo
	 * @throws IntegrationTechnicalException
	 *             the integration technichal exception
	 */
	public EmployeePortalResponseObject cacheManagerInvoker(EmployeePortalRequestObject requestVO)
			throws IntegrationTechnicalException {
		return new CacheManagerInvoker().invokeCache(requestVO);

	}

	/**
	 * DB invoker.
	 * 
	 * @param requestVO
	 *            the request vo
	 * @return the ba ncs integration response vo
	 * @throws IntegrationTechnicalException
	 *             the integration technichal exception
	 */
	protected EmployeePortalResponseObject dBInvoker(EmployeePortalRequestObject requestVO)
			throws IntegrationTechnicalException {
		// call bancs DB service
		System.out.println("Inside dBInvoker");
		return new EmployeeDBInvoker().invokeDB(requestVO);

	}


	/**
	 * @param requestVO
	 * @return
	 * @throws IntegrationTechnicalException
	 */
	protected EmployeePortalResponseObject OIDOAMInvoker(EmployeePortalRequestObject requestVO)throws IntegrationTechnicalException{
		return new OAMOIDInvoker().invokeOIDOAM(requestVO);
	}
	
	/**
	 * Abstract method to executes Audit Log asynchronously.
	 * 
	 * @param request
	 *            the request
	 * @return EmployeePortalResponseObject
	 * @throws IntegrationTechnicalException
	 *             the integration technichal exception
	 */
	public abstract EmployeePortalResponseObject execute(EmployeePortalRequestObject request)
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
	protected EmployeePortalResponseObject EmailInvoker(EmployeePortalRequestObject requestVO)throws IntegrationTechnicalException{
		return new MailComponentInvoker().invokeMailService(requestVO);
	}
	
	protected EmployeePortalResponseObject MessageInvoker(EmployeePortalRequestObject requestVO)throws IntegrationTechnicalException{
		return new MessageComponentInvoker().invokeMessageService(requestVO);
	}


}
