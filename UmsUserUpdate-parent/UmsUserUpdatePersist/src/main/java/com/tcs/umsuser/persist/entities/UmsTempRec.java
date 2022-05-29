package com.tcs.umsuser.persist.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="UMS_USR_TEMP_REC")
public class UmsTempRec implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name="UUTR_REC_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="UMS_TEMP_SEQ")
	@SequenceGenerator(sequenceName = "SEQ_UMS_USR_TEMP_ID", allocationSize = 1, name = "UMS_TEMP_SEQ")
    private long recordId;
    
    @Column(name="UUTR_USR_ID")
    private String userId;

    @Column(name="UUTR_TITLE")
    private String title;
    
    @Column(name="UUTR_FIRST_NAME")
    private String firstName;
    
    @Column(name="UUTR_MIDDLE_NAME")
    private String middlename;
    
    @Column(name="UUTR_LAST_NAME")
    private String lastName;
    
    @Column(name="UUTR_BRANCH_ID")
    private String branchId;
    
    @Column(name="UUTR_GENDER")
    private String gender;
   
    @Column(name="UUTR_DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    
    @Column(name="UUTR_ADDR1")
    private String addr1;
    
    @Column(name="UUTR_ADDR2")
    private String addr2;
    
    @Column(name="UUTR_ADDR3")
    private String addr3;
    
    @Column(name="UUTR_CITY")
    private String city;
    
    @Column(name="UUTR_STATE")
    private String state;
    
    @Column(name="UUTR_COUNTRY")
    private String country;
    
    @Column(name="UUTR_PIN")
    private String pin;
    
    @Column(name="UUTR_EMAIL")
    private String email;
    
    @Column(name="UUTR_PHONE_NO")
    private String phoneNo;
    
    @Column(name="UUTR_MOBILE")
    private String mobile;
    
    @Column(name="UUTR_EXTENSION")
    private String extension;
    
    @Column(name="UUTR_IP_PHONE")
    private String ipPhone;
    
    @Column(name="UUTR_START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    
    @Column(name="UUTR_END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    
    @Column(name="UUTR_SUPERVISOR_ID")
    private String supervisorId;
    
    @Column(name="UUTR_DESIGNATION")
    private String designation;
    
    @Column(name="UUTR_CREATED_BY")
    private String createdBy;

    @Column(name="UUTR_CREATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    
    @Column(name="UUTR_MODIFIED_BY")
    private String modifiedBy;
   
    @Column(name="UUTR_MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date modifiedDate;
    
    @Column(name="UUTR_STATUS")
    private String status;
    
    @Column(name="UUTR_EFFCTIVE_START_DATE")
    @Temporal(TemporalType.DATE)
    private Date effectiveStartDate;
    
    @Column(name="UUTR_FLG_ACTION")
    private String flagAction;
    
    @Column(name="UUTR_REC_STATUS")
    private String recStatus;
    
    @Column(name="UUTR_REC_OPERATION")
    private String recOperation;
    
    @Column(name="UUTR_REC_SQLCODE")
    private String recSqlCode;
    
    @Column(name="UUTR_REC_SQLERRM")
    private String sqlError;

    @Column(name="UUTR_REC_REMARK")
    private String remark;
    
    @Column(name="UUTR_REC_CREATED_BY")
    private String recCreatedBy;

    @Column(name="UUTR_REC_CREATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date recCreatedDate;
    
    @Column(name="UUTR_REC_MODIFIED_BY")
    private String recModifiedBy;

    @Column(name="UUTR_REC_MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date recModifiedDate;
    
	public long getRecordId() {
		return recordId;
	}

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
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

	public String getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEffectiveStartDate() {
		return effectiveStartDate;
	}

	public void setEffectiveStartDate(Date effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

	public String getFlagAction() {
		return flagAction;
	}

	public void setFlagAction(String flagAction) {
		this.flagAction = flagAction;
	}

	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}

	public String getRecOperation() {
		return recOperation;
	}

	public void setRecOperation(String recOperation) {
		this.recOperation = recOperation;
	}

	public String getRecSqlCode() {
		return recSqlCode;
	}

	public void setRecSqlCode(String recSqlCode) {
		this.recSqlCode = recSqlCode;
	}

	public String getSqlError() {
		return sqlError;
	}

	public void setSqlError(String sqlError) {
		this.sqlError = sqlError;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRecCreatedBy() {
		return recCreatedBy;
	}

	public void setRecCreatedBy(String recCreatedBy) {
		this.recCreatedBy = recCreatedBy;
	}

	public Date getRecCreatedDate() {
		return recCreatedDate;
	}

	public void setRecCreatedDate(Date recCreatedDate) {
		this.recCreatedDate = recCreatedDate;
	}

	public String getRecModifiedBy() {
		return recModifiedBy;
	}

	public void setRecModifiedBy(String recModifiedBy) {
		this.recModifiedBy = recModifiedBy;
	}

	public Date getRecModifiedDate() {
		return recModifiedDate;
	}

	public void setRecModifiedDate(Date recModifiedDate) {
		this.recModifiedDate = recModifiedDate;
	}

	@Override
	public String toString() {
		return "UmsTempRec [recordId=" + recordId + ", userId=" + userId
				+ ", title=" + title + ", firstName=" + firstName
				+ ", middlename=" + middlename + ", lastName=" + lastName
				+ ", branchId=" + branchId + ", gender=" + gender + ", dob="
				+ dob + ", addr1=" + addr1 + ", addr2=" + addr2 + ", addr3="
				+ addr3 + ", city=" + city + ", state=" + state + ", country="
				+ country + ", pin=" + pin + ", email=" + email + ", phoneNo="
				+ phoneNo + ", mobile=" + mobile + ", extension=" + extension
				+ ", ipPhone=" + ipPhone + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", supervisorId=" + supervisorId
				+ ", designation=" + designation + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", status=" + status
				+ ", effectiveStartDate=" + effectiveStartDate
				+ ", flagAction=" + flagAction + ", recStatus=" + recStatus
				+ ", recOperation=" + recOperation + ", recSqlCode="
				+ recSqlCode + ", sqlError=" + sqlError + ", remark=" + remark
				+ ", recCreatedBy=" + recCreatedBy + ", recCreatedDate="
				+ recCreatedDate + ", recModifiedBy=" + recModifiedBy
				+ ", recModifiedDate=" + recModifiedDate  + "]";
	}



	
	
 
    
}
