package com.tcs.umsapp.osb.multithreading;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.tcs.umsapp.osb.common.ProvisionDetail;
import com.tcs.umsapp.osb.persist.dao.UMSOSBDaoImpl;
import com.tcs.umsapp.osb.persist.entities.ProvDetailEntity;
import com.tcs.umsapp.osb.persist.services.UMSOSBDao;

public class ScheduledTask {
	private static final Logger LOGGER = Logger.getLogger(ScheduledTask.class);
	
	
	public static void startBatchRoleUpdate(){
		UMSOSBDao umsOsbDao= new UMSOSBDaoImpl();
		
		 LOGGER.info("Inside batch role update method");
		 int numberOfThreads ;
		 long responseValue = 0L;
		 
	     ConcurrentHashMap<Number, ProvisionDetail> provisionMapList = umsOsbDao
	                .getProvisionDetails();

	        int inputDataCount = provisionMapList.size();
	        if (inputDataCount <= 500) {
	            numberOfThreads = 3;
	        } else
	            numberOfThreads = 7;

	        LOGGER.info("Map size: ------  " + inputDataCount);

	        LOGGER.info("Multithread processing starts with :  " + numberOfThreads
	                + "   threads");

	        
	        if(provisionMapList.size()>0){
	        	for(Entry<Number, ProvisionDetail>   probeList : provisionMapList.entrySet()){
	        		ProvisionDetail provDetail = probeList.getValue();
	        		ProvDetailEntity newProb = new ProvDetailEntity();
	        		newProb.setProvisionId(Long.parseLong(provDetail.getProvisionId()));
	        		umsOsbDao.updateIntermediateStatus(newProb);	
	    		}
	        	LOGGER.info("Now future call starts----------------");
	        FutureCallable futureCallable = new FutureCallable();
	        responseValue = futureCallable.executorCall(numberOfThreads, provisionMapList);
	        }else{
	        	LOGGER.info("No data to update ------------ ");
	        }
	        LOGGER.info("Returned in controller :" + responseValue);
	        
	}
}
