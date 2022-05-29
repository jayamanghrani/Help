/**
 * 
 */
package com.tcs.employeeportal.service.ticker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import com.tcs.employeeportal.cache.model.TickerValues;
import com.tcs.employeeportal.db.asbo.request.TickerDBRequestASBO;
import com.tcs.employeeportal.persist.service.CachingDBService;
import com.tcs.employeeportal.persist.service.CachingDBServiceImpl;

/**
 * @author 585226
 *
 */
public  class TickerCacheService {
	
	
public static  ConcurrentMap<String, List<String>> cacheingGenerinc =new  ConcurrentHashMap<String, List<String>>();
static Timer timer = null;
static long delay = 5000; //1800000 1000*60
static long refreshInerval = 1800000; 
List<String> tickerValuesList = null;
static TimerTask task = new TimerTask() {
      @Override
      public void run() {
        // task to run goes here
    	  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	  Date date = new Date();
    	  cacheingGenerinc = new ConcurrentHashMap<String, List<String>>();
        LOGGER.info("The caching in renewed for ticker !!!"+dateFormat.format(date));

      }
    };
    
    static {
    	   timer = new Timer();
		    System.out.println("TickerCacheService static block is executed");
		 
		    long intevalPeriod = 1 * 1000; 
		    // schedules the task to be run in an interval 
		  //  timer.scheduleAtFixedRate(task,delay,intevalPeriod);
		    timer.schedule(task, delay,refreshInerval);
    }
   
/* public TickerCacheService(){
	 cacheingGenerinc =new  ConcurrentHashMap<String, List<String>>();
 }*/
	private static final Logger LOGGER = Logger.getLogger("empPortalLogger");
	public  ConcurrentMap<String, List<String>> cacheTickerValues(TickerDBRequestASBO tdbrasbo ) {
		
		// TODO Auto-generated method stub
		CachingDBService cachingDbService = new CachingDBServiceImpl();
		//TickerDBRequestASBO tdbrasbo = null;
		LOGGER.info("inside the ticker cache service call");
		
		/// Below code to set the cache and so that it is refreshed
		
		TickerValues tv = new TickerValues();
		
		    
		
		    
		  /// Above code to refresh the code
		
		// check if the list is null in the hashmap if the list is not null then take values from db else tale from hashmap
		
		if(cacheingGenerinc.get(tdbrasbo.getTickerinput())!= null){ 
			LOGGER.debug("it should come here");
			tickerValuesList = cacheingGenerinc.get(tdbrasbo.getTickerinput());
			tv.setDailyPremium(tickerValuesList.get(0));
			tv.setDatevalue(tickerValuesList.get(1));
			tv.setMonthlyPremium(tickerValuesList.get(2));
			tv.setUptoPremium(tickerValuesList.get(3));
			
			LOGGER.debug("THE Database call was not done");
			return cacheingGenerinc;
		}
		
		else{
			try {
				tickerValuesList = cachingDbService.getTickerValues(null);
				
				LOGGER.debug("THE Database call was done");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			LOGGER.info("some issue in implementing caching");
		if (tickerValuesList!= null){
		  cacheingGenerinc = new ConcurrentHashMap<>();
		 cacheingGenerinc.put(tdbrasbo.getTickerinput(), tickerValuesList);
		}
		else {
		try {
			
			tv.setDailyPremium(tickerValuesList.get(0));
			tv.setDatevalue(tickerValuesList.get(1));
			tv.setMonthlyPremium(tickerValuesList.get(2));
			tv.setUptoPremium(tickerValuesList.get(3));
		
			 cacheingGenerinc.put(tdbrasbo.getTickerinput(), tickerValuesList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return cacheingGenerinc;
		}
		
/*		for(Map.Entry<TickerValues, List<TickerValues>> entry : makeListMap.entrySet()){
			Cache.put(entry.getKey(), entry.getValue());
			LOGGER.debug(entry.getKey().toString() + " caching completed. Cached object "+ entry.getValue().toString());
		}
		LOGGER.info("cached the ticker values list");
		
		*/

		
		
	}
	
	    

}