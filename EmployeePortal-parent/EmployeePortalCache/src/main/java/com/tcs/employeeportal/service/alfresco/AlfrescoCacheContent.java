package com.tcs.employeeportal.service.alfresco;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.tcs.employeeportal.asbo.alfresco.ContentDataASBO;
import com.tcs.employeeportal.alfresco.asbo.request.GetAlfrecoContentRequestASBO;
import com.tcs.employeeportal.alfresco.asbo.response.GetAlfrescoContentResponseASBO;
import com.tcs.employeeportal.cache.asbo.request.GetContentValueRequestASBO;
import com.tcs.employeeportal.service.alfresco.AlfrescoService;
import com.tcs.employeeportal.cache.Cache;
import com.tcs.employeeportal.cache.key.AlfrescoKey;
import com.tcs.employeeportal.cache.util.CacheConstants;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.vo.cmo.EmployeeResponseVO;

public class AlfrescoCacheContent {
	
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger("empPortalLogger");
	
	/** The alfresco service. */
	private AlfrescoService alfrescoService;

	/**
	 * Instantiates a new alfresco content cache service.
	 */
	public AlfrescoCacheContent() {
		alfrescoService = new AlfrescoService();
	}

	/** The gson. */
	private static Gson gson;

	static {
		gson = getGson();
	}

	/**
	 * Gets the gson.
	 * 
	 * @return the gson
	 */
	public static Gson getGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.disableHtmlEscaping();
		return gsonBuilder.create();
	}

	/**
	 * Sets the gson.
	 *
	 * @param gson            the gson to set
	 */
	public static void setGson(Gson gson) {
		AlfrescoCacheContent.gson = gson;
	}

	/**
	 * Initialize alfresco caching.
	 */
	public void initializeAlfrescoCaching() {
		List<String> typesOfContent = new ArrayList<String>();
		typesOfContent.add(CacheConstants.ADDITIONAL_LINKS_CONTENT);
		typesOfContent.add(CacheConstants.CAROUSEL_CONTENT);
		typesOfContent.add(CacheConstants.INSTRUCTION_TO_USER);
		typesOfContent.add(CacheConstants.EXECUTIVE_MSG);
		typesOfContent.add(CacheConstants.EMPLOYEE_DOCUMENT);
		typesOfContent.add(CacheConstants.EMAIL_CONTENT);
		typesOfContent.add(CacheConstants.LATEST_NEWS_CONTENT);
		typesOfContent.add(CacheConstants.NOTIFICATION_CONTENT);
		typesOfContent.add(CacheConstants.SMS_CONTENT);

		for (String typeOfContent : typesOfContent) {
			cacheAlfrescoContent(typeOfContent);
		}
	}

	/**
	 * Cache alfresco content.
	 *
	 * @param typeOfContent the type of content
	 */
	public void cacheAlfrescoContent(String typeOfContent) {
		LOGGER.info("Caching all " + typeOfContent + ".......");
		AlfrescoKey alfrescoKey = new AlfrescoKey(typeOfContent);
		EmployeeResponseVO employeeResponseVO = null;
		try {
			employeeResponseVO = alfrescoService.getCacheJson("");
		} catch (IntegrationTechnicalException e) {
			LOGGER.info(e.getMessage());
		}

		if(null != employeeResponseVO){
			String json = employeeResponseVO.getJson();
			Cache.put(alfrescoKey, json);
		}
	}

	
	
	/**
	 * Gets the alfresco content.
	 *
	 * @param getContentAlfrescoRequestASBO the get content alfresco request asbo
	 * @return the alfresco content
	 */
	public GetAlfrescoContentResponseASBO getAlfrescoContent(
			GetAlfrecoContentRequestASBO getContentAlfrescoRequestASBO) throws IntegrationTechnicalException {
		LOGGER.info("Type Of Content: "
				+ getContentAlfrescoRequestASBO.getTypeOfContent());
		System.out.println("INSIDE getAlfrescoContent Cache TYPE ----"+ getContentAlfrescoRequestASBO.getTypeOfContent());
		GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO = new GetAlfrescoContentResponseASBO();
		GetContentValueRequestASBO getContentValueRequestASBO = new GetContentValueRequestASBO();
		getContentValueRequestASBO.setTypeOfContent(getContentAlfrescoRequestASBO.getTypeOfContent());
		/*getContentValueRequestASBO.setChannel(getContentAlfrescoRequestASBO.getChannel());*/
		getContentValueRequestASBO.setCacheRegion(CacheConstants.CONTENT);
		
		String requestJson = gson.toJson(getContentValueRequestASBO);
		
		String json = alfrescoService.invokeClient(requestJson);

		Type type = new TypeToken<ArrayList<ContentDataASBO>>() {
		}.getType();

		List<ContentDataASBO> contentDataList = null;
		try {
			contentDataList = gson.fromJson(json, type);
		} catch (JsonSyntaxException e) {
			LOGGER.info(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
		} catch (NullPointerException e) {
			LOGGER.info(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
		}
		List<ContentDataASBO> responseList = new ArrayList<ContentDataASBO>();

		for (ContentDataASBO contentData : contentDataList) {
			List<String> channels = null;
			
			/*if (contentData.getChannel() != null) {
				channels = getParts(contentData.getChannel());
			}
			
			if (getContentAlfrescoRequestASBO.getChannel() != null
					&& contentData.getChannel() != null) {
				if (channels.contains(getContentAlfrescoRequestASBO
						.getChannel())) {

									responseList.add(contentData);
								}*/
			responseList.add(contentData);
		getContentAlfrescoResponseASBO.setHeader(getContentAlfrescoRequestASBO
				.getHeader());
			getContentAlfrescoResponseASBO.setContentDataList(responseList);
	//	getContentAlfrescoResponseASBO.setContentDataList(contentData);
	//}
		}
		return getContentAlfrescoResponseASBO;

	}
	
	
	/**
	 * Gets the parts.
	 *
	 * @param arrayData the array data
	 * @return the parts
	 */
	private List<String> getParts(String arrayData) {
		List<String> parts = new ArrayList<String>();
		String[] initialParts = arrayData.split("\\[");
		String[] secondParts = initialParts[1].split("\\]");
		String[] finalParts = secondParts[0].split(", ");
		for (int i = 0; i < finalParts.length; i++) {
			parts.add(finalParts[i]);
		}
		return parts;
	}
}
