package com.nia.Split;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;



public class FileName_Spliter {
	private static File[] paymentFile_paths_Array;
	protected static final Logger logger = Logger.getLogger( FileName_Spliter.class );
  	boolean RenameStatus;
   	String strNewFileExtName;
	private String FileSerial_number;
	private static FileName_Spliter instance;
	
	Date sysdate=new Date();
	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
	String strSysDate=sdFormat.format(sysdate);
	
	public static synchronized FileName_Spliter getInstance() throws JAXBException
	{
		if (instance == null)
		{
			instance = new FileName_Spliter();
		}
		return instance;
	}
    
	public String Name_Spliter(String Path,String payment_filename, String merchant_Code, String newExtension_name) {
	
		logger.info(" Current payment File name is----   "   +payment_filename);
		logger.info(" merchant_Code --- is   "   +merchant_Code);
		logger.info(" newExtension_name-- is   "   +newExtension_name);
		logger.info(" Path-- is   "   +Path);
		String renameFileExtename= renameFileExtension(Path,payment_filename,merchant_Code,newExtension_name);
		
		logger.info(" renameFileExtename-- is   "   +renameFileExtename);
		
		return renameFileExtename;
     		
}

	

	private String renameFileExtension(String Path,String oldpayment_filename, String merchant_Code,
			String newExtension_name) {
	   	File oldfile = new File(Path);
	 
	 
		  if(!oldfile.exists())
		  {
		  System.out.println("File does not exist.");
		  System.exit(0);
		  }
		////“PNB-EMITRA-20170420-000001.PAY”
		  
		  if (oldfile.isDirectory())
	   		{ 
	   	       paymentFile_paths_Array =oldfile.listFiles();
	   	       
	   	       for(File f :paymentFile_paths_Array)
	   	         {
	   	    	System.out.println(" \n show Existing name---   " +f);
	   	    	 
	   	    	oldpayment_filename =f.getName();
	   	    	System.out.println(" UnSplitted Payment  file name " +oldpayment_filename);
	   	         
		int dotPos = oldpayment_filename.lastIndexOf(".");
		  String strExtension = oldpayment_filename.substring(dotPos + 1);
		  String strFilename = oldpayment_filename.substring(0, dotPos);
		  
		  logger.info(" strExtension-- is   "   +strExtension);
		  logger.info(" strFilename-- is   "   +strFilename);
		  
		   strNewFileExtName = strFilename + "." + newExtension_name;
		  logger.info(" strNewFileExtName-- is   "   +strNewFileExtName);
		  
		  File newfile = new File(strNewFileExtName);
		   RenameStatus = oldfile.renameTo(newfile);
	   	         }       
		  if(!RenameStatus)  {
			  logger.info("FileExtension hasn't been changed successfully.");
		  }
		  else {
			  logger.info("FileExtension has been changed successfully.");
		  }
	   	    }
		 
			return  strNewFileExtName;
}
}