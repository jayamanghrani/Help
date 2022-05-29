package com.tcs.umsapp.unlock.invoker;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.umsapp.unlock.invoker.util.MailContentRequest;
import com.tcs.umsapp.unlock.util.UtilProperties;

/**
 * @author 1092382
 *
 */
public class MailComponentInvoker {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger
            .getLogger(MailComponentInvoker.class);

    public MailComponentInvoker() {

    }
    /**
     * Send mail service
     * @param to
     * @param userId
     * @param newPassword
     * @param userName
     * @return
     */
    public int sendMail(String to, String userId, String newPassword,
            String userName) {

        int successValue = 0;
        String mailBody = "Dear "
                + userName
                + ",\n\nKindly note that your password for the User ID "
                + userId
                + " is "
                + newPassword
                + ". Please change the password on your first login.\n\nWarm Regards,\nTeam NIA\n\n\n";
        MailContentRequest mailContentRequest = new MailContentRequest();
        mailContentRequest.setRecepient(to);
        mailContentRequest.setCc("");
        mailContentRequest.setSender(UtilProperties.getFromId());
        mailContentRequest.setSubject(UtilProperties.getEmailSubject());
        mailContentRequest.setBody(mailBody);

        LOGGER.debug("Mail body: " + mailContentRequest);

        URL url = null;
        HttpsURLConnection connection = null;
        try {

            url = new URL(UtilProperties.getEmailUrl());

            connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    connection.getOutputStream());
            ObjectMapper mapper = new ObjectMapper();
            String requestJson = mapper.writeValueAsString(mailContentRequest);
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
        } catch (Exception e) {
            LOGGER.info("Error :" + e.getMessage());
            successValue = 0;
        }
        return successValue;
    }

}
