
package com.nia.jpa.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;


public class ResourceManager
{

	protected static final Logger logger = Logger.getLogger( ResourceManager.class );
	/* Read Config File and Create Database Connection */
   
	
	String fileName = "config"+File.separator+"config.properties";
    Properties props = new Properties();
     String db_ip="";
     String db_port="";
     String db_schema_name="";
     String db_user_id="";
     String db_password="";
     String db_url="";
     String db_driver="";
     InputStream input = null;
     
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
     
     List<String> Receipent_Array;
    
     
	
    Connection con;
    PreparedStatement stmt1;
	private static ResourceManager instance;
	
	
	
	private void LoadProperty()
	{
	
		try{
       	input = new FileInputStream(fileName);
		props.load(input);
		
		 db_ip=props.getProperty("db_ip");
	     db_port=props.getProperty("db_port");
	     db_schema_name=props.getProperty("db_schema_name");
	     db_user_id=props.getProperty("db_user_id");
	     db_password=props.getProperty("db_password");
	     db_driver=props.getProperty("db_driver");
	
	
	     
	     //Oracle Dev URL
	     //db_url="jdbc:oracle:thin:@10.10.3.246:1521:dgaapp";
		     
		 
	     
	     // OF UAT
	   //db_url="jdbc:oracle:thin:@"+db_ip+":"+db_port+":"+db_schema_name;
	   // System.out.println("DBURL:"+db_url);
	    
	    
	    // OF PRod
	   db_url="jdbc:oracle:thin:@"+db_ip+":"+db_port+"/"+db_schema_name;
	   // System.out.println("DBURL:"+db_url);
	    
	    
	     /* Load Email Config Parameter */
	     
	     
	     sender_email_adddress=props.getProperty("sender_email_adddress");
	     mail_from=props.getProperty("mail_from");
	     password=props.getProperty("password");
	     Receipent_list=props.getProperty("Receipent_list");
	     message_subject=props.getProperty("message_subject");
	     smtp_host=props.getProperty("smtp_host");
	     smtp_port=props.getProperty("smtp_port");
	     smtp_auth=Boolean.parseBoolean(props.getProperty("smtp_auth"));
	     debug=Boolean.parseBoolean(props.getProperty("debug"));
	     smtp_ssl_enable=Boolean.parseBoolean(props.getProperty("smtp_ssl_enable"));
	     
	     
	   }
       catch(IOException e){
   		logger.error( "Excetion Caught in Reading Property file: " + e.getMessage() );
    	   //System.out.println("Excetion Caught in Reading Property file :"+e.getMessage());
       }
		 finally {
				if (input != null) {
					try {
						input.close();
						
					} catch (IOException e) {
						logger.error( "Excetion Caught in Closing  Property file: " + e.getMessage() );
						//e.printStackTrace();
					}
				}
		 }
		
	}

	
    public ResourceManager() 
    {
   	    
    	LoadProperty();
    	
    }
    
      
    
    public static synchronized ResourceManager getInstance()
    {
if (instance == null)
 {
 instance = new ResourceManager();
 }
 return instance;
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
	
	
	/* Send Email Message */
	
	   public void sendHtmlEmail( String message) throws AddressException,
	            MessagingException {
		   
		   logger.info("sending mail ");
	 
	        // sets SMTP server properties
		   
		    Properties properties = new Properties();
	        properties.put("mail.smtp.host", smtp_host);
	        properties.put("mail.smtp.port", smtp_port);
	        properties.put("mail.smtp.auth", smtp_auth);
	        properties.put("mail.smtp.starttls.enable", smtp_ssl_enable);
	 
	        // creates a new session with an authenticator
	        Authenticator auth = new Authenticator() {
	            public PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(mail_from, password);
	            }
	        };
	 
	        Session session = Session.getInstance(properties, auth);
	 
	        // creates a new e-mail message
	        Message msg = new MimeMessage(session);
	        msg.setFrom(new InternetAddress(mail_from));
	     //   InternetAddress[] toAddresses = { new InternetAddress(Receipent_list) };
	        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Receipent_list) );
	        msg.setSubject(message_subject);
	        msg.setSentDate(new Date());
	        // set plain text message
	        msg.setContent(message, "text/html");
	 
	        
	        // sends the e-mail
	        Transport.send(msg);
	 
	    }
	 


}
