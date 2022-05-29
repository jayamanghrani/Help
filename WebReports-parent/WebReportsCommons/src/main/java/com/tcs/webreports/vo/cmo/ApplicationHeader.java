package com.tcs.webreports.vo.cmo;

import java.io.Serializable;
import java.util.List;

public class ApplicationHeader implements Serializable
{
/**
	 * 
	 */
	private static final long serialVersionUID = 3668137636931571981L;
private String name;
private List<String> values;

public ApplicationHeader()
{
}

public ApplicationHeader(String name, List<String> values)
{
  this.name = name;
  this.values = values;
}

public String getName()
{
  return this.name;
}

public void setName(String name)
{
  this.name = name;
}

public List<String> getValues()
{
  return this.values;
}

public void setValues(List<String> values)
{
  this.values = values;
}

public String toString()
{
  StringBuilder builder = new StringBuilder();
  builder.append("ApplicationHeader [name=");
  builder.append(this.name);
  builder.append(", values=");
  builder.append(this.values);
  builder.append("]");
  return builder.toString();
}
}
