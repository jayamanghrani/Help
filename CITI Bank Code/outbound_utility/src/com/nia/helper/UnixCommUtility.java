package com.nia.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class UnixCommUtility {

	private static UnixCommUtility instance;
	protected static final Logger logger = Logger.getLogger( SftpUtility.class );
	public static synchronized UnixCommUtility getInstance()
	{
		if (instance == null)
		{
			instance = new UnixCommUtility();
		}
		return instance;
	}

	public boolean encryptFiles(String cmd)   {
		boolean result = false;

		Runtime run = Runtime.getRuntime();		
		Process p=null;
		try 
		{
			p = run.exec(cmd);
			p.waitFor();	   
			int exitValue=p.exitValue();
			if(exitValue==0){
				result=true;
			}

		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error( " IOException Exception: " + e.getMessage(), e );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			logger.error( " InterruptedException Exception: " + e.getMessage(), e );
		}
		finally{
			p.destroy();
		}
		return result;
	}


	public boolean backupFile(String cmd) {
		boolean result = false;


		//logger.debug("cmd "+cmd);
		//Process p = null ;
		try {
			Process p = new ProcessBuilder("/bin/bash", "-c",cmd).start();


			//p = run.exec(cmd);
			p.waitFor();
			BufferedReader errorReader = 
					new BufferedReader(new InputStreamReader(p.getErrorStream()));
			String line = "";	
			while ((line = errorReader.readLine())!= null) {
				logger.debug(line + "\n");
			}

			int exitValue=p.exitValue();
			//logger.info(exitValue);
			if(exitValue==0){
				result=true;
			}
		} catch (IOException e) {
			logger.error( " IOException Exception: " + e.getMessage(), e );
		} catch (InterruptedException e) {
			logger.error( " InterruptedException Exception: " + e.getMessage(), e );
		}  


		return result;
	}
}
