/**
 * 
 */
package com.tcs.alerts.sms.main;
import com.tcs.alerts.sms.util.MessageSource;
import com.tcs.alerts.sms.util.UtilProperties;

import java.io.*;
import java.util.Date;


/**
 * @author 738566
 *
 */
public class GetLogs {
	
	/** The message source. */
	static MessageSource messageSource=null;
	
	static int count=0;
	
	static Date date= new Date();
	
	static {
		UtilProperties.load();
	}

	/**
	 * Instantiates a new message component invoker.
	 */
	public GetLogs(){
		
	}

	 public static void main(String[] arg){
		
			System.out.println( " File reading started ");
			 Date date= new Date();
		      System.out.println("date"+date);
			SendSMS sendsms= new SendSMS();
			System.setProperty ("jsse.enableSNIExtension", "false");
				String output=null;
		          BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(UtilProperties.getFilePath()));
				
		              StringBuilder sb = new StringBuilder();
		              String line = br.readLine();

		              while (line != null) {
		                  sb.append(line);
		                  sb.append("\n");
		                  line = br.readLine();
		              }
		              output= sb.toString();
		          } 
				catch (IOException e) {
					e.printStackTrace();
				}finally {
		        	  try {
		  				if (br != null)br.close();
		  			} catch (IOException ex) {
		  				ex.printStackTrace();
		  			}
		          }
		          
		      System.out.println("output---"+output);
		       		
		          sendsms.sendMessage(date+"\n Error Report for "+UtilProperties.getServer()+"\n"+output);

		        }

	
}
