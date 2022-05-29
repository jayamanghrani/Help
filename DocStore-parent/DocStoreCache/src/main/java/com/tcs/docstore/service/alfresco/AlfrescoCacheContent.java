package com.tcs.docstore.service.alfresco;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.tcs.docstore.asbo.alfresco.ContentDataASBO;
import com.tcs.docstore.alfresco.asbo.request.GetAlfrecoContentRequestASBO;
import com.tcs.docstore.alfresco.asbo.response.GetAlfrescoContentResponseASBO;
import com.tcs.docstore.cache.asbo.request.GetContentValueRequestASBO;
import com.tcs.docstore.service.alfresco.AlfrescoService;
import com.tcs.docstore.cache.Cache;
import com.tcs.docstore.cache.key.AlfrescoKey;
import com.tcs.docstore.cache.util.CacheConstants;
import com.tcs.docstore.exception.cmo.IntegrationTechnicalException;
import com.tcs.docstore.vo.cmo.DocStoreVO;

public class AlfrescoCacheContent {
	
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger("docStoreLogger");
	
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
	/*	typesOfContent.add(CacheConstants.BROCHURE_CONTENT);
		typesOfContent.add(CacheConstants.ABOUT_US_CONTENT);*/
		typesOfContent.add(CacheConstants.ADDITIONAL_LINKS_CONTENT);
		typesOfContent.add(CacheConstants.CAROUSEL_CONTENT);
		//typesOfContent.add(CacheConstants.DISCLAIMERS_CONTENT);
		typesOfContent.add(CacheConstants.INSTRUCTION_TO_USER);
		typesOfContent.add(CacheConstants.EXECUTIVE_MSG);
		typesOfContent.add(CacheConstants.EMPLOYEE_DOCUMENT);
		typesOfContent.add(CacheConstants.EMAIL_CONTENT);
		//typesOfContent.add(CacheConstants.FAQ_CONTENT);
		typesOfContent.add(CacheConstants.LATEST_NEWS_CONTENT);
		typesOfContent.add(CacheConstants.NOTIFICATION_CONTENT);
		typesOfContent.add(CacheConstants.OLD_EMPLOYEE_DOCUMENT);
		//typesOfContent.add(CacheConstants.POLICY_KEY_FEATURE_CONTENT);
		typesOfContent.add(CacheConstants.SMS_CONTENT);
		//typesOfContent.add(CacheConstants.TERMS_CONDITIONS_CONTENT);
		//typesOfContent.add(CacheConstants.WHATS_NEW_CONTENT);
		//typesOfContent.add(CacheConstants.HELP);
		/*typesOfContent.add(CacheConstants.EMP_SMS);
		typesOfContent.add(CacheConstants.EMP_EMAIL);*/

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
		DocStoreVO docstoreVO = null;
		try {
			docstoreVO = alfrescoService.getCacheJson("");
		} catch (IntegrationTechnicalException e) {
			LOGGER.info(e.getMessage());
		}

		if(null != docstoreVO){
			String json = docstoreVO.getJson();
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
