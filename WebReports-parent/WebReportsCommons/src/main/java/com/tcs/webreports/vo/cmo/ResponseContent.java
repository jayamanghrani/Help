package com.tcs.webreports.vo.cmo;


public class ResponseContent extends ApplicationContent
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 2927558645477150586L;
private String responseJson;
  private String processingTime;

  public String getResponseJson()
  {
    return this.responseJson;
  }

  public void setResponseJson(String responseJson)
  {
    this.responseJson = responseJson;
  }

  public String getProcessingTime()
  {
    return this.processingTime;
  }

  public void setProcessingTime(String processingTime)
  {
    this.processingTime = processingTime;
  }

  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    builder.append("ResponseContent [responseJson=");
    builder.append(this.responseJson);
    builder.append(", processingTime=");
    builder.append(this.processingTime);
    builder.append("]");
    return builder.toString();
  }
}