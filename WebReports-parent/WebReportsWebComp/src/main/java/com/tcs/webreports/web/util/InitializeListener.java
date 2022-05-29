/**
 * 
 */
package com.tcs.webreports.web.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.util.StopWatch;

import com.tcs.webreports.cache.service.ReportsCacheService;
import com.tcs.webreports.config.utils.UtilProperties;
import com.tcs.webreports.util.UtilConstants;
/**
 * @author 738566
 *
 */
public class InitializeListener implements ServletContextListener  {
	
	private ReportsCacheService cacheService;
	/**
	 * Default constructor.
	 */
	public InitializeListener() {
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		
		this.cacheService = new ReportsCacheService();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		UtilProperties.load(UtilConstants.CONFIG_PATH);
		cacheService.initializeCache();
		stopWatch.stop();
		System.err.println("Time taken to initialize : " + stopWatch.prettyPrint());
		
	}

	/**
	 * @return the cacheService
	 */
	public ReportsCacheService getCacheService() {
		return cacheService;
	}

	/**
	 * @param cacheService the cacheService to set
	 */
	public void setCacheService(ReportsCacheService cacheService) {
		this.cacheService = cacheService;
	}
}