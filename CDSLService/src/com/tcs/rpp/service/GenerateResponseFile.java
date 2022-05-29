package com.tcs.rpp.service;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import com.tcs.rpp.bean.request.FileUploadStatusRequest;
import com.tcs.rpp.bean.response.RPPResponse;
import com.tcs.rpp.bean.response.TransactionResponse;
import com.tcs.rpp.util.Log;
import com.tcs.rpp.util.PropertiesUtil;
import com.tcs.rpp.util.UtilConstants;
import com.tcs.rpp.util.UtilProperties;

public class GenerateResponseFile {
    static {
        UtilProperties.load(PropertiesUtil.getConfigProperty("configPath"));
    }

    public static void main(String[] args) throws IOException {
        boolean flag = false;
        try {
           
            FileUploadStatusRequest request = new FileUploadStatusRequest();
            request.setFileName(args[0]);

            request.setFilePath(UtilProperties.getReverseFilePath());
            Log.rpp_Log.info("getting response for file " + request.getFileName());
            Log.rpp_Log.info("run job manually");
            flag = getRPPPaymentResponse(request);
        }catch(Exception e)
        {
            Log.rpp_Log.info("run job atomatically");
            flag=processPaymentFile();
        }
        Log.rpp_Log.info("payment generated ---"+flag);
    }

    public static boolean processPaymentFile() {
        boolean flag = false;
        File[] files=getPayFiles();
        for (File payFile : files) {
            FileUploadStatusRequest request = new FileUploadStatusRequest();
            request.setFileName(payFile.getName());

            request.setFilePath(UtilProperties.getReverseFilePath());
            Log.rpp_Log.info("getting response for file " + payFile.getName());
            
            flag = getRPPPaymentResponse(request);
        }
        return flag;
    }

    public static File[] getPayFiles() {
        File dir = new File(UtilProperties.getPayFilePath());
        return dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains(UtilConstants.getDateFormat());
            }
        });
    }

    public static boolean getRPPPaymentResponse(FileUploadStatusRequest request) {
        RPPServiceIntegration serviceIntegration = new RPPServiceIntegration();
        RPPResponse uploadRes=serviceIntegration.checkFileUploadStatus(UtilConstants.rppUploadStatusUrl, request.getFileName());
        if(uploadRes.getStatus().equalsIgnoreCase("SUCCESS"))
        {
        TransactionResponse transactionResponse = serviceIntegration
                .getResponseFromRPP(UtilConstants.rppResponseUrl, request);
        String[] fileName = request.getFileName().split(UtilConstants.payFileExtention);
        String file = fileName[0].replaceFirst(UtilConstants.merchantCode, UtilConstants.rppName);
        return serviceIntegration.createResponseFile(transactionResponse, file,
                request.getFilePath());
        }else
        {
            UtilConstants.sendHtmlEmail(request.getFileName()+": "+uploadRes.getMessage());
            Log.rpp_Log.info("File: "+request.getFileName()+"  not exist in RPP Database");
            return false;
        }
    }
}