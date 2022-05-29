/**
 * 
 */
package com.tcs.employeeportal.component.invoker;

import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.tcs.employeeportal.component.invoker.MailComponentInvoker;
import com.tcs.employeeportal.config.utils.UtilProperties;
import com.tcs.employeeportal.email.asbo.request.EmailServiceRequestASBO;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;
import com.tcs.employeeportal.vo.cmo.Header;

/**
 * @author 738566
 *
 */
public class MailComponentInvoker {

/** The Constant LOGGER. */
private static final Logger LOGGER = Logger.getLogger("empPortalLogger");
	

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
	 * @return the employee portal response vo
	 * @throws IntegrationTechnicalException
	 *             the integration technical exception
	 */
	public EmployeePortalResponseObject invokeMailService(
			EmployeePortalRequestObject requestVO)
			throws IntegrationTechnicalException {
		
		
		LOGGER.info("invoke mail service method mail component invoker");
		EmployeePortalResponseObject employeePortalResponseObject=new EmployeePortalResponseObject();

		EmailServiceRequestASBO emailServiceRequestASBO=(EmailServiceRequestASBO)requestVO;
		
		Map<String,String> dataMap=emailServiceRequestASBO.getData();
		try {
			if (dataMap.containsKey("MAILTYPE")) {
			
				switch (dataMap.get("MAILTYPE")) 
				{

		
				case "FORGOTPASSWORD":

					this.sendMail(new String[] { dataMap.get("EMAILID") },
							dataMap.get("SUBJECT"), dataMap.get("MESSAGE"));

					LOGGER.debug("Mail Sent successfully to: "
							+ dataMap.get("EMAILID"));
					break;

				}
			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getStackTrace());
			Header header=new Header();
			employeePortalResponseObject.setHeader(header);
			return employeePortalResponseObject;
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
			LOGGER.debug("From: "+mailMessage.getFrom());
			LOGGER.debug("Subject: "+subject);
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
	
}
