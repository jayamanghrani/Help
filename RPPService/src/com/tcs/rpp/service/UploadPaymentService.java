/**
 * 
 */
package com.tcs.rpp.service;
import java.io.IOException;

import com.tcs.rpp.bean.request.FileUploadRequest;
import com.tcs.rpp.bean.response.RPPResponse;
import com.tcs.rpp.util.Log;
import com.tcs.rpp.util.PropertiesUtil;
import com.tcs.rpp.util.UtilConstants;
import com.tcs.rpp.util.UtilProperties;

/**
 * @author 926814
 *
 */
public class UploadPaymentService {
    static{
      UtilProperties.load(PropertiesUtil
              .getConfigProperty("configPath"));
    }
    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
       try{
        Log.rpp_Log.info("BankCode " +args[0] +" Merchantcode "+ args[1]+" filepath "+args[2]+" File Name"+args[3]+" Account number" +args[4]+"total transaction"+args[5]);
        FileUploadRequest fileUploadRequest=new FileUploadRequest();
        fileUploadRequest.setBankCode(args[0]);
        fileUploadRequest.setMerchantCode(args[1]);
        fileUploadRequest.setFileContent(UtilConstants.encodeFileToBase64Binary(args[2]+"/"+args[3]));
        fileUploadRequest.setFileName(args[3]);
        fileUploadRequest.setRemitterAccountNumber(args[4]);
        fileUploadRequest.setTotalTransactionCount(args[5]);
        RPPResponse response=uploadPaymentFile(fileUploadRequest);
        Log.rpp_Log.info(response.getStatus());
       }catch(Exception e)
       {
           Log.rpp_Log.error(e.getStackTrace(),e);
       }
    }  

    public static RPPResponse uploadPaymentFile(FileUploadRequest fileUploadRequest) throws IOException
    {
       RPPServiceIntegration serviceIntegration=new RPPServiceIntegration();
       RPPResponse transactionResponse=null;
       transactionResponse=serviceIntegration.uploadFileToRPP(UtilConstants.uploadPayFileUrl, fileUploadRequest);
        return transactionResponse;
    }
}
