//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-313 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.08 at 04:53:41 PM IST 
//


package com.nia.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fcopy_target_prop complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fcopy_target_prop">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="t_path" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="t_fname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="t_ip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="t_port" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="t_username" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="t_password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fcopy_target_prop", propOrder = {
    "tPath",
    "tFname",
    "tIp",
    "tPort",
    "tUsername",
    "tPassword"
})
public class FcopyTargetProp {

    @XmlElement(name = "t_path", required = true)
    protected String tPath;
    @XmlElement(name = "t_fname", required = true)
    protected String tFname;
    @XmlElement(name = "t_ip", required = true)
    protected String tIp;
    @XmlElement(name = "t_port")
    protected int tPort;
    @XmlElement(name = "t_username", required = true)
    protected String tUsername;
    @XmlElement(name = "t_password", required = true)
    protected String tPassword;
    
    
  /*  @XmlElement(name="filename_prefix" , required=true)
    protected String filename_prefix;*/
    /**
     * Gets the value of the tPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTPath() {
        return tPath;
    }

    /**
     * Sets the value of the tPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTPath(String value) {
        this.tPath = value;
    }

    /**
     * Gets the value of the tFname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTFname() {
        return tFname;
    }

    /**
     * Sets the value of the tFname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTFname(String value) {
        this.tFname = value;
    }

    /**
     * Gets the value of the tIp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTIp() {
        return tIp;
    }

    /**
     * Sets the value of the tIp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTIp(String value) {
        this.tIp = value;
    }

    /**
     * Gets the value of the tPort property.
     * 
     */
    public int getTPort() {
        return tPort;
    }

    /**
     * Sets the value of the tPort property.
     * 
     */
    public void setTPort(int value) {
        this.tPort = value;
    }

    /**
     * Gets the value of the tUsername property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTUsername() {
        return tUsername;
    }

    /**
     * Sets the value of the tUsername property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTUsername(String value) {
        this.tUsername = value;
    }

    /**
     * Gets the value of the tPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTPassword() {
        return tPassword;
    }

    /**
     * Sets the value of the tPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTPassword(String value) {
        this.tPassword = value;
    }

}
