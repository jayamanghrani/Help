package com.tcs.webreports.cache.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import com.google.gson.Gson;
import com.tcs.webreports.cache.Cache;
import com.tcs.webreports.cache.keys.CacheKey;
import com.tcs.webreports.cache.model.AllCache;
import com.tcs.webreports.cache.model.CacheEntry;


public class ReportsCacheService {
	private static final Logger LOGGER = Logger
			.getLogger(ReportsCacheService.class);
	private ReportsChannelCacheService reportsChannelCacheService;
	private ReportsPropertiesCacheService propertiesCacheService;

	public ReportsCacheService() {
		this.reportsChannelCacheService = new ReportsChannelCacheService();
		this.propertiesCacheService = new ReportsPropertiesCacheService();
	}

	/**
	 * cache call to reports properties and channel reports mapping 
	 */
	public void initializeCache() {
		this.propertiesCacheService.cacheReportPath();
		this.reportsChannelCacheService.cacheChannelAndReportsList();
		
		AllCache allCache = new AllCache();
		List<CacheEntry> cacheEntries = new ArrayList<CacheEntry>();
		Set<CacheKey> caheKeys = Cache.keySet();
		for (CacheKey cacheKey : caheKeys) {
			Object object = Cache.get(cacheKey);
			CacheEntry cacheEntry = new CacheEntry();
			cacheEntry.setCacheKey(cacheKey);
			cacheEntry.setObject(object);
			cacheEntries.add(cacheEntry);
		}
		allCache.setCacheEntries(cacheEntries);
		LOGGER.info(new Gson().toJson(allCache));
	}

	/**
	 * @return the reportsChannelCacheService
	 */
	public ReportsChannelCacheService getReportsChannelCacheService() {
		return reportsChannelCacheService;
	}

	/**
	 * @param reportsChannelCacheService the reportsChannelCacheService to set
	 */
	public void setReportsChannelCacheService(
			ReportsChannelCacheService reportsChannelCacheService) {
		this.reportsChannelCacheService = reportsChannelCacheService;
	}

	/**
	 * @return the propertiesCacheService
	 */
	public ReportsPropertiesCacheService getPropertiesCacheService() {
		return propertiesCacheService;
	}

	/**
	 * @param propertiesCacheService the propertiesCacheService to set
	 */
	public void setPropertiesCacheService(
			ReportsPropertiesCacheService propertiesCacheService) {
		this.propertiesCacheService = propertiesCacheService;
	}

}
