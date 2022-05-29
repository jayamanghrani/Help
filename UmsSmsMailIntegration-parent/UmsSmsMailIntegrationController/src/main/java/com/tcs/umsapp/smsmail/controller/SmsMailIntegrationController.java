package com.tcs.umsapp.smsmail.controller;
import org.apache.log4j.Logger;

import com.tcs.umsapp.smsmail.invoker.MailComponentInvoker;
import com.tcs.umsapp.smsmail.invoker.MessageComponentInvoker;
import com.tcs.umsapp.smsmail.so.request.MailContentRequestSO;
import com.tcs.umsapp.smsmail.so.request.SmsContentRequestSO;
import com.tcs.umsapp.smsmail.so.response.MailDeliveryResponseSO;
import com.tcs.umsapp.smsmail.so.response.SmsDeliveryResponseSO;
import com.tcs.umsapp.smsmail.util.UtilProperties;

public class SmsMailIntegrationController {
    private static final Logger LOGGER = Logger
            .getLogger(SmsMailIntegrationController.class);

/**
 * 
 * @param mailContentRequestSO
 * @return
 */
    public MailDeliveryResponseSO sendMailController(
            MailContentRequestSO mailContentRequestSO) {
        int mailStatus = 1;
        if (mailContentRequestSO.getRecepient() != null) {

            MailComponentInvoker mailComponentInvoker = new MailComponentInvoker();
            mailStatus = mailComponentInvoker.sendMail(
                    mailContentRequestSO.getRecepient(),
                    mailContentRequestSO.getCc(),
                    mailContentRequestSO.getSubject(),
                    mailContentRequestSO.getBody(),
                    mailContentRequestSO.getSender());
        }

        LOGGER.info("Status of mail : " + mailStatus);
        MailDeliveryResponseSO deliveryResponseSO = new MailDeliveryResponseSO();
        if (mailStatus == 0) {
            deliveryResponseSO.setResponse(UtilProperties.getMailSuccessMessage());
            deliveryResponseSO.setStatus(mailStatus);
        } else {
            deliveryResponseSO.setResponse(UtilProperties.getFailureMessage());
            deliveryResponseSO.setStatus(mailStatus);
        }
        LOGGER.info("Response :" + deliveryResponseSO);
        return deliveryResponseSO;
    }
    
    /**
     * 
     * @param smsContentRequestSO
     * @return
     */
    public SmsDeliveryResponseSO sendSmsController(
            SmsContentRequestSO smsContentRequestSO) {
        LOGGER.info("Inside sendSmsController");
        int smsStatus = 1;
        if (smsContentRequestSO.getMobileNumber() != null
                && !smsContentRequestSO.getMobileNumber().isEmpty()) {
            MessageComponentInvoker messageComponentInvoker = new MessageComponentInvoker(
                    smsContentRequestSO.getMobileNumber(),
                    smsContentRequestSO.getBody());
            smsStatus = messageComponentInvoker.sendMessage();
        }
        LOGGER.info("Status of sms : " + smsStatus);
        SmsDeliveryResponseSO deliveryResponseSO = new SmsDeliveryResponseSO();
        if (smsStatus == 0) {
            deliveryResponseSO.setResponse(UtilProperties.getSmsSuccess());
            deliveryResponseSO.setStatus(smsStatus);
        } else {
            deliveryResponseSO.setResponse(UtilProperties.getSmsFailed());
            deliveryResponseSO.setStatus(smsStatus);
        }
        LOGGER.info("Response :" + deliveryResponseSO);
        return deliveryResponseSO;
    }
}