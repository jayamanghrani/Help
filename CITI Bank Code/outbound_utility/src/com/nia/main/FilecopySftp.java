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

	private boolean encryptFiles() throws JAXBException, UnixCmd_Exception
	{

		return  ResourceManager.getInstance().encryptFiles();


	}
	private boolean backupPlainFile() throws JAXBException, UnixCmd_Exception, NoFileFoundException
	{

		return  ResourceManager.getInstance().backupPlainFile();


	}

	private boolean backupEncryptedFile() throws JAXBException, UnixCmd_Exception, NoFileFoundException
	{

		return  ResourceManager.getInstance().backupEncryptedFile();


	}

	public static void main(String args[]){

		try {
			int retryCount=0;
			FilecopySftp fcopy = new FilecopySftp();
			long t1 = System.currentTimeMillis();
			long t2;


			//Step 1 - Sign and Encrypt file
			boolean encrypt_result=fcopy.encryptFiles();
			t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug( " File Encryption Complete in  :" + (t2-t1)/1000 + " Sec. with Result:"+encrypt_result );
			}
			//Step 2 - Move plain file to Backup folder
			t1 = System.currentTimeMillis();
			fcopy.backupPlainFile();
			t2 = System.currentTimeMillis();
			

			//Step 3 - Upload file from NIA server to CITI server
			t1 = System.currentTimeMillis();
			fcopy.copyFiletoRemoteDir(retryCount);

			t2 = System.currentTimeMillis();
			

			//Step 4 - Backup encrypted file
			t1 = System.currentTimeMillis();
			boolean backupEncryptResult=fcopy.backupEncryptedFile();
			t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug( "File moved in  :" + (t2-t1)/1000 + " Sec. with Result:"+backupEncryptResult );
			}

		} catch (JAXBException e) {
			logger.error("Exception Occured :: Error Code - "+e.getErrorCode()+" Error Message -  "+e.getMessage());
			logger.error( "JAXBException::" ,e);
			try {
				
				ResourceManager.getInstance().sendMailOnError("JAXBException Exception: " + e.getMessage() + 
						System.getProperty("line.separator")+
						"Please check the log file for more details on error."
						+System.getProperty("line.separator")+System.getProperty("line.separator")
						);
			} catch (Mail_Exception ex) {
				// TODO Auto-generated catch block

			} catch (JAXBException ex) {
				// TODO Auto-generated catch block

			}

		} catch (FilecopytoRemote_Exception e) {
			logger.error("Exception Occured :: Error Message -"+e.getMessage());
			logger.error( " FilecopytoRemote_Exception::" ,e);

		}  catch (UnixCmd_Exception e) {
			logger.error("Exception Occured :: Error Message -"+e.getMessage());
			logger.error( " UnixCmd_Exception::" ,e);

		} catch (NoFileFoundException e) {
			logger.error("Exception Occured :: Error Message -"+e.getMessage());
			logger.error( " NoFileFoundException::" ,e);
			
		} 


	}


}
