package com.tcs.rpp.service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;
import com.tcs.rpp.bean.request.FileUploadRequest;
import com.tcs.rpp.bean.request.FileUploadStatusRequest;
import com.tcs.rpp.bean.response.RPPResponse;
import com.tcs.rpp.bean.response.TransactionDetails;
import com.tcs.rpp.bean.response.TransactionResponse;
import com.tcs.rpp.util.Log;
import com.tcs.rpp.util.UtilConstants;
/**
 * @author 926814
 *
 */
public class RPPServiceIntegration {

    public RPPResponse uploadFileToRPP(String url,FileUploadRequest fileUploadRequest){
       return uploadFile(url,fileUploadRequest);
    }
 
    RPPResponse uploadFile(String url,FileUploadRequest fileUploadRequest) {
        StringBuilder responseJson= new StringBuilder();
        Gson gson = new Gson();
        RPPResponse rppResponse=null;
        try
        {   
          Log.rpp_Log.info("file uploading to RPP-------------");
          HttpPost postRequest = null;
             
         List<NameValuePair> urlParameters = new ArrayList<>();
         urlParameters.add(new BasicNameValuePair("bankCode", fileUploadRequest.getBankCode()));
         urlParameters.add(new BasicNameValuePair("merchantCode", fileUploadRequest.getMerchantCode()));
         urlParameters.add(new BasicNameValuePair("fileName", fileUploadRequest.getFileName()));
         urlParameters.add(new BasicNameValuePair("fileContent",fileUploadRequest.getFileContent()));
         urlParameters.add(new BasicNameValuePair("remitterAccountNumber",fileUploadRequest.getRemitterAccountNumber()));
         urlParameters.add(new BasicNameValuePair("totalTransactionCount",fileUploadRequest.getTotalTransactionCount()));
         
         HttpHost proxy = new HttpHost(UtilConstants.proxyHost,UtilConstants.proxyPort,UtilConstants.proxyType);
         DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
         HttpClient  client = HttpClients.custom().setRoutePlanner(routePlanner).build();

         postRequest = new HttpPost(url);
         postRequest.addHeader("content-type", "application/x-www-form-urlencoded");
         postRequest.addHeader("Authorization", "Basic " + UtilConstants.getAuthKey());
         postRequest.setEntity(new UrlEncodedFormEntity(urlParameters));
         
         HttpResponse response = client.execute(postRequest);
         
         Log.rpp_Log.info("response :" + response.getStatusLine());

         BufferedReader in = new BufferedReader(new InputStreamReader(
                 response.getEntity().getContent()));
         String inputLine;
         while ((inputLine = in.readLine()) != null) {
             Log.rpp_Log.info(" -------------- "+inputLine);
             responseJson.append(inputLine);
         }
         in.close();
         rppResponse=gson.fromJson(responseJson.toString(), RPPResponse.class);
         
         if(rppResponse.getStatus().equalsIgnoreCase("FAILED"))
         {
             UtilConstants.sendHtmlEmail(rppResponse.getMessage());
         }
        }
        catch(Exception e)
        { 
            UtilConstants.sendHtmlEmail(e.getMessage());
            Log.rpp_Log.info("error");
        }
        
        return rppResponse;
    }
  
