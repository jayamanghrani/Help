/**
 * 
 */
package com.tcs.employeeportal.vo.cmo;

import java.io.Serializable;

import com.tcs.employeeportal.vo.cmo.EmployeePortalSessionVO;

/**
 * @author 738566
 *
 */
public class Header implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6174513222650444424L;

	/** The Constant serialVersionUID. */
	
	
	//** The event id. *//*
	private String eventID;

	/** The channel id. */
	private String applicationId;

	/** The response code. */
	private int responseCode;
	
	/** The groups. */
	private String groups;
	
	/** The employee name. */
	private String employeeName;
	
	/** The employee id. */
	private String employeeId;
	
	/** The todaysDate. */
	private String todaysDate;
	
	
	/**
	 * @return the eventID
	 */
	public String getEventID() {
		return eventID;
	}


	/**
	 * @return the todaysDate
	 */
	public String getTodaysDate() {
		return todaysDate;
	}


	/**
	 * @param todaysDate the todaysDate to set
	 */
	public void setTodaysDate(String todaysDate) {
		this.todaysDate = todaysDate;
	}


	/**
	 * @param eventID the eventID to set
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
	 * @param applicationId the applicationId to set
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
	 * @param responseCode the responseCode to set
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
	 * @param groups the groups to set
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
	 * @param employeeName the employeeName to set
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
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Header [eventID=");
		builder.append(eventID);
		builder.append(", groups=");
		builder.append(groups);
		builder.append(", responseCode=");
		builder.append(responseCode);
		builder.append(", employeeName=");
		builder.append(employeeName);
		builder.append(", employeeId=");
		builder.append(employeeId);
		builder.append(", applicationId=");
		builder.append(applicationId);
		builder.append(", todaysDate=");
		builder.append(todaysDate);
			builder.append("]");
		return builder.toString();
	}
}

