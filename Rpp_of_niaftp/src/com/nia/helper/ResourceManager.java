
package com.nia.helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.nia.Split.PaymentFileCopy;
import com.nia.jaxb.Config;
import com.nia.jaxb.EmailProp;
import com.nia.jaxb.fsplitProperty;
import com.nia.jaxb.FilecopyProperty;
import com.nia.jpa.dto.Sftp_fname_dto;
import com.nia.jpa.exception.FilecopytoRemote_Exception;
import com.nia.jpa.exception.Mail_Exception;
import com.nia.jpa.exception.SignPayment_Exception;
import com.nia.sign.DigitalSignBean;
import com.nia.sign.DigitalSignature;
import com.nia.Split.PaymentFile;
import com.nia.Split.PaymentFileCount;
import com.nia.Split.Delate_UnSeperated_PaymentFile;
import com.nia.Split.PaymentFile_Writer;
import com.nia.Split.Splitted_Payment_Backup;
import com.nia.Split.PaymentFile_Writer;



public class ResourceManager
{

	protected static final Logger logger = Logger.getLogger( ResourceManager.class );
	/* Read Config File and Create Database Connection */

	Config logConfig;
	List<FilecopyProperty> fcopy_property;
	Properties props = new Properties();
	String db_ip;
	EmailProp femail_prop;
	fsplitProperty fsplit_property;
	int db_port;
	String db_schema_name;
	String db_user_id;
	String db_password;
	String db_url;
	String db_driver;
	InputStream input = null;


	String target_filePath;
	String Source_filePath;
	String Source_serverip;
	int Source_port;
	String Source_userid;
	String Source_passwd;


	/* File Split Configuration  */
	String recordsLimt;
    String remitterAccountNumber;
    String bank;
	String merchantCode;
	String newExtension;
	String keystore_file="";
	String keyStore_pwd;
	File[] paymentFile_paths_Array;
	String Payment_filename;
	File[] SplitedFileList;
	String Splited_payment_filename;
	File[] tempfolderFile_Array;
	boolean checkdatestatus=false;
	String Filename;
	/* Email Configuration  */
	String sender_email_adddress;
	String mail_from;
	String password;
	String Receipent_list;
	String message_subject;
	String smtp_host;
	String smtp_port;
	boolean smtp_auth;
	boolean debug;
	boolean smtp_ssl_enable;

	Connection con;
	private static ResourceManager instance;

	Date sysdate=new Date();
	SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
	String strSysDate=sdFormat.format(sysdate);

	private void LoadProperty() throws JAXBException
	{

		logConfig = com.nia.helper.PropertyLoader.getInstance().getLogConfiguration();
		femail_prop=logConfig.getEmailProp();

		fcopy_property = logConfig.getFcopyProp();
		db_ip=logConfig.getDbProp().getServerIp();
		db_port=logConfig.getDbProp().getPort();
		db_schema_name=logConfig.getDbProp().getSchemaName();
		db_user_id=logConfig.getDbProp().getUsername();
		db_password=logConfig.getDbProp().getPassword();
		db_driver=logConfig.getDbProp().getDriver();
 
		//For File Splitter
		for (FilecopyProperty fcopyprop : fcopy_property ){
		recordsLimt=fcopyprop.getFcopySource().getRecordsLimit();	
		newExtension=fcopyprop.getFcopySource().getNewExtension();
		keystore_file=fcopyprop.getFcopySource().getKeyStoredFile();
		keyStore_pwd=fcopyprop.getFcopySource().getKeyStoredPwd();
		merchantCode=fcopyprop.getFcopySource().getMerchnatcode();
		bank=fcopyprop.getFcopySource().getBank();
		remitterAccountNumber=fcopyprop.getFcopySource().getRemitterAccountNumber();
		
		}
		Constants.signature_constant=femail_prop.getMessageSignature();


		db_url="jdbc:oracle:thin:@"+db_ip+":"+db_port+"/"+db_schema_name;

		
		


	}


	public ResourceManager() throws JAXBException 
	{

		LoadProperty();

	}





	public Config getLogConfig() {
		return logConfig;
	}


	public void setLogConfig(Config logConfig) {
		this.logConfig = logConfig;
	}


