package com.tcs.employeeportal.component.invoker;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tcs.employeeportal.alfresco.asbo.request.GetAlfrecoContentRequestASBO;
import com.tcs.employeeportal.db.asbo.request.TickerDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.TickerDBResponseASBO;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.service.alfresco.AlfrescoCacheContent;
import com.tcs.employeeportal.service.ticker.TickerCacheService;
import com.tcs.employeeportal.util.UtilConstants;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

public class CacheManagerInvoker {
	
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = "CacheManagerInvoker";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger("empPortalLogger");

	/** The gson. */
	private static Gson gson;

	static {
		gson = getGson();
	}

	/**
	 * Instantiates a newcache manager invoker.
	 */
	public CacheManagerInvoker() {
	}

	/**
	 * Call invokeCache services through CacheManager.
	 * 
	 * @param requestVO
	 *            the request vo
	 * @return EmployeePortalResponseObject
	 * @throws IntegrationTechnicalException
	 *             the integration technichal exception
	 */
	public EmployeePortalResponseObject invokeCache(
			EmployeePortalRequestObject requestVO)
			throws IntegrationTechnicalException {
		LOGGER.info(requestVO.getHeader().toString());
		
		  if (requestVO.getHeader().getEventID()
				.equalsIgnoreCase(UtilConstants.ALFRESCO_GET_CONTENT)) {
			return getContent(requestVO);
		}  

		  if (requestVO.getHeader().getEventID()
				.equalsIgnoreCase(UtilConstants.GET_TICKER_VALUES)) {
			return getCacheTickerValues(requestVO);
		}
		  else {

			return null;
		}
	}

	/**
	 * Gets the content.
	 * 
	 * @param requestVO
	 *            the request vo
	 * @return the content
	 * @throws IntegrationTechnicalException
	 *             the integration technical exception
	 */
	public EmployeePortalResponseObject getContent(
			EmployeePortalRequestObject requestVO)
			throws IntegrationTechnicalException {
		GetAlfrecoContentRequestASBO getContentAlfrescoRequestASBO;
		if (requestVO instanceof GetAlfrecoContentRequestASBO) {
			getContentAlfrescoRequestASBO = (GetAlfrecoContentRequestASBO) requestVO;
		} else {
			throw new IntegrationTechnicalException();
		}

		return new AlfrescoCacheContent()
				.getAlfrescoContent(getContentAlfrescoRequestASBO);

	}

	/**
	 * Gets the gson.
	 * 
	 * @return the gson
	 */
	public static Gson getGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.disableHtmlEscaping();
		gson = gsonBuilder.setDateFormat("dd-MMM-yy").create();
		return gson;
	}

	/**
	 * Gets the class name.
	 * 
	 * @return the class name
	 */
	public static String getClassName() {
		return CLASS_NAME;
	}
	
	private EmployeePortalResponseObject getCacheTickerValues(
			EmployeePortalRequestObject requestVO) {
		
		LOGGER.info("inside the getCacheTickerValues cachemanagerInvoker");
		 ConcurrentMap<String, List<String>> cachedMapTickerValues = new ConcurrentHashMap<String, List<String>>();
		TickerDBRequestASBO tdbrasbo = new TickerDBRequestASBO();
		TickerDBResponseASBO tdbrespasbo = new TickerDBResponseASBO();
		if(requestVO instanceof TickerDBRequestASBO){
			tdbrasbo = (TickerDBRequestASBO) requestVO;
			TickerCacheService tickercacheservice = new TickerCacheService();
			 cachedMapTickerValues=	tickercacheservice.cacheTickerValues(tdbrasbo);
			List<String> cachedVAluesTicker =cachedMapTickerValues.get(tdbrasbo.getTickerinput());
			LOGGER.info("inside the getCacheTickerValues setDatevalue cachedVAluesTicker.get(2)  "+ cachedVAluesTicker.get(1));
			LOGGER.info("inside the getCacheTickerValues setDailyPremium cachedVAluesTicker.get(0)  "+ cachedVAluesTicker.get(0));
			LOGGER.info("inside the getCacheTickerValues setMonthlyPremium cachedVAluesTicker.get(1)  "+ cachedVAluesTicker.get(2));
			LOGGER.info("inside the getCacheTickerValues setUptoPremium cachedVAluesTicker.get(3)  "+ cachedVAluesTicker.get(3));
			
			tdbrespasbo.setDatevalue(cachedVAluesTicker.get(1));
			tdbrespasbo.setDailyPremium(cachedVAluesTicker.get(0));
			tdbrespasbo.setMonthlyPremium(cachedVAluesTicker.get(2));
			tdbrespasbo.setUptoPremium(cachedVAluesTicker.get(3));
}
		// TODO Auto-generated method stub
		return tdbrespasbo;
	}
	
}
