package com.tcs.webreports.cache;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.tcs.webreports.cache.keys.CacheKey;

public class Cache {
	 private static final Logger LOGGER = Logger.getLogger(Cache.class);
	  private static MyCache myCache;
	  private static Map<String, CacheRegion> cacheMaps = null;

	  public static void put(CacheKey key, Object value)
	  {
	    if ((null != key) && (null != value))
	      myCache.put(key, value);
	    else
	      LOGGER.info("Cache :: put() :: Key is " + key + " and value is " + value);
	  }

	  public static Object get(CacheKey key)
	  {
	    return myCache.get(key);
	  }

	  public static int size()
	  {
	    return myCache.size();
	  }

	  public static void remove(CacheKey key)
	  {
	    myCache.remove(key);
	  }

	  public static boolean keyExists(CacheKey key)
	  {
	    return myCache.keyExists(key);
	  }

	  public static Set<CacheKey> keySet()
	  {
	    return myCache.keySet();
	  }

	  public static CacheRegion getCacheMap(String cacheKey)
	  {
	    return (CacheRegion)cacheMaps.get(cacheKey);
	  }

	  public static void addCacheMaps(String cacheKey, CacheRegion cacheRegion)
	  {
	    cacheMaps.put(cacheKey, cacheRegion);
	  }

	  static
	  {
	    String ehcache = "ehcache";
	    if ("hashmap".equals(ehcache))
	      myCache = EhCacheWrapper.getInstance();
	    else {
	      myCache = SimpleCacheWrapper.getInstance();
	    }
	    cacheMaps = new ConcurrentHashMap();
	  }
}
