/**
 * 
 */
package com.tcs.docstore.component.invoker;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.tcs.docstore.config.utils.UtilProperties;
import com.tcs.docstore.db.asbo.request.DocUploadDBRequestASBO;
import com.tcs.docstore.vo.cmo.DocStoreRequestObject;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 738566
 *
 */
public class DocumentEmailCompInvoker {
	/**
	 * Instantiates a new mail component invoker.
	 */
	public DocumentEmailCompInvoker(){
		
		mailSender =new JavaMailSenderImpl();
		mailSender.setHost(UtilProperties.getEmailIP());
		mailSender.setPort(Integer.valueOf(UtilProperties.getEmailPort()));
		
		mailMessage =new SimpleMailMessage();
		mailMessage.setFrom(UtilProperties.getFromID());
		mailMessage.setText(UtilProperties.getDefaultText());
		
	}
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger("docStoreLogger");
		

		/** The mail sender. */
		private JavaMailSenderImpl mailSender;

		/** The mail message. */
		private SimpleMailMessage mailMessage;

	public DocStoreResponseObject invokeMailService(String mailDetail,DocUploadDBRequestASBO dudbreasbo){
		
		LOGGER.info("inside db invoker service class!!");
		// TODO Auto-generated method stub
		String[] to ={mailDetail};
		String mailSubject="Document Store File Upload - "+dudbreasbo.getFileName();
	//	String mailContent="The document named "+dudbreasbo.getFileName()+" is uploaded by  Employee Number "+dudbreasbo.getUserIDUploader();
		String mailContent="<p>Dear All,<br/></p>\n<p>The following document has been uploaded  under IRDAI- Communication/Exposure Draft  by "+ dudbreasbo.getUserIDUploader()+
				".<br/><br/>File Name :"+  dudbreasbo.getFileName() + "<br/>Document Type :"+  dudbreasbo.getDocType() +"<br/><br/>Steps to search the document :<br/><br/>1. Go to Document Store<br/>2. Click on Document Search<br/>3. Enter Search Criteria as below :<br/><br/>Department Name - CMD_Board Secretariat<br/>Document Upload date - Today's date (mail received date) <br/><br/><b>Note:Please review and submit your comments to cs.nia@newindia.co.in</b> <br/> </p>\n<p><br/>Thanks & Regards<br/>Company Secretary<br/>New India Assurance Co.Ltd</p>";
		sendMail(to,mailSubject,mailContent);
		return null;
	}
	
	
	public void sendMail(String[] to, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			
			LOGGER.info("To: "+to[0]);
			LOGGER.info("From: "+mailMessage.getFrom());
			LOGGER.info("Subject: "+subject);
			LOGGER.debug("Content: "+content);
			
			MimeMessageHelper helper = null;
			try {
				helper = new MimeMessageHelper(message, true);
			} catch (javax.mail.MessagingException e) {
				LOGGER.error(e.getMessage(), e);
			}
			try {
				helper.setTo(to);
			} catch (javax.mail.MessagingException e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.getMessage(), e);
			}
			helper.setFrom(mailMessage.getFrom());
			helper.setSubject(subject);
			if(content!=null){
				helper.setText(content, true);
			}else{
				helper.setText(mailMessage.getText(), true);
			}
			
		} catch (MessagingException ex) {
			throw new MailParseException(ex);
		}
		mailSender.send(message);
	}
	
	
/*	public static String getProperty(String property) {
		Properties prop = new Properties();
		InputStream input = null;
		try {

			input = MailComponentInvoker.class.getClassLoader()
					.getResourceAsStream("config.properties");
			prop.load(input);
			return prop.getProperty(property);
		} catch (IOException ex) {
			LOGGER.info("Failed to read properties file", ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException ex) {
					LOGGER.info("Failed to read properties file", ex);
				}
			}
		}
		return property;
	}*/
	
	
}
