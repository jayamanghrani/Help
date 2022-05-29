/**
 * 
 */
package com.tcs.umsrole.web.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.tcs.umsapp.role.controller.BatchUpdateForOIDUMS;
import com.tcs.umsrole.vo.util.UtilConstants;
import com.tcs.umsrole.vo.util.UtilProperties;

/**
 * @author 585226
 *
 */
public class InitializeListener implements ServletContextListener  {
	
    private static final Logger LOGGER = Logger
            .getLogger(InitializeListener.class);

	public InitializeListener() {
		LOGGER.info("Initializing role update --------------------------");
	}


	public void contextDestroyed(ServletContextEvent arg0) {

		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		LOGGER.info("Starting role update schedular for OID and UMS  --------------------------");
		UtilProperties.load(UtilConstants.CONFIG_PATH);
		BatchUpdateForOIDUMS batchUpdate = new BatchUpdateForOIDUMS();
		 LOGGER.info("Batch Time :   " + UtilProperties.getHour_schedular1() +":"+ UtilProperties.getMinute_schedular1()+":"+00);
		batchUpdate.startExecutionAt(UtilProperties.getHour_schedular1(), UtilProperties.getMinute_schedular1(), 00);
		
	}

}
