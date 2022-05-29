package com.nia.Split;

import java.io.File;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;



public class Delate_UnSeperated_PaymentFile {
	
	protected static final Logger logger = Logger.getLogger( Delate_UnSeperated_PaymentFile.class );

	private static Delate_UnSeperated_PaymentFile instance;
	
	public static synchronized Delate_UnSeperated_PaymentFile getInstance()
	{
		if (instance == null)
		{
			instance = new Delate_UnSeperated_PaymentFile();
		}
		return instance;
	}

	public boolean Remove_unSeparated_paymentFile(String path) {
		 boolean result5=false;
		 
		
		 File delete_file_name = new File (path);
		  logger.debug("delete_file_name is " + delete_file_name.getName());
       if(delete_file_name.exists())
	        {
    	   logger.debug("Unsplitted Payment file is  in Folder \n ");
	        	
	        	if(delete_file_name.delete())
	        	{
	        		  logger.debug("Unsplitted Payment file is deleted  from Folder \n ");
	        	
	        	result5= true;
	        	
	        	}
	        	else
	        	{
	        		  logger.debug(" Unsplitted Payment file is not deleted \n ");
	        	}
	        }
	        else{
	        	  logger.debug("Unsplitted Payment file is not in Folder \n ");
	        }
		
		return result5;
	}

}
