package com.tcs.webreports.security.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.errors.IntrusionException;
import com.tcs.webreports.asbo.access.request.IsLoggedInRequestASBO;
import com.tcs.webreports.asbo.access.response.IsLoggedInResponseASBO;
import com.tcs.webreports.config.utils.PropertiesUtil;
import com.tcs.webreports.config.utils.UtilProperties;
import com.tcs.webreports.exception.cmo.ErrorVO;
import com.tcs.webreports.exception.cmo.IntegrationTechnicalException;
import com.tcs.webreports.integration.controller.AccessIntegrationController;
import com.tcs.webreports.security.util.HttpHeaderProperty;
import com.tcs.webreports.security.util.HttpHeaderUtil;
import com.tcs.webreports.util.ExceptionUtil;
import com.tcs.webreports.vo.cmo.RequestContent;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

public class HttpRequestValidator {
	private static final Logger LOGGER = Logger.getLogger(HttpRequestValidator.class);

	  private List<String> excludeParamList = null;

	  private List<String> excludeUrlList = null;

	  public HttpRequestValidator()
	  {
	    this.excludeUrlList = PropertiesUtil.getExcludeUrls();
//	    this.excludeParamList = new ArrayList();
	  }

	  /**
	   * Method to validate request
	   * @param request
	   * @param response
	   * @param requestContent
	   * @return
	   * @throws IOException
	   */
	  public ErrorVO validateHTTPRequest(HttpServletRequest request, HttpServletResponse response, RequestContent requestContent)
	    throws IOException
	  {
		 ErrorVO errorVO=new ErrorVO();
	    if (!isValidURI(request, response)) {
	    	errorVO.setErrorCode(417);
	    	errorVO.setErrorMessage("Invalid URI...");
	      LOGGER.error(requestContent.getCoRelationId() + " 417 Invalid URI...");
	      return errorVO;
	    }
	    
	    int validTokenCode = isValidToken(request, response);
	    if (202 != validTokenCode) {
	      LOGGER.error(getCorealtionId(request) + " " + validTokenCode + " : Invalid Token. Please login and try again.");

	      if ("true".equalsIgnoreCase(UtilProperties.getEnvelopeLoggingEnabled()))
	      {
	    	  System.out.println("invalid uservalidation");
	    	    errorVO.setErrorCode(401);
		    	errorVO.setErrorMessage("Invalid Token. Please login and try again.");
		      return errorVO;
	      }
	    }
	 
	    if (!xssValidationSuccess(request)) {
	      LOGGER.error(requestContent.getCoRelationId() + " 400 XSS validation failed..");
	      errorVO.setErrorCode(400);
	    	errorVO.setErrorMessage("XSS validation failed");
	      return errorVO;
	    }
	    if ((!request.getRequestURI().contains("autoLoginContoller/autologin")) && 
	      (!validRequestBody(requestContent.getRequestJson()))) {
	      LOGGER.error(requestContent.getCoRelationId() + " 412 Invalid Request Body.");
	      errorVO.setErrorCode(412);
	    	errorVO.setErrorMessage("Invalid Request Body.");
	      return errorVO;
	    }
	    errorVO.setErrorCode(202);
	    return errorVO;
	  }

	  /**
	   * Method to validate URI
	   * @param request
	   * @param response
	   * @return
	   */
	  public boolean isValidURI(HttpServletRequest request, HttpServletResponse response)
	  {
	    return !Pattern.matches(".*[|&;$%@'\"<>()+,].*", request.getRequestURI());
	  }

