package com.tcs.umsoid.vo.bean;

import java.util.Date;

/**
 * Bean class to hold Provision details retrieved from database
 * 
 * @author 1351184
 *
 */
public class ProvisionDetails {
	
	private long provisionID;
	private String roleCode;
	private long roleID;
	private String provisionAction;
	private String modifiedBy;
	private Date modifiedDate;
	private String userID;
	private String firstName;
	private String middleName;
	private String lastName;
	private String branchID;
	private String gender;
	private String email;
	private String phoneNo;
	private String designation;
	private String userTypeID;
	public ProvisionDetails() {
		super();
	}
	public ProvisionDetails(
			long provisionID, 
			String roleCode, 
			long roleID,
			String provisionAction, 
			String modifiedBy, 
			Date modifiedDate,
			String userID, 
			String firstName, 
			String middleName,
			String lastName, 
			String branchID, 
			String gender, 
			String email, 
			String phoneNo,
			String designation, 
			String userTypeID) {
		super();
		this.provisionID = provisionID;
		this.roleCode = roleCode;
		this.roleID = roleID;
		this.provisionAction = provisionAction;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.userID = userID;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.branchID = branchID;
		this.gender = gender;
		this.email = email;
		this.phoneNo = phoneNo;
		this.designation = designation;
		this.userTypeID = userTypeID;
	}
	public long getProvisionID() {
		return provisionID;
	}
	public void setProvisionID(long provisionID) {
		this.provisionID = provisionID;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public long getRoleID() {
		return roleID;
	}
	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}
	public String getProvisionAction() {
		return provisionAction;
	}
	public void setProvisionAction(String provisionAction) {
		this.provisionAction = provisionAction;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBranchID() {
		return branchID;
	}
	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getUserTypeID() {
		return userTypeID;
	}
	public void setUserTypeID(String userTypeID) {
		this.userTypeID = userTypeID;
	}
	@Override
	public String toString() {
		return "ProvisionDetails [provisionID=" + provisionID + ", roleCode="
				+ roleCode + ", roleID=" + roleID + ", provisionAction="
				+ provisionAction + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", userID=" + userID
				+ ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", branchID=" + branchID
				+ ", gender=" + gender + ", email=" + email + ", phoneNo="
				+ phoneNo + ", designation=" + designation + ", userTypeID="
				+ userTypeID + "]";
	}
	
	
	
	
}
