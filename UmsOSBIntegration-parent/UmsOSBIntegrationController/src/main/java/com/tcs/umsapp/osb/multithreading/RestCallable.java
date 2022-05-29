package com.tcs.umsapp.osb.multithreading;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.umsapp.osb.common.ProvisionDetail;
import com.tcs.umsapp.osb.persist.dao.UMSOSBDaoImpl;
import com.tcs.umsapp.osb.persist.entities.ProvDetailEntity;
import com.tcs.umsapp.osb.persist.services.UMSOSBDao;
import com.tcs.umsapp.osb.util.UtilProperties;

public class RestCallable implements
        Callable<ConcurrentHashMap<Number, ProvDetailEntity>> {

    private static Logger LOGGER = Logger.getLogger(RestCallable.class);

    private Entry<Number, ProvisionDetail> hashMapList = null;
    private ConcurrentHashMap<Number, ProvDetailEntity> resultList = new ConcurrentHashMap<Number, ProvDetailEntity>();
    
    
    public RestCallable(Entry<Number, ProvisionDetail> entry) {
        this.hashMapList = entry;
    }

    @Override
    public ConcurrentHashMap<Number, ProvDetailEntity> call() throws Exception {
//    	LOGGER.info("-------------------------------------------  "  + Thread.currentThread().getName()   + "        "   +  this.hashMapList.getValue().getProvisionId());

        URL url = null;
        HttpURLConnection conn = null;

 
            if (Thread.currentThread().isInterrupted()) {
                LOGGER.info("Interrupted for termination ");
            }
	            try {
	                url = new URL(UtilProperties.getOsbUrl());
	                conn = (HttpURLConnection) url.openConnection();
	                conn.setDoOutput(true);
	                conn.setRequestMethod("POST");
	                conn.setRequestProperty("Content-Type", "application/json");
	                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
	                        conn.getOutputStream());
	                ObjectMapper mapper = new ObjectMapper();
	                String requestJSON = mapper
	                        .writeValueAsString(this.hashMapList.getValue());
	                LOGGER.info("Sending : ProvID : "
	                        + this.hashMapList.getValue().getProvisionId() + "  App : "
	                        + this.hashMapList.getValue().getApplicationId());
	                LOGGER.info("-------------------------------------------------");
	                outputStreamWriter.write(requestJSON);
	                outputStreamWriter.flush();
	                outputStreamWriter.close();
	
	                if (conn.getResponseCode() == 200) {
	                    ProvDetailEntity provDetail = new ProvDetailEntity();
	                    provDetail.setProvisionId(Long.valueOf(this.hashMapList.getValue()
	                            .getProvisionId()));
	                    provDetail.setProvisionStatus(this.hashMapList.getValue().getStatus());
	                    resultList.putIfAbsent(this.hashMapList.getKey(), provDetail);
	                } else {
	                    LOGGER.info("Connection to OSB failed for Prov ID: "+ this.hashMapList.getValue().getProvisionId() +"   App : "
	                        + this.hashMapList.getValue().getApplicationId()  + "    Failed : HTTP error code : "
	                            + conn.getResponseCode());
	                    UMSOSBDao umsosbDao =new UMSOSBDaoImpl();
		            	umsosbDao.revertIntermediateStatus(Long.valueOf(this.hashMapList.getValue()
		                        .getProvisionId())); 
	                }
	
	            } catch (Exception e) {

	                LOGGER.info("Exception occured in Thread call execution :"
	                        + e.getMessage());
	                LOGGER.error(e.getStackTrace(),e);
	            } finally {
	                conn.disconnect();
	            }

        return resultList;
    }
}