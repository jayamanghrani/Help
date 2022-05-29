package com.tcs.docstore.cache;

import java.util.HashSet;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


import com.tcs.docstore.cache.MyCache;
import com.tcs.docstore.cache.key.CacheKey;

public class EhCacheWrapper implements MyCache {

	/** The wrapper. */
	private static EhCacheWrapper wrapper;
	
	/** The cache. */
	private Cache cache;

	/**
	 * Instantiates a new eh cache wrapper.
	 */
	private EhCacheWrapper() {
		cache = CacheManager.getInstance().getCache("model_names");
	}

	/**
	 * Gets the single instance of EhCacheWrapper.
	 * 
	 * @return single instance of EhCacheWrapper
	 */
	public static EhCacheWrapper getInstance() {
		if (wrapper == null) {
			wrapper = new EhCacheWrapper();
		}
		return wrapper;
	}

	/* (non-Javadoc)
	 * @see com.tcs.docstore.integration.cache.MyCache#put(java.lang.String, java.lang.Object)
	 */
	@Override
	public void put(CacheKey key, Object value) {
		cache.put(new Element(key, value));
	}

	/* (non-Javadoc)
	 * @see com.tcs.bancsins.integration.cache.MyCache#get(java.lang.String)
	 */
	@Override
	public Object get(CacheKey key) {
		return cache.get(key).getObjectValue();
	}

	/**
	 * Shut down cache.
	 */
	public void shutDownCache() {
		CacheManager.getInstance().shutdown();
	}

	/* (non-Javadoc)
	 * @see com.tcs.bancsins.integration.cache.MyCache#size()
	 */
	@Override
	public int size() {
		return cache.getKeys().size();
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
		return cache.isKeyInCache(key);
	}

	/* (non-Javadoc)
	 * @see com.tcs.bancsins.integration.cache.MyCache#keySet()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<CacheKey> keySet() {
		return new HashSet<CacheKey>(cache.getKeys());
	}

}
