package com.tcs.umsapp.upload.persist.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UMS_ROLE_MST")
public class Role {
	
	@Id
	@Column(name = "URM_ROLE_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;
	
	@Column(name = "URM_ROLE_NAME")
	private String roleName;
	
	@Column(name = "URM_APPL_ID")
	private String applicationId;
	
	@Column(name = "URM_INTERNAL_ROLE")
	private String internalRole;
	
	@Column(name = "URM_STATUS")
	private String status;

	
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName
				+ ", applicationId=" + applicationId + ", internalRole="
				+ internalRole + ", status=" + status + "]";
	}

	
	

}
