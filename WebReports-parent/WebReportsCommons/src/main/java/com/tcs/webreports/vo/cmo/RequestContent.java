package com.tcs.webreports.vo.cmo;

import java.util.Map;

public class RequestContent extends ApplicationContent
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 2605553323089624689L;
private String method;
  private String requestJson;
  private Map<String, String[]> parameterMap;

  public String getMethod()
  {
    return this.method;
  }

  public void setMethod(String method)
  {
    this.method = method;
  }

  public String getRequestJson()
  {
    return this.requestJson;
  }

  public void setRequestJson(String requestJson)
  {
    this.requestJson = requestJson;
  }

  public Map<String, String[]> getParameterMap()
  {
    return this.parameterMap;
  }

  public void setParameterMap(Map<String, String[]> parameterMap)
  {
    this.parameterMap = parameterMap;
  }

  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("RequestContent [method=");
    builder.append(this.method);
    builder.append(", requestJson=");
    builder.append(this.requestJson);
    builder.append(", parameterMap=");
    builder.append(this.parameterMap);
    builder.append("]");
    return builder.toString();
  }
}