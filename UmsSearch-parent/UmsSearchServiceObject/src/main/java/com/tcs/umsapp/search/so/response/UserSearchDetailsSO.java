/**
 * 
 */
package com.tcs.umsapp.search.so.response;

import java.util.Date;

import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;

/**
 * @author 926814
 *
 */
public class UserSearchDetailsSO extends UmsappResponseObject{
    /**
     * 
     */
    private static final long serialVersionUID = 4808297482765810340L;
    private String userId;
    private String branchId;
    private String supervisorID;
    private String firstName;
    private String lastName;
    private String designation;
    private String gender;
    private Date dob;
    
    
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


    @Override
    public String toString() {
        return "UserSearchDetailsSO [userId=" + userId + ", branchId="
                + branchId + ", supervisorID=" + supervisorID + ", firstName="
                + firstName + ", lastName=" + lastName + ", designation="
                + designation + ", gender=" + gender + ", dob=" + dob + "]";
    }
    
    
}
