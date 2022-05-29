package com.tcs.umsapp.search.so.search;

import java.io.Serializable;
public class UserSearchInputs implements Serializable{
	
	private static final long serialVersionUID = -1111021374765873271L;
	
	private String userId;
	private String branchId;
	private String supervisorID;
	private String firstName;
	private String lastName;
	private String designation;
	
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
     * @return the supervisorID
     */
    public String getSupervisorID() {
        return supervisorID;
    }

    /**
     * @param supervisorID the supervisorID to set
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

    @Override
	public String toString() {
		return "UserSearchInputs [userId=" + userId + ", branchId=" + branchId
				+ ", supervisorID=" + supervisorID + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", designation=" + designation
				+ "]";
	}
	
	
	

}
