	package com.tcs.umsapp.osb.multithreading;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.tcs.umsapp.osb.common.ProvisionDetail;
import com.tcs.umsapp.osb.persist.dao.UMSOSBDaoImpl;
import com.tcs.umsapp.osb.persist.entities.ProvDetailEntity;
import com.tcs.umsapp.osb.persist.services.UMSOSBDao;


public class FutureCallable {

	static Logger LOGGER = Logger.getLogger(FutureCallable.class);
	
	UMSOSBDao umsOsbDao;
	public FutureCallable(){
		super();
		umsOsbDao = new UMSOSBDaoImpl();
	}
	
	public long executorCall(int numThread, ConcurrentHashMap<Number, ProvisionDetail> provisionDetailList) {
		
		long responseCount = 0;
		ConcurrentHashMap<Number, ProvDetailEntity> futureConcurrentHashMap= new ConcurrentHashMap<>();
		ExecutorService executor = Executors.newFixedThreadPool(numThread);

		List<Future<ConcurrentHashMap<Number, ProvDetailEntity>>> futureResultList = new ArrayList<Future<ConcurrentHashMap<Number, ProvDetailEntity>>>();
		
		if(provisionDetailList.size()>0){
			for (Entry<Number, ProvisionDetail> entry : provisionDetailList.entrySet()) {
				RestCallable worker = new RestCallable(entry);
				Future<ConcurrentHashMap<Number, ProvDetailEntity>> submit = executor
						.submit(worker);
				futureResultList.add(submit);
				responseCount++;
			}
		}
		
		LOGGER.debug("Executor Shutdown Method Called");
		executor.shutdown();
		
		try {
			LOGGER.debug("Executor awaitTermination Method Called");
			executor.awaitTermination(8000, TimeUnit.SECONDS);
			executor.shutdownNow();
		} catch (InterruptedException e) {
			LOGGER.info("Executor ShutdownNow Method executed ------------  Terminated all action");
			executor.shutdownNow();
			e.printStackTrace();
		}
		
		LOGGER.debug("Main thread waiting for get output :  futureResultList   " + futureResultList.size());
		for (Future<ConcurrentHashMap<Number, ProvDetailEntity>> future : futureResultList) {
			try {
				futureConcurrentHashMap = future.get();
			} catch (InterruptedException e) {
				LOGGER.error("InterruptedException occured" + e.getMessage());
				e.printStackTrace();
			} catch (ExecutionException e) {
				LOGGER.error("ExecutionException occured" + e.getMessage());
			}
			catch (Exception e) {
				LOGGER.error("Exception occured" + e.getMessage());
			}
		}
		LOGGER.info("Future task completed");
		
		
//		try {
//		if(futureConcurrentHashMap.size()>0){	
////			LOGGER.info("Now iteration starts ----- :" + futureResultList);
//		for(Future<ConcurrentHashMap<Number, ProvDetailEntity>> future : futureResultList){
//			Iterator<Entry<Number, ProvDetailEntity>> itrr;
//			
//				itrr = future.get().entrySet().iterator();
//				while (itrr.hasNext()) {
//					Map.Entry<Number, ProvDetailEntity> entry = (Map.Entry<Number, ProvDetailEntity>) itrr
//							.next();
//					//LOGGER.info(""+entry.getValue());
//					ProvDetailEntity provDetail = entry.getValue();
//					umsOsbDao.updateIntermediateStatus(provDetail);					
//					responseCount++;
//				}
//		}
//		}else{
//			LOGGER.info("No data present in future map: -----");
//		}
//		
//		}catch (InterruptedException e) {
//			LOGGER.info("Exception in Future Iteration for InterruptedException");
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			LOGGER.info("Exception in Future Iteration for ExecutionException");
//			e.printStackTrace();
//		}		
		return responseCount;
		
	}
	
}
