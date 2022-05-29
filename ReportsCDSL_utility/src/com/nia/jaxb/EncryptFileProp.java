package com.nia.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fencrypt_prop", propOrder = {
	"shFilePath",	
    "plainFilePath",
    "plainFileBackupPath",
    "encryptedFilePath",
    "encryptedFileBackupPath"
})
public class EncryptFileProp {
	//
	// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-313 
	// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
	// Any modifications to this file will be lost upon recompilation of the source schema. 
	// Generated on: 2015.05.08 at 04:53:41 PM IST 
	//



	
	

		 @XmlElement(name = "shFile_Path", required = true)
		    protected String shFilePath;
		
	    @XmlElement(name = "plainFile_Path", required = true)
	    protected String plainFilePath;
	    @XmlElement(name = "plainFileBackup_Path", required = true)
	    protected String plainFileBackupPath;
	    @XmlElement(name = "encryptedFile_Path")
	    protected String encryptedFilePath;
	    
	    @XmlElement(name = "encryptedFileBackup_Path")
	    protected String encryptedFileBackupPath;
	    
	    /**
	     * Gets the value of the plainFilePath property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getShFilePath() {
	        return shFilePath;
	    }

	    /**
	     * Sets the value of the plainFilePath property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setShFilePath(String value) {
	        this.shFilePath = value;
	    }
	    
	    /**
	     * Gets the value of the plainFilePath property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getPlainFilePath() {
	        return plainFilePath;
	    }

	    /**
	     * Sets the value of the plainFilePath property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setPlainFilePath(String value) {
	        this.plainFilePath = value;
	    }

	    /**
	     * Gets the value of the plainFileBackupPath property.
	     * 
	     * @return
	     *     possible object is
	     *     {@link String }
	     *     
	     */
	    public String getPlainFileBackupPath() {
	        return plainFileBackupPath;
	    }

	    /**
	     * Sets the value of the plainFileBackupPath property.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setPlainFileBackupPath(String value) {
	        this.plainFileBackupPath = value;
	    }

	    /**
	     * Gets the value of the encryptedFilePath property.
	     * 
	     */
	    public String getEncryptedFilePath() {
	        return encryptedFilePath;
	    }

	    /**
	     * Sets the value of the encryptedFilePath property.
	     * 
	     */
	    public void setEncryptedFilePath(String value) {
	        this.encryptedFilePath = value;
	    }
	    
	    
	    /**
	     * Gets the value of the encryptedFilePath property.
	     * 
	     */
	    public String getEncryptedFileBackupPath() {
	        return encryptedFileBackupPath;
	    }

	    /**
	     * Sets the value of the encryptedFilePath property.
	     * 
	     */
	    public void setEncryptedFileBackupPath(String value) {
	        this.encryptedFileBackupPath = value;
	    }

	}

	

