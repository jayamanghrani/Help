package com.tcs.umsuser.persist.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="UMS_USR_MST")
public class UserMasterUpdate {
	
	@Id
	@Column(name = "UUM_REC_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "UMS_REC_DTLS_ID")
	@SequenceGenerator(sequenceName = "SEQ_UMS_USR_MST_REC_ID", allocationSize = 1, name = "UMS_REC_DTLS_ID")
	private long recordId;
	
	
	@Column(name = "UUM_USR_ID", unique = true, nullable = false)
	private String userId;
	
	
	@Column(name = "UUM_LOGIN_ID")
	private String loginId;
	
	@Column(name = "UUM_USR_TYPE_ID")
	private String userTypeId;
	
	@Column(name = "UUM_FIRST_NAME")
	private String firstName;
	
	@Column(name = "UUM_MIDDLE_NAME")
	private String middleName;
	
	@Column(name = "UUM_LAST_NAME")
	private String lastName;
	
	@Column(name = "UUM_BRANCH_ID")
	private String branchId;
	
	@Column(name = "UUM_GENDER")
	private String gender;
	
	@Column(name = "UUM_DOB")
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Column(name = "UUM_ADDR1")
	private String addr1;
	
	@Column(name = "UUM_ADDR2")
	private String addr2;
	
	@Column(name = "UUM_ADDR3")
	private String addr3;
	
	@Column(name = "UUM_CITY")
	private String city;
	
	@Column(name = "UUM_STATE")
	private String state;
	
	@Column(name = "UUM_COUNTRY")
	private String country;
	
	@Column(name = "UUM_PIN")
	private String pin;
	
	@Column(name = "UUM_EMAIL")
	private String email;
	
	@Column(name = "UUM_PHONE_NO")
	private String phoneNo;
	
	@Column(name = "UUM_MOBILE")
	private String mobile;
	
	@Column(name = "UUM_EXTENSION")
	private String extension;
	
	@Column(name = "UUM_IP_PHONE")
	private String ipPhone;
	
	@Column(name = "UUM_START_DATE")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name = "UUM_END_DATE")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	
	@Column(name = "UUM_STATUS")
	private String status;
	
	@Column(name = "UUM_CREATED_BY")
	private String createdBy;
	
	@Column(name = "UUM_CREATED_DATE")
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(name = "UUM_MODIFIED_BY")
	private String modifiedBy;
	
	@Column(name = "UUM_MODIFIED_DATE")
	@Temporal(TemporalType.DATE)
	private Date modifiedDate;
	
	@Column(name = "UUM_TITLE")
	private String title;
	
	@Column(name = "UUM_SUPERVISOR_ID")
	private String supervisiorId;
	
	@Column(name = "UUM_DESIGNATION")
	private String designation;
	
	@Column(name = "UUM_PASS_CHNG_DATE")
	@Temporal(TemporalType.DATE)
	private Date pwdChangeDate;
	
	@Column(name = "UUM_PASS_EXP_DATE")
	@Temporal(TemporalType.DATE)
	private Date pwdExpiryDate;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		this.userTypeId = userTypeId;
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

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getAddr3() {
		return addr3;
	}

	public void setAddr3(String addr3) {
		this.addr3 = addr3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getIpPhone() {
		return ipPhone;
	}

	public void setIpPhone(String ipPhone) {
		this.ipPhone = ipPhone;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSupervisiorId() {
		return supervisiorId;
	}

	public void setSupervisiorId(String supervisiorId) {
		this.supervisiorId = supervisiorId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getPwdChangeDate() {
		return pwdChangeDate;
	}

	public void setPwdChangeDate(Date pwdChangeDate) {
		this.pwdChangeDate = pwdChangeDate;
	}

	public Date getPwdExpiryDate() {
		return pwdExpiryDate;
	}

	public void setPwdExpiryDate(Date pwdExpiryDate) {
		this.pwdExpiryDate = pwdExpiryDate;
	}

	public long getRecordId() {
		return recordId;
	}

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	@Override
	public String toString() {
		return "UserMasterUpdate [userId=" + userId + ", recordId=" + recordId
				+ ", loginId=" + loginId + ", userTypeId=" + userTypeId
				+ ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", branchId=" + branchId
				+ ", gender=" + gender + ", dob=" + dob + ", addr1=" + addr1
				+ ", addr2=" + addr2 + ", addr3=" + addr3 + ", city=" + city
				+ ", state=" + state + ", country=" + country + ", pin=" + pin
				+ ", email=" + email + ", phoneNo=" + phoneNo + ", mobile="
				+ mobile + ", extension=" + extension + ", ipPhone=" + ipPhone
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", status=" + status + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", title=" + title
				+ ", supervisiorId=" + supervisiorId + ", designation="
				+ designation + ", pwdChangeDate=" + pwdChangeDate
				+ ", pwdExpiryDate=" + pwdExpiryDate + "]";
	}



	



}
