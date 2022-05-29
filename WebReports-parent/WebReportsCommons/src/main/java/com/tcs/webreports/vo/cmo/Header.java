/**
 * 
 */
package com.tcs.webreports.vo.cmo;

import java.io.Serializable;

/**
 * @author 738566
 *
 */
public class Header
implements Serializable
{
private static final long serialVersionUID = 8476004097971914277L;
private String eventID;
private String applicationId;
private int responseCode;
private String userAgent;
private String coRelationId;
//private WebReportsSessionVO webReportsSessionVO;
private String customerName;
private String typeOfCustomer;

public String getEventID()
{
  return this.eventID;
}

public void setEventID(String eventID)
{
  this.eventID = eventID;
}

public int getResponseCode()
{
  return this.responseCode;
}

public void setResponseCode(int responseCode)
{
  this.responseCode = responseCode;
}

public String getDeviceID()
{
  return this.userAgent;
}

public void setDeviceID(String deviceID)
{
  this.userAgent = deviceID;
}

public String getCoRelationId()
{
  return this.coRelationId;
}

public void setCoRelationId(String coRelationId)
{
  this.coRelationId = coRelationId;
}


//public WebReportsSessionVO getWebReportsSessionVO() {
//	return webReportsSessionVO;
//}
//
//public void setWebReportsSessionVO(WebReportsSessionVO webReportsSessionVO) {
//	this.webReportsSessionVO = webReportsSessionVO;
//}

public String getApplicationId()
{
  return this.applicationId;
}

public void setApplicationId(String channelId)
{
  this.applicationId = channelId;
}

public String getCustomerName()
{
  return this.customerName;
}

public void setCustomerName(String customerName)
{
  this.customerName = customerName;
}

public String getTypeOfCustomer()
{
  return this.typeOfCustomer;
}

public void setTypeOfCustomer(String typeOfCustomer)
{
  this.typeOfCustomer = typeOfCustomer;
}

public String toString()
{
  StringBuilder builder = new StringBuilder();
  builder.append("Header [eventID=");
  builder.append(this.eventID);
  builder.append(", applicationId=");
  builder.append(this.applicationId);
  builder.append(", responseCode=");
  builder.append(this.responseCode);
  builder.append(", userAgent=");
  builder.append(this.userAgent);
  builder.append(", coRelationId=");
  builder.append(this.coRelationId);
  builder.append(", webReportsSessionVO=");
//  builder.append(this.webReportsSessionVO);
  builder.append(", customerName=");
  builder.append(this.customerName);
  builder.append(", typeOfCustomer=");
  builder.append(this.typeOfCustomer);
  builder.append("]");
  return builder.toString();
}
}