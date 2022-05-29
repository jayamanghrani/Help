//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-313 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.20 at 12:01:59 PM IST 
//


package com.nia.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for email_prop complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="email_prop">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sender_email_adddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mail_from" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="successMail_recipientList" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="errorMail_ReceipentList" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="success_subject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="error_subject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smtp_host" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smtp_port" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="smtp_auth" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="debug" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="smtp_ssl_enable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="message_body" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "email_prop", propOrder = {
    "senderEmailAdddress",
    "mailFrom",
    "password",
    "successMailRecipientList",
    "errorMailReceipentList",
    "successSubject",
    "errorSubject",
    "smtpHost",
    "smtpPort",
    "smtpAuth",
    "debug",
    "smtpSslEnable",
    "messageBody",
    "messageSignature"
})
public class EmailProp {

    @XmlElement(name = "sender_email_adddress", required = true)
    protected String senderEmailAdddress;
    @XmlElement(name = "mail_from", required = true)
    protected String mailFrom;
    @XmlElement(required = true)
    protected String password;
    @XmlElement(name = "successMail_recipientList", required = true)
    protected String successMailRecipientList;
    @XmlElement(name = "errorMail_ReceipentList", required = true)
    protected String errorMailReceipentList;
    @XmlElement(name = "success_subject", required = true)
    protected String successSubject;
    @XmlElement(name = "error_subject", required = true)
    protected String errorSubject;
    @XmlElement(name = "smtp_host", required = true)
    protected String smtpHost;
    @XmlElement(name = "smtp_port")
    protected int smtpPort;
    @XmlElement(name = "smtp_auth")
    protected boolean smtpAuth;
    protected boolean debug;
    @XmlElement(name = "smtp_ssl_enable")
    protected boolean smtpSslEnable;
    @XmlElement(name = "message_body", required = true)
    protected String messageBody;
    @XmlElement(name = "message_signature", required = true)
    protected String messageSignature;

    /**
     * Gets the value of the senderEmailAdddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderEmailAdddress() {
        return senderEmailAdddress;
    }

    /**
     * Sets the value of the senderEmailAdddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderEmailAdddress(String value) {
        this.senderEmailAdddress = value;
    }

    /**
     * Gets the value of the mailFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailFrom() {
        return mailFrom;
    }

    /**
     * Sets the value of the mailFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailFrom(String value) {
        this.mailFrom = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the successMailRecipientList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuccessMailRecipientList() {
        return successMailRecipientList;
    }

    /**
     * Sets the value of the successMailRecipientList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuccessMailRecipientList(String value) {
        this.successMailRecipientList = value;
    }

    /**
     * Gets the value of the errorMailReceipentList property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorMailReceipentList() {
        return errorMailReceipentList;
    }

    /**
     * Sets the value of the errorMailReceipentList property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorMailReceipentList(String value) {
        this.errorMailReceipentList = value;
    }

    /**
     * Gets the value of the successSubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuccessSubject() {
        return successSubject;
    }

    /**
     * Sets the value of the successSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuccessSubject(String value) {
        this.successSubject = value;
    }

    /**
     * Gets the value of the errorSubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorSubject() {
        return errorSubject;
    }

    /**
     * Sets the value of the errorSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorSubject(String value) {
        this.errorSubject = value;
    }

    /**
     * Gets the value of the smtpHost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmtpHost() {
        return smtpHost;
    }

    /**
     * Sets the value of the smtpHost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmtpHost(String value) {
        this.smtpHost = value;
    }

    /**
     * Gets the value of the smtpPort property.
     * 
     */
    public int getSmtpPort() {
        return smtpPort;
    }

    /**
     * Sets the value of the smtpPort property.
     * 
     */
    public void setSmtpPort(int value) {
        this.smtpPort = value;
    }

    /**
     * Gets the value of the smtpAuth property.
     * 
     */
    public boolean isSmtpAuth() {
        return smtpAuth;
    }

    /**
     * Sets the value of the smtpAuth property.
     * 
     */
    public void setSmtpAuth(boolean value) {
        this.smtpAuth = value;
    }

    /**
     * Gets the value of the debug property.
     * 
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * Sets the value of the debug property.
     * 
     */
    public void setDebug(boolean value) {
        this.debug = value;
    }

    /**
     * Gets the value of the smtpSslEnable property.
     * 
     */
    public boolean isSmtpSslEnable() {
        return smtpSslEnable;
    }

    /**
     * Sets the value of the smtpSslEnable property.
     * 
     */
    public void setSmtpSslEnable(boolean value) {
        this.smtpSslEnable = value;
    }

    /**
     * Gets the value of the messageBody property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageBody() {
        return messageBody;
    }

    /**
     * Sets the value of the messageBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageBody(String value) {
        this.messageBody = value;
    }

	public String getMessageSignature() {
		return messageSignature;
	}

	public void setMessageSignature(String value) {
		this.messageSignature = value;
	}

}
