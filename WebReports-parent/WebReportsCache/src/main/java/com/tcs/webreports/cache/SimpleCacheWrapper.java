package com.tcs.webreports.cache;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.tcs.webreports.cache.keys.CacheKey;

public class SimpleCacheWrapper implements MyCache
{
	  private Map<CacheKey, Object> cache;
	  private static SimpleCacheWrapper wrapper = new SimpleCacheWrapper();

	  private SimpleCacheWrapper()
	  {
	    this.cache = new ConcurrentHashMap();
	  }

	  public static SimpleCacheWrapper getInstance()
	  {
	    return wrapper;
	  }

	  public void put(CacheKey key, Object value)
	  {
	    this.cache.put(key, value);
	  }

	  public Object get(CacheKey key)
	  {
	    return this.cache.get(key);
	  }

	  public int size()
	  {
	    return this.cache.size();
	  }

	  public void remove(CacheKey key)
	  {
	    this.cache.remove(key);
	  }

	  public boolean keyExists(CacheKey key)
	  {
	    return this.cache.containsKey(key);
	  }

	  public Set<CacheKey> keySet()
	  {
	    return this.cache.keySet();
	  }
}
