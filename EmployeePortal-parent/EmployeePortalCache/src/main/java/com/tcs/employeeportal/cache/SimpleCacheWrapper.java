package com.tcs.employeeportal.cache;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.tcs.employeeportal.cache.MyCache;
import com.tcs.employeeportal.cache.key.CacheKey;

public class SimpleCacheWrapper implements MyCache {

	/** The cache. */
	private Map<CacheKey, Object> cache;
	
	/** The wrapper. */
	private static SimpleCacheWrapper wrapper = new SimpleCacheWrapper();
	
	/**
	 * Instantiates a new simple cache wrapper.
	 */
	private SimpleCacheWrapper() {
		cache = new ConcurrentHashMap<CacheKey, Object>();
	}
	
	/**
	 * Gets the single instance of SimpleCacheWrapper.
	 * 
	 * @return single instance of SimpleCacheWrapper
	 */
	public static SimpleCacheWrapper getInstance() {
		return wrapper;
	}
	
	/* (non-Javadoc)
	 * @see com.tcs.bancsins.integration.cache.MyCache#put(java.lang.String, java.lang.Object)
	 */
	@Override
	public void put(CacheKey key, Object value) {
		cache.put(key, value);
	}
	
	/* (non-Javadoc)
	 * @see com.tcs.bancsins.integration.cache.MyCache#get(java.lang.String)
	 */
	@Override
	public Object get(CacheKey key) {
		return cache.get(key);
	}
	
	/* (non-Javadoc)
	 * @see com.tcs.bancsins.integration.cache.MyCache#size()
	 */
	@Override
	public int size() {
		return cache.size();
	}
	
	/* (non-Javadoc)
	 * @see com.tcs.bancsins.integration.cache.MyCache#remove(java.lang.String)
	 */
	@Override
	public void remove(CacheKey key) {
		cache.remove(key);
	}

	/* (non-Javadoc)
	 * @see com.tcs.bancsins.integration.cache.MyCache#keyExists(java.lang.String)
	 */
	@Override
	public boolean keyExists(CacheKey key) {
		return cache.containsKey(key);
	}
	
	/* (non-Javadoc)
	 * @see com.tcs.bancsins.integration.cache.MyCache#keySet()
	 */
	@Override
	public Set<CacheKey> keySet() {
		return cache.keySet();
	}

	
}
