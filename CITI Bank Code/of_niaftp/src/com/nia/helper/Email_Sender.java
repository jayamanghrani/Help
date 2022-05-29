package com.nia.helper;


import java.io.InputStream;
import java.util.Date;
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


public class Email_Sender {



	protected static final Logger logger = Logger.getLogger( Email_Sender.class );
	//private static Email_Sender instance;
	//String fileName = "config"+System.getProperty("file.separator")+"config.properties";
	Properties props = new Properties();
	InputStream input = null;
	/* Email Configuration  */

	boolean debug;

	


	public boolean sendHtmlEmail( String mail_from,String password,
			String Receipent_list,String smtp_host,int smtp_port,
			boolean smtp_auth,boolean smtp_ssl_enable, String message_subject,String message)  {
		boolean result=false;
		// sets SMTP server properties
		final String mail_from_final=mail_from;
		final String password_final=password;
		Properties properties = new Properties();
		properties.put("mail.smtp.host", smtp_host);
		properties.put("mail.smtp.port", smtp_port);
		properties.put("mail.smtp.auth", smtp_auth);
		properties.put("mail.smtp.starttls.enable", smtp_ssl_enable);

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mail_from_final, password_final);
			}
		};

		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(mail_from));
			
		//   InternetAddress[] toAddresses = { new InternetAddress(Receipent_list) };
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Receipent_list) );
		
		msg.setSubject(message_subject);
		msg.setSentDate(new Date());
		// set plain text message
		msg.setContent(message, "text/plain");

		// sends the e-mail
		Transport.send(msg);
		result=true;
		
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			logger.error("AddressException Exception"+e.getMessage(),e);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			logger.error("MessagingException Exception"+e.getMessage(),e);
		}
		return result;
	}
	
}
