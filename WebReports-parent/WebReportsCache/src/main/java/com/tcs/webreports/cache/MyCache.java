/**
 * 
 */
package com.tcs.webreports.cache;

import java.util.Set;

import com.tcs.webreports.cache.keys.CacheKey;

/**
 * @author 926814
 *
 */


public abstract interface MyCache {
	 public abstract void put(CacheKey paramCacheKey, Object paramObject);

	  public abstract Object get(CacheKey paramCacheKey);

	  public abstract int size();

	  public abstract void remove(CacheKey paramCacheKey);

	  public abstract boolean keyExists(CacheKey paramCacheKey);

	  public abstract Set<CacheKey> keySet();
}