    public RPPResponse checkFileUploadStatus(String url,String fileName)
    {
        StringBuilder responseJson= new StringBuilder();
        Gson gson = new Gson();
        RPPResponse rppResponse=null;
       try
       {         
         HttpPost postRequest = null;
       
         List<NameValuePair> urlParameters = new ArrayList<>();
         urlParameters.add(new BasicNameValuePair("fileName", fileName));
         Log.rpp_Log.info("input String :" + urlParameters);

        HttpHost proxy = new HttpHost(UtilConstants.proxyHost,UtilConstants.proxyPort,UtilConstants.proxyType);
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        HttpClient  client = HttpClients.custom().setRoutePlanner(routePlanner).build();
        postRequest = new HttpPost(url);
        postRequest.addHeader("content-type", "application/x-www-form-urlencoded");
        postRequest.addHeader("Authorization", "Basic " + UtilConstants.getAuthKey());
        postRequest.setEntity(new UrlEncodedFormEntity(urlParameters));
        HttpResponse response = client.execute(postRequest);

        Log.rpp_Log.info("response :" + response);

        BufferedReader in = new BufferedReader(new InputStreamReader(
                response.getEntity().getContent()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            responseJson.append(inputLine);
        }
        in.close();
        rppResponse=gson.fromJson(responseJson.toString(), RPPResponse.class);
        
       }
       catch(Exception e)
       {
           Log.rpp_Log.info("error");
          
       }
       
       return rppResponse;
    }
    
    public TransactionResponse getResponseFromRPP(String url,FileUploadStatusRequest fileStatus)
    {
        StringBuilder responseJson= new StringBuilder();
        TransactionResponse transactionResponse=null;
        Gson gson = new Gson();
       try
       { 
         HttpPost postRequest = null;
         List<NameValuePair> urlParameters = new ArrayList<>();
         urlParameters.add(new BasicNameValuePair("fileName", fileStatus.getFileName()));
         urlParameters.add(new BasicNameValuePair("allRecords", "Y"));
      
        HttpHost proxy = new HttpHost(UtilConstants.proxyHost,UtilConstants.proxyPort,UtilConstants.proxyType);
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        HttpClient  client = HttpClients.custom().setRoutePlanner(routePlanner).build();

        postRequest = new HttpPost(url);
        postRequest.addHeader("content-type", "application/x-www-form-urlencoded");
        postRequest.addHeader("Authorization", "Basic " + UtilConstants.getAuthKey());
        postRequest.setEntity(new UrlEncodedFormEntity(urlParameters));
        HttpResponse response = client.execute(postRequest);

        Log.rpp_Log.info("response :" + response);
       
       

        BufferedReader in = new BufferedReader(new InputStreamReader(
                response.getEntity().getContent()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            responseJson.append(inputLine);
        }
        
        Log.rpp_Log.info("getStatusLine ---------------------------:" + response.getStatusLine());
        in.close();
        
        transactionResponse=gson.fromJson(responseJson.toString(), TransactionResponse.class);
       }
       catch(Exception e)
       {
           UtilConstants.sendHtmlEmail(e.getMessage());
           Log.rpp_Log.error("error",e);
       }
       return transactionResponse;
    }
    
    public boolean createResponseFile(TransactionResponse transactionResponse,String fileName,String reverseFilePath)
    {
        boolean flag=false;
        if(!transactionResponse.getData().isEmpty()){
        try{
        FileWriter out = new FileWriter(fileName+UtilConstants.fileExtention);
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.
                withHeader(UtilConstants.headerFormat))) {
             
            for (TransactionDetails transactionDetails1 : transactionResponse.getData()) {
                printer.printRecord(transactionDetails1.getDISBURSEMENTREFNO(),transactionDetails1.getOUTCODE(),
                        transactionDetails1.getAMOUNT(),transactionDetails1.getIFSC(),
                        transactionDetails1.getBENEFICIARYACCOUNTNUMBER(),transactionDetails1.getDUEDATE(),
                        transactionDetails1.getACKSTATUS(),transactionDetails1.getACKSTATUSDESCRIPTION(),
                        transactionDetails1.getTACKSTATUS(),transactionDetails1.getTACKSTATUSDESCRIPTION(),
                        transactionDetails1.getTACKBID(),transactionDetails1.getRESPSTATUS(),
                        transactionDetails1.getRESPSTATUSDESCRIPTION(),transactionDetails1.getRESPBID(),
                        transactionDetails1.getISNEFT(),transactionDetails1.getCREATEDAT(),
                        transactionDetails1.getFINAL_STATUS(),transactionDetails1.getFINAL_REMARK());
            }
            
           
        }
        File f = new File(fileName+UtilConstants.fileExtention);
        
        if(f.exists() && !f.isDirectory()) { 
            flag=true;
            
            boolean isFile=f.renameTo(new File(reverseFilePath+ f.getName()));
            
            Log.rpp_Log.info("response file created------------"+isFile);
        }
         
        
        }catch(Exception e)
        {
            flag=false;
            UtilConstants.sendHtmlEmail(e.getMessage());
            Log.rpp_Log.error("error",e);
        }
        }else
        {
            UtilConstants.sendHtmlEmail("File not uploaded to RPP");
            Log.rpp_Log.info("File not uploaded to RPP");
        }
     return flag;        
    }


}