	public static synchronized ResourceManager getInstance() throws JAXBException
	{
		if (instance == null)
		{
			instance = new ResourceManager();
		}
		return instance;
	}

	/* Copy file of OF to NIA SFTP server */

	public boolean copyFilestoRemoteDir(int retryCount, Sftp_fname_dto dto) throws FilecopytoRemote_Exception
	{
		boolean result = false;
		boolean CallPoutboundsh_Status=false;

		for (FilecopyProperty fcopyprop : fcopy_property )	
		{
			String LocalFolder=dto.getDestinationPath();
			String tempBAckupLocation=dto.getBankWisePlainFileBackupPath();
			File processdFileLocation = new File(LocalFolder);
			SplitedFileList =processdFileLocation.listFiles();
			
			
			
			 for(File SplitedFilename :SplitedFileList){
				 
				 Splited_payment_filename =SplitedFilename.getName();
				 
				 logger.info("value of Splited_payment_filename in ResourceManager  " +Splited_payment_filename);
	
			result = 	SftpUtility.getInstance().copyFileToRemoteDir(fcopyprop.getFcopyTarget().getTIp(), fcopyprop.getFcopyTarget().getTPort(), fcopyprop.getFcopyTarget().getTUsername(), 
					fcopyprop.getFcopyTarget().getTPassword(),LocalFolder, 
					fcopyprop.getFcopyTarget().getTPath(),fcopyprop.getFcopyTarget().getTbackpath(), Splited_payment_filename,fcopyprop.getFcopySource().isDelSourceFile(), retryCount, fcopyprop.getFcopySource().getRetryAttempts());
			 
			if(!result)
			{
				throw new FilecopytoRemote_Exception("Unable to Copy File "+Splited_payment_filename+" from OF server to NIA SFTP Server:"+
						fcopyprop.getFcopyTarget().getTIp()+"at Location:"+fcopyprop.getFcopyTarget().getTPath()+ ". "
						+ System.getProperty("line.separator") +
						"Please check log file for more details on error. "+System.getProperty("line.separator")+System.getProperty("line.separator"));
			}
			else{ 
				  String PaymnetFilePath=tempBAckupLocation+Splited_payment_filename;
				int  SplittedRecordsNumber;
				try {
					SplittedRecordsNumber = PaymentFileCount.getInstance().payment_record_Count(PaymnetFilePath);
				
					logger.info("value of records --"+SplittedRecordsNumber);

					result =SftpUtility.getInstance().callOutboundShFile(bank,merchantCode,Splited_payment_filename,remitterAccountNumber,SplittedRecordsNumber,fcopyprop.getFcopyTarget().getTIp(), 
								fcopyprop.getFcopyTarget().getTPort(), fcopyprop.getFcopyTarget().getTUsername(), 
								fcopyprop.getFcopyTarget().getTPassword(),fcopyprop.getFcopyTarget().getTPath(),fcopyprop.getFcopyTarget().gettSH_file_path(),
								retryCount, fcopyprop.getFcopySource().getRetryAttempts());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}
			
		}

		}
		return result;
	}

	
	/**** for Copy File from one Folder to OFSFTP Users. 
	 * @throws FilecopytoRemote_Exception 
	 * @throws  ****/

	public boolean CopyFileFromOutFolder(Sftp_fname_dto dto) throws FilecopytoRemote_Exception {
		boolean result = false;
		String fileName=dto.getFile_name();
		logger.info("value of fileName --new -- "+fileName);
		String processdLocation =dto.getDestinationPath();
		logger.info("value of processdLocation --new-- "+processdLocation);
		File f=new File(fileName);

		result=PaymentFileCopy.getInstance().CopyFileFromOutFolder(dto);

		if(!result)
		{
			throw new FilecopytoRemote_Exception("Unable to Copy File "+f.getName()+" from OF server location  to  processdLocation:"+processdLocation		
					+"Please check log file for more details on error. "+System.getProperty("line.separator")+System.getProperty("line.separator"));
		}

		return result;
	}


	public boolean getPaymnetFileDigitalSignature(Sftp_fname_dto dto) {
		boolean SignedFlag= false;
		
		String processdLocation =dto.getDestinationPath();
		String SignedLocation =dto.getBankWiseOutboundAPath();
		DigitalSignBean digitalSignbean =new DigitalSignBean();
		
		digitalSignbean.setInboundFolderSign(processdLocation);
		digitalSignbean.setOutboundFolderSign(SignedLocation);
		
		digitalSignbean.setKEYSTORE_FILE(keystore_file);
		digitalSignbean.setKEYSTORE_PWD(keyStore_pwd);
		digitalSignbean.setNewExtension(newExtension);
		 
		try {
			SignedFlag=DigitalSignature.getInstance().getPaymnetFileDigitalSignature(digitalSignbean);
		
		
		} catch (SignPayment_Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		return SignedFlag;
	}

	public boolean backup_splitted_paymentFile(Sftp_fname_dto dto) {
		boolean backup_result =false;
		try {
		
			backup_result=Splitted_Payment_Backup.getInstance().backup_splitted_paymentFile(dto);	
			} catch (JAXBException e) {

				e.printStackTrace();
			}
			return backup_result;
	}

	public boolean getPaymentFileName_And_Split(Sftp_fname_dto dto) throws IOException, JAXBException {

	boolean status=false;
	int numberOfLine=0;
	int Seq_no=0;
	double nof=0;
	String FilePrefixNew;
	
	String processdLocation=dto.getDestinationPath();
	String Path=dto.getDestinationPath();
	String FilePrefix=dto.getFilePrefix();
	String tempfolderLocation=dto.getBankWisePlainFileBackupPath();
	
	
	File folder = new File(processdLocation);
	int fileEntryCount = folder.listFiles().length;
	if (fileEntryCount == 0)
	{
		logger.info(" No File present "+fileEntryCount +"at on Location");
		status= false;
	}
	else{
		logger.info("UnSplitted Payment file is is on Location"+fileEntryCount);

		if (folder.isDirectory())
		{ 
			paymentFile_paths_Array =folder.listFiles();

			for(File f :paymentFile_paths_Array)
			{
				logger.info(" \n show Existing name---   " +f);

				Payment_filename =f.getName();
				String PaymentFilePath = Path + Payment_filename;

				int RecordsNumber= PaymentFileCount.getInstance().payment_record_Count(PaymentFilePath);
				logger.info("no of Records in UnSplitted Payment File before Pass through UnSpeparator Utility " +RecordsNumber);

				numberOfLine = Integer.parseInt(recordsLimt);   			
				double splitFraction = (RecordsNumber/numberOfLine);  
				logger.info("Number of expected Separated  file  in double formate--temp  -- \t "  + splitFraction);
				int splitFractionInt=(int)splitFraction;          
				if(splitFractionInt==splitFraction)  
				{  
					nof=splitFractionInt;  
					if(nof>1)
					{
						
						FilePrefixNew =FilePrefix+strSysDate;
						File file = new File(tempfolderLocation);
						
						if(file.isDirectory())
						{
							tempfolderFile_Array =file.listFiles();
							for(File temp_filespath :tempfolderFile_Array)
						      {						
								   Filename=temp_filespath.getName();
						    	  logger.info(" Get Filesname  present in  "+ temp_filespath+ "  are---   " + Filename);
						    	 PaymentFile paymentobj  =PaymentFileCount.getInstance().checkPaymentfiledetails(Filename);
						    	 if((paymentobj.getPaymnetDate()).equalsIgnoreCase(strSysDate)){
						    		 
						    		 checkdatestatus=true;
						      }
							
						          }
							 
							if((file.list().length>0)&&checkdatestatus)
							{
								logger.info("File present in tempfolderLocation with currentdate");
								Seq_no=PaymentFileCount.getInstance().checkSequenceNumber(tempfolderLocation);
								PaymentFile_Writer.getInstance().File_Writer(PaymentFilePath,FilePrefixNew,nof,numberOfLine,Seq_no,newExtension);
							}
							else{
								logger.info(" tempfolderLocation -Directory is empty!");

								PaymentFile_Writer.getInstance().newFile_Writer(PaymentFilePath,FilePrefixNew,nof,numberOfLine,newExtension);	
							}
						}	 	    	 
						boolean DeleteUnSeperatedPaymentFileStatus =Delate_UnSeperated_PaymentFile.getInstance().Remove_unSeparated_paymentFile(PaymentFilePath); 
						logger.info("Unsplitted Payment file is deleted  from Folder,DeleteUnSeperatedPaymentFileStatus ---  "  +DeleteUnSeperatedPaymentFileStatus);
					}
					else{
						logger.info(" Number of expected Separated file is equal or less than 1 , So it can not be Separated \n ");
					}
				}
				else  
				 {
					nof=splitFractionInt+1;  
					if(nof>1){
						logger.info(" no. of file, when nof=temp1 +1  --in else 2 nd conditions ----"  +nof); 		               
						FilePrefixNew =FilePrefix+strSysDate;
						//FilePrefix ="PNB-"+merchantCode+"-"+strSysDate;
						logger.info("FilePrefix of Paymnet File ---  "  +FilePrefixNew);
						File file = new File(tempfolderLocation);
						if(file.isDirectory())
						{		
							tempfolderFile_Array =file.listFiles();
							for(File temp_filespath :tempfolderFile_Array)
						      {
								 logger.info(" Files present in resourcemanger are---   " + temp_filespath);
								   Filename=temp_filespath.getName();
						    	  logger.info(" Files present in " + temp_filespath + " resourcemanger are---   " + Filename);
						    	 PaymentFile paymentobj  =PaymentFileCount.getInstance().checkPaymentfiledetails(Filename);
						    	 if((paymentobj.getPaymnetDate()).equalsIgnoreCase(strSysDate)){
						    		 
						    		 checkdatestatus=true;
						      }
							
						          }
							
							if((file.list().length>0)&&checkdatestatus)
							{
								logger.info("File in tempfolderLocation !");
								Seq_no=PaymentFileCount.getInstance().checkSequenceNumber(tempfolderLocation);
								PaymentFile_Writer.getInstance().File_Writer(PaymentFilePath,FilePrefixNew,nof,numberOfLine,Seq_no,newExtension);
							}
							else{
								logger.info(" tempfolderLocation Directory is empty!");

								PaymentFile_Writer.getInstance().newFile_Writer(PaymentFilePath,FilePrefixNew,nof,numberOfLine,newExtension);	
							}
						}
						boolean deleteStatus =Delate_UnSeperated_PaymentFile.getInstance().Remove_unSeparated_paymentFile(PaymentFilePath); 
						logger.info("Unsplitted Payment file is deleted  from Folder---  "  +deleteStatus);

					}  
				    	else{
						logger.info(" Number of expected Separated file is equal or less than 1 , So it can not be Separated \n ");

					        } 
				  }
			} 

		}

		status=true;
	}
	return status;
} 






	public  Connection getConnection()throws SQLException, ClassNotFoundException

	{
		Class.forName(db_driver);

		Connection con  = DriverManager.getConnection(
				db_url, db_user_id,db_password);
		return con;


	}



	public static void close(Connection conn)
	{
		try {
			if (conn != null) conn.close();
		}
		catch (SQLException sqle)
		{
			logger.error( "Exception in Closing Connection: " + sqle.getMessage(), sqle );
			//sqle.printStackTrace();
		}
	}

	public static void close(PreparedStatement stmt)
	{
		try {
			if (stmt != null) stmt.close();
		}
		catch (SQLException sqle)
		{

			logger.error("Closing connection Failed ", sqle);

		}




	}

	public static void close(ResultSet rs)
	{
		try {
			if (rs != null) rs.close();
		}
		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}

	}

	public void RoleBack_Connection(Connection Rolbackcon)
	{
		try{
			// Set this in Property file as Other DB Exception
			Rolbackcon.rollback();
		}
		catch(SQLException ex){}	
	}

	public  void sendMailOnError(String message) throws Mail_Exception{


		Email_Sender emailSender=new Email_Sender(); 
		String salutation=Constants.salutation;
		String signature=Constants.signature + Constants.signature_constant;

		message=salutation +message + signature;

		boolean result=emailSender.sendHtmlEmail( femail_prop.getMailFrom(), femail_prop.getPassword(), femail_prop.getReceipentList(), 
				femail_prop.getSmtpHost(), femail_prop.getSmtpPort(), femail_prop.isSmtpAuth(), 
				femail_prop.isSmtpSslEnable(), femail_prop.getMessageSubject(), message);




		if(!result){

			throw new Mail_Exception("Mail not sent ");


		}
		else{
			logger.info("Error mail has been sent");
		}


	}








}
