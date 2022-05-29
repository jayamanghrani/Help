
package com.tcs.umsapp.web.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.util.StopWatch;
import com.tcs.umsapp.osb.multithreading.ScheduleBatchUpdate;
import com.tcs.umsapp.osb.util.UtilProperties;
import com.tcs.umsapp.util.UtilConstants;

public class InitializeListener implements ServletContextListener {

    private static final Logger LOGGER = Logger
            .getLogger(InitializeListener.class);

    public InitializeListener() {
        LOGGER.info("USMOSB InitializeListener Constructor Called");
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        LOGGER.info("USMOSB InitializeListener Destructor Called");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        LOGGER.info("USMOSB InitializeListener contextInitialized called");

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        UtilProperties.load(UtilConstants.CONFIG_PATH);
        LOGGER.info("Starting batch role update --------------------------");
        ScheduleBatchUpdate scheduleBatchUpdate= new ScheduleBatchUpdate();
        LOGGER.info("First Batch Time :   " + UtilProperties.getHour_schedular1() +":"+ UtilProperties.getMinute_schedular1()+":"+00);
        scheduleBatchUpdate.startExecutionAt(UtilProperties.getHour_schedular1(), UtilProperties.getMinute_schedular1(), 00);
    	LOGGER.info("Second Batch Time :  " + UtilProperties.getHour_schedular2() +":"+ UtilProperties.getMinute_schedular2()+":"+00);
    	scheduleBatchUpdate.startExecutionAt(UtilProperties.getHour_schedular2(), UtilProperties.getMinute_schedular2(), 00);
        
    	
        stopWatch.stop();
        LOGGER.info("Time taken to initialize : " + stopWatch.prettyPrint());

    }

}
