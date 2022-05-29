/**
 * 
 */
package com.tcs.umsapp.web.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import com.tcs.umsapp.general.cache.RoleCacheService;

/**
 * @author 1092382
 *
 */
public class InitializeListener implements ServletContextListener  {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InitializeListener.class);
	
	public InitializeListener() {
		LOGGER.info("UmsappGeneral InitializeListener Constructor Called");
	}


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		LOGGER.info("UmsappGeneral InitializeListener Destructor Called");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		LOGGER.info("UmsappGeneral InitializeListener contextInitialized called");
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		RoleCacheService.getRoleDetailFromDB();
		stopWatch.stop();
		LOGGER.info("Time taken to initialize : " + stopWatch.prettyPrint());
		
		
	}

}