	  /**
	   * Method to validate token
	   * @param httpServletRequest
	   * @param httpServletResponse
	   * @return
	   */
	  public int isValidToken(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	  {
	    int validateTokenCode = 202;
	    if (!isExcludeUrl(this.excludeUrlList, httpServletRequest.getRequestURI())) {
	    	LOGGER.info(getCorealtionId(httpServletRequest) + "inside isValidToken method Authentication Required.");

	      String sessionToken = getSessionTokenFromRequest(httpServletRequest);
	      if (sessionToken != null) {
	        LOGGER.info(getCorealtionId(httpServletRequest) + "before invoking processIsLoggedIn");

	        WebReportsResponseObject responseVO = processIsLoggedIn(httpServletRequest, httpServletResponse);

        LOGGER.info(getCorealtionId(httpServletRequest) + "after invoking processIsLoggedIn");

	        IsLoggedInResponseASBO responseASBO = null;
	        ErrorVO errorVO = null;
        if ((responseVO instanceof IsLoggedInResponseASBO)) {
          responseASBO = (IsLoggedInResponseASBO)responseVO;
        } else {
	          errorVO = (ErrorVO)responseVO;
	          LOGGER.info(errorVO.getErrorMessage());
	          return 500;
	        }
	        if (!responseASBO.isLoggedIn()) {
	          LOGGER.info(getCorealtionId(httpServletRequest) + "un authorized.. 407");

	          validateTokenCode = 401;
	        }
	      } else {
	        LOGGER.info(getCorealtionId(httpServletRequest) + "un authorized.. 407");

	        validateTokenCode = 401;
	      }
	    }
	    return validateTokenCode;
	  }
	  /**
	   * Method to get session from request
	   * @param httpServletRequest
	   * @return
	   */
	  private String getSessionTokenFromRequest(HttpServletRequest httpServletRequest)
	  {
	    LOGGER.info(getCorealtionId(httpServletRequest) + "inside getSessionTokenFromRequest method");

	    String sessionToken = httpServletRequest.getHeader("X-Auth-Token");
	    HttpSession httpSession = httpServletRequest.getSession(false);
	    Cookie[] cookies = httpServletRequest.getCookies();

	    if ((sessionToken == null) && (httpSession != null)) {
	      sessionToken = (String)httpSession.getAttribute("authenticationToken");
	    }
	    else if (cookies != null) {
	      for (Cookie cookie : cookies) {
	        if ("authenticationToken".equals(cookie.getName())) {
	          sessionToken = cookie.getValue();
	          httpSession = httpServletRequest.getSession(true);
	          httpSession.setAttribute("authenticationToken", sessionToken);
	        }
	      }
	    }

	    LOGGER.info(getCorealtionId(httpServletRequest) + "returning session token from getSessionTokenFromRequest method");

	    return sessionToken;
	  }

	  /**
	   * Method to check xss validation
	   * @param request
	   * @return
	   */
	  public boolean xssValidationSuccess(HttpServletRequest request)
	  {
	    LOGGER.info(getCorealtionId(request) + " Entering xssValidationSuccess()");

	    Map queryAttributes = request.getParameterMap();

	    for (Iterator i$ = queryAttributes.keySet().iterator(); i$.hasNext(); ) { Object keyObject = i$.next();
	      if ((keyObject instanceof String)) {
	        String key = (String)keyObject;
	        Object valueObject = queryAttributes.get(key);
	        if ((valueObject instanceof String)) {
	          String value = (String)valueObject;
	          if (xssCheck(value, key).booleanValue()) {
	            LOGGER.info("xss validation failed " + key + " :" + value);
	            return false;
	          }
	        }
	        if ((valueObject instanceof String[])) {
	          String[] values = (String[])(String[])valueObject;
	          for (String value : values) {
	            if (xssCheck(value, key).booleanValue()) {
	              LOGGER.info("xss validation failed " + key + " :" + value);
	              return false;
	            }
	          }
	        }
	      }
	    }

	    LOGGER.info(getCorealtionId(request) + " Exiting xssValidationSuccess()");

	    return true;
	  }

	  public boolean validRequestBody(String jsonBody)
	    throws IOException
	  {
//	    Map<String,String> jsonMap = getMapFromJsonObject(new JSONObject(jsonBody));
//
//	    for (Map.Entry<String,String> entry : jsonMap.entrySet()) {
//	      String key = (String)entry.getKey();
//	      String value = (String)entry.getValue();
//
//	      LOGGER.info("Value of " + key + " : " + value);
//	      if ((value != null) && (!value.equals(" ")) && (!value.equals(""))) {
//	        String validation = PropertiesUtil.getConfigProperty(key);
//	        if (validation != null) {
//	          int maxLength = Integer.parseInt(PropertiesUtil.getConfigProperty(key + ".MaxLength"));
//
//	          boolean allowNull = false;
//	          if ("true".equalsIgnoreCase(PropertiesUtil.getConfigProperty(key + ".AllowNull")))
//	          {
//	            allowNull = true;
//	          }
//	          boolean validity = ESAPI.validator().isValidInput("AuthenticationFilter_" + key, value, validation, maxLength, allowNull);
//
//	          if (!validity) {
//	            LOGGER.error(key + " validation falied");
//	            return false;
//	          }
//	        }
//	        if (xssCheck((String)entry.getValue(), (String)entry.getKey()).booleanValue()) {
//	          return false;
//	        }
//	      }
//	    }
	    return true;
	  }

	  /**
	   * 
	   * @param val
	   * @param key
	   * @return
	   */
	  private Boolean xssCheck(String val, String key)
	  {
	    String value = null;
	    LOGGER.info("xssCheck key : " + key);
	    if ("xmlResult".equalsIgnoreCase(key)) {
	      LOGGER.info("Found " + key);
	      return Boolean.valueOf(false);
	    }
	    if ("docByteString".equalsIgnoreCase(key)) {
	      LOGGER.info("Found " + key);
	      return Boolean.valueOf(false);
	    }
	    try {
	      value = ESAPI.encoder().canonicalize(val);
	      LOGGER.info("XSSCheck() try block value:" + value);
	    } catch (IntrusionException e) {
	      LOGGER.error("Error", e);

	      value = ESAPI.encoder().canonicalize(val, false);
	      LOGGER.info("XSSCheck() catch block value:" + value);
	    }

	    Boolean xssContains = Boolean.valueOf((Pattern.matches(".*[|&$%@'<>()+].*", value)) || (Pattern.matches(".*[|&;$%@'\"<>()+,].*", value)));

	    if (xssContains.booleanValue()) {
	      xssContains = Boolean.valueOf((value.toLowerCase().contains("<script")) || (value.toLowerCase().contains("<img")) || (value.toLowerCase().contains("http:\\")) || (value.toLowerCase().contains("https:\\")) || (value.toLowerCase().contains("%3Cscript")) || (value.toLowerCase().contains("script%3E")) || (value.toLowerCase().contains("%3Ciframe")) || (value.toLowerCase().contains("<iframe")) || (value.toLowerCase().contains("javascript")) || (value.toLowerCase().contains("alert(")));
	    }

	    if (xssContains.booleanValue()) {
	      LOGGER.info("WIAuthFilter xssCheck failed for --> Key: " + key + " Value : " + value);

	      return xssContains;
	    }
	    if (!isExistInList(this.excludeParamList, key)) {
	      xssContains = Boolean.valueOf((Pattern.matches(".*[<>].*", value)) || (value.contains("<")) || (value.contains(">")));
	    }

	    if (xssContains.booleanValue()) {
	      LOGGER.info("AuthenticationFilter xssCheck failed for --> Key: " + key + " Value : " + value);
	    }

	    return xssContains;
	  }
	  /**
	   * Method to chek datalist
	   * @param dataList
	   * @param findKey
	   * @return
	   */
	  private boolean isExistInList(List<String> dataList, String findKey)
	  {
	    boolean exists = false;
	    for (String key : dataList) {
	      if (key.contains(findKey)) {
	        exists = true;
	      }
	    }
	    return exists;
	  }

	  /**
	   * Method to get excluded url
	   * @param excludeUrls
	   * @param findKey
	   * @return
	   */
	  private boolean isExcludeUrl(List<String> excludeUrls, String findKey)
	  {
	    boolean excludeUrl = false;
	    for (String url : excludeUrls) {
	      if (findKey.contains(url)) {
	        excludeUrl = true;
	      }
	    }
	    return excludeUrl;
	  }

	  /**
	   * Method to check logged in user
	   * @param httpServletRequest
	   * @param httpServletResponse
	   * @return
	   */
	  public WebReportsResponseObject processIsLoggedIn(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	  {
	    LOGGER.info(getCorealtionId(httpServletRequest) + "inside processIsLoggedIn method ");

	    IsLoggedInRequestASBO requestASBO = new IsLoggedInRequestASBO();
	    HttpHeaderProperty httpHeaderProperty = (HttpHeaderProperty)httpServletRequest.getAttribute("HTTP_HEADERS");

	    HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
	    String userId = httpServletRequest.getHeader("userid");
	    try {
	      LOGGER.info(getCorealtionId(httpServletRequest) + "inside processIsLoggedIn method setting http headers ");

	      requestASBO.setHeader(httpHeaderUtil.generateSpringHeader(httpHeaderProperty, "isLoggedIn"));
	    }
	    catch (IntegrationTechnicalException e)
	    {
	      LOGGER.info(getCorealtionId(httpServletRequest) + e.getMessage());

	      httpServletResponse.setStatus(400);
	      return ExceptionUtil.getApplicationIdErrorVO(e);
	    }
	    requestASBO.setSessionToken(httpServletRequest.getHeader("X-Auth-Token"));

	    requestASBO.setUserId(userId);
	    AccessIntegrationController controller = new AccessIntegrationController();
	    return controller.processIsLoggedIn(requestASBO);
	  }

	  /**
	   * Method to get corelation Id
	   * @param request
	   * @return
	   */
	  private String getCorealtionId(HttpServletRequest request)
	  {
	    HttpHeaderProperty httpHeaderProperty = (HttpHeaderProperty)request.getAttribute("HTTP_HEADERS");

	    List corelationIds = (List)httpHeaderProperty.getProperty().get("coRelationId");

	    return (String)corelationIds.get(0);
	  }

	  /**
	   * 
	   * @param jsonObject
	   * @return
	   */
	  public Map<String, String> getMapFromJsonObject(JSONObject jsonObject)
	  {
	    long start = System.currentTimeMillis();
	    Map<String,String> map = new HashMap<String,String>();
	    Iterator itr = jsonObject.keys();
	    while (itr.hasNext()) {
	      String key = (String)itr.next();
	      if ((!key.isEmpty()) && (jsonObject.get(key) != null)) {
	        if ((jsonObject.get(key) instanceof String)) {
	          map.put(key, jsonObject.getString(key));
	        } else if ((jsonObject.get(key) instanceof JSONObject)) {
	          Map<String,String> map2 = getMapFromJsonObject(jsonObject.getJSONObject(key));

	          for (Map.Entry<String,String> entry : map2.entrySet()) {
	            map.put(entry.getKey(), entry.getValue());
	          }
	        }
	      }
	    }
	    long end = System.currentTimeMillis();
	    LOGGER.info("Time taken to getMapFromJsonObject: " + (end - start) + "ms");
	    return map;
	  }
}