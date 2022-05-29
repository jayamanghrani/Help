package com.nia.Split;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import com.nia.jpa.dto.Sftp_fname_dto;



public class PaymentFileCopy {
 
	private static PaymentFileCopy instance;
	protected static final Logger logger = Logger.getLogger( PaymentFileCopy.class );
	
	public static synchronized PaymentFileCopy getInstance()
	{
		if (instance == null)
		{
			instance = new PaymentFileCopy();
		}
		return instance;
	}


	public PaymentFileCopy()
	{

	}


/*	public boolean CopyFileFromOutFolder(String fileName,String processdLocation) {
		String SourcePath=fileName;
		String processdFileLocation=processdLocation;
		 boolean result = false;
		String cmd ="cp "+ SourcePath+" " +processdFileLocation; 
		logger.info( " cmd for transfer File   : " +cmd);
		
			try {
				Process p = new ProcessBuilder("/bin/bash", "-c",cmd).start();
				p.waitFor();
				BufferedReader errorReader = 
						new BufferedReader(new InputStreamReader(p.getErrorStream()));
				String line = "";	
				while ((line = errorReader.readLine())!= null) {
					logger.debug(line + "\n");
				}
				int exitValue=p.exitValue();
				if(exitValue==0){				
					result=true;
					
				}
			} catch (IOException e) {
				logger.error("UnixCommUtility : backupFile : IOException Exception"+e.getMessage(),e);
				
			} catch (InterruptedException e) {
				logger.error("UnixCommUtility : backupFile : InterruptedException Exception"+e.getMessage(),e);
			}  
			System.out.println("UnixCommUtility : backupFile method : Exit" +result );
			
			return result;
		
	}
	
	*/
	    public boolean CopyFileFromOutFolder(Sftp_fname_dto dto) {
		String SourcePath=dto.getFile_name();
		String processdFileLocation=dto.getDestinationPath();
		 boolean result = false;
		String cmd ="cp "+ SourcePath+" " +processdFileLocation; 
		logger.info( " cmd for transfer File   : " +cmd);
		
			try {
				Process p = new ProcessBuilder("/bin/bash", "-c",cmd).start();
				p.waitFor();
				BufferedReader errorReader = 
						new BufferedReader(new InputStreamReader(p.getErrorStream()));
				String line = "";	
				while ((line = errorReader.readLine())!= null) {
					logger.debug(line + "\n");
				}
				int exitValue=p.exitValue();
				if(exitValue==0){				
					result=true;
					
				}
			} catch (IOException e) {
				logger.error("UnixCommUtility : backupFile : IOException Exception"+e.getMessage(),e);
				
			} catch (InterruptedException e) {
				logger.error("UnixCommUtility : backupFile : InterruptedException Exception"+e.getMessage(),e);
			}  
			System.out.println("UnixCommUtility : backupFile method : Exit" +result );
			
			return result;
		
	}


	
	 
}
