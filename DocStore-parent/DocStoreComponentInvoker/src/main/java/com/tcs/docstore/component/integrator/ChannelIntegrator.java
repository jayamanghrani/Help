/**
 * 
 */
package com.tcs.docstore.component.integrator;

import org.apache.log4j.Logger;

import com.tcs.docstore.component.invoker.CacheManagerInvoker;
import com.tcs.docstore.component.invoker.DocumentEmailCompInvoker;
import com.tcs.docstore.component.invoker.DocStoreDBInvoker;
import com.tcs.docstore.component.invoker.MailComponentInvoker;
import com.tcs.docstore.component.invoker.MessageComponentInvoker;
import com.tcs.docstore.db.asbo.request.DocUploadDBRequestASBO;
import com.tcs.docstore.exception.cmo.IntegrationTechnicalException;
import com.tcs.docstore.vo.cmo.DocStoreRequestObject;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;
import com.tcs.docstore.access.invoker.OAMOIDInvoker;

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


	/**
	 * Cache manager invoker.
	 * 
	 * @param requestVO
	 *            the request vo
	 * @return the ba ncs integration response vo
	 * @throws IntegrationTechnicalException
	 *             the integration technichal exception
	 */
	public DocStoreResponseObject cacheManagerInvoker(DocStoreRequestObject requestVO)
			throws IntegrationTechnicalException {
		return new CacheManagerInvoker().invokeCache(requestVO);

	}

	/**
	 * DB invoker.
	 * 
	 * @param requestVO
	 *            the request vo
	 * @return the employee portal response vo
	 * @throws IntegrationTechnicalException
	 *             the integration technichal exception
	 */
	protected DocStoreResponseObject dBInvoker(DocStoreRequestObject requestVO)
			throws IntegrationTechnicalException {
		// call bancs DB service
		LOGGER.info("Inside dBInvoker");
		return new DocStoreDBInvoker().invokeDB(requestVO);

	}


	/**
	 * @param requestVO
	 * @return
	 * @throws IntegrationTechnicalException
	 */
	protected DocStoreResponseObject OIDOAMInvoker(DocStoreRequestObject requestVO)throws IntegrationTechnicalException{
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
	public abstract DocStoreResponseObject execute(DocStoreRequestObject request)
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
	protected DocStoreResponseObject EmailInvoker(DocStoreRequestObject requestVO)throws IntegrationTechnicalException{
		return new MailComponentInvoker().invokeMailService(requestVO);
	}
	
	protected DocStoreResponseObject MessageInvoker(DocStoreRequestObject requestVO)throws IntegrationTechnicalException{
		return new MessageComponentInvoker().invokeMessageService(requestVO);
	}
	
	
	protected DocStoreResponseObject DocEmailInvoker(String mailDetail,DocUploadDBRequestASBO dudbreasbo)throws IntegrationTechnicalException{
		LOGGER.info("inside this DocEmailInvoker");
		return new DocumentEmailCompInvoker().invokeMailService(mailDetail,dudbreasbo);
	}


}
