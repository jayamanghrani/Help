/**
 * 
 */
package com.tcs.employeeportal.component.invoker;

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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.tcs.employeeportal.component.util.RESTComponentInvokerUtil;
import com.tcs.employeeportal.util.UtilConstants;
import com.tcs.employeeportal.config.utils.PropertiesUtil;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;
import com.tcs.employeeportal.vo.cmo.EmployeeResponseVO;
import com.tcs.employeeportal.vo.cmo.Header;


/**
 * @author 738566
 *
 */
public class EmployeeRestInvoker {
	
	/*private static final Logger LOGGER = Logger
			.getLogger(EmployeeRestInvoker.class);*/
	
	/** The logger. */
	private static final Logger LOGGER = Logger.getLogger("empPortalLogger");
	
	public EmployeeRestInvoker() {
	}
	
/*	public String getURL(String eventId) {
		LOGGER.info("event id......." + eventId);
		
	
		String urlString = null;
		if (UtilConstants.GET_BREAKIN_DETAILS.equalsIgnoreCase(eventId)) {
			return RESTComponentInvokerUtil.breakinDetails;
		} else
		{
			return null;
		}
	}*/
	
	/**
	 * Invoke client.
	 * 
	 * @param json
	 *            the json
	 * @param URL
	 *            the url
	 * @param header
	 *            the header
	 * @return the string
	 * @throws IntegrationTechnicalException
	 *             the integration technichal exception
	 */
	
/*	public String invokeClient(EmployeePortalRequestObject json, String URL, Header header) throws IntegrationTechnicalException {
		String coRelationId = null;
		String eventId = null;
		String errorMsg = null;
		if (null != header) {
		//	coRelationId = header.getCoRelationId();
			eventId = header.getEventID();
		}
		RestTemplate restTemplate = new RestTemplate();
		String result = null;
		LOGGER.debug("invoking emp service...");

		LOGGER.debug("URL: "+ eventId + ": "+ URL);
		LOGGER.info("EMP INPUT :: "+ eventId + ": "+json);
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
			String timeOut = PropertiesUtil.getConfigData(eventId + ".TIMEOUT");
			errorMsg = PropertiesUtil.getConfigData(eventId + ".ERROR_MSG");
			if (null != timeOut && !timeOut.isEmpty()) {
				rf.setConnectTimeout(Integer.parseInt(timeOut));
				rf.setReadTimeout(Integer.parseInt(timeOut));
			} else {
				rf.setConnectTimeout(Integer.parseInt(PropertiesUtil.getConfigData("DEFAULT_TIMEOUT")));
				rf.setReadTimeout(Integer.parseInt(PropertiesUtil.getConfigData("DEFAULT_TIMEOUT")));
			}

			restTemplate.setRequestFactory(rf);
			StopWatch stopWatch = new StopWatch();
			LOGGER.info(eventId + " Emp invocation at : " + System.currentTimeMillis());
			stopWatch.start();
			result = restTemplate.postForObject(URL, entity, String.class);
			stopWatch.stop();
			LOGGER.debug(eventId + " Emp response at : " + System.currentTimeMillis());
			LOGGER.info(eventId + " Time taken to process Emp service : " + stopWatch.prettyPrint());
			LOGGER.debug(eventId + " Data received from Emp is..." + result);

		} catch (HttpClientErrorException e) {
			LOGGER.error(eventId + " Emp service invocation failed: "+ eventId +": " + e.getResponseBodyAsString(), e);
			throw new IntegrationTechnicalException(e.getResponseBodyAsString(), e);
		} catch (HttpServerErrorException e) {
			LOGGER.error(eventId + " Emp service invocation failed: " + eventId +": " + e.getResponseBodyAsString(), e);
						throw new IntegrationTechnicalException(e.getResponseBodyAsString(), e);
		} catch (ResourceAccessException e) {
			LOGGER.error(eventId + " Emp service invocation failed: " + eventId +": " + e.getMessage(), e);
			if (null != errorMsg && !errorMsg.isEmpty()) {
				throw new IntegrationTechnicalException(errorMsg, e);
			} else {
				throw new IntegrationTechnicalException(PropertiesUtil.getConfigData("DEFAULT_ERROR_MSG"), e);
			}
		} catch (Exception e) {
			LOGGER.error(eventId + " Emp service invocation failed: " + eventId +": " , e);
			throw new IntegrationTechnicalException(e);
		}

		return result;
	}*/

/*	public EmployeePortalResponseObject invokeDB(EmployeePortalRequestObject requestVO) throws IntegrationTechnicalException {
		String URL = getURL(requestVO.getHeader().getEventID());
		Header header = requestVO.getHeader();
		requestVO.setHeader(null);
	//	String json = gson.toJson(requestVO);
	//	LOGGER.info(header.getCoRelationId() + " input json:" + json);
		EmployeeResponseVO employeeResponseVO = new EmployeeResponseVO();
		employeeResponseVO.setJson(invokeClient(requestVO, URL, header));
		employeeResponseVO.setHeader(header);
		return employeeResponseVO;
	} */
	
	
/*	public static void main(){
		System.out.println("I am inside main method");
		EmployeeDBInvoker dt = new EmployeeDBInvoker();
		String as= "Pending";
		BreakinAssessmentDao breakinAssessmentDao = null;
		List<TBreakinAssessment> list =  breakinAssessmentDao.getBreakinDetailsResponse(as);
		System.out.println("I am inside main method 2 " +list.get(0));
		
		
		
	}*/

}
