package com.tcs.docstore.service.alfresco;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tcs.docstore.cache.util.CacheConstants;
import com.tcs.docstore.exception.cmo.IntegrationTechnicalException;
import com.tcs.docstore.vo.cmo.DocStoreVO;


public class AlfrescoService {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger("docStoreLogger");

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
	 * @param gson
	 *            the gson to set
	 */
	public static void setGson(Gson gson) {
		AlfrescoService.gson = gson;
	}
	
	/**
	 * Gets the cache json.
	 *
	 * @param alfrescoKey the alfresco key
	 * @return the cache json
	 * @throws IntegrationTechnicalException the integration technical exception
	 */
	public DocStoreVO getCacheJson(String json) throws IntegrationTechnicalException {
		String URL = CacheConstants.ALFRESCO_URL;
		//String json = gson.toJson(alfrescoKey);
		DocStoreVO docstoreVO = new DocStoreVO();
		docstoreVO.setJson(invokeClient(json));
		return docstoreVO;
	}

	/**
	 * Invoke client.
	 * 
	 * @param json
	 *            the json
	 * @param URL
	 *            the url
	 * @return the string
	 * @throws IntegrationTechnicalException
	 *             the integration technichal exception
	 */
	public String invokeClient(String json)
			throws IntegrationTechnicalException {
		String errorMsg = null;
		String URL = CacheConstants.ALFRESCO_URL;
		RestTemplate restTemplate = new RestTemplate();
		String result = null;
		LOGGER.info("invoking alfresco service...");
		LOGGER.info("URL:" + URL);
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
			messageConverters.add(new StringHttpMessageConverter());
			messageConverters.add(new MappingJackson2HttpMessageConverter());
			restTemplate.setMessageConverters(messageConverters);
			HttpEntity<Object> entity = new HttpEntity<Object>(json, headers);
			HttpComponentsClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory();
			restTemplate.setRequestFactory(rf);
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			result = restTemplate.postForObject(URL, entity, String.class);
			stopWatch.stop();
			LOGGER.debug(" CACHE OUTPUT :: " + result);
	
		} catch (Exception e) {
			LOGGER.error("alfresco invocation failed", e);
			e.printStackTrace();
			throw new IntegrationTechnicalException(e);
		}

		return result;
	}
}
