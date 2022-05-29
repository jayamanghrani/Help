package com.tcs.umsapp.smsmail.invoker;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import com.tcs.umsapp.smsmail.invoker.util.MessageSource;
import com.tcs.umsapp.smsmail.util.UtilProperties;

public class MessageComponentInvoker {
	/** The message source. */
	MessageSource messageSource=null;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(MessageComponentInvoker.class);
	
	/**
	 * Instantiates a new message component invoker.
	 */
	public  MessageComponentInvoker(String mobileNo,String msgBody){
		
		messageSource=new MessageSource();
		messageSource.setProxyHost(UtilProperties.getProxyHost());
		messageSource.setProxyServerPort(Integer.valueOf(UtilProperties.getPort()));
		messageSource.setUserId(UtilProperties.getUserId());
		messageSource.setPassword(UtilProperties.getPassword());
		messageSource.setMsgBody(msgBody);
		messageSource.setMobile(mobileNo);
		messageSource.setSmsSenderId(UtilProperties.getSmsSenderId());
		messageSource.setSmsProviderUrl(UtilProperties.getSmsProviderUrl());
	}
	/**
	 * Send message.
	 *
	 * @param messageData the message data
	 */
	public int sendMessage(){
		 HttpsURLConnection conn =null;
		 BufferedReader rd =  null;
		 int status = 0;
		 
		 LOGGER.info("Inside send message method of MessageComponentInvoker");
		try {
            String message= messageSource.getMsgBody();
            Date date = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String formattedDate= dateFormat.format(date);
            
            LOGGER.info("Message Souce :" + message);
            
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append("username=");
            stringBuilder.append(URLEncoder.encode(messageSource.getUserId(),"UTF-8"));
            stringBuilder.append("&password=");
            stringBuilder.append(URLEncoder.encode(messageSource.getPassword(),"UTF-8"));
            stringBuilder.append("&messageType=text");
            stringBuilder.append("&mobile=");
            stringBuilder.append(URLEncoder.encode(messageSource.getMobile(),"UTF-8"));
            stringBuilder.append("&senderId=");
            stringBuilder.append(URLEncoder.encode(messageSource.getSmsSenderId(),"UTF-8"));
            stringBuilder.append("&message=");
            stringBuilder.append(URLEncoder.encode(message+formattedDate,"UTF-8"));
                       
            LOGGER.debug("Message Souce builder:" + stringBuilder);
            
            String newData=stringBuilder.toString();
            URL url = new URL(null, messageSource.getSmsProviderUrl()+newData, new sun.net.www.protocol.https.Handler());
            LOGGER.debug("SMS url......... : "+url);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(messageSource.getProxyHost(), messageSource.getProxyServerPort()));
             try {
				conn = (HttpsURLConnection)url.openConnection(proxy);
            conn.setRequestMethod("GET"); 
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);          
            conn.connect();
             } catch (Exception e) {
                 status = 1; 
                 LOGGER.error("Error in SMS connection -- "+e.getStackTrace());
             }
            LOGGER.info("Before CONNECT");
             rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                    buffer.append(line).append("\n");
            }
            LOGGER.info("SMS Sent successfully to user");
            LOGGER.debug("SMS Sent successfully to: " + messageSource.getMobile());
           
            } catch (Exception e) {
            	status=1;
            	LOGGER.error("SMS exception : "+e.getStackTrace());
            }
		finally {
		
             try {
				rd.close();
			} catch (IOException e) {
				LOGGER.error("exception : "+e.getStackTrace());
			}
             conn.disconnect();
		}
		
		return status;
	}
}