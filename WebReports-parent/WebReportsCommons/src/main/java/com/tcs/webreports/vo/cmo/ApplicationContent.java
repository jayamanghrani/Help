package com.tcs.webreports.vo.cmo;

import java.io.Serializable;
import java.util.List;

public class ApplicationContent implements Serializable
{
/**
	 * 
	 */
	private static final long serialVersionUID = 7416759575048654793L;
private long entryTime;
private String coRelationId;
private List<ApplicationHeader> headers;

public long getEntryTime()
{
  return this.entryTime;
}

public void setEntryTime(long entryTime)
{
  this.entryTime = entryTime;
}

public String getCoRelationId()
{
  return this.coRelationId;
}

public void setCoRelationId(String coRelationId)
{
  this.coRelationId = coRelationId;
}

public List<ApplicationHeader> getHeaders()
{
  return this.headers;
}

public void setHeaders(List<ApplicationHeader> headers)
{
  this.headers = headers;
}

public String toString()
{
  StringBuilder builder = new StringBuilder();
  builder.append("ApplicationContent [entryTime=");
  builder.append(this.entryTime);
  builder.append(", coRelationId=");
  builder.append(this.coRelationId);
  builder.append(", headers=");
  builder.append(this.headers);
  builder.append("]");
  return builder.toString();
}
}
