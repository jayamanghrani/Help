package com.tcs.umsapp.role.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
public class BatchUpdateForOIDUMS {
	
	private static final Logger LOGGER = Logger.getLogger(BatchUpdateForOIDUMS.class);

	private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    
    public void startExecutionAt(int targetHour, int targetMin, int targetSec)
    {
    	LOGGER.info("Runnable task scheduled");
        Runnable taskRunnable =()-> 
            {
            	try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					LOGGER.error(e.getStackTrace(),e);
				}
            	LOGGER.info("Inside Runnable task run method");
            	RoleIntegrationController roleController = new RoleIntegrationController();
            	roleController.getOIDAndUMSRequest();
                startExecutionAt(targetHour, targetMin, targetSec);
        };
        long delay = computeNextDelay(targetHour, targetMin, targetSec);
        LOGGER.info("at :    "+targetHour + ":" + targetMin +":" + targetSec);
        executorService.schedule(taskRunnable, delay, TimeUnit.MILLISECONDS);
    }
    
    private long computeNextDelay(int targetHour, int targetMin, int targetSec) 
    {
        LocalDateTime localNow = LocalDateTime.now();
        ZoneId currentZone = ZoneId.systemDefault();
        ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
        ZonedDateTime zonedNextTarget = zonedNow.withHour(targetHour).withMinute(targetMin).withSecond(targetSec);
        if(zonedNow.compareTo(zonedNextTarget) > 0)
            zonedNextTarget = zonedNextTarget.plusDays(1);

        Duration duration = Duration.between(zonedNow, zonedNextTarget);
        LOGGER.info(" duration ----------  :   " +duration);
        return duration.toMillis();
    }
    
    
}
