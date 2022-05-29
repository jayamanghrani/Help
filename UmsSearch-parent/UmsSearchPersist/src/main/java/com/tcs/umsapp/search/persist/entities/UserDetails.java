package com.tcs.umsapp.search.persist.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="UMS_USR_MST")

public class UserDetails {

	@Id
	@Column(name = "UUM_USR_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userId;
	
	@Column(name = "UUM_BRANCH_ID")
	private String branchId;
	
	@Column(name = "UUM_SUPERVISOR_ID")
	private String supervisorId; 
	
	@Column(name = "UUM_FIRST_NAME")
	private String firstName; 
	
	@Column(name = "UUM_LAST_NAME")
	private String lastName;  
	
	@Column(name = "UUM_DESIGNATION")
	private String designation;
	
	@Column(name = "UUM_GENDER")
	private String gender;
	
	@Column(name = "UUM_DOB")
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@Column(name = "UUM_ADDR1")
	private String address1;
	
	@Column(name = "UUM_ADDR2")
	private String address2;
	
	@Column(name = "UUM_ADDR3")
	private String address3;

	@Column(name = "UUM_CITY")
	private String city;
	
	@Column(name = "UUM_STATE")
	private String state;
	
	@Column(name = "UUM_PIN")
	private String pin;
	
	@Column(name = "UUM_EMAIL")
	private String email;
	
	@Column(name = "UUM_PHONE_NO")
	private String phoneNo;
	
	@Column(name = "UUM_MOBILE")
	private String mobile;
	
	@Column(name = "UUM_START_DATE")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name = "UUM_PERMISSION_LIST")
	private String permissionList;
	
	@Column(name = "UUM_STATUS")
    private String status;
	
	

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }



    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }



    /**
     * @return the branchId
     */
    public String getBranchId() {
        return branchId;
    }



    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }



    /**
     * @return the supervisorId
     */
    public String getSupervisorId() {
        return supervisorId;
    }



    /**
     * @param supervisorId the supervisorId to set
     */
    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }



    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }



    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }



    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    /**
     * @return the designation
     */
    public String getDesignation() {
        return designation;
    }



    /**
     * @param designation the designation to set
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }



    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }



    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }



    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }



    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }



    /**
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }



    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }



    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }



    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }



    /**
     * @return the address3
     */
    public String getAddress3() {
        return address3;
    }



    /**
     * @param address3 the address3 to set
     */
    public void setAddress3(String address3) {
        this.address3 = address3;
    }



    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }



    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }



    /**
     * @return the state
     */
    public String getState() {
        return state;
    }



    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }



    /**
     * @return the pin
     */
    public String getPin() {
        return pin;
    }



    /**
     * @param pin the pin to set
     */
    public void setPin(String pin) {
        this.pin = pin;
    }



    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }



    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }



    /**
     * @return the phoneNo
     */
    public String getPhoneNo() {
        return phoneNo;
    }



    /**
     * @param phoneNo the phoneNo to set
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }



    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }



    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }



    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }



    /**
     * @return the permissionList
     */
    public String getPermissionList() {
        return permissionList;
    }



    /**
     * @param permissionList the permissionList to set
     */
    public void setPermissionList(String permissionList) {
        this.permissionList = permissionList;
    }



    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }



    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }



    @Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", branchID=" + branchId
				+ ", supervisorId=" + supervisorId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", designation=" + designation
				+ ", gender=" + gender + ", dob=" + dob + ", address1="
				+ address1 + ", address2=" + address2 + ", address3="
				+ address3 + ", city=" + city + ", state=" + state + ", pin="
				+ pin + ", email=" + email + ", phoneNo=" + phoneNo
				+ ", mobile=" + mobile + ", startDate=" + startDate
				+ ", permissionList=" + permissionList + "]";
	}

	
	
	
	
	
	
}

