/**
 * 
 */
package com.tcs.umsuser.request.asbo;

import java.util.Date;

import com.tcs.umsuser.vo.cmo.UmsRequestObject;


/**
 * @author 926814
 *
 */
public class UpdateUserRequestASBO extends UmsRequestObject{

	private static final long serialVersionUID = 1L;
		private String userId;
	    private String title;
	    private String firstName;
	    private String middleName;
	    private String lastName;
	    private String branchId;
	    private String gender;
	    private Date dob;
	    private String addr1;
	    private String addr2;
	    private String addr3;
	    private String city;
	    private String state; 
	    private String country;
	    private String pin;
	    private String email;
	    private String phoneNo;
	    private String mobileNo;
	    private String extension;
	    private String ipPhone;
	    private Date startDate;
	    private Date endDate;
	    private String supervisorId;
	    private String designation;
	    private String createdBy;
	    private Date createdDate;
	    private String modifiedBy;
	    private Date modifiedDate;
	    private String status;
	    private Date effectiveStartDate;
	    private String flagAction; 
	    
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
		public String getMobileNo() {
			return mobileNo;
		}
		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
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

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		@Override
		public String toString() {
			return "UpdateUserRequestASBO [userId=" + userId + ", title="
					+ title + ", firstName=" + firstName + ", middleName="
					+ middleName + ", lastName=" + lastName + ", branchId="
					+ branchId + ", gender=" + gender + ", dob=" + dob
					+ ", addr1=" + addr1 + ", addr2=" + addr2 + ", addr3="
					+ addr3 + ", city=" + city + ", state=" + state
					+ ", country=" + country + ", pin=" + pin + ", email="
					+ email + ", phoneNo=" + phoneNo + ", mobileNo=" + mobileNo
					+ ", extension=" + extension + ", ipPhone=" + ipPhone
					+ ", startDate=" + startDate + ", endDate=" + endDate
					+ ", supervisorId=" + supervisorId + ", designation="
					+ designation + ", createdBy=" + createdBy
					+ ", createdDate=" + createdDate + ", modifiedBy="
					+ modifiedBy + ", modifiedDate=" + modifiedDate
					+ ", status=" + status + ", effectiveStartDate="
					+ effectiveStartDate + ", flagAction=" + flagAction
					+ "]";
		}
	    


}
