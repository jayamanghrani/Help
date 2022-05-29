package com.nia.main;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.nia.helper.ResourceManager;
import com.nia.helper.SftpUtility;
import com.nia.jaxb.Config;
import com.nia.jpa.exception.FilecopytoRemote_Exception;
import com.nia.jpa.exception.Mail_Exception;
import com.nia.jpa.exception.NoFileFoundException;
import com.nia.jpa.exception.UnixCmd_Exception;

public class Filecopyciti {

	Config logConfig;

	protected static  Logger logger;
	Connection con;
	SftpUtility sftputil;

	public Filecopyciti() throws JAXBException
	{
		Date d=new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.setProperty("current.date", dateFormat.format(d));
		logger = Logger.getLogger( Filecopyciti.class );
		
		
		logger.debug("Utility started at :: "+ d.getHours()+":"+d.getMinutes()+":"+d.getSeconds());

		LoadProperty();	
	}


	private void LoadProperty() throws JAXBException
	{
		logConfig = ResourceManager.getInstance().getLogConfig();	
		sftputil = SftpUtility.getInstance();

	}

	private boolean copyFileFromRemoteDir(int retryCount) throws FilecopytoRemote_Exception, JAXBException,  NoFileFoundException 
	{


		return( ResourceManager.getInstance().copyFilesfromRemoteDir(retryCount));

	}

	private boolean decryptFiles() throws JAXBException, UnixCmd_Exception
	{

		return  ResourceManager.getInstance().decryptFiles();


	}

	private boolean backupDownloadedFile() throws JAXBException, UnixCmd_Exception
	{

		return  ResourceManager.getInstance().backupDownloadedFile();


	}


	private boolean backupDecryptedFile() throws UnixCmd_Exception, JAXBException{
		return ResourceManager.getInstance().backupDecryptedFile();
	}


	public static void main(String args[]){

		try {
			Filecopyciti fcopy = new Filecopyciti();
			
			int retryCount=0;
			long t1 = System.currentTimeMillis();
						
			//Step -1 Download the files from CITI server to NIA server
			boolean fcopy_result = fcopy.copyFileFromRemoteDir(retryCount);

			long t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug( " File Copy Complete in  :" + (t2-t1)/1000 + " Sec. with Result:"+fcopy_result );
			}
			// Step -2 Decrypt the file
			t1 = System.currentTimeMillis();

			boolean decrypt_result=fcopy.decryptFiles();

			 t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug( " File decrypted in  :" + (t2-t1)/1000 + " Sec. with Result:"+decrypt_result );
			}
			//Step -3 Move downloaded file to backup folder
			t1 = System.currentTimeMillis();

			boolean backupEncryptResult=fcopy.backupDownloadedFile();

			t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug( " Encrypted File Backup in  :" + (t2-t1)/1000 + " Sec. with Result:"+backupEncryptResult );
			}

			//Step -4 Mail the upload download information
			t1 = System.currentTimeMillis();

				boolean mailResult=ResourceManager.getInstance().sendMailOnSuccess();

				 t2 = System.currentTimeMillis();
				if (logger.isDebugEnabled()) {
					logger.debug( " E-mail sent in  :" + (t2-t1)/1000 + " Sec. with Result:"+mailResult );
				}


			/*//Step -5 Move decrypted file to backup folder
			t1 = System.currentTimeMillis();

			boolean backupResult=fcopy.backupDecryptedFile();

			t2 = System.currentTimeMillis();
			if (logger.isDebugEnabled()) {
				logger.debug( " Decrypted file Backup in  :" + (t2-t1)/1000 + " Sec. with Result:"+backupResult );
			}
*/


			//	boolean fcopy_result = fcopy.copyFiletoRemoteDir();


		} catch (JAXBException e) {
			logger.error("Exception Occured :: Error Code - "+e.getErrorCode()+" Error Message -  "+e.getMessage());
			logger.error( "JAXBException::" ,e);
			try {
				ResourceManager.getInstance().sendMailOnError("JAXBException Exception: " + e.getMessage()+System.getProperty("line.separator"));
			} catch (Mail_Exception ex) {
				// TODO Auto-generated catch block

			} catch (JAXBException ex) {
				// TODO Auto-generated catch block
				
			}

		} catch (FilecopytoRemote_Exception e) {
			logger.error("Exception Occured :: Error Message -"+e.getMessage());
			logger.error( " FilecopytoRemote_Exception::" ,e);

		}   catch (Mail_Exception e) {
			logger.error("Exception Occured :: Error Code - "+e.getErrorCode()+" Error Message -"+e.getMessage());
			logger.error( " Sftp_fname_dao_Exception::" ,e);

		} 
		catch (UnixCmd_Exception e) {
			logger.error("Exception Occured :: Error Message -"+e.getMessage());
			logger.error( " UnixCmd_Exception::" ,e);

		} catch (NoFileFoundException e) {
			// TODO Auto-generated catch block

		} catch (IOException e) {
			logger.error("Exception Occured :: Error Message -  "+e.getMessage());
			logger.error( " IOException::",e );
			try {
				ResourceManager.getInstance().sendMailOnError("IOException Exception: " + e.getMessage()+System.getProperty("line.separator"));
			} catch (Mail_Exception ex) {
				// TODO Auto-generated catch block

			} catch (JAXBException ex) {
				// TODO Auto-generated catch block
				
			}
		}

	}


}
