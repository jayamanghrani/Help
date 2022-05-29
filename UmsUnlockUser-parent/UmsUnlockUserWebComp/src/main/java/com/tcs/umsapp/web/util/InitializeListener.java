/**
 * 
 */
package com.tcs.umsapp.web.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.util.StopWatch;

import com.tcs.umsapp.unlock.util.UtilProperties;
import com.tcs.umsapp.util.UtilConstants;

/**
 * @author 1092382
 *
 */
public class InitializeListener implements ServletContextListener  {
	
	private static final Logger LOGGER = Logger.getLogger(InitializeListener.class);
	
	public InitializeListener() {
		LOGGER.info("UmsUnlockUser InitializeListener Constructor Called");
	}


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		LOGGER.info("UmsUnlockUser InitializeListener Destructor Called");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		LOGGER.info("UmsUnlockUser InitializeListener contextInitialized called");
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		UtilProperties.load(UtilConstants.CONFIG_PATH);
		stopWatch.stop();
		LOGGER.info("Time taken to initialize : " + stopWatch.prettyPrint());
		
		
	}

}
