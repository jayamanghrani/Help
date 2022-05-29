package com.nia.sign;

public class DigitalSignBean {
	
	String InboundFolderSign ;
    String OutboundFolderSign;
    String KEYSTORE_FILE;
    String KEYSTORE_PWD;
    String newExtension;
   
	/**
	 * @return the inboundFolderSign
	 */
	public String getInboundFolderSign() {
		return InboundFolderSign;
	}
	/**
	 * @param inboundFolderSign the inboundFolderSign to set
	 */
	public void setInboundFolderSign(String inboundFolderSign) {
		InboundFolderSign = inboundFolderSign;
	}
	/**
	 * @return the outboundFolderSign
	 */
	public String getOutboundFolderSign() {
		return OutboundFolderSign;
	}
	/**
	 * @param outboundFolderSign the outboundFolderSign to set
	 */
	public void setOutboundFolderSign(String outboundFolderSign) {
		OutboundFolderSign = outboundFolderSign;
	}
	/**
	 * @return the kEYSTORE_FILE
	 */
	public String getKEYSTORE_FILE() {
		return KEYSTORE_FILE;
	}
	/**
	 * @param kEYSTORE_FILE the kEYSTORE_FILE to set
	 */
	public void setKEYSTORE_FILE(String kEYSTORE_FILE) {
		KEYSTORE_FILE = kEYSTORE_FILE;
	}
	/**
	 * @return the kEYSTORE_PWD
	 */
	public String getKEYSTORE_PWD() {
		return KEYSTORE_PWD;
	}
	/**
	 * @param kEYSTORE_PWD the kEYSTORE_PWD to set
	 */
	public void setKEYSTORE_PWD(String kEYSTORE_PWD) {
		KEYSTORE_PWD = kEYSTORE_PWD;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DigitalSignBean [InboundFolderSign=" + InboundFolderSign
				+ ", OutboundFolderSign=" + OutboundFolderSign
				+ ", KEYSTORE_FILE=" + KEYSTORE_FILE + ", KEYSTORE_PWD="
				+ KEYSTORE_PWD + ", newExtension=" + newExtension + "]";
	}
	/**
	 * @return the newExtension
	 */
	public String getNewExtension() {
		return newExtension;
	}
	/**
	 * @param newExtension the newExtension to set
	 */
	public void setNewExtension(String newExtension) {
		this.newExtension = newExtension;
	}
	public DigitalSignBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	

}
