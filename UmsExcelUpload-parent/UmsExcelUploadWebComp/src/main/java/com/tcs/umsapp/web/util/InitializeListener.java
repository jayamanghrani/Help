/**
 * 
 */
package com.tcs.umsapp.web.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.util.StopWatch;

import com.tcs.umsapp.upload.vo.util.UtilProperties;
import com.tcs.umsapp.upload.vo.util.UtilConstants;
/**
 * @author 1092382
 *
 */
public class InitializeListener implements ServletContextListener  {
	
	/**
	 * Default constructor.
	 */
	public InitializeListener() {
		System.out.println("UmsAppExcelUpload InitializeListener Constructor");
	}


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {	
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("UmsAppExcelUpload InitializeListener contextInitialized");
	     UtilProperties.load(UtilConstants.CONFIG_PATH);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		stopWatch.stop();
		System.err.println("Time taken to initialize : " + stopWatch.prettyPrint());
	}

}
