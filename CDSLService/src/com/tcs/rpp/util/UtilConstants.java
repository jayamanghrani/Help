/**
 * 
 */
package com.tcs.rpp.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

/**
 * @author 926814
 *
 */
public class UtilConstants {

    
    public static final String uploadPayFileUrl=UtilProperties.getUploadPayFileUrl();
    public static final String rppResponseUrl=UtilProperties.getRppResponseUrl();
    public static final String rppUploadStatusUrl=UtilProperties.getRppUploadStatusUrl();
    public static final String logPath=UtilProperties.getLoggerPath();
    public static final String proxyHost=UtilProperties.getProxyHost();
    public static final Integer proxyPort=Integer.parseInt(UtilProperties.getProxyPort());
    public static final String proxyType=UtilProperties.getProxyType();
    public static final String fileExtention=".csv";
    public static final String dateFormat="yyyyMMdd";
    public static final String payFileExtention=".PAY";
    public static final String merchantCode="PNB";
    public static final String rppName="RPP";
    
    public static final String[] headerFormat={"DISBURSEMENTREFNO",
                            "OUTCODE", "AMOUNT", "IFSC",
                            "BENEFICIARYACCOUNTNUMBER", "DUEDATE", "ACKSTATUS",
                            "ACKSTATUSDESCRIPTION", "TACKSTATUS",
                            "TACKSTATUSDESCRIPTION", "TACKBID", "RESPSTATUS",
                            "RESPSTATUSDESCRIPTION", "RESPBID", "ISNEFT",
                            "CREATEDAT", "FINAL_STATUS", "FINAL_REMARK"};
    public static String getAuthKey() throws IOException {
        String sso = UtilProperties.getSsoId();
        String secrete = UtilProperties.getSecreteKey();
        String authString = sso + ":" + secrete;
        return new String(Base64.encodeBase64(authString.getBytes()));
    }
    
    
    public static String encodeFileToBase64Binary(String filePath){
        
        File file = new File(filePath);
        byte[] encoded=null;
        try {
            encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            Log.rpp_Log.error(e);
        }
        return new String(encoded);
    }
    
    public static String getDateFormat() {
        Date today = new Date();
        DateFormat dateFormat = new SimpleDateFormat(UtilConstants.dateFormat);
        String date = dateFormat.format(today);
        String result = null;
        Date myDate;
        try {
            myDate = dateFormat.parse(date);
            Date oneDayBefore = new Date(myDate.getTime() - 2);
            result = dateFormat.format(oneDayBefore);

            Log.rpp_Log.info("Files response created for date format " + result);
        } catch (ParseException e) {
            Log.rpp_Log.error("error " + e);
        }
        return result;
    }
    
    public static boolean sendHtmlEmail(String message)  {
        boolean result=false;
        // sets SMTP server properties
        final String mail_from_final=UtilProperties.getMailFrom();
        final String password_final=UtilProperties.getPassword();
        Properties properties = new Properties();
        properties.put("mail.smtp.host", UtilProperties.getSmtpHost());
        properties.put("mail.smtp.port", UtilProperties.getSmtpPort());
        properties.put("mail.smtp.auth", UtilProperties.getSmtpAuth());
        properties.put("mail.smtp.starttls.enable", UtilProperties.getSslEnable());

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail_from_final, password_final);
            }
        };

        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(mail_from_final));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(UtilProperties.getReceipentList()) );
        msg.setSubject(UtilProperties.getMessageSubject());
        msg.setSentDate(new Date());
        // set plain text message
        msg.setContent(message, "text/plain");

        // sends the e-mail
        Transport.send(msg);
        result=true;

        } catch (AddressException e) {
            Log.rpp_Log.error("AddressException Exception"+e.getMessage(),e);

        } catch (MessagingException e) {
            Log.rpp_Log.error("MessagingException Exception"+e.getMessage(),e);
        }
        return result;
    }
 
    
}
