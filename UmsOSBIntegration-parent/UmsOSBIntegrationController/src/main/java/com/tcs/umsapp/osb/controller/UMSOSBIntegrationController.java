package com.tcs.umsapp.osb.controller;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.umsapp.osb.common.ProvisionDetail;
import com.tcs.umsapp.osb.multithreading.FutureCallable;
import com.tcs.umsapp.osb.persist.dao.UMSOSBDaoImpl;
import com.tcs.umsapp.osb.persist.entities.ProvDetailEntity;
import com.tcs.umsapp.osb.persist.services.UMSOSBDao;
import com.tcs.umsapp.osb.request.OSBRequestSO;
import com.tcs.umsapp.osb.response.AcknowledgeRequestSO;
import com.tcs.umsapp.osb.util.UtilProperties;

public class UMSOSBIntegrationController {

    private static final Logger LOGGER = Logger
            .getLogger(UMSOSBIntegrationController.class);

    UMSOSBDao umsOsbDao;

    public UMSOSBIntegrationController() {
        super();
        umsOsbDao = new UMSOSBDaoImpl();
    }

    public void generateUpdateRequest() {

        LOGGER.info("Inside UMSOSBIntegrationController.generateRequest");
        int numberOfThreads;
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

    public void sendAcknowledgeRequest(OSBRequestSO osbRequestSO) {
        LOGGER.info("Inside UMSOSBIntegrationController.updateResponse : "
                + osbRequestSO.toString());

        AcknowledgeRequestSO acknowledgeRequestSO = new AcknowledgeRequestSO();
        acknowledgeRequestSO
                .setProvId(osbRequestSO.getProvisionId().toString());
        acknowledgeRequestSO.setProvDate(osbRequestSO.getProvisionDate());
        acknowledgeRequestSO.setRemark(osbRequestSO.getRemark());
        acknowledgeRequestSO.setSqlCode(osbRequestSO.getErrorDec());
        acknowledgeRequestSO.setSqlMessage(osbRequestSO.getErrorDec());
        acknowledgeRequestSO.setStatus(osbRequestSO.getProvisionStatus());
        LOGGER.info("Inside UMSOSBIntegrationController.updateResponse : "
                + acknowledgeRequestSO.toString());

        URL url = null;
        HttpsURLConnection connection = null;
        try {

            url = new URL(UtilProperties.getAckUrl());

            connection =(HttpsURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    connection.getOutputStream());
            ObjectMapper mapper = new ObjectMapper();
            String requestJson = mapper
                    .writeValueAsString(acknowledgeRequestSO);
            LOGGER.info("JSON Ack convert: " + requestJson);
            outputStreamWriter.write(requestJson);
            outputStreamWriter.flush();
            outputStreamWriter.close();

            if (connection.getResponseCode() == 200) {
                LOGGER.info("Response received successfully From Acknowledge Service ");
            } else {
                LOGGER.info("Connection failed to Acknowledge Service");
                throw new RuntimeException("Failed : HTTP error code : "
                        + connection.getResponseCode());
            }

        } catch (Exception e) {
            LOGGER.info("Error :" + e.getMessage());

        }
    }
	

}
