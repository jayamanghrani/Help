package com.tcs.umsapp.search.so.request;


import com.tcs.umsapp.search.vo.cmo.UmsappRequestObject;


public class GetContentRequestSO extends UmsappRequestObject {
	
	private static final long serialVersionUID = -4662629643177021288L;
	private String userId;
	private String branchId;
	private String supervisorId;
	private String firstName;
	private String lastName;
	private String designation;
	private String applicationId;
    private String roleId;

	
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
     * @return the applicationId
     */
    public String getApplicationId() {
        return applicationId;
    }


    /**
     * @param applicationId the applicationId to set
     */
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }


    /**
     * @return the roleId
     */
    public String getRoleId() {
        return roleId;
    }


    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    @Override
    public String toString() {
        return "GetContentRequestSO [userId=" + userId + ", branchId="
                + branchId + ", supervisorId=" + supervisorId + ", firstName="
                + firstName + ", lastName=" + lastName + ", designation="
                + designation + ", applicationId=" + applicationId
                + ", roleId=" + roleId + "]";
    }
	
	

}
