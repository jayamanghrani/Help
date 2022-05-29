package com.tcs.docstore.cache;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;


import com.tcs.docstore.cache.CacheRegion;
import com.tcs.docstore.cache.Config;
import com.tcs.docstore.cache.EhCacheWrapper;
import com.tcs.docstore.cache.MyCache;
import com.tcs.docstore.cache.SimpleCacheWrapper;
import com.tcs.docstore.cache.key.CacheKey;

/**
 * The Class Cache.
 */
public final class Cache {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger("docStoreLogger");
	
	/** The Constant CHANNEL_ROLE. */
	public static final String CHANNEL_ROLE = "CHANNEL_ROLE";
	/** The cache. */
	private static MyCache myCache;
	
	/** The cache maps. */
	private static Map<String, CacheRegion> cacheMaps = null;

	static {
		String ehcache = "ehcache";
		if (Config.CACHE.equals(ehcache)) {
			myCache = EhCacheWrapper.getInstance();
		} else {
			myCache = SimpleCacheWrapper.getInstance();
		}
		cacheMaps = new ConcurrentHashMap<String, CacheRegion>();
	}

	/**
	 * Instantiates a new cache.
	 */
	private Cache() {
	}

	/**
	 * Put.
	 * 
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public static void put(CacheKey key, Object value) {
		
		if(null != key && null != value) {
			myCache.put(key, value);
		} else {
			LOGGER.info("Cache :: put() :: Key is "+key+" and value is "+value);
		}
		
	}

	/**
	 * Gets the.
	 * 
	 * @param key
	 *            the key
	 * @return the object
	 */
	public static Object get(CacheKey key) {
		return myCache.get(key);
	}

	/**
	 * Size.
	 * 
	 * @return the int
	 */
	public static int size() {
		return myCache.size();
	}

	/**
	 * Removes the.
	 * 
	 * @param key
	 *            the key
	 */
	public static void remove(CacheKey key) {
		myCache.remove(key);
	}

	/**
	 * Key exists.
	 * 
	 * @param key
	 *            the key
	 * @return true, if successful
	 */
	public static boolean keyExists(CacheKey key) {
		return myCache.keyExists(key);
	}

	/**
	 * Key set.
	 * 
	 * @return the sets the
	 */
	public static Set<CacheKey> keySet() {
		return myCache.keySet();
	}

	/**
	 * Gets the cache map.
	 *
	 * @param cacheKey the cache key
	 * @return the cache map
	 */
	public static CacheRegion getCacheMap(String cacheKey) {
		return cacheMaps.get(cacheKey);
	}

	/**
	 * Adds the cache maps.
	 *
	 * @param cacheKey the cache key
	 * @param cacheRegion the cache region
	 */
	public static void addCacheMaps(String cacheKey , CacheRegion cacheRegion) {
		Cache.cacheMaps.put(cacheKey, cacheRegion);
	}
}