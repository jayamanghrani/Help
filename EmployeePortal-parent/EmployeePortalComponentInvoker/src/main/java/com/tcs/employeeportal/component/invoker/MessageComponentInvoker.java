/**
 * 
 */
package com.tcs.employeeportal.component.invoker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import com.tcs.employeeportal.component.util.MessageSource;
import com.tcs.employeeportal.config.utils.UtilProperties;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.message.asbo.request.MessageServiceRequestASBO;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

/**
 * @author 738566
 *
 */
public class MessageComponentInvoker {


	/** The message source. */
	MessageSource messageSource=null;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger("empPortalLogger");
	
	/**
	 * Instantiates a new message component invoker.
	 */
	public  MessageComponentInvoker(){
		
		messageSource=new MessageSource();
		messageSource.setProxyHost(UtilProperties.getPROXY_HOST());
		messageSource.setProxyPassword(UtilProperties.getPROXY_PASSWORD());
		messageSource.setProxyServerPort(Integer.valueOf(UtilProperties.getPROXY_SERVER_PORT()));
		messageSource.setProxyUser(UtilProperties.getPROXY_USER());
		messageSource.setUserId(UtilProperties.getUSER_ID());
		messageSource.setPassword(UtilProperties.getPASSWORD());
		messageSource.setEncodeFormat(UtilProperties.getENCODE_FORMAT());
		messageSource.setVersion(UtilProperties.getVERSION());
		messageSource.setContentType("TEXT");
		messageSource.setScheme("PLAIN");
		messageSource.setSmsProviderUrl(UtilProperties.getSMS_PROVIDER_URL());
		
	}

	/**
	 * Invoke message service.
	 *
	 * @param requestVO the request vo
	 * @return the employee portal response object
	 * @throws IntegrationTechnicalException the integration technical exception
	 */
	
	public EmployeePortalResponseObject invokeMessageService(
			EmployeePortalRequestObject requestVO)
			throws IntegrationTechnicalException {
	
		MessageServiceRequestASBO messageServiceRequestASBO = null;
		Map<String,String> dataMap = null;
		
		if(null != requestVO && requestVO instanceof MessageServiceRequestASBO) {
			messageServiceRequestASBO=(MessageServiceRequestASBO)requestVO;
			
			dataMap=messageServiceRequestASBO.getData();
			if (dataMap.containsKey("messageType")) {
			
				switch (dataMap.get("messageType")) {

					case "FORGOTPASSWORD":
						this.sendMessage(dataMap);
						break;		
	
					case "FORGOTPASSWORDMSG":
						this.sendMessage(dataMap);
						break;
		
				}
			}
		}
		
		return null;
	}
		
		
	

	/**
	 * Send message.
	 *
	 * @param messageData the message data
	 */
	public void sendMessage(Map<String,String> messageData){
		 HttpURLConnection conn =null;
		 BufferedReader rd =  null;
	
		try {
            String dataURL="";
            String data = "";        

            String message=messageData.get("message");
            String date=messageData.get("date");
            String mobileNo=messageData.get("mobileNo");
           
            
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append("method=sendMessage");
            stringBuilder.append("&userid=");
            stringBuilder.append(URLEncoder.encode(messageSource.getUserId(),"UTF-8"));
            stringBuilder.append("&password=");
            stringBuilder.append(URLEncoder.encode(messageSource.getPassword(),"UTF-8"));
            stringBuilder.append("&msg=");
            stringBuilder.append(URLEncoder.encode(message+date,"UTF-8"));
            stringBuilder.append("&send_to=");
            stringBuilder.append(URLEncoder.encode(mobileNo,"UTF-8"));
            stringBuilder.append("&v=1.1");
            stringBuilder.append("&msg_type=TEXT");
            stringBuilder.append("&auth_scheme=PLAIN");
            dataURL=stringBuilder.toString();
      
            
          //  String newData=URLEncoder.encode(dataURL,"UTF-8"); 
            
            String newData=dataURL;
            URL url = new URL(messageSource.getSmsProviderUrl()+newData); 
            LOGGER.debug("SMS url......... : "+url);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(messageSource.getProxyHost(), messageSource.getProxyServerPort()));
             try {
				conn = (HttpURLConnection)url.openConnection(proxy);
			} catch (Exception e) {
				
				LOGGER.error("Error in SMS connection -- "+e.getStackTrace());
			}
            String encoding = new String(Base64.encodeBase64(new String(messageSource.getProxyUser()+":"+messageSource.getProxyPassword()).getBytes()));
             //  String encoding = new String(messageSource.getProxyUser()+":"+messageSource.getProxyPassword());
            conn.setRequestProperty("Proxy-Authorization", "Basic" + encoding);
            conn.setRequestMethod("GET"); 
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);          
            conn.connect();
            LOGGER.debug("Before CONNECT");
             rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                    buffer.append(line).append("\n");
            }
            LOGGER.info("SMS Sent successfully to: " + mobileNo);
           
            } catch (Exception e) {
            	e.printStackTrace();
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
	}
	
}
