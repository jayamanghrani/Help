package com.nia.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBException;

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
	private boolean copyFiletoRemoteDir(int retryCount, Sftp_fname_dto dto) throws FilecopytoRemote_Exception, JAXBException
	{
		
		return  ResourceManager.getInstance().copyFilestoRemoteDir(retryCount,dto);
		
	}

   
    private boolean CopyFileFromOutFolder(Sftp_fname_dto dto) throws JAXBException, FilecopytoRemote_Exception {
 		
		return ResourceManager.getInstance().CopyFileFromOutFolder(dto);
	} 
    private boolean getPaymentFileName_And_Split(Sftp_fname_dto dto) throws IOException, JAXBException {
    	
		return ResourceManager.getInstance().getPaymentFileName_And_Split(dto);
	}
     private boolean backup_splitted_paymentFile(Sftp_fname_dto dto) throws JAXBException {
		
		return ResourceManager.getInstance().backup_splitted_paymentFile(dto);
	} 
	private boolean getPaymnetFileDigitalSignature(Sftp_fname_dto dto) throws JAXBException {
		
		return ResourceManager.getInstance().getPaymnetFileDigitalSignature(dto);
	}
	
	private Sftp_fname_dto getFileName(String officeCode, String sourceName, String bankName) throws SQLException, ClassNotFoundException, Sftp_fname_dao_Exception, JAXBException
	{

		con = ResourceManager.getInstance().getConnection();

		Sftp_fname_daoImpl dao = new Sftp_fname_daoImpl();

		return dao.findAll(con,officeCode,sourceName,bankName);

	}


	public static void main(String args[]){
           String office=null;
           String source=null;
           String bankName=null;
           boolean fcopy_result=false;
		 System.out.println("hii main class");
		
		try {
				if(args.length == 3)
				{
					office=args[0];
					source=args[1];
					bankName=args[2];
					if(null != office && null!=source && null!=bankName)
					{
						if(Constants.isValidSource(source))
						{
							if(Constants.SOURCE.BSSBY.equalsIgnoreCase(source))
						   
							{
	                     
		                   	Filecopy_of fcopy = new Filecopy_of();
		                 	int retryCount=0;
			                String fileName="";
			                long t1 = System.currentTimeMillis();	
			        		//**** get the file name & path, destination path, file prefix  ****//
			                
			              Sftp_fname_dto dto = fcopy.getFileName(office,source,bankName);
			                
			              /*  Sftp_fname_dto dto =new Sftp_fname_dto();
			                dto.setFile_name("/applog/conc/out/o100322613.out");
			                dto.setDestinationPath("/ofsftp/Data/Processd/");
			                dto.setFilePrefix("PNB-TESTRPP-");
			                dto.setBankWiseOutboundAPath("/ofsftp/Data/Processd/");
			                dto.setBankWisePlainFileBackupPath("/ofsftp/Data/temp/");
			                dto.setBankWiseEncryptedFileBackupPath("NA");
			                dto.setRejectedFileCommonPath("NA");
			                
			              System.out.println("value inside dto --"+dto);*/
			       
			         
			                 
		             	if (null != dto)
			     
			                  {
		             		logger.debug( " dto comes from DB --" + dto );

			                  	
	                           /**** 1.for Copy File from one Folder to OFSFTP Users. ****/
	                          	boolean result= fcopy.CopyFileFromOutFolder(dto); 
	                           
	                           /*** 2.For File Splitter, Split File ****/
	                        
								boolean status = fcopy.getPaymentFileName_And_Split(dto);
								
								/****3. For Backup of Splitted File ****/
								boolean Backup_result=fcopy.backup_splitted_paymentFile(dto);
								
								/****4. For Call HSM and Encryption to Encrypt File ****/
								boolean DigitalSignature_status=fcopy.getPaymnetFileDigitalSignature(dto);
								
								
								/****5. For Sftp Encrypted File From OF Application Server to NIASFTP Server ****/	                          
			                
			                	if(DigitalSignature_status){
			                		logger.debug("Plain File has  been Signed. going to sftp to NIAFTP Server");
			                		fcopy_result = fcopy.copyFiletoRemoteDir(retryCount,dto);
			                	  
			                	}else{
			                		 logger.debug("Plain File has not been Signed. File will not sftp to NIAFTP Server");
			                	}
			                	
			             	int index=fileName.lastIndexOf('/');
				            String fName=fileName.substring(index+1,fileName.length());
				            long t2 = System.currentTimeMillis();
				            if (logger.isDebugEnabled()) {
					            logger.debug( " File '" + fName +"' Copy Complete in  :" + (t2-t1)/1000 + " Sec. with Result:"+fcopy_result );
				                   }
			                   }

			              else {
				
			              	String message="BSSBY Payment File not generated on OF server.   "+System.getProperty("line.separator") +
			 			"Hence file not transferred to NIA SFTP server.  " +System.getProperty("line.separator") +
						 System.getProperty("line.separator")+System.getProperty("line.separator");
				
				    logger.error(message);
				        ResourceManager.getInstance().sendMailOnError(message);
								
			    }
			
		 }
			else
			{			
				logger.error("Filecopy_of : main : Please pass the Source argument properly.Kindly check with Sources Values");
			}
			}
			else
			{
				logger.error("Filecopy_of : main : Please pass the arguments properly.No one argumnets is null");
			}
		}
		else
		{
			logger.error("Filecopy_of : main : Please pass the arguments properly. All three argumnets doesnot contains in args[]");
		}
	} 
		} catch (JAXBException e) {
			
			logger.error("Exception Occured :: Error Code - "+ e.getErrorCode()+" Error Message -  "+e.getMessage());
			logger.error( "JAXBException::" ,e);
			
		} catch (FilecopytoRemote_Exception e) {
			
			logger.error("Exception Occured :: Error Message - "+e.getMessage());
			logger.error( " FilecopytoRemote_Exception::" ,e);
			
		} 
		
     	catch (Sftp_fname_dao_Exception e) {
			 
			logger.error("Exception Occured :: Error Message - "+e.getMessage());
			logger.error( " Sftp_fname_dao_Exception::" ,e);
			
		} catch (SQLException e) {
			
			logger.error("Exception Occured :: Error Code - "+e.getErrorCode()+" Error Message -  "+e.getMessage());
			logger.error( " SQLException::",e );
			
		} catch (ClassNotFoundException e) {
			
			logger.error("Exception Occured :: Error Message -  "+e.getMessage());
			logger.error( " ClassNotFoundException::",e );	
		}
		
		catch (Mail_Exception e) {
			logger.error("Exception Occured:: Error Code - "+e.getErrorCode()+" Error Message -  "+e.getMessage());
			logger.error( " Mail_Exception::",e );
		}
		catch (IOException e) {
			
			e.printStackTrace();
			logger.error(" IOException Exception Occured:- Error Message -  "+e.getMessage());
			logger.error( " Mail_Exception::",e );
		}

	}



 



}
