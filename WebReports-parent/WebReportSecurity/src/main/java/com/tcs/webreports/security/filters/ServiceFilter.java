package com.tcs.webreports.security.filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.tcs.webreports.config.utils.UtilProperties;
import com.tcs.webreports.exception.cmo.ErrorVO;
import com.tcs.webreports.security.util.HttpHeaderProperty;
import com.tcs.webreports.vo.cmo.ApplicationHeader;
import com.tcs.webreports.vo.cmo.RequestContent;
import com.tcs.webreports.vo.cmo.ResponseContent;

public class ServiceFilter
  implements Filter
{
  private static final Logger LOGGER = Logger.getLogger(ServiceFilter.class);

  public void init(FilterConfig filterConfig)
    throws ServletException
  {
  }

  public void destroy()
  {
  }

  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
    throws IOException, ServletException
  {
    try
    {
      long startTime = System.currentTimeMillis();
      HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
      HttpServletResponse httpServletResponse = (HttpServletResponse)servletResponse;
      Gson gson = new Gson();
      HttpRequestValidator validator = new HttpRequestValidator();

      LOGGER.info(new StringBuilder().append("Entering at :").append(httpServletRequest.getRequestURI()).toString());

      createHttpHeaderProperty(httpServletRequest);

      ServletRequestWrapper wrapper = new ServletRequestWrapper(httpServletRequest);

      RequestContent requestContent = getRequestContent(wrapper, startTime);

      if ("true".equalsIgnoreCase(UtilProperties.getEnvelopeLoggingEnabled()))
      {
        LOGGER.info(new StringBuilder().append("REQUEST CONTENT : ").append(gson.toJson(requestContent)).toString());
      }

      addCorelationId(httpServletRequest, requestContent.getCoRelationId());

      addCORSHeader(httpServletResponse);

      HttpServletResponseCopier responseCopier = new HttpServletResponseCopier(httpServletResponse);
      String responseJson = "";
     ErrorVO errorVo =null;
     errorVo=validator.validateHTTPRequest(httpServletRequest, httpServletResponse, requestContent);

      LOGGER.info(new StringBuilder().append("Status Code from HttpRequestValidator : ").append(errorVo.getErrorCode()).toString());
      if (202 == errorVo.getErrorCode())
      {
        filterChain.doFilter(wrapper, responseCopier);
      }
      else
    	  {
    	  httpServletResponse.sendError(errorVo.getErrorCode(), errorVo.getErrorMessage());
    	  return;
    	  }

      responseCopier.flushBuffer();

      byte[] copy = responseCopier.getCopy();
      responseJson = new String(copy, httpServletResponse.getCharacterEncoding());

      if ("true".equalsIgnoreCase(UtilProperties.getEnvelopeLoggingEnabled()))
      {
        ResponseContent responseContent = getResponseContent(requestContent, httpServletResponse, responseJson);

        LOGGER.info(new StringBuilder().append("RESPONSE CONTENT : ").append(gson.toJson(responseContent)).toString());
      }

      LOGGER.info(new StringBuilder().append("Http Response Status for ").append(httpServletRequest.getRequestURI()).append(" : ").append(httpServletResponse.getStatus()).toString());
      LOGGER.info(new StringBuilder().append("Exiting from :").append(httpServletRequest.getRequestURI()).toString());
    } catch (Exception e) {
      LOGGER.error("Exception in filter :", e);
    }
  }

  /**
   * Method to get request content
   * @param wrapper
   * @param startTime
   * @return
   * @throws IOException
   */
  private RequestContent getRequestContent(ServletRequestWrapper wrapper, long startTime)
    throws IOException
  {
    RequestContent requestContent = new RequestContent();
    requestContent.setMethod(wrapper.getMethod());
    requestContent.setEntryTime(startTime);
    requestContent.setCoRelationId(buildCorelationId(wrapper, startTime));
    requestContent.setHeaders(getHeaders(wrapper));
    requestContent.setParameterMap(wrapper.getParameterMap());
    requestContent.setRequestJson(replaceExclusions(getRequestJson(wrapper)));
    return requestContent;
  }

  /**
   * Method to build corelation ID
   * @param request
   * @param startTime
   * @return
   */
  private String buildCorelationId(HttpServletRequest request, long startTime)
  {
    String requestUri = request.getRequestURI();
    String[] parts = requestUri.split("/");
    String controller = parts[(parts.length - 2)];
    String action = parts[(parts.length - 1)];
    StringBuilder builder = new StringBuilder();
    builder.append(request.getHeader("applicationid"));
    builder.append("_");
    String userId = request.getHeader("userid");
    if ((userId == null) || (userId.isEmpty()))
      builder.append("PRELOGIN");
    else {
      builder.append(userId);
    }
    builder.append("_");
    builder.append(controller);
    builder.append("_");
    builder.append(action);
    builder.append("_");
    builder.append(startTime);
    builder.append("_");
    if (null != request.getHeader("X-Auth-Token"))
      builder.append(request.getHeader("X-Auth-Token").hashCode());
    else {
      builder.append("PRELOGIN");
    }
    return builder.toString();
  }

  /**
   * Method to get response content
   * @param requestContent
   * @param response
   * @param responseJson
   * @return
   * @throws IOException
   */
  private ResponseContent getResponseContent(RequestContent requestContent, HttpServletResponse response, String responseJson)
    throws IOException
  {
    long startTime = requestContent.getEntryTime();
    ResponseContent responseContent = new ResponseContent();
    responseContent.setEntryTime(startTime);
    responseContent.setCoRelationId(requestContent.getCoRelationId());
    responseContent.setHeaders(getHeaders(response));
    responseContent.setResponseJson(replaceExclusions(responseJson));
    long endTime = System.currentTimeMillis();
    responseContent.setProcessingTime(new StringBuilder().append(endTime - startTime).append("ms").toString());
    return responseContent;
  }

  /**
   * Method to get headers from request
   * @param request
   * @return
   */
  private List<ApplicationHeader> getHeaders(HttpServletRequest request)
  {
    List headers = new ArrayList();
    HttpHeaderProperty httpHeaderProperty = (HttpHeaderProperty)request.getAttribute("HTTP_HEADERS");
    Map<String, List<String>> headerMap = httpHeaderProperty.getProperty();
    for (Map.Entry entry : headerMap.entrySet()) {
      headers.add(new ApplicationHeader((String)entry.getKey(), (List)entry.getValue()));
    }
    return headers;
  }

  /**
   * Method to get headers from response
   * @param response
   * @return
   */
  private List<ApplicationHeader> getHeaders(HttpServletResponse response)
  {
    List headers = new ArrayList();
    List<String> headerNames = (List)response.getHeaderNames();
    for (String headerName : headerNames) {
      headers.add(new ApplicationHeader(headerName, (List)response.getHeaders(headerName)));
    }
    List statusCode = new ArrayList();
    statusCode.add(String.valueOf(response.getStatus()));
    headers.add(new ApplicationHeader("Status-Code", statusCode));

    List contentTypes = new ArrayList();
    contentTypes.add(response.getContentType());
    headers.add(new ApplicationHeader("Content-Type", contentTypes));

    List contentLength = new ArrayList();
    contentLength.add(String.valueOf(response.getBufferSize()));
    headers.add(new ApplicationHeader("Content-Length", contentLength));

    return headers;
  }

  /**
   * Method to build request json
   * @param wrapper
   * @return
   * @throws IOException
   */
  public String getRequestJson(ServletRequestWrapper wrapper)
    throws IOException
  {
    BufferedReader reader = new BufferedReader(new InputStreamReader(wrapper.getInputStream(), StandardCharsets.UTF_8));

    String line = "";
    StringBuilder builder = new StringBuilder();
    while ((line = reader.readLine()) != null) {
      builder.append(line);
    }
    return builder.toString();
  }

  /**
   * Method to mask sensitive information
   * @param jsonString
   * @return
   */
  public String replaceExclusions(String jsonString)
  {
    String json = jsonString;
    HttpRequestValidator validator = new HttpRequestValidator();
    if (!json.isEmpty()) {
      Map<String, String> jsonMap = validator.getMapFromJsonObject(new JSONObject(jsonString));

      for (Map.Entry entry : jsonMap.entrySet()) {
        String key = (String)entry.getKey();
        String value = (String)entry.getValue();
        if ("password".equalsIgnoreCase(key)) {
          json = replaceExclusions(jsonString, key, value);
        }
        if ((key.endsWith("password")) || (key.endsWith("Password"))) {
          json = replaceExclusions(jsonString, key, value);
        }
      }
    }
    return json;
  }

  /**
   * 
   * @param jsonString
   * @param key
   * @param value
   * @return
   */
  private String replaceExclusions(String jsonString, String key, String value)
  {
    String start = jsonString.substring(0, jsonString.indexOf(key));
    String end = jsonString.substring(jsonString.indexOf(key), jsonString.length());

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < value.length(); i++) {
      builder.append("*");
    }
    end = end.replaceFirst(value, builder.toString());
    return new StringBuilder().append(start).append(end).toString();
  }

  /**
   * 
   * @param request
   * @param coRelationId
   */
  private void addCorelationId(HttpServletRequest request, String coRelationId)
  {
    HttpHeaderProperty httpHeaderProperty = (HttpHeaderProperty)request.getAttribute("HTTP_HEADERS");

    List corelationIds = new ArrayList();
    corelationIds.add(coRelationId);
    httpHeaderProperty.getProperty().put("coRelationId", corelationIds);
  }

  private void createHttpHeaderProperty(HttpServletRequest req)
  {
    Enumeration headerNames = req.getHeaderNames();
    Map headersMap = new HashMap();
    while (headerNames.hasMoreElements()) {
      String key = (String)headerNames.nextElement();
      Enumeration headers = req.getHeaders(key);
      String header = (String)headers.nextElement();
      String[] headerParts = header.split(", ");
      List headerList = new ArrayList();
      for (String part : headerParts) {
        headerList.add(part);
      }
      headersMap.put(key.toLowerCase(), headerList);
    }
    Locale locale = req.getLocale();
    List country = new ArrayList();
    country.add(locale.getCountry());

    List language = new ArrayList();
    language.add(locale.getLanguage());

    List localAddress = new ArrayList();
    localAddress.add(req.getLocalAddr());

    List remoteAddress = new ArrayList();
    remoteAddress.add(req.getRemoteAddr());

    List serverName = new ArrayList();
    serverName.add(req.getServerName());

    List requestUri = new ArrayList();
    requestUri.add(req.getRequestURI());

    headersMap.put("Locale-Country", country);
    headersMap.put("Locale-Language", language);
    headersMap.put("Local-Address", localAddress);
    headersMap.put("Remote-Address", remoteAddress);
    headersMap.put("Server-Name", serverName);
    headersMap.put("Request-URI", requestUri);
    req.setAttribute("HTTP_HEADERS", new HttpHeaderProperty(headersMap));
  }

  /**
   * 
   * @param response
   */
  private void addCORSHeader(HttpServletResponse response)
  {
    response.addHeader("Access-Control-Allow-Credentials", "true");
    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, HEAD");
    response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept,X-Auth-Token, applicationid, userid, typeofcustomer, customername");
    response.addHeader("Access-Control-Max-Age", "1728000");
    response.addHeader("Access-Control-Expose-Headers", "Current-Date,Current-Time");
    String currentDate = String.valueOf(System.currentTimeMillis());
    response.addHeader("Current-Date", currentDate);
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    String currentTime = sdf.format(date);
    response.addHeader("Current-Time", currentTime);
  }
}