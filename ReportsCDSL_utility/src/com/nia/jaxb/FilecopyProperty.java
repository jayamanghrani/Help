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
 * <p>Java class for filecopy_property complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="filecopy_property">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fcopy_source" type="{http://www.example.org/config_schema}fcopy_source_prop"/>
 *         &lt;element name="fcopy_target" type="{http://www.example.org/config_schema}fcopy_target_prop"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filecopy_property", propOrder = {
    "fcopySource",
    "fcopyTarget"
})
public class FilecopyProperty {

    @XmlElement(name = "fcopy_source", required = true)
    protected FcopySourceProp fcopySource;
    @XmlElement(name = "fcopy_target", required = true)
    protected FcopyTargetProp fcopyTarget;

    /**
     * Gets the value of the fcopySource property.
     * 
     * @return
     *     possible object is
     *     {@link FcopySourceProp }
     *     
     */
    public FcopySourceProp getFcopySource() {
        return fcopySource;
    }

    /**
     * Sets the value of the fcopySource property.
     * 
     * @param value
     *     allowed object is
     *     {@link FcopySourceProp }
     *     
     */
    public void setFcopySource(FcopySourceProp value) {
        this.fcopySource = value;
    }

    /**
     * Gets the value of the fcopyTarget property.
     * 
     * @return
     *     possible object is
     *     {@link FcopyTargetProp }
     *     
     */
    public FcopyTargetProp getFcopyTarget() {
        return fcopyTarget;
    }

    /**
     * Sets the value of the fcopyTarget property.
     * 
     * @param value
     *     allowed object is
     *     {@link FcopyTargetProp }
     *     
     */
    public void setFcopyTarget(FcopyTargetProp value) {
        this.fcopyTarget = value;
    }

}
