package com.nia.Split;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.nia.helper.SftpUtility;



public class PaymentFileCount {
	
	private static PaymentFileCount instance;
	
	protected static final Logger logger = Logger.getLogger( PaymentFileCount.class );
	
	private static File[] backup_paymentFile_paths_Array;
	private double Count;
	private String tempBackup_payment_filename;
	private String  PaymnetDate;
	private String[] arrayString;
	private String Payment_Filename;
	private int maxnumber;
	
	public static synchronized PaymentFileCount getInstance()
	{
		if (instance == null)
		{
			instance = new PaymentFileCount();
		}
		return instance;
	}



	public PaymentFileCount()
	{

	}
	
	/*Convert Date into String Date*/
	
	 Date sysdate=new Date();
	 SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
	 String CurrentSysDate=sdFormat.format(sysdate);
	
	 
	 
	 
	public int payment_record_Count(String Path) throws IOException {
		 int  count = 0;
	     int readChars = 0;
	    InputStream is = new BufferedInputStream (new FileInputStream(Path));
		    try {
		        byte[] c = new byte[1024];
		       
		        boolean empty = true;
		        while ((readChars = is.read(c)) != -1) {
		            empty = false;
		            for (int i = 0; i < readChars; ++i) {
		                if (c[i] == '\n') {
		                    ++count;
		                }
		            }
		        }
		        return (count == 0 && !empty) ? 1 : count;
		    } finally {
		        is.close();
		    }
	}

	public  int checkSequenceNumber(String payment_file_Bcakup_pathname)
	   {
		
	 	   File tempBackupfile = new File(payment_file_Bcakup_pathname);
	 	  ArrayList<Integer>Seqlist= new ArrayList<Integer>();

	 	 if (tempBackupfile.isDirectory())
	   		{ 
	   	       backup_paymentFile_paths_Array =tempBackupfile.listFiles();
	   	       
	   	       for(File fname :backup_paymentFile_paths_Array)
	   	         {
	   	   
	   	               Payment_Filename=fname.getName();
	   	    	PaymentFile paymnetfileobj=checkPaymentfiledetails(Payment_Filename);
	   	    
	   	    	 
						     if((paymnetfileobj.getPaymnetDate()).equalsIgnoreCase(CurrentSysDate)){
								logger.info("inside CurrentDate Check condition, if Current date is equal to Paymnet date");
			   	    	        	 
			   	    	        	  String  SequenceNumeber =paymnetfileobj.getSequenceNumeber();
			   	    	            	logger.info("value of SequenceNumeber in String ++++++++--"+SequenceNumeber);
			   	    	              int seqNumber=Integer.parseInt(SequenceNumeber);				   	    	                 
			   	    	              Seqlist.add(seqNumber);  
			   	    	           logger.info("value of inside Sequence List --"+Seqlist.toString());  
			   	    	 
	                          }
	   	         
						    
	                
	          }
	   	         maxnumber= getMaxSequenceNumber(Seqlist);
	   	       }
	 	    else{
	 	    	logger.info("No file present in Folder");
 	            }
	 	return maxnumber;
	   }

	      public PaymentFile checkPaymentfiledetails(String filename) {
	    	  PaymentFile paymnetfiledetails =new PaymentFile();
	    	
  	    	      tempBackup_payment_filename=filename;
  	    	    int dotPos = tempBackup_payment_filename.lastIndexOf(".");
 		        String strExtension = tempBackup_payment_filename.substring(dotPos + 1);
 		        
 		           String tempBackup_payment_filename_Prefix = tempBackup_payment_filename.substring(0, dotPos);
 		              
  	    	         arrayString = tempBackup_payment_filename_Prefix.split("-");
  	    	         
  	    	          String  Bankfilename = arrayString[0];
  	    	          String  MerchantCode = arrayString[1];
  	    	          String  getPaymnetDate = arrayString[2];  
  	    	          String  SequenceNumeber = arrayString[3];  
	    	            	  paymnetfiledetails.setBnakfilename(Bankfilename);
	    	            	  paymnetfiledetails.setMerchantCode(MerchantCode);
	    	            	  paymnetfiledetails.setPaymnetDate(getPaymnetDate);
	    	            	  paymnetfiledetails.setSequenceNumeber(SequenceNumeber);
  	    	            
		     return paymnetfiledetails;
	}



		private int getMaxSequenceNumber(ArrayList<Integer> seqlist) 
	         {
	    	          int maxvalue =Collections.max(seqlist);
	    	          logger.info("maxvale--"+maxvalue);
	    	          
		     return maxvalue;
         	}
	
}
