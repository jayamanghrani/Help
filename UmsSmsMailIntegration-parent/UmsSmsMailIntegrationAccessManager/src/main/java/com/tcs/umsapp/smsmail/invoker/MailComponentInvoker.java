package com.tcs.umsapp.smsmail.invoker;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import com.tcs.umsapp.smsmail.util.UtilProperties;

public class MailComponentInvoker {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(MailComponentInvoker.class);

    /** The mail sender. */
    private JavaMailSenderImpl mailSender;

    /** The mail message. */
    private SimpleMailMessage mailMessage;

    /**
     * Instantiates a new mail component invoker.
     */
    public MailComponentInvoker() {

        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(UtilProperties.getEmailIP());
        mailSender.setPort(Integer.valueOf(UtilProperties.getEmailPort()));
        mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(UtilProperties.getFromID());
        mailMessage.setText(UtilProperties.getDefaultText());

    }

    public int sendMail(String to, String cc, String subject, String mailBody,
            String sender) {
        int status = 0;
       
        
        MimeMessage message = mailSender.createMimeMessage();
        try {
            LOGGER.debug("To: " + to);
            LOGGER.debug("From: " + sender);
            LOGGER.debug("Subject: " + subject);
            LOGGER.debug("Content: " + mailBody);
            LOGGER.debug("cc: " + cc);

            MimeMessageHelper helper = null;
            try {
                helper = new MimeMessageHelper(message, true);
            } catch (javax.mail.MessagingException e) {
                status = 1;
                LOGGER.error(e.getMessage(), e);
            }
            try {
                helper.setTo(to);
            } catch (javax.mail.MessagingException e) {
                status = 1;
                LOGGER.error(e.getMessage(), e);
            }
            if (sender != null && !sender.isEmpty()) {
                try {
                    helper.setFrom(sender);
                } catch (javax.mail.MessagingException e) {
                    status = 1;
                    LOGGER.error(e.getMessage(), e);
                }
            } else {
                helper.setFrom(mailMessage.getFrom());
            }
            if (subject != null && !subject.isEmpty()) {
                helper.setSubject(subject);
            } else {
                helper.setSubject(UtilProperties.getDefaultSubject());
            }
            if (mailBody != null && !mailBody.isEmpty()) {
                helper.setText(mailBody, true);
            } else {
                helper.setText(mailMessage.getText());
            }
            LOGGER.debug(""+mailSender.getHost());
            LOGGER.debug(""+mailSender.getPort());
            LOGGER.debug(""+mailSender.getUsername());
            LOGGER.debug(""+mailSender.getUsername());
            
        } catch (MessagingException ex) {
            status = 1;
        }
        
        
        try {
            mailSender.send(message);
        } catch (MailException ex) {
            LOGGER.error("Mail exception : " + ex.getMessage());
            status = 1;
        }

        return status;
    }

}
