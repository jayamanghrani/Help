package com.tcs.umsapp.upload.persist.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UMS_APPL_MST")
public class Application {

	@Id
	@Column(name = "UAM_APPL_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String applicationId;

	@Column(name = "UAM_INTERNAL_APPL")
	private String internalRole;
	
	@Column(name = "UAM_APPL_NAME")
	private String applicationName;
	
	
	
	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	

	public String getInternalRole() {
		return internalRole;
	}

	public void setInternalRole(String internalRole) {
		this.internalRole = internalRole;
	}

	@Override
	public String toString() {
		return "Application [applicationId=" + applicationId
				+ ", internalRole=" + internalRole + ", applicationName="
				+ applicationName + "]";
	}


	
	
}
