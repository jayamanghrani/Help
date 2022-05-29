package com.tcs.umsoid.vo.bean;

import javax.naming.directory.Attribute;

/**
 * 
 * Bean class for holding user details for OID users
 * 
 * @author 1351184
 *
 */
public class OIDUserDetails {

	private String distinguishedName;
	private String userID;
	private String cn;
	private String givenName;
	private String middleName;
	private String lastName;
	private String branchID;
	private String phoneNumber;
	private String gender;
	private String designation;
	private String passwordChangeFlag;
	private String lastSuccessfulLogin;
	private String previousLogin;
	private String twoFA;
	private String defaultProfileGroup;
	private String password;
	private String mail;
	private String status;
	
	public OIDUserDetails() {
		super();
	}

	public OIDUserDetails(String distinguishedName, String userID, String cn,
			String givenName, String middleName, String lastName, String branchID,
			String phoneNumber, String gender, String designation,
			String passwordChangeFlag, String lastSuccessfulLogin,
			String previousLogin, String twoFA, String defaultProfileGroup,
			String password, String mail, String status) {
		super();
		
		
		this.distinguishedName = distinguishedName;
		this.userID = userID;
		this.cn = cn;
		this.givenName = givenName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.branchID = branchID;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.designation = designation;
		this.passwordChangeFlag = passwordChangeFlag;
		this.lastSuccessfulLogin = lastSuccessfulLogin;
		this.previousLogin = previousLogin;
		this.twoFA = twoFA;
		this.defaultProfileGroup = defaultProfileGroup;
		this.password = password;
		this.mail = mail;
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setStatus(Attribute status) {
		this.status = status.toString().split(":")[1].trim();
	}
	public String getDistinguishedName() {
		return distinguishedName;
	}
	public void setDistinguishedName(Attribute distinguishedName) {
		this.distinguishedName = distinguishedName.toString().split(":")[1].trim();
	}
	public void setDistinguishedName(String distinguishedName) {
		this.distinguishedName = distinguishedName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(Attribute userID) {
		this.userID = userID.toString().split(":")[1].trim();
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(Attribute cn) {
		this.cn = cn.toString().split(":")[1].trim();
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(Attribute givenName) {
		this.givenName = givenName.toString().split(":")[1].trim();
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(Attribute middleName) {
		this.middleName = middleName.toString().split(":")[1].trim();
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(Attribute lastName) {
		this.lastName = lastName.toString().split(":")[1].trim();
	}
	public String getBranchID() {
		return branchID;
	}
	public void setBranchID(Attribute branchID) {
		this.branchID = branchID.toString().split(":")[1].trim();
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Attribute phoneNumber) {
		this.phoneNumber = phoneNumber.toString().split(":")[1].trim();
	}
	public String getGender() {
		return gender;
	}
	public void setGender(Attribute gender) {
		this.gender = gender.toString().split(":")[1].trim();
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(Attribute designation) {
		this.designation = designation.toString().split(":")[1].trim();
	}
	public String getPasswordChangeFlag() {
		return passwordChangeFlag;
	}
	public void setPasswordChangeFlag(Attribute passwordChangeFlag) {
		this.passwordChangeFlag = passwordChangeFlag.toString().split(":")[1].trim();
	}
	public String getLastSuccessfulLogin() {
		return lastSuccessfulLogin;
	}
	public void setLastSuccessfulLogin(Attribute lastSuccessfulLogin) {
		this.lastSuccessfulLogin = lastSuccessfulLogin.toString().split(":")[1].trim();
	}
	public String getPreviousLogin() {
		return previousLogin;
	}
	public void setPreviousLogin(Attribute previousLogin) {
		this.previousLogin = previousLogin.toString().split(":")[1].trim();
	}
	public String getTwoFA() {
		return twoFA;
	}
	public void setTwoFA(Attribute twoFA) {
		this.twoFA = twoFA.toString().split(":")[1].trim();
	}
	public String getDefaultProfileGroup() {
		return defaultProfileGroup;
	}
	public void setDefaultProfileGroup(Attribute defaultProfileGroup) {
		this.defaultProfileGroup = defaultProfileGroup.toString().split(":")[1].trim();
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(Attribute password) {
		this.password = password.toString().split(":")[1].trim();
	}
	public String getMail() {
		return mail;
	}
	public void setMail(Attribute mail) {
		this.mail = mail.toString().split(":")[1].trim();
	}
	@Override
	public String toString() {
		return "OIDUserDetails [distinguishedName=" + distinguishedName
				+ ", userID=" + userID + ", cn=" + cn + ", givenName="
				+ givenName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", branchID=" + branchID + ", phoneNumber="
				+ phoneNumber + ", gender=" + gender + ", designation="
				+ designation + ", passwordChangeFlag=" + passwordChangeFlag
				+ ", lastSuccessfulLogin=" + lastSuccessfulLogin
				+ ", previousLogin=" + previousLogin + ", twoFA=" + twoFA
				+ ", defaultProfileGroup=" + defaultProfileGroup
				+ ", password=" + password + ", mail=" + mail + ", status="
				+ status + "]";
	}
	
	
	
}
