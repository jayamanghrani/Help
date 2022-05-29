package com.tcs.webreports.component.invoker;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tcs.webreports.cache.asbo.request.GetChannelReportsCacheRequestASBO;
import com.tcs.webreports.cache.service.ReportsChannelCacheService;
//import com.tcs.webreports.cache.asbo.request.GetChannelReportsCacheRequestASBO;
//import com.tcs.webreports.cache.service.ReportsChannelCacheService;
import com.tcs.webreports.exception.cmo.IntegrationTechnicalException;
import com.tcs.webreports.util.UtilConstants;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

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
	 * @return WebReportsResponseObject
	 * @throws IntegrationTechnicalException
	 *             the integration technichal exception
	 */
	public WebReportsResponseObject invokeCache(
			WebReportsRequestObject requestVO)
			throws IntegrationTechnicalException {
		LOGGER.info(requestVO.getHeader().toString());
		 if (requestVO.getHeader().getEventID()
					.equalsIgnoreCase(UtilConstants.GET_CHANNEL_REPORTS)) {
				return getChannelReports(requestVO);
			}  
   else {
			return null;
		}
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
	
	public WebReportsResponseObject getChannelReports(
			WebReportsRequestObject requestVO)
			throws IntegrationTechnicalException {
		GetChannelReportsCacheRequestASBO getChannelReportsCacheRequestASBO;
		if (requestVO instanceof GetChannelReportsCacheRequestASBO) {
			getChannelReportsCacheRequestASBO = (GetChannelReportsCacheRequestASBO) requestVO;
		} else {
			throw new IntegrationTechnicalException();
		}
		return new ReportsChannelCacheService().getChannelReportsList(getChannelReportsCacheRequestASBO);
	}
	
}