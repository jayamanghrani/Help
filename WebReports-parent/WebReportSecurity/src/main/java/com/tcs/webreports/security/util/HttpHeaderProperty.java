package com.tcs.webreports.security.util;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class HttpHeaderProperty
{
  private Map<String, List<String>> property;

  public HttpHeaderProperty()
  {
  }

  public HttpHeaderProperty(Map<String, List<String>> property)
  {
    this.property = property;
  }

  public Map<String, List<String>> getProperty()
  {
    return this.property;
  }

  public void setProperty(Map<String, List<String>> property)
  {
    this.property = property;
  }
}