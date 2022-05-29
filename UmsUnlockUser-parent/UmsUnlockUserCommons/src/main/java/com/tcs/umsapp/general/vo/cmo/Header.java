package com.tcs.umsapp.general.vo.cmo;

import java.io.Serializable;

public class Header implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String eventID;
	private String applicationId;
	private int responseCode;
	private String groups;
	private String employeeName;
	private String employeeId;
	private String todaysDate;

	
	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getTodaysDate() {
		return todaysDate;
	}

	public void setTodaysDate(String todaysDate) {
		this.todaysDate = todaysDate;
	}
	
	
	

}

