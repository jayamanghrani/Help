package com.tcs.umsapp.search.commons;

import java.util.Date;

public class UserSearchDetails {

    private String userId;
    private String branchId;
    private String supervisorID;
    private String firstName;
    private String lastName;
    private String designation;
    private String gender;
    private String dob;
    private String address;
    private String email;
    private String phoneNo;
    private String mobile;
    private String startDate;
    private String permissionList;

    public UserSearchDetails() {

    }

    /**
     * 
     * @param userId
     * @param branchId
     * @param supervisorID
     * @param firstName
     * @param lastName
     * @param designation
     * @param gender
     * @param dob
     * @param address
     * @param email
     * @param phoneNo
     * @param mobile
     * @param startDate
     * @param permissionList
     */
    public UserSearchDetails(String userId, String branchId,
            String supervisorID, String firstName, String lastName,
            String designation, String gender, String dob, String address,
            String email, String phoneNo, String mobile, String startDate,
            String permissionList) {
        super();
        this.userId = userId;
        this.branchId = branchId;
        this.supervisorID = supervisorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.designation = designation;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.email = email;
        this.phoneNo = phoneNo;
        this.mobile = mobile;
        this.startDate = startDate;
        this.permissionList = permissionList;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
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
     * @param branchId
     *            the branchId to set
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /**
     * @return the supervisorID
     */
    public String getSupervisorID() {
        return supervisorID;
    }

    /**
     * @param supervisorID
     *            the supervisorID to set
     */
    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
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
     * @param lastName
     *            the lastName to set
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
     * @param designation
     *            the designation to set
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
     * @param gender
     *            the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob
     *            the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
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
     * @param phoneNo
     *            the phoneNo to set
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
     * @param mobile
     *            the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the permissionList
     */
    public String getPermissionList() {
        return permissionList;
    }

    /**
     * @param permissionList
     *            the permissionList to set
     */
    public void setPermissionList(String permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String toString() {
        return "UserSearchDetails [userId=" + userId + ", branchId=" + branchId
                + ", supervisorID=" + supervisorID + ", firstName=" + firstName
                + ", lastName=" + lastName + ", designation=" + designation
                + ", gender=" + gender + ", dob=" + dob + ", address="
                + address + ", email=" + email + ", phoneNo=" + phoneNo
                + ", mobile=" + mobile + ", startDate=" + startDate
                + ", permissionList=" + permissionList + "]";
    }

}
