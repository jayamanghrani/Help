package com.tcs.umsapp.search.vo.cmo;

import java.io.Serializable;

public class Header implements Serializable {

    private static final long serialVersionUID = 1L;

    private String eventID;
    private String applicationId;
    private int responseCode;
    private String groups;
    private String employeeName;
    private String employeeId;
    private String todaysDate;

    /**
     * @return the eventID
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * @param eventID
     *            the eventID to set
     */
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    /**
     * @return the applicationId
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * @param applicationId
     *            the applicationId to set
     */
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * @return the responseCode
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode
     *            the responseCode to set
     */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @return the groups
     */
    public String getGroups() {
        return groups;
    }

    /**
     * @param groups
     *            the groups to set
     */
    public void setGroups(String groups) {
        this.groups = groups;
    }

    /**
     * @return the employeeName
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * @param employeeName
     *            the employeeName to set
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * @return the employeeId
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId
     *            the employeeId to set
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the todaysDate
     */
    public String getTodaysDate() {
        return todaysDate;
    }

    /**
     * @param todaysDate
     *            the todaysDate to set
     */
    public void setTodaysDate(String todaysDate) {
        this.todaysDate = todaysDate;
    }

}
