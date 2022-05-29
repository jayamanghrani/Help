/**
 * 
 *//*
package com.tcs.docstore.service.ticker;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.tcs.docstore.cache.AllCache;
import com.tcs.docstore.cache.Cache;
import com.tcs.docstore.cache.CacheEntry;
import com.tcs.docstore.cache.key.CacheKey;
import com.tcs.docstore.db.asbo.request.TickerDBRequestASBO;

*//**
 * @author 585226
 *
 *//*
public class CacheService {
	
	*//** The Constant LOGGER. *//*
	private static final Logger LOGGER = Logger.getLogger("docStoreLogger");

	private TickerCacheService tickercacheservice;
	private TickerDBRequestASBO tdbrasbo  ;
	
	public CacheService(){
		tickercacheservice = new TickerCacheService();
		tdbrasbo = new TickerDBRequestASBO();
		
	}
	
	public void initializeCache() {
		LOGGER.info("initializing cache");
		tdbrasbo.setTickerinput("intialisecache");
	//	tickercacheservice.cacheTickerValues(tdbrasbo);
		
		AllCache allCache = new AllCache();
		List<CacheEntry> cacheEntries = new ArrayList<CacheEntry>();
		Set<CacheKey> caheKeys = Cache.keySet();
		for (CacheKey cacheKey : caheKeys) {
			Object object = Cache.get(cacheKey);
			CacheEntry cacheEntry = new CacheEntry();
			cacheEntry.setCacheKey((com.tcs.docstore.cache.CacheKey) (cacheKey));
			cacheEntry.setObject(object);
			cacheEntries.add(cacheEntry);
		}
		allCache.setCacheEntries(cacheEntries);
		LOGGER.info(new Gson().toJson(allCache));
		
		
	}
}
*/