
package com.nia.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;

import com.nia.helper.Constants;
import com.nia.helper.ResourceManager;
import com.nia.jaxb.Config;
import com.nia.jpa.daoImpl.Sftp_fname_daoImpl;
import com.nia.jpa.dto.Sftp_fname_dto;
import com.nia.jpa.exception.FilecopytoRemote_Exception;
import com.nia.jpa.exception.Mail_Exception;
import com.nia.jpa.exception.Sftp_fname_dao_Exception;

public class Filecopy_of {

	Config logConfig;

	protected static  Logger logger ;
	Connection con;


	public Filecopy_of() throws JAXBException
	{
		Date d=new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		System.setProperty("current.date", dateFormat.format(d));
		logger = Logger.getLogger( Filecopy_of.class );
		
		logger.debug("Utility started at :: "+ d.getHours()+":"+d.getMinutes()+":"+d.getSeconds());

		LoadProperty();	
	}


	private void LoadProperty() throws JAXBException
	{
		logConfig = ResourceManager.getInstance().getLogConfig();	


	}

	private boolean copyFiletoRemoteDir(String fileName,int retryCount) throws FilecopytoRemote_Exception, JAXBException
	{
		
		return  ResourceManager.getInstance().copyFilestoRemoteDir(fileName,retryCount);
		
	}




	private Sftp_fname_dto getFileName() throws SQLException, ClassNotFoundException, Sftp_fname_dao_Exception, JAXBException
	{

		con = ResourceManager.getInstance().getConnection();
		//	con.setAutoCommit(false);

		Sftp_fname_daoImpl dao = new Sftp_fname_daoImpl();

		return dao.findAll(con);
		//	System.out.println("List Prepared"+programe_list.size());

	}


	public static void main(String args[]){

		try {
			Filecopy_of fcopy = new Filecopy_of();
			int retryCount=0;
			String fileName="";
			long t1 = System.currentTimeMillis();	
			
			Sftp_fname_dto dto = fcopy.getFileName();
			
			
			if (null != dto)
			{
				fileName = dto.getFile_name();
				
				boolean fcopy_result = fcopy.copyFiletoRemoteDir(fileName,retryCount);
				int index=fileName.lastIndexOf('/');
				
				String fName=fileName.substring(index+1,fileName.length());
				long t2 = System.currentTimeMillis();
				if (logger.isDebugEnabled()) {
					logger.debug( " File '" + fName +"' Copy Complete in  :" + (t2-t1)/1000 + " Sec. with Result:"+fcopy_result );
				}
			}

			else {
				
				String message="Payment File not generated on OF server.   "+System.getProperty("line.separator") +
						"Hence file not transferred to NIA SFTP server.  " +System.getProperty("line.separator") +
						 System.getProperty("line.separator")+System.getProperty("line.separator");
				
				logger.error(message);
				ResourceManager.getInstance().sendMailOnError(message);
				
				
			}


		} catch (JAXBException e) {
			
			logger.error("Exception Occured :: Error Code - "+ e.getErrorCode()+" Error Message -  "+e.getMessage());
			logger.error( "JAXBException::" ,e);
			
		} catch (FilecopytoRemote_Exception e) {
			
			logger.error("Exception Occured :: Error Message - "+e.getMessage());
			logger.error( " FilecopytoRemote_Exception::" ,e);
			
		} catch (Sftp_fname_dao_Exception e) {
			 
			logger.error("Exception Occured :: Error Message - "+e.getMessage());
			logger.error( " Sftp_fname_dao_Exception::" ,e);
			
		} catch (SQLException e) {
			
			logger.error("Exception Occured :: Error Code - "+e.getErrorCode()+" Error Message -  "+e.getMessage());
			logger.error( " SQLException::",e );
			
		} catch (ClassNotFoundException e) {
			logger.error("Exception Occured :: Error Message -  "+e.getMessage());
			logger.error( " ClassNotFoundException::",e );
			
			
		} catch (Mail_Exception e) {
			logger.error("Exception Occured:: Error Code - "+e.getErrorCode()+" Error Message -  "+e.getMessage());
			logger.error( " Mail_Exception::",e );
		}

	}


}
