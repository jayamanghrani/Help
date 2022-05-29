package com.nia.main;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.nia.helper.ResourceManager;
import com.nia.jaxb.Config;
import com.nia.jpa.exception.FilecopytoRemote_Exception;
import com.nia.jpa.exception.Mail_Exception;
import com.nia.jpa.exception.NoFileFoundException;
import com.nia.jpa.exception.UnixCmd_Exception;

public class FilecopySftp {

	Config logConfig;

	protected static  Logger logger ;
	Connection con;


	public FilecopySftp() throws JAXBException
	{
		Date d=new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.setProperty("current.date", dateFormat.format(d));
		logger = Logger.getLogger( FilecopySftp.class );


		logger.debug("Utility started at :: "+ d.getHours()+":"+d.getMinutes()+":"+d.getSeconds());
		LoadProperty();	
	}


	private void LoadProperty() throws JAXBException
	{
		logConfig = ResourceManager.getInstance().getLogConfig();	


	}

	private boolean copyFiletoRemoteDir(int retryCount) throws FilecopytoRemote_Exception, JAXBException
	{
		return  ResourceManager.getInstance().copyFilestoRemoteDir(retryCount);

	}

	

	public static void main(String args[]){

		try {
			int retryCount=0;
			FilecopySftp fcopy = new FilecopySftp();
			long t1 = System.currentTimeMillis();
			long t2;

			//Step 3 - Upload file from NIA server to CDSL server
			t1 = System.currentTimeMillis();
			
			logger.error("Inside main, coping file to cdsl server");
			fcopy.copyFiletoRemoteDir(retryCount);
			t2 = System.currentTimeMillis();

		} catch (JAXBException e) {
			logger.error("Exception Occured :: Error Code - "+e.getErrorCode()+" Error Message -  "+e.getMessage());
			logger.error( "JAXBException::" ,e);
			

		} catch (FilecopytoRemote_Exception e) {
			logger.error("Exception Occured :: Error Message -"+e.getMessage());
			logger.error( " FilecopytoRemote_Exception::" ,e);

		} 




	}


}
