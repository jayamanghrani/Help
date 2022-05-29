package com.tcs.docstore.component.invoker;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tcs.docstore.alfresco.asbo.request.GetAlfrecoContentRequestASBO;
import com.tcs.docstore.db.asbo.request.GetDepartmentListAlfrescoDBRequestASBO;
import com.tcs.docstore.db.asbo.response.GetDepartmentListDBResponseASBO;
import com.tcs.docstore.exception.cmo.IntegrationTechnicalException;
import com.tcs.docstore.service.alfresco.AlfrescoCacheContent;
import com.tcs.docstore.service.alfresco.EmpDepartmentListService;
import com.tcs.docstore.util.UtilConstants;
import com.tcs.docstore.vo.cmo.DocStoreRequestObject;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

public class CacheManagerInvoker {
	
	/** The Constant CLASS_NAME. */
	private static final String CLASS_NAME = "CacheManagerInvoker";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger("docStoreLogger");

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
	public DocStoreResponseObject invokeCache(
			DocStoreRequestObject requestVO)
			throws IntegrationTechnicalException {
		LOGGER.info(requestVO.getHeader().toString());
		
		  if (requestVO.getHeader().getEventID()
				.equalsIgnoreCase(UtilConstants.ALFRESCO_GET_CONTENT)) {
			return getContent(requestVO);
		}  

	
		  if (requestVO.getHeader().getEventID()
					.equalsIgnoreCase(UtilConstants.GET_DEPARTMENT_LIST)) {
				return getDepartmentListValues(requestVO);
			}else {

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
	public DocStoreResponseObject getContent(
			DocStoreRequestObject requestVO)
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
	
	
	private DocStoreResponseObject getDepartmentListValues(
			DocStoreRequestObject requestVO) {
		
		GetDepartmentListDBResponseASBO requestdbrespASBO = new GetDepartmentListDBResponseASBO();
		GetDepartmentListAlfrescoDBRequestASBO requestdbrequestASBO = new GetDepartmentListAlfrescoDBRequestASBO();
		ConcurrentMap<String, List<String>> cachedMapDepartmentList = new ConcurrentHashMap<String, List<String>>();
		if(requestVO instanceof GetDepartmentListAlfrescoDBRequestASBO){
			requestdbrequestASBO = (GetDepartmentListAlfrescoDBRequestASBO) requestVO;
			EmpDepartmentListService deptCacheService = new EmpDepartmentListService();
			cachedMapDepartmentList=	deptCacheService.cacheDepartmentList(requestdbrequestASBO);
			List<String> cacheddepartmentList =cachedMapDepartmentList.get(requestdbrequestASBO.getListDepartments());
			requestdbrespASBO.setGetDepartmentNameList(cacheddepartmentList);
}
		// TODO Auto-generated method stub
		return requestdbrespASBO;
	}
}
