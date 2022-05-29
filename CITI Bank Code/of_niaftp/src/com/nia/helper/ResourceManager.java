
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
import com.nia.jaxb.FilecopyProperty;
import com.nia.jpa.exception.FilecopytoRemote_Exception;
import com.nia.jpa.exception.Mail_Exception;



public class ResourceManager
{

	protected static final Logger logger = Logger.getLogger( ResourceManager.class );
	/* Read Config File and Create Database Connection */

	Config logConfig;
	List<FilecopyProperty> fcopy_property;
	Properties props = new Properties();
	String db_ip;
	EmailProp femail_prop;
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

	Connection con;
	private static ResourceManager instance;


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
	public boolean copyFilestoRemoteDir(String fileName,int retryCount) throws FilecopytoRemote_Exception
	{
		boolean result = false;
		
		for (FilecopyProperty fcopyprop : fcopy_property )
		{
			File f=new File(fileName);
			result = 	SftpUtility.getInstance().copyFileToRemoteDir(fcopyprop.getFcopyTarget().getTIp(), 
					fcopyprop.getFcopyTarget().getTPort(), fcopyprop.getFcopyTarget().getTUsername(), 
					fcopyprop.getFcopyTarget().getTPassword(), 
					fcopyprop.getFcopySource().getSPath()+System.getProperty("file.separator")+fcopyprop.getFcopySource().getSFname(), 
					fcopyprop.getFcopyTarget().getTPath(), fileName,fcopyprop.getFcopyTarget().getTFilenamePrefix(),fcopyprop.getFcopySource().isDelSourceFile(), retryCount, fcopyprop.getFcopySource().getRetryAttempts());
			
			if(!result)
			{
				throw new FilecopytoRemote_Exception("Unable to Copy File "+f.getName()+" from OF server to NIA SFTP Server:"+
						fcopyprop.getFcopyTarget().getTIp()+"at Location:"+fcopyprop.getFcopyTarget().getTPath()+ ". "
						+ System.getProperty("line.separator") +
						"Please check log file for more details on error. "+System.getProperty("line.separator")+System.getProperty("line.separator"));
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
