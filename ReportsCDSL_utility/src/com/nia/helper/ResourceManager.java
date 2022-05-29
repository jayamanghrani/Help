
package com.nia.helper;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.nia.jaxb.Config;
import com.nia.jaxb.EmailProp;
import com.nia.jaxb.EncryptFileProp;
import com.nia.jaxb.FilecopyProperty;
import com.nia.jpa.exception.FilecopytoRemote_Exception;
import com.nia.jpa.exception.Mail_Exception;
import com.nia.jpa.exception.NoFileFoundException;
import com.nia.jpa.exception.UnixCmd_Exception;



public class ResourceManager
{

	protected static final Logger logger = Logger.getLogger( ResourceManager.class );
	/* Read Config File and Create Database Connection */

	Config logConfig;
	List<FilecopyProperty> fcopy_property;

	EncryptFileProp fencrypt_property;
	EmailProp femail_prop;
	Properties props = new Properties();
	String db_ip;
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



	//Encryption properties
	String shFile_Path;
	String plainFile_Path;
	String plainFileBackup_Path;
	String encryptedFile_Path;
	String encryptedFileBackup_Path;


	Connection con;
	private static ResourceManager instance;


	private void LoadProperty() throws JAXBException
	{

		logConfig = com.nia.helper.PropertyLoader.getInstance().getLogConfiguration();
		femail_prop=logConfig.getEmailProp();
		fcopy_property = logConfig.getFcopyProp();
		db_ip=logConfig.getDbProp().getServerIp();

		fencrypt_property=logConfig.getFencryptProp();
		db_port=logConfig.getDbProp().getPort();
		db_schema_name=logConfig.getDbProp().getSchemaName();
		db_user_id=logConfig.getDbProp().getUsername();
		db_password=logConfig.getDbProp().getPassword();
		db_driver=logConfig.getDbProp().getDriver();
		Constants.signature_constant=femail_prop.getMessageSignature();



	


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



	/* Copy file to CDSL SFTP Server directory */
	public boolean copyFilestoRemoteDir(int retryCount) throws FilecopytoRemote_Exception
	{
		boolean result = false;

		for (FilecopyProperty fcopyprop : fcopy_property )
		{

			result = 	SftpUtility.getInstance().copyFileToRemoteDir(fcopyprop.getFcopyTarget().getTIp(), fcopyprop.getFcopyTarget().getTPort(), fcopyprop.getFcopyTarget().getTUsername(), 
					fcopyprop.getFcopyTarget().getTPassword(), fcopyprop.getFcopySource().getSPath(), 
					fcopyprop.getFcopyTarget().getTPath(), fcopyprop.getFcopySource().isDelSourceFile(),retryCount,fcopyprop.getFcopySource().getRetryAttempts());

			if(!result)
			{
				throw new FilecopytoRemote_Exception("File transfer from "+fcopyprop.getFcopySource().getSFname()+" on NIA SFTP Server to  CDSL Server:"+
						" failed. " +System.getProperty("line.separator")+ "Please check the log file for more details on error."
								+System.getProperty("line.separator")+System.getProperty("line.separator"));
			}
		}


		return result;
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

	public boolean encryptFiles() throws UnixCmd_Exception   {
		boolean result = false;


		String cmd = fencrypt_property.getShFilePath() + "signandencrypt.sh"; 
		result=UnixCommUtility.getInstance().encryptFiles(cmd);
		if(!result){
			throw new UnixCmd_Exception("Error in Encrypting Payment File at location '"+fencrypt_property.getShFilePath()+"' on NIA SFTP server. "+System.getProperty("line.separator")+
					" Hence Transfer of files from NIA SFTP server to CITI server failed. " +System.getProperty("line.separator")+
					"Please check the log file for more details on error."
						+System.getProperty("line.separator")+System.getProperty("line.separator"));

		}
		return result;
	}

	public boolean backupPlainFile() throws UnixCmd_Exception, NoFileFoundException {
		boolean result = false;


		File folder = new File(fencrypt_property.getPlainFilePath());
		int fileEntryCount = folder.listFiles().length;
		if (fileEntryCount == 0)
		{

			logger.info("No files Present in the location - "+fencrypt_property.getPlainFilePath()+ " on NIA SFTP server" +
					". Hence no files transferred to CITI server.");
			throw new NoFileFoundException("No files Present in the location - "+fencrypt_property.getPlainFilePath()+ " on NIA SFTP server" +
					". Hence no files transferred to CITI server." +System.getProperty("line.separator") +
					"Please check the log file for more details on error."
					+System.getProperty("line.separator")+System.getProperty("line.separator"));
		}
		else{
			String cmd ="mv "+ fencrypt_property.getPlainFilePath() +"*.* " + fencrypt_property.getPlainFileBackupPath(); 
			result=UnixCommUtility.getInstance().backupFile(cmd);

			if(!result){
				throw new UnixCmd_Exception("Error in taking backup of files from "+ fencrypt_property.getPlainFilePath()
						+ " to " + fencrypt_property.getPlainFileBackupPath() +" on NIA SFTP server. " +System.getProperty("line.separator")+
						" Hence files are not transferred from NIA SFTP server to CITI server. " +System.getProperty("line.separator")+
						"Please check the log file for more details on error."
						+System.getProperty("line.separator")+System.getProperty("line.separator"));
			}

			else{
				logger.info("Files from "+ fencrypt_property.getPlainFilePath()
						+ " has beend moved  to " + fencrypt_property.getPlainFileBackupPath());
			}
		}
		return result;
	}

	public boolean backupEncryptedFile() throws UnixCmd_Exception, NoFileFoundException {
		boolean result = false;

		File folder = new File(fencrypt_property.getEncryptedFilePath());
		int fileEntryCount = folder.listFiles().length;
		if (fileEntryCount == 0)
		{

			logger.info("No files Present in the location - "+fencrypt_property.getEncryptedFilePath()+ " on NIA sFTP server" +
					". Hence no files transferred to CITI server.");
			throw new NoFileFoundException("No files Present in the location - "+fencrypt_property.getEncryptedFilePath()+ " on NIA SFTP server" +
					". Hence no files transferred to CITI server."+System.getProperty("line.separator")+"Please check the log file for more details on error."
							+System.getProperty("line.separator")+System.getProperty("line.separator"));
		}
		else{

			String cmd ="mv "+ fencrypt_property.getEncryptedFilePath() +"*.* " 
					+ fencrypt_property.getEncryptedFileBackupPath(); 
			result=UnixCommUtility.getInstance().backupFile(cmd);

			if(!result){
				throw new UnixCmd_Exception("Error in taking backup of Encrypted File from " 
						+ fencrypt_property.getEncryptedFilePath()	+ " to " 
						+ fencrypt_property.getEncryptedFileBackupPath()+" on NIA SFTP server. " +System.getProperty("line.separator")+
						" Hence files are not transferred from NIA SFTP server to CITI server. " +System.getProperty("line.separator")+
						"Please check the log file for more details on error."
						+System.getProperty("line.separator")+System.getProperty("line.separator"));
			}
			else{
				logger.info("Encrypted File from " 
						+ fencrypt_property.getEncryptedFilePath()	+ " has been moved to " 
						+ fencrypt_property.getEncryptedFileBackupPath() +" on NIA SFTP server." );
			}
		}
		return result;
	}

	public  void sendMailOnError(String message) throws Mail_Exception{

		String signature=Constants.signature+Constants.signature_constant;
		message=Constants.salutation+message+signature;
		Email_Sender emailSender=new Email_Sender(); 
		boolean result=emailSender.sendHtmlEmail( femail_prop.getMailFrom(), femail_prop.getPassword(), femail_prop.getReceipentList(), 
				femail_prop.getSmtpHost(), femail_prop.getSmtpPort(), femail_prop.isSmtpAuth(), 
				femail_prop.isSmtpSslEnable(), femail_prop.getMessageSubject(), message);




		if(!result){

			throw new Mail_Exception("Mail not sent ");


		}
		else{
			logger.info("Error mail hgas been sent to "+femail_prop.getReceipentList());
		}


	}
}
