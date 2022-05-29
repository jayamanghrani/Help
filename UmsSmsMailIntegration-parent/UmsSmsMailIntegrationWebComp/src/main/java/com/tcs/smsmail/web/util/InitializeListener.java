/**
 * 
 */
package com.tcs.smsmail.web.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.util.StopWatch;

import com.tcs.umsapp.smsmail.util.UtilConstants;
import com.tcs.umsapp.smsmail.util.UtilProperties;

public class InitializeListener implements ServletContextListener {

    private static final Logger LOGGER = Logger
            .getLogger(InitializeListener.class);

    public InitializeListener() {
        LOGGER.info("smsmail InitializeListener Constructor Called");
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        LOGGER.info("smsmail InitializeListener Destructor Called");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {

        LOGGER.info("smsmail InitializeListener contextInitialized called");

        StopWatch stopWatch = new StopWatch();
      
        stopWatch.start();
        UtilProperties.load(UtilConstants.CONFIG_PATH);
        stopWatch.stop();
        LOGGER.info("Time taken to initialize : " + stopWatch.prettyPrint());

    }

}
