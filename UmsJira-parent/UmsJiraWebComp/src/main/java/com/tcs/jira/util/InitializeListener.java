/**
 * 
 */
package com.tcs.jira.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;
import com.tcs.jira.vo.util.UtilConstants;
import com.tcs.jira.vo.util.UtilProperties;

/**
 * @author 585226
 *
 */
public class InitializeListener implements ServletContextListener {

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
    }

}
