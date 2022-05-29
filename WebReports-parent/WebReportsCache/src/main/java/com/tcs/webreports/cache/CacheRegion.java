package com.tcs.webreports.cache;

import com.tcs.webreports.cache.keys.CacheKey;

import java.util.Set;
public class CacheRegion<K extends CacheKey, V>{
	  private MyCache myCache;

	  public CacheRegion()
	  {
	    String ehcache = "ehcache";
	    if ("hashmap".equals(ehcache))
	      this.myCache = EhCacheWrapper.getInstance();
	    else
	      this.myCache = SimpleCacheWrapper.getInstance();
	  }

	  public void put(K key, V value)
	  {
	    this.myCache.put(key, value);
	  }

	  public V get(K key)
	  {
	    return (V) this.myCache.get(key);
	  }

	  public int size()
	  {
	    return this.myCache.size();
	  }

	  public void remove(K key)
	  {
	    this.myCache.remove(key);
	  }

	  public boolean keyExists(K key)
	  {
	    return this.myCache.keyExists(key);
	  }

	  public Set<K> keySet()
	  {
	    return (Set<K>) this.myCache.keySet();
	  }
}
