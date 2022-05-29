package com.tcs.umsapp.general.persist.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UMS_APPL_MST")
public class AppRole {

    @Id
    @Column(name = "UAM_APPL_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String applicationId;

    @Column(name = "UAM_APPL_NAME")
    private String aoolicationName;

    @Column(name = "UAM_INTERNAL_APPL")
    private String internalRole;
    
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

    public String getAoolicationName() {
        return aoolicationName;
    }

    public void setAoolicationName(String aoolicationName) {
        this.aoolicationName = aoolicationName;
    }

	@Override
	public String toString() {
		return "AppRole [applicationId=" + applicationId + ", aoolicationName="
				+ aoolicationName + ", internalRole=" + internalRole + "]";
	}
    
    

   

}
