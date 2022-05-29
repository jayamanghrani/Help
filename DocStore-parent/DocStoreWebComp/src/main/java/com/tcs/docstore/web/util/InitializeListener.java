/**
 * 
 */
package com.tcs.docstore.web.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.util.StopWatch;

import com.tcs.docstore.config.utils.UtilProperties;
import com.tcs.docstore.util.UtilConstants;

/*import com.tcs.docstore.service.ticker.CacheService;*/
/**
 * @author 585226
 *
 */
public class InitializeListener implements ServletContextListener  {
	
	/*private CacheService cacheService;*/
	/*private UtilProperties utilProperties;*/
	/**
	 * Default constructor.
	 */
	
	/*static{
		UtilProperties.load(UtilConstants.CONFIG_PATH);
	}*/

	/**
	 * Default constructor.
	 */
	public InitializeListener() {

	}

	/**
	 * Context initialized.
	 *
	 * @param sce the sce
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		UtilProperties.load(UtilConstants.CONFIG_PATH);
		stopWatch.stop();
		System.out.println("Time taken to initialize : " + stopWatch.prettyPrint());
	}

	/**
	 * Context destroyed.
	 *
	 * @param sce the sce
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		/*cacheService = null;*/
	
	}

}
