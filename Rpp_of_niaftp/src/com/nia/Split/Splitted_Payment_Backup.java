package com.nia.Split;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.nia.jpa.dto.Sftp_fname_dto;



public class Splitted_Payment_Backup {
	

	private static Splitted_Payment_Backup instance;
	private boolean result;
    protected static final Logger logger = Logger.getLogger( Splitted_Payment_Backup.class );
    
    
	public static synchronized Splitted_Payment_Backup getInstance() throws JAXBException
	{
		if (instance == null)
		{
			instance = new Splitted_Payment_Backup();
		}
		return instance;
	}

	

	/*public boolean backup_splitted_paymentFile(String processdLocation,String tempfolderLocation) {
		boolean Copy_result=false;
		
	    File source = new File(processdLocation);
		logger.info(" value of Source folder in Splitted_Payment_Backup Class  is " +processdLocation);
		File dest = new File(tempfolderLocation);
		logger.info("value of destination folder in Splitted_Payment_Backup Class  is" +tempfolderLocation);	
			String cmd ="cp"+" "+ processdLocation+"*"+" " +tempfolderLocation; 
			logger.info( " cmd for transfer File   : " +cmd);
			
					Process p;
					try {
						p = new ProcessBuilder("/bin/bash", "-c",cmd).start();
						
						p.waitFor();
						BufferedReader errorReader = 
								new BufferedReader(new InputStreamReader(p.getErrorStream()));
						String line = "";	
						while ((line = errorReader.readLine())!= null) {
							logger.debug(line + "\n");
						}
						int exitValue=p.exitValue();
						if(exitValue==0){				
							Copy_result=true;
							
						}
						
					} catch (IOException e) {
				
						e.printStackTrace();
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				

		return Copy_result;
        }	*/
		
	  
	public boolean backup_splitted_paymentFile(Sftp_fname_dto dto) {
		boolean Copy_result=false;
		
		String processdLocation=dto.getDestinationPath();
		String tempfolderLocation=dto.getBankWisePlainFileBackupPath();
		
	    File source = new File(processdLocation);
		logger.info(" value of Source folder in Splitted_Payment_Backup Class  is " +processdLocation);
		File dest = new File(tempfolderLocation);
		logger.info("value of destination folder in Splitted_Payment_Backup Class  is" +tempfolderLocation);	
			String cmd ="cp"+" "+ processdLocation+"*"+" " +tempfolderLocation; 
			logger.info( " cmd for transfer File from "+processdLocation +" To "+tempfolderLocation+" : " +cmd);
			
					Process p;
					try {
						p = new ProcessBuilder("/bin/bash", "-c",cmd).start();
						
						p.waitFor();
						BufferedReader errorReader = 
								new BufferedReader(new InputStreamReader(p.getErrorStream()));
						String line = "";	
						while ((line = errorReader.readLine())!= null) {
							logger.debug(line + "\n");
						}
						int exitValue=p.exitValue();
						if(exitValue==0){				
							Copy_result=true;
							
						}
						
					} catch (IOException e) {
				
						e.printStackTrace();
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				

		return Copy_result;
        }



	
	}
	
	
	


