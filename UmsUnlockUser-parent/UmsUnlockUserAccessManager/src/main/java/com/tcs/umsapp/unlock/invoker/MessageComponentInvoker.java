package com.tcs.umsapp.unlock.invoker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.umsapp.unlock.invoker.util.MessageSource;
import com.tcs.umsapp.unlock.invoker.util.SmsContentRequest;
import com.tcs.umsapp.unlock.util.UtilProperties;

public class MessageComponentInvoker {

    MessageSource messageSource = null;

    private static final Logger LOGGER = Logger
            .getLogger(MessageComponentInvoker.class);

    public MessageComponentInvoker() {

    }
    /**
     * send message service
     * @param userId
     * @param password
     * @param mobileNo
     * @return
     */
    public int sendMessage(String userId, String password, String mobileNo) {

        int successValue = 0;
        String message = "Kindly note that your password for the User ID "
                + userId + " is " + password
                + ". Please change the password on your first login.";
        SmsContentRequest smsContentRequest = new SmsContentRequest();
        smsContentRequest.setMobileNumber(mobileNo);
        smsContentRequest.setBody(message);

        LOGGER.debug("Mail body: " + smsContentRequest);

        URL url = null;
        HttpsURLConnection connection = null;
        try {

            url = new URL(UtilProperties.getSmsUrl());

            connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    connection.getOutputStream());
            ObjectMapper mapper = new ObjectMapper();
            String requestJson = mapper.writeValueAsString(smsContentRequest);
            LOGGER.debug("JSON String convert: " + requestJson);
            outputStreamWriter.write(requestJson);
            outputStreamWriter.flush();
            outputStreamWriter.close();

            if (connection.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (connection.getInputStream())));
                String s = null;
                while ((s = br.readLine()) != null) {
                    LOGGER.info("Response From Mail Service: " + s);

                }
            }
            successValue = 1;
        }

        catch (Exception e) {
            LOGGER.info("Error :" + e.getMessage());
            successValue = 0;
        }

        return successValue;
    }

}
