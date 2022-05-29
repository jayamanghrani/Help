/**
 * 
 */
package com.tcs.umsoid.web.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.util.StopWatch;

import com.tcs.umsoid.util.UtilConstants;
import com.tcs.umsoid.vo.util.UtilProperties;
/**
 * @author 585226
 *
 */
public class InitializeListener implements ServletContextListener  {
	
    private static final Logger LOGGER = Logger.getLogger("umsOIDLogger");

	/**
	 * Default constructor.
	 */
	public InitializeListener() {
		LOGGER.info("inside the InitializeListener constructor");
	}


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		LOGGER.info("inside the InitializeListener contextInitialized");
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		UtilProperties.load(UtilConstants.CONFIG_PATH);
		stopWatch.stop();
		LOGGER.info("Time taken to initialize : " + stopWatch.prettyPrint());
		
	}

}
