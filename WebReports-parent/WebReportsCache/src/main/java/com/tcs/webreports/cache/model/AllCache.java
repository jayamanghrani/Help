package com.tcs.webreports.cache.model;

import java.io.Serializable;
import java.util.List;

public class AllCache implements Serializable
{
	  private static final long serialVersionUID = -9106096970254318468L;
	  private List<CacheEntry> cacheEntries;

	  public List<CacheEntry> getCacheEntries()
	  {
	    return this.cacheEntries;
	  }

	  public void setCacheEntries(List<CacheEntry> cacheEntries)
	  {
	    this.cacheEntries = cacheEntries;
	  }
}
