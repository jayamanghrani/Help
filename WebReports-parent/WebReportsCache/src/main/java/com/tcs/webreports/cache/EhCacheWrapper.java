package com.tcs.webreports.cache;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tcs.webreports.cache.keys.CacheKey;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhCacheWrapper implements MyCache
{
	  private static EhCacheWrapper wrapper;
	  private Cache cache;

	  private EhCacheWrapper()
	  {
	    this.cache = CacheManager.getInstance().getCache("model_names");
	  }

	  public static EhCacheWrapper getInstance()
	  {
	    if (wrapper == null) {
	      wrapper = new EhCacheWrapper();
	    }
	    return wrapper;
	  }

	  public void put(CacheKey key, Object value)
	  {
	    this.cache.put(new Element(key, value));
	  }

	  public Object get(CacheKey key)
	  {
	    return this.cache.get(key).getObjectValue();
	  }

	  public void shutDownCache()
	  {
	    CacheManager.getInstance().shutdown();
	  }

	  public int size()
	  {
	    return this.cache.getKeys().size();
	  }

	  public void remove(CacheKey key)
	  {
	    this.cache.remove(key);
	  }

	  public boolean keyExists(CacheKey key)
	  {
	    return this.cache.isKeyInCache(key);
	  }

	  public Set<CacheKey> keySet()
	  {
	    return new HashSet(this.cache.getKeys());
	  }
}
