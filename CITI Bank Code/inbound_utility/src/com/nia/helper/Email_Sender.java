package com.nia.helper;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.hp.gagawa.java.Document;
import com.hp.gagawa.java.elements.Br;
import com.nia.jaxb.FIdentifier;

public class Email_Sender {



	protected static final Logger logger = Logger.getLogger( Email_Sender.class );
	//private static Email_Sender instance;
	//String fileName = "config"+System.getProperty("file.separator")+"config.properties";
	Properties props = new Properties();
	InputStream input = null;
	/* Email Configuration  */

	boolean debug;

	ArrayList<String> attachedFiles;








	public boolean sendEmailWithAttachments(String sender_email_adddress,String mail_from,String password,
			String Receipent_list,String message_subject,String smtp_host,int smtp_port,
			boolean smtp_auth,boolean smtp_ssl_enable,String localFilePath,
			List<FIdentifier> sprefix,String message_body)   {
		boolean result=false;
		attachedFiles=new ArrayList<String>();

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
		try {
			Session session = Session.getInstance(properties, auth);

			// creates a new e-mail message
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(mail_from));

			//   InternetAddress[] toAddresses = { new InternetAddress(Receipent_list) };
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Receipent_list) );
			msg.setSubject(message_subject);
			msg.setSentDate(new Date());


			Multipart multipart = new MimeMultipart();

			// creates body part for the message
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			//message_body=message_body.replace("Team","Team,"+ System.getProperty("line.separator"));



			messageBodyPart.setContent(message_body, "text/html");
			multipart.addBodyPart(messageBodyPart);
			// creates body part for the attachment
			MimeBodyPart attachPart ;
			int fcount=0;
			File folder = new File(localFilePath);
			//int fileEntryCount = folder.listFiles().length;
			for (final File fileEntry : folder.listFiles()) {
				if (!fileEntry.isDirectory()) {


					File f = new File(localFilePath+System.getProperty("file.separator")+fileEntry.getName());
					String fileName = f.getName();
					/* for (FIdentifier fidentifier: sprefix)
					   {
					if(fileName.startsWith(fidentifier.getFPrefix()) ){*/
					fcount = fcount+1;
					//String attachFile = "D:/Documents/MyFile.mp4";
					attachPart=new MimeBodyPart();
					attachPart.attachFile(f);
					// adds attachment to the multipart
					multipart.addBodyPart(attachPart);
					attachedFiles.add(fileName);

					//}
					//   }
				}
			}

			msg.setContent(multipart);

			if(fcount==0){
				logger.debug("No files of specified format in the folder and hence mail not sent");
				result = true;
			}

			else{		
				// sends the e-mail
				Transport.send(msg);

				logger.info(fcount +" files are attached to the mail");
				result = true;
			}
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			logger.error("AddressException Exception"+e.getMessage(),e);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			logger.error("MessagingException Exception"+e.getMessage(),e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("IOException Exception"+e.getMessage(),e);
		}
		return result;
	}

	/*	public void moveFiles() throws IOException, InterruptedException{

		for(int i=0; i<attachedFiles.size();i++){
			String fileName=attachedFiles.get(i).toString();
			Runtime run = Runtime.getRuntime();  
			String cmd = "mv "+localFilePath+System.getProperty("file.separator")+fileName+" "+ backupFilePath; 
			logger.info("Transferring file "+cmd);
			Process  p = run.exec(cmd);  
			p.getErrorStream();  
			p.waitFor();
		}



	}*/

	public boolean sendEmail( String mail_from,String password,
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
	
	public boolean sendHTMLEmail( String mail_from,String password,
			String Receipent_list,String smtp_host,int smtp_port,
			boolean smtp_auth,boolean smtp_ssl_enable, String message_subject,Document document)  {
		boolean result=false;
		
		document.body.appendChild(new Br());	
		document.body.appendText(Constants.signature);
		document.body.appendChild(new Br());
		
		System.out.println(System.getProperty("line.separator").toString());
		String signature[] = Constants.signature_constant.split("\n");
		for(int i=0;i<signature.length;i++){
			document.body.appendText(signature[i]);
			document.body.appendChild(new Br());
			
		}
		
		
		String message=document.write();
		message =Constants.salutation +"<br />"+message;
		
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
		msg.setContent(message, "text/html");

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
