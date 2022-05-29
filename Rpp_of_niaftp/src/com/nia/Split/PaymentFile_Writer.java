package com.nia.Split;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.lang.StringUtils;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;



public class PaymentFile_Writer {
	
	protected static final Logger logger = Logger.getLogger( PaymentFile_Writer.class );
	private String New_Splitted_paymentFileName;
    private String Required_payment_file_pathname;
	private static PaymentFile_Writer instance;
	
	public static synchronized PaymentFile_Writer getInstance() throws JAXBException
	{
		if (instance == null)
		{
			instance = new PaymentFile_Writer();
		}
		return instance;
	}

	public  void File_Writer(String path, String fileprefix_new,double nof, int nol,int seq_no,String newExtension_name) throws IOException {
		
		

   	     FileInputStream fstream = new FileInputStream(path);
        DataInputStream in = new DataInputStream(fstream);   
   
        
        BufferedReader br = new BufferedReader(new InputStreamReader(in)); 
        
        String strLine; 
        int number =0;
        for (int j=1;j<=nof;j++)  
        {  
        
        	  String Value =Integer.toString(j+ seq_no);
        	  
        	  String seq_Value= StringUtils.leftPad(Value,6, "0");
        	  logger.info("value of seq_number" +seq_Value);
        	  New_Splitted_paymentFileName=fileprefix_new+"-"+seq_Value+"."+newExtension_name; 
        	  logger.info("New Splitted File name  is ---"  +New_Splitted_paymentFileName);
    
     	 File file = new File(path);
  	     String  payment_file_pathname= file.getParent();
  	     
  	  
  	       Required_payment_file_pathname= payment_file_pathname+"/";
  	        
		FileWriter fstream1 = new FileWriter(Required_payment_file_pathname +New_Splitted_paymentFileName); // Destination File Location  
    
		BufferedWriter out = new BufferedWriter(fstream1);   
       
       for (int i=1;i<=nol ;i++)  
       {  
        strLine = br.readLine();   
        if (strLine!= null)  
        {  
         out.write(strLine);   
         if(i!=nol)  
         {  
      	   number++;
          out.newLine();  
         }  
        }  
       }  
       out.close();  
      }  
   in.close();  
  
	}

	public void newFile_Writer(String path, String fileprefix_new, double nof,
			int nol, String newExtension_name) throws IOException {		 
		   	 FileInputStream fstream = new FileInputStream(path);
		        DataInputStream in = new DataInputStream(fstream);   
		        BufferedReader br = new BufferedReader(new InputStreamReader(in)); 
		        
		        String strLine; 
		        int number =0;
		        for (int j=1;j<=nof;j++)  
		        {  
		        	    
		        	  
		        	  String New_Value =Integer.toString(j);
		        	  
		        	  String new_seq_Value= StringUtils.leftPad(New_Value,6, "0");
		        	    logger.info("value of new_seq_Value ---" +  new_seq_Value);
		     
		        	  New_Splitted_paymentFileName=fileprefix_new+"-"+new_seq_Value+"."+newExtension_name;  
		        	    
		        	   File file = new File(path);
		  	           String  payment_file_pathname= file.getParent();
		  	     
		  	  
		  	     Required_payment_file_pathname= payment_file_pathname+"/";
		  	     	
				FileWriter fstream1 = new FileWriter(Required_payment_file_pathname +New_Splitted_paymentFileName); // Destination File Location  
		    
				BufferedWriter out = new BufferedWriter(fstream1);   
		       
		       for (int i=1;i<=nol ;i++)  
		       {  
		        strLine = br.readLine();   
		        if (strLine!= null)  
		        {  
		         out.write(strLine);   
		         if(i!=nol)  
		         {  
		      	   number++;
		          out.newLine();  
		         }  
		        }  
		       }  
		       out.close();  
		      }  
		   in.close();  
		
	}

}
