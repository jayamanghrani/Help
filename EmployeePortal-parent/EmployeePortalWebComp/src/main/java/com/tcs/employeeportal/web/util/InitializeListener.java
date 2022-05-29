/**
 * 
 */
package com.tcs.employeeportal.web.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.util.StopWatch;

import com.tcs.employeeportal.config.utils.UtilProperties;
import com.tcs.employeeportal.service.ticker.CacheService;
import com.tcs.employeeportal.util.UtilConstants;
/**
 * @author 585226
 *
 */
public class InitializeListener implements ServletContextListener  {
	
	private CacheService cacheService;
	/**
	 * Default constructor.
	 */
	public InitializeListener() {
		System.out.println("inside the InitializeListener constructor");
	}


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		cacheService = null;
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
		System.out.println("inside the InitializeListener contextInitialized");
		cacheService = new CacheService();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		UtilProperties.load(UtilConstants.CONFIG_PATH);
		cacheService.initializeCache();
		stopWatch.stop();
		System.err.println("Time taken to initialize : " + stopWatch.prettyPrint());
		
	}

}
