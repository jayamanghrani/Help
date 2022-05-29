package com.tcs.webreports.cache.model;

import java.io.Serializable;

import com.tcs.webreports.cache.keys.CacheKey;

public class CacheEntry implements Serializable
{
	  private static final long serialVersionUID = -9106096970254318468L;
	  private CacheKey cacheKey;
	  private Object object;

	  public CacheKey getCacheKey()
	  {
	    return this.cacheKey;
	  }

	  public void setCacheKey(CacheKey cacheKey)
	  {
	    this.cacheKey = cacheKey;
	  }

	  public Object getObject()
	  {
	    return this.object;
	  }

	  public void setObject(Object object)
	  {
	    this.object = object;
	  }
}
