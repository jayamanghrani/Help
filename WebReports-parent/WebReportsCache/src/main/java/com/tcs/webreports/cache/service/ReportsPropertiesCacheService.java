package com.tcs.webreports.cache.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.tcs.webreports.cache.Cache;
import com.tcs.webreports.cache.CacheRegion;
import com.tcs.webreports.cache.keys.ReportPathKey;
import com.tcs.webreports.util.UtilConstants;


public class ReportsPropertiesCacheService {

	private final Logger LOGGER = Logger
			.getLogger(ReportsPropertiesCacheService.class);

	/**
	 * Get report path key
	 * @param reportName
	 * @return
	 */
	private ReportPathKey getReportPathKey(String reportName) {
		return new ReportPathKey(reportName);
	}
	/**
	 * Caching report properties file
	 */
	public void cacheReportPath() {
		LOGGER.info("caching reports path");
		Map propertiesMap = new HashMap();
		CacheRegion dataMap = new CacheRegion();
		Properties properties = new Properties();
		FileInputStream inputStream;
		try {
			ReportPathKey reportPathKey = null;
			inputStream = new FileInputStream(UtilConstants.REPORT_ABSOLUTE_PATH);
			properties.load(inputStream);
			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				reportPathKey = getReportPathKey(key);
				dataMap.put(reportPathKey, value);
			}
			Cache.addCacheMaps("getReportPath", dataMap);
		} catch (IOException e) {
			LOGGER.error(e.getStackTrace(),e);
			e.printStackTrace();
		}
	}
	/**
	 * get reports absolute path
	 * @param reportName
	 * @return
	 */
	public static String getAbsolutePath(String reportName) {
		String absolutePath = null;
		ReportPathKey reportPathKey = new ReportPathKey(reportName);

		if (Cache.get(reportPathKey) != null) {
			absolutePath = (String) Cache.get(reportPathKey);
		}
		return absolutePath;
	}
}
