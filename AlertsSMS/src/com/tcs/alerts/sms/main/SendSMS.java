/**
 * 
 */
package com.tcs.alerts.sms.main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.tcs.alerts.sms.util.UtilProperties;

/**
 * @author 738566
 *
 */
public class SendSMS {
	
	static HttpsURLConnection connection = null;

	static {
		UtilProperties.load();
	}
	
  	/**
	 * Send message.
	 *
	 * @param messageData the message data
	 */
  
	
	public static class DummyTrustManager implements X509TrustManager {

		public DummyTrustManager() {
		}

		public boolean isClientTrusted(X509Certificate cert[]) {
			return true;
		}

		public boolean isServerTrusted(X509Certificate cert[]) {
			return true;
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[0];
		}

		public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

		}

		public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

		}
	}
	public static class DummyHostnameVerifier implements HostnameVerifier {

		public boolean verify( String urlHostname, String certHostname ) {
			return true;
		}

		public boolean verify(String arg0, SSLSession arg1) {
			return true;
		}
	}
	
	
  public void sendMessage(String messageData){

			try {
				System.out.println(UtilProperties.getSms_provider_url());
				System.setProperty("https.proxyHost", UtilProperties.getProxyHost());
				System.setProperty("https.proxyPort", UtilProperties.getProxyServerPort());
				System.setProperty ("UseSunHttpHandler","true");
				sendSingleUnicodeSMS(UtilProperties.getUserid(), UtilProperties.getPassword(), UtilProperties.getSender(), UtilProperties.getRecipients(), messageData);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				//connection.disconnect();
			}
  }
  
	public static void sendSingleUnicodeSMS(String username,String password, String senderId,String mobileNo, String message)
	{
		try {
			SSLContext sslcontext = null;
			try {
				sslcontext = SSLContext.getInstance("TLSv1.2");
	        	sslcontext.init(new KeyManager[0],
						new TrustManager[] { new DummyTrustManager() },
						new SecureRandom());
			} catch (NoSuchAlgorithmException e) {
			
				e.printStackTrace();
				
			} catch (KeyManagementException e) {
				e.printStackTrace();
			}
			
			SSLSocketFactory factory = sslcontext.getSocketFactory();
			HttpsURLConnection connection = null;
			 BufferedReader rd =  null;
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append("username=");
            stringBuilder.append(URLEncoder.encode(username,"UTF-8"));
            stringBuilder.append("&password=");
            stringBuilder.append(URLEncoder.encode(password,"UTF-8"));
            stringBuilder.append("&messageType=text");
            stringBuilder.append("&mobile=");
            stringBuilder.append(URLEncoder.encode(mobileNo,"UTF-8"));
            stringBuilder.append("&senderId=");
            stringBuilder.append(URLEncoder.encode(senderId,"UTF-8"));
            stringBuilder.append("&message=");
            stringBuilder.append(URLEncoder.encode(message,"UTF-8"));
            String  dataURL=stringBuilder.toString();
  			URL url = new URL(null, UtilProperties.getSms_provider_url()+dataURL, new sun.net.www.protocol.https.Handler());
			System.out.println("SMS url......... : "+url);
            connection = (HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET"); 
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);     
            connection.setSSLSocketFactory(factory);
            connection.setHostnameVerifier(new DummyHostnameVerifier());
            connection.connect();
            System.out.println("Before CONNECT");
             rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder buffer = new StringBuilder();
            while ((line = rd.readLine()) != null) {
                    buffer.append(line).append("\n");
            }
            System.out.println("SMS : " + buffer.toString());
            System.out.println("SMS Sent successfully to: " + mobileNo);
			 rd.close();
			
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
}