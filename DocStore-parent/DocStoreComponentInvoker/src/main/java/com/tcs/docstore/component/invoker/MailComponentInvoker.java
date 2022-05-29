/**
 * 
 */
package com.tcs.docstore.component.invoker;

import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import com.tcs.docstore.component.invoker.MailComponentInvoker;
import com.tcs.docstore.config.utils.UtilProperties;
import com.tcs.docstore.email.asbo.request.EmailServiceRequestASBO;
import com.tcs.docstore.exception.cmo.IntegrationTechnicalException;
import com.tcs.docstore.vo.cmo.DocStoreRequestObject;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;
import com.tcs.docstore.vo.cmo.Header;

/**
 * @author 738566
 *
 */
public class MailComponentInvoker {

/** The Constant LOGGER. */
private static final Logger LOGGER = Logger.getLogger("docStoreLogger");
	

	/** The mail sender. */
	private JavaMailSenderImpl mailSender;

	/** The mail message. */
	private SimpleMailMessage mailMessage;
	
	
	
	/**
	 * Instantiates a new mail component invoker.
	 */
	public MailComponentInvoker(){
		
		mailSender =new JavaMailSenderImpl();
		mailSender.setHost(UtilProperties.getEmailIP());
		mailSender.setPort(Integer.valueOf(UtilProperties.getEmailPort()));
		
		mailMessage =new SimpleMailMessage();
		mailMessage.setFrom(UtilProperties.getFromID());
		mailMessage.setText(UtilProperties.getDefaultText());
		
	}
	
	
	/**
	 * Invoke mail.
	 * 
	 * @param requestVO
	 *            the request vo
	 * @return the ba ncs integration response vo
	 * @throws IntegrationTechnicalException
	 *             the integration technical exception
	 */
	public DocStoreResponseObject invokeMailService(
			DocStoreRequestObject requestVO)
			throws IntegrationTechnicalException {
		
		
		LOGGER.info("invoke mail service method mail component invoker");
		DocStoreResponseObject docStoreResponseObject=new DocStoreResponseObject();

		EmailServiceRequestASBO emailServiceRequestASBO=(EmailServiceRequestASBO)requestVO;
		
		Map<String,String> dataMap=emailServiceRequestASBO.getData();
		try {
			if (dataMap.containsKey("MAILTYPE")) {
			
				switch (dataMap.get("MAILTYPE")) 
				{
							
				case "FORGOTPASSWORD":

					this.sendMail(new String[] { dataMap.get("EMAILID") },
							dataMap.get("SUBJECT"), dataMap.get("MESSAGE"));

					LOGGER.info("Mail Sent successfully to: "
							+ dataMap.get("EMAILID"));
					break;
	
				}
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
			Header header=new Header();
			docStoreResponseObject.setHeader(header);
			return docStoreResponseObject;
		}
		
		return null;
	}


	/**
	 * Send mail.
	 *
	 * @param to the to
	 * @param subject the subject
	 * @param content the content
	 */
	
	public void sendMail(String[] to, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			
			
			
			LOGGER.info("To: "+to[0]);
			LOGGER.info("From: "+mailMessage.getFrom());
			LOGGER.info("Subject: "+subject);
			LOGGER.info("Content: "+content);
			
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
	

}
