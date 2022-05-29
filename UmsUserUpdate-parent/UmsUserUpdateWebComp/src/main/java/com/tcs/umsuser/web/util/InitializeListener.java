/**
 * 
 */
package com.tcs.umsuser.web.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.util.StopWatch;

import com.tcs.umsuser.controller.UserTempBatchUpdate;
import com.tcs.umsuser.util.UtilConstants;
import com.tcs.umsuser.util.UtilProperties;

public class InitializeListener implements ServletContextListener {

    private static final Logger LOGGER = Logger
            .getLogger(InitializeListener.class);

    public InitializeListener() {
        LOGGER.info("UMSOSB InitializeListener Constructor Called");
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        LOGGER.info("UMSOSB InitializeListener Destructor Called");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        LOGGER.info("UMSOSB InitializeListener contextInitialized called");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        UtilProperties.load(UtilConstants.CONFIG_PATH);
        LOGGER.info("Starting batch role update --------------------------");
        UserTempBatchUpdate batchUpdate= new UserTempBatchUpdate();
        batchUpdate.startExecutionAt(UtilProperties.getHour_schedular(), UtilProperties.getMinute_schedular(), 00);

        LOGGER.info("Time taken to initialize : " + stopWatch.prettyPrint());

    }

}
